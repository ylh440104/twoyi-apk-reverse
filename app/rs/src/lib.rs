// This Source Code Form is subject to the terms of the Mozilla Public
// License, v. 2.0. If a copy of the MPL was not distributed with this
// file, You can obtain one at https://mozilla.org/MPL/2.0/.

use jni::objects::{GlobalRef, JValue};
use jni::sys::{jclass, jfloat, jint, jobject, JNI_ERR, jstring};
use jni::JNIEnv;
use jni::{JavaVM, NativeMethod};
use log::{error, info, Level, debug};
use ndk_sys;
use once_cell::sync::OnceCell;
use std::ffi::c_void;

use std::sync::atomic::{AtomicBool, Ordering};
use std::thread;

use android_logger::Config;

use std::fs::File;
use std::process::{Command, Stdio};

mod input;
mod event_stream;
mod touch_slots;
mod key_events;
mod renderer_bindings;

/// ## Examples
/// ```
/// let method:NativeMethod = jni_method!(native_method, "(Ljava/lang/String;)V");
/// ```
macro_rules! jni_method {
    ( $name: tt, $method:tt, $signature:expr ) => {{
        jni::NativeMethod {
            name: jni::strings::JNIString::from(stringify!($name)),
            sig: jni::strings::JNIString::from($signature),
            fn_ptr: $method as *mut c_void,
        }
    }};
}

static RENDERER_STARTED: AtomicBool = AtomicBool::new(false);
static GUEST_START_FAILED: AtomicBool = AtomicBool::new(false);
static JVM_HANDLE: OnceCell<JavaVM> = OnceCell::new();
static RENDERER_CLASS: OnceCell<GlobalRef> = OnceCell::new();

fn notify_guest_exited(exit_code: i32) {
    GUEST_START_FAILED.store(true, Ordering::Release);
    if let (Some(jvm), Some(clazz)) = (JVM_HANDLE.get(), RENDERER_CLASS.get()) {
        if let Ok(env) = jvm.attach_current_thread() {
            let class_obj: jni::objects::JClass = clazz.as_obj().into();
            let _ = env.call_static_method(
                class_obj,
                "onGuestExited",
                "(I)V",
                &[JValue::Int(exit_code)],
            );
            let _ = env.exception_clear();
        }
    }
}

pub fn guest_failed(_env: JNIEnv, _clz: jclass) -> jint {
    if GUEST_START_FAILED.load(Ordering::Acquire) { 1 } else { 0 }
}

#[no_mangle]
pub fn renderer_init(
    env: JNIEnv,
    _clz: jclass,
    surface: jobject,
    loader: jstring,
    xdpi: jfloat,
    ydpi: jfloat,
    fps: jint,
) {
    debug!("renderer_init");
    if GUEST_START_FAILED.load(Ordering::Acquire) {
        let _ = env.throw_new("java/lang/IllegalStateException",
            "Guest startup previously failed; restart the host process before retrying");
        return;
    }
    let window = unsafe { ndk_sys::ANativeWindow_fromSurface(env.get_native_interface(), surface) };

    let window = match std::ptr::NonNull::new(window) {
        Some(x) => x,
        None => {
            error!("ANativeWindow_fromSurface was null!");
            let _ = env.throw_new("java/lang/IllegalStateException", "No valid render Surface");
            return;
        }
    };

    let window = unsafe { ndk::native_window::NativeWindow::from_ptr(window) };

    let width = window.width();
    let height = window.height();

    info!(
        "renderer_init width: {}, height: {}, fps: {}",
        width, height, fps
    );

    if RENDERER_STARTED.compare_exchange(false, true, 
        Ordering::Acquire, Ordering::Relaxed).is_err() {
        let win = window.ptr().as_ptr() as *mut c_void;
        unsafe {
            renderer_bindings::setNativeWindow(win);
            renderer_bindings::resetSubWindow(win, 0, 0, width, height, width, height, 1.0, 0.0);
        }
    } else {
        input::start_input_system(width, height);

        thread::spawn(move || {
            let win = window.ptr().as_ptr() as *mut c_void;
            info!("win: {:#?}", win);
            unsafe {
                renderer_bindings::startOpenGLRenderer(
                    win,
                    width,
                    height,
                    xdpi as i32,
                    ydpi as i32,
                    fps as i32,
                );
            }
        });

        let launch = (|| -> Result<std::process::Child, String> {
            let loader_path: String = env.get_string(loader.into())
                .map_err(|e| format!("Invalid loader path: {}", e))?.into();
            let outputs = File::create("/data/data/io.twoyi/log.txt")
                .map_err(|e| format!("Cannot open guest log: {}", e))?;
            let errors = outputs.try_clone()
                .map_err(|e| format!("Cannot duplicate guest log: {}", e))?;
            Command::new("./init")
                .current_dir("/data/data/io.twoyi/rootfs")
                .env("TYLOADER", loader_path)
                .stdout(Stdio::from(outputs))
                .stderr(Stdio::from(errors))
                .spawn().map_err(|e| format!("Cannot start guest init: {}", e))
        })();
        match launch {
            Ok(mut child) => {
                thread::spawn(move || {
                    let code = match child.wait() {
                        Ok(status) => {
                            error!("guest init exited: {}", status);
                            status.code().unwrap_or(-1)
                        }
                        Err(e) => {
                            error!("cannot wait for guest init: {}", e);
                            -1
                        }
                    };
                    // Native renderer/input cleanup is not yet restartable.
                    notify_guest_exited(code);
                });
            }
            Err(message) => {
                notify_guest_exited(-1);
                error!("{}", message);
                let _ = env.throw_new("java/lang/IllegalStateException", message);
            }
        }
    }
}

#[no_mangle]
pub fn renderer_reset_window(
    env: JNIEnv,
    _clz: jclass,
    surface: jobject,
    _top: jint,
    _left: jint,
    _width: jint,
    _height: jint,
) {
    debug!("reset_window");
    unsafe {
        let window = ndk_sys::ANativeWindow_fromSurface(env.get_native_interface(), surface);
        renderer_bindings::resetSubWindow(window as *mut c_void, 0, 0, _width, _height, _width, _height, 1.0, 0.0);
    }
}

#[no_mangle]
pub fn renderer_remove_window(env: JNIEnv, _clz: jclass, surface: jobject) {
    debug!("renderer_remove_window");

    unsafe {
        let window = ndk_sys::ANativeWindow_fromSurface(env.get_native_interface(), surface);
        renderer_bindings::removeSubWindow(window as *mut c_void);
    }
}

#[no_mangle]
pub fn handle_touch(env: JNIEnv, _clz: jclass, event: jobject) {
    // TODO: cache the field id.
    let ptr = env.get_field(event, "mNativePtr", "J").unwrap();

    if let JValue::Long(p) = ptr {
        let ev = unsafe {
            let nonptr =
            std::ptr::NonNull::new(std::mem::transmute::<i64, *mut ndk_sys::AInputEvent>(p))
                .unwrap();
            ndk::event::MotionEvent::from_ptr(nonptr)
        };
        input::handle_touch(ev)
    }
}

pub fn send_key_code(_env: JNIEnv, _clz: jclass, keycode: jint) {
    debug!("send key code!");
    input::send_key_code(keycode);
}

unsafe fn register_natives(jvm: &JavaVM, class_name: &str, methods: &[NativeMethod]) -> jint {
    let env: JNIEnv = jvm.get_env().unwrap();
    let jni_version = env.get_version().unwrap();
    let version: jint = jni_version.into();

    debug!("JNI Version : {:#?} ", jni_version);

    let clazz = match env.find_class(class_name) {
        Ok(clazz) => clazz,
        Err(e) => {
            error!("java class not found : {:?}", e);
            return JNI_ERR;
        }
    };
    debug!("clazz: {:#?}", clazz);

    if let Ok(global_ref) = env.new_global_ref(clazz) {
        let _ = RENDERER_CLASS.set(global_ref);
    }

    let result = env.register_native_methods(clazz, &methods);

    if result.is_ok() {
        debug!("register_natives : succeed");
        version
    } else {
        error!("register_natives : failed ");
        JNI_ERR
    }
}

#[no_mangle]
#[allow(non_snake_case)]
unsafe fn JNI_OnLoad(jvm: JavaVM, _reserved: *mut c_void) -> jint {
    android_logger::init_once(
        Config::default()
            .with_min_level(Level::Info)
            .with_tag("CLIENT_EGL"),
    );

    debug!("JNI_OnLoad");

    let class_name: &str = "io/twoyi/Renderer";
    let jni_methods = [
        jni_method!(init, renderer_init, "(Landroid/view/Surface;Ljava/lang/String;FFI)V"),
        jni_method!(
            resetWindow,
            renderer_reset_window,
            "(Landroid/view/Surface;IIII)V"
        ),
        jni_method!(
            removeWindow,
            renderer_remove_window,
            "(Landroid/view/Surface;)V"
        ),
        jni_method!(handleTouch, handle_touch, "(Landroid/view/MotionEvent;)V"),
        jni_method!(sendKeycode, send_key_code, "(I)V"),
        jni_method!(guestFailed, guest_failed, "()I"),
    ];

    let ret = register_natives(&jvm, class_name, jni_methods.as_ref());
    let _ = JVM_HANDLE.set(jvm);
    ret
}

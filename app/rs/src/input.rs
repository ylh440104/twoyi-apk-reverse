// This Source Code Form is subject to the terms of the Mozilla Public
// License, v. 2.0. If a copy of the MPL was not distributed with this
// file, You can obtain one at https://mozilla.org/MPL/2.0/.

use libc::*;
use libc::{c_char, c_int};
use ndk::event::{MotionAction, MotionEvent};
use std::mem;
use std::thread;
use std::{io::Write};
use uinput_sys::*;

use std::sync::mpsc::{ channel, Sender};
use std::sync::Mutex;
use once_cell::sync::Lazy;

use log::info;

const FF_MAX: u16 = 0x7f;

const TOUCH_PATH: &'static str = "/data/data/io.twoyi/rootfs/dev/input/touch";
const TOUCH_DEVICE_NAME: &'static str = "vtouch";
const TOUCH_DEVICE_UNIQUE_ID: &'static str = "<vtouch 0>";

const KEY_DEVICE_NAME: &'static str = "vkey";
const KEY_DEVICE_UNIQUE_ID: &'static str = "<keyboard 0>";
const KEY_PATH: &'static str = "/data/data/io.twoyi/rootfs/dev/input/key0";

#[repr(C)]
#[derive(Clone, Copy)]
struct device_info {
    name: [c_char; 80],
    driver_version: c_int,
    id: input_id,
    physical_location: [c_char; 80],
    unique_id: [c_char; 80],
    key_bitmask: [u8; (KEY_MAX as usize + 1) / 8],
    abs_bitmask: [u8; (ABS_MAX as usize + 1) / 8],
    rel_bitmask: [u8; (REL_MAX as usize + 1) / 8],
    sw_bitmask: [u8; (SW_MAX as usize + 1) / 8],
    led_bitmask: [u8; (LED_MAX as usize + 1) / 8],
    ff_bitmask: [u8; (FF_MAX as usize + 1) / 8],
    prop_bitmask: [u8; (INPUT_PROP_MAX as usize + 1) / 8],
    abs_max: [u32; ABS_CNT as usize],
    abs_min: [u32; ABS_CNT as usize],
}

unsafe fn any_as_u8_slice<T: Sized>(p: &T) -> &[u8] {
    ::std::slice::from_raw_parts((p as *const T) as *const u8, ::std::mem::size_of::<T>())
}

fn copy_to_cstr<const COUNT: usize>(data: &str, arr: &mut [u8; COUNT]) {
    let cstr = std::ffi::CString::new(data).expect("create cstring failed");
    let bytes = cstr.as_bytes_with_nul();
    let mut len = bytes.len();
    if len >= COUNT {
        len = COUNT;
    }
    arr[..len].copy_from_slice(bytes);
}

const MAX_POINTERS: usize = 5;

static INPUT_SENDER: Lazy<Mutex<Option<Sender<input_event>>>> = Lazy::new(|| { Mutex::new(None)});
static KEY_SENDER: Lazy<Mutex<Option<Sender<input_event>>>> = Lazy::new(|| { Mutex::new(None)});

pub fn start_input_system(width: i32, height: i32) {
    thread::spawn(move || {
        touch_server(width, height);
    });
    thread::spawn(|| {
        key_server();
    });
}

pub fn input_event_write(
    tx: &std::sync::mpsc::Sender<input_event>,
    kind: i32,
    code: i32,
    val: i32,
) {
    let mut tp = libc::timespec { tv_sec:0, tv_nsec: 0 };
    let _ = unsafe { clock_gettime(CLOCK_MONOTONIC, &mut tp) };
    let tv = timeval {
        tv_sec: tp.tv_sec,
        tv_usec: tp.tv_nsec / 1000
    };

    let ev = input_event {
        kind: kind as u16,
        code: code as u16,
        value: val,
        time: tv,
    };
    let _ = tx.send(ev);
}

static TOUCH_SLOTS: Lazy<Mutex<crate::touch_slots::TouchSlots<MAX_POINTERS>>> =
    Lazy::new(|| Mutex::new(crate::touch_slots::TouchSlots::new()));

fn release_all(fd: &Sender<input_event>, slots: &mut crate::touch_slots::TouchSlots<MAX_POINTERS>) {
    for (slot, id) in slots.clear().iter().enumerate() {
        if id.is_some() {
            input_event_write(fd, EV_ABS, ABS_MT_SLOT, slot as i32);
            input_event_write(fd, EV_ABS, ABS_MT_TRACKING_ID, -1);
        }
    }
    input_event_write(fd, EV_KEY, BTN_TOUCH, 0);
    input_event_write(fd, EV_KEY, BTN_TOOL_FINGER, 0);
}

pub fn handle_touch(ev: MotionEvent) {
    let sender = INPUT_SENDER.lock().unwrap();
    let fd = match sender.as_ref() { Some(fd) => fd, None => return };
    let mut slots = TOUCH_SLOTS.lock().unwrap();
    match ev.action() {
        MotionAction::Cancel | MotionAction::Up => {
            release_all(fd, &mut slots);
        }
        MotionAction::Down | MotionAction::PointerDown => {
            if ev.pointer_index() >= ev.pointer_count() { return; }
            if ev.action() == MotionAction::Down {
                // A new gesture also recovers from an unobserved previous cancel.
                release_all(fd, &mut slots);
            }
            let pointer = ev.pointer_at_index(ev.pointer_index());
            let id = pointer.pointer_id();
            if let Some(slot) = slots.press(id) {
                input_event_write(fd, EV_KEY, BTN_TOUCH, 1);
                input_event_write(fd, EV_KEY, BTN_TOOL_FINGER, 1);
                input_event_write(fd, EV_ABS, ABS_MT_SLOT, slot as i32);
                input_event_write(fd, EV_ABS, ABS_MT_TRACKING_ID, id);
                input_event_write(fd, EV_ABS, ABS_MT_POSITION_X, pointer.x() as i32);
                input_event_write(fd, EV_ABS, ABS_MT_POSITION_Y, pointer.y() as i32);
                input_event_write(fd, EV_ABS, ABS_MT_PRESSURE,
                    (pointer.pressure().max(0.0).min(1.0) * 80.0) as i32);
            }
        }
        MotionAction::Move => {
            for index in 0..ev.pointer_count() {
                let pointer = ev.pointer_at_index(index);
                if let Some(slot) = slots.find(pointer.pointer_id()) {
                    input_event_write(fd, EV_ABS, ABS_MT_SLOT, slot as i32);
                    input_event_write(fd, EV_ABS, ABS_MT_POSITION_X, pointer.x() as i32);
                    input_event_write(fd, EV_ABS, ABS_MT_POSITION_Y, pointer.y() as i32);
                    input_event_write(fd, EV_ABS, ABS_MT_PRESSURE,
                        (pointer.pressure().max(0.0).min(1.0) * 80.0) as i32);
                }
            }
        }
        MotionAction::PointerUp => {
            if ev.pointer_index() >= ev.pointer_count() { return; }
            let id = ev.pointer_at_index(ev.pointer_index()).pointer_id();
            if let Some(slot) = slots.release(id) {
                input_event_write(fd, EV_ABS, ABS_MT_SLOT, slot as i32);
                input_event_write(fd, EV_ABS, ABS_MT_TRACKING_ID, -1);
                if slots.is_empty() {
                    input_event_write(fd, EV_KEY, BTN_TOUCH, 0);
                    input_event_write(fd, EV_KEY, BTN_TOOL_FINGER, 0);
                }
            }
        }
        _ => return,
    }
    input_event_write(fd, EV_SYN, SYN_REPORT, 0);
}

fn generate_touch_device(width: i32, height: i32) -> device_info {
    let iid = input_id {
        product: 0x1,
        version: 0,
        vendor: 0,
        bustype: 0,
    };

    let mut info = device_info {
        name: unsafe { mem::zeroed() },
        driver_version: 0x1,
        id: iid,
        physical_location: unsafe { mem::zeroed() },
        unique_id: unsafe { mem::zeroed() },
        key_bitmask: unsafe { mem::zeroed() },
        abs_bitmask: unsafe { mem::zeroed() },
        rel_bitmask: unsafe { mem::zeroed() },
        sw_bitmask: unsafe { mem::zeroed() },
        led_bitmask: unsafe { mem::zeroed() },
        ff_bitmask: unsafe { mem::zeroed() },
        prop_bitmask: unsafe { mem::zeroed() },
        abs_max: unsafe { mem::zeroed() },
        abs_min: unsafe { mem::zeroed() },
    };

    copy_to_cstr(TOUCH_DEVICE_NAME, &mut info.name);
    copy_to_cstr(TOUCH_PATH, &mut info.physical_location);
    copy_to_cstr(TOUCH_DEVICE_UNIQUE_ID, &mut info.unique_id);

    let direct = INPUT_PROP_DIRECT as usize;
    info.prop_bitmask[direct / 8] |= 1 << (direct % 8);
    for code in [BTN_TOUCH, BTN_TOOL_FINGER] {
        let bit = code as usize;
        info.key_bitmask[bit / 8] |= 1 << (bit % 8);
    }
    for code in [ABS_MT_SLOT, ABS_MT_TRACKING_ID, ABS_MT_POSITION_X,
                 ABS_MT_POSITION_Y, ABS_MT_PRESSURE] {
        let bit = code as usize;
        info.abs_bitmask[bit / 8] |= 1 << (bit % 8);
    }

    info.abs_min[ABS_MT_POSITION_X as usize] = 0;
    info.abs_max[ABS_MT_POSITION_X as usize] = width as u32;

    info.abs_min[ABS_MT_POSITION_Y as usize] = 0;
    info.abs_max[ABS_MT_POSITION_Y as usize] = height as u32;

    info.abs_max[ABS_MT_TRACKING_ID as usize] = 31;

    info.abs_min[ABS_MT_SLOT as usize] = 0;
    info.abs_max[ABS_MT_SLOT as usize] = (MAX_POINTERS - 1) as u32;
    info.abs_min[ABS_MT_PRESSURE as usize] = 0;
    info.abs_max[ABS_MT_PRESSURE as usize] = 80;

    info
}

fn touch_server(width: i32, height: i32) {
    let device = generate_touch_device(width, height);
    let _ = std::fs::remove_file(TOUCH_PATH);
    let listener = unix_socket::UnixListener::bind(TOUCH_PATH).unwrap();
    for stream in listener.incoming() {
        match stream {
            Ok(mut stream) => {
                info!("touch client connected!");

                if stream.write_all(unsafe { any_as_u8_slice(&device) }).is_err() {
                    continue;
                }

                let (tx, rx) = channel::<input_event>();
                {
                    let mut sender = INPUT_SENDER.lock().unwrap();
                    TOUCH_SLOTS.lock().unwrap().clear();
                    *sender = Some(tx);
                }

                thread::spawn(move || {
                    crate::event_stream::forward_events(rx, |ev| {
                        stream.write_all(unsafe { any_as_u8_slice(&ev) })
                    });
                });
            }
            Err(_) => {
                info!("touch server error happened!");
                break;
            }
        }
    }

    info!("drop listener!");
}

fn generate_key_device() -> device_info {
    let mut info: device_info = unsafe { std::mem::MaybeUninit::zeroed().assume_init() };

    info.driver_version = 0x1;
    info.id.product = 0x1;

    copy_to_cstr(KEY_DEVICE_NAME, &mut info.name);
    copy_to_cstr(KEY_PATH, &mut info.physical_location);
    copy_to_cstr(KEY_DEVICE_UNIQUE_ID, &mut info.unique_id);

    for &(_, code) in crate::key_events::KEY_MAP {
        let bit = code as usize;
        info.key_bitmask[bit / 8] |= 1 << (bit % 8);
    }

    info
}

pub fn send_key_code(keycode: i32) {
    if let Some(events) = crate::key_events::click(keycode) {
        if let Some(ref tx) = *KEY_SENDER.lock().unwrap() {
            for (kind, code, value) in events {
                input_event_write(tx, kind, code, value);
            }
        }
    }
}

fn key_server() {
    let device = generate_key_device();
    let _ = std::fs::remove_file(KEY_PATH);
    let listener = unix_socket::UnixListener::bind(KEY_PATH).unwrap();
    for stream in listener.incoming() {
        match stream {
            Ok(mut stream) => {
                info!("key client connected!");

                if stream.write_all(unsafe { any_as_u8_slice(&device) }).is_err() {
                    continue;
                }

                let (tx, rx) = channel::<input_event>();
                *KEY_SENDER.lock().unwrap() = Some(tx);

                thread::spawn(move || {
                    crate::event_stream::forward_events(rx, |ev| {
                        stream.write_all(unsafe { any_as_u8_slice(&ev) })
                    });
                });
            }
            Err(_) => {
                info!("key server error happened!");
                break;
            }
        }
    }
}

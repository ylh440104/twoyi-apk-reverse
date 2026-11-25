package io.twoyi.host;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AppOpsManager;
import android.app.UiAutomation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.system.Os;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import p000a.AbstractC0000a;
import p009a8.C0167l2;
import p009a8.CallableC0116b1;
import p068f9.AbstractC1283a;
import p068f9.AbstractC1294l;
import p068f9.C1292j;
import p069fa.C1312k;
import p101i8.C1674j;
import p101i8.C1679o;
import p114j8.AbstractC1933m;
import p114j8.AbstractC1944x;
import p114j8.AbstractC1945y;
import p114j8.C1940t;
import p170o7.AbstractC2780a;
import p255w8.InterfaceC4252c;
import p265x8.AbstractC4450j;
import p285z6.C4643c0;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.u */
/* loaded from: classes.dex */
public class BinderC1809u extends AbstractBinderC1801n0 {

    /* renamed from: W */
    private static final C1807s f7161W = new Object();

    /* renamed from: X */
    public static final int f7162X = 8;

    /* renamed from: Y */
    private static final String f7163Y = "u";

    /* renamed from: Z */
    @Deprecated
    public static final String f7164Z = "twoyi-twin";

    /* renamed from: a0 */
    @Deprecated
    public static final long f7165a0 = 60000;

    /* renamed from: b0 */
    @Deprecated
    public static final long f7166b0 = 10;

    /* renamed from: c0 */
    @Deprecated
    public static final long f7167c0 = 1000;

    /* renamed from: d0 */
    @Deprecated
    public static final long f7168d0 = 100;

    /* renamed from: e0 */
    @Deprecated
    public static final long f7169e0 = 10;

    /* renamed from: f0 */
    @Deprecated
    public static final long f7170f0 = 80;

    /* renamed from: g0 */
    @Deprecated
    public static final int f7171g0 = 1000;

    /* renamed from: h0 */
    @Deprecated
    public static final int f7172h0 = 40;

    /* renamed from: i0 */
    @Deprecated
    public static final long f7173i0 = 1000;

    /* renamed from: j0 */
    @Deprecated
    public static final int f7174j0 = 270532608;

    /* renamed from: C */
    private final Context f7175C;

    /* renamed from: D */
    private final Object f7176D;

    /* renamed from: E */
    private VirtualDisplay f7177E;

    /* renamed from: F */
    private int f7178F;

    /* renamed from: G */
    private int f7179G;

    /* renamed from: H */
    private boolean f7180H;

    /* renamed from: I */
    private boolean f7181I;

    /* renamed from: J */
    private String f7182J;

    /* renamed from: K */
    private C1813y f7183K;

    /* renamed from: L */
    private volatile long f7184L;

    /* renamed from: M */
    private volatile long f7185M;

    /* renamed from: N */
    private final HashMap<String, Integer> f7186N;

    /* renamed from: O */
    private final HashSet<Integer> f7187O;

    /* renamed from: P */
    private final String f7188P;

    /* renamed from: Q */
    private final HandlerThread f7189Q;

    /* renamed from: R */
    private final Handler f7190R;

    /* renamed from: S */
    private volatile UiAutomation f7191S;

    /* renamed from: T */
    private long f7192T;

    /* renamed from: U */
    private final Runnable f7193U;

    /* renamed from: V */
    private final RunnableC1808t f7194V;

    public BinderC1809u(Context context) {
        attachInterface(this, "io.twoyi.host.IHostHelper");
        this.f7175C = context;
        this.f7176D = new Object();
        this.f7186N = new HashMap<>();
        this.f7187O = new HashSet<>();
        this.f7193U = new RunnableC1805q(this, 0);
        if (Process.myUid() == 0) {
            if (!AbstractC4450j.m7251a(Looper.myLooper(), Looper.getMainLooper())) {
                Log.w(f7163Y, "switching uid off the main thread");
            }
            Os.setgid(f7171g0);
            Os.setuid(f7171g0);
        }
        C1292j c1292j = AbstractC1775a0.f7036a;
        int myUid = Process.myUid();
        String packageName = context.getPackageName();
        AbstractC4450j.m7254d(packageName, "getPackageName(...)");
        if (myUid != 1000) {
            if (myUid == 2000) {
                packageName = "com.android.shell";
            }
        } else {
            packageName = "android";
        }
        this.f7188P = packageName;
        HandlerThread handlerThread = new HandlerThread("host-automation");
        handlerThread.start();
        this.f7189Q = handlerThread;
        this.f7190R = new Handler(handlerThread.getLooper());
        Log.i(f7163Y, "helper up: uid=" + Process.myUid() + " as " + packageName + " sdk=" + Build.VERSION.SDK_INT);
        this.f7194V = new RunnableC1808t(this);
    }

    /* renamed from: c0 */
    public static UiAutomation m3100c0(BinderC1809u binderC1809u) {
        UiAutomation uiAutomation = binderC1809u.f7191S;
        if (uiAutomation == null) {
            try {
                C1679o c1679o = AbstractC1795k0.f7106a;
                Looper looper = binderC1809u.f7189Q.getLooper();
                AbstractC4450j.m7254d(looper, "getLooper(...)");
                UiAutomation m3069b = AbstractC1795k0.m3069b(looper);
                AccessibilityServiceInfo serviceInfo = m3069b.getServiceInfo();
                serviceInfo.flags |= 64;
                m3069b.setServiceInfo(serviceInfo);
                binderC1809u.f7192T = SystemClock.uptimeMillis();
                binderC1809u.f7191S = m3069b;
                return m3069b;
            } catch (Exception e10) {
                binderC1809u.m3114n0("UiAutomation", e10);
                return null;
            }
        }
        return uiAutomation;
    }

    /* renamed from: f0 */
    public static final boolean m3103f0(BinderC1809u binderC1809u) {
        binderC1809u.getClass();
        if (SystemClock.uptimeMillis() - binderC1809u.f7184L <= 5000) {
            return true;
        }
        return false;
    }

    /* renamed from: g0 */
    public static String m3104g0(String... strArr) {
        C1312k c1312k = new C1312k(2);
        ArrayList arrayList = c1312k.f5117a;
        arrayList.add("am");
        c1312k.m2292b(strArr);
        Process start = new ProcessBuilder((String[]) arrayList.toArray(new String[arrayList.size()])).redirectErrorStream(true).start();
        InputStream inputStream = start.getInputStream();
        AbstractC4450j.m7254d(inputStream, "getInputStream(...)");
        String m4858l = AbstractC2780a.m4858l(new BufferedReader(new InputStreamReader(inputStream, AbstractC1283a.f5006a), 8192));
        if (!start.waitFor(10L, TimeUnit.SECONDS)) {
            start.destroy();
        }
        return m4858l;
    }

    /* renamed from: i0 */
    public static ArrayList m3105i0(List list) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            AccessibilityWindowInfo accessibilityWindowInfo = (AccessibilityWindowInfo) obj;
            if (accessibilityWindowInfo.getType() != 2 && accessibilityWindowInfo.getType() != 3) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* renamed from: k0 */
    public static String m3106k0(UiAutomation uiAutomation) {
        SparseArray windowsOnAllDisplays;
        try {
            windowsOnAllDisplays = uiAutomation.getWindowsOnAllDisplays();
            AbstractC4450j.m7254d(windowsOnAllDisplays, "getWindowsOnAllDisplays(...)");
            String m3279q0 = AbstractC1933m.m3279q0(AbstractC0000a.m26g0(0, windowsOnAllDisplays.size()), null, null, null, new C0167l2(18, windowsOnAllDisplays), 31);
            return "flags=0x" + Integer.toHexString(uiAutomation.getServiceInfo().flags) + " windows={" + m3279q0 + "}";
        } catch (Exception e10) {
            return "error ".concat(e10.getClass().getSimpleName());
        }
    }

    /* renamed from: o0 */
    public static AccessibilityNodeInfo m3107o0(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        if (accessibilityNodeInfo != null && i <= 40) {
            if (accessibilityNodeInfo.isEditable() && accessibilityNodeInfo.isFocused()) {
                return accessibilityNodeInfo;
            }
            int childCount = accessibilityNodeInfo.getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                AccessibilityNodeInfo m3107o0 = m3107o0(accessibilityNodeInfo.getChild(i10), i + 1);
                if (m3107o0 != null) {
                    return m3107o0;
                }
            }
            return null;
        }
        return null;
    }

    /* renamed from: p0 */
    public static AccessibilityNodeInfo m3108p0(ArrayList arrayList) {
        AccessibilityNodeInfo accessibilityNodeInfo;
        int size = arrayList.size();
        int i = 0;
        do {
            accessibilityNodeInfo = null;
            if (i >= size) {
                break;
            }
            Object obj = arrayList.get(i);
            i++;
            try {
                AccessibilityNodeInfo root = ((AccessibilityWindowInfo) obj).getRoot();
                if (root != null) {
                    accessibilityNodeInfo = root.findFocus(1);
                }
            } catch (Exception unused) {
            }
        } while (accessibilityNodeInfo == null);
        return accessibilityNodeInfo;
    }

    /* renamed from: s0 */
    public static /* synthetic */ void m3109s0(BinderC1809u binderC1809u, List list, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                list = null;
            }
            binderC1809u.m3116r0(list);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: pullBackSpills");
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: A */
    public int mo3081A(int i, int i10, int i11, Surface surface) {
        Throwable th;
        int i12;
        VirtualDisplay m3111j0;
        synchronized (this.f7176D) {
            try {
                VirtualDisplay virtualDisplay = this.f7177E;
                if (virtualDisplay != null) {
                    try {
                        virtualDisplay.release();
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
                this.f7177E = null;
                this.f7183K = null;
                try {
                    try {
                        try {
                            m3111j0 = m3111j0(i, i10, i11, surface, true);
                        } catch (Throwable th3) {
                            th = th3;
                            th = th;
                            throw th;
                        }
                    } catch (SecurityException e10) {
                        m3114n0("trusted display refused, creating an untrusted one", e10);
                        m3111j0 = m3111j0(i, i10, i11, surface, false);
                    }
                    i12 = m3111j0.getDisplay().getDisplayId();
                    this.f7177E = m3111j0;
                    this.f7183K = new C1813y(i12);
                    this.f7178F = i;
                    this.f7179G = i10;
                    try {
                        AbstractC1795k0.m3076i(i12);
                    } catch (Exception e11) {
                        m3114n0("ime policy", e11);
                    }
                    try {
                        AbstractC1795k0.m3075h(i12);
                    } catch (Exception e12) {
                        m3114n0("windowing mode", e12);
                    }
                    if (!this.f7181I) {
                        try {
                            m3104g0("start", "--display", String.valueOf(i12), "-n", this.f7175C.getPackageName() + "/io.twoyi.host.HostBackdropActivity");
                        } catch (Exception e13) {
                            m3114n0("backdrop", e13);
                        }
                    }
                    Log.i(f7163Y, "display " + i12 + " " + i + "x" + i10 + "@" + i11 + " trusted=" + this.f7180H);
                } catch (Exception e14) {
                    m3114n0("createDisplay", e14);
                    i12 = -1;
                }
                return i12;
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: B */
    public void mo3082B() {
        synchronized (this.f7176D) {
            VirtualDisplay virtualDisplay = this.f7177E;
            if (virtualDisplay != null) {
                virtualDisplay.release();
            }
            this.f7177E = null;
            this.f7183K = null;
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: G */
    public byte[] mo3083G() {
        try {
            m3109s0(this, null, 1, null);
            C4643c0 m3118u0 = m3118u0();
            if (m3118u0 == null) {
                return null;
            }
            return AbstractC1797l0.m3080b(m3118u0);
        } catch (Exception e10) {
            m3114n0("dumpState", e10);
            return null;
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: H */
    public int mo3084H(String str) {
        ComponentName component;
        AbstractC4450j.m7255e(str, "packageName");
        m3115q0();
        this.f7185M = SystemClock.uptimeMillis();
        Integer m3113m0 = m3113m0();
        if (m3113m0 == null) {
            return 3;
        }
        int intValue = m3113m0.intValue();
        Intent launchIntentForPackage = this.f7175C.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null && (component = launchIntentForPackage.getComponent()) != null) {
            try {
                C1292j c1292j = AbstractC1775a0.f7036a;
                C1679o c1679o = AbstractC1795k0.f7106a;
                if (AbstractC1775a0.m3022a(str, AbstractC1795k0.m3078k(this.f7175C))) {
                    return 2;
                }
                String valueOf = String.valueOf(intValue);
                String str2 = "0x" + Integer.toHexString(f7174j0);
                String flattenToShortString = component.flattenToShortString();
                AbstractC4450j.m7254d(flattenToShortString, "flattenToShortString(...)");
                String m3104g0 = m3104g0("start", "--display", valueOf, "-a", "android.intent.action.MAIN", "-c", "android.intent.category.LAUNCHER", "-f", str2, "-n", flattenToShortString);
                if (!AbstractC1294l.m2258y0(m3104g0, "Error", false)) {
                    return 0;
                }
                String str3 = "launch " + str + ": " + AbstractC1294l.m2254X0(m3104g0).toString();
                this.f7182J = str3;
                String str4 = f7163Y;
                AbstractC4450j.m7252b(str3);
                Log.w(str4, str3);
                return 3;
            } catch (Exception e10) {
                m3114n0("launch ".concat(str), e10);
                return 3;
            }
        }
        return 1;
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: L */
    public void mo3085L() {
        Set m3269G0;
        Integer m3113m0 = m3113m0();
        if (m3113m0 != null) {
            int intValue = m3113m0.intValue();
            synchronized (this.f7187O) {
                m3269G0 = AbstractC1933m.m3269G0(this.f7187O);
                this.f7187O.clear();
            }
            if (!m3269G0.isEmpty()) {
                try {
                    C1679o c1679o = AbstractC1795k0.f7106a;
                    ArrayList m3078k = AbstractC1795k0.m3078k(this.f7175C);
                    ArrayList arrayList = new ArrayList();
                    int size = m3078k.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = m3078k.get(i);
                        i++;
                        C1814z c1814z = (C1814z) obj;
                        if (m3269G0.contains(Integer.valueOf(c1814z.f7207d)) && c1814z.f7204a == 0) {
                            arrayList.add(obj);
                        }
                    }
                    int size2 = arrayList.size();
                    int i10 = 0;
                    while (i10 < size2) {
                        Object obj2 = arrayList.get(i10);
                        i10++;
                        C1679o c1679o2 = AbstractC1795k0.f7106a;
                        AbstractC1795k0.m3072e(((C1814z) obj2).f7207d, intValue);
                    }
                    if (!arrayList.isEmpty()) {
                        mo3090a0(0, f7171g0);
                    }
                    Log.i(f7163Y, "took back " + arrayList.size() + " of " + m3269G0.size() + " task(s) from the user's screen");
                } catch (Exception e10) {
                    m3114n0("move back", e10);
                }
            }
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: O */
    public void mo3086O(String str, int i) {
        int unsafeCheckOpRawNoThrow;
        AbstractC4450j.m7255e(str, "packageName");
        synchronized (this.f7186N) {
            this.f7186N.remove(str);
        }
        try {
            int packageUid = this.f7175C.getPackageManager().getPackageUid(str, 0);
            C1679o c1679o = AbstractC1795k0.f7106a;
            Context context = this.f7175C;
            AbstractC4450j.m7255e(context, "context");
            unsafeCheckOpRawNoThrow = ((AppOpsManager) context.getSystemService(AppOpsManager.class)).unsafeCheckOpRawNoThrow("android:play_audio", packageUid, str);
            if (unsafeCheckOpRawNoThrow == 1) {
                Context context2 = this.f7175C;
                C1292j c1292j = AbstractC1775a0.f7036a;
                if (i == 3) {
                    i = 0;
                }
                AbstractC1795k0.m3077j(context2, packageUid, str, i);
                Log.i(f7163Y, "unmuted ".concat(str));
            }
        } catch (PackageManager.NameNotFoundException unused) {
        } catch (Exception e10) {
            m3114n0("unmute ".concat(str), e10);
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: R */
    public boolean mo3087R(KeyEvent keyEvent) {
        AbstractC4450j.m7255e(keyEvent, "event");
        Integer m3113m0 = m3113m0();
        if (m3113m0 == null) {
            return false;
        }
        m3115q0();
        try {
            int intValue = m3113m0.intValue();
            C1679o c1679o = AbstractC1795k0.f7106a;
            return AbstractC1795k0.m3071d(keyEvent, intValue, 1);
        } catch (Exception e10) {
            m3114n0("inject ".concat("key"), e10);
            return false;
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: T */
    public void mo3088T(Surface surface) {
        synchronized (this.f7176D) {
            VirtualDisplay virtualDisplay = this.f7177E;
            if (virtualDisplay != null) {
                virtualDisplay.setSurface(surface);
            }
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: V */
    public boolean mo3089V(MotionEvent motionEvent) {
        AbstractC4450j.m7255e(motionEvent, "event");
        Integer m3113m0 = m3113m0();
        if (m3113m0 == null) {
            return false;
        }
        m3115q0();
        try {
            int intValue = m3113m0.intValue();
            C1679o c1679o = AbstractC1795k0.f7106a;
            return AbstractC1795k0.m3071d(motionEvent, intValue, 0);
        } catch (Exception e10) {
            m3114n0("inject ".concat("motion"), e10);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0036  */
    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: a0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int mo3090a0(int r18, int r19) {
        /*
            r17 = this;
            r0 = r19
            long r0 = (long) r0
            r2 = 4
            r3 = r2 & r2
            if (r3 == 0) goto Lb
            r3 = 800(0x320, double:3.953E-321)
            goto Ld
        Lb:
            r3 = 0
        Ld:
            long r5 = android.os.SystemClock.uptimeMillis()
            r8 = 0
            r9 = r8
            r10 = 0
        L14:
            r11 = 1
            r12 = r17
            m3109s0(r12, r8, r11, r8)
            z6.c0 r13 = r12.m3118u0()     // Catch: java.lang.Exception -> L29
            if (r13 == 0) goto L29
            z6.i0 r13 = r13.f17058a     // Catch: java.lang.Exception -> L29
            if (r13 == 0) goto L29
            int r13 = r13.hashCode()     // Catch: java.lang.Exception -> L29
            goto L2a
        L29:
            r13 = 0
        L2a:
            long r14 = android.os.SystemClock.uptimeMillis()
            long r14 = r14 - r5
            r7 = r18
            if (r13 == r7) goto L36
            r16 = r11
            goto L38
        L36:
            r16 = 0
        L38:
            if (r16 == 0) goto L45
            if (r9 != 0) goto L3d
            goto L45
        L3d:
            int r8 = r9.intValue()
            if (r13 != r8) goto L45
            r8 = r11
            goto L46
        L45:
            r8 = 0
        L46:
            if (r16 == 0) goto L53
            if (r9 != 0) goto L4b
            goto L51
        L4b:
            int r9 = r9.intValue()
            if (r13 == r9) goto L53
        L51:
            int r10 = r10 + r11
            goto L54
        L53:
            r10 = 0
        L54:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r13)
            if (r8 != 0) goto L6e
            if (r10 >= r2) goto L6e
            if (r16 != 0) goto L62
            int r8 = (r14 > r3 ? 1 : (r14 == r3 ? 0 : -1))
            if (r8 >= 0) goto L6e
        L62:
            int r8 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r8 < 0) goto L67
            goto L6e
        L67:
            r13 = 80
            android.os.SystemClock.sleep(r13)
            r8 = 0
            goto L14
        L6e:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.host.BinderC1809u.mo3090a0(int, int):int");
    }

    public void destroy() {
        m3117t0();
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: e */
    public boolean mo3091e() {
        boolean z10;
        Integer m3113m0 = m3113m0();
        if (m3113m0 != null) {
            int intValue = m3113m0.intValue();
            try {
                C1292j c1292j = AbstractC1775a0.f7036a;
                C1679o c1679o = AbstractC1795k0.f7106a;
                ArrayList m3078k = AbstractC1795k0.m3078k(this.f7175C);
                if (!m3078k.isEmpty()) {
                    int size = m3078k.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = m3078k.get(i);
                        i++;
                        C1814z c1814z = (C1814z) obj;
                        if (c1814z.f7204a == intValue && c1814z.f7205b && !c1814z.f7209f) {
                            z10 = true;
                            break;
                        }
                    }
                }
                z10 = false;
                if (z10) {
                    this.f7185M = 0L;
                }
                if (!z10) {
                    if (SystemClock.uptimeMillis() - this.f7185M < 5000) {
                    }
                }
                return true;
            } catch (Exception e10) {
                m3114n0("isShowing", e10);
                return true;
            }
        }
        return false;
    }

    /* renamed from: h0 */
    public final UiAutomation m3110h0() {
        this.f7190R.removeCallbacks(this.f7193U);
        this.f7190R.postDelayed(this.f7193U, f7165a0);
        UiAutomation uiAutomation = this.f7191S;
        if (uiAutomation != null) {
            return uiAutomation;
        }
        FutureTask futureTask = new FutureTask(new CallableC0116b1(2, this));
        this.f7190R.post(futureTask);
        try {
            return (UiAutomation) futureTask.get(10L, TimeUnit.SECONDS);
        } catch (Exception e10) {
            m3114n0("UiAutomation", e10);
            return null;
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    public boolean isEmpty() {
        Integer m3113m0 = m3113m0();
        if (m3113m0 != null) {
            int intValue = m3113m0.intValue();
            try {
                C1679o c1679o = AbstractC1795k0.f7106a;
                ArrayList m3078k = AbstractC1795k0.m3078k(this.f7175C);
                m3116r0(m3078k);
                C1292j c1292j = AbstractC1775a0.f7036a;
                if (!m3078k.isEmpty()) {
                    int size = m3078k.size();
                    int i = 0;
                    while (i < size) {
                        Object obj = m3078k.get(i);
                        i++;
                        C1814z c1814z = (C1814z) obj;
                        if (c1814z.f7204a == intValue && !c1814z.f7209f) {
                            return false;
                        }
                    }
                    return true;
                }
                return true;
            } catch (Exception e10) {
                m3114n0("isEmpty", e10);
                return false;
            }
        }
        return true;
    }

    /* renamed from: j0 */
    public final VirtualDisplay m3111j0(int i, int i10, int i11, Surface surface, boolean z10) {
        boolean z11;
        int i12;
        int i13;
        int i14;
        boolean z12 = false;
        try {
            C1292j c1292j = AbstractC1775a0.f7036a;
            C1679o c1679o = AbstractC1795k0.f7106a;
            z11 = AbstractC4450j.m7251a(AbstractC1795k0.m3074g(this.f7175C), "com.android.launcher3.secondarydisplay.SecondaryDisplayLauncher");
        } catch (Exception e10) {
            m3114n0("secondary home", e10);
            z11 = false;
        }
        C1679o c1679o2 = AbstractC1795k0.f7106a;
        Context context = this.f7175C;
        String str = this.f7188P;
        AbstractC4450j.m7255e(context, "context");
        AbstractC4450j.m7255e(str, "ownerPackage");
        Object newInstance = DisplayManager.class.getDeclaredConstructor(Context.class).newInstance(new C1793j0(context, str));
        AbstractC4450j.m7254d(newInstance, "newInstance(...)");
        DisplayManager displayManager = (DisplayManager) newInstance;
        C1292j c1292j2 = AbstractC1775a0.f7036a;
        int i15 = Build.VERSION.SDK_INT;
        if (!z10) {
            i14 = 8521;
        } else {
            if (z11) {
                i12 = 16201;
            } else {
                i12 = 15689;
            }
            if (i15 >= 34) {
                i14 = 81920 | i12;
            } else {
                i13 = i12;
                VirtualDisplay createVirtualDisplay = displayManager.createVirtualDisplay(f7164Z, i, i10, i11, surface, i13);
                this.f7180H = z10;
                if (z10 && z11) {
                    z12 = true;
                }
                this.f7181I = z12;
                AbstractC4450j.m7252b(createVirtualDisplay);
                return createVirtualDisplay;
            }
        }
        i13 = i14;
        VirtualDisplay createVirtualDisplay2 = displayManager.createVirtualDisplay(f7164Z, i, i10, i11, surface, i13);
        this.f7180H = z10;
        if (z10) {
            z12 = true;
        }
        this.f7181I = z12;
        AbstractC4450j.m7252b(createVirtualDisplay2);
        return createVirtualDisplay2;
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: k */
    public String mo3092k() {
        Display display;
        Integer num;
        int i;
        Integer num2;
        String str;
        synchronized (this.f7176D) {
            try {
                VirtualDisplay virtualDisplay = this.f7177E;
                String str2 = null;
                if (virtualDisplay != null) {
                    display = virtualDisplay.getDisplay();
                } else {
                    display = null;
                }
                int myUid = Process.myUid();
                int i10 = Build.VERSION.SDK_INT;
                String str3 = this.f7188P;
                if (display != null) {
                    num = Integer.valueOf(display.getDisplayId());
                } else {
                    num = null;
                }
                int i11 = this.f7178F;
                int i12 = this.f7179G;
                boolean z10 = this.f7180H;
                if (display != null) {
                    i = display.getFlags();
                } else {
                    i = 0;
                }
                String hexString = Integer.toHexString(i);
                if (display != null) {
                    num2 = Integer.valueOf(display.getState());
                } else {
                    num2 = null;
                }
                UiAutomation uiAutomation = this.f7191S;
                if (uiAutomation != null) {
                    str2 = m3106k0(uiAutomation);
                }
                str = "uid=" + myUid + " sdk=" + i10 + " as=" + str3 + " display=" + num + " " + i11 + "x" + i12 + " trusted=" + z10 + " flags=0x" + hexString + " state=" + num2 + " automation=" + str2 + " lastError=" + this.f7182J;
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    /* renamed from: l0 */
    public final void m3112l0() {
        UiAutomation uiAutomation = this.f7191S;
        if (uiAutomation == null) {
            return;
        }
        this.f7191S = null;
        try {
            C1679o c1679o = AbstractC1795k0.f7106a;
            UiAutomation.class.getMethod("disconnect", null).invoke(uiAutomation, null);
        } catch (Exception e10) {
            m3114n0("UiAutomation disconnect", e10);
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: m */
    public int mo3093m(String str, boolean z10) {
        boolean z11;
        int packageUid;
        int unsafeCheckOpRawNoThrow;
        String packageName;
        boolean isApplicationUid;
        boolean z12;
        boolean m3022a;
        AbstractC4450j.m7255e(str, "packageName");
        synchronized (this.f7186N) {
            Integer num = this.f7186N.get(str);
            if (num != null) {
                return num.intValue();
            }
            int i = -1;
            try {
                z11 = false;
                packageUid = this.f7175C.getPackageManager().getPackageUid(str, 0);
                C1679o c1679o = AbstractC1795k0.f7106a;
                Context context = this.f7175C;
                AbstractC4450j.m7255e(context, "context");
                unsafeCheckOpRawNoThrow = ((AppOpsManager) context.getSystemService(AppOpsManager.class)).unsafeCheckOpRawNoThrow("android:play_audio", packageUid, str);
                C1292j c1292j = AbstractC1775a0.f7036a;
                packageName = this.f7175C.getPackageName();
                AbstractC4450j.m7254d(packageName, "getPackageName(...)");
                isApplicationUid = Process.isApplicationUid(packageUid);
                if (this.f7175C.getPackageManager().getLaunchIntentForPackage(str) != null) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                m3022a = AbstractC1775a0.m3022a(str, AbstractC1795k0.m3078k(this.f7175C));
                if (!z10 && AbstractC1795k0.m3073f(this.f7175C).contains(Integer.valueOf(packageUid))) {
                    z11 = true;
                }
            } catch (Exception e10) {
                m3114n0("mute ".concat(str), e10);
            }
            if (str.equals(packageName) || !isApplicationUid || !z12 || m3022a || z11 || (unsafeCheckOpRawNoThrow != 0 && unsafeCheckOpRawNoThrow != 3)) {
                return -1;
            }
            AbstractC1795k0.m3077j(this.f7175C, packageUid, str, 1);
            this.f7186N.put(str, Integer.valueOf(unsafeCheckOpRawNoThrow));
            Log.i(f7163Y, "muted " + str + " (was " + unsafeCheckOpRawNoThrow + ")");
            i = unsafeCheckOpRawNoThrow;
            return i;
        }
    }

    /* renamed from: m0 */
    public final Integer m3113m0() {
        Integer num;
        Display display;
        synchronized (this.f7176D) {
            VirtualDisplay virtualDisplay = this.f7177E;
            if (virtualDisplay != null && (display = virtualDisplay.getDisplay()) != null) {
                num = Integer.valueOf(display.getDisplayId());
            } else {
                num = null;
            }
        }
        return num;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable] */
    /* renamed from: n0 */
    public final void m3114n0(String str, Exception exc) {
        InvocationTargetException invocationTargetException;
        ?? targetException;
        if (exc instanceof InvocationTargetException) {
            invocationTargetException = (InvocationTargetException) exc;
        } else {
            invocationTargetException = null;
        }
        if (invocationTargetException != null && (targetException = invocationTargetException.getTargetException()) != 0) {
            exc = targetException;
        }
        this.f7182J = str + ": " + exc.getClass().getSimpleName() + ": " + exc.getMessage();
        Log.w(f7163Y, str, exc);
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: o */
    public void mo3094o() {
        boolean z10;
        Integer m3113m0 = m3113m0();
        if (m3113m0 != null) {
            if (this.f7191S != null) {
                this.f7190R.removeCallbacks(this.f7193U);
                this.f7190R.postDelayed(this.f7193U, f7165a0);
            }
            m3109s0(this, null, 1, null);
            synchronized (this.f7176D) {
                z10 = this.f7180H;
            }
            if (z10) {
                try {
                    C1674j c1674j = (C1674j) AbstractC1795k0.f7114i.getValue();
                    ((Method) c1674j.f6543j).invoke(c1674j.f6542i, m3113m0, Long.valueOf(SystemClock.uptimeMillis()), 2, 0);
                } catch (Exception e10) {
                    m3114n0("keepAwake", e10);
                }
            }
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: q */
    public boolean mo3095q(final String str, final boolean z10) {
        AbstractC4450j.m7255e(str, "text");
        return m3120w0(new InterfaceC4252c() { // from class: io.twoyi.host.r
            @Override // p255w8.InterfaceC4252c
            /* renamed from: i */
            public final Object mo169i(Object obj) {
                String str2;
                AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) obj;
                AbstractC4450j.m7255e(accessibilityNodeInfo, "node");
                BinderC1809u.this.m3115q0();
                String str3 = "";
                if (!z10 && !accessibilityNodeInfo.isShowingHintText()) {
                    CharSequence text = accessibilityNodeInfo.getText();
                    if (text != null) {
                        str2 = text.toString();
                    } else {
                        str2 = null;
                    }
                    if (str2 != null) {
                        str3 = str2;
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str3 + str);
                return Boolean.valueOf(accessibilityNodeInfo.performAction(2097152, bundle));
            }
        });
    }

    /* renamed from: q0 */
    public final void m3115q0() {
        this.f7184L = SystemClock.uptimeMillis();
        this.f7190R.removeCallbacks(this.f7194V);
        this.f7190R.postDelayed(this.f7194V, 1000L);
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [java.util.List, java.lang.Object] */
    /* renamed from: r0 */
    public final void m3116r0(List list) {
        C1813y c1813y;
        C1812x m3124a;
        Integer m3113m0 = m3113m0();
        if (m3113m0 != null) {
            int intValue = m3113m0.intValue();
            synchronized (this.f7176D) {
                c1813y = this.f7183K;
            }
            if (c1813y != null) {
                if (list == null) {
                    try {
                        C1679o c1679o = AbstractC1795k0.f7106a;
                        list = AbstractC1795k0.m3078k(this.f7175C);
                    } catch (Exception e10) {
                        m3114n0("pull back", e10);
                        return;
                    }
                }
                C1292j c1292j = AbstractC1775a0.f7036a;
                String packageName = this.f7175C.getPackageName();
                AbstractC4450j.m7254d(packageName, "getPackageName(...)");
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    C1814z c1814z = (C1814z) obj;
                    if (c1814z.f7204a == intValue && c1814z.f7206c.contains(packageName) && !c1814z.f7209f) {
                        arrayList.add(obj);
                    }
                }
                int size = arrayList.size();
                boolean z10 = false;
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    C1814z c1814z2 = (C1814z) obj2;
                    C1679o c1679o2 = AbstractC1795k0.f7106a;
                    AbstractC1795k0.m3072e(c1814z2.f7207d, 0);
                    Log.i(f7163Y, "moved Twoyi's task " + c1814z2.f7207d + " off display " + intValue);
                }
                synchronized (c1813y) {
                    if (SystemClock.uptimeMillis() - this.f7184L <= 5000) {
                        z10 = true;
                    }
                    m3124a = c1813y.m3124a(list, z10);
                }
                for (C1814z c1814z3 : m3124a.f7199a) {
                    String str = (String) AbstractC1933m.m3274l0(c1814z3.f7206c);
                    if (str != null) {
                        C1292j c1292j2 = AbstractC1775a0.f7036a;
                        String m3023b = AbstractC1775a0.m3023b(c1814z3.f7207d, AbstractC1795k0.m3070c(str));
                        LinkedHashSet linkedHashSet = m3124a.f7200b;
                        String packageName2 = this.f7175C.getPackageName();
                        AbstractC4450j.m7254d(packageName2, "getPackageName(...)");
                        if (m3023b != null && !m3023b.equals(packageName2) && linkedHashSet.contains(m3023b)) {
                            AbstractC1795k0.m3072e(c1814z3.f7207d, intValue);
                            Log.i(f7163Y, "moved task " + c1814z3.f7207d + " (" + str + ", opened by " + m3023b + ") back to display " + intValue);
                        }
                    }
                }
            }
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: s */
    public void mo3096s() {
        m3110h0();
    }

    /* renamed from: t0 */
    public final void m3117t0() {
        List<C1674j> m3296W;
        synchronized (this.f7186N) {
            m3296W = AbstractC1944x.m3296W(this.f7186N);
        }
        for (C1674j c1674j : m3296W) {
            mo3086O((String) c1674j.f6542i, ((Number) c1674j.f6543j).intValue());
        }
        synchronized (this.f7176D) {
            VirtualDisplay virtualDisplay = this.f7177E;
            if (virtualDisplay != null) {
                virtualDisplay.release();
            }
            this.f7177E = null;
            this.f7183K = null;
        }
        this.f7190R.post(new RunnableC1805q(this, 1));
        this.f7189Q.quitSafely();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f3 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v2, types: [i7.d, java.lang.Object] */
    /* renamed from: u0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final p285z6.C4643c0 m3118u0() {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.host.BinderC1809u.m3118u0():z6.c0");
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: v */
    public boolean mo3097v() {
        return m3120w0(new C0167l2(17, this));
    }

    /* renamed from: v0 */
    public final List m3119v0() {
        SparseArray windowsOnAllDisplays;
        List list;
        Integer m3113m0 = m3113m0();
        if (m3113m0 != null) {
            int intValue = m3113m0.intValue();
            UiAutomation m3110h0 = m3110h0();
            if (m3110h0 != null) {
                try {
                    AbstractC1795k0.m3068a(m3110h0);
                } catch (Exception e10) {
                    m3114n0("clear accessibility cache", e10);
                }
                long j3 = this.f7192T + 1000;
                while (true) {
                    windowsOnAllDisplays = m3110h0.getWindowsOnAllDisplays();
                    list = (List) windowsOnAllDisplays.get(intValue);
                    if (list == null) {
                        list = C1940t.f7630i;
                    }
                    if (!list.isEmpty() || SystemClock.uptimeMillis() >= j3) {
                        break;
                    }
                    SystemClock.sleep(100L);
                }
                return list;
            }
            return null;
        }
        return null;
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: w */
    public boolean mo3098w() {
        C1813y c1813y;
        LinkedHashSet linkedHashSet;
        Integer m3113m0 = m3113m0();
        if (m3113m0 != null) {
            int intValue = m3113m0.intValue();
            try {
                C1292j c1292j = AbstractC1775a0.f7036a;
                C1679o c1679o = AbstractC1795k0.f7106a;
                C1814z m3024c = AbstractC1775a0.m3024c(intValue, AbstractC1795k0.m3078k(this.f7175C));
                if (m3024c != null) {
                    synchronized (this.f7176D) {
                        c1813y = this.f7183K;
                    }
                    if (c1813y != null) {
                        synchronized (c1813y) {
                            int i = m3024c.f7207d;
                            HashSet hashSet = c1813y.f7202b;
                            if (hashSet != null) {
                                Integer valueOf = Integer.valueOf(i);
                                linkedHashSet = new LinkedHashSet(AbstractC1945y.m3299Q(hashSet.size() + 1));
                                linkedHashSet.addAll(hashSet);
                                linkedHashSet.add(valueOf);
                            } else {
                                linkedHashSet = null;
                            }
                            c1813y.f7202b = linkedHashSet;
                        }
                    }
                    AbstractC1795k0.m3072e(m3024c.f7207d, 0);
                    synchronized (this.f7187O) {
                        this.f7187O.add(Integer.valueOf(m3024c.f7207d));
                    }
                    Log.i(f7163Y, "gave task " + m3024c.f7207d + " to the user's screen");
                    return true;
                }
            } catch (Exception e10) {
                m3114n0("move to main", e10);
            }
        }
        return false;
    }

    /* renamed from: w0 */
    public final boolean m3120w0(InterfaceC4252c interfaceC4252c) {
        try {
            List m3119v0 = m3119v0();
            AccessibilityNodeInfo accessibilityNodeInfo = null;
            if (m3119v0 != null) {
                ArrayList m3105i0 = m3105i0(m3119v0);
                AccessibilityNodeInfo m3108p0 = m3108p0(m3105i0);
                if (m3108p0 == null || !m3108p0.isEditable()) {
                    int size = m3105i0.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        Object obj = m3105i0.get(i);
                        i++;
                        AccessibilityNodeInfo m3107o0 = m3107o0(((AccessibilityWindowInfo) obj).getRoot(), 0);
                        if (m3107o0 != null) {
                            accessibilityNodeInfo = m3107o0;
                            break;
                        }
                    }
                    if (accessibilityNodeInfo == null) {
                    }
                }
                accessibilityNodeInfo = m3108p0;
            }
            if (accessibilityNodeInfo == null) {
                return false;
            }
            return ((Boolean) interfaceC4252c.mo169i(accessibilityNodeInfo)).booleanValue();
        } catch (Exception e10) {
            m3114n0("focused input", e10);
            return false;
        }
    }
}

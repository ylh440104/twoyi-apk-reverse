package io.twoyi.hal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.net.LocalSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;
import android.util.Range;
import android.view.Surface;
import ba.C0588o;
import java.io.IOException;
import java.lang.Thread;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import p007a6.RunnableC0077b;
import p009a8.C0127d2;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.v */
/* loaded from: classes.dex */
public final class C1764v {

    /* renamed from: O */
    public static final /* synthetic */ int f6950O = 0;

    /* renamed from: A */
    public CameraDevice f6951A;

    /* renamed from: B */
    public CameraDevice f6952B;

    /* renamed from: C */
    public CameraCaptureSession f6953C;

    /* renamed from: D */
    public CameraCaptureSession f6954D;

    /* renamed from: E */
    public ImageReader f6955E;

    /* renamed from: F */
    public C1762u f6956F;

    /* renamed from: G */
    public C1754q f6957G;

    /* renamed from: H */
    public long f6958H;

    /* renamed from: b */
    public final CameraManager f6966b;

    /* renamed from: c */
    public final C1723c f6967c;

    /* renamed from: d */
    public final C1734g f6968d;

    /* renamed from: e */
    public final C1729e f6969e;

    /* renamed from: f */
    public final int f6970f;

    /* renamed from: g */
    public final int f6971g;

    /* renamed from: h */
    public final int f6972h;

    /* renamed from: i */
    public final Semaphore f6973i;

    /* renamed from: j */
    public final C1724c0 f6974j;

    /* renamed from: k */
    public final C0588o f6975k;

    /* renamed from: m */
    public String f6977m;

    /* renamed from: n */
    public boolean f6978n;

    /* renamed from: o */
    public boolean f6979o;

    /* renamed from: p */
    public boolean f6980p;

    /* renamed from: q */
    public boolean f6981q;

    /* renamed from: r */
    public boolean f6982r;

    /* renamed from: s */
    public boolean f6983s;

    /* renamed from: t */
    public boolean f6984t;

    /* renamed from: u */
    public boolean f6985u;

    /* renamed from: v */
    public boolean f6986v;

    /* renamed from: w */
    public int f6987w;

    /* renamed from: x */
    public HandlerThread f6988x;

    /* renamed from: y */
    public Handler f6989y;

    /* renamed from: z */
    public Thread f6990z;

    /* renamed from: a */
    public final Object f6965a = new Object();

    /* renamed from: l */
    public final LinkedHashMap f6976l = new LinkedHashMap();

    /* renamed from: N */
    public int f6964N = 1;

    /* renamed from: I */
    public long f6959I = 1;

    /* renamed from: J */
    public final C1748n f6960J = new C1748n(this);

    /* renamed from: K */
    public final C1750o f6961K = new C1750o(this);

    /* renamed from: L */
    public final C1752p f6962L = new C1752p(this);

    /* renamed from: M */
    public final C1746m f6963M = new ImageReader.OnImageAvailableListener() { // from class: io.twoyi.hal.m
        /* JADX WARN: Removed duplicated region for block: B:101:0x00f6 A[Catch: all -> 0x00d7, RuntimeException -> 0x00dc, TryCatch #12 {RuntimeException -> 0x00dc, all -> 0x00d7, blocks: (B:21:0x003b, B:22:0x003d, B:35:0x0054, B:39:0x0061, B:40:0x0063, B:43:0x006e, B:47:0x0081, B:48:0x0083, B:69:0x00d6, B:100:0x00f5, B:101:0x00f6, B:102:0x00fd, B:107:0x00ff, B:24:0x003e, B:26:0x0042, B:28:0x0047, B:30:0x004b, B:34:0x0053, B:50:0x0084, B:52:0x0088, B:53:0x008b, B:55:0x008f, B:57:0x009f, B:59:0x00a2, B:61:0x00ab, B:63:0x00c8, B:64:0x00d2, B:42:0x0064), top: B:20:0x003b, inners: #1, #3, #6 }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x0115  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:116:0x0034 -> B:78:0x0111). Please report as a decompilation issue!!! */
        @Override // android.media.ImageReader.OnImageAvailableListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onImageAvailable(android.media.ImageReader r13) {
            /*
                Method dump skipped, instructions count: 295
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1746m.onImageAvailable(android.media.ImageReader):void");
        }
    };

    /* JADX WARN: Type inference failed for: r0v7, types: [io.twoyi.hal.m] */
    public C1764v(CameraManager cameraManager, C1723c c1723c, C1734g c1734g, C1729e c1729e, int i, int i10, Semaphore semaphore, C1724c0 c1724c0) {
        this.f6966b = cameraManager;
        this.f6967c = c1723c;
        this.f6968d = c1734g;
        this.f6969e = c1729e;
        this.f6970f = i;
        this.f6971g = i10;
        this.f6973i = semaphore;
        this.f6974j = c1724c0;
        int m2950j = AbstractC1742k.m2950j(c1734g.f6815a, c1734g.f6816b);
        this.f6972h = m2950j;
        this.f6975k = new C0588o(m2950j);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0020 A[Catch: all -> 0x0029, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0009, B:8:0x000d, B:10:0x0011, B:12:0x0015, B:18:0x0020, B:19:0x0022, B:26:0x002b), top: B:3:0x0003 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void m2986a(io.twoyi.hal.C1764v r3) {
        /*
            java.lang.Object r0 = r3.f6965a
            monitor-enter(r0)
            int r1 = r3.f6964N     // Catch: java.lang.Throwable -> L29
            r2 = 8
            if (r1 != r2) goto L2b
            boolean r1 = r3.f6979o     // Catch: java.lang.Throwable -> L29
            if (r1 != 0) goto L1c
            boolean r1 = r3.f6980p     // Catch: java.lang.Throwable -> L29
            if (r1 != 0) goto L1c
            android.hardware.camera2.CameraDevice r1 = r3.f6952B     // Catch: java.lang.Throwable -> L29
            if (r1 != 0) goto L1c
            android.hardware.camera2.CameraCaptureSession r1 = r3.f6954D     // Catch: java.lang.Throwable -> L29
            if (r1 == 0) goto L1a
            goto L1c
        L1a:
            r1 = 0
            goto L1d
        L1c:
            r1 = 1
        L1d:
            if (r1 == 0) goto L20
            goto L2b
        L20:
            android.os.HandlerThread r3 = r3.f6988x     // Catch: java.lang.Throwable -> L29
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L29
            if (r3 == 0) goto L28
            r3.quitSafely()
        L28:
            return
        L29:
            r3 = move-exception
            goto L2d
        L2b:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L29
            return
        L2d:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L29
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1764v.m2986a(io.twoyi.hal.v):void");
    }

    /* renamed from: b */
    public static int m2987b(Integer num, String str) {
        if (num != null && num.intValue() >= 0) {
            return num.intValue();
        }
        throw new IllegalArgumentException("Host result omitted ".concat(str));
    }

    /* renamed from: c */
    public final boolean m2988c(long j3) {
        long elapsedRealtime = j3 - SystemClock.elapsedRealtime();
        if (elapsedRealtime > 0) {
            try {
                if (this.f6973i.tryAcquire(Math.min(elapsedRealtime, 1500L), TimeUnit.MILLISECONDS)) {
                    synchronized (this.f6965a) {
                        try {
                            if (this.f6964N != 1) {
                                this.f6973i.release();
                                return false;
                            }
                            this.f6978n = true;
                            return true;
                        } finally {
                        }
                    }
                }
            } catch (InterruptedException unused) {
                synchronized (this.f6965a) {
                    try {
                        int i = this.f6964N;
                        if (i != 7 && i != 8) {
                            Thread.currentThread().interrupt();
                        }
                    } finally {
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0079, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0017, code lost:
    
        if (r9.f6976l.isEmpty() == false) goto L14;
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m2989d(int r10, long r11) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.f6965a
            monitor-enter(r0)
        L3:
            r1 = 1
            if (r10 == r1) goto L35
            r1 = 2
            if (r10 == r1) goto L2b
            r1 = 3
            if (r10 == r1) goto L1d
            int r1 = r9.f6964N     // Catch: java.lang.Throwable -> L40
            r2 = 5
            if (r1 != r2) goto L1b
            java.util.LinkedHashMap r1 = r9.f6976l     // Catch: java.lang.Throwable -> L40
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L40
            if (r1 != 0) goto L1b
        L19:
            r1 = 1
            goto L3b
        L1b:
            r1 = 0
            goto L3b
        L1d:
            int r1 = r9.f6964N     // Catch: java.lang.Throwable -> L40
            r2 = 4
            if (r1 != r2) goto L1b
            boolean r1 = r9.f6981q     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L1b
            boolean r1 = r9.f6982r     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L1b
            goto L19
        L2b:
            int r1 = r9.f6964N     // Catch: java.lang.Throwable -> L40
            r2 = 4
            if (r1 != r2) goto L1b
            boolean r1 = r9.f6981q     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L1b
            goto L19
        L35:
            int r1 = r9.f6964N     // Catch: java.lang.Throwable -> L40
            r2 = 3
            if (r1 != r2) goto L1b
            goto L19
        L3b:
            if (r1 == 0) goto L42
            r10 = 1
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            return r10
        L40:
            r10 = move-exception
            goto L7a
        L42:
            int r1 = r9.f6964N     // Catch: java.lang.Throwable -> L40
            r2 = 6
            r3 = 0
            if (r1 == r2) goto L78
            r2 = 7
            if (r1 == r2) goto L78
            r4 = 8
            if (r1 != r4) goto L50
            goto L78
        L50:
            long r5 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L40
            long r5 = r11 - r5
            r7 = 0
            int r1 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r1 > 0) goto L5e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            return r3
        L5e:
            java.lang.Object r1 = r9.f6965a     // Catch: java.lang.Throwable -> L40 java.lang.InterruptedException -> L64
            r1.wait(r5)     // Catch: java.lang.Throwable -> L40 java.lang.InterruptedException -> L64
            goto L3
        L64:
            int r10 = r9.f6964N     // Catch: java.lang.Throwable -> L40
            if (r10 == r2) goto L76
            if (r10 == r4) goto L76
            java.lang.Thread r10 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L40
            r10.interrupt()     // Catch: java.lang.Throwable -> L40
            java.lang.String r10 = "Camera start interrupted"
            r9.m2999n(r10)     // Catch: java.lang.Throwable -> L40
        L76:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            return r3
        L78:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            return r3
        L7a:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L40
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1764v.m2989d(int, long):boolean");
    }

    /* renamed from: e */
    public final void m2990e() {
        synchronized (this.f6965a) {
            if (!this.f6985u && this.f6964N != 8) {
                this.f6985u = true;
                this.f6964N = 7;
                this.f6981q = false;
                this.f6982r = false;
                m2996k(8, "Camera session is closing");
                m2994i();
                CameraCaptureSession cameraCaptureSession = this.f6953C;
                this.f6953C = null;
                this.f6954D = cameraCaptureSession;
                ImageReader imageReader = this.f6955E;
                this.f6955E = null;
                CameraDevice cameraDevice = this.f6951A;
                this.f6951A = null;
                this.f6952B = cameraDevice;
                Thread thread = this.f6990z;
                this.f6965a.notifyAll();
                if (thread != null && thread != Thread.currentThread()) {
                    thread.interrupt();
                }
                if (cameraCaptureSession != null) {
                    try {
                        cameraCaptureSession.stopRepeating();
                    } catch (CameraAccessException | RuntimeException e10) {
                        Log.w("v", "Unable to stop camera repeating request", e10);
                    }
                    try {
                        cameraCaptureSession.abortCaptures();
                    } catch (CameraAccessException | RuntimeException e11) {
                        Log.w("v", "Unable to abort camera captures", e11);
                    }
                    try {
                        cameraCaptureSession.close();
                    } catch (RuntimeException e12) {
                        Log.w("v", "Unable to close camera capture session", e12);
                        synchronized (this.f6965a) {
                            this.f6954D = null;
                            this.f6965a.notifyAll();
                        }
                    }
                }
                if (imageReader != null) {
                    try {
                        imageReader.setOnImageAvailableListener(null, null);
                        imageReader.close();
                    } catch (RuntimeException e13) {
                        Log.w("v", "Unable to close camera ImageReader", e13);
                    }
                }
                if (cameraDevice != null) {
                    try {
                        cameraDevice.close();
                    } catch (RuntimeException e14) {
                        Log.w("v", "Unable to close host camera", e14);
                        synchronized (this.f6965a) {
                            this.f6952B = null;
                            this.f6965a.notifyAll();
                        }
                    }
                }
            }
        }
    }

    /* renamed from: f */
    public final CaptureRequest m2991f(CameraDevice cameraDevice, Surface surface, C1762u c1762u) {
        C1754q c1754q = c1762u.f6934b;
        CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(3);
        createCaptureRequest.addTarget(surface);
        createCaptureRequest.setTag(Long.valueOf(c1762u.f6944l));
        CaptureRequest.Key key = CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE;
        C1729e c1729e = this.f6969e;
        createCaptureRequest.set(key, new Range(Integer.valueOf(c1729e.f6778a), Integer.valueOf(c1729e.f6779b)));
        createCaptureRequest.set(CaptureRequest.CONTROL_MODE, Integer.valueOf(c1754q.f6876b));
        createCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(c1754q.f6877c));
        createCaptureRequest.set(CaptureRequest.CONTROL_AF_TRIGGER, Integer.valueOf(c1754q.f6878d));
        createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, Integer.valueOf(c1754q.f6879e));
        createCaptureRequest.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(c1754q.f6880f));
        createCaptureRequest.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, Integer.valueOf(c1754q.f6881g));
        createCaptureRequest.set(CaptureRequest.CONTROL_AWB_MODE, Integer.valueOf(c1754q.f6882h));
        createCaptureRequest.set(CaptureRequest.FLASH_MODE, Integer.valueOf(c1754q.f6883i));
        return createCaptureRequest.build();
    }

    /* renamed from: g */
    public final CaptureRequest m2992g(CameraDevice cameraDevice, Surface surface) {
        int i = 3;
        CaptureRequest.Builder createCaptureRequest = cameraDevice.createCaptureRequest(3);
        createCaptureRequest.addTarget(surface);
        CaptureRequest.Key key = CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE;
        C1729e c1729e = this.f6969e;
        createCaptureRequest.set(key, new Range(Integer.valueOf(c1729e.f6778a), Integer.valueOf(c1729e.f6779b)));
        createCaptureRequest.set(CaptureRequest.CONTROL_MODE, 1);
        CaptureRequest.Key key2 = CaptureRequest.CONTROL_AF_MODE;
        C1723c c1723c = this.f6967c;
        int[] iArr = c1723c.f6750g;
        int[] iArr2 = c1723c.f6750g;
        if (C0127d2.m375E(iArr, 4)) {
            i = 4;
        } else if (!C0127d2.m375E(iArr2, 3)) {
            if (C0127d2.m375E(iArr2, 1)) {
                i = 1;
            } else {
                i = 0;
            }
        }
        createCaptureRequest.set(key2, Integer.valueOf(i));
        createCaptureRequest.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
        createCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
        createCaptureRequest.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(Math.max(c1723c.f6753j, Math.min(c1723c.f6754k, 0))));
        createCaptureRequest.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 0);
        createCaptureRequest.set(CaptureRequest.CONTROL_AWB_MODE, 1);
        createCaptureRequest.set(CaptureRequest.FLASH_MODE, 0);
        return createCaptureRequest.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x018b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x00ae A[Catch: all -> 0x0098, TryCatch #3 {all -> 0x0098, blocks: (B:41:0x006b, B:43:0x006f, B:45:0x0073, B:47:0x0077, B:49:0x007b, B:51:0x007f, B:57:0x008e, B:64:0x00a3, B:65:0x00b5, B:66:0x00c6, B:164:0x00a8, B:166:0x00ae, B:167:0x019d, B:168:0x01a4, B:171:0x01a5, B:172:0x01ac, B:173:0x01ad, B:174:0x01b4), top: B:40:0x006b }] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x019d A[Catch: all -> 0x0098, TRY_ENTER, TryCatch #3 {all -> 0x0098, blocks: (B:41:0x006b, B:43:0x006f, B:45:0x0073, B:47:0x0077, B:49:0x007b, B:51:0x007f, B:57:0x008e, B:64:0x00a3, B:65:0x00b5, B:66:0x00c6, B:164:0x00a8, B:166:0x00ae, B:167:0x019d, B:168:0x01a4, B:171:0x01a5, B:172:0x01ac, B:173:0x01ad, B:174:0x01b4), top: B:40:0x006b }] */
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.twoyi.hal.C1758s m2993h(long r20, io.twoyi.hal.C1754q r22) {
        /*
            Method dump skipped, instructions count: 455
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1764v.m2993h(long, io.twoyi.hal.q):io.twoyi.hal.s");
    }

    /* renamed from: i */
    public final void m2994i() {
        LinkedHashMap linkedHashMap = this.f6976l;
        Iterator it = linkedHashMap.values().iterator();
        while (it.hasNext()) {
            ((C1760t) it.next()).close();
        }
        linkedHashMap.clear();
    }

    /* renamed from: j */
    public final void m2995j() {
        boolean z10;
        HandlerThread handlerThread;
        Handler handler;
        m2990e();
        long max = Math.max(0L, 1500L) + SystemClock.elapsedRealtime();
        synchronized (this.f6965a) {
            while (true) {
                if (!this.f6979o && this.f6952B == null) {
                    break;
                }
                long elapsedRealtime = max - SystemClock.elapsedRealtime();
                if (elapsedRealtime <= 0) {
                    break;
                }
                try {
                    this.f6965a.wait(elapsedRealtime);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            if (!this.f6979o && !this.f6980p && this.f6952B == null && this.f6954D == null) {
                z10 = false;
                m2994i();
                this.f6964N = 8;
                this.f6951A = null;
                this.f6953C = null;
                this.f6955E = null;
                this.f6952B = null;
                this.f6954D = null;
                handlerThread = this.f6988x;
                handler = this.f6989y;
                this.f6965a.notifyAll();
            }
            z10 = true;
            m2994i();
            this.f6964N = 8;
            this.f6951A = null;
            this.f6953C = null;
            this.f6955E = null;
            this.f6952B = null;
            this.f6954D = null;
            handlerThread = this.f6988x;
            handler = this.f6989y;
            this.f6965a.notifyAll();
        }
        if (handlerThread != null) {
            if (z10 && handler != null) {
                handler.postDelayed(new RunnableC0077b(7, handlerThread), 5000L);
            } else {
                handlerThread.quitSafely();
                if (Thread.currentThread() != handlerThread) {
                    long max2 = Math.max(0L, max - SystemClock.elapsedRealtime());
                    if (max2 > 0) {
                        try {
                            handlerThread.join(max2);
                        } catch (InterruptedException unused2) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
        synchronized (this.f6965a) {
            try {
                if (this.f6978n) {
                    this.f6978n = false;
                    this.f6973i.release();
                }
            } finally {
            }
        }
    }

    /* renamed from: k */
    public final void m2996k(int i, String str) {
        C1762u c1762u = this.f6956F;
        if (c1762u != null && c1762u.f6945m == null && c1762u.f6941i == 0) {
            c1762u.f6941i = i;
            c1762u.f6942j = str;
            this.f6965a.notifyAll();
        }
    }

    /* renamed from: l */
    public final void m2997l(String str, Throwable th) {
        boolean z10;
        C1727d0 c1727d0;
        synchronized (this.f6965a) {
            try {
                int i = this.f6964N;
                if (i != 7 && i != 8) {
                    if (this.f6983s && !this.f6986v) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    if (z10) {
                        this.f6986v = true;
                    }
                    m2999n(str);
                    if (z10) {
                        C1724c0 c1724c0 = this.f6974j;
                        C1735g0 c1735g0 = c1724c0.f6761a;
                        C1733f0 c1733f0 = c1724c0.f6762b;
                        if (c1733f0.f6810f == this && !c1733f0.f6807c) {
                            c1733f0.f6809e = true;
                            synchronized (c1733f0) {
                                c1727d0 = c1733f0.f6813i;
                                c1733f0.f6813i = null;
                            }
                            if (c1727d0 != null) {
                                c1727d0.m2885b();
                            }
                            if (th == null) {
                                Log.e("g0", "Fatal Camera2 session failure: " + str);
                            } else {
                                Log.e("g0", "Fatal Camera2 session failure: " + str, th);
                            }
                            LocalSocket localSocket = c1733f0.f6805a;
                            try {
                                localSocket.shutdownInput();
                            } catch (IOException unused) {
                            }
                            try {
                                localSocket.shutdownOutput();
                            } catch (IOException unused2) {
                            }
                        }
                    }
                }
            } finally {
            }
        }
    }

    /* renamed from: m */
    public final void m2998m(RuntimeException runtimeException) {
        synchronized (this.f6965a) {
            try {
                if (this.f6964N != 5) {
                    return;
                }
                int i = this.f6987w + 1;
                this.f6987w = i;
                if (i == 1) {
                    Log.w("v", "Dropping malformed Camera2 YUV frame", runtimeException);
                }
                if (i >= 3) {
                    m2997l("Host camera repeatedly returned invalid YUV frames", runtimeException);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: n */
    public final void m2999n(String str) {
        this.f6964N = 6;
        this.f6977m = str;
        m2996k(9, str);
        m2994i();
        this.f6965a.notifyAll();
    }

    /* renamed from: o */
    public final void m3000o(String str) {
        synchronized (this.f6965a) {
            try {
                int i = this.f6964N;
                if (i != 7 && i != 8) {
                    m2999n(str);
                }
            } finally {
            }
        }
    }

    /* renamed from: p */
    public final void m3001p(String str) {
        synchronized (this.f6965a) {
            try {
                int i = this.f6964N;
                if (i != 6 && i != 7 && i != 8) {
                    m2999n(str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0058 A[Catch: all -> 0x004d, TryCatch #2 {all -> 0x004d, blocks: (B:41:0x0044, B:43:0x0048, B:46:0x0050, B:48:0x0058, B:49:0x005a), top: B:40:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x005d  */
    /* renamed from: q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m3002q(long r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.f6965a
            monitor-enter(r0)
            int r1 = r6.f6964N     // Catch: java.lang.Throwable -> Lf
            r2 = 0
            r3 = 1
            if (r1 == r3) goto L11
            r7 = 5
            if (r1 != r7) goto Ld
            r2 = r3
        Ld:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lf
            return r2
        Lf:
            r7 = move-exception
            goto L63
        L11:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> Lf
            r6.f6990z = r1     // Catch: java.lang.Throwable -> Lf
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lf
            r0 = 8
            r1 = 7
            r4 = 0
            boolean r7 = r6.m3003r(r7)     // Catch: java.lang.Throwable -> L40
            java.lang.Object r8 = r6.f6965a
            monitor-enter(r8)
            int r5 = r6.f6964N     // Catch: java.lang.Throwable -> L35
            if (r5 == r1) goto L29
            if (r5 != r0) goto L2a
        L29:
            r2 = r3
        L2a:
            java.lang.Thread r0 = r6.f6990z     // Catch: java.lang.Throwable -> L35
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L35
            if (r0 != r1) goto L37
            r6.f6990z = r4     // Catch: java.lang.Throwable -> L35
            goto L37
        L35:
            r7 = move-exception
            goto L3e
        L37:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L35
            if (r2 == 0) goto L3d
            java.lang.Thread.interrupted()
        L3d:
            return r7
        L3e:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L35
            throw r7
        L40:
            r7 = move-exception
            java.lang.Object r8 = r6.f6965a
            monitor-enter(r8)
            int r5 = r6.f6964N     // Catch: java.lang.Throwable -> L4d
            if (r5 == r1) goto L4f
            int r1 = r6.f6964N     // Catch: java.lang.Throwable -> L4d
            if (r1 != r0) goto L50
            goto L4f
        L4d:
            r7 = move-exception
            goto L61
        L4f:
            r2 = r3
        L50:
            java.lang.Thread r0 = r6.f6990z     // Catch: java.lang.Throwable -> L4d
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Throwable -> L4d
            if (r0 != r1) goto L5a
            r6.f6990z = r4     // Catch: java.lang.Throwable -> L4d
        L5a:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L4d
            if (r2 == 0) goto L60
            java.lang.Thread.interrupted()
        L60:
            throw r7
        L61:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> L4d
            throw r7
        L63:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lf
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1764v.m3002q(long):boolean");
    }

    /* renamed from: r */
    public final boolean m3003r(long j3) {
        CameraDevice cameraDevice;
        CameraCaptureSession cameraCaptureSession;
        if (!m2988c(j3)) {
            m3000o("Camera is busy");
            return false;
        }
        HandlerThread handlerThread = new HandlerThread("twoyi_camera2_" + this.f6967c.f6744a);
        handlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: io.twoyi.hal.l
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                C1764v.this.m2997l("Camera callback thread failed", th);
            }
        });
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        synchronized (this.f6965a) {
            try {
                if (this.f6964N != 1) {
                    handlerThread.quitSafely();
                    return false;
                }
                this.f6988x = handlerThread;
                this.f6989y = handler;
                this.f6964N = 2;
                this.f6979o = true;
                try {
                    this.f6966b.openCamera(this.f6967c.f6745b, this.f6961K, handler);
                    if (!m2989d(1, j3)) {
                        m3001p("Timed out opening host camera");
                        return false;
                    }
                    synchronized (this.f6965a) {
                        if (this.f6964N == 3 && (cameraDevice = this.f6951A) != null) {
                            try {
                                C1734g c1734g = this.f6968d;
                                ImageReader newInstance = ImageReader.newInstance(c1734g.f6815a, c1734g.f6816b, 35, 3);
                                newInstance.setOnImageAvailableListener(this.f6963M, handler);
                                synchronized (this.f6965a) {
                                    if (this.f6964N == 3 && this.f6951A == cameraDevice) {
                                        this.f6955E = newInstance;
                                        this.f6964N = 4;
                                        this.f6980p = true;
                                        try {
                                            cameraDevice.createCaptureSession(Collections.singletonList(newInstance.getSurface()), this.f6962L, handler);
                                            if (!m2989d(2, j3)) {
                                                m3001p("Timed out configuring host camera");
                                                return false;
                                            }
                                            synchronized (this.f6965a) {
                                                if (this.f6964N == 4 && this.f6981q && (cameraCaptureSession = this.f6953C) != null) {
                                                    try {
                                                        cameraCaptureSession.setRepeatingRequest(m2992g(cameraDevice, newInstance.getSurface()), null, handler);
                                                        if (!m2989d(3, j3)) {
                                                            m3001p("Timed out activating host camera stream");
                                                            return false;
                                                        }
                                                        synchronized (this.f6965a) {
                                                            if (this.f6964N == 4 && this.f6953C == cameraCaptureSession) {
                                                                this.f6964N = 5;
                                                                this.f6983s = true;
                                                                this.f6987w = 0;
                                                                this.f6965a.notifyAll();
                                                                if (m2989d(4, j3)) {
                                                                    return true;
                                                                }
                                                                m3001p("Timed out waiting for first host camera frame");
                                                                return false;
                                                            }
                                                            return false;
                                                        }
                                                    } catch (CameraAccessException | RuntimeException unused) {
                                                        m3000o("Unable to start host camera repeating request");
                                                        return false;
                                                    }
                                                }
                                                return false;
                                            }
                                        } catch (CameraAccessException | RuntimeException unused2) {
                                            synchronized (this.f6965a) {
                                                this.f6980p = false;
                                                m3000o("Unable to configure host camera");
                                                return false;
                                            }
                                        }
                                    }
                                    newInstance.close();
                                    return false;
                                }
                            } catch (RuntimeException unused3) {
                                m3000o("Unable to create the camera ImageReader");
                                return false;
                            }
                        }
                        return false;
                    }
                } catch (CameraAccessException | IllegalArgumentException | SecurityException unused4) {
                    synchronized (this.f6965a) {
                        this.f6979o = false;
                        m3000o("Unable to open host camera");
                        return false;
                    }
                }
            } finally {
            }
        }
    }

    /* renamed from: s */
    public final void m3004s() {
        C1760t c1760t;
        C1762u c1762u = this.f6956F;
        if (c1762u != null) {
            long j3 = c1762u.f6935c;
            if (j3 > 0 && c1762u.f6945m == null && c1762u.f6941i == 0 && (c1760t = (C1760t) this.f6976l.remove(Long.valueOf(j3))) != null) {
                c1760t.f6924j = null;
                byte[] bArr = c1760t.f6923i;
                long j10 = c1762u.f6933a;
                long j11 = c1762u.f6935c;
                int i = this.f6967c.f6751h;
                C1734g c1734g = this.f6968d;
                c1762u.f6945m = new C1758s(j10, j11, i, c1734g.f6815a, c1734g.f6816b, c1762u.f6936d, c1762u.f6937e, c1762u.f6938f, c1762u.f6939g, c1762u.f6940h, bArr, this.f6975k);
                m2994i();
                this.f6965a.notifyAll();
            }
        }
    }
}

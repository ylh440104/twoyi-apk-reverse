package io.twoyi.hal;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.net.LocalSocket;
import android.os.Handler;
import android.util.Log;
import io.twoyi.AbstractServiceC1716h0;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ConcurrentHashMap;
import p009a8.C0127d2;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.g0 */
/* loaded from: classes.dex */
public final class C1735g0 {

    /* renamed from: i */
    public static final C1735g0 f6817i = new C1735g0();

    /* renamed from: a */
    public final ConcurrentHashMap f6818a = new ConcurrentHashMap();

    /* renamed from: b */
    public final Object f6819b = new Object();

    /* renamed from: c */
    public int f6820c;

    /* renamed from: d */
    public boolean f6821d;

    /* renamed from: e */
    public volatile Context f6822e;

    /* renamed from: f */
    public volatile CameraManager f6823f;

    /* renamed from: g */
    public volatile C0127d2 f6824g;

    /* renamed from: h */
    public volatile Handler f6825h;

    /* renamed from: c */
    public static void m2926c(LocalSocket localSocket) {
        try {
            localSocket.close();
        } catch (IOException e10) {
            Log.w("g0", "Unable to close camera socket", e10);
        }
    }

    /* renamed from: h */
    public static void m2927h(C1733f0 c1733f0) {
        if (c1733f0.f6806b && c1733f0.f6811g != null) {
        } else {
            throw new C1730e0(2, "Camera is not open");
        }
    }

    /* renamed from: j */
    public static void m2928j(C1733f0 c1733f0) {
        C1727d0 c1727d0;
        if (!c1733f0.f6807c && !c1733f0.f6808d) {
            c1733f0.f6808d = true;
            synchronized (c1733f0) {
                c1727d0 = c1733f0.f6813i;
                c1733f0.f6813i = null;
            }
            if (c1727d0 != null) {
                c1727d0.m2885b();
            }
            C1764v c1764v = c1733f0.f6810f;
            if (c1764v != null) {
                c1764v.m2990e();
            }
        }
    }

    /* renamed from: k */
    public static void m2929k(OutputStream outputStream, C1738i c1738i, C1764v c1764v) {
        ByteBuffer order = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN);
        order.putInt(c1764v.f6968d.f6815a);
        order.putInt(c1764v.f6968d.f6816b);
        order.putInt(825382478);
        C1729e c1729e = c1764v.f6969e;
        order.putInt(c1729e.f6778a);
        order.putInt(c1729e.f6779b);
        order.putInt(c1764v.f6972h);
        AbstractC1742k.m2957q(outputStream, c1738i.f6831a, c1738i.f6832b, 0, order.array());
    }

    /* renamed from: a */
    public final void m2930a(C1733f0 c1733f0) {
        C1727d0 c1727d0;
        c1733f0.f6807c = true;
        c1733f0.f6808d = true;
        c1733f0.f6806b = false;
        synchronized (c1733f0) {
            c1727d0 = c1733f0.f6813i;
            c1733f0.f6813i = null;
        }
        if (c1727d0 != null) {
            c1727d0.m2885b();
        }
        m2931b(c1733f0);
        c1733f0.f6811g = null;
        m2926c(c1733f0.f6805a);
    }

    /* renamed from: b */
    public final void m2931b(C1733f0 c1733f0) {
        C1764v c1764v = c1733f0.f6810f;
        c1733f0.f6810f = null;
        if (c1764v != null) {
            c1764v.m2990e();
            c1764v.m2995j();
        }
        m2932d(c1733f0);
    }

    /* renamed from: d */
    public final void m2932d(C1733f0 c1733f0) {
        boolean z10;
        synchronized (this.f6819b) {
            try {
                synchronized (c1733f0) {
                    z10 = c1733f0.f6814j;
                    c1733f0.f6814j = false;
                }
                if (!z10) {
                    return;
                }
                int i = this.f6820c;
                if (i > 0) {
                    this.f6820c = i - 1;
                }
                if (this.f6820c == 0 && this.f6821d) {
                    this.f6821d = false;
                    Context context = this.f6822e;
                    if (context == null) {
                        return;
                    }
                    try {
                        AbstractServiceC1716h0.m2863b(context, false, null, null);
                    } catch (RuntimeException e10) {
                        Log.w("g0", "Unable to disable camera foreground-service type", e10);
                    }
                }
            } finally {
            }
        }
    }

    /* renamed from: e */
    public final void m2933e(LocalSocket localSocket) {
        C1733f0 c1733f0 = new C1733f0(localSocket);
        Thread thread = new Thread(new RunnableC1759s0(2, this, c1733f0), "twoyi_camera_v2_peer_close");
        thread.setDaemon(true);
        thread.start();
        try {
            try {
                try {
                    InputStream inputStream = localSocket.getInputStream();
                    OutputStream outputStream = localSocket.getOutputStream();
                    long j3 = 0;
                    while (true) {
                        C1738i m2952l = AbstractC1742k.m2952l(inputStream, j3);
                        if (m2952l == null) {
                            break;
                        }
                        long j10 = m2952l.f6832b;
                        try {
                            m2935g(c1733f0, m2952l, outputStream);
                        } catch (C1730e0 e10) {
                            AbstractC1742k.m2955o(outputStream, m2952l.f6831a, m2952l.f6832b, e10.f6780i, e10.getMessage());
                        }
                        j3 = j10;
                    }
                } catch (C1736h e11) {
                    Log.w("g0", "Camera device protocol failure", e11);
                }
            } catch (EOFException e12) {
                if (!c1733f0.f6808d) {
                    Log.w("g0", "Camera device packet ended early", e12);
                }
            } catch (IOException e13) {
                if (!c1733f0.f6808d) {
                    Log.e("g0", "Camera device transport failed", e13);
                }
            }
            m2930a(c1733f0);
        } catch (Throwable th) {
            m2930a(c1733f0);
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
    
        throw new java.io.IOException("Invalid opcode on camera factory connection");
     */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2934f(android.net.LocalSocket r13) {
        /*
            r12 = this;
            java.lang.String r1 = "g0"
            java.io.InputStream r2 = r13.getInputStream()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            java.io.OutputStream r3 = r13.getOutputStream()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            r4 = 0
        Lc:
            io.twoyi.hal.i r9 = io.twoyi.hal.AbstractC1742k.m2952l(r2, r4)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            if (r9 != 0) goto L16
        L12:
            m2926c(r13)
            goto L77
        L16:
            long r10 = r9.f6832b     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            int r0 = r9.f6831a     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            r4 = 1
            if (r0 != r4) goto L5d
            r0 = 0
            io.twoyi.hal.AbstractC1742k.m2953m(r9, r0)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            a8.d2 r0 = r12.f6824g     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            if (r0 == 0) goto L43
            a8.d2 r0 = r0.m383J()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            java.lang.Object r0 = r0.f461j     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            r8 = r0
            byte[] r8 = (byte[]) r8     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            int r4 = r9.f6831a     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            long r5 = r9.f6832b     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            r7 = 0
            io.twoyi.hal.AbstractC1742k.m2957q(r3, r4, r5, r7, r8)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            goto L5b
        L37:
            r0 = move-exception
            goto L78
        L39:
            r0 = move-exception
            goto L65
        L3b:
            r0 = move-exception
            goto L6b
        L3d:
            r0 = move-exception
            goto L71
        L3f:
            r0 = move-exception
            goto L4b
        L41:
            r0 = move-exception
            goto L4b
        L43:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            java.lang.String r4 = "Camera HAL was not initialized"
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
            throw r0     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d java.lang.IllegalStateException -> L3f io.twoyi.hal.C1720b -> L41
        L4b:
            java.lang.String r4 = "Unable to build Camera2 catalog"
            android.util.Log.e(r1, r4, r0)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            int r4 = r9.f6831a     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            long r5 = r9.f6832b     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            java.lang.String r8 = "Camera catalog unavailable"
            r7 = 9
            io.twoyi.hal.AbstractC1742k.m2955o(r3, r4, r5, r7, r8)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
        L5b:
            r4 = r10
            goto Lc
        L5d:
            io.twoyi.hal.h r0 = new io.twoyi.hal.h     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            java.lang.String r2 = "Invalid opcode on camera factory connection"
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
            throw r0     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 io.twoyi.hal.C1736h -> L3b java.io.EOFException -> L3d
        L65:
            java.lang.String r2 = "Camera factory transport failed"
            android.util.Log.e(r1, r2, r0)     // Catch: java.lang.Throwable -> L37
            goto L12
        L6b:
            java.lang.String r2 = "Camera factory protocol failure"
            android.util.Log.w(r1, r2, r0)     // Catch: java.lang.Throwable -> L37
            goto L12
        L71:
            java.lang.String r2 = "Camera factory packet ended early"
            android.util.Log.w(r1, r2, r0)     // Catch: java.lang.Throwable -> L37
            goto L12
        L77:
            return
        L78:
            m2926c(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1735g0.m2934f(android.net.LocalSocket):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:289:0x046a A[Catch: IllegalStateException -> 0x047d, b -> 0x047f, TryCatch #16 {b -> 0x047f, IllegalStateException -> 0x047d, blocks: (B:278:0x0446, B:280:0x044a, B:282:0x0454, B:285:0x045b, B:289:0x046a, B:291:0x0481, B:292:0x0489, B:294:0x048a, B:295:0x0491), top: B:277:0x0446 }] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0481 A[Catch: IllegalStateException -> 0x047d, b -> 0x047f, TryCatch #16 {b -> 0x047f, IllegalStateException -> 0x047d, blocks: (B:278:0x0446, B:280:0x044a, B:282:0x0454, B:285:0x045b, B:289:0x046a, B:291:0x0481, B:292:0x0489, B:294:0x048a, B:295:0x0491), top: B:277:0x0446 }] */
    /* JADX WARN: Type inference failed for: r13v5, types: [java.lang.Object, io.twoyi.hal.d0] */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.lang.Object, io.twoyi.hal.d0] */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2935g(io.twoyi.hal.C1733f0 r27, io.twoyi.hal.C1738i r28, java.io.OutputStream r29) {
        /*
            Method dump skipped, instructions count: 1203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1735g0.m2935g(io.twoyi.hal.f0, io.twoyi.hal.i, java.io.OutputStream):void");
    }

    /* renamed from: i */
    public final void m2936i(C1733f0 c1733f0) {
        boolean z10;
        int i;
        synchronized (c1733f0) {
            z10 = c1733f0.f6814j;
            c1733f0.f6814j = false;
        }
        if (z10 && (i = this.f6820c) > 0) {
            this.f6820c = i - 1;
        }
        if (this.f6820c == 0) {
            this.f6821d = false;
            Context context = this.f6822e;
            if (context != null) {
                try {
                    AbstractServiceC1716h0.m2863b(context, false, null, null);
                } catch (RuntimeException e10) {
                    Log.w("g0", "Unable to roll back camera foreground-service type", e10);
                }
            }
        }
    }
}

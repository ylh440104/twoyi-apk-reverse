package io.twoyi.hal;

import android.net.LocalSocket;
import android.util.Log;
import ba.C0587n;
import io.twoyi.utils.IOUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p009a8.C0127d2;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.j0 */
/* loaded from: classes.dex */
public final class C1741j0 {

    /* renamed from: c */
    public static final C1741j0 f6847c = new C1741j0();

    /* renamed from: d */
    public static final ExecutorService f6848d = Executors.newCachedThreadPool();

    /* renamed from: a */
    public final AtomicInteger f6849a = new AtomicInteger();

    /* renamed from: b */
    public C0127d2 f6850b;

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c2, code lost:
    
        throw new java.io.IOException(p198r3.AbstractC3356a.m5776b(r4, "bad frame length "));
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void m2937b(java.io.InputStream r13, p009a8.C0127d2 r14) {
        /*
            r0 = 9
            byte[] r1 = new byte[r0]
        L4:
            io.twoyi.hal.C1761t0.m2980d(r13, r1, r0)
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.wrap(r1)
            java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r2 = r2.order(r3)
            int r4 = r2.getInt()
            int r5 = r2.getInt()
            byte r2 = r2.get()
            if (r4 < 0) goto Lb7
            r6 = 1048576(0x100000, float:1.469368E-39)
            if (r4 > r6) goto Lb7
            byte[] r6 = new byte[r4]
            io.twoyi.hal.C1761t0.m2980d(r13, r6, r4)
            java.lang.Object r7 = r14.f460i
            java.util.concurrent.ConcurrentHashMap r7 = (java.util.concurrent.ConcurrentHashMap) r7
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)
            java.lang.Object r7 = r7.get(r8)
            io.twoyi.hal.i0 r7 = (io.twoyi.hal.C1739i0) r7
            if (r7 != 0) goto L39
            goto L4
        L39:
            r8 = 3
            if (r2 == r8) goto L8a
            r9 = 4
            if (r2 != r9) goto L40
            goto L8a
        L40:
            r5 = 5
            if (r2 != r5) goto L6a
            if (r4 != r9) goto L6a
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.wrap(r6)
            java.nio.ByteBuffer r2 = r2.order(r3)
            int r2 = r2.getInt()
            ba.n r3 = new ba.n
            java.io.ByteArrayOutputStream r4 = r7.f6834a
            byte[] r4 = r4.toByteArray()
            java.io.ByteArrayOutputStream r5 = r7.f6835b
            byte[] r5 = r5.toByteArray()
            r3.<init>(r2, r4, r5)
            r7.f6837d = r3
            java.util.concurrent.CountDownLatch r2 = r7.f6836c
            r2.countDown()
            goto L4
        L6a:
            r3 = 6
            if (r2 != r3) goto L7c
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8
            r2.<init>(r6, r3)
            r7.f6838e = r2
            java.util.concurrent.CountDownLatch r2 = r7.f6836c
            r2.countDown()
            goto L4
        L7c:
            java.io.IOException r13 = new java.io.IOException
            java.lang.String r14 = "bad frame kind "
            java.lang.String r0 = " length "
            java.lang.String r14 = p008a7.AbstractC0108z.m336l(r2, r4, r14, r0)
            r13.<init>(r14)
            throw r13
        L8a:
            java.io.ByteArrayOutputStream r3 = r7.f6834a
            int r9 = r3.size()
            java.io.ByteArrayOutputStream r10 = r7.f6835b
            int r11 = r10.size()
            int r11 = r11 + r9
            int r11 = r11 + r4
            r9 = 33554432(0x2000000, float:9.403955E-38)
            r12 = 0
            if (r11 <= r9) goto Lae
            java.lang.String r2 = "output over 33554432 bytes"
            r7.f6838e = r2
            java.util.concurrent.CountDownLatch r2 = r7.f6836c
            r2.countDown()
            byte[] r2 = new byte[r12]     // Catch: java.io.IOException -> L4
            r3 = 2
            r14.m393T(r2, r5, r3)     // Catch: java.io.IOException -> L4
            goto L4
        Lae:
            if (r2 != r8) goto Lb1
            goto Lb2
        Lb1:
            r3 = r10
        Lb2:
            r3.write(r6, r12, r4)
            goto L4
        Lb7:
            java.io.IOException r13 = new java.io.IOException
            java.lang.String r14 = "bad frame length "
            java.lang.String r14 = p198r3.AbstractC3356a.m5776b(r4, r14)
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1741j0.m2937b(java.io.InputStream, a8.d2):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [a8.d2, java.lang.Object] */
    /* renamed from: a */
    public final void m2938a(LocalSocket localSocket) {
        try {
            InputStream inputStream = localSocket.getInputStream();
            OutputStream outputStream = localSocket.getOutputStream();
            int m2981e = C1761t0.m2981e(inputStream);
            C1761t0.m2982f(outputStream, 1);
            if (m2981e != 1) {
                Log.e("j0", "guest speaks version " + m2981e + "; ROM and App do not match");
                return;
            }
            ?? obj = new Object();
            obj.f460i = new ConcurrentHashMap();
            obj.f461j = outputStream;
            synchronized (this) {
                if (this.f6850b != null) {
                    Log.w("j0", "refused a second control connection");
                    return;
                }
                this.f6850b = obj;
                Log.i("j0", "guest control service connected");
                try {
                    try {
                        m2937b(inputStream, obj);
                        throw null;
                    } catch (EOFException unused) {
                        Log.i("j0", "guest control service disconnected");
                        m2939c(obj);
                    }
                } catch (Throwable th) {
                    m2939c(obj);
                    throw th;
                }
            }
        } finally {
            IOUtils.closeSilently(localSocket);
        }
    }

    /* renamed from: c */
    public final void m2939c(C0127d2 c0127d2) {
        synchronized (this) {
            this.f6850b = null;
        }
        for (C1739i0 c1739i0 : ((ConcurrentHashMap) c0127d2.f460i).values()) {
            c1739i0.f6838e = "guest control service disconnected";
            c1739i0.f6836c.countDown();
        }
    }

    /* renamed from: d */
    public final C0587n m2940d(int i, long j3, String str) {
        C0127d2 c0127d2;
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        if (bytes.length <= 1048576) {
            synchronized (this) {
                c0127d2 = this.f6850b;
                if (c0127d2 == null) {
                    throw new IOException("guest control service is not connected");
                }
            }
            int incrementAndGet = this.f6849a.incrementAndGet();
            C1739i0 c1739i0 = new C1739i0();
            ((ConcurrentHashMap) c0127d2.f460i).put(Integer.valueOf(incrementAndGet), c1739i0);
            try {
                try {
                    try {
                        c0127d2.m393T(bytes, incrementAndGet, i);
                        if (c1739i0.f6836c.await(j3, TimeUnit.MILLISECONDS)) {
                            if (c1739i0.f6837d != null) {
                                return c1739i0.f6837d;
                            }
                            throw new IOException(c1739i0.f6838e);
                        }
                        try {
                            c0127d2.m393T(new byte[0], incrementAndGet, 2);
                        } catch (IOException unused) {
                        }
                        throw new IOException("timed out after " + j3 + " ms: " + str);
                    } finally {
                        ((ConcurrentHashMap) c0127d2.f460i).remove(Integer.valueOf(incrementAndGet));
                    }
                } catch (IOException unused2) {
                    Thread.currentThread().interrupt();
                    throw new InterruptedIOException(str);
                }
            } catch (InterruptedException unused3) {
                c0127d2.m393T(new byte[0], incrementAndGet, 2);
                Thread.currentThread().interrupt();
                throw new InterruptedIOException(str);
            }
        }
        throw new IOException("request over 1048576 bytes");
    }
}

package io.twoyi;

import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.u0 */
/* loaded from: classes.dex */
public final class C1840u0 {

    /* renamed from: a */
    public final Handler f7313a;

    /* renamed from: b */
    public final ImageReader f7314b;

    /* renamed from: c */
    public final int f7315c;

    /* renamed from: d */
    public final int f7316d;

    /* renamed from: e */
    public Image f7317e;

    /* renamed from: f */
    public long f7318f;

    public C1840u0(int i, int i10) {
        ImageReader newInstance;
        HandlerThread handlerThread = new HandlerThread("guest-offscreen");
        this.f7315c = i;
        this.f7316d = i10;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.f7313a = handler;
        if (Build.VERSION.SDK_INT >= 29) {
            newInstance = ImageReader.newInstance(i, i10, 1, 2, 514L);
            this.f7314b = newInstance;
        } else {
            this.f7314b = ImageReader.newInstance(i, i10, 1, 2);
        }
        this.f7314b.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() { // from class: io.twoyi.t0
            @Override // android.media.ImageReader.OnImageAvailableListener
            public final void onImageAvailable(ImageReader imageReader) {
                C1840u0 c1840u0 = C1840u0.this;
                Image image = c1840u0.f7317e;
                if (image != null) {
                    image.close();
                    c1840u0.f7317e = null;
                }
                try {
                    Image acquireLatestImage = c1840u0.f7314b.acquireLatestImage();
                    if (acquireLatestImage != null) {
                        c1840u0.f7317e = acquireLatestImage;
                        long j3 = c1840u0.f7318f;
                        c1840u0.f7318f = 1 + j3;
                        if (j3 == 0) {
                            Log.i("u0", "first offscreen frame " + c1840u0.f7315c + "x" + c1840u0.f7316d);
                        }
                    }
                } catch (IllegalStateException e10) {
                    Log.w("u0", "offscreen frame not acquired", e10);
                } catch (UnsupportedOperationException e11) {
                    long j10 = c1840u0.f7318f;
                    c1840u0.f7318f = 1 + j10;
                    if (j10 == 0) {
                        Log.w("u0", "offscreen frames not readable: " + e11.getMessage());
                    }
                }
            }
        }, handler);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap m3152a(android.media.Image r15) {
        /*
            java.lang.String r0 = "offscreen buffer format 0x"
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 28
            java.lang.String r3 = "u0"
            if (r1 < r2) goto L4e
            android.hardware.HardwareBuffer r1 = p014b.AbstractC0354s.m926g(r15)
            if (r1 == 0) goto L49
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L3f
            int r0 = r1.getFormat()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = java.lang.Integer.toHexString(r0)     // Catch: java.lang.Throwable -> L3f
            r2.append(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = ", image format 0x"
            r2.append(r0)     // Catch: java.lang.Throwable -> L3f
            int r0 = r15.getFormat()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = java.lang.Integer.toHexString(r0)     // Catch: java.lang.Throwable -> L3f
            r2.append(r0)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L3f
            android.util.Log.i(r3, r0)     // Catch: java.lang.Throwable -> L3f
            int r0 = r1.getFormat()     // Catch: java.lang.Throwable -> L3f
            r1.close()
            goto L52
        L3f:
            r15 = move-exception
            r1.close()     // Catch: java.lang.Throwable -> L44
            goto L48
        L44:
            r0 = move-exception
            r15.addSuppressed(r0)
        L48:
            throw r15
        L49:
            if (r1 == 0) goto L4e
            r1.close()
        L4e:
            int r0 = r15.getFormat()
        L52:
            android.media.Image$Plane[] r1 = r15.getPlanes()
            r2 = 0
            r1 = r1[r2]
            int r4 = r15.getWidth()
            int r15 = r15.getHeight()
            int r5 = r1.getRowStride()
            java.nio.ByteBuffer r1 = r1.getBuffer()
            java.nio.ByteOrder r6 = java.nio.ByteOrder.LITTLE_ENDIAN
            java.nio.ByteBuffer r1 = r1.order(r6)
            r6 = 43
            r7 = 1
            if (r0 != r6) goto L76
            r6 = r7
            goto L77
        L76:
            r6 = r2
        L77:
            r8 = 2
            if (r6 != 0) goto L95
            if (r0 == r7) goto L95
            if (r0 == r8) goto L95
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r1 = "unsupported offscreen buffer format 0x"
            r15.<init>(r1)
            java.lang.String r0 = java.lang.Integer.toHexString(r0)
            r15.append(r0)
            java.lang.String r15 = r15.toString()
            android.util.Log.w(r3, r15)
            r15 = 0
            return r15
        L95:
            int r0 = r4 * r15
            int[] r0 = new int[r0]
            r3 = r2
        L9a:
            if (r3 >= r15) goto Ld6
            int r7 = r3 * r5
            r9 = r2
        L9f:
            if (r9 >= r4) goto Ld3
            int r10 = r9 * 4
            int r10 = r10 + r7
            int r10 = r1.getInt(r10)
            if (r6 == 0) goto Lb8
            r11 = r10 & 1023(0x3ff, float:1.434E-42)
            int r11 = r11 >> r8
            int r12 = r10 >>> 10
            r12 = r12 & 1023(0x3ff, float:1.434E-42)
            int r12 = r12 >> r8
            int r10 = r10 >>> 20
            r10 = r10 & 1023(0x3ff, float:1.434E-42)
            int r10 = r10 >> r8
            goto Lc2
        Lb8:
            r11 = r10 & 255(0xff, float:3.57E-43)
            int r12 = r10 >>> 8
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r10 = r10 >>> 16
            r10 = r10 & 255(0xff, float:3.57E-43)
        Lc2:
            int r13 = r3 * r4
            int r13 = r13 + r9
            r14 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            int r11 = r11 << 16
            r11 = r11 | r14
            int r12 = r12 << 8
            r11 = r11 | r12
            r10 = r10 | r11
            r0[r13] = r10
            int r9 = r9 + 1
            goto L9f
        Ld3:
            int r3 = r3 + 1
            goto L9a
        Ld6:
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r15 = android.graphics.Bitmap.createBitmap(r0, r4, r15, r1)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.C1840u0.m3152a(android.media.Image):android.graphics.Bitmap");
    }
}

package io.twoyi.hal;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.a1 */
/* loaded from: classes.dex */
public final class C1719a1 {

    /* renamed from: d */
    public static final int[] f6727d = {1, 2, 4, 5, 6, 8, 19};

    /* renamed from: e */
    public static final C1719a1 f6728e = new Object();

    /* renamed from: a */
    public Object f6729a;

    /* renamed from: b */
    public Object f6730b;

    /* renamed from: c */
    public Object f6731c;

    /* renamed from: a */
    public static void m2869a(ByteBuffer byteBuffer, String str) {
        byte[] bytes;
        if (str == null) {
            bytes = new byte[0];
        } else {
            bytes = str.getBytes(StandardCharsets.UTF_8);
        }
        int min = Math.min(bytes.length, 63);
        if (min == 0) {
            min = 0;
        } else {
            int i = min - 1;
            while (i > 0 && (bytes[i] & 192) == 128) {
                i--;
            }
            byte b5 = bytes[i];
            int i10 = 1;
            if ((b5 & 255) >= 128) {
                if ((b5 & 224) == 192) {
                    i10 = 2;
                } else if ((b5 & 240) == 224) {
                    i10 = 3;
                } else if ((b5 & 248) == 240) {
                    i10 = 4;
                }
            }
            if (i10 + i > min) {
                min = i;
            }
        }
        byteBuffer.put(bytes, 0, min);
        while (min < 64) {
            byteBuffer.put((byte) 0);
            min++;
        }
    }

    /* renamed from: d */
    public static int m2870d(byte[] bArr, int i) {
        return ByteBuffer.wrap(bArr, i, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0095  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m2871b(android.net.LocalSocket r10) {
        /*
            r9 = this;
            java.lang.String r0 = "handleSensors"
            java.lang.String r1 = "a1"
            android.util.Log.d(r1, r0)
            r0 = 0
            java.io.InputStream r2 = r10.getInputStream()     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L73
            io.twoyi.hal.z0 r3 = new io.twoyi.hal.z0     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L73
            r3.<init>(r9, r10)     // Catch: java.lang.Throwable -> L6f java.lang.Exception -> L73
            r0 = 4
            byte[] r4 = new byte[r0]     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
        L14:
            io.twoyi.hal.C1761t0.m2980d(r2, r4, r0)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r5 = 0
            int r6 = m2870d(r4, r5)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r7 = 1
            if (r6 != r7) goto L28
            r3.m3017e()     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            goto L14
        L23:
            r0 = move-exception
            goto L93
        L26:
            r0 = move-exception
            goto L76
        L28:
            r7 = 2
            if (r6 != r7) goto L44
            r6 = 12
            byte[] r7 = new byte[r6]     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            io.twoyi.hal.C1761t0.m2980d(r2, r7, r6)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            int r5 = m2870d(r7, r5)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            int r6 = m2870d(r7, r0)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r8 = 8
            int r7 = m2870d(r7, r8)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r3.m3013a(r5, r6, r7)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            goto L14
        L44:
            r7 = 3
            if (r6 != r7) goto L54
            byte[] r6 = new byte[r0]     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            io.twoyi.hal.C1761t0.m2980d(r2, r6, r0)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            int r5 = m2870d(r6, r5)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r3.m3016d(r5)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            goto L14
        L54:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r0.<init>()     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            java.lang.String r2 = "unknown sensor command: "
            r0.append(r2)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r0.append(r6)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            android.util.Log.w(r1, r0)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L26
            r3.m3015c()
            io.twoyi.utils.IOUtils.closeSilently(r10)
            return
        L6f:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L93
        L73:
            r2 = move-exception
            r3 = r0
            r0 = r2
        L76:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L23
            r2.<init>()     // Catch: java.lang.Throwable -> L23
            java.lang.String r4 = "sensor connection closed: "
            r2.append(r4)     // Catch: java.lang.Throwable -> L23
            r2.append(r0)     // Catch: java.lang.Throwable -> L23
            java.lang.String r0 = r2.toString()     // Catch: java.lang.Throwable -> L23
            android.util.Log.d(r1, r0)     // Catch: java.lang.Throwable -> L23
            if (r3 == 0) goto L8f
            r3.m3015c()
        L8f:
            io.twoyi.utils.IOUtils.closeSilently(r10)
            return
        L93:
            if (r3 == 0) goto L98
            r3.m3015c()
        L98:
            io.twoyi.utils.IOUtils.closeSilently(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1719a1.m2871b(android.net.LocalSocket):void");
    }

    /* renamed from: c */
    public Sensor m2872c(int i) {
        Iterator<Sensor> it = ((SensorManager) this.f6730b).getSensorList(i).iterator();
        Sensor sensor = null;
        while (it.hasNext()) {
            sensor = it.next();
            if (!sensor.isWakeUpSensor()) {
                break;
            }
        }
        return sensor;
    }

    /* renamed from: e */
    public void m2873e(Object obj) {
        Log.w("a1", "setService: " + obj);
        synchronized (((byte[]) this.f6729a)) {
            this.f6730b = obj;
        }
    }
}

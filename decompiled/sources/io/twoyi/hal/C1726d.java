package io.twoyi.hal;

import java.nio.ByteBuffer;
import p011aa.C0266y;
import p086h5.C1530r;
import p174p.AbstractC2824a;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.d */
/* loaded from: classes.dex */
public class C1726d {

    /* renamed from: a */
    public final /* synthetic */ int f6766a = 1;

    /* renamed from: b */
    public final int f6767b;

    /* renamed from: c */
    public int f6768c;

    /* renamed from: d */
    public int f6769d;

    /* renamed from: e */
    public int f6770e;

    /* renamed from: f */
    public final Object f6771f;

    /* renamed from: g */
    public Object f6772g;

    public C1726d(int i) {
        this.f6767b = i;
        if (i > 0) {
            this.f6771f = new C0266y(1);
            this.f6772g = new C1530r(20);
        } else {
            AbstractC2824a.m4925c("maxSize <= 0");
            throw null;
        }
    }

    /* renamed from: a */
    public static void m2881a(C1726d c1726d, int i, int i10, int i11, byte[] bArr, int i12, int i13) {
        int i14 = i12;
        int i15 = c1726d.f6768c;
        int i16 = c1726d.f6770e;
        ByteBuffer byteBuffer = (ByteBuffer) c1726d.f6771f;
        if (i >= 0 && i10 >= 0 && i11 > 0 && i14 >= 0 && i13 > 0) {
            long j3 = c1726d.f6767b;
            long j10 = i16;
            long j11 = (i10 * j10) + (i * c1726d.f6769d) + j3;
            long j12 = i11 - 1;
            long j13 = (j10 * j12) + j11;
            long j14 = i14;
            long j15 = (j12 * i13) + j14;
            if (j11 >= j3 && j13 >= j11 && j13 < i15 && j15 >= j14 && j15 < bArr.length) {
                if (i16 == 1 && i13 == 1) {
                    byteBuffer.limit(i15);
                    byteBuffer.position((int) j11);
                    byteBuffer.get(bArr, i14, i11);
                    return;
                }
                int intExact = Math.toIntExact((j13 - j11) + 1);
                byte[] bArr2 = (byte[]) c1726d.f6772g;
                if (bArr2 == null || bArr2.length < intExact) {
                    c1726d.f6772g = new byte[intExact];
                }
                byteBuffer.limit(i15);
                byteBuffer.position((int) j11);
                byteBuffer.get((byte[]) c1726d.f6772g, 0, intExact);
                int i17 = 0;
                for (int i18 = 0; i18 < i11; i18++) {
                    bArr[i14] = ((byte[]) c1726d.f6772g)[i17];
                    i14 += i13;
                    i17 += i16;
                }
                return;
            }
            throw new IllegalArgumentException("YUV plane is shorter than its stride layout");
        }
        throw new IllegalArgumentException("Negative YUV plane coordinate");
    }

    /* renamed from: b */
    public Object m2882b(Object obj) {
        AbstractC4450j.m7255e(obj, "key");
        synchronized (((C1530r) this.f6772g)) {
            C0266y c0266y = (C0266y) this.f6771f;
            c0266y.getClass();
            Object obj2 = c0266y.f916a.get(obj);
            if (obj2 != null) {
                this.f6769d++;
                return obj2;
            }
            this.f6770e++;
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a3, code lost:
    
        throw new java.lang.IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object m2883c(java.lang.Object r6, java.lang.Object r7) {
        /*
            r5 = this;
            java.lang.String r0 = "key"
            p265x8.AbstractC4450j.m7255e(r6, r0)
            java.lang.Object r0 = r5.f6772g
            h5.r r0 = (p086h5.C1530r) r0
            monitor-enter(r0)
            int r1 = r5.f6768c     // Catch: java.lang.Throwable -> L26
            int r1 = r1 + 1
            r5.f6768c = r1     // Catch: java.lang.Throwable -> L26
            java.lang.Object r1 = r5.f6771f     // Catch: java.lang.Throwable -> L26
            aa.y r1 = (p011aa.C0266y) r1     // Catch: java.lang.Throwable -> L26
            r1.getClass()     // Catch: java.lang.Throwable -> L26
            java.util.LinkedHashMap r1 = r1.f916a     // Catch: java.lang.Throwable -> L26
            java.lang.Object r6 = r1.put(r6, r7)     // Catch: java.lang.Throwable -> L26
            if (r6 == 0) goto L29
            int r7 = r5.f6768c     // Catch: java.lang.Throwable -> L26
            int r7 = r7 + (-1)
            r5.f6768c = r7     // Catch: java.lang.Throwable -> L26
            goto L29
        L26:
            r6 = move-exception
            goto La6
        L29:
            monitor-exit(r0)
            int r7 = r5.f6767b
        L2c:
            java.lang.Object r0 = r5.f6772g
            h5.r r0 = (p086h5.C1530r) r0
            monitor-enter(r0)
            int r1 = r5.f6768c     // Catch: java.lang.Throwable -> L46
            if (r1 < 0) goto L9c
            java.lang.Object r1 = r5.f6771f     // Catch: java.lang.Throwable -> L46
            aa.y r1 = (p011aa.C0266y) r1     // Catch: java.lang.Throwable -> L46
            java.util.LinkedHashMap r1 = r1.f916a     // Catch: java.lang.Throwable -> L46
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L48
            int r1 = r5.f6768c     // Catch: java.lang.Throwable -> L46
            if (r1 != 0) goto L9c
            goto L48
        L46:
            r6 = move-exception
            goto La4
        L48:
            int r1 = r5.f6768c     // Catch: java.lang.Throwable -> L46
            if (r1 <= r7) goto L9a
            java.lang.Object r1 = r5.f6771f     // Catch: java.lang.Throwable -> L46
            aa.y r1 = (p011aa.C0266y) r1     // Catch: java.lang.Throwable -> L46
            java.util.LinkedHashMap r1 = r1.f916a     // Catch: java.lang.Throwable -> L46
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L46
            if (r1 == 0) goto L59
            goto L9a
        L59:
            java.lang.Object r1 = r5.f6771f     // Catch: java.lang.Throwable -> L46
            aa.y r1 = (p011aa.C0266y) r1     // Catch: java.lang.Throwable -> L46
            java.util.LinkedHashMap r1 = r1.f916a     // Catch: java.lang.Throwable -> L46
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> L46
            java.lang.String r2 = "<get-entries>(...)"
            p265x8.AbstractC4450j.m7254d(r1, r2)     // Catch: java.lang.Throwable -> L46
            java.lang.Iterable r1 = (java.lang.Iterable) r1     // Catch: java.lang.Throwable -> L46
            java.lang.Object r1 = p114j8.AbstractC1933m.m3274l0(r1)     // Catch: java.lang.Throwable -> L46
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.lang.Throwable -> L46
            if (r1 != 0) goto L74
            monitor-exit(r0)
            return r6
        L74:
            java.lang.Object r2 = r1.getKey()     // Catch: java.lang.Throwable -> L46
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Throwable -> L46
            java.lang.Object r3 = r5.f6771f     // Catch: java.lang.Throwable -> L46
            aa.y r3 = (p011aa.C0266y) r3     // Catch: java.lang.Throwable -> L46
            r3.getClass()     // Catch: java.lang.Throwable -> L46
            java.lang.String r4 = "key"
            p265x8.AbstractC4450j.m7255e(r2, r4)     // Catch: java.lang.Throwable -> L46
            java.util.LinkedHashMap r3 = r3.f916a     // Catch: java.lang.Throwable -> L46
            r3.remove(r2)     // Catch: java.lang.Throwable -> L46
            int r2 = r5.f6768c     // Catch: java.lang.Throwable -> L46
            java.lang.String r3 = "value"
            p265x8.AbstractC4450j.m7255e(r1, r3)     // Catch: java.lang.Throwable -> L46
            int r2 = r2 + (-1)
            r5.f6768c = r2     // Catch: java.lang.Throwable -> L46
            monitor-exit(r0)
            goto L2c
        L9a:
            monitor-exit(r0)
            return r6
        L9c:
            java.lang.String r6 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L46
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L46
            throw r7     // Catch: java.lang.Throwable -> L46
        La4:
            monitor-exit(r0)
            throw r6
        La6:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1726d.m2883c(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public String toString() {
        int i;
        String str;
        switch (this.f6766a) {
            case 1:
                synchronized (((C1530r) this.f6772g)) {
                    try {
                        int i10 = this.f6769d;
                        int i11 = this.f6770e + i10;
                        if (i11 != 0) {
                            i = (i10 * 100) / i11;
                        } else {
                            i = 0;
                        }
                        str = "LruCache[maxSize=" + this.f6767b + ",hits=" + this.f6769d + ",misses=" + this.f6770e + ",hitRate=" + i + "%]";
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return str;
            default:
                return super.toString();
        }
    }

    public C1726d(ByteBuffer byteBuffer, int i, int i10) {
        if (byteBuffer != null) {
            this.f6771f = byteBuffer.duplicate();
            this.f6767b = byteBuffer.position();
            this.f6768c = byteBuffer.limit();
            this.f6769d = i;
            this.f6770e = i10;
            return;
        }
        throw new IllegalArgumentException("Plane buffer is missing");
    }
}

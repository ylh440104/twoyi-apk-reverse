package io.twoyi.host;

import p008a7.AbstractC0108z;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.p */
/* loaded from: classes.dex */
public final class C1804p {

    /* renamed from: a */
    public final int f7151a;

    /* renamed from: b */
    public final float f7152b;

    /* renamed from: c */
    public final float f7153c;

    /* renamed from: d */
    public final long f7154d;

    public C1804p(float f4, float f10, int i, long j3) {
        this.f7151a = i;
        this.f7152b = f4;
        this.f7153c = f10;
        this.f7154d = j3;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof C1804p) {
                C1804p c1804p = (C1804p) obj;
                if (this.f7151a != c1804p.f7151a || Float.compare(this.f7152b, c1804p.f7152b) != 0 || Float.compare(this.f7153c, c1804p.f7153c) != 0 || this.f7154d != c1804p.f7154d) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.f7154d) + AbstractC0108z.m326b(this.f7153c, AbstractC0108z.m326b(this.f7152b, Integer.hashCode(this.f7151a) * 31, 31), 31);
    }

    public final String toString() {
        return "Sample(action=" + this.f7151a + ", x=" + this.f7152b + ", y=" + this.f7153c + ", atMs=" + this.f7154d + ")";
    }
}

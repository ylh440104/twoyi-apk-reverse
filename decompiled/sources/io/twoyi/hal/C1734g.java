package io.twoyi.hal;

import java.util.Objects;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.g */
/* loaded from: classes.dex */
public final class C1734g {

    /* renamed from: a */
    public final int f6815a;

    /* renamed from: b */
    public final int f6816b;

    public C1734g(int i, int i10) {
        this.f6815a = i;
        this.f6816b = i10;
    }

    /* renamed from: a */
    public final long m2925a() {
        return this.f6815a * this.f6816b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1734g)) {
            return false;
        }
        C1734g c1734g = (C1734g) obj;
        if (this.f6815a == c1734g.f6815a && this.f6816b == c1734g.f6816b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f6815a), Integer.valueOf(this.f6816b));
    }

    public final String toString() {
        return this.f6815a + "x" + this.f6816b;
    }
}

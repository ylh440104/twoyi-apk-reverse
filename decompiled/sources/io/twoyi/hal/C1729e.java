package io.twoyi.hal;

import java.util.Objects;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.e */
/* loaded from: classes.dex */
public final class C1729e {

    /* renamed from: a */
    public final int f6778a;

    /* renamed from: b */
    public final int f6779b;

    public C1729e(int i, int i10) {
        this.f6778a = i;
        this.f6779b = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1729e)) {
            return false;
        }
        C1729e c1729e = (C1729e) obj;
        if (this.f6778a == c1729e.f6778a && this.f6779b == c1729e.f6779b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.f6778a), Integer.valueOf(this.f6779b));
    }

    public final String toString() {
        return "[" + this.f6778a + ", " + this.f6779b + "]";
    }
}

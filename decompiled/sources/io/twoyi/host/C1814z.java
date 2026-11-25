package io.twoyi.host;

import java.util.LinkedHashSet;
import p008a7.AbstractC0108z;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.z */
/* loaded from: classes.dex */
public final class C1814z {

    /* renamed from: a */
    public final int f7204a;

    /* renamed from: b */
    public final boolean f7205b;

    /* renamed from: c */
    public final LinkedHashSet f7206c;

    /* renamed from: d */
    public final int f7207d;

    /* renamed from: e */
    public final boolean f7208e;

    /* renamed from: f */
    public final boolean f7209f;

    public C1814z(int i, boolean z10, LinkedHashSet linkedHashSet, int i10, boolean z11, boolean z12) {
        this.f7204a = i;
        this.f7205b = z10;
        this.f7206c = linkedHashSet;
        this.f7207d = i10;
        this.f7208e = z11;
        this.f7209f = z12;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof C1814z) {
                C1814z c1814z = (C1814z) obj;
                if (this.f7204a != c1814z.f7204a || this.f7205b != c1814z.f7205b || !this.f7206c.equals(c1814z.f7206c) || this.f7207d != c1814z.f7207d || this.f7208e != c1814z.f7208e || this.f7209f != c1814z.f7209f) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f7209f) + AbstractC0108z.m330f(AbstractC0108z.m327c(this.f7207d, (this.f7206c.hashCode() + AbstractC0108z.m330f(Integer.hashCode(this.f7204a) * 31, 31, this.f7205b)) * 31, 31), 31, this.f7208e);
    }

    public final String toString() {
        return "TaskRow(displayId=" + this.f7204a + ", visible=" + this.f7205b + ", packages=" + this.f7206c + ", id=" + this.f7207d + ", standard=" + this.f7208e + ", backdrop=" + this.f7209f + ")";
    }
}

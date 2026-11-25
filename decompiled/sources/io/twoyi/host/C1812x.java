package io.twoyi.host;

import java.util.LinkedHashSet;
import java.util.List;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.x */
/* loaded from: classes.dex */
public final class C1812x {

    /* renamed from: a */
    public final Object f7199a;

    /* renamed from: b */
    public final LinkedHashSet f7200b;

    public C1812x(List list, LinkedHashSet linkedHashSet) {
        this.f7199a = list;
        this.f7200b = linkedHashSet;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof C1812x) {
                C1812x c1812x = (C1812x) obj;
                if (!this.f7199a.equals(c1812x.f7199a) || !this.f7200b.equals(c1812x.f7200b)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f7200b.hashCode() + (this.f7199a.hashCode() * 31);
    }

    public final String toString() {
        return "Arrivals(tasks=" + this.f7199a + ", displayPackages=" + this.f7200b + ")";
    }
}

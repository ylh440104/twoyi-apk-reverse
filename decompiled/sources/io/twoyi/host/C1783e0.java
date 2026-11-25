package io.twoyi.host;

import lb.AbstractC2295g;
import p151m9.C2436g;
import p265x8.AbstractC4450j;
import p265x8.C4459s;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.e0 */
/* loaded from: classes.dex */
public final class C1783e0 {

    /* renamed from: a */
    public final /* synthetic */ C4459s f7058a;

    /* renamed from: b */
    public final /* synthetic */ C2436g f7059b;

    public C1783e0(C4459s c4459s, C2436g c2436g) {
        this.f7058a = c4459s;
        this.f7059b = c2436g;
    }

    /* renamed from: a */
    public final void m3028a(int i, int i10) {
        EnumC1779c0 enumC1779c0;
        if (i != 32257) {
            return;
        }
        Object obj = this.f7058a.f16480i;
        if (obj != null) {
            AbstractC2295g.m3848f((C1783e0) obj);
            if (i10 == 0) {
                enumC1779c0 = EnumC1779c0.f7047i;
            } else {
                enumC1779c0 = EnumC1779c0.f7049k;
            }
            this.f7059b.mo1972f(enumC1779c0);
            return;
        }
        AbstractC4450j.m7260j("listener");
        throw null;
    }
}

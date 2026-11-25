package io.twoyi;

import android.content.Context;
import p009a8.C0127d2;
import p031c8.C0717a;
import p067f8.EnumC1179g0;
import p179p4.AbstractC2956d;
import p265x8.C4448h;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.w0 */
/* loaded from: classes.dex */
public final class C1844w0 {

    /* renamed from: a */
    public final /* synthetic */ C0127d2 f7334a;

    public C1844w0(C0127d2 c0127d2) {
        this.f7334a = c0127d2;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [w8.c, x8.h] */
    /* renamed from: a */
    public final void m3158a() {
        int i;
        int i10;
        C0127d2 c0127d2 = this.f7334a;
        C0717a c0717a = (C0717a) c0127d2.f461j;
        if (!c0717a.f3031a) {
            Context context = (Context) c0717a.f3032b;
            GuestInstanceClient m2758d = GuestInstanceClient.m2758d((String) c0717a.f3033c);
            int[] iArr = null;
            if (m2758d != null && (i = m2758d.f6626o) > 0 && (i10 = m2758d.f6627p) > 0) {
                iArr = new int[]{i, i10};
            }
            if (iArr != null) {
                AbstractC2956d.m5293o(context, iArr[0], iArr[1]);
            }
        }
        ((C4448h) c0127d2.f460i).mo169i(EnumC1179g0.f4796j);
    }
}

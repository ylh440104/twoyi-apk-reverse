package io.twoyi.host;

import android.content.Context;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.g0 */
/* loaded from: classes.dex */
public final class BinderC1787g0 extends BinderC1809u {

    /* renamed from: k0 */
    public static final int f7066k0 = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BinderC1787g0(Context context) {
        super(context);
        AbstractC4450j.m7255e(context, "context");
    }

    @Override // io.twoyi.host.BinderC1809u, io.twoyi.host.InterfaceC1803o0
    public void destroy() {
        m3117t0();
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}

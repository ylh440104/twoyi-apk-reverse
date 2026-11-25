package io.twoyi;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import io.twoyi.hal.C1727d0;
import io.twoyi.hal.C1743k0;
import p024c.C0600b;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.g0 */
/* loaded from: classes.dex */
public final class ResultReceiverC1714g0 extends ResultReceiver {

    /* renamed from: i */
    public final /* synthetic */ int f6699i;

    /* renamed from: j */
    public final /* synthetic */ Object f6700j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ResultReceiverC1714g0(Handler handler, Object obj, int i) {
        super(handler);
        this.f6699i = i;
        this.f6700j = obj;
    }

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i, Bundle bundle) {
        switch (this.f6699i) {
            case AbstractC4695a.f17215a /* 0 */:
                C1743k0 c1743k0 = (C1743k0) this.f6700j;
                boolean z10 = true;
                if (i != 1) {
                    z10 = false;
                }
                c1743k0.m2958a(z10);
                return;
            default:
                C0600b c0600b = (C0600b) this.f6700j;
                boolean z11 = true;
                if (i != 1) {
                    z11 = false;
                }
                ((C1727d0) c0600b.f2494b).m2886c(Boolean.valueOf(z11));
                return;
        }
    }
}

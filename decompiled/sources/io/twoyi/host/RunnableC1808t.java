package io.twoyi.host;

import android.os.Handler;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.t */
/* loaded from: classes.dex */
public final class RunnableC1808t implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ BinderC1809u f7160i;

    public RunnableC1808t(BinderC1809u binderC1809u) {
        this.f7160i = binderC1809u;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Handler handler;
        BinderC1809u binderC1809u = this.f7160i;
        BinderC1809u.m3109s0(binderC1809u, null, 1, null);
        if (BinderC1809u.m3103f0(binderC1809u)) {
            handler = binderC1809u.f7190R;
            handler.postDelayed(this, 1000L);
        }
    }
}

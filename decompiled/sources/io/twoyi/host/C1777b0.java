package io.twoyi.host;

import android.os.Handler;
import p007a6.RunnableC0077b;
import p089h8.RunnableC1582r;
import p255w8.InterfaceC4252c;
import p265x8.C4455o;
import p265x8.C4459s;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.b0 */
/* loaded from: classes.dex */
public final /* synthetic */ class C1777b0 {

    /* renamed from: a */
    public final /* synthetic */ C4455o f7042a;

    /* renamed from: b */
    public final /* synthetic */ RunnableC1582r f7043b;

    /* renamed from: c */
    public final /* synthetic */ InterfaceC4252c f7044c;

    /* renamed from: d */
    public final /* synthetic */ C4459s f7045d;

    public /* synthetic */ C1777b0(C4455o c4455o, RunnableC1582r runnableC1582r, InterfaceC4252c interfaceC4252c, C4459s c4459s) {
        this.f7042a = c4455o;
        this.f7043b = runnableC1582r;
        this.f7044c = interfaceC4252c;
        this.f7045d = c4459s;
    }

    /* renamed from: a */
    public final void m3027a() {
        C4455o c4455o = this.f7042a;
        if (c4455o.f16476i) {
            return;
        }
        c4455o.f16476i = true;
        Handler handler = C1785f0.f7062b;
        handler.removeCallbacks(this.f7043b);
        handler.post(new RunnableC0077b(10, this.f7045d));
        this.f7044c.mo169i(Boolean.TRUE);
    }
}

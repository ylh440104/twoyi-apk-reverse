package io.twoyi.hal;

import android.os.Handler;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import p089h8.EnumC1588x;
import p089h8.InterfaceC1586v;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.x */
/* loaded from: classes.dex */
public final /* synthetic */ class C1768x implements InterfaceC1586v {

    /* renamed from: a */
    public final /* synthetic */ int f6994a;

    /* renamed from: b */
    public final /* synthetic */ Object f6995b;

    public /* synthetic */ C1768x(int i, Object obj) {
        this.f6994a = i;
        this.f6995b = obj;
    }

    @Override // p089h8.InterfaceC1586v
    /* renamed from: a */
    public final void mo2654a(EnumC1588x enumC1588x) {
        switch (this.f6994a) {
            case AbstractC4695a.f17215a /* 0 */:
                C1769x0 c1769x0 = (C1769x0) this.f6995b;
                if (enumC1588x == EnumC1588x.f6343i) {
                    if (!((AtomicBoolean) c1769x0.f6998b).get()) {
                        C1770y.f7002d.execute(new RunnableC1766w(c1769x0, 1));
                        return;
                    }
                    return;
                } else {
                    ExecutorService executorService = C1770y.f7001c;
                    Log.w("y", "record audio permission unavailable: " + enumC1588x);
                    c1769x0.m3006a();
                    return;
                }
            case 1:
                ((C1727d0) this.f6995b).m2886c(enumC1588x);
                return;
            default:
                C1773z0 c1773z0 = (C1773z0) this.f6995b;
                synchronized (c1773z0) {
                    c1773z0.f7016g = false;
                }
                if (enumC1588x == EnumC1588x.f6343i) {
                    ((Handler) c1773z0.f7018i.f6731c).post(new RunnableC1771y0(c1773z0, 1));
                    return;
                }
                Log.w("a1", "step counter permission unavailable: " + enumC1588x);
                return;
        }
    }
}

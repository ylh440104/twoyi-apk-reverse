package io.twoyi;

import java.util.Iterator;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.r */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1833r implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f7304i;

    /* renamed from: j */
    public final /* synthetic */ BinderC1839u f7305j;

    public /* synthetic */ RunnableC1833r(BinderC1839u binderC1839u, float f4) {
        this.f7304i = 0;
        this.f7305j = binderC1839u;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f7304i) {
            case AbstractC4695a.f17215a /* 0 */:
                Iterator it = this.f7305j.f7312i.f6615d.iterator();
                while (it.hasNext()) {
                    ((C1844w0) it.next()).getClass();
                }
                return;
            case 1:
                this.f7305j.f7312i.m2763g(5, null);
                return;
            case 2:
                this.f7305j.f7312i.m2763g(3, null);
                return;
            default:
                this.f7305j.f7312i.m2763g(2, null);
                return;
        }
    }

    public /* synthetic */ RunnableC1833r(BinderC1839u binderC1839u, int i) {
        this.f7304i = i;
        this.f7305j = binderC1839u;
    }
}

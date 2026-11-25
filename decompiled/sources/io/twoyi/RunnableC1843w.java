package io.twoyi;

import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.w */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1843w implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f7332i;

    /* renamed from: j */
    public final /* synthetic */ ServiceConnectionC1845x f7333j;

    public /* synthetic */ RunnableC1843w(ServiceConnectionC1845x serviceConnectionC1845x, int i) {
        this.f7332i = i;
        this.f7333j = serviceConnectionC1845x;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f7332i) {
            case AbstractC4695a.f17215a /* 0 */:
                ServiceConnectionC1845x serviceConnectionC1845x = this.f7333j;
                GuestInstanceClient.m2756a(serviceConnectionC1845x.f7335a, serviceConnectionC1845x);
                return;
            case 1:
                ServiceConnectionC1845x serviceConnectionC1845x2 = this.f7333j;
                GuestInstanceClient.m2756a(serviceConnectionC1845x2.f7335a, serviceConnectionC1845x2);
                return;
            default:
                ServiceConnectionC1845x serviceConnectionC1845x3 = this.f7333j;
                GuestInstanceClient.m2756a(serviceConnectionC1845x3.f7335a, serviceConnectionC1845x3);
                return;
        }
    }
}

package io.twoyi.hal;

import android.os.Handler;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.m0 */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1747m0 implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f6860i;

    /* renamed from: j */
    public final /* synthetic */ C1757r0 f6861j;

    public /* synthetic */ RunnableC1747m0(C1757r0 c1757r0, int i) {
        this.f6860i = i;
        this.f6861j = c1757r0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.f6860i;
        C1757r0 c1757r0 = this.f6861j;
        switch (i) {
            case AbstractC4695a.f17215a /* 0 */:
                Handler handler = c1757r0.f6904p;
                if (handler != null) {
                    handler.post(new RunnableC1747m0(c1757r0, 1));
                    return;
                }
                return;
            case 1:
                c1757r0.m2973n();
                return;
            default:
                C1757r0 c1757r02 = C1757r0.f6888t;
                c1757r0.m2973n();
                return;
        }
    }
}

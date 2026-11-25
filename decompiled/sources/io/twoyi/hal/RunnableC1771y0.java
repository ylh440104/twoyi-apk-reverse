package io.twoyi.hal;

import android.os.Handler;
import p089h8.AbstractC1589y;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.y0 */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1771y0 implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f7006i;

    /* renamed from: j */
    public final /* synthetic */ C1773z0 f7007j;

    public /* synthetic */ RunnableC1771y0(C1773z0 c1773z0, int i) {
        this.f7006i = i;
        this.f7007j = c1773z0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f7006i) {
            case AbstractC4695a.f17215a /* 0 */:
                C1773z0 c1773z0 = this.f7007j;
                ((Handler) c1773z0.f7018i.f6731c).post(new RunnableC1771y0(c1773z0, 1));
                return;
            default:
                C1773z0 c1773z02 = this.f7007j;
                synchronized (c1773z02) {
                    if (!c1773z02.f7017h && c1773z02.f7014e && AbstractC1589y.m2661g("android.permission.ACTIVITY_RECOGNITION")) {
                        if (!c1773z02.f7012c.containsKey(19)) {
                            c1773z02.m3014b(19, c1773z02.f7015f, true);
                            return;
                        }
                        return;
                    }
                    return;
                }
        }
    }
}

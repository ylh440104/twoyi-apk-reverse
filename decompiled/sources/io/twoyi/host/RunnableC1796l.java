package io.twoyi.host;

import android.os.Process;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.l */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1796l implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f7115i;

    /* renamed from: j */
    public final /* synthetic */ HostDisplayService f7116j;

    public /* synthetic */ RunnableC1796l(HostDisplayService hostDisplayService, int i) {
        this.f7115i = i;
        this.f7116j = hostDisplayService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.f7115i;
        HostDisplayService hostDisplayService = this.f7116j;
        switch (i) {
            case AbstractC4695a.f17215a /* 0 */:
                int i10 = HostDisplayService.f7019x;
                hostDisplayService.stopSelf();
                Process.killProcess(Process.myPid());
                return;
            default:
                int i11 = HostDisplayService.f7019x;
                hostDisplayService.m3020f();
                return;
        }
    }
}

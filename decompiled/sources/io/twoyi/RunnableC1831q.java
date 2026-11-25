package io.twoyi;

import android.content.Intent;
import android.os.RemoteException;
import na.AbstractC2689d;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.q */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1831q implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f7302i;

    /* renamed from: j */
    public final /* synthetic */ GuestInstanceClient f7303j;

    public /* synthetic */ RunnableC1831q(GuestInstanceClient guestInstanceClient, int i) {
        this.f7302i = i;
        this.f7303j = guestInstanceClient;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InterfaceC1832q0 interfaceC1832q0;
        switch (this.f7302i) {
            case AbstractC4695a.f17215a /* 0 */:
                this.f7303j.m2765i();
                return;
            default:
                GuestInstanceClient guestInstanceClient = this.f7303j;
                GuestInstanceClient.m2757c();
                guestInstanceClient.f6628q = true;
                guestInstanceClient.f6622k = false;
                guestInstanceClient.f6629r = false;
                guestInstanceClient.f6614c.removeCallbacks(guestInstanceClient.f6635x);
                if (guestInstanceClient.f6620i != null) {
                    interfaceC1832q0 = guestInstanceClient.f6620i;
                } else {
                    interfaceC1832q0 = guestInstanceClient.f6609A;
                }
                if (interfaceC1832q0 != null) {
                    try {
                        interfaceC1832q0.mo2788a();
                    } catch (RemoteException unused) {
                    }
                } else if (GuestInstanceClient.m2760j(guestInstanceClient.f6612a, guestInstanceClient.m2762f()) >= 0) {
                    guestInstanceClient.f6612a.startService(new Intent(guestInstanceClient.f6612a, (Class<?>) AbstractC2689d.m4530K(guestInstanceClient.f6613b)).setAction("io.twoyi.action.SHUTDOWN_GUEST_INSTANCE"));
                }
                guestInstanceClient.m2767l();
                return;
        }
    }
}

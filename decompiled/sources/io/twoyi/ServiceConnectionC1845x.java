package io.twoyi;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Surface;
import java.util.Map;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.x */
/* loaded from: classes.dex */
public final class ServiceConnectionC1845x implements ServiceConnection, IBinder.DeathRecipient {

    /* renamed from: a */
    public final /* synthetic */ GuestInstanceClient f7335a;

    public ServiceConnectionC1845x(GuestInstanceClient guestInstanceClient) {
        this.f7335a = guestInstanceClient;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        this.f7335a.f6614c.post(new RunnableC1843w(this, 1));
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        this.f7335a.f6614c.post(new RunnableC1843w(this, 2));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (this.f7335a.f6619h == this) {
            boolean z10 = false;
            try {
                iBinder.linkToDeath(this, 0);
            } catch (RemoteException unused) {
            }
            InterfaceC1832q0 m2778b0 = BinderC1708d0.m2778b0(iBinder);
            this.f7335a.f6620i = m2778b0;
            try {
                String m2762f = this.f7335a.m2762f();
                GuestInstanceClient guestInstanceClient = this.f7335a;
                BinderC1839u binderC1839u = guestInstanceClient.f6611C;
                if (guestInstanceClient.f6623l && guestInstanceClient.f6617f.isEmpty()) {
                    z10 = true;
                }
                boolean z11 = z10;
                GuestInstanceClient guestInstanceClient2 = this.f7335a;
                m2778b0.mo2785Q(m2762f, binderC1839u, z11, guestInstanceClient2.f6624m, guestInstanceClient2.f6625n);
                m2778b0.mo2780E(this.f7335a.f6630s);
                for (Map.Entry entry : this.f7335a.f6617f.entrySet()) {
                    C1841v c1841v = (C1841v) entry.getValue();
                    long longValue = ((Long) entry.getKey()).longValue();
                    Surface surface = c1841v.f7329a;
                    C1707d c1707d = c1841v.f7330b;
                    m2778b0.mo2782J(longValue, surface, c1707d.f6651a, c1707d.f6652b, c1707d.f6653c, c1707d.f6654d, c1707d.f6655e);
                }
            } catch (RemoteException e10) {
                Log.w("GuestInstanceClient", "guest instance process went away during start", e10);
            } catch (RuntimeException e11) {
                Log.e("GuestInstanceClient", "guest instance start failed", e11);
                this.f7335a.m2763g(4, e11);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f7335a.f6614c.post(new RunnableC1843w(this, 0));
    }
}

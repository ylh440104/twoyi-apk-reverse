package io.twoyi;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.RemoteException;
import p009a8.C0138f3;
import p067f8.AbstractC1173f0;
import p067f8.C1178g;
import p067f8.C1195i4;
import p166o3.AbstractC2776b;
import p255w8.InterfaceC4252c;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.y */
/* loaded from: classes.dex */
public final class ServiceConnectionC1847y implements ServiceConnection {

    /* renamed from: a */
    public final /* synthetic */ GuestInstanceClient f7341a;

    public ServiceConnectionC1847y(GuestInstanceClient guestInstanceClient) {
        this.f7341a = guestInstanceClient;
    }

    /* renamed from: a */
    public final void m3161a() {
        this.f7341a.f6609A = null;
        C0138f3 c0138f3 = this.f7341a.f6636y;
        if (c0138f3 != null) {
            c0138f3.f500c = null;
            c0138f3.f501d = false;
            if (c0138f3.f503f) {
                c0138f3.f503f = false;
                InterfaceC4252c interfaceC4252c = c0138f3.f502e;
                if (interfaceC4252c != null) {
                    C1195i4 c1195i4 = AbstractC1173f0.f4783e;
                    Resources resources = c0138f3.f498a.getResources();
                    AbstractC4450j.m7254d(resources, "getResources(...)");
                    interfaceC4252c.mo169i(new C1178g(false, AbstractC2776b.m4801y(c1195i4, resources), false));
                }
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        GuestInstanceClient guestInstanceClient = this.f7341a;
        if (guestInstanceClient.f6637z == this) {
            m3161a();
            guestInstanceClient.f6612a.unbindService(this);
            guestInstanceClient.f6637z = null;
            C0138f3 c0138f3 = guestInstanceClient.f6636y;
            if (c0138f3 != null) {
                guestInstanceClient.m2768m(c0138f3);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (this.f7341a.f6637z != this) {
            return;
        }
        InterfaceC1832q0 m2778b0 = BinderC1708d0.m2778b0(iBinder);
        this.f7341a.f6609A = m2778b0;
        try {
            m2778b0.mo2780E(this.f7341a.f6630s);
        } catch (RemoteException unused) {
        }
        this.f7341a.m2764h(m2778b0);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (this.f7341a.f6637z == this) {
            m3161a();
        }
    }
}

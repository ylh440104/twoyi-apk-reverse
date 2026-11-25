package io.twoyi;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.j0 */
/* loaded from: classes.dex */
public final class ServiceConnectionC1818j0 implements ServiceConnection {

    /* renamed from: a */
    public final /* synthetic */ C1820k0 f7231a;

    public ServiceConnectionC1818j0(C1820k0 c1820k0) {
        this.f7231a = c1820k0;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f7231a.f7284m = BinderC1708d0.m2778b0(iBinder);
        this.f7231a.f7283l.countDown();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f7231a.f7284m = null;
    }
}

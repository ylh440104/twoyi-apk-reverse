package io.twoyi.host;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import p000a.AbstractC0000a;
import p027c3.C0679e;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.v */
/* loaded from: classes.dex */
public final class ServiceConnectionC1810v implements ServiceConnection {

    /* renamed from: a */
    public final Context f7195a;

    /* renamed from: b */
    public final boolean f7196b;

    public ServiceConnectionC1810v(Context context, boolean z10) {
        AbstractC4450j.m7255e(context, "app");
        this.f7195a = context;
        this.f7196b = z10;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String str;
        AbstractC4450j.m7255e(componentName, "name");
        AbstractC4450j.m7255e(iBinder, "binder");
        if (AbstractC0000a.f1b == this) {
            InterfaceC1803o0 m3099b0 = AbstractBinderC1801n0.m3099b0(iBinder);
            AbstractC0000a.f0a = m3099b0;
            if (this.f7196b) {
                str = "Shizuku";
            } else {
                str = "root";
            }
            Log.i("a", "helper connected via ".concat(str));
            C0679e c0679e = AbstractC0000a.f2c;
            if (c0679e != null) {
                AbstractC4450j.m7252b(m3099b0);
                c0679e.m1465k(m3099b0);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        AbstractC4450j.m7255e(componentName, "name");
        if (AbstractC0000a.f1b == this) {
            Log.w("a", "helper process went away");
            AbstractC0000a.f0a = null;
            AbstractC0000a.f1b = null;
            if (AbstractC0000a.f2c != null) {
                C1794k.m3062a("helper process died");
            }
        }
    }
}

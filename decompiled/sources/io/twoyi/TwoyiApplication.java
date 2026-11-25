package io.twoyi;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import io.twoyi.host.AbstractC1778c;
import na.AbstractC2689d;
import p009a8.AbstractC0109a;
import p216t.AbstractC3611n;
import rikka.shizuku.ShizukuProvider;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* loaded from: classes.dex */
public class TwoyiApplication extends Application {

    /* renamed from: i */
    public boolean f6638i;

    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        boolean z10;
        String str;
        super.attachBaseContext(context);
        C1842v0.f7331a = new C1701a(this);
        String m4544i = AbstractC2689d.m4544i();
        if (m4544i != null && !context.getPackageName().equals(m4544i)) {
            z10 = false;
        } else {
            z10 = true;
        }
        this.f6638i = z10;
        boolean z11 = ShizukuProvider.f12146i;
        StringBuilder sb = new StringBuilder("Enable built-in multi-process support (from ");
        if (z10) {
            str = "provider process";
        } else {
            str = "non-provider process";
        }
        sb.append(str);
        sb.append(")");
        Log.d("ShizukuProvider", sb.toString());
        ShizukuProvider.f12147j = z10;
        ShizukuProvider.f12146i = true;
    }

    @Override // android.app.Application
    public final void onCreate() {
        super.onCreate();
        AbstractC3611n.f12864a = false;
        if (this.f6638i || AbstractC2689d.m4546m() >= 0) {
            AbstractC1712f0.m2812F(this);
        }
        if (this.f6638i) {
            int i = AbstractC0109a.f409a;
            int i10 = AbstractC1778c.f7046a;
        }
    }
}

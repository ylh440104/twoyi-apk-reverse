package io.twoyi.host;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.util.ArrayList;
import lb.AbstractC2295g;
import lb.C2293e;
import lb.C2294f;
import moe.shizuku.api.BinderContainer;
import p007a6.RunnableC0077b;
import p089h8.RunnableC1582r;
import p255w8.InterfaceC4252c;
import p265x8.AbstractC4450j;
import rikka.shizuku.ShizukuProvider;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.f0 */
/* loaded from: classes.dex */
public final class C1785f0 {

    /* renamed from: a */
    public static final C1785f0 f7061a = new Object();

    /* renamed from: b */
    public static final Handler f7062b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    public static boolean f7063c;

    /* JADX WARN: Type inference failed for: r0v2, types: [x8.s, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v1, types: [x8.o, java.lang.Object] */
    /* renamed from: a */
    public static void m3029a(Context context, InterfaceC4252c interfaceC4252c) {
        Bundle bundle;
        IBinder iBinder;
        AbstractC4450j.m7255e(context, "context");
        if (!f7063c) {
            f7063c = true;
            Context applicationContext = context.getApplicationContext();
            if (!ShizukuProvider.f12147j) {
                Log.d("ShizukuProvider", "request binder in non-provider process");
                BroadcastReceiver broadcastReceiver = new BroadcastReceiver();
                if (Build.VERSION.SDK_INT >= 33) {
                    applicationContext.registerReceiver(broadcastReceiver, new IntentFilter("moe.shizuku.api.action.BINDER_RECEIVED"), 4);
                } else {
                    applicationContext.registerReceiver(broadcastReceiver, new IntentFilter("moe.shizuku.api.action.BINDER_RECEIVED"));
                }
                try {
                    bundle = applicationContext.getContentResolver().call(Uri.parse("content://" + applicationContext.getPackageName() + ".shizuku"), "getBinder", (String) null, new Bundle());
                } catch (Throwable unused) {
                    bundle = null;
                }
                if (bundle != null) {
                    bundle.setClassLoader(BinderContainer.class.getClassLoader());
                    BinderContainer binderContainer = (BinderContainer) bundle.getParcelable("moe.shizuku.privileged.api.intent.extra.BINDER");
                    if (binderContainer != null && (iBinder = binderContainer.f9254i) != null) {
                        Log.i("ShizukuProvider", "Binder received from other process");
                        AbstractC2295g.m3846d(iBinder, applicationContext.getPackageName());
                    }
                }
            }
        }
        ?? obj = new Object();
        ?? obj2 = new Object();
        RunnableC1582r runnableC1582r = new RunnableC1582r(obj, obj2, interfaceC4252c, 1);
        obj2.f16480i = new C1777b0(obj, runnableC1582r, interfaceC4252c, obj2);
        Handler handler = f7062b;
        handler.postDelayed(runnableC1582r, 3000L);
        Object obj3 = obj2.f16480i;
        if (obj3 != null) {
            C1777b0 c1777b0 = (C1777b0) obj3;
            if (AbstractC2295g.f8693f) {
                handler.post(new RunnableC0077b(12, c1777b0));
            }
            ArrayList arrayList = AbstractC2295g.f8696i;
            synchronized (arrayList) {
                arrayList.add(new C2293e(c1777b0, handler));
            }
            return;
        }
        AbstractC4450j.m7260j("listener");
        throw null;
    }

    /* renamed from: c */
    public static C2294f m3030c(Context context) {
        AbstractC4450j.m7255e(context, "context");
        C2294f c2294f = new C2294f(new ComponentName(context.getPackageName(), BinderC1787g0.class.getName()));
        c2294f.f8686d = "host-helper";
        c2294f.f8685c = "host";
        c2294f.f8687e = false;
        c2294f.f8684b = 10375;
        return c2294f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0054, code lost:
    
        if (r8 == r1) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARN: Type inference failed for: r8v8, types: [x8.s, java.lang.Object] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m3031b(android.content.Context r7, p171o8.AbstractC2784c r8) {
        /*
            Method dump skipped, instructions count: 228
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.host.C1785f0.m3031b(android.content.Context, o8.c):java.lang.Object");
    }
}

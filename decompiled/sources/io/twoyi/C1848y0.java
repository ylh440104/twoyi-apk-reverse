package io.twoyi;

import android.content.Context;
import android.content.Intent;
import android.net.LocalSocket;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import p042d8.RunnableC0863c;
import p042d8.RunnableC0865d;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.y0 */
/* loaded from: classes.dex */
public final class C1848y0 {

    /* renamed from: e */
    public static C1848y0 f7342e;

    /* renamed from: f */
    public static final ExecutorService f7343f = Executors.newCachedThreadPool();

    /* renamed from: a */
    public final AtomicBoolean f7344a = new AtomicBoolean(false);

    /* renamed from: b */
    public final CountDownLatch f7345b = new CountDownLatch(1);

    /* renamed from: c */
    public final Context f7346c;

    /* renamed from: d */
    public volatile String f7347d;

    public C1848y0(Context context) {
        this.f7346c = context;
    }

    /* renamed from: a */
    public final void m3162a(String str, long j3) {
        boolean z10;
        Intent className;
        if (str.startsWith("SWITCH_HOST")) {
            C1850z0 c1850z0 = C1850z0.f7349d;
            Context context = this.f7346c;
            if (c1850z0.f7350a.get()) {
                if (c1850z0.f7351b.get()) {
                    className = new Intent("android.intent.action.MAIN");
                    className.addCategory("android.intent.category.HOME");
                } else {
                    className = new Intent().setClassName(context, C1842v0.f7331a.mo2770b(context));
                }
                className.setFlags(335544320);
                context.startActivity(className);
                c1850z0.f7351b.set(!r8.get());
                return;
            }
            return;
        }
        if (str.startsWith("BOOT_COMPLETED")) {
            C1709e c1709e = C1709e.f6658s;
            synchronized (c1709e.f6659a) {
                C1821l c1821l = c1709e.f6675q;
                if (!c1709e.f6676r && c1709e.f6662d == 2 && c1821l != null && c1821l.f7287a == j3) {
                    synchronized (c1821l) {
                        if (!c1821l.f7289c && !c1821l.f7288b.isDone()) {
                            z10 = c1821l.f7288b.complete(null);
                        }
                        z10 = false;
                    }
                    if (z10) {
                        BootPrefetch.onBootCompleted();
                    }
                }
                z10 = false;
            }
            if (z10) {
                Log.i("y0", "guest boot completed session=" + j3);
                C1850z0 c1850z02 = C1850z0.f7349d;
                if (c1850z02.f7350a.compareAndSet(false, true)) {
                    c1850z02.f7352c.countDown();
                    return;
                }
                return;
            }
            Log.w("y0", "ignore stale/duplicate BOOT_COMPLETED session=" + j3);
            return;
        }
        if (str.startsWith("SETTINGS")) {
            C1842v0.f7331a.mo2774f(this.f7346c);
        }
    }

    /* renamed from: b */
    public final void m3163b(LocalSocket localSocket) {
        long j3;
        long j10;
        C1709e c1709e = C1709e.f6658s;
        synchronized (c1709e.f6659a) {
            C1821l c1821l = c1709e.f6675q;
            if (c1821l == null) {
                j3 = 0;
            } else {
                j3 = c1821l.f7287a;
            }
            j10 = j3;
        }
        f7343f.submit(new RunnableC0863c(this, localSocket, j10, 2));
    }

    /* renamed from: c */
    public final void m3164c(String str) {
        if (this.f7344a.compareAndSet(false, true)) {
            this.f7347d = str;
            ExecutorService executorService = f7343f;
            executorService.submit(new RunnableC1823m(8, this));
            executorService.submit(new RunnableC0865d(2));
        }
    }
}

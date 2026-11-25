package io.twoyi;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.Surface;
import io.twoyi.hal.C1761t0;
import io.twoyi.hal.C1763u0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import na.AbstractC2689d;
import p089h8.AbstractC1589y;
import p245v7.C4159b;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.e0 */
/* loaded from: classes.dex */
public abstract class AbstractServiceC1710e0 extends AbstractServiceC1716h0 {

    /* renamed from: y */
    public static final /* synthetic */ int f6677y = 0;

    /* renamed from: q */
    public String f6683q;

    /* renamed from: r */
    public String f6684r;

    /* renamed from: t */
    public int f6686t;

    /* renamed from: u */
    public int f6687u;

    /* renamed from: v */
    public boolean f6688v;

    /* renamed from: w */
    public String f6689w;

    /* renamed from: l */
    public final Handler f6678l = new Handler(Looper.getMainLooper());

    /* renamed from: m */
    public final RemoteCallbackListC1706c0 f6679m = new RemoteCallbackListC1706c0(this, 0);

    /* renamed from: n */
    public final Object f6680n = new Object();

    /* renamed from: o */
    public final LongSparseArray f6681o = new LongSparseArray();

    /* renamed from: p */
    public int f6682p = 0;

    /* renamed from: s */
    public float f6685s = -1.0f;

    /* renamed from: x */
    public final BinderC1708d0 f6690x = new BinderC1708d0(this, 0);

    /* renamed from: e */
    public final void m2802e(long j3) {
        Surface surface;
        synchronized (this.f6680n) {
            surface = (Surface) this.f6681o.get(j3);
            this.f6681o.remove(j3);
        }
        if (surface == null) {
            return;
        }
        C1709e.f6658s.m2799c(surface);
        this.f6678l.post(new RunnableC1823m(5, surface));
    }

    /* renamed from: f */
    public final void m2803f(Throwable th) {
        String th2;
        while (true) {
            if (((th instanceof CompletionException) || (th instanceof ExecutionException)) && th.getCause() != null) {
                th = th.getCause();
            }
        }
        Log.e("e0", "guest session failed", th);
        if (th.getMessage() != null) {
            th2 = th.getMessage();
        } else {
            th2 = th.toString();
        }
        m2806i(4, th2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, io.twoyi.o] */
    /* renamed from: g */
    public final void m2804g(String str) {
        synchronized (this.f6680n) {
            try {
                String str2 = this.f6689w;
                if (str2 != null) {
                    if (str2.equals(str)) {
                        return;
                    }
                    throw new IllegalArgumentException("slot already serves instance " + this.f6689w);
                }
                C4159b m6882e = C4159b.m6882e(this);
                if (m6882e.m6887d(str) != null) {
                    m6882e.m6891j(str);
                    try {
                        m6882e.m6890i(AbstractC2689d.m4546m(), str);
                    } catch (IOException e10) {
                        Log.w("e0", "slot record not written", e10);
                    }
                    ?? obj = new Object();
                    C1827o.m3140a(obj);
                    C1761t0.m2978b(this);
                    if (C1848y0.f7342e == null) {
                        C1848y0.f7342e = new C1848y0(this);
                    }
                    C1848y0.f7342e.m3164c("TWOYI_SOCK");
                    this.f6689w = str;
                    Log.i("e0", "slot " + AbstractC2689d.m4546m() + " serves instance " + str + " " + ((Object) obj));
                    return;
                }
                throw new IllegalArgumentException("unknown guest instance " + str);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: h */
    public final void m2805h() {
        int i;
        synchronized (this.f6680n) {
            try {
                if (this.f6688v) {
                    return;
                }
                this.f6688v = true;
                Log.i("e0", "stopping instance " + this.f6683q);
                C1709e c1709e = C1709e.f6658s;
                ArrayList arrayList = new ArrayList();
                synchronized (this.f6680n) {
                    i = 0;
                    for (int i10 = 0; i10 < this.f6681o.size(); i10++) {
                        try {
                            arrayList.add((Surface) this.f6681o.valueAt(i10));
                        } finally {
                        }
                    }
                    this.f6681o.clear();
                }
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    c1709e.m2799c((Surface) obj);
                }
                synchronized (c1709e.f6659a) {
                    try {
                        if (!c1709e.f6676r) {
                            c1709e.f6676r = true;
                            C1825n c1825n = c1709e.f6672n;
                            C1821l c1821l = c1709e.f6675q;
                            CompletableFuture completableFuture = c1709e.f6664f;
                            CancellationException cancellationException = new CancellationException("guest boot coordinator closed");
                            c1709e.f6663e.completeExceptionally(cancellationException);
                            if (c1821l != null) {
                                c1821l.m3138a(cancellationException);
                            }
                            if (completableFuture != null && !completableFuture.isDone()) {
                                completableFuture.completeExceptionally(cancellationException);
                            }
                            if (c1825n != null) {
                                try {
                                    c1825n.close();
                                } catch (IOException e10) {
                                    Log.e("e", "guest backend close failed", e10);
                                }
                            } else {
                                c1709e.f6673o.complete(null);
                            }
                            c1709e.f6661c.shutdown();
                        }
                    } finally {
                    }
                }
                AbstractServiceC1716h0.m2865d(this);
                this.f6678l.postDelayed(new RunnableC1823m(4, this), 500L);
            } finally {
            }
        }
    }

    /* renamed from: i */
    public final void m2806i(int i, String str) {
        RemoteCallbackListC1706c0 remoteCallbackListC1706c0;
        synchronized (this.f6680n) {
            try {
                int i10 = this.f6682p;
                if (i10 != 4 && i10 != 5 && i > i10) {
                    this.f6682p = i;
                    this.f6684r = str;
                    remoteCallbackListC1706c0 = this.f6679m;
                    int beginBroadcast = remoteCallbackListC1706c0.beginBroadcast();
                    for (int i11 = 0; i11 < beginBroadcast; i11++) {
                        InterfaceC1836s0 interfaceC1836s0 = (InterfaceC1836s0) remoteCallbackListC1706c0.getBroadcastItem(i11);
                        if (i != 2) {
                            if (i != 3) {
                                if (i != 4) {
                                    if (i == 5) {
                                        try {
                                            interfaceC1836s0.mo3145M();
                                        } catch (RemoteException unused) {
                                        }
                                    }
                                } else {
                                    interfaceC1836s0.mo3146U(str);
                                }
                            } else {
                                interfaceC1836s0.mo3148i();
                            }
                        } else {
                            interfaceC1836s0.mo3150u();
                        }
                    }
                    remoteCallbackListC1706c0.finishBroadcast();
                }
            } catch (Throwable th) {
                remoteCallbackListC1706c0.finishBroadcast();
                throw th;
            } finally {
            }
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        String stringExtra;
        if (intent == null) {
            stringExtra = null;
        } else {
            stringExtra = intent.getStringExtra("io.twoyi.extra.INSTANCE_ID");
        }
        if (stringExtra != null) {
            m2804g(stringExtra);
            C1842v0.f7331a.mo2772d(this);
        }
        return this.f6690x;
    }

    @Override // io.twoyi.AbstractServiceC1716h0, android.app.Service
    public final void onCreate() {
        float f4;
        super.onCreate();
        C1763u0 c1763u0 = C1763u0.f6946d;
        c1763u0.getClass();
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            applicationContext = this;
        }
        c1763u0.f6947a = applicationContext;
        C1849z c1849z = new C1849z(this);
        c1763u0.f6948b = c1849z;
        if (c1763u0.f6949c != -1) {
            int i = c1763u0.f6949c;
            if (i == -1) {
                f4 = -1.0f;
            } else {
                f4 = i / 255.0f;
            }
            c1849z.m3166b(f4);
        }
        AbstractC1589y.f6358h = new C1849z(this);
    }

    @Override // io.twoyi.AbstractServiceC1716h0, android.app.Service
    public final int onStartCommand(Intent intent, int i, int i10) {
        if (intent != null && "io.twoyi.action.SHUTDOWN_GUEST_INSTANCE".equals(intent.getAction())) {
            m2805h();
            return 2;
        }
        if (intent != null && C1842v0.f7331a.mo2773e(this, intent)) {
            return 2;
        }
        super.onStartCommand(intent, i, i10);
        return 2;
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        return false;
    }
}

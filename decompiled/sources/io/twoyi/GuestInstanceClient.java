package io.twoyi;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import na.AbstractC2689d;
import p009a8.C0127d2;
import p009a8.C0138f3;
import p067f8.C1160d;
import p067f8.C1202k;
import p067f8.EnumC1179g0;
import p198r3.AbstractC3356a;
import p245v7.C4159b;
import p255w8.InterfaceC4252c;
import p265x8.AbstractC4450j;
import p265x8.C4448h;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* loaded from: classes.dex */
public final class GuestInstanceClient {

    /* renamed from: D */
    public static GuestInstanceClient f6607D;

    /* renamed from: E */
    public static final GuestInstanceClient[] f6608E = new GuestInstanceClient[4];

    /* renamed from: A */
    public volatile InterfaceC1832q0 f6609A;

    /* renamed from: B */
    public int f6610B;

    /* renamed from: a */
    public final Context f6612a;

    /* renamed from: b */
    public final int f6613b;

    /* renamed from: g */
    public String f6618g;

    /* renamed from: h */
    public ServiceConnectionC1845x f6619h;

    /* renamed from: i */
    public volatile InterfaceC1832q0 f6620i;

    /* renamed from: k */
    public boolean f6622k;

    /* renamed from: l */
    public boolean f6623l;

    /* renamed from: m */
    public int f6624m;

    /* renamed from: n */
    public int f6625n;

    /* renamed from: o */
    public int f6626o;

    /* renamed from: p */
    public int f6627p;

    /* renamed from: q */
    public volatile boolean f6628q;

    /* renamed from: r */
    public boolean f6629r;

    /* renamed from: s */
    public boolean f6630s;

    /* renamed from: t */
    public boolean f6631t;

    /* renamed from: u */
    public MotionEvent f6632u;

    /* renamed from: w */
    public float f6634w;

    /* renamed from: y */
    public C0138f3 f6636y;

    /* renamed from: z */
    public ServiceConnectionC1847y f6637z;

    /* renamed from: c */
    public final Handler f6614c = new Handler(Looper.getMainLooper());

    /* renamed from: d */
    public final CopyOnWriteArrayList f6615d = new CopyOnWriteArrayList();

    /* renamed from: e */
    public final IdentityHashMap f6616e = new IdentityHashMap();

    /* renamed from: f */
    public final HashMap f6617f = new HashMap();

    /* renamed from: j */
    public int f6621j = 0;

    /* renamed from: v */
    public long f6633v = (Process.myPid() << 32) + 1;

    /* renamed from: x */
    public final RunnableC1831q f6635x = new RunnableC1831q(this, 0);

    /* renamed from: C */
    public final BinderC1839u f6611C = new BinderC1839u(this);

    public GuestInstanceClient(Context context, int i) {
        this.f6612a = context;
        this.f6613b = i;
    }

    /* renamed from: a */
    public static void m2756a(GuestInstanceClient guestInstanceClient, ServiceConnectionC1845x serviceConnectionC1845x) {
        int i;
        if (guestInstanceClient.f6619h != serviceConnectionC1845x) {
            return;
        }
        guestInstanceClient.f6620i = null;
        if (guestInstanceClient.f6629r) {
            guestInstanceClient.f6614c.removeCallbacks(guestInstanceClient.f6635x);
            Log.i("GuestInstanceClient", "guest instance process replaced; binding a fresh one");
            guestInstanceClient.m2765i();
        } else {
            if (guestInstanceClient.f6628q) {
                guestInstanceClient.m2767l();
                return;
            }
            if (guestInstanceClient.f6621j == 0 && (i = guestInstanceClient.f6610B) < 2) {
                guestInstanceClient.f6610B = i + 1;
                Log.w("GuestInstanceClient", "guest instance process died before boot; binding again");
                guestInstanceClient.m2765i();
            } else {
                guestInstanceClient.m2767l();
                guestInstanceClient.m2763g(4, new IllegalStateException("guest process died"));
            }
        }
    }

    /* renamed from: c */
    public static void m2757c() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
        } else {
            throw new IllegalStateException("GuestInstanceClient is main-thread only");
        }
    }

    /* renamed from: d */
    public static synchronized GuestInstanceClient m2758d(String str) {
        synchronized (GuestInstanceClient.class) {
            GuestInstanceClient guestInstanceClient = f6607D;
            if (guestInstanceClient != null && str.equals(guestInstanceClient.m2762f())) {
                return f6607D;
            }
            GuestInstanceClient[] guestInstanceClientArr = f6608E;
            for (int i = 0; i < 4; i++) {
                GuestInstanceClient guestInstanceClient2 = guestInstanceClientArr[i];
                if (guestInstanceClient2 != null && str.equals(guestInstanceClient2.f6618g)) {
                    return guestInstanceClient2;
                }
            }
            return null;
        }
    }

    /* renamed from: e */
    public static synchronized GuestInstanceClient m2759e(Context context, String str) {
        GuestInstanceClient guestInstanceClient;
        synchronized (GuestInstanceClient.class) {
            Context applicationContext = context.getApplicationContext();
            C4159b m6882e = C4159b.m6882e(applicationContext);
            if (str.equals(m6882e.m6889g().f15129a)) {
                synchronized (GuestInstanceClient.class) {
                    try {
                        if (f6607D == null) {
                            f6607D = new GuestInstanceClient(applicationContext.getApplicationContext(), 0);
                        }
                        guestInstanceClient = f6607D;
                    } finally {
                    }
                }
                return guestInstanceClient;
            }
            GuestInstanceClient[] guestInstanceClientArr = f6608E;
            for (int i = 0; i < 4; i++) {
                GuestInstanceClient guestInstanceClient2 = guestInstanceClientArr[i];
                if (guestInstanceClient2 != null && str.equals(guestInstanceClient2.f6618g)) {
                    return guestInstanceClient2;
                }
            }
            int i10 = 1;
            while (true) {
                if (i10 < 4) {
                    if (!str.equals(m6882e.m6892k(i10)) || !AbstractC2689d.m4555x(applicationContext, i10)) {
                        i10++;
                    }
                } else {
                    i10 = -1;
                    break;
                }
            }
            for (int i11 = 1; i10 < 0 && i11 < 4; i11++) {
                if (f6608E[i11] == null && !AbstractC2689d.m4555x(applicationContext, i11)) {
                    i10 = i11;
                }
            }
            if (i10 >= 0) {
                GuestInstanceClient guestInstanceClient3 = new GuestInstanceClient(applicationContext, i10);
                guestInstanceClient3.f6618g = str;
                f6608E[i10] = guestInstanceClient3;
                return guestInstanceClient3;
            }
            throw new IllegalStateException("no free guest slot for instance " + str);
        }
    }

    /* renamed from: j */
    public static int m2760j(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        C4159b m6882e = C4159b.m6882e(applicationContext);
        if (str.equals(m6882e.m6889g().f15129a)) {
            if (!AbstractC2689d.m4555x(applicationContext, 0)) {
                return -1;
            }
            return 0;
        }
        for (int i = 1; i < 4; i++) {
            if (str.equals(m6882e.m6892k(i)) && AbstractC2689d.m4555x(applicationContext, i)) {
                return i;
            }
        }
        return -1;
    }

    private static native boolean nativeDisconnectSurface(Surface surface);

    /* renamed from: b */
    public final void m2761b() {
        if (this.f6619h != null) {
            return;
        }
        ServiceConnectionC1845x serviceConnectionC1845x = new ServiceConnectionC1845x(this);
        int i = this.f6613b;
        Class m4530K = AbstractC2689d.m4530K(i);
        Context context = this.f6612a;
        if (!context.bindService(new Intent(context, (Class<?>) m4530K).putExtra("io.twoyi.extra.INSTANCE_ID", m2762f()), serviceConnectionC1845x, 65)) {
            m2763g(4, new IllegalStateException(AbstractC3356a.m5776b(i, "unable to bind guest instance slot ")));
        } else {
            this.f6619h = serviceConnectionC1845x;
        }
    }

    /* renamed from: f */
    public final String m2762f() {
        if (this.f6618g == null) {
            this.f6618g = C4159b.m6882e(this.f6612a).m6889g().f15129a;
        }
        return this.f6618g;
    }

    /* JADX WARN: Type inference failed for: r3v11, types: [w8.c, x8.h] */
    /* JADX WARN: Type inference failed for: r3v5, types: [w8.c, x8.h] */
    /* JADX WARN: Type inference failed for: r3v8, types: [w8.c, x8.h] */
    /* renamed from: g */
    public final void m2763g(int i, RuntimeException runtimeException) {
        int i10 = this.f6621j;
        if (i10 != 4 && i10 != 5 && i > i10) {
            this.f6621j = i;
            Iterator it = this.f6615d.iterator();
            while (it.hasNext()) {
                C1844w0 c1844w0 = (C1844w0) it.next();
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                ((C4448h) c1844w0.f7334a.f460i).mo169i(EnumC1179g0.f4798l);
                            }
                        } else {
                            C0127d2 c0127d2 = c1844w0.f7334a;
                            AbstractC4450j.m7255e(runtimeException, "error");
                            ((C4448h) c0127d2.f460i).mo169i(EnumC1179g0.f4797k);
                        }
                    } else {
                        c1844w0.m3158a();
                    }
                } else {
                    ((C4448h) c1844w0.f7334a.f460i).mo169i(EnumC1179g0.f4795i);
                }
            }
        }
    }

    /* renamed from: h */
    public final void m2764h(InterfaceC1832q0 interfaceC1832q0) {
        C1160d m433e;
        InterfaceC4252c interfaceC4252c;
        C0138f3 c0138f3 = this.f6636y;
        if (c0138f3 != null) {
            try {
                InterfaceC1824m0 mo2787X = interfaceC1832q0.mo2787X();
                if (mo2787X != null) {
                    c0138f3.f500c = mo2787X;
                    c0138f3.f501d = false;
                    if (c0138f3.f502e != null && (m433e = c0138f3.m433e(mo2787X)) != null && (interfaceC4252c = c0138f3.f502e) != null) {
                        interfaceC4252c.mo169i(new C1202k(m433e));
                    }
                }
            } catch (RemoteException unused) {
            }
        }
    }

    /* renamed from: i */
    public final void m2765i() {
        this.f6629r = false;
        this.f6628q = false;
        m2767l();
        Renderer.load();
        for (C1841v c1841v : this.f6617f.values()) {
            if (c1841v.f7329a.isValid() && nativeDisconnectSurface(c1841v.f7329a)) {
                Log.i("GuestInstanceClient", "released a stale window connection");
            }
        }
        this.f6621j = 0;
        this.f6622k = true;
        m2761b();
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [w8.c, x8.h] */
    /* JADX WARN: Type inference failed for: r5v11, types: [w8.c, x8.h] */
    /* JADX WARN: Type inference failed for: r5v5, types: [w8.c, x8.h] */
    /* JADX WARN: Type inference failed for: r5v8, types: [w8.c, x8.h] */
    /* renamed from: k */
    public final void m2766k(C1844w0 c1844w0) {
        EnumC1179g0 enumC1179g0 = EnumC1179g0.f4795i;
        m2757c();
        if (!this.f6622k && this.f6628q) {
            this.f6621j = 0;
            this.f6610B = 0;
        }
        if (!this.f6615d.contains(c1844w0)) {
            this.f6615d.add(c1844w0);
            int i = this.f6621j;
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            ((C4448h) c1844w0.f7334a.f460i).mo169i(EnumC1179g0.f4798l);
                        }
                    } else {
                        new IllegalStateException("guest session failed");
                        ((C4448h) c1844w0.f7334a.f460i).mo169i(EnumC1179g0.f4797k);
                    }
                } else {
                    ((C4448h) c1844w0.f7334a.f460i).mo169i(enumC1179g0);
                    c1844w0.m3158a();
                }
            } else {
                ((C4448h) c1844w0.f7334a.f460i).mo169i(enumC1179g0);
            }
        }
        if (this.f6622k) {
            return;
        }
        this.f6622k = true;
        this.f6628q = false;
        m2761b();
    }

    /* renamed from: l */
    public final void m2767l() {
        ServiceConnectionC1845x serviceConnectionC1845x = this.f6619h;
        this.f6619h = null;
        this.f6620i = null;
        if (serviceConnectionC1845x != null) {
            try {
                this.f6612a.unbindService(serviceConnectionC1845x);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    /* renamed from: m */
    public final void m2768m(C0138f3 c0138f3) {
        m2757c();
        this.f6636y = c0138f3;
        if (this.f6637z != null) {
            InterfaceC1832q0 interfaceC1832q0 = this.f6609A;
            if (interfaceC1832q0 != null) {
                m2764h(interfaceC1832q0);
                return;
            }
            return;
        }
        ServiceConnectionC1847y serviceConnectionC1847y = new ServiceConnectionC1847y(this);
        if (this.f6612a.bindService(new Intent(this.f6612a, (Class<?>) AbstractC2689d.m4530K(this.f6613b)).putExtra("io.twoyi.extra.INSTANCE_ID", m2762f()), serviceConnectionC1847y, 0)) {
            this.f6637z = serviceConnectionC1847y;
            return;
        }
        Log.w("GuestInstanceClient", "unable to watch guest instance slot " + this.f6613b);
    }
}

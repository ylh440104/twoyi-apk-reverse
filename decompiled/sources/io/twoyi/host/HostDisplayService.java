package io.twoyi.host;

import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.Surface;
import io.twoyi.AbstractServiceC1716h0;
import io.twoyi.BinderC1708d0;
import io.twoyi.InterfaceC1836s0;
import io.twoyi.RemoteCallbackListC1706c0;
import lb.AbstractC2295g;
import lb.C2294f;
import p000a.AbstractC0000a;
import p007a6.RunnableC0077b;
import p009a8.C0218w0;
import p057ea.C1024a;
import p223t7.AbstractC3725a;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* loaded from: classes.dex */
public final class HostDisplayService extends AbstractServiceC1716h0 {

    /* renamed from: x */
    public static final /* synthetic */ int f7019x = 0;

    /* renamed from: q */
    public volatile int f7025q;

    /* renamed from: r */
    public String f7026r;

    /* renamed from: s */
    public int f7027s;

    /* renamed from: t */
    public int f7028t;

    /* renamed from: u */
    public Boolean f7029u;

    /* renamed from: v */
    public boolean f7030v;

    /* renamed from: l */
    public final Handler f7020l = new Handler(Looper.getMainLooper());

    /* renamed from: m */
    public final RemoteCallbackListC1706c0 f7021m = new RemoteCallbackListC1706c0(this, 1);

    /* renamed from: n */
    public final LongSparseArray f7022n = new LongSparseArray();

    /* renamed from: o */
    public final LongSparseArray f7023o = new LongSparseArray();

    /* renamed from: p */
    public long f7024p = Long.MIN_VALUE;

    /* renamed from: w */
    public final BinderC1708d0 f7031w = new BinderC1708d0(this, 1);

    /* renamed from: e */
    public final void m3019e(long j3) {
        LongSparseArray longSparseArray = this.f7022n;
        Surface surface = (Surface) longSparseArray.get(j3);
        if (surface == null) {
            return;
        }
        longSparseArray.remove(j3);
        this.f7023o.remove(j3);
        if (this.f7024p == j3) {
            this.f7024p = Long.MIN_VALUE;
            InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
            C1794k.f7099n = null;
            C1794k.f7100o = 0;
            C1794k.f7101p = 0;
            C1794k.m3063b();
        }
        C1794k.f7090e.execute(new RunnableC0077b(9, surface));
    }

    /* renamed from: f */
    public final void m3020f() {
        DisplayManager displayManager;
        if (this.f7030v) {
            return;
        }
        this.f7030v = true;
        Log.i("HostDisplayService", "stopping the background display");
        InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
        InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
        C1794k.f7086a = null;
        C1794k.f7091f = null;
        C1794k.f7095j = null;
        C1794k.f7089d.removeCallbacksAndMessages(null);
        C1792j c1792j = C1794k.f7104s;
        if (c1792j != null && (displayManager = C1794k.f7105t) != null) {
            displayManager.unregisterDisplayListener(c1792j);
        }
        C1794k.f7104s = null;
        if (interfaceC1803o02 != null) {
            C1794k.f7090e.execute(new RunnableC1780d(interfaceC1803o02, 4));
        }
        if (AbstractC4450j.m7251a(Looper.myLooper(), Looper.getMainLooper())) {
            ServiceConnectionC1810v serviceConnectionC1810v = AbstractC0000a.f1b;
            if (serviceConnectionC1810v != null) {
                if (!serviceConnectionC1810v.f7196b) {
                    AbstractC3725a.m6354o(serviceConnectionC1810v);
                } else {
                    try {
                        C1785f0 c1785f0 = C1785f0.f7061a;
                        try {
                            ((C1024a) AbstractC2295g.m3849g()).m1977d0(null, C2294f.m3842b(C1785f0.m3030c(serviceConnectionC1810v.f7195a), true));
                        } catch (RemoteException e10) {
                            throw new RuntimeException(e10);
                        }
                    } catch (RuntimeException unused) {
                    }
                }
            }
            AbstractC0000a.f1b = null;
            AbstractC0000a.f0a = null;
            AbstractC0000a.f2c = null;
            AbstractServiceC1716h0.m2865d(this);
            this.f7020l.postDelayed(new RunnableC1796l(this, 0), 500L);
            return;
        }
        throw new IllegalStateException("HostHelperClient is main-thread only");
    }

    /* renamed from: g */
    public final void m3021g(int i, String str) {
        if (this.f7025q != 4 && i > this.f7025q) {
            this.f7025q = i;
            this.f7026r = str;
            if (str != null) {
                Log.w("HostDisplayService", "background display failed: ".concat(str));
            }
            int beginBroadcast = this.f7021m.beginBroadcast();
            for (int i10 = 0; i10 < beginBroadcast; i10++) {
                try {
                    IInterface broadcastItem = this.f7021m.getBroadcastItem(i10);
                    AbstractC4450j.m7254d(broadcastItem, "getBroadcastItem(...)");
                    InterfaceC1836s0 interfaceC1836s0 = (InterfaceC1836s0) broadcastItem;
                    if (i != 2) {
                        if (i != 3) {
                            if (i == 4) {
                                interfaceC1836s0.mo3146U(str);
                            }
                        } else {
                            interfaceC1836s0.mo3148i();
                        }
                    } else {
                        interfaceC1836s0.mo3150u();
                    }
                } catch (RemoteException unused) {
                } catch (Throwable th) {
                    this.f7021m.finishBroadcast();
                    throw th;
                }
            }
            this.f7021m.finishBroadcast();
        }
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        C0218w0.m540h(this);
        return this.f7031w;
    }

    @Override // io.twoyi.AbstractServiceC1716h0, android.app.Service
    public final int onStartCommand(Intent intent, int i, int i10) {
        String str;
        if (intent != null) {
            str = intent.getAction();
        } else {
            str = null;
        }
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != -2039861721) {
                if (hashCode == 1416496179 && str.equals("io.twoyi.action.ANSWER_AGENT_NEED")) {
                    C0218w0.m540h(this).f579e.mo430b(null, intent.getBooleanExtra("accepted", false));
                    return 2;
                }
            } else if (str.equals("io.twoyi.action.SHUTDOWN_GUEST_INSTANCE")) {
                m3020f();
                return 2;
            }
        }
        super.onStartCommand(intent, i, i10);
        return 2;
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        return false;
    }
}

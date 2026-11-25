package io.twoyi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import io.twoyi.AbstractC1712f0;
import io.twoyi.AbstractServiceC1716h0;
import io.twoyi.InterfaceC1836s0;
import io.twoyi.hal.C1741j0;
import io.twoyi.hal.RunnableC1737h0;
import io.twoyi.host.AbstractC1775a0;
import io.twoyi.host.AbstractC1797l0;
import io.twoyi.host.C1794k;
import io.twoyi.host.HostDisplayService;
import io.twoyi.host.InterfaceC1803o0;
import io.twoyi.host.RunnableC1796l;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import na.AbstractC2689d;
import p000a.AbstractC0000a;
import p008a7.C0106x;
import p009a8.C0137f2;
import p009a8.C0156j1;
import p009a8.C0218w0;
import p009a8.C0226y0;
import p009a8.RunnableC0121c1;
import p019b4.AbstractC0447a;
import p024c.C0600b;
import p027c3.C0679e;
import p028c4.RunnableC0690k;
import p042d8.RunnableC0865d;
import p068f9.C1292j;
import p081h0.AbstractC1474y0;
import p085h4.ExecutorC1492c;
import p089h8.AbstractC1589y;
import p089h8.EnumC1588x;
import p202r7.AbstractC3377c;
import p214s7.AbstractC3524f;
import p214s7.C3517b0;
import p247w0.AbstractC4176k;
import p265x8.AbstractC4450j;
import p278z.AbstractC4540b;
import p285z6.C4655i0;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.d0 */
/* loaded from: classes.dex */
public final class BinderC1708d0 extends Binder implements InterfaceC1832q0 {

    /* renamed from: i */
    public final /* synthetic */ int f6656i;

    /* renamed from: j */
    public final /* synthetic */ AbstractServiceC1716h0 f6657j;

    public BinderC1708d0(AbstractServiceC1716h0 abstractServiceC1716h0, int i) {
        this.f6656i = i;
        this.f6657j = abstractServiceC1716h0;
        attachInterface(this, "io.twoyi.IGuestInstance");
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [io.twoyi.p0, java.lang.Object, io.twoyi.q0] */
    /* renamed from: b0 */
    public static InterfaceC1832q0 m2778b0(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("io.twoyi.IGuestInstance");
        if (queryLocalInterface != null && (queryLocalInterface instanceof InterfaceC1832q0)) {
            return (InterfaceC1832q0) queryLocalInterface;
        }
        ?? obj = new Object();
        obj.f7299i = iBinder;
        return obj;
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: E */
    public final void mo2780E(boolean z10) {
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                C1850z0.f7349d.f7351b.set(z10);
                if (z10) {
                    AbstractC1589y.m2662h();
                }
                C1842v0.f7331a.mo2771c(z10);
                return;
            default:
                C0156j1 c0156j1 = C0156j1.f574k;
                if (c0156j1 != null) {
                    c0156j1.m461b(new C0226y0(c0156j1, z10, 0));
                    return;
                }
                return;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: F */
    public final void mo2781F(String str, long j3, ParcelFileDescriptor parcelFileDescriptor) {
        try {
            switch (this.f6656i) {
                case AbstractC4695a.f17215a /* 0 */:
                    try {
                        C1741j0 c1741j0 = C1741j0.f6847c;
                        ParcelFileDescriptor dup = parcelFileDescriptor.dup();
                        c1741j0.getClass();
                        C1741j0.f6848d.execute(new RunnableC1737h0(c1741j0, dup, 1, str, j3));
                        parcelFileDescriptor.close();
                        return;
                    } finally {
                    }
                default:
                    AbstractC4450j.m7255e(parcelFileDescriptor, "result");
                    parcelFileDescriptor.close();
                    return;
            }
        } catch (IOException e10) {
            int i = AbstractServiceC1710e0.f6677y;
            Log.e("e0", "unable to run a guest command", e10);
        }
        int i10 = AbstractServiceC1710e0.f6677y;
        Log.e("e0", "unable to run a guest command", e10);
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: J */
    public final void mo2782J(final long j3, final Surface surface, final int i, final int i10, float f4, float f10, int i11) {
        Surface surface2;
        boolean z10;
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                AbstractServiceC1710e0 abstractServiceC1710e0 = (AbstractServiceC1710e0) this.f6657j;
                C1707d c1707d = new C1707d(i, i10, f4, f10, i11);
                synchronized (abstractServiceC1710e0.f6680n) {
                    try {
                        surface2 = (Surface) abstractServiceC1710e0.f6681o.get(j3);
                        if (surface2 == null) {
                            abstractServiceC1710e0.f6681o.put(j3, surface);
                            surface2 = surface;
                        } else if (surface2 != surface) {
                            surface.release();
                        }
                    } finally {
                    }
                }
                C1709e c1709e = C1709e.f6658s;
                c1709e.getClass();
                if (surface2 != null) {
                    synchronized (c1709e.f6659a) {
                        try {
                            if (!c1709e.f6676r) {
                                boolean z11 = false;
                                if (c1709e.f6665g != surface2) {
                                    c1709e.f6665g = surface2;
                                    c1709e.f6669k++;
                                    c1709e.f6671m = 0;
                                }
                                if (!c1709e.f6663e.isDone()) {
                                    z10 = c1709e.f6663e.complete(c1707d);
                                } else {
                                    z10 = false;
                                }
                                int i12 = 1;
                                if (c1709e.f6666h == surface2) {
                                    z11 = true;
                                }
                                if (z10) {
                                    Log.i("e", "initial display fixed from Surface: " + c1707d);
                                }
                                if (z11) {
                                    c1709e.m2801g(new RunnableC1713g(c1709e, surface2, c1707d, i12));
                                    return;
                                } else {
                                    c1709e.m2801g(new RunnableC1823m(i12, c1709e));
                                    return;
                                }
                            }
                            return;
                        } finally {
                        }
                    }
                }
                return;
            default:
                AbstractC4450j.m7255e(surface, "surface");
                final HostDisplayService hostDisplayService = (HostDisplayService) this.f6657j;
                hostDisplayService.f7020l.post(new Runnable() { // from class: io.twoyi.host.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        HostDisplayService hostDisplayService2 = HostDisplayService.this;
                        long j10 = j3;
                        Surface surface3 = surface;
                        int i13 = i;
                        int i14 = i10;
                        LongSparseArray longSparseArray = hostDisplayService2.f7022n;
                        Surface surface4 = (Surface) longSparseArray.get(j10);
                        if (surface4 == null) {
                            longSparseArray.put(j10, surface3);
                        } else {
                            if (surface4 != surface3) {
                                surface3.release();
                            }
                            surface3 = surface4;
                        }
                        hostDisplayService2.f7023o.put(j10, new int[]{i13, i14});
                        hostDisplayService2.f7024p = j10;
                        InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
                        C1794k.f7099n = surface3;
                        C1794k.f7100o = i13;
                        C1794k.f7101p = i14;
                        C1794k.m3063b();
                        if (surface3 != null) {
                            C1794k.f7096k = SystemClock.uptimeMillis() + 5000;
                            Handler handler = C1794k.f7089d;
                            RunnableC0865d runnableC0865d = C1794k.f7097l;
                            handler.removeCallbacks(runnableC0865d);
                            InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                            if (interfaceC1803o02 != null && C1794k.f7099n != null) {
                                C1794k.f7090e.execute(new RunnableC1780d(interfaceC1803o02, 3));
                                handler.postDelayed(runnableC0865d, 1000L);
                            }
                        }
                    }
                });
                return;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: K */
    public final void mo2783K(MotionEvent motionEvent) {
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                if (motionEvent != null) {
                    if (C1850z0.f7349d.f7350a.get()) {
                        Renderer.handleTouch(motionEvent);
                    }
                    motionEvent.recycle();
                    return;
                }
                return;
            default:
                if (motionEvent != null) {
                    if (((HostDisplayService) this.f6657j).f7025q == 3) {
                        InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
                        InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                        if (interfaceC1803o02 != null && C1794k.f7100o > 0 && C1794k.f7101p > 0) {
                            C1292j c1292j = AbstractC1775a0.f7036a;
                            int i = C1794k.f7100o;
                            int i10 = C1794k.f7101p;
                            int i11 = C1794k.f7087b;
                            int i12 = C1794k.f7088c;
                            float f4 = i11;
                            float f10 = C1794k.f7102q;
                            float f11 = i12;
                            float f12 = C1794k.f7103r;
                            float min = Math.min(f4 / f10, f11 / f12);
                            float f13 = 2;
                            float f14 = (f4 - (f10 * min)) / f13;
                            float f15 = (f11 - (f12 * min)) / f13;
                            float f16 = f4 / (i * min);
                            float f17 = f11 / (i10 * min);
                            float f18 = (-f14) / min;
                            float f19 = (-f15) / min;
                            MotionEvent obtain = MotionEvent.obtain(motionEvent);
                            Matrix matrix = new Matrix();
                            matrix.setScale(f16, f17);
                            matrix.postTranslate(f18, f19);
                            obtain.transform(matrix);
                            C1794k.f7090e.execute(new RunnableC0121c1(9, obtain, interfaceC1803o02));
                        }
                    }
                    motionEvent.recycle();
                    return;
                }
                return;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: N */
    public final boolean mo2784N() {
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                return C1850z0.f7349d.f7350a.get();
            default:
                if (((HostDisplayService) this.f6657j).f7025q == 3) {
                    return true;
                }
                return false;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: Q */
    public final void mo2785Q(String str, final InterfaceC1836s0 interfaceC1836s0, boolean z10, final int i, final int i10) {
        boolean z11;
        CompletableFuture completableFuture;
        boolean z12;
        int i11;
        int i12;
        float f4;
        float f10;
        String str2;
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                final AbstractServiceC1710e0 abstractServiceC1710e0 = (AbstractServiceC1710e0) this.f6657j;
                synchronized (abstractServiceC1710e0.f6680n) {
                    try {
                        z11 = false;
                        if (abstractServiceC1710e0.f6688v) {
                            Log.w("e0", "start after stop; exiting for a fresh instance process");
                            Process.killProcess(Process.myPid());
                        } else {
                            abstractServiceC1710e0.m2804g(str);
                            String str3 = abstractServiceC1710e0.f6683q;
                            if (str3 == null) {
                                abstractServiceC1710e0.f6683q = str;
                            } else if (!str3.equals(str)) {
                                throw new IllegalArgumentException("slot already serves instance " + abstractServiceC1710e0.f6683q);
                            }
                            if (interfaceC1836s0 != null) {
                                abstractServiceC1710e0.f6679m.register(interfaceC1836s0, Integer.valueOf(Binder.getCallingPid()));
                                int i13 = abstractServiceC1710e0.f6682p;
                                String str4 = abstractServiceC1710e0.f6684r;
                                float f11 = abstractServiceC1710e0.f6685s;
                                int i14 = abstractServiceC1710e0.f6686t;
                                int i15 = abstractServiceC1710e0.f6687u;
                                if (i14 > 0 && i15 > 0) {
                                    try {
                                        interfaceC1836s0.mo3149r(i14, i15);
                                    } catch (RemoteException unused) {
                                    }
                                }
                                if (i13 != 2) {
                                    if (i13 != 3) {
                                        if (i13 != 4) {
                                            if (i13 == 5) {
                                                interfaceC1836s0.mo3145M();
                                            }
                                        } else {
                                            interfaceC1836s0.mo3146U(str4);
                                        }
                                    } else {
                                        interfaceC1836s0.mo3150u();
                                        interfaceC1836s0.mo3148i();
                                    }
                                } else {
                                    interfaceC1836s0.mo3150u();
                                }
                                if (f11 >= AbstractC4176k.f15279f) {
                                    interfaceC1836s0.mo3147W(f11);
                                }
                            }
                            if (abstractServiceC1710e0.f6682p == 0) {
                                abstractServiceC1710e0.f6682p = 1;
                                Log.i("e0", "starting instance " + str + " in slot " + AbstractC2689d.m4546m());
                                final C1709e c1709e = C1709e.f6658s;
                                if (!c1709e.f6673o.isDone() && AbstractServiceC1716h0.f6706k.compareAndSet(false, true)) {
                                    abstractServiceC1710e0.startForegroundService(new Intent(abstractServiceC1710e0, (Class<?>) AbstractC2689d.m4545l()));
                                }
                                c1709e.f6663e.thenAccept(new Consumer() { // from class: io.twoyi.a0
                                    @Override // java.util.function.Consumer
                                    public final void accept(Object obj) {
                                        AbstractServiceC1710e0 abstractServiceC1710e02 = AbstractServiceC1710e0.this;
                                        C1707d c1707d = (C1707d) obj;
                                        int i16 = c1707d.f6651a;
                                        int i17 = c1707d.f6652b;
                                        synchronized (abstractServiceC1710e02.f6680n) {
                                            try {
                                                abstractServiceC1710e02.f6686t = i16;
                                                abstractServiceC1710e02.f6687u = i17;
                                                int beginBroadcast = abstractServiceC1710e02.f6679m.beginBroadcast();
                                                for (int i18 = 0; i18 < beginBroadcast; i18++) {
                                                    try {
                                                        ((InterfaceC1836s0) abstractServiceC1710e02.f6679m.getBroadcastItem(i18)).mo3149r(i16, i17);
                                                    } catch (RemoteException unused2) {
                                                    } catch (Throwable th) {
                                                        abstractServiceC1710e02.f6679m.finishBroadcast();
                                                        throw th;
                                                    }
                                                }
                                                abstractServiceC1710e02.f6679m.finishBroadcast();
                                            } catch (Throwable th2) {
                                                throw th2;
                                            }
                                        }
                                    }
                                });
                                c1709e.f6673o.whenComplete(new BiConsumer() { // from class: io.twoyi.b0
                                    @Override // java.util.function.BiConsumer
                                    public final void accept(Object obj, Object obj2) {
                                        Throwable th = (Throwable) obj2;
                                        AbstractServiceC1710e0 abstractServiceC1710e02 = AbstractServiceC1710e0.this;
                                        abstractServiceC1710e02.getClass();
                                        AbstractServiceC1716h0.m2865d(abstractServiceC1710e02);
                                        if (th != null) {
                                            abstractServiceC1710e02.m2803f(th);
                                        } else {
                                            Log.w("e0", "guest session exited normally");
                                            abstractServiceC1710e02.m2806i(5, null);
                                        }
                                    }
                                });
                                final Context applicationContext = abstractServiceC1710e0.getApplicationContext();
                                synchronized (c1709e.f6659a) {
                                    try {
                                        if (c1709e.f6676r) {
                                            completableFuture = new CompletableFuture();
                                            completableFuture.completeExceptionally(new IllegalStateException("guest boot coordinator closed"));
                                        } else {
                                            final CompletableFuture completableFuture2 = c1709e.f6664f;
                                            if (completableFuture2 == null) {
                                                c1709e.f6662d = 2;
                                                c1709e.f6664f = new CompletableFuture();
                                                long j3 = c1709e.f6674p;
                                                c1709e.f6674p = 1 + j3;
                                                final C1821l c1821l = new C1821l(j3);
                                                c1709e.f6675q = c1821l;
                                                completableFuture2 = c1709e.f6664f;
                                                c1709e.f6661c.execute(new Runnable() { // from class: io.twoyi.b
                                                    @Override // java.lang.Runnable
                                                    public final void run() {
                                                        C1825n c1825n;
                                                        C1709e c1709e2 = C1709e.this;
                                                        Context context = applicationContext;
                                                        CompletableFuture completableFuture3 = completableFuture2;
                                                        C1821l c1821l2 = c1821l;
                                                        c1709e2.getClass();
                                                        BootPrefetch.start(context);
                                                        try {
                                                            C1709e.m2797e(context);
                                                            C1709e.m2795b(context);
                                                            synchronized (c1709e2.f6659a) {
                                                                try {
                                                                    if (!c1709e2.f6676r && c1709e2.f6675q == c1821l2) {
                                                                        c1825n = c1709e2.f6672n;
                                                                        if (c1825n == null) {
                                                                            c1825n = new C1825n();
                                                                            c1709e2.f6672n = c1825n;
                                                                        }
                                                                    } else {
                                                                        throw new CancellationException("boot session replaced or closed before backend start");
                                                                    }
                                                                } finally {
                                                                }
                                                            }
                                                            c1825n.f7293i.whenComplete((BiConsumer) new C1705c(c1709e2, c1821l2, 0));
                                                            c1825n.f7294j.whenComplete((BiConsumer) new C1705c(c1709e2, c1821l2, 1));
                                                            C1707d c1707d = (C1707d) c1709e2.f6663e.get();
                                                            C1840u0 c1840u0 = new C1840u0(c1707d.f6651a, c1707d.f6652b);
                                                            synchronized (c1709e2.f6659a) {
                                                                if (!c1709e2.f6676r && c1709e2.f6675q == c1821l2) {
                                                                    c1709e2.f6667i = c1840u0;
                                                                } else {
                                                                    throw new CancellationException("boot session replaced or closed before runtime start");
                                                                }
                                                            }
                                                            c1709e2.m2801g(new RunnableC1823m(1, c1709e2));
                                                            c1825n.m3139b(context, c1707d).get();
                                                            c1821l2.f7288b.get();
                                                            synchronized (c1709e2.f6659a) {
                                                                if (!c1709e2.f6676r && c1709e2.f6675q == c1821l2) {
                                                                    c1709e2.f6662d = 3;
                                                                } else {
                                                                    throw new CancellationException("boot session replaced or closed");
                                                                }
                                                            }
                                                            completableFuture3.complete(null);
                                                            Log.i("e", "guest runtime prepared: " + c1707d);
                                                            c1709e2.m2801g(new RunnableC1823m(1, c1709e2));
                                                        } catch (Throwable th) {
                                                            c1821l2.m3138a(th);
                                                            synchronized (c1709e2.f6659a) {
                                                                try {
                                                                    if (!c1709e2.f6676r && c1709e2.f6675q == c1821l2) {
                                                                        c1709e2.f6662d = 4;
                                                                    }
                                                                    c1709e2.f6673o.completeExceptionally(th);
                                                                    Log.e("e", "guest runtime preparation failed", th);
                                                                    completableFuture3.completeExceptionally(th);
                                                                } finally {
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                            }
                                            completableFuture = completableFuture2;
                                        }
                                    } finally {
                                    }
                                }
                                completableFuture.whenComplete((BiConsumer) new C1705c(abstractServiceC1710e0, c1709e));
                            }
                        }
                    } finally {
                    }
                }
                if (z10) {
                    C1709e c1709e2 = C1709e.f6658s;
                    Display display = ((DisplayManager) ((AbstractServiceC1710e0) this.f6657j).getSystemService(DisplayManager.class)).getDisplay(0);
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    display.getRealMetrics(displayMetrics);
                    int i16 = displayMetrics.widthPixels;
                    int i17 = displayMetrics.heightPixels;
                    if (i16 > i17) {
                        z12 = true;
                    } else {
                        z12 = false;
                    }
                    if (z12) {
                        i11 = i17;
                    } else {
                        i11 = i16;
                    }
                    if (z12) {
                        i12 = i16;
                    } else {
                        i12 = i17;
                    }
                    if (z12) {
                        f4 = displayMetrics.ydpi;
                    } else {
                        f4 = displayMetrics.xdpi;
                    }
                    float f12 = f4;
                    if (z12) {
                        f10 = displayMetrics.xdpi;
                    } else {
                        f10 = displayMetrics.ydpi;
                    }
                    float f13 = f10;
                    int refreshRate = (int) display.getRefreshRate();
                    if (refreshRate <= 0) {
                        refreshRate = 60;
                    }
                    int i18 = refreshRate;
                    C1707d c1707d = new C1707d(i11, i12, f12, f13, i18);
                    if (i > 0 && i10 > 0) {
                        c1707d = new C1707d(i, i10, f12, f13, i18);
                    }
                    if (i > 0 && i10 > 0) {
                        str2 = "Twin frame (headless start)";
                    } else {
                        str2 = "device display (headless start)";
                    }
                    synchronized (c1709e2.f6659a) {
                        try {
                            if (!c1709e2.f6676r && c1709e2.f6663e.complete(c1707d)) {
                                z11 = true;
                            }
                        } finally {
                        }
                    }
                    if (z11) {
                        Log.i("e", "initial display fixed from " + str2 + ": " + c1707d);
                        return;
                    }
                    return;
                }
                return;
            default:
                AbstractC4450j.m7255e(str, "instanceId");
                final int callingPid = Binder.getCallingPid();
                final HostDisplayService hostDisplayService = (HostDisplayService) this.f6657j;
                hostDisplayService.f7020l.post(new Runnable() { // from class: io.twoyi.host.n
                    @Override // java.lang.Runnable
                    public final void run() {
                        Executor executorC1492c;
                        int i19;
                        HostDisplayService hostDisplayService2 = HostDisplayService.this;
                        InterfaceC1836s0 interfaceC1836s02 = interfaceC1836s0;
                        int i20 = callingPid;
                        int i21 = i;
                        int i22 = i10;
                        if (hostDisplayService2.f7030v) {
                            Log.w("HostDisplayService", "start after stop; exiting for a fresh process");
                            Process.killProcess(Process.myPid());
                            return;
                        }
                        int i23 = 2;
                        if (interfaceC1836s02 != null) {
                            hostDisplayService2.f7021m.register(interfaceC1836s02, Integer.valueOf(i20));
                            try {
                                int i24 = hostDisplayService2.f7027s;
                                if (i24 > 0 && (i19 = hostDisplayService2.f7028t) > 0) {
                                    interfaceC1836s02.mo3149r(i24, i19);
                                }
                                if (hostDisplayService2.f7025q == 3) {
                                    interfaceC1836s02.mo3150u();
                                }
                                int i25 = hostDisplayService2.f7025q;
                                String str5 = hostDisplayService2.f7026r;
                                if (i25 != 2) {
                                    if (i25 != 3) {
                                        if (i25 == 4) {
                                            interfaceC1836s02.mo3146U(str5);
                                        }
                                    } else {
                                        interfaceC1836s02.mo3148i();
                                    }
                                } else {
                                    interfaceC1836s02.mo3150u();
                                }
                                Boolean bool = hostDisplayService2.f7029u;
                                if (bool != null) {
                                    interfaceC1836s02.mo3144D(bool.booleanValue());
                                }
                            } catch (RemoteException unused2) {
                            }
                        }
                        if (hostDisplayService2.f7025q == 0) {
                            if (!AbstractC1712f0.m2813G(hostDisplayService2)) {
                                hostDisplayService2.m3021g(4, "not in ROOT mode");
                                return;
                            }
                            hostDisplayService2.f7025q = 1;
                            if (AbstractServiceC1716h0.f6706k.compareAndSet(false, true)) {
                                hostDisplayService2.startForegroundService(new Intent(hostDisplayService2, (Class<?>) AbstractC2689d.m4545l()));
                            }
                            C0106x c0106x = new C0106x(16, hostDisplayService2);
                            C1794k.f7091f = c0106x;
                            Context applicationContext2 = hostDisplayService2.getApplicationContext();
                            C1776b c1776b = C1776b.f7039a;
                            AbstractC4450j.m7252b(applicationContext2);
                            C1776b.f7040b = applicationContext2.getSharedPreferences("host_audio", 0);
                            C1794k.f7105t = (DisplayManager) applicationContext2.getSystemService(DisplayManager.class);
                            C0679e c0679e = new C0679e(i21, i22, applicationContext2, c0106x);
                            if (AbstractC4450j.m7251a(Looper.myLooper(), Looper.getMainLooper())) {
                                AbstractC0000a.f2c = c0679e;
                                InterfaceC1803o0 interfaceC1803o0 = AbstractC0000a.f0a;
                                if (interfaceC1803o0 != null) {
                                    c0679e.m1465k(interfaceC1803o0);
                                    return;
                                }
                                if (AbstractC0000a.f1b == null && !AbstractC0000a.f3d) {
                                    Context applicationContext3 = applicationContext2.getApplicationContext();
                                    AbstractC4450j.m7252b(applicationContext3);
                                    EnumC1774a m2835c = AbstractC1712f0.m2835c(applicationContext3);
                                    if (m2835c == null) {
                                        C1794k.m3062a("no root or Shizuku access");
                                        return;
                                    }
                                    AbstractC0000a.f3d = true;
                                    if (m2835c == EnumC1774a.f7033j) {
                                        C1785f0 c1785f0 = C1785f0.f7061a;
                                        C1785f0.m3029a(applicationContext3, new C0137f2(applicationContext3, i23));
                                        return;
                                    }
                                    ExecutorService executorService = AbstractC3377c.f12089i;
                                    C3517b0 m6011b = AbstractC3524f.m6011b();
                                    if (m6011b != null) {
                                        if (m6011b.m5798b()) {
                                            m6011b = null;
                                        }
                                        if (m6011b != null) {
                                            try {
                                                m6011b.close();
                                            } catch (IOException unused3) {
                                            }
                                        }
                                    }
                                    if (Build.VERSION.SDK_INT >= 28) {
                                        executorC1492c = AbstractC0447a.m1111e(applicationContext3);
                                    } else {
                                        executorC1492c = new ExecutorC1492c(new Handler(applicationContext3.getMainLooper()), 0);
                                    }
                                    C0600b c0600b = new C0600b(5, applicationContext3);
                                    C3517b0 m6011b2 = AbstractC3524f.m6011b();
                                    if (m6011b2 != null) {
                                        if (executorC1492c == null) {
                                            c0600b.m1423d(m6011b2);
                                            return;
                                        } else {
                                            executorC1492c.execute(new RunnableC0121c1(17, c0600b, m6011b2));
                                            return;
                                        }
                                    }
                                    AbstractC3377c.f12089i.execute(new RunnableC0121c1(16, executorC1492c, c0600b));
                                    return;
                                }
                                return;
                            }
                            throw new IllegalStateException("HostHelperClient is main-thread only");
                        }
                    }
                });
                return;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: S */
    public final void mo2786S(final long j3) {
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                ((AbstractServiceC1710e0) this.f6657j).m2802e(j3);
                return;
            default:
                final HostDisplayService hostDisplayService = (HostDisplayService) this.f6657j;
                hostDisplayService.f7020l.post(new Runnable() { // from class: io.twoyi.host.o
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i = HostDisplayService.f7019x;
                        HostDisplayService.this.m3019e(j3);
                    }
                });
                return;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: X */
    public final InterfaceC1824m0 mo2787X() {
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                return C1842v0.f7331a.mo2769a((AbstractServiceC1710e0) this.f6657j);
            default:
                return C0218w0.m540h((HostDisplayService) this.f6657j).f583i;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: a */
    public final void mo2788a() {
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                ((AbstractServiceC1710e0) this.f6657j).m2805h();
                return;
            default:
                HostDisplayService hostDisplayService = (HostDisplayService) this.f6657j;
                hostDisplayService.f7020l.post(new RunnableC1796l(hostDisplayService, 1));
                return;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: g */
    public final boolean mo2789g() {
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                return false;
            default:
                if (((HostDisplayService) this.f6657j).f7025q != 3) {
                    return false;
                }
                InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
                try {
                    InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                    if (interfaceC1803o02 == null) {
                        return false;
                    }
                    if (!interfaceC1803o02.mo3098w()) {
                        return false;
                    }
                    return true;
                } catch (RemoteException unused) {
                    return false;
                }
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: h */
    public final int mo2790h(String str) {
        int i = 3;
        switch (this.f6656i) {
            default:
                AbstractC4450j.m7255e(str, "packageName");
                if (((HostDisplayService) this.f6657j).f7025q == 3) {
                    InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
                    InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                    if (interfaceC1803o02 != null) {
                        try {
                            i = interfaceC1803o02.mo3084H(str);
                        } catch (RemoteException unused) {
                        }
                        if (i == 0) {
                            C1794k.f7089d.post(new RunnableC0865d(4));
                        }
                    }
                }
            case AbstractC4695a.f17215a /* 0 */:
                return i;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: n */
    public final void mo2791n(int i, String str) {
        EnumC1588x enumC1588x;
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                try {
                    enumC1588x = EnumC1588x.valueOf(str);
                } catch (IllegalArgumentException | NullPointerException unused) {
                    enumC1588x = EnumC1588x.f6345k;
                }
                AbstractC1589y.f6351a.post(new RunnableC0690k(i, enumC1588x, 1));
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object, io.twoyi.r0] */
    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i10) {
        boolean z10;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("io.twoyi.IGuestInstance");
        }
        if (i == 1598968902) {
            parcel2.writeString("io.twoyi.IGuestInstance");
            return true;
        }
        InterfaceC1836s0 interfaceC1836s0 = null;
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        boolean z11 = false;
        switch (i) {
            case 1:
                String readString = parcel.readString();
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("io.twoyi.IGuestInstanceListener");
                    if (queryLocalInterface != null && (queryLocalInterface instanceof InterfaceC1836s0)) {
                        interfaceC1836s0 = (InterfaceC1836s0) queryLocalInterface;
                    } else {
                        ?? obj5 = new Object();
                        obj5.f7306i = readStrongBinder;
                        interfaceC1836s0 = obj5;
                    }
                }
                InterfaceC1836s0 interfaceC1836s02 = interfaceC1836s0;
                if (parcel.readInt() != 0) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                mo2785Q(readString, interfaceC1836s02, z10, parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 2:
                mo2788a();
                parcel2.writeNoException();
                return true;
            case 3:
                long readLong = parcel.readLong();
                Parcelable.Creator creator = Surface.CREATOR;
                if (parcel.readInt() != 0) {
                    obj4 = creator.createFromParcel(parcel);
                }
                mo2782J(readLong, (Surface) obj4, parcel.readInt(), parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 4:
                mo2786S(parcel.readLong());
                parcel2.writeNoException();
                return true;
            case AbstractC4540b.f16788j /* 5 */:
                Parcelable.Creator creator2 = MotionEvent.CREATOR;
                if (parcel.readInt() != 0) {
                    obj3 = creator2.createFromParcel(parcel);
                }
                mo2783K((MotionEvent) obj3);
                return true;
            case AbstractC4540b.f16786h /* 6 */:
                mo2793t(parcel.readInt());
                return true;
            case 7:
                if (parcel.readInt() != 0) {
                    z11 = true;
                }
                mo2780E(z11);
                return true;
            case 8:
                mo2791n(parcel.readInt(), parcel.readString());
                return true;
            case 9:
                boolean mo2784N = mo2784N();
                parcel2.writeNoException();
                parcel2.writeInt(mo2784N ? 1 : 0);
                return true;
            case 10:
                InterfaceC1824m0 mo2787X = mo2787X();
                parcel2.writeNoException();
                parcel2.writeStrongInterface(mo2787X);
                return true;
            case 11:
                byte[] mo2794x = mo2794x();
                parcel2.writeNoException();
                parcel2.writeByteArray(mo2794x);
                return true;
            case AbstractC1474y0.f5961f /* 12 */:
                boolean mo2789g = mo2789g();
                parcel2.writeNoException();
                parcel2.writeInt(mo2789g ? 1 : 0);
                return true;
            case 13:
                int mo2790h = mo2790h(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(mo2790h);
                return true;
            case 14:
                String readString2 = parcel.readString();
                long readLong2 = parcel.readLong();
                Parcelable.Creator creator3 = ParcelFileDescriptor.CREATOR;
                if (parcel.readInt() != 0) {
                    obj2 = creator3.createFromParcel(parcel);
                }
                mo2781F(readString2, readLong2, (ParcelFileDescriptor) obj2);
                return true;
            case AbstractC4540b.f16789k /* 15 */:
                String readString3 = parcel.readString();
                long readLong3 = parcel.readLong();
                Parcelable.Creator creator4 = ParcelFileDescriptor.CREATOR;
                if (parcel.readInt() != 0) {
                    obj = creator4.createFromParcel(parcel);
                }
                mo2792p(readString3, readLong3, (ParcelFileDescriptor) obj);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i10);
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: p */
    public final void mo2792p(String str, long j3, ParcelFileDescriptor parcelFileDescriptor) {
        try {
            switch (this.f6656i) {
                case AbstractC4695a.f17215a /* 0 */:
                    try {
                        C1741j0 c1741j0 = C1741j0.f6847c;
                        ParcelFileDescriptor dup = parcelFileDescriptor.dup();
                        c1741j0.getClass();
                        C1741j0.f6848d.execute(new RunnableC1737h0(c1741j0, dup, 7, str, j3));
                        parcelFileDescriptor.close();
                        return;
                    } finally {
                    }
                default:
                    AbstractC4450j.m7255e(parcelFileDescriptor, "result");
                    parcelFileDescriptor.close();
                    return;
            }
        } catch (IOException e10) {
            int i = AbstractServiceC1710e0.f6677y;
            Log.e("e0", "unable to run a guest operation", e10);
        }
        int i10 = AbstractServiceC1710e0.f6677y;
        Log.e("e0", "unable to run a guest operation", e10);
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: t */
    public final void mo2793t(int i) {
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                if (C1850z0.f7349d.f7350a.get()) {
                    Renderer.sendKeycode(i);
                    return;
                }
                return;
            default:
                if (((HostDisplayService) this.f6657j).f7025q == 3) {
                    InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
                    InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                    if (interfaceC1803o02 != null) {
                        C1794k.f7090e.execute(new RunnableC0690k(i, interfaceC1803o02, 3));
                        return;
                    }
                    return;
                }
                return;
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: x */
    public final byte[] mo2794x() {
        byte[] mo3083G;
        C4655i0 c4655i0;
        List list;
        switch (this.f6656i) {
            case AbstractC4695a.f17215a /* 0 */:
                return null;
            default:
                InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
                InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                if (interfaceC1803o02 == null) {
                    return null;
                }
                C1794k.f7089d.post(new RunnableC0865d(7));
                int i = 8;
                try {
                    long uptimeMillis = SystemClock.uptimeMillis() + 5000;
                    while (true) {
                        mo3083G = interfaceC1803o02.mo3083G();
                        if ((mo3083G == null || (c4655i0 = AbstractC1797l0.m3079a(mo3083G).f17058a) == null || (list = c4655i0.f17082i) == null || !(!list.isEmpty())) && !interfaceC1803o02.isEmpty() && SystemClock.uptimeMillis() < uptimeMillis) {
                            SystemClock.sleep(300L);
                        }
                    }
                    C1794k.f7089d.post(new RunnableC0865d(i));
                    return mo3083G;
                } catch (RemoteException unused) {
                    C1794k.f7089d.post(new RunnableC0865d(i));
                    return null;
                } catch (Throwable th) {
                    C1794k.f7089d.post(new RunnableC0865d(i));
                    throw th;
                }
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    /* renamed from: c0 */
    private final void m2779c0(int i, String str) {
    }
}

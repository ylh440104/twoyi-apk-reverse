package io.twoyi.host;

import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import io.twoyi.C1840u0;
import io.twoyi.InterfaceC1836s0;
import io.twoyi.RemoteCallbackListC1706c0;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p008a7.C0106x;
import p009a8.RunnableC0121c1;
import p009a8.ThreadFactoryC0110a0;
import p042d8.RunnableC0865d;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.k */
/* loaded from: classes.dex */
public final class C1794k {

    /* renamed from: a */
    public static volatile InterfaceC1803o0 f7086a;

    /* renamed from: b */
    public static volatile int f7087b;

    /* renamed from: c */
    public static volatile int f7088c;

    /* renamed from: f */
    public static C0106x f7091f;

    /* renamed from: g */
    public static C1840u0 f7092g;

    /* renamed from: h */
    public static boolean f7093h;

    /* renamed from: i */
    public static boolean f7094i;

    /* renamed from: j */
    public static Boolean f7095j;

    /* renamed from: k */
    public static long f7096k;

    /* renamed from: n */
    public static volatile Surface f7099n;

    /* renamed from: o */
    public static volatile int f7100o;

    /* renamed from: p */
    public static volatile int f7101p;

    /* renamed from: q */
    public static volatile int f7102q;

    /* renamed from: r */
    public static volatile int f7103r;

    /* renamed from: s */
    public static C1792j f7104s;

    /* renamed from: t */
    public static DisplayManager f7105t;

    /* renamed from: d */
    public static final Handler f7089d = new Handler(Looper.getMainLooper());

    /* renamed from: e */
    public static final ExecutorService f7090e = Executors.newSingleThreadExecutor(new ThreadFactoryC0110a0(4));

    /* renamed from: l */
    public static final RunnableC0865d f7097l = new RunnableC0865d(5);

    /* renamed from: m */
    public static final RunnableC0865d f7098m = new RunnableC0865d(6);

    /* renamed from: a */
    public static final void m3062a(String str) {
        Log.w("k", "lost: " + str);
        f7086a = null;
        C0106x c0106x = f7091f;
        if (c0106x != null) {
            AbstractC4450j.m7255e(str, "reason");
            HostDisplayService hostDisplayService = (HostDisplayService) c0106x.f404j;
            int i = HostDisplayService.f7019x;
            hostDisplayService.m3021g(4, str);
        }
    }

    /* renamed from: b */
    public static void m3063b() {
        InterfaceC1803o0 interfaceC1803o0 = f7086a;
        if (interfaceC1803o0 == null) {
            return;
        }
        f7090e.execute(new RunnableC0121c1(10, interfaceC1803o0, m3065d()));
    }

    /* renamed from: c */
    public static void m3064c() {
        Handler handler = f7089d;
        RunnableC0865d runnableC0865d = f7098m;
        handler.removeCallbacks(runnableC0865d);
        if (!f7093h) {
            return;
        }
        InterfaceC1803o0 interfaceC1803o0 = f7086a;
        if (interfaceC1803o0 != null) {
            f7090e.execute(new RunnableC1780d(interfaceC1803o0, 0));
        }
        handler.postDelayed(runnableC0865d, 10000L);
    }

    /* renamed from: d */
    public static Surface m3065d() {
        Surface surface;
        Surface surface2 = f7099n;
        if (surface2 == null) {
            C1840u0 c1840u0 = f7092g;
            if (c1840u0 != null && (surface = c1840u0.f7314b.getSurface()) != null && (f7093h || f7094i)) {
                return surface;
            }
            return null;
        }
        return surface2;
    }

    /* renamed from: e */
    public static void m3066e(boolean z10) {
        if (!Boolean.valueOf(z10).equals(f7095j)) {
            f7095j = Boolean.valueOf(z10);
            C0106x c0106x = f7091f;
            if (c0106x != null) {
                HostDisplayService hostDisplayService = (HostDisplayService) c0106x.f404j;
                hostDisplayService.f7029u = Boolean.valueOf(z10);
                RemoteCallbackListC1706c0 remoteCallbackListC1706c0 = hostDisplayService.f7021m;
                int beginBroadcast = remoteCallbackListC1706c0.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        IInterface broadcastItem = remoteCallbackListC1706c0.getBroadcastItem(i);
                        AbstractC4450j.m7254d(broadcastItem, "getBroadcastItem(...)");
                        ((InterfaceC1836s0) broadcastItem).mo3144D(z10);
                    } catch (RemoteException unused) {
                    } catch (Throwable th) {
                        remoteCallbackListC1706c0.finishBroadcast();
                        throw th;
                    }
                }
                remoteCallbackListC1706c0.finishBroadcast();
            }
        }
    }

    /* renamed from: f */
    public static final void m3067f(int i, DisplayManager displayManager) {
        Point point = new Point();
        Display display = displayManager.getDisplay(i);
        if (display != null) {
            display.getRealSize(point);
            int i10 = point.x;
            if (i10 > 0 && point.y > 0) {
                f7102q = i10;
                f7103r = point.y;
            }
        }
    }
}

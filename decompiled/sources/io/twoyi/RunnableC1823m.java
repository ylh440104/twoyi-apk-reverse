package io.twoyi;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import io.twoyi.hal.C1727d0;
import io.twoyi.hal.C1743k0;
import io.twoyi.utils.IOUtils;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import p024c.C0600b;
import p089h8.AbstractC1574j;
import p202r7.AbstractC3377c;
import p214s7.C3517b0;
import p214s7.C3526g;
import p278z.AbstractC4540b;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.m */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1823m implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f7291i;

    /* renamed from: j */
    public final /* synthetic */ Object f7292j;

    public /* synthetic */ RunnableC1823m(int i, Object obj) {
        this.f7291i = i;
        this.f7292j = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LocalSocket localSocket;
        IOException e10;
        LocalSocket localSocket2 = null;
        switch (this.f7291i) {
            case AbstractC4695a.f17215a /* 0 */:
                C1825n c1825n = (C1825n) this.f7292j;
                try {
                    int waitForExit = DirectLauncher.waitForExit();
                    synchronized (c1825n) {
                        try {
                            if (c1825n.f7295k) {
                                c1825n.f7294j.complete(null);
                            } else {
                                c1825n.f7294j.completeExceptionally(new IllegalStateException("direct guest init exited: waitStatus=" + waitForExit));
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return;
                } finally {
                    synchronized (c1825n) {
                        try {
                        } finally {
                        }
                    }
                }
            case 1:
                ((C1709e) this.f7292j).m2798a();
                return;
            case 2:
                AbstractC3377c abstractC3377c = (AbstractC3377c) this.f7292j;
                char[] cArr = BootLogTexture.f6594u;
                try {
                    abstractC3377c.mo5799c(500L, TimeUnit.MILLISECONDS);
                    return;
                } catch (Throwable unused) {
                    return;
                }
            case 3:
                BootLogTexture bootLogTexture = (BootLogTexture) this.f7292j;
                char[] cArr2 = BootLogTexture.f6594u;
                C3517b0 m2650e = AbstractC1574j.m2650e();
                bootLogTexture.f6603q = m2650e;
                while (bootLogTexture.f6595i.get()) {
                    try {
                        C1711f c1711f = new C1711f(bootLogTexture);
                        C3526g c3526g = new C3526g(m2650e);
                        c3526g.m6012h0("timeout -s 9 30 logcat -v brief *I");
                        c3526g.f12607b = c1711f;
                        c3526g.f12608c = C3526g.f12603g;
                        c3526g.m6013i0();
                    } catch (Throwable unused2) {
                    }
                }
                try {
                    m2650e.mo5799c(1L, TimeUnit.SECONDS);
                } catch (Throwable unused3) {
                }
                bootLogTexture.f6603q = null;
                return;
            case 4:
                ((AbstractServiceC1710e0) this.f7292j).stopSelf();
                Process.killProcess(Process.myPid());
                return;
            case AbstractC4540b.f16788j /* 5 */:
                ((Surface) this.f7292j).release();
                return;
            case AbstractC4540b.f16786h /* 6 */:
                ((C1727d0) ((C0600b) this.f7292j).f2494b).m2886c(Boolean.FALSE);
                return;
            case 7:
                ((C1743k0) this.f7292j).m2958a(false);
                return;
            default:
                C1848y0 c1848y0 = (C1848y0) this.f7292j;
                try {
                    localSocket = new LocalSocket(3);
                    try {
                        try {
                            localSocket.bind(new LocalSocketAddress(c1848y0.f7347d, LocalSocketAddress.Namespace.ABSTRACT));
                            LocalServerSocket localServerSocket = new LocalServerSocket(localSocket.getFileDescriptor());
                            c1848y0.f7345b.countDown();
                            Log.i("y0", "control socket ready");
                            Thread currentThread = Thread.currentThread();
                            while (!currentThread.isInterrupted()) {
                                c1848y0.m3163b(localServerSocket.accept());
                            }
                        } catch (IOException e11) {
                            e10 = e11;
                            Log.e("y0", "start socket failed", e10);
                            c1848y0.f7344a.set(false);
                            SystemClock.sleep(1000L);
                            c1848y0.m3164c(c1848y0.f7347d);
                            IOUtils.closeSilently(localSocket);
                            return;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        localSocket2 = localSocket;
                        IOUtils.closeSilently(localSocket2);
                        throw th;
                    }
                } catch (IOException e12) {
                    localSocket = null;
                    e10 = e12;
                } catch (Throwable th3) {
                    th = th3;
                    IOUtils.closeSilently(localSocket2);
                    throw th;
                }
                IOUtils.closeSilently(localSocket);
                return;
        }
    }
}

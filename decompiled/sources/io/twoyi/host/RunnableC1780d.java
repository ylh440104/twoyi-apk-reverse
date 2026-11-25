package io.twoyi.host;

import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.d */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1780d implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f7051i;

    /* renamed from: j */
    public final /* synthetic */ InterfaceC1803o0 f7052j;

    public /* synthetic */ RunnableC1780d(InterfaceC1803o0 interfaceC1803o0, int i) {
        this.f7051i = i;
        this.f7052j = interfaceC1803o0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final boolean z10;
        int i = this.f7051i;
        InterfaceC1803o0 interfaceC1803o0 = this.f7052j;
        switch (i) {
            case AbstractC4695a.f17215a /* 0 */:
                try {
                    interfaceC1803o0.mo3094o();
                    return;
                } catch (RemoteException e10) {
                    Log.w("k", "helper call failed", e10);
                    return;
                }
            case 1:
                C1776b.f7039a.m3026b(interfaceC1803o0);
                return;
            case 2:
                try {
                    interfaceC1803o0.mo3096s();
                    return;
                } catch (RemoteException e11) {
                    Log.w("k", "helper call failed", e11);
                    return;
                }
            case 3:
                try {
                    final boolean isEmpty = interfaceC1803o0.isEmpty();
                    if (!isEmpty) {
                        if (interfaceC1803o0.mo3091e()) {
                            z10 = true;
                            C1794k.f7089d.post(new Runnable() { // from class: io.twoyi.host.e
                                @Override // java.lang.Runnable
                                public final void run() {
                                    boolean z11 = z10;
                                    boolean z12 = isEmpty;
                                    InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                                    if (z11) {
                                        C1794k.f7096k = 0L;
                                    }
                                    if (z12) {
                                        C1794k.m3066e(true);
                                    } else if (z11) {
                                        C1794k.m3066e(false);
                                    } else if (SystemClock.uptimeMillis() >= C1794k.f7096k) {
                                        C1794k.m3066e(true);
                                    }
                                }
                            });
                            return;
                        }
                    }
                    z10 = false;
                    C1794k.f7089d.post(new Runnable() { // from class: io.twoyi.host.e
                        @Override // java.lang.Runnable
                        public final void run() {
                            boolean z11 = z10;
                            boolean z12 = isEmpty;
                            InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                            if (z11) {
                                C1794k.f7096k = 0L;
                            }
                            if (z12) {
                                C1794k.m3066e(true);
                            } else if (z11) {
                                C1794k.m3066e(false);
                            } else if (SystemClock.uptimeMillis() >= C1794k.f7096k) {
                                C1794k.m3066e(true);
                            }
                        }
                    });
                    return;
                } catch (RemoteException unused) {
                    return;
                }
            default:
                C1776b.f7039a.m3026b(interfaceC1803o0);
                try {
                    interfaceC1803o0.mo3082B();
                    return;
                } catch (RemoteException e12) {
                    Log.w("k", "helper call failed", e12);
                    return;
                }
        }
    }
}

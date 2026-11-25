package io.twoyi.hal;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.d1 */
/* loaded from: classes.dex */
public final class C1728d1 extends ConnectivityManager.NetworkCallback {

    /* renamed from: a */
    public final /* synthetic */ int f6776a;

    /* renamed from: b */
    public final /* synthetic */ Object f6777b;

    public /* synthetic */ C1728d1(int i, Object obj) {
        this.f6776a = i;
        this.f6777b = obj;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        switch (this.f6776a) {
            case AbstractC4695a.f17215a /* 0 */:
                C1731e1 c1731e1 = (C1731e1) this.f6777b;
                c1731e1.m2922z();
                C1731e1.m2894a(c1731e1, network, null);
                return;
            case 1:
                C1731e1 c1731e12 = (C1731e1) this.f6777b;
                c1731e12.f6789f.add(network);
                c1731e12.m2918v();
                return;
            default:
                super.onAvailable(network);
                return;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        switch (this.f6776a) {
            case AbstractC4695a.f17215a /* 0 */:
                C1731e1.m2894a((C1731e1) this.f6777b, network, networkCapabilities);
                return;
            case 1:
            default:
                super.onCapabilitiesChanged(network, networkCapabilities);
                return;
            case 2:
                if (!networkCapabilities.hasTransport(1) && networkCapabilities.hasTransport(0)) {
                    C1722b1 c1722b1 = (C1722b1) this.f6777b;
                    c1722b1.m2877c(c1722b1.f6737a);
                    return;
                }
                return;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        switch (this.f6776a) {
            case AbstractC4695a.f17215a /* 0 */:
                if (network.equals(((C1731e1) this.f6777b).f6787d)) {
                    C1731e1 c1731e1 = (C1731e1) this.f6777b;
                    c1731e1.m2919w(c1731e1.m2912p());
                    return;
                }
                return;
            default:
                super.onLinkPropertiesChanged(network, linkProperties);
                return;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        switch (this.f6776a) {
            case AbstractC4695a.f17215a /* 0 */:
                if (network.equals(((C1731e1) this.f6777b).f6787d)) {
                    ((C1731e1) this.f6777b).f6787d = null;
                }
                ((C1731e1) this.f6777b).m2922z();
                ((C1731e1) this.f6777b).m2901D();
                C1731e1 c1731e1 = (C1731e1) this.f6777b;
                c1731e1.m2919w(c1731e1.m2912p());
                return;
            case 1:
                C1731e1 c1731e12 = (C1731e1) this.f6777b;
                c1731e12.f6789f.remove(network);
                c1731e12.m2918v();
                return;
            default:
                C1722b1 c1722b1 = C1722b1.f6736h;
                Log.d("b1", "ConnectivityManager onLost ");
                C1722b1 c1722b12 = (C1722b1) this.f6777b;
                c1722b12.f6741e = -1;
                if (!C1722b1.m2875b(c1722b12.f6737a)) {
                    c1722b12.f6740d.sendEmptyMessageDelayed(5, 200L);
                    return;
                }
                return;
        }
    }
}

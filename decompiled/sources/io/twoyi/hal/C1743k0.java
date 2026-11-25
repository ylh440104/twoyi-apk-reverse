package io.twoyi.hal;

import android.location.Location;
import android.util.Log;
import io.twoyi.AbstractServiceC1716h0;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import p247w0.AbstractC4176k;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.k0 */
/* loaded from: classes.dex */
public final /* synthetic */ class C1743k0 {

    /* renamed from: a */
    public final /* synthetic */ C1757r0 f6852a;

    /* renamed from: b */
    public final /* synthetic */ OutputStream f6853b;

    /* renamed from: c */
    public final /* synthetic */ long f6854c;

    public /* synthetic */ C1743k0(C1757r0 c1757r0, OutputStream outputStream, long j3) {
        this.f6852a = c1757r0;
        this.f6853b = outputStream;
        this.f6854c = j3;
    }

    /* renamed from: a */
    public final void m2958a(boolean z10) {
        C1757r0 c1757r0 = this.f6852a;
        if (c1757r0.f6896h) {
            OutputStream outputStream = this.f6853b;
            long j3 = this.f6854c;
            if (c1757r0.m2972m(outputStream, j3)) {
                c1757r0.f6896h = false;
                if (!z10) {
                    Log.e("r0", "location foreground-service type unavailable");
                    return;
                }
                if (c1757r0.m2972m(outputStream, j3) && !c1757r0.f6895g) {
                    try {
                        List<String> providers = c1757r0.f6890b.getProviders(true);
                        String m2961b = C1757r0.m2961b(providers);
                        if (m2961b == null) {
                            Log.w("r0", "no enabled location provider");
                            AbstractServiceC1716h0.m2864c(c1757r0.f6889a, false, null, null);
                            return;
                        }
                        Location m2968h = c1757r0.m2968h(providers);
                        C1755q0 c1755q0 = new C1755q0(c1757r0, outputStream, j3);
                        c1757r0.f6890b.requestLocationUpdates(m2961b, 1000L, AbstractC4176k.f15279f, c1755q0, c1757r0.f6904p.getLooper());
                        if (!c1757r0.m2972m(outputStream, j3)) {
                            c1757r0.f6890b.removeUpdates(c1755q0);
                            AbstractServiceC1716h0.m2864c(c1757r0.f6889a, false, null, null);
                            return;
                        }
                        c1757r0.f6891c = c1755q0;
                        c1757r0.f6897i = m2961b;
                        c1757r0.f6898j = outputStream;
                        c1757r0.f6900l = j3;
                        c1757r0.f6895g = true;
                        if ("gps".equals(m2961b)) {
                            c1757r0.m2974o(outputStream, j3);
                        } else {
                            c1757r0.m2977r(Collections.EMPTY_LIST, outputStream, j3);
                        }
                        if (m2968h != null) {
                            c1757r0.m2975p(m2968h, outputStream, j3);
                            if (c1757r0.m2971l(outputStream, j3)) {
                                Log.d("r0", "seed location from " + m2968h.getProvider());
                            } else {
                                return;
                            }
                        }
                        Log.d("r0", "location updates started: ".concat(m2961b));
                        return;
                    } catch (SecurityException e10) {
                        Log.e("r0", "location permission revoked while starting updates", e10);
                        AbstractServiceC1716h0.m2864c(c1757r0.f6889a, false, null, null);
                        c1757r0.m2967g(outputStream, j3, false);
                        return;
                    } catch (RuntimeException e11) {
                        Log.e("r0", "cannot start location updates", e11);
                        AbstractServiceC1716h0.m2864c(c1757r0.f6889a, false, null, null);
                        c1757r0.m2967g(outputStream, j3, false);
                        return;
                    }
                }
                AbstractServiceC1716h0.m2864c(c1757r0.f6889a, false, null, null);
            }
        }
    }
}

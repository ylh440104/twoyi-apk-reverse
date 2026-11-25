package io.twoyi.hal;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import java.io.OutputStream;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.q0 */
/* loaded from: classes.dex */
public final class C1755q0 implements LocationListener {

    /* renamed from: a */
    public final OutputStream f6884a;

    /* renamed from: b */
    public final long f6885b;

    /* renamed from: c */
    public final /* synthetic */ C1757r0 f6886c;

    public C1755q0(C1757r0 c1757r0, OutputStream outputStream, long j3) {
        this.f6886c = c1757r0;
        this.f6884a = outputStream;
        this.f6885b = j3;
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location location) {
        this.f6886c.m2975p(location, this.f6884a, this.f6885b);
    }

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
        OutputStream outputStream = this.f6884a;
        long j3 = this.f6885b;
        C1757r0 c1757r0 = this.f6886c;
        if (c1757r0.m2971l(outputStream, j3) && str.equals(c1757r0.f6897i)) {
            C1757r0 c1757r02 = C1757r0.f6888t;
            Log.d("r0", "onProviderDisabled: ".concat(str));
            c1757r0.m2970k();
            c1757r0.m2963c();
            c1757r0.m2973n();
        }
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
        C1757r0 c1757r0 = this.f6886c;
        OutputStream outputStream = this.f6884a;
        long j3 = this.f6885b;
        if (c1757r0.m2971l(outputStream, j3)) {
            C1757r0 c1757r02 = C1757r0.f6888t;
            Log.d("r0", "onProviderEnabled: " + str);
            try {
                c1757r0.m2975p(c1757r0.f6890b.getLastKnownLocation(str), outputStream, j3);
            } catch (SecurityException e10) {
                C1757r0 c1757r03 = C1757r0.f6888t;
                Log.w("r0", "cannot read last location for enabled provider", e10);
                c1757r0.m2967g(outputStream, j3, false);
            } catch (RuntimeException e11) {
                C1757r0 c1757r04 = C1757r0.f6888t;
                Log.w("r0", "reading last location for enabled provider failed", e11);
            }
        }
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}

package io.twoyi.hal;

import android.location.GnssStatus;
import android.os.Build;
import android.util.Log;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import p247w0.AbstractC4176k;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.p0 */
/* loaded from: classes.dex */
public final class C1753p0 extends GnssStatus.Callback {

    /* renamed from: a */
    public final /* synthetic */ OutputStream f6872a;

    /* renamed from: b */
    public final /* synthetic */ long f6873b;

    /* renamed from: c */
    public final /* synthetic */ C1757r0 f6874c;

    public C1753p0(C1757r0 c1757r0, OutputStream outputStream, long j3) {
        this.f6874c = c1757r0;
        this.f6872a = outputStream;
        this.f6873b = j3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r16v0, types: [int] */
    @Override // android.location.GnssStatus.Callback
    public final void onSatelliteStatusChanged(GnssStatus gnssStatus) {
        float f4;
        boolean z10;
        boolean hasBasebandCn0DbHz;
        C1757r0 c1757r0 = this.f6874c;
        OutputStream outputStream = this.f6872a;
        long j3 = this.f6873b;
        if (c1757r0.m2971l(outputStream, j3) && gnssStatus != null) {
            int satelliteCount = gnssStatus.getSatelliteCount();
            int min = Math.min(satelliteCount, 256);
            if (satelliteCount > min) {
                Log.w("r0", "truncate GNSS snapshot from " + satelliteCount + " to " + min);
            }
            ArrayList arrayList = new ArrayList(min);
            int i = 0;
            for (int i10 = 0; i10 < min; i10++) {
                boolean hasEphemerisData = gnssStatus.hasEphemerisData(i10);
                boolean z11 = hasEphemerisData;
                if (gnssStatus.hasAlmanacData(i10)) {
                    z11 = (hasEphemerisData ? 1 : 0) | 2;
                }
                boolean z12 = z11;
                if (gnssStatus.usedInFix(i10)) {
                    z12 = (z11 ? 1 : 0) | 4;
                }
                boolean hasCarrierFrequencyHz = gnssStatus.hasCarrierFrequencyHz(i10);
                float f10 = AbstractC4176k.f15279f;
                if (hasCarrierFrequencyHz) {
                    ?? r02 = (z12 ? 1 : 0) | '\b';
                    f4 = gnssStatus.getCarrierFrequencyHz(i10);
                    z10 = r02;
                } else {
                    f4 = 0.0f;
                    z10 = z12;
                }
                boolean z13 = z10;
                if (Build.VERSION.SDK_INT >= 30) {
                    hasBasebandCn0DbHz = gnssStatus.hasBasebandCn0DbHz(i10);
                    z13 = z10;
                    if (hasBasebandCn0DbHz) {
                        ?? r03 = (z10 ? 1 : 0) | 16;
                        f10 = gnssStatus.getBasebandCn0DbHz(i10);
                        z13 = r03;
                    }
                }
                ?? r16 = z13;
                try {
                    arrayList.add(new C1740j(gnssStatus.getConstellationType(i10), r16, gnssStatus.getSvid(i10), gnssStatus.getCn0DbHz(i10), gnssStatus.getElevationDegrees(i10), gnssStatus.getAzimuthDegrees(i10), f4, f10));
                    if ((r16 & 4) != 0) {
                        i++;
                    }
                } catch (IllegalArgumentException e10) {
                    Log.w("r0", "discard invalid host satellite at index " + i10, e10);
                }
            }
            c1757r0.f6893e = i;
            c1757r0.m2977r(arrayList, outputStream, j3);
        }
    }

    @Override // android.location.GnssStatus.Callback
    public final void onStopped() {
        if (this.f6874c.m2971l(this.f6872a, this.f6873b)) {
            this.f6874c.f6893e = 0;
            this.f6874c.m2977r(Collections.EMPTY_LIST, this.f6872a, this.f6873b);
        }
    }
}

package io.twoyi.host;

import android.hardware.display.DisplayManager;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.j */
/* loaded from: classes.dex */
public final class C1792j implements DisplayManager.DisplayListener {

    /* renamed from: a */
    public final /* synthetic */ int f7083a;

    /* renamed from: b */
    public final /* synthetic */ DisplayManager f7084b;

    public C1792j(int i, DisplayManager displayManager) {
        this.f7083a = i;
        this.f7084b = displayManager;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayChanged(int i) {
        int i10 = this.f7083a;
        if (i == i10) {
            C1794k.m3067f(i10, this.f7084b);
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayAdded(int i) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayRemoved(int i) {
    }
}

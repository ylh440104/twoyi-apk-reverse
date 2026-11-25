package io.twoyi;

import android.os.ParcelFileDescriptor;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.i0 */
/* loaded from: classes.dex */
public final /* synthetic */ class C1816i0 {

    /* renamed from: a */
    public final /* synthetic */ int f7222a;

    /* renamed from: b */
    public final /* synthetic */ C1820k0 f7223b;

    /* renamed from: c */
    public final /* synthetic */ String f7224c;

    public /* synthetic */ C1816i0(C1820k0 c1820k0, String str, int i) {
        this.f7222a = i;
        this.f7223b = c1820k0;
        this.f7224c = str;
    }

    /* renamed from: a */
    public final void m3125a(InterfaceC1832q0 interfaceC1832q0, ParcelFileDescriptor parcelFileDescriptor) {
        switch (this.f7222a) {
            case AbstractC4695a.f17215a /* 0 */:
                interfaceC1832q0.mo2781F(this.f7224c, this.f7223b.f7282k, parcelFileDescriptor);
                return;
            default:
                interfaceC1832q0.mo2792p(this.f7224c, this.f7223b.f7282k, parcelFileDescriptor);
                return;
        }
    }
}

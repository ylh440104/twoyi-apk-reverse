package io.twoyi;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.n0 */
/* loaded from: classes.dex */
public final class C1826n0 implements InterfaceC1828o0 {

    /* renamed from: i */
    public IBinder f7297i;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f7297i;
    }

    @Override // io.twoyi.InterfaceC1828o0
    /* renamed from: l */
    public final void mo428l(String str) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgentListener");
            obtain.writeString(str);
            this.f7297i.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}

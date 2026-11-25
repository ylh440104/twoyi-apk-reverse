package io.twoyi;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.l0 */
/* loaded from: classes.dex */
public final class C1822l0 implements InterfaceC1824m0 {

    /* renamed from: i */
    public IBinder f7290i;

    @Override // io.twoyi.InterfaceC1824m0
    /* renamed from: I */
    public final String mo439I(InterfaceC1828o0 interfaceC1828o0) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            obtain.writeStrongInterface(interfaceC1828o0);
            this.f7290i.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1824m0
    /* renamed from: a */
    public final void mo440a() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            this.f7290i.transact(5, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f7290i;
    }

    @Override // io.twoyi.InterfaceC1824m0
    /* renamed from: b */
    public final void mo441b(String str, boolean z10) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            obtain.writeInt(z10 ? 1 : 0);
            obtain.writeString(str);
            this.f7290i.transact(7, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1824m0
    /* renamed from: c */
    public final void mo442c() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            this.f7290i.transact(3, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1824m0
    public final void cancel() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            this.f7290i.transact(4, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1824m0
    /* renamed from: d */
    public final void mo443d() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            this.f7290i.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1824m0
    /* renamed from: f */
    public final void mo444f(String str) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            obtain.writeString(str);
            this.f7290i.transact(6, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1824m0
    /* renamed from: j */
    public final void mo445j(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            obtain.writeString(str);
            obtain.writeString(str2);
            this.f7290i.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1824m0
    /* renamed from: y */
    public final void mo446y(InterfaceC1828o0 interfaceC1828o0) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestAgent");
            obtain.writeStrongInterface(interfaceC1828o0);
            this.f7290i.transact(9, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}

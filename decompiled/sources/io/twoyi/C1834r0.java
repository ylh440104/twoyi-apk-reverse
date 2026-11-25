package io.twoyi;

import android.os.IBinder;
import android.os.Parcel;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.r0 */
/* loaded from: classes.dex */
public final class C1834r0 implements InterfaceC1836s0 {

    /* renamed from: i */
    public IBinder f7306i;

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: D */
    public final void mo3144D(boolean z10) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstanceListener");
            obtain.writeInt(z10 ? 1 : 0);
            this.f7306i.transact(8, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: M */
    public final void mo3145M() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstanceListener");
            this.f7306i.transact(4, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: U */
    public final void mo3146U(String str) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstanceListener");
            obtain.writeString(str);
            this.f7306i.transact(3, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: W */
    public final void mo3147W(float f4) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstanceListener");
            obtain.writeFloat(f4);
            this.f7306i.transact(5, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f7306i;
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: i */
    public final void mo3148i() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstanceListener");
            this.f7306i.transact(2, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: r */
    public final void mo3149r(int i, int i10) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstanceListener");
            obtain.writeInt(i);
            obtain.writeInt(i10);
            this.f7306i.transact(6, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: u */
    public final void mo3150u() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstanceListener");
            this.f7306i.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: z */
    public final void mo3151z(int i, String[] strArr) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstanceListener");
            obtain.writeInt(i);
            obtain.writeStringArray(strArr);
            this.f7306i.transact(7, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}

package io.twoyi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.view.MotionEvent;
import android.view.Surface;
import na.AbstractC2697l;
import p009a8.BinderC0151i1;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.p0 */
/* loaded from: classes.dex */
public final class C1830p0 implements InterfaceC1832q0 {

    /* renamed from: i */
    public IBinder f7299i;

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: E */
    public final void mo2780E(boolean z10) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeInt(z10 ? 1 : 0);
            this.f7299i.transact(7, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: F */
    public final void mo2781F(String str, long j3, ParcelFileDescriptor parcelFileDescriptor) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeString(str);
            obtain.writeLong(j3);
            AbstractC2697l.m4588a(obtain, parcelFileDescriptor);
            this.f7299i.transact(14, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: J */
    public final void mo2782J(long j3, Surface surface, int i, int i10, float f4, float f10, int i11) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeLong(j3);
            AbstractC2697l.m4588a(obtain, surface);
            obtain.writeInt(i);
            obtain.writeInt(i10);
            obtain.writeFloat(f4);
            obtain.writeFloat(f10);
            obtain.writeInt(i11);
            this.f7299i.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: K */
    public final void mo2783K(MotionEvent motionEvent) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            AbstractC2697l.m4588a(obtain, motionEvent);
            this.f7299i.transact(5, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: Q */
    public final void mo2785Q(String str, InterfaceC1836s0 interfaceC1836s0, boolean z10, int i, int i10) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeString(str);
            obtain.writeStrongInterface(interfaceC1836s0);
            obtain.writeInt(z10 ? 1 : 0);
            obtain.writeInt(i);
            obtain.writeInt(i10);
            this.f7299i.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: S */
    public final void mo2786S(long j3) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeLong(j3);
            this.f7299i.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, io.twoyi.l0] */
    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: X */
    public final InterfaceC1824m0 mo2787X() {
        InterfaceC1824m0 interfaceC1824m0;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            this.f7299i.transact(10, obtain, obtain2, 0);
            obtain2.readException();
            IBinder readStrongBinder = obtain2.readStrongBinder();
            int i = BinderC0151i1.f548j;
            if (readStrongBinder == null) {
                interfaceC1824m0 = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("io.twoyi.IGuestAgent");
                if (queryLocalInterface != null && (queryLocalInterface instanceof InterfaceC1824m0)) {
                    interfaceC1824m0 = (InterfaceC1824m0) queryLocalInterface;
                } else {
                    ?? obj = new Object();
                    obj.f7290i = readStrongBinder;
                    interfaceC1824m0 = obj;
                }
            }
            return interfaceC1824m0;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: a */
    public final void mo2788a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            this.f7299i.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f7299i;
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: g */
    public final boolean mo2789g() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            boolean z10 = false;
            this.f7299i.transact(12, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z10 = true;
            }
            return z10;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: h */
    public final int mo2790h(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeString(str);
            this.f7299i.transact(13, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: n */
    public final void mo2791n(int i, String str) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeInt(i);
            obtain.writeString(str);
            this.f7299i.transact(8, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: p */
    public final void mo2792p(String str, long j3, ParcelFileDescriptor parcelFileDescriptor) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeString(str);
            obtain.writeLong(j3);
            AbstractC2697l.m4588a(obtain, parcelFileDescriptor);
            this.f7299i.transact(15, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: t */
    public final void mo2793t(int i) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            obtain.writeInt(4);
            this.f7299i.transact(6, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }

    @Override // io.twoyi.InterfaceC1832q0
    /* renamed from: x */
    public final byte[] mo2794x() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.IGuestInstance");
            this.f7299i.transact(11, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createByteArray();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}

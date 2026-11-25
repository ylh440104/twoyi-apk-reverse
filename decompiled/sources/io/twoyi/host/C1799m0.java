package io.twoyi.host;

import android.os.IBinder;
import android.os.Parcel;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import na.AbstractC2689d;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.m0 */
/* loaded from: classes.dex */
public final class C1799m0 implements InterfaceC1803o0 {

    /* renamed from: i */
    public IBinder f7123i;

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: A */
    public final int mo3081A(int i, int i10, int i11, Surface surface) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            obtain.writeInt(i);
            obtain.writeInt(i10);
            obtain.writeInt(i11);
            AbstractC2689d.m4536a(obtain, surface);
            this.f7123i.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: B */
    public final void mo3082B() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            this.f7123i.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: G */
    public final byte[] mo3083G() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            this.f7123i.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.createByteArray();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: H */
    public final int mo3084H(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            obtain.writeString(str);
            this.f7123i.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: L */
    public final void mo3085L() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            this.f7123i.transact(20, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: O */
    public final void mo3086O(String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            obtain.writeString(str);
            obtain.writeInt(i);
            this.f7123i.transact(17, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: R */
    public final boolean mo3087R(KeyEvent keyEvent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            AbstractC2689d.m4536a(obtain, keyEvent);
            boolean z10 = false;
            this.f7123i.transact(6, obtain, obtain2, 0);
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

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: T */
    public final void mo3088T(Surface surface) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            AbstractC2689d.m4536a(obtain, surface);
            this.f7123i.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: V */
    public final boolean mo3089V(MotionEvent motionEvent) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            AbstractC2689d.m4536a(obtain, motionEvent);
            boolean z10 = false;
            this.f7123i.transact(5, obtain, obtain2, 0);
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

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: a0 */
    public final int mo3090a0(int i, int i10) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            obtain.writeInt(i);
            obtain.writeInt(3000);
            this.f7123i.transact(14, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f7123i;
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: e */
    public final boolean mo3091e() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            boolean z10 = false;
            this.f7123i.transact(18, obtain, obtain2, 0);
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

    @Override // io.twoyi.host.InterfaceC1803o0
    public final boolean isEmpty() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            boolean z10 = false;
            this.f7123i.transact(11, obtain, obtain2, 0);
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

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: k */
    public final String mo3092k() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            this.f7123i.transact(13, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: m */
    public final int mo3093m(String str, boolean z10) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            obtain.writeString(str);
            obtain.writeInt(z10 ? 1 : 0);
            this.f7123i.transact(16, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: o */
    public final void mo3094o() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            this.f7123i.transact(12, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: q */
    public final boolean mo3095q(String str, boolean z10) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            obtain.writeString(str);
            obtain.writeInt(z10 ? 1 : 0);
            boolean z11 = false;
            this.f7123i.transact(9, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z11 = true;
            }
            return z11;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: s */
    public final void mo3096s() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            this.f7123i.transact(15, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: v */
    public final boolean mo3097v() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            boolean z10 = false;
            this.f7123i.transact(10, obtain, obtain2, 0);
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

    @Override // io.twoyi.host.InterfaceC1803o0
    /* renamed from: w */
    public final boolean mo3098w() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("io.twoyi.host.IHostHelper");
            boolean z10 = false;
            this.f7123i.transact(19, obtain, obtain2, 0);
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
}

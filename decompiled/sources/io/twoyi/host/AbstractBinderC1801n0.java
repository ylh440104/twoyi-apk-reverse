package io.twoyi.host;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.n0 */
/* loaded from: classes.dex */
public abstract class AbstractBinderC1801n0 extends Binder implements InterfaceC1803o0 {

    /* renamed from: A */
    static final int f7129A = 18;

    /* renamed from: B */
    static final int f7130B = 16777115;

    /* renamed from: i */
    static final int f7131i = 2;

    /* renamed from: j */
    static final int f7132j = 3;

    /* renamed from: k */
    static final int f7133k = 4;

    /* renamed from: l */
    static final int f7134l = 5;

    /* renamed from: m */
    static final int f7135m = 6;

    /* renamed from: n */
    static final int f7136n = 7;

    /* renamed from: o */
    static final int f7137o = 8;

    /* renamed from: p */
    static final int f7138p = 9;

    /* renamed from: q */
    static final int f7139q = 10;

    /* renamed from: r */
    static final int f7140r = 11;

    /* renamed from: s */
    static final int f7141s = 12;

    /* renamed from: t */
    static final int f7142t = 13;

    /* renamed from: u */
    static final int f7143u = 14;

    /* renamed from: v */
    static final int f7144v = 15;

    /* renamed from: w */
    static final int f7145w = 16;

    /* renamed from: x */
    static final int f7146x = 17;

    /* renamed from: y */
    static final int f7147y = 19;

    /* renamed from: z */
    static final int f7148z = 20;

    /* JADX WARN: Type inference failed for: r0v2, types: [io.twoyi.host.m0, java.lang.Object, io.twoyi.host.o0] */
    /* renamed from: b0 */
    public static InterfaceC1803o0 m3099b0(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("io.twoyi.host.IHostHelper");
        if (queryLocalInterface != null && (queryLocalInterface instanceof InterfaceC1803o0)) {
            return (InterfaceC1803o0) queryLocalInterface;
        }
        ?? obj = new Object();
        obj.f7123i = iBinder;
        return obj;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i10) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("io.twoyi.host.IHostHelper");
        }
        if (i == 1598968902) {
            parcel2.writeString("io.twoyi.host.IHostHelper");
            return true;
        }
        if (i != f7130B) {
            Object obj = null;
            boolean z10 = false;
            switch (i) {
                case f7131i /* 2 */:
                    int readInt = parcel.readInt();
                    int readInt2 = parcel.readInt();
                    int readInt3 = parcel.readInt();
                    Parcelable.Creator creator = Surface.CREATOR;
                    if (parcel.readInt() != 0) {
                        obj = creator.createFromParcel(parcel);
                    }
                    int mo3081A = mo3081A(readInt, readInt2, readInt3, (Surface) obj);
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3081A);
                    return true;
                case f7132j /* 3 */:
                    Parcelable.Creator creator2 = Surface.CREATOR;
                    if (parcel.readInt() != 0) {
                        obj = creator2.createFromParcel(parcel);
                    }
                    mo3088T((Surface) obj);
                    parcel2.writeNoException();
                    return true;
                case f7133k /* 4 */:
                    mo3082B();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    Parcelable.Creator creator3 = MotionEvent.CREATOR;
                    if (parcel.readInt() != 0) {
                        obj = creator3.createFromParcel(parcel);
                    }
                    boolean mo3089V = mo3089V((MotionEvent) obj);
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3089V ? 1 : 0);
                    return true;
                case 6:
                    Parcelable.Creator creator4 = KeyEvent.CREATOR;
                    if (parcel.readInt() != 0) {
                        obj = creator4.createFromParcel(parcel);
                    }
                    boolean mo3087R = mo3087R((KeyEvent) obj);
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3087R ? 1 : 0);
                    return true;
                case f7136n /* 7 */:
                    int mo3084H = mo3084H(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3084H);
                    return true;
                case 8:
                    byte[] mo3083G = mo3083G();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(mo3083G);
                    return true;
                case 9:
                    String readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z10 = true;
                    }
                    boolean mo3095q = mo3095q(readString, z10);
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3095q ? 1 : 0);
                    return true;
                case 10:
                    boolean mo3097v = mo3097v();
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3097v ? 1 : 0);
                    return true;
                case f7140r /* 11 */:
                    boolean isEmpty = isEmpty();
                    parcel2.writeNoException();
                    parcel2.writeInt(isEmpty ? 1 : 0);
                    return true;
                case 12:
                    mo3094o();
                    parcel2.writeNoException();
                    return true;
                case f7142t /* 13 */:
                    String mo3092k = mo3092k();
                    parcel2.writeNoException();
                    parcel2.writeString(mo3092k);
                    return true;
                case f7143u /* 14 */:
                    int mo3090a0 = mo3090a0(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3090a0);
                    return true;
                case 15:
                    mo3096s();
                    parcel2.writeNoException();
                    return true;
                case f7145w /* 16 */:
                    String readString2 = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z10 = true;
                    }
                    int mo3093m = mo3093m(readString2, z10);
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3093m);
                    return true;
                case f7146x /* 17 */:
                    mo3086O(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case f7129A /* 18 */:
                    boolean mo3091e = mo3091e();
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3091e ? 1 : 0);
                    return true;
                case f7147y /* 19 */:
                    boolean mo3098w = mo3098w();
                    parcel2.writeNoException();
                    parcel2.writeInt(mo3098w ? 1 : 0);
                    return true;
                case f7148z /* 20 */:
                    mo3085L();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i10);
            }
        }
        destroy();
        parcel2.writeNoException();
        return true;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }
}

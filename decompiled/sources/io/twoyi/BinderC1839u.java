package io.twoyi;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import java.util.Iterator;
import lb.RunnableC2290b;
import p009a8.RunnableC0121c1;
import p014b.RunnableC0346k;
import p067f8.EnumC1179g0;
import p265x8.C4448h;
import p278z.AbstractC4540b;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.u */
/* loaded from: classes.dex */
public final class BinderC1839u extends Binder implements InterfaceC1836s0 {

    /* renamed from: i */
    public final /* synthetic */ GuestInstanceClient f7312i;

    public BinderC1839u(GuestInstanceClient guestInstanceClient) {
        this.f7312i = guestInstanceClient;
        attachInterface(this, "io.twoyi.IGuestInstanceListener");
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: D */
    public final void mo3144D(final boolean z10) {
        this.f7312i.f6614c.post(new Runnable() { // from class: io.twoyi.s
            /* JADX WARN: Type inference failed for: r1v5, types: [w8.c, x8.h] */
            @Override // java.lang.Runnable
            public final void run() {
                EnumC1179g0 enumC1179g0;
                Iterator it = BinderC1839u.this.f7312i.f6615d.iterator();
                while (it.hasNext()) {
                    ?? r12 = (C4448h) ((C1844w0) it.next()).f7334a.f460i;
                    if (z10) {
                        enumC1179g0 = EnumC1179g0.f4799m;
                    } else {
                        enumC1179g0 = EnumC1179g0.f4800n;
                    }
                    r12.mo169i(enumC1179g0);
                }
            }
        });
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: M */
    public final void mo3145M() {
        this.f7312i.f6614c.post(new RunnableC1833r(this, 1));
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: U */
    public final void mo3146U(String str) {
        this.f7312i.f6614c.post(new RunnableC0121c1(7, this, str));
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: W */
    public final void mo3147W(float f4) {
        this.f7312i.f6614c.post(new RunnableC1833r(this, f4));
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: i */
    public final void mo3148i() {
        this.f7312i.f6614c.post(new RunnableC1833r(this, 2));
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i10) {
        boolean z10;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("io.twoyi.IGuestInstanceListener");
        }
        if (i == 1598968902) {
            parcel2.writeString("io.twoyi.IGuestInstanceListener");
            return true;
        }
        switch (i) {
            case 1:
                mo3150u();
                return true;
            case 2:
                mo3148i();
                return true;
            case 3:
                mo3146U(parcel.readString());
                return true;
            case 4:
                mo3145M();
                return true;
            case AbstractC4540b.f16788j /* 5 */:
                mo3147W(parcel.readFloat());
                return true;
            case AbstractC4540b.f16786h /* 6 */:
                mo3149r(parcel.readInt(), parcel.readInt());
                return true;
            case 7:
                mo3151z(parcel.readInt(), parcel.createStringArray());
                return true;
            case 8:
                if (parcel.readInt() != 0) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                mo3144D(z10);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i10);
        }
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: r */
    public final void mo3149r(int i, int i10) {
        this.f7312i.f6614c.post(new RunnableC2290b(this, i, i10, 2));
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: u */
    public final void mo3150u() {
        this.f7312i.f6614c.post(new RunnableC1833r(this, 3));
    }

    @Override // io.twoyi.InterfaceC1836s0
    /* renamed from: z */
    public final void mo3151z(int i, String[] strArr) {
        this.f7312i.f6614c.post(new RunnableC0346k(i, 3, this, strArr));
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}

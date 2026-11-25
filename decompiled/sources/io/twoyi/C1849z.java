package io.twoyi;

import android.os.RemoteException;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.z */
/* loaded from: classes.dex */
public final /* synthetic */ class C1849z {

    /* renamed from: a */
    public final /* synthetic */ AbstractServiceC1710e0 f7348a;

    /* renamed from: a */
    public boolean m3165a(int i, String[] strArr) {
        int i10;
        AbstractServiceC1710e0 abstractServiceC1710e0 = this.f7348a;
        synchronized (abstractServiceC1710e0.f6680n) {
            try {
                int beginBroadcast = abstractServiceC1710e0.f6679m.beginBroadcast();
                i10 = 0;
                for (int i11 = 0; i11 < beginBroadcast; i11++) {
                    try {
                        ((InterfaceC1836s0) abstractServiceC1710e0.f6679m.getBroadcastItem(i11)).mo3151z(i, strArr);
                        i10++;
                    } catch (RemoteException unused) {
                    } catch (Throwable th) {
                        abstractServiceC1710e0.f6679m.finishBroadcast();
                        throw th;
                    }
                }
                abstractServiceC1710e0.f6679m.finishBroadcast();
            } catch (Throwable th2) {
                throw th2;
            }
        }
        if (i10 <= 0) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public void m3166b(float f4) {
        AbstractServiceC1710e0 abstractServiceC1710e0 = this.f7348a;
        synchronized (abstractServiceC1710e0.f6680n) {
            try {
                abstractServiceC1710e0.f6685s = f4;
                int beginBroadcast = abstractServiceC1710e0.f6679m.beginBroadcast();
                for (int i = 0; i < beginBroadcast; i++) {
                    try {
                        ((InterfaceC1836s0) abstractServiceC1710e0.f6679m.getBroadcastItem(i)).mo3147W(f4);
                    } catch (RemoteException unused) {
                    } catch (Throwable th) {
                        abstractServiceC1710e0.f6679m.finishBroadcast();
                        throw th;
                    }
                }
                abstractServiceC1710e0.f6679m.finishBroadcast();
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}

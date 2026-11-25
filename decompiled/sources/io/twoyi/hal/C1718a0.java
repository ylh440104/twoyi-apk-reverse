package io.twoyi.hal;

import android.content.Context;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.a0 */
/* loaded from: classes.dex */
public final class C1718a0 {

    /* renamed from: c */
    public static final C1718a0 f6723c = new Object();

    /* renamed from: d */
    public static final ReentrantLock f6724d = new ReentrantLock();

    /* renamed from: a */
    public Context f6725a;

    /* renamed from: b */
    public LocalSocket f6726b;

    /* renamed from: a */
    public final LocalSocket m2868a() {
        LocalSocket localSocket = this.f6726b;
        if (localSocket != null) {
            return localSocket;
        }
        try {
            this.f6726b = new LocalSocket(2);
            this.f6726b.connect(new LocalSocketAddress("twoyi_bt_server", LocalSocketAddress.Namespace.ABSTRACT));
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                this.f6726b.close();
                this.f6726b = null;
            } catch (IOException e10) {
                e10.printStackTrace();
            }
        }
        return this.f6726b;
    }
}

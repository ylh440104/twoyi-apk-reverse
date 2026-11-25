package io.twoyi.hal;

import android.os.SystemClock;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.d0 */
/* loaded from: classes.dex */
public final class C1727d0 {

    /* renamed from: a */
    public boolean f6773a;

    /* renamed from: b */
    public boolean f6774b;

    /* renamed from: c */
    public Object f6775c;

    /* renamed from: a */
    public final synchronized Object m2884a(long j3) {
        Object obj;
        while (!this.f6773a && !this.f6774b) {
            long elapsedRealtime = j3 - SystemClock.elapsedRealtime();
            if (elapsedRealtime <= 0) {
                break;
            }
            try {
                wait(elapsedRealtime);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                this.f6774b = true;
            }
        }
        if (this.f6773a && !this.f6774b) {
            obj = this.f6775c;
        } else {
            obj = null;
        }
        return obj;
    }

    /* renamed from: b */
    public final synchronized void m2885b() {
        if (!this.f6773a) {
            this.f6774b = true;
            notifyAll();
        }
    }

    /* renamed from: c */
    public final synchronized void m2886c(Object obj) {
        if (!this.f6773a && !this.f6774b) {
            this.f6773a = true;
            this.f6775c = obj;
            notifyAll();
        }
    }
}

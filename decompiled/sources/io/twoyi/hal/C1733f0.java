package io.twoyi.hal;

import android.net.LocalSocket;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.f0 */
/* loaded from: classes.dex */
public final class C1733f0 {

    /* renamed from: a */
    public final LocalSocket f6805a;

    /* renamed from: b */
    public volatile boolean f6806b;

    /* renamed from: c */
    public volatile boolean f6807c;

    /* renamed from: d */
    public volatile boolean f6808d;

    /* renamed from: e */
    public volatile boolean f6809e;

    /* renamed from: f */
    public volatile C1764v f6810f;

    /* renamed from: g */
    public volatile C1723c f6811g;

    /* renamed from: h */
    public volatile long f6812h = -1;

    /* renamed from: i */
    public C1727d0 f6813i;

    /* renamed from: j */
    public boolean f6814j;

    public C1733f0(LocalSocket localSocket) {
        this.f6805a = localSocket;
    }

    /* renamed from: a */
    public final synchronized void m2923a(C1727d0 c1727d0) {
        if (this.f6813i == c1727d0) {
            this.f6813i = null;
        }
    }

    /* renamed from: b */
    public final synchronized boolean m2924b(C1727d0 c1727d0) {
        if (!this.f6807c && !this.f6808d && !this.f6809e) {
            this.f6813i = c1727d0;
            return true;
        }
        return false;
    }
}

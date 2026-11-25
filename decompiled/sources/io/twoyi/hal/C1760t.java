package io.twoyi.hal;

import ba.C0588o;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.t */
/* loaded from: classes.dex */
public final class C1760t implements AutoCloseable {

    /* renamed from: i */
    public final byte[] f6923i;

    /* renamed from: j */
    public C0588o f6924j;

    public C1760t(byte[] bArr, C0588o c0588o) {
        this.f6923i = bArr;
        this.f6924j = c0588o;
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        C0588o c0588o = this.f6924j;
        this.f6924j = null;
        if (c0588o != null) {
            c0588o.m1328B(this.f6923i);
        }
    }
}

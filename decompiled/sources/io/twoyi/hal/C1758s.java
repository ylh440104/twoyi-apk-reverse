package io.twoyi.hal;

import ba.C0588o;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.s */
/* loaded from: classes.dex */
public final class C1758s implements AutoCloseable {

    /* renamed from: i */
    public final long f6908i;

    /* renamed from: j */
    public final long f6909j;

    /* renamed from: k */
    public final int f6910k;

    /* renamed from: l */
    public final int f6911l;

    /* renamed from: m */
    public final int f6912m;

    /* renamed from: n */
    public final int f6913n;

    /* renamed from: o */
    public final int f6914o;

    /* renamed from: p */
    public final int f6915p;

    /* renamed from: q */
    public final int f6916q;

    /* renamed from: r */
    public final int f6917r;

    /* renamed from: s */
    public final byte[] f6918s;

    /* renamed from: t */
    public C0588o f6919t;

    public C1758s(long j3, long j10, int i, int i10, int i11, int i12, int i13, int i14, int i15, int i16, byte[] bArr, C0588o c0588o) {
        this.f6908i = j3;
        this.f6909j = j10;
        this.f6910k = i;
        this.f6911l = i10;
        this.f6912m = i11;
        this.f6913n = i12;
        this.f6914o = i13;
        this.f6915p = i14;
        this.f6916q = i15;
        this.f6917r = i16;
        this.f6918s = bArr;
        this.f6919t = c0588o;
    }

    @Override // java.lang.AutoCloseable
    public final synchronized void close() {
        C0588o c0588o = this.f6919t;
        this.f6919t = null;
        if (c0588o != null) {
            c0588o.m1328B(this.f6918s);
        }
    }
}

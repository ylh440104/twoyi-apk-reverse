package io.twoyi;

import p247w0.AbstractC4176k;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.d */
/* loaded from: classes.dex */
public final class C1707d {

    /* renamed from: a */
    public final int f6651a;

    /* renamed from: b */
    public final int f6652b;

    /* renamed from: c */
    public final float f6653c;

    /* renamed from: d */
    public final float f6654d;

    /* renamed from: e */
    public final int f6655e;

    public C1707d(int i, int i10, float f4, float f10, int i11) {
        if (i > 0 && i10 > 0 && f4 > AbstractC4176k.f15279f && f10 > AbstractC4176k.f15279f && i11 > 0) {
            this.f6651a = i;
            this.f6652b = i10;
            this.f6653c = f4;
            this.f6654d = f10;
            this.f6655e = i11;
            return;
        }
        throw new IllegalArgumentException("invalid guest display configuration");
    }

    public final String toString() {
        return this.f6651a + "x" + this.f6652b + " @" + this.f6655e + "Hz, " + this.f6653c + "x" + this.f6654d + "dpi";
    }
}

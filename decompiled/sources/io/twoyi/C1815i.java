package io.twoyi;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.i */
/* loaded from: classes.dex */
public final class C1815i {

    /* renamed from: a */
    public final float f7210a;

    /* renamed from: b */
    public final float f7211b;

    /* renamed from: c */
    public final float f7212c;

    /* renamed from: d */
    public final boolean f7213d;

    /* renamed from: e */
    public final int f7214e;

    /* renamed from: f */
    public final float[] f7215f;

    /* renamed from: g */
    public float f7216g;

    /* renamed from: h */
    public float f7217h;

    /* renamed from: i */
    public float f7218i;

    /* renamed from: j */
    public int f7219j;

    /* renamed from: k */
    public float f7220k;

    /* renamed from: l */
    public C1715h[] f7221l;

    public C1815i(float f4, float f10, float f11, int i, boolean z10) {
        this.f7210a = f4;
        this.f7211b = f10;
        this.f7212c = f11;
        this.f7213d = z10;
        this.f7214e = i;
        this.f7215f = new float[i];
        for (int i10 = 0; i10 < i; i10++) {
            float f12 = 1.0f - (i10 / i);
            this.f7215f[i10] = f12 * f12;
        }
    }
}

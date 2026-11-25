package io.twoyi.hal;

import p247w0.AbstractC4176k;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.j */
/* loaded from: classes.dex */
public final class C1740j {

    /* renamed from: a */
    public final int f6839a;

    /* renamed from: b */
    public final int f6840b;

    /* renamed from: c */
    public final int f6841c;

    /* renamed from: d */
    public final float f6842d;

    /* renamed from: e */
    public final float f6843e;

    /* renamed from: f */
    public final float f6844f;

    /* renamed from: g */
    public final float f6845g;

    /* renamed from: h */
    public final float f6846h;

    public C1740j(int i, int i10, int i11, float f4, float f10, float f11, float f12, float f13) {
        if (i >= 0 && i <= 7) {
            if ((i10 & (-32)) == 0) {
                if (i11 >= 1 && i11 <= 200) {
                    AbstractC1742k.m2941a(f4, AbstractC4176k.f15279f, 63.0f, "cn0DbHz");
                    AbstractC1742k.m2941a(f10, -90.0f, 90.0f, "elevationDegrees");
                    AbstractC1742k.m2941a(f11, AbstractC4176k.f15279f, 360.0f, "azimuthDegrees");
                    if ((i10 & 8) != 0) {
                        if (!Float.isFinite(f12) || f12 <= AbstractC4176k.f15279f) {
                            throw new IllegalArgumentException("invalid carrierFrequencyHz");
                        }
                    } else if (Float.floatToIntBits(f12) != 0) {
                        throw new IllegalArgumentException("carrierFrequencyHz without flag");
                    }
                    if ((i10 & 16) != 0) {
                        AbstractC1742k.m2941a(f13, AbstractC4176k.f15279f, 63.0f, "basebandCn0DbHz");
                    } else if (Float.floatToIntBits(f13) != 0) {
                        throw new IllegalArgumentException("basebandCn0DbHz without flag");
                    }
                    this.f6839a = i;
                    this.f6840b = i10;
                    this.f6841c = i11;
                    this.f6842d = f4;
                    this.f6843e = f10;
                    this.f6844f = f11;
                    this.f6845g = f12;
                    this.f6846h = f13;
                    return;
                }
                throw new IllegalArgumentException("invalid svid");
            }
            throw new IllegalArgumentException("invalid satellite flags");
        }
        throw new IllegalArgumentException("invalid constellation");
    }
}

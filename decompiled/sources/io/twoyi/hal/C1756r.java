package io.twoyi.hal;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.r */
/* loaded from: classes.dex */
public final class C1756r extends Exception {

    /* renamed from: i */
    public final int f6887i;

    public C1756r(int i, String str) {
        super(str);
        this.f6887i = i;
    }

    public C1756r(Exception exc) {
        super("Unable to submit host capture", exc);
        this.f6887i = 9;
    }
}

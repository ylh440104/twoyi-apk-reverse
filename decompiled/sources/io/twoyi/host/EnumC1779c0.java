package io.twoyi.host;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.c0 */
/* loaded from: classes.dex */
public final class EnumC1779c0 {

    /* renamed from: i */
    public static final EnumC1779c0 f7047i;

    /* renamed from: j */
    public static final EnumC1779c0 f7048j;

    /* renamed from: k */
    public static final EnumC1779c0 f7049k;

    /* renamed from: l */
    public static final /* synthetic */ EnumC1779c0[] f7050l;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, io.twoyi.host.c0] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, io.twoyi.host.c0] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, io.twoyi.host.c0] */
    static {
        ?? r02 = new Enum("GRANTED", 0);
        f7047i = r02;
        ?? r12 = new Enum("NOT_RUNNING", 1);
        f7048j = r12;
        ?? r2 = new Enum("DENIED", 2);
        f7049k = r2;
        f7050l = new EnumC1779c0[]{r02, r12, r2};
    }

    public static EnumC1779c0 valueOf(String str) {
        return (EnumC1779c0) Enum.valueOf(EnumC1779c0.class, str);
    }

    public static EnumC1779c0[] values() {
        return (EnumC1779c0[]) f7050l.clone();
    }
}

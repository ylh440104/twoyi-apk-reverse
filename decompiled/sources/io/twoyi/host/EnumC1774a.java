package io.twoyi.host;

import p183p8.C2996b;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.a */
/* loaded from: classes.dex */
public final class EnumC1774a {

    /* renamed from: i */
    public static final EnumC1774a f7032i;

    /* renamed from: j */
    public static final EnumC1774a f7033j;

    /* renamed from: k */
    public static final /* synthetic */ EnumC1774a[] f7034k;

    /* renamed from: l */
    public static final /* synthetic */ C2996b f7035l;

    /* JADX WARN: Type inference failed for: r0v0, types: [io.twoyi.host.a, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r1v1, types: [io.twoyi.host.a, java.lang.Enum] */
    static {
        ?? r02 = new Enum("ROOT", 0);
        f7032i = r02;
        ?? r12 = new Enum("SHIZUKU", 1);
        f7033j = r12;
        EnumC1774a[] enumC1774aArr = {r02, r12};
        f7034k = enumC1774aArr;
        f7035l = new C2996b(enumC1774aArr);
    }

    public static EnumC1774a valueOf(String str) {
        return (EnumC1774a) Enum.valueOf(EnumC1774a.class, str);
    }

    public static EnumC1774a[] values() {
        return (EnumC1774a[]) f7034k.clone();
    }
}

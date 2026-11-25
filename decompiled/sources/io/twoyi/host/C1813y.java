package io.twoyi.host;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import p114j8.AbstractC1921a0;
import p114j8.AbstractC1938r;
import p114j8.C1940t;
import p114j8.C1942v;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.y */
/* loaded from: classes.dex */
public final class C1813y {

    /* renamed from: a */
    public final int f7201a;

    /* renamed from: b */
    public HashSet f7202b;

    /* renamed from: c */
    public Object f7203c = C1942v.f7632i;

    public C1813y(int i) {
        this.f7201a = i;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.Set, java.lang.Object] */
    /* renamed from: a */
    public final C1812x m3124a(List list, boolean z10) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((C1814z) obj).f7204a == 0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            C1814z c1814z = (C1814z) obj2;
            if (c1814z.f7204a == this.f7201a && c1814z.f7208e) {
                arrayList2.add(obj2);
            }
        }
        HashSet hashSet = new HashSet();
        int size = arrayList2.size();
        int i = 0;
        int i10 = 0;
        while (i10 < size) {
            Object obj3 = arrayList2.get(i10);
            i10++;
            AbstractC1938r.m3291g0(((C1814z) obj3).f7206c, hashSet);
        }
        LinkedHashSet m3232h0 = AbstractC1921a0.m3232h0(this.f7203c, hashSet);
        HashSet hashSet2 = this.f7202b;
        HashSet hashSet3 = new HashSet();
        int size2 = arrayList.size();
        int i11 = 0;
        while (i11 < size2) {
            Object obj4 = arrayList.get(i11);
            i11++;
            hashSet3.add(Integer.valueOf(((C1814z) obj4).f7207d));
        }
        this.f7202b = hashSet3;
        this.f7203c = hashSet;
        if (hashSet2 != null && z10) {
            ArrayList arrayList3 = new ArrayList();
            int size3 = arrayList.size();
            while (i < size3) {
                Object obj5 = arrayList.get(i);
                i++;
                C1814z c1814z2 = (C1814z) obj5;
                if (!hashSet2.contains(Integer.valueOf(c1814z2.f7207d)) && c1814z2.f7208e) {
                    arrayList3.add(obj5);
                }
            }
            return new C1812x(arrayList3, m3232h0);
        }
        return new C1812x(C1940t.f7630i, m3232h0);
    }
}

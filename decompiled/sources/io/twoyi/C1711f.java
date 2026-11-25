package io.twoyi;

import java.util.AbstractList;
import java.util.concurrent.Executor;
import p009a8.RunnableC0121c1;
import p214s7.AbstractC3521d0;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.f */
/* loaded from: classes.dex */
public final class C1711f extends AbstractList {

    /* renamed from: i */
    public final Executor f6691i = AbstractC3521d0.f12595b;

    /* renamed from: j */
    public final /* synthetic */ BootLogTexture f6692j;

    public C1711f(BootLogTexture bootLogTexture) {
        this.f6692j = bootLogTexture;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        this.f6691i.execute(new RunnableC0121c1(15, this, obj));
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return null;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        return null;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        return null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return 0;
    }
}

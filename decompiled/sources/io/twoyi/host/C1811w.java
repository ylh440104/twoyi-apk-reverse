package io.twoyi.host;

import android.content.Intent;
import android.os.IBinder;
import p223t7.AbstractC3725a;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.w */
/* loaded from: classes.dex */
public final class C1811w extends AbstractC3725a {

    /* renamed from: c */
    public static final int f7197c = 8;

    /* renamed from: b */
    private BinderC1809u f7198b;

    public C1811w() {
        super(null);
    }

    @Override // p223t7.AbstractC3725a
    /* renamed from: g */
    public IBinder mo3121g(Intent intent) {
        AbstractC4450j.m7255e(intent, "intent");
        BinderC1809u binderC1809u = this.f7198b;
        if (binderC1809u != null) {
            return binderC1809u;
        }
        AbstractC4450j.m7260j("helper");
        throw null;
    }

    @Override // p223t7.AbstractC3725a
    /* renamed from: h */
    public void mo3122h() {
        this.f7198b = new BinderC1809u(this);
    }

    @Override // p223t7.AbstractC3725a
    /* renamed from: i */
    public void mo3123i() {
        BinderC1809u binderC1809u = this.f7198b;
        if (binderC1809u != null) {
            binderC1809u.m3117t0();
        } else {
            AbstractC4450j.m7260j("helper");
            throw null;
        }
    }
}

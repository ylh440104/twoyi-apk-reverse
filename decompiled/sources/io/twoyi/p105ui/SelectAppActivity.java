package io.twoyi.p105ui;

import android.os.Bundle;
import p014b.AbstractActivityC0349n;
import p014b.AbstractC0352q;
import p014b.C0343i0;
import p014b.C0345j0;
import p024c.AbstractC0608j;
import p094i1.C1610f;
import p264x7.C4426e;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* loaded from: classes.dex */
public final class SelectAppActivity extends AbstractActivityC0349n {

    /* renamed from: B */
    public static final /* synthetic */ int f7319B = 0;

    @Override // p014b.AbstractActivityC0349n, android.app.Activity
    public final void onCreate(Bundle bundle) {
        C0345j0 c0345j0 = new C0345j0(0, 0, 1, C0343i0.f1461l);
        AbstractC0352q.m913a(this, c0345j0, c0345j0);
        super.onCreate(bundle);
        AbstractC0608j.m1424a(this, new C1610f(-1920351686, true, new C4426e(this, getIntent().getStringExtra("instance"), 0)));
    }
}

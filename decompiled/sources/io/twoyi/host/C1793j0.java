package io.twoyi.host;

import android.content.Context;
import android.content.ContextWrapper;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.j0 */
/* loaded from: classes.dex */
public final class C1793j0 extends ContextWrapper {

    /* renamed from: a */
    public final /* synthetic */ String f7085a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1793j0(Context context, String str) {
        super(context);
        this.f7085a = str;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final String getOpPackageName() {
        return this.f7085a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final String getPackageName() {
        return this.f7085a;
    }
}

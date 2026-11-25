package io.twoyi;

import android.content.Context;
import android.content.Intent;
import io.twoyi.host.HostDisplayService;
import p009a8.C0156j1;
import p009a8.C0218w0;
import p009a8.C0226y0;
import p089h8.AbstractC1571g0;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.a */
/* loaded from: classes.dex */
public final class C1701a extends C1842v0 {

    /* renamed from: b */
    public final TwoyiApplication f6639b;

    public C1701a(TwoyiApplication twoyiApplication) {
        this.f6639b = twoyiApplication;
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: a */
    public final InterfaceC1824m0 mo2769a(Context context) {
        return C0218w0.m540h(context).f583i;
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: b */
    public final String mo2770b(Context context) {
        return "io.twoyi.ui.twin.MainActivity";
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: c */
    public final void mo2771c(boolean z10) {
        C0156j1 c0156j1 = C0156j1.f574k;
        if (c0156j1 != null) {
            c0156j1.m461b(new C0226y0(c0156j1, z10, 0));
        }
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: d */
    public final void mo2772d(AbstractServiceC1710e0 abstractServiceC1710e0) {
        C0218w0.m540h(abstractServiceC1710e0);
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: e */
    public final boolean mo2773e(AbstractServiceC1710e0 abstractServiceC1710e0, Intent intent) {
        if (!"io.twoyi.action.ANSWER_AGENT_NEED".equals(intent.getAction())) {
            return false;
        }
        C0156j1 m540h = C0218w0.m540h(abstractServiceC1710e0);
        m540h.f579e.mo430b(null, intent.getBooleanExtra("accepted", false));
        return true;
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: f */
    public final void mo2774f(Context context) {
        AbstractC1571g0.m2627c(context, new Intent("io.twoyi.action.OPEN_SETTINGS").setClassName(context, "io.twoyi.ui.twin.MainActivity").addFlags(603979776));
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: g */
    public final Class mo2775g() {
        if (AbstractC1712f0.m2813G(this.f6639b)) {
            return HostDisplayService.class;
        }
        return GuestInstanceService$Slot0.class;
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: h */
    public final String mo2776h(AbstractServiceC1716h0 abstractServiceC1716h0) {
        return abstractServiceC1716h0.getString(R.string.twin_session_channel_name);
    }

    @Override // io.twoyi.C1842v0
    /* renamed from: i */
    public final String mo2777i(AbstractServiceC1716h0 abstractServiceC1716h0) {
        int i;
        if (AbstractC1712f0.m2813G(abstractServiceC1716h0)) {
            i = R.string.twin_host_notification_title;
        } else {
            i = R.string.twin_session_notification_title;
        }
        return abstractServiceC1716h0.getString(i);
    }
}

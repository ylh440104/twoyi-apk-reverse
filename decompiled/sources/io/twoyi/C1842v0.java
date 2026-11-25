package io.twoyi;

import android.content.Context;
import android.content.Intent;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.v0 */
/* loaded from: classes.dex */
public class C1842v0 {

    /* renamed from: a */
    public static volatile C1842v0 f7331a = new Object();

    /* renamed from: a */
    public InterfaceC1824m0 mo2769a(Context context) {
        return null;
    }

    /* renamed from: b */
    public String mo2770b(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage != null && launchIntentForPackage.getComponent() != null) {
            return launchIntentForPackage.getComponent().getClassName();
        }
        throw new IllegalStateException("no launcher Activity; override entryActivity");
    }

    /* renamed from: e */
    public boolean mo2773e(AbstractServiceC1710e0 abstractServiceC1710e0, Intent intent) {
        return false;
    }

    /* renamed from: g */
    public Class mo2775g() {
        return GuestInstanceService$Slot0.class;
    }

    /* renamed from: h */
    public String mo2776h(AbstractServiceC1716h0 abstractServiceC1716h0) {
        return abstractServiceC1716h0.getApplicationInfo().loadLabel(abstractServiceC1716h0.getPackageManager()).toString();
    }

    /* renamed from: i */
    public String mo2777i(AbstractServiceC1716h0 abstractServiceC1716h0) {
        return abstractServiceC1716h0.getApplicationInfo().loadLabel(abstractServiceC1716h0.getPackageManager()).toString();
    }

    /* renamed from: c */
    public void mo2771c(boolean z10) {
    }

    /* renamed from: d */
    public void mo2772d(AbstractServiceC1710e0 abstractServiceC1710e0) {
    }

    /* renamed from: f */
    public void mo2774f(Context context) {
    }
}

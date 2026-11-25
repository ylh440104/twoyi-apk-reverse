package io.twoyi.hal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.LocalSocket;
import android.util.Log;
import na.AbstractC2697l;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.b0 */
/* loaded from: classes.dex */
public final class C1721b0 {

    /* renamed from: d */
    public static final C1721b0 f6732d = new C1721b0();

    /* renamed from: a */
    public Context f6733a;

    /* renamed from: b */
    public LocalSocket f6734b;

    /* renamed from: c */
    public final C1772z f6735c = new C1772z(1, this);

    /* renamed from: a */
    public final void m2874a(LocalSocket localSocket) {
        Log.d("b0", "HAL handleBroadcast");
        this.f6734b = localSocket;
        Context context = this.f6733a;
        if (context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        AbstractC2697l.m4574M(context, this.f6735c, intentFilter, 4);
    }
}

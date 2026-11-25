package io.twoyi.hal;

import android.content.Context;
import android.net.LocalSocket;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import io.twoyi.hal.ril.TwoyiModemNative;
import p004a3.C0042m0;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.w0 */
/* loaded from: classes.dex */
public final class HandlerC1767w0 extends Handler {
    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        String str;
        int i = message.what;
        C1769x0 c1769x0 = C1769x0.f6996e;
        boolean z10 = true;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    try {
                        int simState = ((TelephonyManager) ((Context) c1769x0.f6998b).getSystemService("phone")).getSimState();
                        if (simState == 0 || simState == 1 || simState == 6) {
                            z10 = false;
                        }
                    } catch (Exception e10) {
                        Log.e("x0", "get sim card status failed" + e10.getMessage());
                    }
                    Log.d("x0", "isSimCardEnabled:" + z10);
                    if (z10) {
                        TwoyiModemNative twoyiModemNative = TwoyiModemNative.getInstance();
                        LocalSocket localSocket = c1769x0.f6997a;
                        try {
                            str = ((TelephonyManager) ((Context) c1769x0.f6998b).getSystemService("phone")).getSubscriberId();
                        } catch (Exception e11) {
                            Log.e("x0", "get imsi failed" + e11.getMessage());
                            str = null;
                        }
                        twoyiModemNative.init(localSocket, str);
                        TwoyiModemNative.getInstance().setOnModemCallback(new C0042m0(4));
                        return;
                    }
                    return;
                }
                return;
            }
            c1769x0.m3007c(false);
            return;
        }
        c1769x0.m3007c(true);
    }
}

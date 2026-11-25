package io.twoyi.hal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LocalSocket;
import android.net.NetworkRequest;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.ServiceState;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import na.AbstractC2697l;
import p052e5.HandlerC0954a;
import p081h0.AbstractC1474y0;
import p181p6.AbstractC2967c;
import p181p6.AbstractC2977m;
import p278z.AbstractC4540b;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.b1 */
/* loaded from: classes.dex */
public final class C1722b1 {

    /* renamed from: h */
    public static final C1722b1 f6736h = new C1722b1();

    /* renamed from: a */
    public Context f6737a;

    /* renamed from: b */
    public LocalSocket f6738b;

    /* renamed from: c */
    public HandlerThread f6739c;

    /* renamed from: d */
    public HandlerC0954a f6740d;

    /* renamed from: e */
    public int f6741e = -1;

    /* renamed from: f */
    public C1728d1 f6742f = null;

    /* renamed from: g */
    public final C1772z f6743g = new C1772z(4, this);

    /* renamed from: b */
    public static boolean m2875b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", null);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(connectivityManager, null)).booleanValue();
        } catch (Exception e10) {
            e10.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public final void m2876a(LocalSocket localSocket) {
        Log.d("b1", "HAL handleTelephony");
        this.f6741e = -1;
        this.f6738b = localSocket;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f6737a.getSystemService("connectivity");
        if (this.f6742f == null) {
            this.f6742f = new C1728d1(2, this);
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            builder.addTransportType(0);
            if (this.f6742f != null) {
                connectivityManager.registerNetworkCallback(builder.build(), this.f6742f);
            }
        }
        Context context = this.f6737a;
        if (context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        AbstractC2697l.m4574M(context, this.f6743g, intentFilter, 4);
    }

    /* renamed from: c */
    public final void m2877c(Context context) {
        int i;
        ServiceState serviceState;
        if (!AbstractC2967c.m5358a(context, AbstractC2977m.m5365b("android.permission.READ_PHONE_STATE"))) {
            Log.d("b1", "checkPermission  ACCESS_NETWORK_STATE");
            i = -3;
        } else {
            int i10 = 0;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
                if (defaultDataSubscriptionId < 0) {
                    serviceState = telephonyManager.getServiceState();
                } else {
                    Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getServiceStateForSubscriber", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    serviceState = (ServiceState) declaredMethod.invoke(telephonyManager, Integer.valueOf(defaultDataSubscriptionId));
                }
                Method method = ServiceState.class.getMethod("getHwNetworkType", null);
                method.setAccessible(true);
                Integer num = (Integer) method.invoke(serviceState, null);
                if (num != null) {
                    i10 = num.intValue();
                }
            } catch (Exception unused) {
            }
            if (i10 != 20) {
                switch (i10) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        i = 2;
                        break;
                    case 3:
                    case AbstractC4540b.f16788j /* 5 */:
                    case AbstractC4540b.f16786h /* 6 */:
                    case 8:
                    case 9:
                    case 10:
                    case AbstractC1474y0.f5961f /* 12 */:
                    case 14:
                    case AbstractC4540b.f16789k /* 15 */:
                        i = 3;
                        break;
                    case 13:
                        i = 4;
                        break;
                    default:
                        i = -1;
                        break;
                }
            } else {
                i = 5;
            }
        }
        Log.d("b1", "getNetworkType: " + i);
        if (i != -1 && this.f6741e != i) {
            Log.d("b1", "net state changed");
            this.f6740d.sendEmptyMessageDelayed(4, 200L);
            Message obtainMessage = this.f6740d.obtainMessage(6);
            obtainMessage.arg1 = i;
            this.f6740d.sendMessage(obtainMessage);
            this.f6741e = i;
        }
    }

    /* renamed from: d */
    public final void m2878d(int i) {
        LocalSocket localSocket = this.f6738b;
        if (localSocket != null && localSocket.isConnected()) {
            try {
                OutputStream outputStream = this.f6738b.getOutputStream();
                C1721b0 c1721b0 = C1721b0.f6732d;
                outputStream.write(i);
            } catch (IOException e10) {
                e10.printStackTrace();
            }
        }
    }
}

package io.twoyi.hal;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.LocalSocket;
import android.os.Parcel;
import android.util.Log;
import io.twoyi.p105ui.twin.MainActivity;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import p009a8.C0127d2;
import p067f8.C1171e4;
import p067f8.EnumC1259v0;
import p101i8.C1681q;
import p183p8.C2996b;
import p265x8.AbstractC4450j;
import p278z.AbstractC4540b;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.z */
/* loaded from: classes.dex */
public final class C1772z extends BroadcastReceiver {

    /* renamed from: a */
    public final /* synthetic */ int f7008a;

    /* renamed from: b */
    public final /* synthetic */ Object f7009b;

    public /* synthetic */ C1772z(int i, Object obj) {
        this.f7008a = i;
        this.f7009b = obj;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Object obj;
        boolean z10 = false;
        int i = 4;
        switch (this.f7008a) {
            case AbstractC4695a.f17215a /* 0 */:
                C0127d2 c0127d2 = (C0127d2) this.f7009b;
                BluetoothAdapter bluetoothAdapter = (BluetoothAdapter) c0127d2.f460i;
                C1718a0.f6724d.lock();
                try {
                    String action = intent.getAction();
                    intent.setPackage(null);
                    if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) == 12) {
                            Field declaredField = bluetoothAdapter.getClass().getDeclaredField("mService");
                            declaredField.setAccessible(true);
                            ((C1719a1) c0127d2.f461j).m2873e(declaredField.get(bluetoothAdapter));
                        }
                        Log.e("d2", "bluetooth onReceive ACTION_STATE_CHANGED, state: " + intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE));
                    } else {
                        Log.e("d2", "bluetooth onReceive " + intent);
                    }
                    LocalSocket m2868a = C1718a0.f6723c.m2868a();
                    if (m2868a != null) {
                        try {
                            OutputStream outputStream = m2868a.getOutputStream();
                            outputStream.write(0);
                            outputStream.flush();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                        }
                        Parcel obtain = Parcel.obtain();
                        C0127d2.m376Z(obtain, intent);
                        try {
                            byte[] marshall = obtain.marshall();
                            int length = marshall.length;
                            try {
                                OutputStream outputStream2 = m2868a.getOutputStream();
                                outputStream2.write(new byte[]{(byte) (length & 255), (byte) ((length >> 8) & 255), (byte) ((length >> 16) & 255), (byte) ((length >> 24) & 255)}, 0, 4);
                                outputStream2.flush();
                            } catch (IOException e11) {
                                e11.printStackTrace();
                            }
                            OutputStream outputStream3 = m2868a.getOutputStream();
                            outputStream3.write(marshall, 0, marshall.length);
                            outputStream3.flush();
                            z10 = true;
                        } catch (IOException e12) {
                            e12.printStackTrace();
                        }
                        obtain.recycle();
                        if (!z10) {
                            C1718a0 c1718a0 = C1718a0.f6723c;
                            try {
                                LocalSocket localSocket = c1718a0.f6726b;
                                if (localSocket != null) {
                                    localSocket.close();
                                }
                            } catch (IOException e13) {
                                e13.printStackTrace();
                            }
                            c1718a0.f6726b = null;
                        }
                    }
                } catch (Exception e14) {
                    Log.e("d2", "mBluetoothReceiver error ", e14);
                }
                C1718a0.f6724d.unlock();
                return;
            case 1:
                C1721b0 c1721b0 = (C1721b0) this.f7009b;
                LocalSocket localSocket2 = c1721b0.f6734b;
                if (localSocket2 != null && localSocket2.isConnected()) {
                    intent.setPackage(null);
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        OutputStream outputStream4 = c1721b0.f6734b.getOutputStream();
                        outputStream4.write(2);
                        C0127d2.m376Z(obtain2, intent);
                        byte[] marshall2 = obtain2.marshall();
                        outputStream4.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(marshall2.length).array());
                        outputStream4.write(marshall2);
                        obtain2.recycle();
                        return;
                    } catch (IOException e15) {
                        e15.printStackTrace();
                        return;
                    }
                }
                return;
            case 2:
                C1757r0 c1757r0 = (C1757r0) this.f7009b;
                c1757r0.f6904p.post(new RunnableC1747m0(c1757r0, 2));
                return;
            case 3:
                if ("io.twoyi.light.CHANGE".equals(intent.getAction())) {
                    ((C1763u0) this.f7009b).getClass();
                    return;
                }
                return;
            case 4:
                C1722b1 c1722b1 = (C1722b1) this.f7009b;
                LocalSocket localSocket3 = c1722b1.f6738b;
                if (localSocket3 != null && localSocket3.isConnected() && intent.getAction().equals("android.intent.action.SIM_STATE_CHANGED")) {
                    String stringExtra = intent.getStringExtra("ss");
                    C1722b1 c1722b12 = C1722b1.f6736h;
                    Log.d("b1", "ss:" + stringExtra);
                    stringExtra.getClass();
                    char c6 = 65535;
                    switch (stringExtra.hashCode()) {
                        case -2044189691:
                            if (stringExtra.equals("LOADED")) {
                                c6 = 0;
                                break;
                            }
                            break;
                        case 77848963:
                            if (stringExtra.equals("READY")) {
                                c6 = 1;
                                break;
                            }
                            break;
                        case 1924388665:
                            if (stringExtra.equals("ABSENT")) {
                                c6 = 2;
                                break;
                            }
                            break;
                    }
                    switch (c6) {
                        case AbstractC4695a.f17215a /* 0 */:
                            c1722b1.f6740d.sendEmptyMessageDelayed(2, 1000L);
                            if (C1722b1.m2875b(c1722b1.f6737a)) {
                                c1722b1.f6740d.sendEmptyMessageDelayed(4, 1500L);
                            } else {
                                c1722b1.f6740d.sendEmptyMessageDelayed(5, 1500L);
                            }
                            c1722b1.m2877c(c1722b1.f6737a);
                            return;
                        case 1:
                            c1722b1.f6740d.sendEmptyMessageDelayed(0, 500L);
                            return;
                        case 2:
                            c1722b1.f6740d.sendEmptyMessageDelayed(1, 500L);
                            return;
                        default:
                            return;
                    }
                }
                return;
            case AbstractC4540b.f16788j /* 5 */:
                String action2 = intent.getAction();
                if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action2)) {
                    int intExtra = intent.getIntExtra("wifi_state", 4);
                    ((C1731e1) this.f7009b).m2922z();
                    C1731e1 c1731e1 = (C1731e1) this.f7009b;
                    synchronized (c1731e1.f6795l) {
                        c1731e1.f6798o = null;
                    }
                    C1731e1 c1731e12 = (C1731e1) this.f7009b;
                    synchronized (c1731e12.f6790g) {
                        OutputStream outputStream5 = c1731e12.f6792i;
                        if (outputStream5 != null) {
                            try {
                                outputStream5.write(0);
                                C1731e1.m2891H(c1731e12.f6792i, intExtra);
                            } catch (IOException e16) {
                                c1731e12.m2921y(e16);
                            }
                        }
                    }
                    if (intExtra == 1 || intExtra == 0) {
                        ((C1731e1) this.f7009b).f6787d = null;
                        C1731e1 c1731e13 = (C1731e1) this.f7009b;
                        c1731e13.m2919w(c1731e13.m2912p());
                        return;
                    }
                    return;
                }
                if ("android.net.wifi.SCAN_RESULTS".equals(action2)) {
                    boolean booleanExtra = intent.getBooleanExtra("resultsUpdated", false);
                    C1731e1 c1731e14 = (C1731e1) this.f7009b;
                    synchronized (c1731e14.f6795l) {
                        c1731e14.f6798o = null;
                    }
                    C1731e1 c1731e15 = (C1731e1) this.f7009b;
                    synchronized (c1731e15.f6790g) {
                        OutputStream outputStream6 = c1731e15.f6792i;
                        if (outputStream6 != null) {
                            try {
                                outputStream6.write(1);
                                C1731e1.m2891H(c1731e15.f6792i, booleanExtra ? 1 : 0);
                            } catch (IOException e17) {
                                c1731e15.m2921y(e17);
                            }
                            return;
                        }
                        return;
                    }
                }
                if ("android.net.wifi.STATE_CHANGE".equals(action2)) {
                    ((C1731e1) this.f7009b).m2922z();
                    ((C1731e1) this.f7009b).m2901D();
                    C1731e1 c1731e16 = (C1731e1) this.f7009b;
                    c1731e16.m2919w(c1731e16.m2912p());
                    return;
                }
                if ("android.location.MODE_CHANGED".equals(action2)) {
                    ((C1731e1) this.f7009b).m2922z();
                    C1731e1 c1731e17 = (C1731e1) this.f7009b;
                    synchronized (c1731e17.f6795l) {
                        c1731e17.f6798o = null;
                    }
                    return;
                }
                return;
            default:
                AbstractC4450j.m7255e(context, "context");
                AbstractC4450j.m7255e(intent, "intent");
                String stringExtra2 = intent.getStringExtra("control");
                if (stringExtra2 != null) {
                    C2996b c2996b = EnumC1259v0.f4954n;
                    c2996b.getClass();
                    C1681q c1681q = new C1681q(i, c2996b);
                    while (true) {
                        if (c1681q.hasNext()) {
                            obj = c1681q.next();
                            if (AbstractC4450j.m7251a(((EnumC1259v0) obj).name(), stringExtra2)) {
                            }
                        } else {
                            obj = null;
                        }
                    }
                    EnumC1259v0 enumC1259v0 = (EnumC1259v0) obj;
                    if (enumC1259v0 != null) {
                        C1171e4 c1171e4 = ((MainActivity) this.f7009b).f7323C;
                        if (c1171e4 != null) {
                            c1171e4.m2181f(enumC1259v0.f4955i);
                            return;
                        } else {
                            AbstractC4450j.m7260j("vm");
                            throw null;
                        }
                    }
                    return;
                }
                return;
        }
    }
}

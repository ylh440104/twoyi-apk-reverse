package io.twoyi.hal;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.LocalSocket;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.RouteInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import io.twoyi.host.BinderC1809u;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import na.AbstractC2697l;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p009a8.AbstractC0179o;
import p081h0.AbstractC1474y0;
import p089h8.AbstractC1589y;
import p278z.AbstractC4540b;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.e1 */
/* loaded from: classes.dex */
public final class C1731e1 {

    /* renamed from: t */
    public static final C1731e1 f6781t = new C1731e1();

    /* renamed from: u */
    public static final String f6782u = C1731e1.class.getSimpleName();

    /* renamed from: v */
    public static final Pattern f6783v = Pattern.compile("([\\p{XDigit}]{2}:){5}[\\p{XDigit}]{2}");

    /* renamed from: a */
    public volatile Context f6784a;

    /* renamed from: b */
    public volatile WifiManager f6785b;

    /* renamed from: c */
    public volatile ConnectivityManager f6786c;

    /* renamed from: d */
    public volatile Network f6787d;

    /* renamed from: h */
    public LocalSocket f6791h;

    /* renamed from: i */
    public OutputStream f6792i;

    /* renamed from: j */
    public LocalSocket f6793j;

    /* renamed from: k */
    public int f6794k;

    /* renamed from: m */
    public WifiInfo f6796m;

    /* renamed from: n */
    public long f6797n;

    /* renamed from: o */
    public List f6798o;

    /* renamed from: p */
    public long f6799p;

    /* renamed from: e */
    public final AtomicLong f6788e = new AtomicLong();

    /* renamed from: f */
    public final ConcurrentHashMap.KeySetView f6789f = ConcurrentHashMap.newKeySet();

    /* renamed from: g */
    public final Object f6790g = new Object();

    /* renamed from: l */
    public final Object f6795l = new Object();

    /* renamed from: q */
    public final C1772z f6800q = new C1772z(5, this);

    /* renamed from: r */
    public final C1728d1 f6801r = new C1728d1(0, this);

    /* renamed from: s */
    public final C1728d1 f6802s = new C1728d1(1, this);

    /* renamed from: B */
    public static JSONObject m2887B(LinkProperties linkProperties) {
        Object interfaceName;
        int mtu;
        boolean hasGateway;
        Object obj;
        String str;
        int type;
        JSONObject jSONObject = new JSONObject();
        Object obj2 = "";
        if (linkProperties.getInterfaceName() == null) {
            interfaceName = "";
        } else {
            interfaceName = linkProperties.getInterfaceName();
        }
        jSONObject.put("interfaceName", interfaceName);
        JSONArray jSONArray = new JSONArray();
        Iterator<LinkAddress> it = linkProperties.getLinkAddresses().iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toString());
        }
        jSONObject.put("linkAddresses", jSONArray);
        JSONArray jSONArray2 = new JSONArray();
        for (RouteInfo routeInfo : linkProperties.getRoutes()) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("destination", routeInfo.getDestination().toString());
            hasGateway = routeInfo.hasGateway();
            if (hasGateway) {
                obj = routeInfo.getGateway().getHostAddress();
            } else {
                obj = JSONObject.NULL;
            }
            jSONObject2.put("gateway", obj);
            if (routeInfo.getInterface() == null) {
                str = "";
            } else {
                str = routeInfo.getInterface();
            }
            jSONObject2.put("interfaceName", str);
            String str2 = "UNKNOWN";
            if (Build.VERSION.SDK_INT >= 30) {
                try {
                    type = routeInfo.getType();
                } catch (LinkageError | RuntimeException unused) {
                }
                if (type != 1) {
                    if (type != 7) {
                        if (type == 9) {
                            str2 = "THROW";
                        }
                    } else {
                        str2 = "UNREACHABLE";
                    }
                    jSONObject2.put("type", str2);
                    jSONArray2.put(jSONObject2);
                }
            }
            str2 = "UNICAST";
            jSONObject2.put("type", str2);
            jSONArray2.put(jSONObject2);
        }
        jSONObject.put("routes", jSONArray2);
        JSONArray jSONArray3 = new JSONArray();
        Iterator<InetAddress> it2 = linkProperties.getDnsServers().iterator();
        while (it2.hasNext()) {
            jSONArray3.put(it2.next().getHostAddress());
        }
        jSONObject.put("dnsServers", jSONArray3);
        if (linkProperties.getDomains() != null) {
            obj2 = linkProperties.getDomains();
        }
        jSONObject.put("domains", obj2);
        mtu = linkProperties.getMtu();
        jSONObject.put("mtu", mtu);
        return jSONObject;
    }

    /* renamed from: C */
    public static int m2888C(InputStream inputStream) {
        long j3 = 0;
        for (int i = 0; i < 4; i++) {
            long read = inputStream.read();
            if (read != -1) {
                j3 |= read << (i * 8);
            } else {
                throw new IOException("Premature end of data");
            }
        }
        return (int) j3;
    }

    /* renamed from: F */
    public static JSONObject m2889F(ScanResult scanResult) {
        JSONObject jSONObject = new JSONObject();
        String str = scanResult.SSID;
        String str2 = "";
        if (str == null) {
            str = "";
        }
        jSONObject.put("ssid", str);
        jSONObject.put("bssid", m2898i(scanResult.BSSID));
        String str3 = scanResult.capabilities;
        if (str3 != null) {
            str2 = str3;
        }
        jSONObject.put("capabilities", str2);
        jSONObject.put("frequencyMhz", scanResult.frequency);
        jSONObject.put("rssiDbm", scanResult.level);
        jSONObject.put("timestampUs", scanResult.timestamp);
        return jSONObject;
    }

    /* renamed from: G */
    public static String m2890G(String str) {
        if (str != null && str.length() >= 2 && str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    /* renamed from: H */
    public static void m2891H(OutputStream outputStream, int i) {
        outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i).array());
    }

    /* renamed from: I */
    public static void m2892I(OutputStream outputStream, Parcelable parcelable) {
        if (parcelable == null) {
            m2891H(outputStream, 0);
            return;
        }
        Parcel obtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            m2891H(outputStream, marshall.length);
            outputStream.write(marshall);
        } finally {
            obtain.recycle();
        }
    }

    /* renamed from: J */
    public static void m2893J(OutputStream outputStream, byte[] bArr) {
        if (bArr.length != 0 && bArr.length <= 65536) {
            m2891H(outputStream, bArr.length);
            outputStream.write(bArr);
            outputStream.flush();
            return;
        }
        throw new IOException("Invalid WiFi snapshot body length");
    }

    /* renamed from: a */
    public static void m2894a(C1731e1 c1731e1, Network network, NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null && c1731e1.f6786c != null) {
            try {
                networkCapabilities = c1731e1.f6786c.getNetworkCapabilities(network);
            } catch (RuntimeException e10) {
                Log.w(f6782u, "get WiFi capabilities failed", e10);
            }
        }
        if (networkCapabilities != null && networkCapabilities.hasTransport(1)) {
            c1731e1.f6787d = network;
        }
        c1731e1.m2919w(c1731e1.m2912p());
    }

    /* renamed from: c */
    public static JSONArray m2895c(int... iArr) {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(iArr.length);
        for (int i : iArr) {
            jSONArray.put(i);
        }
        return jSONArray;
    }

    /* renamed from: e */
    public static void m2896e(LocalSocket localSocket) {
        if (localSocket == null) {
            return;
        }
        try {
            localSocket.close();
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0192  */
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject m2897h(android.net.wifi.WifiInfo r5, android.net.wifi.WifiInfo r6) {
        /*
            Method dump skipped, instructions count: 562
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1731e1.m2897h(android.net.wifi.WifiInfo, android.net.wifi.WifiInfo):org.json.JSONObject");
    }

    /* renamed from: i */
    public static String m2898i(String str) {
        if (str != null && f6783v.matcher(str).matches()) {
            return str;
        }
        return "02:00:00:00:00:00";
    }

    /* renamed from: o */
    public static String m2899o(WifiInfo wifiInfo) {
        String ssid;
        if (wifiInfo == null || (ssid = wifiInfo.getSSID()) == null || ssid.length() == 0 || "<unknown ssid>".equalsIgnoreCase(ssid)) {
            return "\"WiFi\"";
        }
        return ssid;
    }

    /* renamed from: A */
    public final boolean m2900A() {
        int m2917u = m2917u();
        if (m2917u != 1 && m2917u != 0) {
            if (m2908k() != null) {
                return true;
            }
            if (this.f6786c == null) {
                return false;
            }
            try {
                NetworkInfo networkInfo = this.f6786c.getNetworkInfo(1);
                if (networkInfo != null) {
                    if (networkInfo.isConnected()) {
                        return true;
                    }
                }
                return false;
            } catch (RuntimeException e10) {
                Log.w(f6782u, "get WiFi NetworkInfo failed", e10);
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033 A[Catch: RuntimeException -> 0x0047, TryCatch #0 {RuntimeException -> 0x0047, blocks: (B:13:0x002b, B:15:0x0033, B:17:0x0037, B:19:0x003f), top: B:12:0x002b }] */
    /* renamed from: D */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2901D() {
        /*
            r7 = this;
            android.net.ConnectivityManager r0 = r7.f6786c
            r1 = 0
            if (r0 != 0) goto L7
        L5:
            r0 = r1
            goto L17
        L7:
            android.net.ConnectivityManager r0 = r7.f6786c     // Catch: java.lang.RuntimeException -> Le
            android.net.Network r0 = r0.getActiveNetwork()     // Catch: java.lang.RuntimeException -> Le
            goto L17
        Le:
            r0 = move-exception
            java.lang.String r2 = io.twoyi.hal.C1731e1.f6782u
            java.lang.String r3 = "getActiveNetwork failed"
            android.util.Log.w(r2, r3, r0)
            goto L5
        L17:
            android.net.NetworkCapabilities r2 = r7.m2913q(r0)
            r3 = 1
            if (r2 == 0) goto L25
            boolean r2 = r2.hasTransport(r3)
            if (r2 == 0) goto L25
            r1 = r0
        L25:
            if (r1 != 0) goto L53
            android.net.ConnectivityManager r0 = r7.f6786c
            if (r0 == 0) goto L53
            android.net.ConnectivityManager r0 = r7.f6786c     // Catch: java.lang.RuntimeException -> L47
            android.net.Network[] r0 = r0.getAllNetworks()     // Catch: java.lang.RuntimeException -> L47
            if (r0 == 0) goto L53
            int r2 = r0.length     // Catch: java.lang.RuntimeException -> L47
            r4 = 0
        L35:
            if (r4 >= r2) goto L53
            r5 = r0[r4]     // Catch: java.lang.RuntimeException -> L47
            android.net.NetworkCapabilities r6 = r7.m2913q(r5)     // Catch: java.lang.RuntimeException -> L47
            if (r6 == 0) goto L49
            boolean r6 = r6.hasTransport(r3)     // Catch: java.lang.RuntimeException -> L47
            if (r6 == 0) goto L49
            r1 = r5
            goto L53
        L47:
            r0 = move-exception
            goto L4c
        L49:
            int r4 = r4 + 1
            goto L35
        L4c:
            java.lang.String r2 = io.twoyi.hal.C1731e1.f6782u
            java.lang.String r3 = "enumerate networks failed"
            android.util.Log.w(r2, r3, r0)
        L53:
            r7.f6787d = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1731e1.m2901D():void");
    }

    /* renamed from: E */
    public final void m2902E() {
        NetworkRequest.Builder clearCapabilities;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.location.MODE_CHANGED");
        try {
            AbstractC2697l.m4574M(this.f6784a, this.f6800q, intentFilter, 2);
        } catch (RuntimeException e10) {
            Log.e(f6782u, "register WiFi receiver failed", e10);
        }
        if (this.f6786c != null) {
            try {
                clearCapabilities = new NetworkRequest.Builder().clearCapabilities();
                this.f6786c.registerNetworkCallback(clearCapabilities.addTransportType(1).build(), this.f6801r);
            } catch (RuntimeException e11) {
                Log.e(f6782u, "register WiFi network callback failed", e11);
            }
            try {
                this.f6786c.registerNetworkCallback(new NetworkRequest.Builder().addTransportType(0).addCapability(12).build(), this.f6802s);
            } catch (RuntimeException e12) {
                Log.e(f6782u, "register cellular network callback failed", e12);
            }
        }
    }

    /* renamed from: b */
    public final void m2903b(LocalSocket localSocket, int i) {
        synchronized (this.f6790g) {
            try {
                if (this.f6794k != i) {
                    LocalSocket localSocket2 = this.f6793j;
                    m2906g();
                    m2896e(localSocket2);
                    this.f6794k = i;
                }
                LocalSocket localSocket3 = this.f6793j;
                if (localSocket3 != localSocket) {
                    m2896e(localSocket3);
                }
                this.f6793j = localSocket;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00da A[Catch: RuntimeException -> 0x00c2, JSONException -> 0x00c5, TryCatch #3 {RuntimeException -> 0x00c2, JSONException -> 0x00c5, blocks: (B:29:0x0063, B:36:0x009b, B:40:0x00b6, B:41:0x00ca, B:43:0x00da, B:44:0x00e1, B:46:0x00e8, B:47:0x00ef, B:48:0x0103, B:50:0x010b, B:53:0x0125, B:54:0x0130, B:56:0x013d, B:60:0x0141, B:61:0x0158, B:65:0x00eb, B:66:0x00df, B:67:0x00c8), top: B:28:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e8 A[Catch: RuntimeException -> 0x00c2, JSONException -> 0x00c5, TryCatch #3 {RuntimeException -> 0x00c2, JSONException -> 0x00c5, blocks: (B:29:0x0063, B:36:0x009b, B:40:0x00b6, B:41:0x00ca, B:43:0x00da, B:44:0x00e1, B:46:0x00e8, B:47:0x00ef, B:48:0x0103, B:50:0x010b, B:53:0x0125, B:54:0x0130, B:56:0x013d, B:60:0x0141, B:61:0x0158, B:65:0x00eb, B:66:0x00df, B:67:0x00c8), top: B:28:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010b A[Catch: RuntimeException -> 0x00c2, JSONException -> 0x00c5, TryCatch #3 {RuntimeException -> 0x00c2, JSONException -> 0x00c5, blocks: (B:29:0x0063, B:36:0x009b, B:40:0x00b6, B:41:0x00ca, B:43:0x00da, B:44:0x00e1, B:46:0x00e8, B:47:0x00ef, B:48:0x0103, B:50:0x010b, B:53:0x0125, B:54:0x0130, B:56:0x013d, B:60:0x0141, B:61:0x0158, B:65:0x00eb, B:66:0x00df, B:67:0x00c8), top: B:28:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0130 A[EDGE_INSN: B:64:0x0130->B:54:0x0130 BREAK  A[LOOP:0: B:48:0x0103->B:63:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00eb A[Catch: RuntimeException -> 0x00c2, JSONException -> 0x00c5, TryCatch #3 {RuntimeException -> 0x00c2, JSONException -> 0x00c5, blocks: (B:29:0x0063, B:36:0x009b, B:40:0x00b6, B:41:0x00ca, B:43:0x00da, B:44:0x00e1, B:46:0x00e8, B:47:0x00ef, B:48:0x0103, B:50:0x010b, B:53:0x0125, B:54:0x0130, B:56:0x013d, B:60:0x0141, B:61:0x0158, B:65:0x00eb, B:66:0x00df, B:67:0x00c8), top: B:28:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00df A[Catch: RuntimeException -> 0x00c2, JSONException -> 0x00c5, TryCatch #3 {RuntimeException -> 0x00c2, JSONException -> 0x00c5, blocks: (B:29:0x0063, B:36:0x009b, B:40:0x00b6, B:41:0x00ca, B:43:0x00da, B:44:0x00e1, B:46:0x00e8, B:47:0x00ef, B:48:0x0103, B:50:0x010b, B:53:0x0125, B:54:0x0130, B:56:0x013d, B:60:0x0141, B:61:0x0158, B:65:0x00eb, B:66:0x00df, B:67:0x00c8), top: B:28:0x0063 }] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] m2904d() {
        /*
            Method dump skipped, instructions count: 353
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1731e1.m2904d():byte[]");
    }

    /* renamed from: f */
    public final void m2905f(LocalSocket localSocket, int i) {
        boolean z10;
        synchronized (this.f6790g) {
            try {
                if (this.f6793j == localSocket && this.f6794k == i) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (z10) {
                    m2906g();
                }
            } finally {
            }
        }
    }

    /* renamed from: g */
    public final void m2906g() {
        this.f6793j = null;
        this.f6794k = 0;
        this.f6792i = null;
        LocalSocket localSocket = this.f6791h;
        if (localSocket != null) {
            try {
                localSocket.close();
            } catch (IOException unused) {
            }
            this.f6791h = null;
        }
    }

    /* renamed from: j */
    public final NetworkInfo m2907j() {
        if (this.f6786c == null) {
            return null;
        }
        try {
            NetworkInfo activeNetworkInfo = this.f6786c.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return m2912p();
            }
            return activeNetworkInfo;
        } catch (RuntimeException e10) {
            Log.w(f6782u, "getActiveNetworkInfo failed", e10);
            if (!m2900A()) {
                return null;
            }
            return m2912p();
        }
    }

    /* renamed from: k */
    public final Network m2908k() {
        Network network = this.f6787d;
        NetworkCapabilities m2913q = m2913q(network);
        if (m2913q != null && m2913q.hasTransport(1)) {
            return network;
        }
        m2901D();
        return this.f6787d;
    }

    /* renamed from: l */
    public final WifiInfo m2909l() {
        if (this.f6785b == null) {
            return null;
        }
        synchronized (this.f6795l) {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                WifiInfo wifiInfo = this.f6796m;
                if (wifiInfo == null || (Build.VERSION.SDK_INT < 31 && elapsedRealtime - this.f6797n >= BinderC1809u.f7165a0)) {
                    try {
                        WifiInfo connectionInfo = this.f6785b.getConnectionInfo();
                        this.f6796m = connectionInfo;
                        this.f6797n = elapsedRealtime;
                        return connectionInfo;
                    } catch (RuntimeException e10) {
                        Log.w(f6782u, "getConnectionInfo failed", e10);
                        return null;
                    }
                }
                return wifiInfo;
            } finally {
            }
        }
    }

    /* renamed from: m */
    public final JSONArray m2910m() {
        String str;
        JSONArray jSONArray = new JSONArray();
        if (!m2900A()) {
            return jSONArray;
        }
        WifiInfo m2909l = m2909l();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("NID", 0);
            jSONObject.put("SAU", 0);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ST", 0);
            jSONObject2.put("NSDR", 0);
            jSONObject2.put("TDT", 0L);
            Object obj = JSONObject.NULL;
            jSONObject2.put("NSBSD", obj);
            jSONObject2.put("CCT", 0L);
            jSONObject2.put("HEC", true);
            jSONObject2.put("NR", false);
            jSONObject2.put("CC", obj);
            jSONObject.put("NSST", jSONObject2);
            jSONObject.put("SSD", m2899o(m2909l));
            if (m2909l != null) {
                str = m2898i(m2909l.getBSSID());
            } else {
                str = "02:00:00:00:00:00";
            }
            jSONObject.put("BSSD", str);
            jSONObject.put("PSK", obj);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < 4; i++) {
                jSONArray2.put(JSONObject.NULL);
            }
            jSONObject.put("WK", jSONArray2);
            jSONObject.put("WKIX", 0);
            jSONObject.put("PIRT", 0);
            jSONObject.put("HDSD", false);
            jSONObject.put("AKMM", m2895c(0));
            jSONObject.put("APTC", m2895c(new int[0]));
            jSONObject.put("AAAG", m2895c(new int[0]));
            jSONObject.put("APCP", m2895c(new int[0]));
            jSONObject.put("AGCP", m2895c(new int[0]));
            jSONArray.put(jSONObject);
            return jSONArray;
        } catch (JSONException e10) {
            Log.e(f6782u, "json error: " + e10);
            return jSONArray;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0040 A[Catch: JSONException -> 0x0045, TryCatch #0 {JSONException -> 0x0045, blocks: (B:12:0x0039, B:14:0x0040, B:15:0x004f, B:17:0x0056, B:19:0x005e, B:22:0x0068, B:24:0x006f, B:25:0x0073, B:27:0x007a, B:28:0x0081, B:32:0x008c, B:33:0x0096, B:35:0x009d, B:36:0x00a5, B:39:0x00ae, B:41:0x00b4, B:42:0x00c4, B:44:0x00cb, B:45:0x00cf), top: B:11:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0056 A[Catch: JSONException -> 0x0045, TryCatch #0 {JSONException -> 0x0045, blocks: (B:12:0x0039, B:14:0x0040, B:15:0x004f, B:17:0x0056, B:19:0x005e, B:22:0x0068, B:24:0x006f, B:25:0x0073, B:27:0x007a, B:28:0x0081, B:32:0x008c, B:33:0x0096, B:35:0x009d, B:36:0x00a5, B:39:0x00ae, B:41:0x00b4, B:42:0x00c4, B:44:0x00cb, B:45:0x00cf), top: B:11:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005e A[Catch: JSONException -> 0x0045, TryCatch #0 {JSONException -> 0x0045, blocks: (B:12:0x0039, B:14:0x0040, B:15:0x004f, B:17:0x0056, B:19:0x005e, B:22:0x0068, B:24:0x006f, B:25:0x0073, B:27:0x007a, B:28:0x0081, B:32:0x008c, B:33:0x0096, B:35:0x009d, B:36:0x00a5, B:39:0x00ae, B:41:0x00b4, B:42:0x00c4, B:44:0x00cb, B:45:0x00cf), top: B:11:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006f A[Catch: JSONException -> 0x0045, TryCatch #0 {JSONException -> 0x0045, blocks: (B:12:0x0039, B:14:0x0040, B:15:0x004f, B:17:0x0056, B:19:0x005e, B:22:0x0068, B:24:0x006f, B:25:0x0073, B:27:0x007a, B:28:0x0081, B:32:0x008c, B:33:0x0096, B:35:0x009d, B:36:0x00a5, B:39:0x00ae, B:41:0x00b4, B:42:0x00c4, B:44:0x00cb, B:45:0x00cf), top: B:11:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007a A[Catch: JSONException -> 0x0045, TryCatch #0 {JSONException -> 0x0045, blocks: (B:12:0x0039, B:14:0x0040, B:15:0x004f, B:17:0x0056, B:19:0x005e, B:22:0x0068, B:24:0x006f, B:25:0x0073, B:27:0x007a, B:28:0x0081, B:32:0x008c, B:33:0x0096, B:35:0x009d, B:36:0x00a5, B:39:0x00ae, B:41:0x00b4, B:42:0x00c4, B:44:0x00cb, B:45:0x00cf), top: B:11:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009d A[Catch: JSONException -> 0x0045, TryCatch #0 {JSONException -> 0x0045, blocks: (B:12:0x0039, B:14:0x0040, B:15:0x004f, B:17:0x0056, B:19:0x005e, B:22:0x0068, B:24:0x006f, B:25:0x0073, B:27:0x007a, B:28:0x0081, B:32:0x008c, B:33:0x0096, B:35:0x009d, B:36:0x00a5, B:39:0x00ae, B:41:0x00b4, B:42:0x00c4, B:44:0x00cb, B:45:0x00cf), top: B:11:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00cb A[Catch: JSONException -> 0x0045, TryCatch #0 {JSONException -> 0x0045, blocks: (B:12:0x0039, B:14:0x0040, B:15:0x004f, B:17:0x0056, B:19:0x005e, B:22:0x0068, B:24:0x006f, B:25:0x0073, B:27:0x007a, B:28:0x0081, B:32:0x008c, B:33:0x0096, B:35:0x009d, B:36:0x00a5, B:39:0x00ae, B:41:0x00b4, B:42:0x00c4, B:44:0x00cb, B:45:0x00cf), top: B:11:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0038  */
    /* renamed from: n */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONObject m2911n() {
        /*
            r9 = this;
            boolean r0 = r9.m2900A()
            android.net.wifi.WifiInfo r1 = r9.m2909l()
            android.net.Network r2 = r9.m2908k()
            android.net.NetworkCapabilities r2 = r9.m2913q(r2)
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 31
            if (r3 < r4) goto L27
            if (r2 == 0) goto L27
            android.net.TransportInfo r3 = androidx.lifecycle.AbstractC0293j0.m728j(r2)
            boolean r3 = r3 instanceof android.net.wifi.WifiInfo
            if (r3 == 0) goto L27
            android.net.TransportInfo r2 = androidx.lifecycle.AbstractC0293j0.m728j(r2)
            android.net.wifi.WifiInfo r2 = (android.net.wifi.WifiInfo) r2
            goto L2b
        L27:
            android.net.wifi.WifiInfo r2 = r9.m2909l()
        L2b:
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r4 = "MNID"
            r5 = 0
            r6 = -1
            if (r0 == 0) goto L38
            r7 = r5
            goto L39
        L38:
            r7 = r6
        L39:
            r3.put(r4, r7)     // Catch: org.json.JSONException -> L45
            java.lang.String r4 = "MRSI"
            if (r2 == 0) goto L48
            int r7 = r2.getRssi()     // Catch: org.json.JSONException -> L45
            goto L4f
        L45:
            r0 = move-exception
            goto Ld3
        L48:
            if (r0 == 0) goto L4d
            r7 = -60
            goto L4f
        L4d:
            r7 = -127(0xffffffffffffff81, float:NaN)
        L4f:
            r3.put(r4, r7)     // Catch: org.json.JSONException -> L45
            java.lang.String r4 = "MLSP"
            if (r2 == 0) goto L5b
            int r7 = r2.getLinkSpeed()     // Catch: org.json.JSONException -> L45
            goto L5c
        L5b:
            r7 = r6
        L5c:
            if (r1 == 0) goto L63
            int r8 = r1.getLinkSpeed()     // Catch: org.json.JSONException -> L45
            goto L64
        L63:
            r8 = r6
        L64:
            if (r7 <= 0) goto L67
            goto L68
        L67:
            r7 = r8
        L68:
            r3.put(r4, r7)     // Catch: org.json.JSONException -> L45
            java.lang.String r4 = "MIADR"
            if (r2 == 0) goto L73
            int r5 = r2.getIpAddress()     // Catch: org.json.JSONException -> L45
        L73:
            r3.put(r4, r5)     // Catch: org.json.JSONException -> L45
            java.lang.String r4 = "MWSSD"
            if (r0 == 0) goto L7f
            java.lang.String r5 = m2899o(r1)     // Catch: org.json.JSONException -> L45
            goto L81
        L7f:
            java.lang.String r5 = "\"WiFi\""
        L81:
            r3.put(r4, r5)     // Catch: org.json.JSONException -> L45
            java.lang.String r4 = "MBSSD"
            java.lang.String r5 = "02:00:00:00:00:00"
            if (r0 == 0) goto L95
            if (r1 == 0) goto L95
            java.lang.String r1 = r1.getBSSID()     // Catch: org.json.JSONException -> L45
            java.lang.String r1 = m2898i(r1)     // Catch: org.json.JSONException -> L45
            goto L96
        L95:
            r1 = r5
        L96:
            r3.put(r4, r1)     // Catch: org.json.JSONException -> L45
            java.lang.String r1 = "MMADR"
            if (r2 == 0) goto La5
            java.lang.String r4 = r2.getMacAddress()     // Catch: org.json.JSONException -> L45
            java.lang.String r5 = m2898i(r4)     // Catch: org.json.JSONException -> L45
        La5:
            r3.put(r1, r5)     // Catch: org.json.JSONException -> L45
            java.lang.String r1 = "MSSA"
            if (r0 == 0) goto Lbd
            if (r2 == 0) goto Lbd
            android.net.wifi.SupplicantState r4 = r2.getSupplicantState()     // Catch: org.json.JSONException -> L45
            if (r4 == 0) goto Lbd
            android.net.wifi.SupplicantState r0 = r2.getSupplicantState()     // Catch: org.json.JSONException -> L45
            java.lang.String r0 = r0.name()     // Catch: org.json.JSONException -> L45
            goto Lc4
        Lbd:
            if (r0 == 0) goto Lc2
            java.lang.String r0 = "COMPLETED"
            goto Lc4
        Lc2:
            java.lang.String r0 = "DISCONNECTED"
        Lc4:
            r3.put(r1, r0)     // Catch: org.json.JSONException -> L45
            java.lang.String r0 = "MFRE"
            if (r2 == 0) goto Lcf
            int r6 = r2.getFrequency()     // Catch: org.json.JSONException -> L45
        Lcf:
            r3.put(r0, r6)     // Catch: org.json.JSONException -> L45
            return r3
        Ld3:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "json error: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = io.twoyi.hal.C1731e1.f6782u
            android.util.Log.e(r1, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.hal.C1731e1.m2911n():org.json.JSONObject");
    }

    /* renamed from: p */
    public final NetworkInfo m2912p() {
        String str;
        NetworkInfo.DetailedState detailedState;
        boolean m2900A = m2900A();
        NetworkInfo m489h = AbstractC0179o.m489h();
        if (m2900A) {
            str = m2890G(m2899o(m2909l()));
        } else {
            str = null;
        }
        if (m2900A) {
            detailedState = NetworkInfo.DetailedState.CONNECTED;
        } else {
            detailedState = NetworkInfo.DetailedState.DISCONNECTED;
        }
        m489h.setDetailedState(detailedState, null, str);
        return m489h;
    }

    /* renamed from: q */
    public final NetworkCapabilities m2913q(Network network) {
        if (network != null && this.f6786c != null) {
            try {
                return this.f6786c.getNetworkCapabilities(network);
            } catch (RuntimeException e10) {
                Log.w(f6782u, "getNetworkCapabilities failed", e10);
            }
        }
        return null;
    }

    /* renamed from: r */
    public final List m2914r() {
        List unmodifiableList;
        if (!AbstractC1589y.m2661g("android.permission.ACCESS_FINE_LOCATION")) {
            return Collections.EMPTY_LIST;
        }
        if (this.f6785b == null) {
            return Collections.EMPTY_LIST;
        }
        synchronized (this.f6795l) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            List list = this.f6798o;
            if (list != null && elapsedRealtime - this.f6799p < BinderC1809u.f7165a0) {
                return list;
            }
            try {
                List<ScanResult> scanResults = this.f6785b.getScanResults();
                if (scanResults == null) {
                    unmodifiableList = Collections.EMPTY_LIST;
                } else {
                    unmodifiableList = Collections.unmodifiableList(new ArrayList(scanResults));
                }
                this.f6798o = unmodifiableList;
                this.f6799p = elapsedRealtime;
                return unmodifiableList;
            } catch (RuntimeException e10) {
                Log.w(f6782u, "getScanResults failed", e10);
                return Collections.EMPTY_LIST;
            }
        }
    }

    /* renamed from: s */
    public final JSONArray m2915s() {
        List<ScanResult> m2914r = m2914r();
        JSONArray jSONArray = new JSONArray();
        for (ScanResult scanResult : m2914r) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("SSID", scanResult.SSID);
                jSONObject.put("BSSID", m2898i(scanResult.BSSID));
                jSONObject.put("frequency", scanResult.frequency);
                jSONObject.put("level", scanResult.level);
                jSONObject.put("timestamp", scanResult.timestamp);
                jSONObject.put("capabilities", scanResult.capabilities);
                jSONArray.put(jSONObject);
            } catch (JSONException e10) {
                Log.e(f6782u, "json error: " + e10);
            }
        }
        return jSONArray;
    }

    /* renamed from: t */
    public final JSONArray m2916t() {
        boolean is60GHzBandSupported;
        boolean is6GHzBandSupported;
        boolean is24GHzBandSupported;
        JSONArray jSONArray = new JSONArray();
        if (this.f6785b != null) {
            try {
                int i = Build.VERSION.SDK_INT;
                if (i >= 31) {
                    is24GHzBandSupported = this.f6785b.is24GHzBandSupported();
                    if (is24GHzBandSupported) {
                        jSONArray.put("2.4_GHZ");
                    }
                }
                if (this.f6785b.is5GHzBandSupported()) {
                    jSONArray.put("5_GHZ");
                }
                if (i >= 30) {
                    is6GHzBandSupported = this.f6785b.is6GHzBandSupported();
                    if (is6GHzBandSupported) {
                        jSONArray.put("6_GHZ");
                    }
                }
                if (i >= 31) {
                    is60GHzBandSupported = this.f6785b.is60GHzBandSupported();
                    if (is60GHzBandSupported) {
                        jSONArray.put("60_GHZ");
                    }
                }
            } catch (LinkageError e10) {
                e = e10;
                Log.w(f6782u, "query supported WiFi bands failed", e);
                return jSONArray;
            } catch (RuntimeException e11) {
                e = e11;
                Log.w(f6782u, "query supported WiFi bands failed", e);
                return jSONArray;
            }
        }
        return jSONArray;
    }

    /* renamed from: u */
    public final int m2917u() {
        if (this.f6785b == null) {
            return 4;
        }
        try {
            return this.f6785b.getWifiState();
        } catch (RuntimeException e10) {
            Log.w(f6782u, "getWifiState failed", e10);
            return 4;
        }
    }

    /* renamed from: v */
    public final void m2918v() {
        synchronized (this.f6790g) {
            OutputStream outputStream = this.f6792i;
            if (outputStream == null) {
                return;
            }
            try {
                outputStream.write(4);
                m2891H(this.f6792i, !this.f6789f.isEmpty() ? 1 : 0);
            } catch (IOException e10) {
                m2921y(e10);
            }
        }
    }

    /* renamed from: w */
    public final void m2919w(NetworkInfo networkInfo) {
        synchronized (this.f6790g) {
            OutputStream outputStream = this.f6792i;
            if (outputStream == null) {
                return;
            }
            try {
                outputStream.write(2);
                m2892I(this.f6792i, networkInfo);
            } catch (IOException e10) {
                m2921y(e10);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: x */
    public final void m2920x(LocalSocket localSocket) {
        InputStream inputStream;
        OutputStream outputStream;
        boolean z10;
        int i;
        int i10;
        int i11;
        String str = f6782u;
        Log.d(str, "HAL handleWifi");
        try {
            int m2888C = m2888C(localSocket.getInputStream());
            if (m2888C < 0 && m2888C != Integer.MIN_VALUE) {
                int i12 = -m2888C;
                synchronized (this.f6790g) {
                    try {
                        if (this.f6794k != i12) {
                            LocalSocket localSocket2 = this.f6793j;
                            m2906g();
                            m2896e(localSocket2);
                            this.f6794k = i12;
                        }
                        LocalSocket localSocket3 = this.f6791h;
                        if (localSocket3 != localSocket) {
                            m2896e(localSocket3);
                        }
                        this.f6791h = localSocket;
                        try {
                            OutputStream outputStream2 = localSocket.getOutputStream();
                            this.f6792i = outputStream2;
                            outputStream2.write(0);
                            m2891H(this.f6792i, m2917u());
                            this.f6792i.write(2);
                            m2892I(this.f6792i, m2912p());
                            this.f6792i.write(4);
                            m2891H(this.f6792i, 1 ^ (this.f6789f.isEmpty() ? 1 : 0));
                            Log.d(str, "HAL wifi event stream attached, session=" + i12);
                        } catch (IOException e10) {
                            m2921y(e10);
                        }
                    } finally {
                    }
                }
                return;
            }
            try {
                try {
                    inputStream = localSocket.getInputStream();
                    outputStream = localSocket.getOutputStream();
                } catch (Throwable th) {
                    m2905f(localSocket, m2888C);
                    m2896e(localSocket);
                    throw th;
                }
            } catch (IOException e11) {
                Log.e(f6782u, "HAL handleWifi failed: " + e11);
            }
            if (m2888C > 0) {
                m2903b(localSocket, m2888C);
                while (true) {
                    int read = inputStream.read();
                    if (read > -1) {
                        switch (read) {
                            case AbstractC4695a.f17215a /* 0 */:
                                if (m2888C(inputStream) == 1) {
                                    z10 = true;
                                } else {
                                    z10 = false;
                                }
                                int m2917u = m2917u();
                                if (!z10 ? !(m2917u == 1 || m2917u == 0) : !(m2917u == 3 || m2917u == 2)) {
                                    i = 0;
                                    m2891H(outputStream, i);
                                    break;
                                }
                                i = 1;
                                m2891H(outputStream, i);
                                break;
                            case 1:
                                byte[] bytes = m2915s().toString().getBytes(StandardCharsets.UTF_8);
                                m2891H(outputStream, bytes.length);
                                outputStream.write(bytes);
                                break;
                            case 2:
                                m2891H(outputStream, m2917u());
                                break;
                            case 3:
                                byte[] bytes2 = m2910m().toString().getBytes(StandardCharsets.UTF_8);
                                m2891H(outputStream, bytes2.length);
                                outputStream.write(bytes2);
                                break;
                            case 4:
                                byte[] bytes3 = m2911n().toString().getBytes(StandardCharsets.UTF_8);
                                m2891H(outputStream, bytes3.length);
                                outputStream.write(bytes3);
                                break;
                            case AbstractC4540b.f16788j /* 5 */:
                                m2892I(outputStream, m2907j());
                                break;
                            case AbstractC4540b.f16786h /* 6 */:
                                m2892I(outputStream, m2908k());
                                break;
                            case 7:
                                Network network = null;
                                if (this.f6786c != null) {
                                    try {
                                        network = this.f6786c.getActiveNetwork();
                                    } catch (RuntimeException e12) {
                                        Log.w(f6782u, "getActiveNetwork failed", e12);
                                    }
                                }
                                m2892I(outputStream, network);
                                break;
                            case 8:
                                int m2888C2 = m2888C(inputStream);
                                m2888C(inputStream);
                                if (m2888C2 == 0 && m2900A()) {
                                    i10 = 1;
                                } else {
                                    i10 = 0;
                                }
                                m2891H(outputStream, i10);
                                break;
                            case 9:
                                m2891H(outputStream, 0);
                                break;
                            case 10:
                                m2891H(outputStream, m2900A() ? 1 : 0);
                                break;
                            case 11:
                                if (AbstractC1589y.m2661g("android.permission.ACCESS_FINE_LOCATION") && this.f6785b != null) {
                                    try {
                                        i11 = this.f6785b.startScan();
                                    } catch (RuntimeException e13) {
                                        Log.w(f6782u, "startScan failed", e13);
                                    }
                                    m2891H(outputStream, i11);
                                    break;
                                }
                                i11 = 0;
                                m2891H(outputStream, i11);
                                break;
                            case AbstractC1474y0.f5961f /* 12 */:
                                int m2888C3 = m2888C(inputStream);
                                if (m2888C3 > 0) {
                                    byte[] bArr = new byte[m2888C3];
                                    C1761t0.m2980d(inputStream, bArr, m2888C3);
                                    new String(bArr, StandardCharsets.UTF_8);
                                }
                                m2891H(outputStream, -1);
                                break;
                            case 13:
                                m2888C(inputStream);
                                m2891H(outputStream, 0);
                                break;
                            case 14:
                                m2888C(inputStream);
                                m2891H(outputStream, 0);
                                break;
                            case AbstractC4540b.f16789k /* 15 */:
                                m2893J(outputStream, m2904d());
                                break;
                            default:
                                Log.w(f6782u, "Unknown WiFi command: " + read);
                                break;
                        }
                    } else {
                        m2905f(localSocket, m2888C);
                        m2896e(localSocket);
                        return;
                    }
                }
            } else {
                throw new IOException("Invalid WiFi forward session id");
            }
        } catch (IOException e14) {
            Log.e(f6782u, "HAL handleWifi failed: " + e14);
            m2896e(localSocket);
        }
    }

    /* renamed from: y */
    public final void m2921y(IOException iOException) {
        Log.e(f6782u, "HAL wifi server failed: " + iOException);
        LocalSocket localSocket = this.f6793j;
        m2906g();
        m2896e(localSocket);
    }

    /* renamed from: z */
    public final void m2922z() {
        synchronized (this.f6795l) {
            this.f6796m = null;
        }
    }
}

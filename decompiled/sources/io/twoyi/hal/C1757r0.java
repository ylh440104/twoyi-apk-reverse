package io.twoyi.hal;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.LocalSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;
import io.twoyi.AbstractServiceC1716h0;
import io.twoyi.utils.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import p089h8.AbstractC1589y;
import p089h8.EnumC1588x;
import p089h8.InterfaceC1586v;
import p247w0.AbstractC4176k;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.r0 */
/* loaded from: classes.dex */
public final class C1757r0 {

    /* renamed from: t */
    public static final C1757r0 f6888t = new C1757r0();

    /* renamed from: a */
    public Context f6889a;

    /* renamed from: b */
    public LocationManager f6890b;

    /* renamed from: c */
    public C1755q0 f6891c;

    /* renamed from: d */
    public C1753p0 f6892d;

    /* renamed from: e */
    public volatile int f6893e;

    /* renamed from: f */
    public boolean f6894f;

    /* renamed from: g */
    public boolean f6895g;

    /* renamed from: h */
    public boolean f6896h;

    /* renamed from: i */
    public String f6897i;

    /* renamed from: j */
    public OutputStream f6898j;

    /* renamed from: k */
    public long f6899k;

    /* renamed from: m */
    public long f6901m;

    /* renamed from: o */
    public HandlerThread f6903o;

    /* renamed from: p */
    public Handler f6904p;

    /* renamed from: q */
    public C1772z f6905q;

    /* renamed from: r */
    public OutputStream f6906r;

    /* renamed from: l */
    public long f6900l = -1;

    /* renamed from: n */
    public final Object f6902n = new Object();

    /* renamed from: s */
    public final RunnableC1747m0 f6907s = new RunnableC1747m0(this, 0);

    /* renamed from: a */
    public static void m2960a(C1757r0 c1757r0, OutputStream outputStream) {
        if (c1757r0.f6906r != outputStream) {
            return;
        }
        c1757r0.m2970k();
        c1757r0.f6894f = false;
        c1757r0.m2963c();
        c1757r0.f6906r = null;
    }

    /* renamed from: b */
    public static String m2961b(List list) {
        if (list.contains("gps")) {
            return "gps";
        }
        if (list.contains("network")) {
            return "network";
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!"passive".equals(str)) {
                return str;
            }
        }
        return null;
    }

    /* renamed from: i */
    public static String m2962i(double d6, boolean z10) {
        int i;
        String str;
        if (Double.isFinite(d6)) {
            double abs = Math.abs(d6);
            if (z10) {
                i = 90;
            } else {
                i = 180;
            }
            if (abs <= i) {
                int floor = (int) Math.floor(abs);
                double round = Math.round(((abs - floor) * 60.0d) * 10000.0d) / 10000.0d;
                if (round >= 60.0d) {
                    floor++;
                    round = 0.0d;
                }
                if (floor <= i) {
                    if (z10) {
                        str = "%02d%07.4f";
                    } else {
                        str = "%03d%07.4f";
                    }
                    return String.format(Locale.US, str, Integer.valueOf(floor), Double.valueOf(round));
                }
                throw new IllegalArgumentException("coordinate rounds out of range");
            }
            throw new IllegalArgumentException("coordinate out of range");
        }
        throw new IllegalArgumentException("non-finite coordinate");
    }

    /* renamed from: c */
    public final void m2963c() {
        String str;
        if (!this.f6895g && !this.f6896h && this.f6891c == null && this.f6892d == null) {
            return;
        }
        StringBuilder sb = new StringBuilder("close location");
        if (this.f6897i == null) {
            str = "";
        } else {
            str = ": " + this.f6897i;
        }
        sb.append(str);
        Log.d("r0", sb.toString());
        C1755q0 c1755q0 = this.f6891c;
        C1753p0 c1753p0 = this.f6892d;
        this.f6891c = null;
        this.f6892d = null;
        this.f6896h = false;
        LocationManager locationManager = this.f6890b;
        if (locationManager != null) {
            if (c1755q0 != null) {
                try {
                    locationManager.removeUpdates(c1755q0);
                } catch (SecurityException e10) {
                    Log.w("r0", "cannot remove location updates", e10);
                } catch (RuntimeException e11) {
                    Log.w("r0", "removing location updates failed", e11);
                }
            }
            if (c1753p0 != null) {
                try {
                    this.f6890b.unregisterGnssStatusCallback(c1753p0);
                } catch (SecurityException e12) {
                    Log.w("r0", "cannot remove GNSS status callback", e12);
                } catch (RuntimeException e13) {
                    Log.w("r0", "removing GNSS status callback failed", e13);
                }
            }
        }
        this.f6895g = false;
        this.f6897i = null;
        this.f6898j = null;
        this.f6900l = -1L;
        this.f6893e = 0;
        AbstractServiceC1716h0.m2864c(this.f6889a, false, null, null);
    }

    /* renamed from: d */
    public final String m2964d(Location location) {
        char c6;
        char c10;
        String str;
        long time = location.getTime();
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        boolean hasAltitude = location.hasAltitude();
        double altitude = location.getAltitude();
        boolean hasAccuracy = location.hasAccuracy();
        float accuracy = location.getAccuracy();
        int i = this.f6893e;
        if (time <= 0) {
            time = System.currentTimeMillis();
        }
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss.SSS", locale);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(new Date(time));
        if (latitude < 0.0d) {
            c6 = 'S';
        } else {
            c6 = 'N';
        }
        if (longitude < 0.0d) {
            c10 = 'W';
        } else {
            c10 = 'E';
        }
        String m2962i = m2962i(latitude, true);
        String m2962i2 = m2962i(longitude, false);
        int max = Math.max(0, Math.min(99, i));
        String str2 = "";
        if (!hasAccuracy || !Float.isFinite(accuracy) || accuracy <= AbstractC4176k.f15279f) {
            str = "";
        } else {
            str = String.format(locale, "%.1f", Float.valueOf(Math.min(200.0f, Math.max(0.1f, accuracy / 5.0f))));
        }
        if (hasAltitude && Double.isFinite(altitude)) {
            str2 = String.format(locale, "%.1f", Double.valueOf(altitude));
        }
        String format2 = String.format(locale, "GPGGA,%s,%s,%c,%s,%c,1,%02d,%s,%s,M,,M,,", format, m2962i, Character.valueOf(c6), m2962i2, Character.valueOf(c10), Integer.valueOf(max), str, str2);
        int i10 = 0;
        for (int i11 = 0; i11 < format2.length(); i11++) {
            i10 ^= format2.charAt(i11);
        }
        return String.format(Locale.US, "$%s*%02X\r\n", format2, Integer.valueOf(i10));
    }

    /* renamed from: e */
    public final void m2965e(OutputStream outputStream, long j3) {
        if (m2972m(outputStream, j3) && !this.f6895g && !this.f6896h) {
            if (this.f6890b == null) {
                Log.e("r0", "location service unavailable");
            } else if (!AbstractC1589y.m2661g("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
                Log.w("r0", "location permission not granted");
            } else {
                this.f6896h = true;
                AbstractServiceC1716h0.m2864c(this.f6889a, true, this.f6904p, new C1743k0(this, outputStream, j3));
            }
        }
    }

    /* renamed from: f */
    public final byte[] m2966f(Location location) {
        try {
            String m2964d = m2964d(location);
            try {
                byte[] m2945e = AbstractC1742k.m2945e(m2964d);
                Log.d("r0", "GPGGA format: ".concat(m2964d));
                return m2945e;
            } catch (IllegalArgumentException e10) {
                Log.w("r0", "cannot encode host NMEA", e10);
                return null;
            }
        } catch (IllegalArgumentException e11) {
            Log.w("r0", "discard invalid host location", e11);
            return null;
        }
    }

    /* renamed from: g */
    public final void m2967g(OutputStream outputStream, long j3, boolean z10) {
        if (!m2972m(outputStream, j3)) {
            return;
        }
        m2970k();
        if (z10) {
            this.f6894f = false;
        }
        m2963c();
    }

    /* renamed from: h */
    public final Location m2968h(List list) {
        Iterator it = list.iterator();
        Location location = null;
        while (it.hasNext()) {
            Location lastKnownLocation = this.f6890b.getLastKnownLocation((String) it.next());
            if (lastKnownLocation != null && (location == null || lastKnownLocation.getAccuracy() < location.getAccuracy())) {
                location = lastKnownLocation;
            }
        }
        return location;
    }

    /* renamed from: j */
    public final void m2969j(LocalSocket localSocket) {
        Log.d("r0", "HAL handleGps");
        final int i = 0;
        final OutputStream outputStream = null;
        try {
            try {
                InputStream inputStream = localSocket.getInputStream();
                final OutputStream outputStream2 = localSocket.getOutputStream();
                localSocket.setSoTimeout(5000);
                int m2981e = C1761t0.m2981e(inputStream);
                final int i10 = 2;
                if (m2981e != 2) {
                    Log.w("r0", "unsupported GPS wire version: " + m2981e);
                    if (outputStream2 != null) {
                        this.f6904p.post(new Runnable(this) { // from class: io.twoyi.hal.l0

                            /* renamed from: j */
                            public final /* synthetic */ C1757r0 f6857j;

                            {
                                this.f6857j = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                byte[] m2966f;
                                switch (i) {
                                    case AbstractC4695a.f17215a /* 0 */:
                                        C1757r0.m2960a(this.f6857j, outputStream2);
                                        return;
                                    case 1:
                                        C1757r0 c1757r0 = this.f6857j;
                                        OutputStream outputStream3 = outputStream2;
                                        if (c1757r0.f6906r != outputStream3) {
                                            c1757r0.m2970k();
                                            c1757r0.f6894f = false;
                                            c1757r0.m2963c();
                                            c1757r0.f6906r = outputStream3;
                                            c1757r0.f6893e = 0;
                                            c1757r0.f6901m = 0L;
                                            if (c1757r0.f6890b != null && AbstractC1589y.m2661g("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
                                                try {
                                                    Location m2968h = c1757r0.m2968h(c1757r0.f6890b.getProviders(true));
                                                    if (m2968h != null && Math.abs(System.currentTimeMillis() - m2968h.getTime()) <= 43200000 && (m2966f = c1757r0.m2966f(m2968h)) != null) {
                                                        try {
                                                            long elapsedRealtimeNanos = m2968h.getElapsedRealtimeNanos();
                                                            if (elapsedRealtimeNanos <= 0) {
                                                                elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                                                            }
                                                            byte[] m2944d = AbstractC1742k.m2944d(1, 1L, elapsedRealtimeNanos, m2966f);
                                                            synchronized (c1757r0.f6902n) {
                                                                outputStream3.write(m2944d);
                                                                c1757r0.f6901m = 1L;
                                                            }
                                                            Log.d("r0", "seed guest location from " + m2968h.getProvider());
                                                            return;
                                                        } catch (IOException e10) {
                                                            Log.d("r0", "GPS seed not delivered: " + e10.getMessage());
                                                            return;
                                                        } catch (IllegalArgumentException e11) {
                                                            Log.w("r0", "cannot encode GPS seed frame", e11);
                                                            return;
                                                        }
                                                    }
                                                    return;
                                                } catch (RuntimeException e12) {
                                                    Log.w("r0", "cannot read host last location for guest seed", e12);
                                                    return;
                                                }
                                            }
                                            return;
                                        }
                                        return;
                                    case 2:
                                        final C1757r0 c1757r02 = this.f6857j;
                                        final OutputStream outputStream4 = outputStream2;
                                        if (c1757r02.f6906r != outputStream4) {
                                            Log.d("r0", "ignore OPEN from stale GPS connection");
                                            return;
                                        }
                                        if (c1757r02.f6894f) {
                                            Log.d("r0", "ignore duplicate GPS OPEN");
                                            return;
                                        }
                                        c1757r02.m2970k();
                                        c1757r02.f6894f = true;
                                        final long j3 = c1757r02.f6899k;
                                        AbstractC1589y.m2664j(new InterfaceC1586v() { // from class: io.twoyi.hal.n0
                                            @Override // p089h8.InterfaceC1586v
                                            /* renamed from: a */
                                            public final void mo2654a(final EnumC1588x enumC1588x) {
                                                final C1757r0 c1757r03 = C1757r0.this;
                                                Handler handler = c1757r03.f6904p;
                                                final OutputStream outputStream5 = outputStream4;
                                                final long j10 = j3;
                                                handler.post(new Runnable() { // from class: io.twoyi.hal.o0
                                                    @Override // java.lang.Runnable
                                                    public final void run() {
                                                        C1757r0 c1757r04 = C1757r0.this;
                                                        OutputStream outputStream6 = outputStream5;
                                                        long j11 = j10;
                                                        if (!c1757r04.m2972m(outputStream6, j11)) {
                                                            return;
                                                        }
                                                        EnumC1588x enumC1588x2 = EnumC1588x.f6343i;
                                                        EnumC1588x enumC1588x3 = enumC1588x;
                                                        if (enumC1588x3 == enumC1588x2) {
                                                            c1757r04.m2965e(outputStream6, j11);
                                                            return;
                                                        }
                                                        Log.w("r0", "location permission request finished: " + enumC1588x3);
                                                    }
                                                });
                                            }
                                        }, true, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
                                        return;
                                    default:
                                        C1757r0 c1757r03 = this.f6857j;
                                        if (c1757r03.f6906r != outputStream2) {
                                            Log.d("r0", "ignore CLOSE from stale GPS connection");
                                            return;
                                        }
                                        if (!c1757r03.f6894f && !c1757r03.f6895g) {
                                            Log.d("r0", "ignore duplicate GPS CLOSE");
                                            return;
                                        }
                                        c1757r03.m2970k();
                                        c1757r03.f6894f = false;
                                        c1757r03.m2963c();
                                        return;
                                }
                            }
                        });
                    }
                    IOUtils.closeSilently(localSocket);
                    return;
                }
                C1761t0.m2982f(outputStream2, 2);
                localSocket.setSoTimeout(0);
                final int i11 = 1;
                this.f6904p.post(new Runnable(this) { // from class: io.twoyi.hal.l0

                    /* renamed from: j */
                    public final /* synthetic */ C1757r0 f6857j;

                    {
                        this.f6857j = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        byte[] m2966f;
                        switch (i11) {
                            case AbstractC4695a.f17215a /* 0 */:
                                C1757r0.m2960a(this.f6857j, outputStream2);
                                return;
                            case 1:
                                C1757r0 c1757r0 = this.f6857j;
                                OutputStream outputStream3 = outputStream2;
                                if (c1757r0.f6906r != outputStream3) {
                                    c1757r0.m2970k();
                                    c1757r0.f6894f = false;
                                    c1757r0.m2963c();
                                    c1757r0.f6906r = outputStream3;
                                    c1757r0.f6893e = 0;
                                    c1757r0.f6901m = 0L;
                                    if (c1757r0.f6890b != null && AbstractC1589y.m2661g("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
                                        try {
                                            Location m2968h = c1757r0.m2968h(c1757r0.f6890b.getProviders(true));
                                            if (m2968h != null && Math.abs(System.currentTimeMillis() - m2968h.getTime()) <= 43200000 && (m2966f = c1757r0.m2966f(m2968h)) != null) {
                                                try {
                                                    long elapsedRealtimeNanos = m2968h.getElapsedRealtimeNanos();
                                                    if (elapsedRealtimeNanos <= 0) {
                                                        elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                                                    }
                                                    byte[] m2944d = AbstractC1742k.m2944d(1, 1L, elapsedRealtimeNanos, m2966f);
                                                    synchronized (c1757r0.f6902n) {
                                                        outputStream3.write(m2944d);
                                                        c1757r0.f6901m = 1L;
                                                    }
                                                    Log.d("r0", "seed guest location from " + m2968h.getProvider());
                                                    return;
                                                } catch (IOException e10) {
                                                    Log.d("r0", "GPS seed not delivered: " + e10.getMessage());
                                                    return;
                                                } catch (IllegalArgumentException e11) {
                                                    Log.w("r0", "cannot encode GPS seed frame", e11);
                                                    return;
                                                }
                                            }
                                            return;
                                        } catch (RuntimeException e12) {
                                            Log.w("r0", "cannot read host last location for guest seed", e12);
                                            return;
                                        }
                                    }
                                    return;
                                }
                                return;
                            case 2:
                                final C1757r0 c1757r02 = this.f6857j;
                                final OutputStream outputStream4 = outputStream2;
                                if (c1757r02.f6906r != outputStream4) {
                                    Log.d("r0", "ignore OPEN from stale GPS connection");
                                    return;
                                }
                                if (c1757r02.f6894f) {
                                    Log.d("r0", "ignore duplicate GPS OPEN");
                                    return;
                                }
                                c1757r02.m2970k();
                                c1757r02.f6894f = true;
                                final long j3 = c1757r02.f6899k;
                                AbstractC1589y.m2664j(new InterfaceC1586v() { // from class: io.twoyi.hal.n0
                                    @Override // p089h8.InterfaceC1586v
                                    /* renamed from: a */
                                    public final void mo2654a(final EnumC1588x enumC1588x) {
                                        final C1757r0 c1757r03 = C1757r0.this;
                                        Handler handler = c1757r03.f6904p;
                                        final OutputStream outputStream5 = outputStream4;
                                        final long j10 = j3;
                                        handler.post(new Runnable() { // from class: io.twoyi.hal.o0
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                C1757r0 c1757r04 = C1757r0.this;
                                                OutputStream outputStream6 = outputStream5;
                                                long j11 = j10;
                                                if (!c1757r04.m2972m(outputStream6, j11)) {
                                                    return;
                                                }
                                                EnumC1588x enumC1588x2 = EnumC1588x.f6343i;
                                                EnumC1588x enumC1588x3 = enumC1588x;
                                                if (enumC1588x3 == enumC1588x2) {
                                                    c1757r04.m2965e(outputStream6, j11);
                                                    return;
                                                }
                                                Log.w("r0", "location permission request finished: " + enumC1588x3);
                                            }
                                        });
                                    }
                                }, true, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
                                return;
                            default:
                                C1757r0 c1757r03 = this.f6857j;
                                if (c1757r03.f6906r != outputStream2) {
                                    Log.d("r0", "ignore CLOSE from stale GPS connection");
                                    return;
                                }
                                if (!c1757r03.f6894f && !c1757r03.f6895g) {
                                    Log.d("r0", "ignore duplicate GPS CLOSE");
                                    return;
                                }
                                c1757r03.m2970k();
                                c1757r03.f6894f = false;
                                c1757r03.m2963c();
                                return;
                        }
                    }
                });
                while (true) {
                    int read = inputStream.read();
                    if (read != -1) {
                        if (read == 1) {
                            this.f6904p.post(new Runnable(this) { // from class: io.twoyi.hal.l0

                                /* renamed from: j */
                                public final /* synthetic */ C1757r0 f6857j;

                                {
                                    this.f6857j = this;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    byte[] m2966f;
                                    switch (i10) {
                                        case AbstractC4695a.f17215a /* 0 */:
                                            C1757r0.m2960a(this.f6857j, outputStream2);
                                            return;
                                        case 1:
                                            C1757r0 c1757r0 = this.f6857j;
                                            OutputStream outputStream3 = outputStream2;
                                            if (c1757r0.f6906r != outputStream3) {
                                                c1757r0.m2970k();
                                                c1757r0.f6894f = false;
                                                c1757r0.m2963c();
                                                c1757r0.f6906r = outputStream3;
                                                c1757r0.f6893e = 0;
                                                c1757r0.f6901m = 0L;
                                                if (c1757r0.f6890b != null && AbstractC1589y.m2661g("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
                                                    try {
                                                        Location m2968h = c1757r0.m2968h(c1757r0.f6890b.getProviders(true));
                                                        if (m2968h != null && Math.abs(System.currentTimeMillis() - m2968h.getTime()) <= 43200000 && (m2966f = c1757r0.m2966f(m2968h)) != null) {
                                                            try {
                                                                long elapsedRealtimeNanos = m2968h.getElapsedRealtimeNanos();
                                                                if (elapsedRealtimeNanos <= 0) {
                                                                    elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                                                                }
                                                                byte[] m2944d = AbstractC1742k.m2944d(1, 1L, elapsedRealtimeNanos, m2966f);
                                                                synchronized (c1757r0.f6902n) {
                                                                    outputStream3.write(m2944d);
                                                                    c1757r0.f6901m = 1L;
                                                                }
                                                                Log.d("r0", "seed guest location from " + m2968h.getProvider());
                                                                return;
                                                            } catch (IOException e10) {
                                                                Log.d("r0", "GPS seed not delivered: " + e10.getMessage());
                                                                return;
                                                            } catch (IllegalArgumentException e11) {
                                                                Log.w("r0", "cannot encode GPS seed frame", e11);
                                                                return;
                                                            }
                                                        }
                                                        return;
                                                    } catch (RuntimeException e12) {
                                                        Log.w("r0", "cannot read host last location for guest seed", e12);
                                                        return;
                                                    }
                                                }
                                                return;
                                            }
                                            return;
                                        case 2:
                                            final C1757r0 c1757r02 = this.f6857j;
                                            final OutputStream outputStream4 = outputStream2;
                                            if (c1757r02.f6906r != outputStream4) {
                                                Log.d("r0", "ignore OPEN from stale GPS connection");
                                                return;
                                            }
                                            if (c1757r02.f6894f) {
                                                Log.d("r0", "ignore duplicate GPS OPEN");
                                                return;
                                            }
                                            c1757r02.m2970k();
                                            c1757r02.f6894f = true;
                                            final long j3 = c1757r02.f6899k;
                                            AbstractC1589y.m2664j(new InterfaceC1586v() { // from class: io.twoyi.hal.n0
                                                @Override // p089h8.InterfaceC1586v
                                                /* renamed from: a */
                                                public final void mo2654a(final EnumC1588x enumC1588x) {
                                                    final C1757r0 c1757r03 = C1757r0.this;
                                                    Handler handler = c1757r03.f6904p;
                                                    final OutputStream outputStream5 = outputStream4;
                                                    final long j10 = j3;
                                                    handler.post(new Runnable() { // from class: io.twoyi.hal.o0
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            C1757r0 c1757r04 = C1757r0.this;
                                                            OutputStream outputStream6 = outputStream5;
                                                            long j11 = j10;
                                                            if (!c1757r04.m2972m(outputStream6, j11)) {
                                                                return;
                                                            }
                                                            EnumC1588x enumC1588x2 = EnumC1588x.f6343i;
                                                            EnumC1588x enumC1588x3 = enumC1588x;
                                                            if (enumC1588x3 == enumC1588x2) {
                                                                c1757r04.m2965e(outputStream6, j11);
                                                                return;
                                                            }
                                                            Log.w("r0", "location permission request finished: " + enumC1588x3);
                                                        }
                                                    });
                                                }
                                            }, true, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
                                            return;
                                        default:
                                            C1757r0 c1757r03 = this.f6857j;
                                            if (c1757r03.f6906r != outputStream2) {
                                                Log.d("r0", "ignore CLOSE from stale GPS connection");
                                                return;
                                            }
                                            if (!c1757r03.f6894f && !c1757r03.f6895g) {
                                                Log.d("r0", "ignore duplicate GPS CLOSE");
                                                return;
                                            }
                                            c1757r03.m2970k();
                                            c1757r03.f6894f = false;
                                            c1757r03.m2963c();
                                            return;
                                    }
                                }
                            });
                        } else if (read == 0) {
                            final int i12 = 3;
                            this.f6904p.post(new Runnable(this) { // from class: io.twoyi.hal.l0

                                /* renamed from: j */
                                public final /* synthetic */ C1757r0 f6857j;

                                {
                                    this.f6857j = this;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    byte[] m2966f;
                                    switch (i12) {
                                        case AbstractC4695a.f17215a /* 0 */:
                                            C1757r0.m2960a(this.f6857j, outputStream2);
                                            return;
                                        case 1:
                                            C1757r0 c1757r0 = this.f6857j;
                                            OutputStream outputStream3 = outputStream2;
                                            if (c1757r0.f6906r != outputStream3) {
                                                c1757r0.m2970k();
                                                c1757r0.f6894f = false;
                                                c1757r0.m2963c();
                                                c1757r0.f6906r = outputStream3;
                                                c1757r0.f6893e = 0;
                                                c1757r0.f6901m = 0L;
                                                if (c1757r0.f6890b != null && AbstractC1589y.m2661g("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
                                                    try {
                                                        Location m2968h = c1757r0.m2968h(c1757r0.f6890b.getProviders(true));
                                                        if (m2968h != null && Math.abs(System.currentTimeMillis() - m2968h.getTime()) <= 43200000 && (m2966f = c1757r0.m2966f(m2968h)) != null) {
                                                            try {
                                                                long elapsedRealtimeNanos = m2968h.getElapsedRealtimeNanos();
                                                                if (elapsedRealtimeNanos <= 0) {
                                                                    elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                                                                }
                                                                byte[] m2944d = AbstractC1742k.m2944d(1, 1L, elapsedRealtimeNanos, m2966f);
                                                                synchronized (c1757r0.f6902n) {
                                                                    outputStream3.write(m2944d);
                                                                    c1757r0.f6901m = 1L;
                                                                }
                                                                Log.d("r0", "seed guest location from " + m2968h.getProvider());
                                                                return;
                                                            } catch (IOException e10) {
                                                                Log.d("r0", "GPS seed not delivered: " + e10.getMessage());
                                                                return;
                                                            } catch (IllegalArgumentException e11) {
                                                                Log.w("r0", "cannot encode GPS seed frame", e11);
                                                                return;
                                                            }
                                                        }
                                                        return;
                                                    } catch (RuntimeException e12) {
                                                        Log.w("r0", "cannot read host last location for guest seed", e12);
                                                        return;
                                                    }
                                                }
                                                return;
                                            }
                                            return;
                                        case 2:
                                            final C1757r0 c1757r02 = this.f6857j;
                                            final OutputStream outputStream4 = outputStream2;
                                            if (c1757r02.f6906r != outputStream4) {
                                                Log.d("r0", "ignore OPEN from stale GPS connection");
                                                return;
                                            }
                                            if (c1757r02.f6894f) {
                                                Log.d("r0", "ignore duplicate GPS OPEN");
                                                return;
                                            }
                                            c1757r02.m2970k();
                                            c1757r02.f6894f = true;
                                            final long j3 = c1757r02.f6899k;
                                            AbstractC1589y.m2664j(new InterfaceC1586v() { // from class: io.twoyi.hal.n0
                                                @Override // p089h8.InterfaceC1586v
                                                /* renamed from: a */
                                                public final void mo2654a(final EnumC1588x enumC1588x) {
                                                    final C1757r0 c1757r03 = C1757r0.this;
                                                    Handler handler = c1757r03.f6904p;
                                                    final OutputStream outputStream5 = outputStream4;
                                                    final long j10 = j3;
                                                    handler.post(new Runnable() { // from class: io.twoyi.hal.o0
                                                        @Override // java.lang.Runnable
                                                        public final void run() {
                                                            C1757r0 c1757r04 = C1757r0.this;
                                                            OutputStream outputStream6 = outputStream5;
                                                            long j11 = j10;
                                                            if (!c1757r04.m2972m(outputStream6, j11)) {
                                                                return;
                                                            }
                                                            EnumC1588x enumC1588x2 = EnumC1588x.f6343i;
                                                            EnumC1588x enumC1588x3 = enumC1588x;
                                                            if (enumC1588x3 == enumC1588x2) {
                                                                c1757r04.m2965e(outputStream6, j11);
                                                                return;
                                                            }
                                                            Log.w("r0", "location permission request finished: " + enumC1588x3);
                                                        }
                                                    });
                                                }
                                            }, true, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
                                            return;
                                        default:
                                            C1757r0 c1757r03 = this.f6857j;
                                            if (c1757r03.f6906r != outputStream2) {
                                                Log.d("r0", "ignore CLOSE from stale GPS connection");
                                                return;
                                            }
                                            if (!c1757r03.f6894f && !c1757r03.f6895g) {
                                                Log.d("r0", "ignore duplicate GPS CLOSE");
                                                return;
                                            }
                                            c1757r03.m2970k();
                                            c1757r03.f6894f = false;
                                            c1757r03.m2963c();
                                            return;
                                    }
                                }
                            });
                        } else {
                            Log.w("r0", "unknown GPS command: " + read);
                            break;
                        }
                    } else {
                        break;
                    }
                }
                Log.d("r0", "GPS connection reached EOF");
                this.f6904p.post(new Runnable(this) { // from class: io.twoyi.hal.l0

                    /* renamed from: j */
                    public final /* synthetic */ C1757r0 f6857j;

                    {
                        this.f6857j = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        byte[] m2966f;
                        switch (i) {
                            case AbstractC4695a.f17215a /* 0 */:
                                C1757r0.m2960a(this.f6857j, outputStream2);
                                return;
                            case 1:
                                C1757r0 c1757r0 = this.f6857j;
                                OutputStream outputStream3 = outputStream2;
                                if (c1757r0.f6906r != outputStream3) {
                                    c1757r0.m2970k();
                                    c1757r0.f6894f = false;
                                    c1757r0.m2963c();
                                    c1757r0.f6906r = outputStream3;
                                    c1757r0.f6893e = 0;
                                    c1757r0.f6901m = 0L;
                                    if (c1757r0.f6890b != null && AbstractC1589y.m2661g("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
                                        try {
                                            Location m2968h = c1757r0.m2968h(c1757r0.f6890b.getProviders(true));
                                            if (m2968h != null && Math.abs(System.currentTimeMillis() - m2968h.getTime()) <= 43200000 && (m2966f = c1757r0.m2966f(m2968h)) != null) {
                                                try {
                                                    long elapsedRealtimeNanos = m2968h.getElapsedRealtimeNanos();
                                                    if (elapsedRealtimeNanos <= 0) {
                                                        elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                                                    }
                                                    byte[] m2944d = AbstractC1742k.m2944d(1, 1L, elapsedRealtimeNanos, m2966f);
                                                    synchronized (c1757r0.f6902n) {
                                                        outputStream3.write(m2944d);
                                                        c1757r0.f6901m = 1L;
                                                    }
                                                    Log.d("r0", "seed guest location from " + m2968h.getProvider());
                                                    return;
                                                } catch (IOException e10) {
                                                    Log.d("r0", "GPS seed not delivered: " + e10.getMessage());
                                                    return;
                                                } catch (IllegalArgumentException e11) {
                                                    Log.w("r0", "cannot encode GPS seed frame", e11);
                                                    return;
                                                }
                                            }
                                            return;
                                        } catch (RuntimeException e12) {
                                            Log.w("r0", "cannot read host last location for guest seed", e12);
                                            return;
                                        }
                                    }
                                    return;
                                }
                                return;
                            case 2:
                                final C1757r0 c1757r02 = this.f6857j;
                                final OutputStream outputStream4 = outputStream2;
                                if (c1757r02.f6906r != outputStream4) {
                                    Log.d("r0", "ignore OPEN from stale GPS connection");
                                    return;
                                }
                                if (c1757r02.f6894f) {
                                    Log.d("r0", "ignore duplicate GPS OPEN");
                                    return;
                                }
                                c1757r02.m2970k();
                                c1757r02.f6894f = true;
                                final long j3 = c1757r02.f6899k;
                                AbstractC1589y.m2664j(new InterfaceC1586v() { // from class: io.twoyi.hal.n0
                                    @Override // p089h8.InterfaceC1586v
                                    /* renamed from: a */
                                    public final void mo2654a(final EnumC1588x enumC1588x) {
                                        final C1757r0 c1757r03 = C1757r0.this;
                                        Handler handler = c1757r03.f6904p;
                                        final OutputStream outputStream5 = outputStream4;
                                        final long j10 = j3;
                                        handler.post(new Runnable() { // from class: io.twoyi.hal.o0
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                C1757r0 c1757r04 = C1757r0.this;
                                                OutputStream outputStream6 = outputStream5;
                                                long j11 = j10;
                                                if (!c1757r04.m2972m(outputStream6, j11)) {
                                                    return;
                                                }
                                                EnumC1588x enumC1588x2 = EnumC1588x.f6343i;
                                                EnumC1588x enumC1588x3 = enumC1588x;
                                                if (enumC1588x3 == enumC1588x2) {
                                                    c1757r04.m2965e(outputStream6, j11);
                                                    return;
                                                }
                                                Log.w("r0", "location permission request finished: " + enumC1588x3);
                                            }
                                        });
                                    }
                                }, true, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
                                return;
                            default:
                                C1757r0 c1757r03 = this.f6857j;
                                if (c1757r03.f6906r != outputStream2) {
                                    Log.d("r0", "ignore CLOSE from stale GPS connection");
                                    return;
                                }
                                if (!c1757r03.f6894f && !c1757r03.f6895g) {
                                    Log.d("r0", "ignore duplicate GPS CLOSE");
                                    return;
                                }
                                c1757r03.m2970k();
                                c1757r03.f6894f = false;
                                c1757r03.m2963c();
                                return;
                        }
                    }
                });
                IOUtils.closeSilently(localSocket);
            } catch (IOException e10) {
                Log.d("r0", "GPS connection closed", e10);
                if (0 != 0) {
                    this.f6904p.post(new Runnable(this) { // from class: io.twoyi.hal.l0

                        /* renamed from: j */
                        public final /* synthetic */ C1757r0 f6857j;

                        {
                            this.f6857j = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            byte[] m2966f;
                            switch (i) {
                                case AbstractC4695a.f17215a /* 0 */:
                                    C1757r0.m2960a(this.f6857j, outputStream);
                                    return;
                                case 1:
                                    C1757r0 c1757r0 = this.f6857j;
                                    OutputStream outputStream3 = outputStream;
                                    if (c1757r0.f6906r != outputStream3) {
                                        c1757r0.m2970k();
                                        c1757r0.f6894f = false;
                                        c1757r0.m2963c();
                                        c1757r0.f6906r = outputStream3;
                                        c1757r0.f6893e = 0;
                                        c1757r0.f6901m = 0L;
                                        if (c1757r0.f6890b != null && AbstractC1589y.m2661g("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
                                            try {
                                                Location m2968h = c1757r0.m2968h(c1757r0.f6890b.getProviders(true));
                                                if (m2968h != null && Math.abs(System.currentTimeMillis() - m2968h.getTime()) <= 43200000 && (m2966f = c1757r0.m2966f(m2968h)) != null) {
                                                    try {
                                                        long elapsedRealtimeNanos = m2968h.getElapsedRealtimeNanos();
                                                        if (elapsedRealtimeNanos <= 0) {
                                                            elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                                                        }
                                                        byte[] m2944d = AbstractC1742k.m2944d(1, 1L, elapsedRealtimeNanos, m2966f);
                                                        synchronized (c1757r0.f6902n) {
                                                            outputStream3.write(m2944d);
                                                            c1757r0.f6901m = 1L;
                                                        }
                                                        Log.d("r0", "seed guest location from " + m2968h.getProvider());
                                                        return;
                                                    } catch (IOException e102) {
                                                        Log.d("r0", "GPS seed not delivered: " + e102.getMessage());
                                                        return;
                                                    } catch (IllegalArgumentException e11) {
                                                        Log.w("r0", "cannot encode GPS seed frame", e11);
                                                        return;
                                                    }
                                                }
                                                return;
                                            } catch (RuntimeException e12) {
                                                Log.w("r0", "cannot read host last location for guest seed", e12);
                                                return;
                                            }
                                        }
                                        return;
                                    }
                                    return;
                                case 2:
                                    final C1757r0 c1757r02 = this.f6857j;
                                    final OutputStream outputStream4 = outputStream;
                                    if (c1757r02.f6906r != outputStream4) {
                                        Log.d("r0", "ignore OPEN from stale GPS connection");
                                        return;
                                    }
                                    if (c1757r02.f6894f) {
                                        Log.d("r0", "ignore duplicate GPS OPEN");
                                        return;
                                    }
                                    c1757r02.m2970k();
                                    c1757r02.f6894f = true;
                                    final long j3 = c1757r02.f6899k;
                                    AbstractC1589y.m2664j(new InterfaceC1586v() { // from class: io.twoyi.hal.n0
                                        @Override // p089h8.InterfaceC1586v
                                        /* renamed from: a */
                                        public final void mo2654a(final EnumC1588x enumC1588x) {
                                            final C1757r0 c1757r03 = C1757r0.this;
                                            Handler handler = c1757r03.f6904p;
                                            final OutputStream outputStream5 = outputStream4;
                                            final long j10 = j3;
                                            handler.post(new Runnable() { // from class: io.twoyi.hal.o0
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    C1757r0 c1757r04 = C1757r0.this;
                                                    OutputStream outputStream6 = outputStream5;
                                                    long j11 = j10;
                                                    if (!c1757r04.m2972m(outputStream6, j11)) {
                                                        return;
                                                    }
                                                    EnumC1588x enumC1588x2 = EnumC1588x.f6343i;
                                                    EnumC1588x enumC1588x3 = enumC1588x;
                                                    if (enumC1588x3 == enumC1588x2) {
                                                        c1757r04.m2965e(outputStream6, j11);
                                                        return;
                                                    }
                                                    Log.w("r0", "location permission request finished: " + enumC1588x3);
                                                }
                                            });
                                        }
                                    }, true, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
                                    return;
                                default:
                                    C1757r0 c1757r03 = this.f6857j;
                                    if (c1757r03.f6906r != outputStream) {
                                        Log.d("r0", "ignore CLOSE from stale GPS connection");
                                        return;
                                    }
                                    if (!c1757r03.f6894f && !c1757r03.f6895g) {
                                        Log.d("r0", "ignore duplicate GPS CLOSE");
                                        return;
                                    }
                                    c1757r03.m2970k();
                                    c1757r03.f6894f = false;
                                    c1757r03.m2963c();
                                    return;
                            }
                        }
                    });
                }
                IOUtils.closeSilently(localSocket);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                this.f6904p.post(new Runnable(this) { // from class: io.twoyi.hal.l0

                    /* renamed from: j */
                    public final /* synthetic */ C1757r0 f6857j;

                    {
                        this.f6857j = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        byte[] m2966f;
                        switch (i) {
                            case AbstractC4695a.f17215a /* 0 */:
                                C1757r0.m2960a(this.f6857j, outputStream);
                                return;
                            case 1:
                                C1757r0 c1757r0 = this.f6857j;
                                OutputStream outputStream3 = outputStream;
                                if (c1757r0.f6906r != outputStream3) {
                                    c1757r0.m2970k();
                                    c1757r0.f6894f = false;
                                    c1757r0.m2963c();
                                    c1757r0.f6906r = outputStream3;
                                    c1757r0.f6893e = 0;
                                    c1757r0.f6901m = 0L;
                                    if (c1757r0.f6890b != null && AbstractC1589y.m2661g("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
                                        try {
                                            Location m2968h = c1757r0.m2968h(c1757r0.f6890b.getProviders(true));
                                            if (m2968h != null && Math.abs(System.currentTimeMillis() - m2968h.getTime()) <= 43200000 && (m2966f = c1757r0.m2966f(m2968h)) != null) {
                                                try {
                                                    long elapsedRealtimeNanos = m2968h.getElapsedRealtimeNanos();
                                                    if (elapsedRealtimeNanos <= 0) {
                                                        elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
                                                    }
                                                    byte[] m2944d = AbstractC1742k.m2944d(1, 1L, elapsedRealtimeNanos, m2966f);
                                                    synchronized (c1757r0.f6902n) {
                                                        outputStream3.write(m2944d);
                                                        c1757r0.f6901m = 1L;
                                                    }
                                                    Log.d("r0", "seed guest location from " + m2968h.getProvider());
                                                    return;
                                                } catch (IOException e102) {
                                                    Log.d("r0", "GPS seed not delivered: " + e102.getMessage());
                                                    return;
                                                } catch (IllegalArgumentException e11) {
                                                    Log.w("r0", "cannot encode GPS seed frame", e11);
                                                    return;
                                                }
                                            }
                                            return;
                                        } catch (RuntimeException e12) {
                                            Log.w("r0", "cannot read host last location for guest seed", e12);
                                            return;
                                        }
                                    }
                                    return;
                                }
                                return;
                            case 2:
                                final C1757r0 c1757r02 = this.f6857j;
                                final OutputStream outputStream4 = outputStream;
                                if (c1757r02.f6906r != outputStream4) {
                                    Log.d("r0", "ignore OPEN from stale GPS connection");
                                    return;
                                }
                                if (c1757r02.f6894f) {
                                    Log.d("r0", "ignore duplicate GPS OPEN");
                                    return;
                                }
                                c1757r02.m2970k();
                                c1757r02.f6894f = true;
                                final long j3 = c1757r02.f6899k;
                                AbstractC1589y.m2664j(new InterfaceC1586v() { // from class: io.twoyi.hal.n0
                                    @Override // p089h8.InterfaceC1586v
                                    /* renamed from: a */
                                    public final void mo2654a(final EnumC1588x enumC1588x) {
                                        final C1757r0 c1757r03 = C1757r0.this;
                                        Handler handler = c1757r03.f6904p;
                                        final OutputStream outputStream5 = outputStream4;
                                        final long j10 = j3;
                                        handler.post(new Runnable() { // from class: io.twoyi.hal.o0
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                C1757r0 c1757r04 = C1757r0.this;
                                                OutputStream outputStream6 = outputStream5;
                                                long j11 = j10;
                                                if (!c1757r04.m2972m(outputStream6, j11)) {
                                                    return;
                                                }
                                                EnumC1588x enumC1588x2 = EnumC1588x.f6343i;
                                                EnumC1588x enumC1588x3 = enumC1588x;
                                                if (enumC1588x3 == enumC1588x2) {
                                                    c1757r04.m2965e(outputStream6, j11);
                                                    return;
                                                }
                                                Log.w("r0", "location permission request finished: " + enumC1588x3);
                                            }
                                        });
                                    }
                                }, true, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION");
                                return;
                            default:
                                C1757r0 c1757r03 = this.f6857j;
                                if (c1757r03.f6906r != outputStream) {
                                    Log.d("r0", "ignore CLOSE from stale GPS connection");
                                    return;
                                }
                                if (!c1757r03.f6894f && !c1757r03.f6895g) {
                                    Log.d("r0", "ignore duplicate GPS CLOSE");
                                    return;
                                }
                                c1757r03.m2970k();
                                c1757r03.f6894f = false;
                                c1757r03.m2963c();
                                return;
                        }
                    }
                });
            }
            IOUtils.closeSilently(localSocket);
            throw th;
        }
    }

    /* renamed from: k */
    public final void m2970k() {
        this.f6899k++;
    }

    /* renamed from: l */
    public final boolean m2971l(OutputStream outputStream, long j3) {
        if (m2972m(outputStream, j3) && this.f6895g && this.f6898j == outputStream && this.f6900l == j3) {
            return true;
        }
        return false;
    }

    /* renamed from: m */
    public final boolean m2972m(OutputStream outputStream, long j3) {
        if (this.f6894f && this.f6906r == outputStream && this.f6899k == j3) {
            return true;
        }
        return false;
    }

    /* renamed from: n */
    public final void m2973n() {
        OutputStream outputStream;
        if (this.f6894f && !this.f6895g && (outputStream = this.f6906r) != null) {
            m2965e(outputStream, this.f6899k);
        }
    }

    /* renamed from: o */
    public final void m2974o(OutputStream outputStream, long j3) {
        if (m2971l(outputStream, j3) && this.f6892d == null) {
            C1753p0 c1753p0 = new C1753p0(this, outputStream, j3);
            this.f6892d = c1753p0;
            try {
                if (!this.f6890b.registerGnssStatusCallback(c1753p0, this.f6904p)) {
                    Log.w("r0", "GNSS status callback registration rejected");
                    if (this.f6892d == c1753p0) {
                        this.f6892d = null;
                    }
                    m2977r(Collections.EMPTY_LIST, outputStream, j3);
                    return;
                }
                m2977r(Collections.EMPTY_LIST, outputStream, j3);
            } catch (SecurityException e10) {
                Log.w("r0", "cannot register GNSS status callback", e10);
                if (this.f6892d == c1753p0) {
                    this.f6892d = null;
                }
                m2977r(Collections.EMPTY_LIST, outputStream, j3);
            } catch (RuntimeException e11) {
                Log.w("r0", "GNSS status callback registration failed", e11);
                if (this.f6892d == c1753p0) {
                    this.f6892d = null;
                }
                m2977r(Collections.EMPTY_LIST, outputStream, j3);
            }
        }
    }

    /* renamed from: p */
    public final void m2975p(Location location, OutputStream outputStream, long j3) {
        byte[] m2966f;
        if (m2971l(outputStream, j3) && location != null && (m2966f = m2966f(location)) != null) {
            long elapsedRealtimeNanos = location.getElapsedRealtimeNanos();
            if (elapsedRealtimeNanos <= 0) {
                elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            }
            m2976q(1, elapsedRealtimeNanos, m2966f, outputStream, j3);
        }
    }

    /* renamed from: q */
    public final void m2976q(int i, long j3, byte[] bArr, OutputStream outputStream, long j10) {
        long j11;
        if (!m2971l(outputStream, j10)) {
            return;
        }
        long j12 = this.f6901m + 1;
        if (j12 > 4294967295L) {
            j11 = 1;
        } else {
            j11 = j12;
        }
        try {
            byte[] m2944d = AbstractC1742k.m2944d(i, j11, j3, bArr);
            long j13 = j11;
            try {
                synchronized (this.f6902n) {
                    try {
                        if (!m2971l(outputStream, j10)) {
                            return;
                        }
                        outputStream.write(m2944d);
                        this.f6901m = j13;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (IOException e10) {
                Log.w("r0", "writing GPS frame to guest failed", e10);
                m2967g(outputStream, j10, true);
            }
        } catch (IllegalArgumentException e11) {
            Log.w("r0", "cannot encode GPS frame", e11);
        }
    }

    /* renamed from: r */
    public final void m2977r(List list, OutputStream outputStream, long j3) {
        try {
            m2976q(2, SystemClock.elapsedRealtimeNanos(), AbstractC1742k.m2946f(list), outputStream, j3);
        } catch (IllegalArgumentException e10) {
            Log.w("r0", "cannot encode host satellite snapshot", e10);
        }
    }
}

package io.twoyi.hal;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import p007a6.RunnableC0077b;
import p009a8.C0127d2;
import p042d8.RunnableC0865d;
import p052e5.HandlerC0954a;
import p081h0.AbstractC1474y0;
import p089h8.AbstractC1589y;
import p278z.AbstractC4540b;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.t0 */
/* loaded from: classes.dex */
public final class C1761t0 {

    /* renamed from: g */
    public static final C1761t0 f6925g = new C1761t0();

    /* renamed from: h */
    public static final ExecutorService f6926h = Executors.newCachedThreadPool();

    /* renamed from: c */
    public volatile boolean f6929c;

    /* renamed from: f */
    public volatile Context f6932f;

    /* renamed from: a */
    public final AtomicBoolean f6927a = new AtomicBoolean(false);

    /* renamed from: b */
    public final CountDownLatch f6928b = new CountDownLatch(1);

    /* renamed from: d */
    public final Object[] f6930d = new Object[11];

    /* renamed from: e */
    public final boolean[] f6931e = new boolean[11];

    public C1761t0() {
        for (int i = 0; i < 11; i++) {
            this.f6930d[i] = new Object();
        }
    }

    /* renamed from: b */
    public static void m2978b(Context context) {
        C1761t0 c1761t0 = f6925g;
        if (!c1761t0.f6927a.compareAndSet(false, true)) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        c1761t0.f6932f = context;
        f6926h.execute(new RunnableC0865d(3));
    }

    /* JADX WARN: Type inference failed for: r4v44, types: [a8.d2, java.lang.Object] */
    /* renamed from: c */
    public static void m2979c(Context context, int i) {
        switch (i) {
            case AbstractC4695a.f17215a /* 0 */:
                C1770y c1770y = C1770y.f7003e;
                c1770y.getClass();
                c1770y.f7005b = new WeakReference(context);
                return;
            case 1:
                C1725c1 c1725c1 = C1725c1.f6763c;
                synchronized (c1725c1.f6764a) {
                    try {
                        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
                        if (vibrator != null && vibrator.hasVibrator()) {
                            c1725c1.f6765b = vibrator;
                            return;
                        }
                        Log.w("c1", "host vibrator is unavailable");
                        c1725c1.f6765b = null;
                        return;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            case 2:
                C1763u0 c1763u0 = C1763u0.f6946d;
                c1763u0.getClass();
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    c1763u0.f6947a = context;
                    return;
                }
                throw new IllegalArgumentException("context is null");
            case 3:
                C1718a0.f6723c.f6725a = context;
                new C0127d2(24);
                return;
            case 4:
                C1757r0 c1757r0 = C1757r0.f6888t;
                c1757r0.getClass();
                Context applicationContext2 = context.getApplicationContext();
                if (applicationContext2 != null) {
                    context = applicationContext2;
                }
                c1757r0.f6889a = context;
                c1757r0.f6890b = (LocationManager) context.getSystemService("location");
                HandlerThread handlerThread = new HandlerThread("twoyi_gps_thread", 10);
                c1757r0.f6903o = handlerThread;
                handlerThread.start();
                c1757r0.f6904p = new Handler(c1757r0.f6903o.getLooper());
                AbstractC1589y.m2655a("android.permission.ACCESS_FINE_LOCATION", c1757r0.f6907s);
                c1757r0.f6905q = new C1772z(2, c1757r0);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.location.PROVIDERS_CHANGED");
                intentFilter.addAction("android.location.MODE_CHANGED");
                if (Build.VERSION.SDK_INT >= 33) {
                    context.registerReceiver(c1757r0.f6905q, intentFilter, 2);
                    return;
                } else {
                    context.registerReceiver(c1757r0.f6905q, intentFilter);
                    return;
                }
            case AbstractC4540b.f16788j /* 5 */:
                C1721b0.f6732d.f6733a = context;
                return;
            case AbstractC4540b.f16786h /* 6 */:
                C1731e1 c1731e1 = C1731e1.f6781t;
                synchronized (c1731e1) {
                    if (c1731e1.f6784a != null) {
                        return;
                    }
                    Context applicationContext3 = context.getApplicationContext();
                    if (applicationContext3 != null) {
                        context = applicationContext3;
                    }
                    c1731e1.f6784a = context;
                    c1731e1.f6785b = (WifiManager) c1731e1.f6784a.getSystemService("wifi");
                    c1731e1.f6786c = (ConnectivityManager) c1731e1.f6784a.getSystemService("connectivity");
                    AbstractC1589y.m2655a("android.permission.ACCESS_FINE_LOCATION", new RunnableC0077b(8, c1731e1));
                    c1731e1.m2902E();
                    c1731e1.m2901D();
                    return;
                }
            case 7:
                C1769x0 c1769x0 = C1769x0.f6996e;
                c1769x0.f6998b = context;
                HandlerThread handlerThread2 = new HandlerThread("twoyi_ril_thread", 10);
                c1769x0.f6999c = handlerThread2;
                handlerThread2.start();
                c1769x0.f7000d = new Handler(((HandlerThread) c1769x0.f6999c).getLooper());
                return;
            case 8:
                C1722b1 c1722b1 = C1722b1.f6736h;
                c1722b1.f6737a = context;
                HandlerThread handlerThread3 = new HandlerThread("twoyi_telephony_thread", 10);
                c1722b1.f6739c = handlerThread3;
                handlerThread3.start();
                c1722b1.f6740d = new HandlerC0954a(c1722b1, c1722b1.f6739c.getLooper(), 1);
                return;
            case 9:
                C1719a1 c1719a1 = C1719a1.f6728e;
                c1719a1.f6729a = context;
                c1719a1.f6730b = (SensorManager) context.getSystemService("sensor");
                HandlerThread handlerThread4 = new HandlerThread("twoyi_sensor_thread", 10);
                handlerThread4.start();
                c1719a1.f6731c = new Handler(handlerThread4.getLooper());
                if (Build.VERSION.SDK_INT >= 29 && c1719a1.m2872c(19) == null && ((Context) c1719a1.f6729a).checkSelfPermission("android.permission.ACTIVITY_RECOGNITION") != 0) {
                    Log.i("a1", "step counter unavailable: ACTIVITY_RECOGNITION not granted");
                    return;
                }
                return;
            case 10:
                C1735g0 c1735g0 = C1735g0.f6817i;
                synchronized (c1735g0) {
                    if (c1735g0.f6824g != null) {
                        return;
                    }
                    if (context != null) {
                        Context applicationContext4 = context.getApplicationContext();
                        if (applicationContext4 != null) {
                            context = applicationContext4;
                        }
                        Object systemService = context.getSystemService("camera");
                        if (systemService instanceof CameraManager) {
                            c1735g0.f6822e = context;
                            c1735g0.f6823f = (CameraManager) systemService;
                            CameraManager cameraManager = c1735g0.f6823f;
                            ?? obj = new Object();
                            if (cameraManager != null) {
                                obj.f460i = cameraManager;
                                c1735g0.f6824g = obj;
                                c1735g0.f6825h = new Handler(Looper.getMainLooper());
                                return;
                            }
                            throw new IllegalArgumentException("CameraManager is required");
                        }
                        throw new IllegalStateException("CameraManager is unavailable");
                    }
                    throw new IllegalArgumentException("Camera HAL context is required");
                }
            default:
                return;
        }
    }

    /* renamed from: d */
    public static void m2980d(InputStream inputStream, byte[] bArr, int i) {
        if (i >= 0) {
            int i10 = 0;
            while (i10 < i) {
                int read = inputStream.read(bArr, i10, i - i10);
                if (read >= 0) {
                    i10 += read;
                } else {
                    throw new EOFException();
                }
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    /* renamed from: e */
    public static int m2981e(InputStream inputStream) {
        byte[] bArr = new byte[4];
        m2980d(inputStream, bArr, 4);
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    /* renamed from: f */
    public static void m2982f(OutputStream outputStream, int i) {
        outputStream.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(i).array());
    }

    /* renamed from: a */
    public final boolean m2983a(int i) {
        int i10;
        switch (i) {
            case AbstractC4695a.f17215a /* 0 */:
            case 1:
            case 10:
                i10 = 0;
                break;
            case 2:
            case 7:
            case 9:
            default:
                i10 = -1;
                break;
            case 3:
                i10 = 1;
                break;
            case 4:
                i10 = 2;
                break;
            case AbstractC4540b.f16788j /* 5 */:
            case AbstractC4540b.f16786h /* 6 */:
                i10 = 10;
                break;
            case 8:
                i10 = 4;
                break;
            case 11:
                i10 = 5;
                break;
            case AbstractC1474y0.f5961f /* 12 */:
                i10 = 6;
                break;
            case 13:
                i10 = 7;
                break;
            case 14:
                i10 = 8;
                break;
            case AbstractC4540b.f16789k /* 15 */:
                i10 = 9;
                break;
        }
        if (i10 < 0) {
            return true;
        }
        Context context = this.f6932f;
        if (context == null) {
            Log.e("t0", "HAL context unavailable for type " + i);
            return false;
        }
        synchronized (this.f6930d[i10]) {
            if (this.f6931e[i10]) {
                return true;
            }
            try {
                m2979c(context, i10);
                this.f6931e[i10] = true;
                return true;
            } catch (RuntimeException e10) {
                Log.e("t0", "lazy HAL init failed for type " + i, e10);
                return false;
            }
        }
    }
}

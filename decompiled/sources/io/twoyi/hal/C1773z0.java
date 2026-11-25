package io.twoyi.hal;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.LocalSocket;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import io.twoyi.utils.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import p089h8.AbstractC1589y;
import p247w0.AbstractC4176k;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.z0 */
/* loaded from: classes.dex */
public final class C1773z0 implements SensorEventListener {

    /* renamed from: a */
    public final LocalSocket f7010a;

    /* renamed from: b */
    public final OutputStream f7011b;

    /* renamed from: c */
    public final HashMap f7012c = new HashMap();

    /* renamed from: d */
    public final RunnableC1771y0 f7013d;

    /* renamed from: e */
    public boolean f7014e;

    /* renamed from: f */
    public int f7015f;

    /* renamed from: g */
    public boolean f7016g;

    /* renamed from: h */
    public boolean f7017h;

    /* renamed from: i */
    public final /* synthetic */ C1719a1 f7018i;

    public C1773z0(C1719a1 c1719a1, LocalSocket localSocket) {
        this.f7018i = c1719a1;
        this.f7010a = localSocket;
        this.f7011b = localSocket.getOutputStream();
        RunnableC1771y0 runnableC1771y0 = new RunnableC1771y0(this, 0);
        this.f7013d = runnableC1771y0;
        if (Build.VERSION.SDK_INT >= 29) {
            AbstractC1589y.m2655a("android.permission.ACTIVITY_RECOGNITION", runnableC1771y0);
        }
    }

    /* renamed from: a */
    public final void m3013a(int i, int i10, int i11) {
        boolean z10;
        boolean z11 = false;
        if (i == 19 && Build.VERSION.SDK_INT >= 29) {
            synchronized (this) {
                try {
                    if (this.f7017h) {
                        return;
                    }
                    if (i10 != 0) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    this.f7014e = z10;
                    this.f7015f = i11;
                    if (!z10) {
                        m3014b(19, i11, false);
                        return;
                    }
                    if (AbstractC1589y.m2661g("android.permission.ACTIVITY_RECOGNITION")) {
                        m3014b(19, this.f7015f, true);
                        return;
                    }
                    if (!this.f7016g) {
                        this.f7016g = true;
                        z11 = true;
                    }
                    if (!z11) {
                        return;
                    }
                    AbstractC1589y.m2664j(new C1768x(2, this), true, "android.permission.ACTIVITY_RECOGNITION");
                    return;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        synchronized (this) {
            if (i10 != 0) {
                z11 = true;
            }
            m3014b(i, i11, z11);
        }
    }

    /* renamed from: b */
    public final void m3014b(int i, int i10, boolean z10) {
        if (!this.f7017h) {
            Integer valueOf = Integer.valueOf(i);
            HashMap hashMap = this.f7012c;
            Sensor sensor = (Sensor) hashMap.remove(valueOf);
            C1719a1 c1719a1 = this.f7018i;
            if (sensor != null) {
                ((SensorManager) c1719a1.f6730b).unregisterListener(this, sensor);
            }
            if (z10) {
                Sensor m2872c = c1719a1.m2872c(i);
                if (m2872c == null) {
                    Log.w("a1", "activate unknown sensor type: " + i);
                    return;
                }
                try {
                    if (((SensorManager) c1719a1.f6730b).registerListener(this, m2872c, Math.max(i10, m2872c.getMinDelay()), (Handler) c1719a1.f6731c)) {
                        hashMap.put(Integer.valueOf(i), m2872c);
                        return;
                    }
                    Log.w("a1", "registerListener failed for type " + i);
                } catch (SecurityException e10) {
                    Log.w("a1", "sensor permission changed for type " + i, e10);
                } catch (RuntimeException e11) {
                    Log.w("a1", "registerListener failed for type " + i, e11);
                }
            }
        }
    }

    /* renamed from: c */
    public final synchronized void m3015c() {
        try {
            if (this.f7017h) {
                return;
            }
            this.f7017h = true;
            if (Build.VERSION.SDK_INT >= 29) {
                RunnableC1771y0 runnableC1771y0 = this.f7013d;
                synchronized (AbstractC1589y.f6354d) {
                    try {
                        HashMap hashMap = AbstractC1589y.f6355e;
                        List list = (List) hashMap.get("android.permission.ACTIVITY_RECOGNITION");
                        if (list != null) {
                            list.remove(runnableC1771y0);
                            if (list.isEmpty()) {
                                hashMap.remove("android.permission.ACTIVITY_RECOGNITION");
                            }
                        }
                    } finally {
                    }
                }
            }
            ((SensorManager) this.f7018i.f6730b).unregisterListener(this);
            this.f7012c.clear();
            IOUtils.closeSilently(this.f7010a);
        } catch (Throwable th) {
            throw th;
        }
    }

    /* renamed from: d */
    public final void m3016d(int i) {
        if (this.f7017h) {
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(28);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(0);
        allocate.putLong(SystemClock.elapsedRealtimeNanos());
        allocate.putInt(1);
        allocate.putInt(i);
        allocate.putLong(0L);
        try {
            m3018f(allocate.array());
        } catch (IOException unused) {
            Log.d("a1", "flush marker write failed, closing");
            m3015c();
        }
    }

    /* renamed from: e */
    public final void m3017e() {
        C1719a1 c1719a1;
        int i;
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        while (true) {
            c1719a1 = this.f7018i;
            if (i10 >= 7) {
                break;
            }
            Sensor m2872c = c1719a1.m2872c(C1719a1.f6727d[i10]);
            if (m2872c != null) {
                arrayList.add(m2872c);
            }
            i10++;
        }
        if (Build.VERSION.SDK_INT >= 29 && ((Context) c1719a1.f6729a).getPackageManager().hasSystemFeature("android.hardware.sensor.stepcounter") && c1719a1.m2872c(19) == null && !AbstractC1589y.m2661g("android.permission.ACTIVITY_RECOGNITION")) {
            i = 1;
        } else {
            i = 0;
        }
        int size = arrayList.size() + i;
        ByteBuffer allocate = ByteBuffer.allocate((size * 156) + 4);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(size);
        int size2 = arrayList.size();
        int i11 = 0;
        while (i11 < size2) {
            Object obj = arrayList.get(i11);
            i11++;
            Sensor sensor = (Sensor) obj;
            allocate.putInt(sensor.getType());
            allocate.putInt(sensor.getMinDelay());
            allocate.putInt(sensor.getMaxDelay());
            allocate.putInt(sensor.getFifoMaxEventCount());
            allocate.putFloat(sensor.getMaximumRange());
            allocate.putFloat(sensor.getResolution());
            allocate.putFloat(sensor.getPower());
            C1719a1.m2869a(allocate, sensor.getName());
            C1719a1.m2869a(allocate, sensor.getVendor());
        }
        if (i != 0) {
            allocate.putInt(19);
            allocate.putInt(0);
            allocate.putInt(0);
            allocate.putInt(0);
            allocate.putFloat(1.0f);
            allocate.putFloat(1.0f);
            allocate.putFloat(AbstractC4176k.f15279f);
            C1719a1.m2869a(allocate, "Step Counter");
            C1719a1.m2869a(allocate, "twoyi");
        }
        m3018f(allocate.array());
        Log.i("a1", "sent sensor list, count: " + size);
    }

    /* renamed from: f */
    public final synchronized void m3018f(byte[] bArr) {
        if (!this.f7017h) {
            this.f7011b.write(bArr);
            this.f7011b.flush();
        } else {
            throw new IOException("forwarder closed");
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        float f4;
        float f10;
        if (this.f7017h) {
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(28);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(sensorEvent.sensor.getType());
        allocate.putLong(sensorEvent.timestamp);
        allocate.putInt(sensorEvent.accuracy);
        if (sensorEvent.sensor.getType() == 19) {
            allocate.putLong(sensorEvent.values[0]);
            allocate.putInt(0);
        } else {
            float[] fArr = sensorEvent.values;
            int length = fArr.length;
            float f11 = AbstractC4176k.f15279f;
            if (length > 0) {
                f4 = fArr[0];
            } else {
                f4 = 0.0f;
            }
            allocate.putFloat(f4);
            float[] fArr2 = sensorEvent.values;
            if (fArr2.length > 1) {
                f10 = fArr2[1];
            } else {
                f10 = 0.0f;
            }
            allocate.putFloat(f10);
            float[] fArr3 = sensorEvent.values;
            if (fArr3.length > 2) {
                f11 = fArr3[2];
            }
            allocate.putFloat(f11);
        }
        try {
            m3018f(allocate.array());
        } catch (IOException unused) {
            Log.d("a1", "sensor event write failed, closing");
            m3015c();
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }
}

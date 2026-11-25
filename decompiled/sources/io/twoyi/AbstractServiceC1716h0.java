package io.twoyi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import io.twoyi.hal.C1743k0;
import java.util.concurrent.atomic.AtomicBoolean;
import na.AbstractC2689d;
import p024c.C0600b;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.h0 */
/* loaded from: classes.dex */
public abstract class AbstractServiceC1716h0 extends Service {

    /* renamed from: k */
    public static final AtomicBoolean f6706k = new AtomicBoolean(false);

    /* renamed from: i */
    public boolean f6707i;

    /* renamed from: j */
    public boolean f6708j;

    /* renamed from: b */
    public static void m2863b(Context context, boolean z10, Handler handler, C0600b c0600b) {
        if (!f6706k.get()) {
            if (c0600b != null && handler != null) {
                handler.post(new RunnableC1823m(6, c0600b));
                return;
            }
            return;
        }
        Intent putExtra = new Intent(context, (Class<?>) AbstractC2689d.m4545l()).setAction("io.twoyi.action.SET_GUEST_CAMERA_ACTIVE").putExtra("camera_active", z10);
        if (c0600b != null) {
            putExtra.putExtra("camera_result", new ResultReceiverC1714g0(handler, c0600b, 1));
        }
        context.startService(putExtra);
    }

    /* renamed from: c */
    public static void m2864c(Context context, boolean z10, Handler handler, C1743k0 c1743k0) {
        if (!f6706k.get()) {
            if (c1743k0 != null) {
                handler.post(new RunnableC1823m(7, c1743k0));
            }
        } else {
            Intent putExtra = new Intent(context, (Class<?>) AbstractC2689d.m4545l()).setAction("io.twoyi.action.SET_GUEST_LOCATION_ACTIVE").putExtra("location_active", z10);
            if (c1743k0 != null) {
                putExtra.putExtra("location_result", new ResultReceiverC1714g0(handler, c1743k0, 0));
            }
            context.startService(putExtra);
        }
    }

    /* renamed from: d */
    public static void m2865d(Context context) {
        if (f6706k.compareAndSet(true, false)) {
            context.startService(new Intent(context, (Class<?>) AbstractC2689d.m4545l()).setAction("io.twoyi.action.STOP_GUEST_SESSION"));
        }
    }

    /* renamed from: a */
    public final int m2866a() {
        int i;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 34) {
            i = 1073741824;
        } else {
            i = 0;
        }
        if (this.f6707i) {
            i |= 8;
        }
        if (this.f6708j && i10 >= 30) {
            return i | 64;
        }
        return i;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        AbstractC1712f0.m2812F(this);
        NotificationChannel notificationChannel = new NotificationChannel("guest_session", C1842v0.f7331a.mo2776h(this), 1);
        notificationChannel.setShowBadge(false);
        ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i10) {
        String action;
        char c6;
        int m2866a;
        ResultReceiver resultReceiver;
        if (intent == null) {
            action = null;
        } else {
            action = intent.getAction();
        }
        int i11 = 0;
        if ("io.twoyi.action.SET_GUEST_LOCATION_ACTIVE".equals(action)) {
            boolean booleanExtra = intent.getBooleanExtra("location_active", false);
            this.f6707i = booleanExtra;
            if (booleanExtra) {
                c6 = '\b';
            }
            c6 = 0;
        } else {
            if ("io.twoyi.action.SET_GUEST_CAMERA_ACTIVE".equals(action)) {
                boolean booleanExtra2 = intent.getBooleanExtra("camera_active", false);
                this.f6708j = booleanExtra2;
                if (booleanExtra2 && Build.VERSION.SDK_INT >= 30) {
                    c6 = '@';
                }
            } else if ("io.twoyi.action.STOP_GUEST_SESSION".equals(action)) {
                this.f6707i = false;
                this.f6708j = false;
            }
            c6 = 0;
        }
        Notification build = new Notification.Builder(this, "guest_session").setSmallIcon(R.drawable.ic_guest_session).setContentTitle(C1842v0.f7331a.mo2777i(this)).setContentIntent(PendingIntent.getActivity(this, 0, new Intent().setClassName(this, C1842v0.f7331a.mo2770b(this)).setFlags(603979776), 67108864)).setOngoing(true).build();
        if (Build.VERSION.SDK_INT < 29) {
            startForeground(737, build);
            m2866a = m2866a();
        } else {
            m2866a = m2866a();
            try {
                startForeground(737, build, m2866a);
            } catch (SecurityException e10) {
                Log.e("h0", "cannot enable requested foreground-service types", e10);
                if (c6 == '\b') {
                    this.f6707i = false;
                } else if (c6 == '@') {
                    this.f6708j = false;
                } else {
                    this.f6707i = false;
                    this.f6708j = false;
                }
                m2866a = m2866a();
                try {
                    startForeground(737, build, m2866a);
                } catch (SecurityException unused) {
                    this.f6707i = false;
                    this.f6708j = false;
                    m2866a = m2866a();
                    startForeground(737, build, m2866a);
                }
            }
        }
        if ("io.twoyi.action.SET_GUEST_LOCATION_ACTIVE".equals(action)) {
            ResultReceiver resultReceiver2 = (ResultReceiver) intent.getParcelableExtra("location_result");
            if (resultReceiver2 != null) {
                if (!intent.getBooleanExtra("location_active", false) || (m2866a & 8) != 0) {
                    i11 = 1;
                }
                resultReceiver2.send(i11, null);
            }
        } else if ("io.twoyi.action.SET_GUEST_CAMERA_ACTIVE".equals(action) && (resultReceiver = (ResultReceiver) intent.getParcelableExtra("camera_result")) != null) {
            if (!intent.getBooleanExtra("camera_active", false) || Build.VERSION.SDK_INT < 30 || (m2866a & 64) != 0) {
                i11 = 1;
            }
            resultReceiver.send(i11, null);
        }
        if ("io.twoyi.action.STOP_GUEST_SESSION".equals(action)) {
            stopForeground(1);
            stopSelf();
            return 2;
        }
        return 2;
    }
}

package io.twoyi.hal;

import android.content.Context;
import android.media.AudioRecord;
import android.net.LocalSocket;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.x0 */
/* loaded from: classes.dex */
public final class C1769x0 {

    /* renamed from: e */
    public static final C1769x0 f6996e = new Object();

    /* renamed from: a */
    public LocalSocket f6997a;

    /* renamed from: b */
    public Object f6998b;

    /* renamed from: c */
    public Object f6999c;

    /* renamed from: d */
    public Object f7000d;

    /* renamed from: b */
    public static void m3005b(AudioRecord audioRecord) {
        if (audioRecord != null) {
            try {
                if (audioRecord.getRecordingState() == 3) {
                    audioRecord.stop();
                }
            } catch (RuntimeException unused) {
            }
            try {
                audioRecord.release();
            } catch (RuntimeException unused2) {
            }
        }
    }

    /* renamed from: a */
    public void m3006a() {
        synchronized (this.f6999c) {
            try {
                if (!((AtomicBoolean) this.f6998b).compareAndSet(false, true)) {
                    return;
                }
                AudioRecord audioRecord = (AudioRecord) this.f7000d;
                if (audioRecord != null) {
                    try {
                        audioRecord.stop();
                    } catch (RuntimeException unused) {
                    }
                }
                C1770y.m3008a(this.f6997a);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: c */
    public void m3007c(boolean z10) {
        Context context = (Context) this.f6998b;
        if (context != null) {
            try {
                ((TelephonyManager) context.getSystemService("phone")).setDataEnabled(z10);
            } catch (Exception unused) {
                Log.e("x0", "need permission.MODIFY_PHONE_STATE setDataEnabled: " + z10);
            }
        }
    }
}

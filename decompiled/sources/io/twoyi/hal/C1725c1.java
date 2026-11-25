package io.twoyi.hal;

import android.net.LocalSocket;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import io.twoyi.utils.IOUtils;
import java.io.IOException;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.c1 */
/* loaded from: classes.dex */
public final class C1725c1 {

    /* renamed from: c */
    public static final C1725c1 f6763c = new C1725c1();

    /* renamed from: a */
    public final Object f6764a = new Object();

    /* renamed from: b */
    public Vibrator f6765b;

    /* renamed from: a */
    public final void m2879a(LocalSocket localSocket) {
        try {
            int m2981e = C1761t0.m2981e(localSocket.getInputStream());
            if (m2981e < 0) {
                Log.w("c1", "reject negative vibrator duration: " + m2981e);
            } else {
                Log.d("c1", "vibrator duration: " + m2981e + " ms");
                m2880b(m2981e);
            }
        } catch (IOException e10) {
            Log.w("c1", "cannot read vibrator command", e10);
        } finally {
            IOUtils.closeSilently(localSocket);
        }
    }

    /* renamed from: b */
    public final void m2880b(int i) {
        synchronized (this.f6764a) {
            Vibrator vibrator = this.f6765b;
            if (vibrator == null) {
                Log.w("c1", "ignore vibrator command: host vibrator is unavailable");
                return;
            }
            try {
                if (i == 0) {
                    vibrator.cancel();
                } else {
                    vibrator.vibrate(VibrationEffect.createOneShot(i, -1));
                }
            } catch (RuntimeException e10) {
                Log.e("c1", "host vibrator command failed", e10);
            }
        }
    }
}

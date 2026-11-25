package io.twoyi.hal;

import android.content.Context;
import android.content.Intent;
import android.net.LocalSocket;
import android.util.Log;
import io.twoyi.C1849z;
import io.twoyi.utils.IOUtils;
import java.io.IOException;
import java.util.ArrayList;
import p052e5.C0955b;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.u0 */
/* loaded from: classes.dex */
public final class C1763u0 {

    /* renamed from: d */
    public static final C1763u0 f6946d;

    /* renamed from: a */
    public volatile Context f6947a;

    /* renamed from: b */
    public volatile C1849z f6948b;

    /* renamed from: c */
    public volatile int f6949c;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, io.twoyi.hal.u0] */
    static {
        ?? obj = new Object();
        new C1772z(3, obj);
        obj.f6949c = -1;
        f6946d = obj;
    }

    /* renamed from: a */
    public final void m2984a(LocalSocket localSocket) {
        try {
            int m2981e = C1761t0.m2981e(localSocket.getInputStream());
            if (m2981e >= 0 && m2981e <= 255) {
                if (this.f6949c != m2981e) {
                    this.f6949c = m2981e;
                    Log.i("u0", "guest backlight brightness=" + m2981e);
                    m2985b();
                }
                return;
            }
            Log.w("u0", "ignoring invalid guest brightness: " + m2981e);
        } catch (IOException e10) {
            Log.w("u0", "light session failed", e10);
        } finally {
            IOUtils.closeSilently(localSocket);
        }
    }

    /* renamed from: b */
    public final void m2985b() {
        C0955b c0955b;
        boolean z10;
        float f4;
        C1849z c1849z = this.f6948b;
        if (c1849z != null) {
            int i = this.f6949c;
            if (i == -1) {
                f4 = -1.0f;
            } else {
                f4 = i / 255.0f;
            }
            c1849z.m3166b(f4);
        }
        Context context = this.f6947a;
        if (context == null) {
            Log.w("u0", "brightness arrived before light HAL initialization");
            return;
        }
        Intent intent = new Intent("io.twoyi.light.CHANGE");
        synchronized (C0955b.f3970e) {
            try {
                if (C0955b.f3971f == null) {
                    C0955b.f3971f = new C0955b(context.getApplicationContext());
                }
                c0955b = C0955b.f3971f;
            } finally {
            }
        }
        synchronized (c0955b.f3973b) {
            try {
                intent.getAction();
                String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(c0955b.f3972a.getContentResolver());
                intent.getData();
                String scheme = intent.getScheme();
                intent.getCategories();
                if ((intent.getFlags() & 8) != 0) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (z10) {
                    Log.v("LocalBroadcastManager", "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
                }
                ArrayList arrayList = (ArrayList) c0955b.f3974c.get(intent.getAction());
                if (arrayList != null) {
                    if (z10) {
                        Log.v("LocalBroadcastManager", "Action list: " + arrayList);
                    }
                    if (arrayList.size() > 0) {
                        if (arrayList.get(0) == null) {
                            if (z10) {
                                throw null;
                            }
                            throw null;
                        }
                        throw new ClassCastException();
                    }
                }
            } finally {
            }
        }
    }
}

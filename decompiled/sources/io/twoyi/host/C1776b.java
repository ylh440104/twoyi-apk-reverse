package io.twoyi.host;

import android.content.SharedPreferences;
import android.os.RemoteException;
import java.util.HashSet;
import java.util.Map;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.b */
/* loaded from: classes.dex */
public final class C1776b {

    /* renamed from: b */
    public static volatile SharedPreferences f7040b;

    /* renamed from: a */
    public static final C1776b f7039a = new Object();

    /* renamed from: c */
    public static final HashSet f7041c = new HashSet();

    /* renamed from: a */
    public final synchronized void m3025a(InterfaceC1803o0 interfaceC1803o0, String str, boolean z10) {
        try {
            AbstractC4450j.m7255e(interfaceC1803o0, "helper");
            AbstractC4450j.m7255e(str, "pkg");
            SharedPreferences sharedPreferences = f7040b;
            if (sharedPreferences == null) {
                return;
            }
            if (!sharedPreferences.contains(str)) {
                HashSet hashSet = f7041c;
                if (!hashSet.contains(str)) {
                    int mo3093m = interfaceC1803o0.mo3093m(str, z10);
                    if (mo3093m >= 0) {
                        sharedPreferences.edit().putInt(str, mo3093m).apply();
                    } else if (!z10) {
                        hashSet.add(str);
                    }
                }
            }
        } finally {
        }
    }

    /* renamed from: b */
    public final synchronized void m3026b(InterfaceC1803o0 interfaceC1803o0) {
        Integer num;
        int i;
        try {
            AbstractC4450j.m7255e(interfaceC1803o0, "helper");
            SharedPreferences sharedPreferences = f7040b;
            if (sharedPreferences == null) {
                return;
            }
            f7041c.clear();
            Map<String, ?> all = sharedPreferences.getAll();
            AbstractC4450j.m7254d(all, "getAll(...)");
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                try {
                    if (value instanceof Integer) {
                        num = (Integer) value;
                    } else {
                        num = null;
                    }
                    if (num != null) {
                        i = num.intValue();
                    } else {
                        i = 3;
                    }
                    interfaceC1803o0.mo3086O(key, i);
                    sharedPreferences.edit().remove(key).apply();
                } catch (RemoteException unused) {
                    return;
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}

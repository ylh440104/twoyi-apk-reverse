package io.twoyi;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.x0 */
/* loaded from: classes.dex */
public final class C1846x0 {

    /* renamed from: d */
    public static C1846x0 f7336d;

    /* renamed from: e */
    public static final ExecutorService f7337e = Executors.newCachedThreadPool();

    /* renamed from: a */
    public volatile OutputStreamWriter f7338a;

    /* renamed from: c */
    public LocalSocket f7340c = null;

    /* renamed from: b */
    public final ReentrantReadWriteLock f7339b = new ReentrantReadWriteLock();

    /* renamed from: a */
    public final void m3159a() {
        if (this.f7340c != null) {
            return;
        }
        try {
            LocalSocket localSocket = new LocalSocket(3);
            this.f7340c = localSocket;
            localSocket.connect(new LocalSocketAddress("TWOYI_SOCK"));
            this.f7340c.getInputStream();
            this.f7338a = new OutputStreamWriter(this.f7340c.getOutputStream());
        } catch (IOException e10) {
            e10.printStackTrace();
        }
    }

    /* renamed from: b */
    public final void m3160b() {
        synchronized (this) {
            try {
                if (this.f7338a == null) {
                    m3159a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        try {
            this.f7339b.writeLock().lock();
            this.f7338a.write("PING");
            this.f7338a.flush();
        } catch (Throwable unused) {
        }
        this.f7339b.writeLock().unlock();
    }
}

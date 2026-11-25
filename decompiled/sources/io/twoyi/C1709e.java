package io.twoyi;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.system.ErrnoException;
import android.system.Os;
import android.util.Log;
import android.view.Surface;
import io.twoyi.hal.C1761t0;
import io.twoyi.utils.RomManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import p009a8.RunnableC0121c1;
import p009a8.ThreadFactoryC0110a0;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.e */
/* loaded from: classes.dex */
public final class C1709e {

    /* renamed from: s */
    public static final C1709e f6658s = new C1709e();

    /* renamed from: f */
    public CompletableFuture f6664f;

    /* renamed from: g */
    public Surface f6665g;

    /* renamed from: h */
    public Surface f6666h;

    /* renamed from: i */
    public C1840u0 f6667i;

    /* renamed from: j */
    public boolean f6668j;

    /* renamed from: k */
    public long f6669k;

    /* renamed from: m */
    public int f6671m;

    /* renamed from: n */
    public C1825n f6672n;

    /* renamed from: q */
    public C1821l f6675q;

    /* renamed from: r */
    public boolean f6676r;

    /* renamed from: a */
    public final Object f6659a = new Object();

    /* renamed from: b */
    public final Handler f6660b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    public final ExecutorService f6661c = Executors.newSingleThreadExecutor(new ThreadFactoryC0110a0(2));

    /* renamed from: d */
    public int f6662d = 1;

    /* renamed from: e */
    public final CompletableFuture f6663e = new CompletableFuture();

    /* renamed from: l */
    public long f6670l = -1;

    /* renamed from: o */
    public final CompletableFuture f6673o = new CompletableFuture();

    /* renamed from: p */
    public long f6674p = 1;

    /* renamed from: b */
    public static void m2795b(Context context) {
        long nanos = TimeUnit.SECONDS.toNanos(10L) + System.nanoTime();
        try {
            long max = Math.max(0L, nanos - System.nanoTime());
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            C1761t0 c1761t0 = C1761t0.f6925g;
            if (c1761t0.f6928b.await(max, timeUnit) && c1761t0.f6929c) {
                if (C1848y0.f7342e == null) {
                    C1848y0.f7342e = new C1848y0(context);
                }
                C1848y0 c1848y0 = C1848y0.f7342e;
                if (c1848y0.f7345b.await(Math.max(0L, nanos - System.nanoTime()), timeUnit)) {
                    return;
                } else {
                    throw new IOException("Twoyi control socket did not become ready");
                }
            }
            throw new IOException("Twoyi HAL socket did not become ready");
        } catch (InterruptedException e10) {
            Thread.currentThread().interrupt();
            throw new IOException("interrupted while waiting for host sockets", e10);
        }
    }

    /* renamed from: d */
    public static void m2796d(File file) {
        if ((file.isDirectory() || file.mkdirs()) && file.isDirectory()) {
            return;
        }
        throw new IOException("unable to prepare host runtime directory: " + file);
    }

    /* renamed from: e */
    public static void m2797e(Context context) {
        m2796d(new File("/data/data/io.twoyi/rootfs"));
        m2796d(new File("/data/data/io.twoyi/rootfs/dev/input/touch").getParentFile());
        m2796d(new File("/data/data/io.twoyi/rootfs/dev/input/key0").getParentFile());
        File romSdcardDir = RomManager.getRomSdcardDir(context);
        m2796d(romSdcardDir);
        try {
            Os.chmod(romSdcardDir.getAbsolutePath(), 511);
        } catch (ErrnoException e10) {
            throw new IOException("unable to set primary storage mode: " + romSdcardDir, e10);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x007b A[Catch: all -> 0x0076, TryCatch #3 {all -> 0x0076, blocks: (B:31:0x005c, B:35:0x006f, B:39:0x007b, B:40:0x007d, B:53:0x0062, B:56:0x0067), top: B:30:0x005c }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2798a() {
        /*
            Method dump skipped, instructions count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.C1709e.m2798a():void");
    }

    /* renamed from: c */
    public final void m2799c(Surface surface) {
        boolean z10;
        if (surface != null) {
            synchronized (this.f6659a) {
                try {
                    if (this.f6665g == surface) {
                        this.f6665g = null;
                        this.f6669k++;
                    }
                    if (this.f6666h == surface) {
                        this.f6666h = null;
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z10) {
                m2801g(new RunnableC0121c1(6, this, surface));
            }
        }
    }

    /* renamed from: f */
    public final void m2800f(RuntimeException runtimeException) {
        synchronized (this.f6659a) {
            try {
                long j3 = this.f6670l;
                long j10 = this.f6669k;
                if (j3 == j10) {
                    return;
                }
                this.f6670l = j10;
                Log.e("e", "renderer window lifecycle failed", runtimeException);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: g */
    public final void m2801g(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.f6660b.post(runnable);
        }
    }
}

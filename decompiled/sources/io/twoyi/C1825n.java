package io.twoyi;

import android.content.Context;
import io.twoyi.utils.RomManager;
import java.io.Closeable;
import java.util.concurrent.CompletableFuture;
import p245v7.C4158a;
import p245v7.C4159b;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.n */
/* loaded from: classes.dex */
public final class C1825n implements Closeable {

    /* renamed from: i */
    public final CompletableFuture f7293i = new CompletableFuture();

    /* renamed from: j */
    public final CompletableFuture f7294j = new CompletableFuture();

    /* renamed from: k */
    public boolean f7295k;

    /* renamed from: l */
    public boolean f7296l;

    /* renamed from: b */
    public final CompletableFuture m3139b(Context context, C1707d c1707d) {
        CompletableFuture completableFuture = new CompletableFuture();
        synchronized (this) {
            if (!this.f7296l) {
                if (!this.f7295k) {
                    this.f7296l = true;
                } else {
                    throw new IllegalStateException("direct guest backend closed");
                }
            } else {
                throw new IllegalStateException("direct guest backend already started");
            }
        }
        try {
            if (RomManager.shouldExtractRootfs(context)) {
                BootPrefetch.cancel();
                if (!RomManager.extractRootfs(context)) {
                    throw new IllegalStateException("rootfs installation failed");
                }
            }
            RomManager.initRootfs(context);
            RomManager.bootTwoyi(context);
            String loaderPath = RomManager.getLoaderPath(context);
            C4158a m6886b = C4159b.m6882e(context).m6886b();
            DirectLauncher.prepare(loaderPath, "androidboot.hardware=goldfish androidboot.serialno=" + m6886b.f15132d + " androidboot.twoyi.instance=" + m6886b.f15129a, c1707d.f6651a, c1707d.f6652b, c1707d.f6653c, c1707d.f6654d, c1707d.f6655e);
            synchronized (this) {
                if (this.f7295k) {
                    DirectLauncher.stop();
                    throw new IllegalStateException("direct guest backend closed");
                }
            }
            Thread thread = new Thread(new RunnableC1823m(0, this), "direct-guest-lifetime");
            thread.setDaemon(true);
            thread.start();
            this.f7293i.complete(null);
            completableFuture.complete(null);
            return completableFuture;
        } catch (Throwable th) {
            DirectLauncher.stop();
            this.f7293i.completeExceptionally(th);
            completableFuture.completeExceptionally(th);
            synchronized (this) {
                try {
                    if (this.f7295k) {
                        this.f7294j.complete(null);
                    } else {
                        this.f7294j.completeExceptionally(th);
                    }
                    return completableFuture;
                } finally {
                }
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        try {
            if (this.f7295k) {
                return;
            }
            this.f7295k = true;
            this.f7293i.completeExceptionally(new IllegalStateException("direct guest backend closed"));
            if (this.f7296l) {
                DirectLauncher.stop();
            } else {
                this.f7294j.complete(null);
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}

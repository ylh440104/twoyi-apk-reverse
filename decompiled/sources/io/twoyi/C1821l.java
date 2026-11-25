package io.twoyi;

import java.util.concurrent.CompletableFuture;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.l */
/* loaded from: classes.dex */
public final class C1821l {

    /* renamed from: a */
    public final long f7287a;

    /* renamed from: b */
    public final CompletableFuture f7288b = new CompletableFuture();

    /* renamed from: c */
    public boolean f7289c;

    public C1821l(long j3) {
        if (j3 > 0) {
            this.f7287a = j3;
            return;
        }
        throw new IllegalArgumentException("boot session id must be positive");
    }

    /* renamed from: a */
    public final synchronized void m3138a(Throwable th) {
        if (this.f7289c) {
            return;
        }
        this.f7289c = true;
        if (!this.f7288b.isDone()) {
            this.f7288b.completeExceptionally(th);
        }
    }
}

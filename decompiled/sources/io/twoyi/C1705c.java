package io.twoyi;

import java.util.function.BiConsumer;
import p009a8.RunnableC0121c1;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.c */
/* loaded from: classes.dex */
public final /* synthetic */ class C1705c implements BiConsumer {

    /* renamed from: a */
    public final /* synthetic */ int f6646a;

    /* renamed from: b */
    public final /* synthetic */ C1709e f6647b;

    /* renamed from: c */
    public final /* synthetic */ Object f6648c;

    public /* synthetic */ C1705c(C1709e c1709e, C1821l c1821l, int i) {
        this.f6646a = i;
        this.f6647b = c1709e;
        this.f6648c = c1821l;
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        Throwable illegalStateException;
        switch (this.f6646a) {
            case AbstractC4695a.f17215a /* 0 */:
                C1709e c1709e = this.f6647b;
                C1821l c1821l = (C1821l) this.f6648c;
                if (((Throwable) obj2) != null) {
                    c1709e.getClass();
                    return;
                }
                synchronized (c1709e.f6659a) {
                    if (!c1709e.f6676r && c1709e.f6675q == c1821l) {
                        c1709e.f6668j = true;
                        c1709e.m2801g(new RunnableC1823m(1, c1709e));
                    }
                }
                return;
            case 1:
                C1709e c1709e2 = this.f6647b;
                C1821l c1821l2 = (C1821l) this.f6648c;
                Throwable th = (Throwable) obj2;
                c1709e2.getClass();
                synchronized (c1821l2) {
                    if (!c1821l2.f7289c) {
                        c1821l2.f7289c = true;
                        if (!c1821l2.f7288b.isDone()) {
                            if (th != null) {
                                illegalStateException = th;
                            } else {
                                illegalStateException = new IllegalStateException("guest backend exited before BOOT_COMPLETED");
                            }
                            c1821l2.f7288b.completeExceptionally(illegalStateException);
                        }
                    }
                }
                if (th != null) {
                    c1709e2.f6673o.completeExceptionally(th);
                    return;
                } else {
                    c1709e2.f6673o.complete(null);
                    return;
                }
            default:
                AbstractServiceC1710e0 abstractServiceC1710e0 = (AbstractServiceC1710e0) this.f6648c;
                C1709e c1709e3 = this.f6647b;
                Throwable th2 = (Throwable) obj2;
                if (th2 != null) {
                    abstractServiceC1710e0.m2803f(th2);
                    return;
                } else {
                    if (!c1709e3.f6673o.isDone()) {
                        abstractServiceC1710e0.m2806i(2, null);
                        Thread thread = new Thread(new RunnableC0121c1(8, abstractServiceC1710e0, c1709e3), "guest-waiting-boot");
                        thread.setDaemon(true);
                        thread.start();
                        return;
                    }
                    return;
                }
        }
    }

    public /* synthetic */ C1705c(AbstractServiceC1710e0 abstractServiceC1710e0, C1709e c1709e) {
        this.f6646a = 2;
        this.f6648c = abstractServiceC1710e0;
        this.f6647b = c1709e;
    }
}

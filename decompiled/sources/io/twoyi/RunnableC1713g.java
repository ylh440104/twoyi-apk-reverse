package io.twoyi;

import android.media.Image;
import android.util.Log;
import android.view.Surface;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.g */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1713g implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f6695i;

    /* renamed from: j */
    public final /* synthetic */ Object f6696j;

    /* renamed from: k */
    public final /* synthetic */ Object f6697k;

    /* renamed from: l */
    public final /* synthetic */ Object f6698l;

    public /* synthetic */ RunnableC1713g(Object obj, Object obj2, Object obj3, int i) {
        this.f6695i = i;
        this.f6696j = obj;
        this.f6697k = obj2;
        this.f6698l = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f6695i) {
            case AbstractC4695a.f17215a /* 0 */:
                BootPrefetch.m2755a((String) this.f6696j, (String) this.f6697k, (String) this.f6698l);
                return;
            case 1:
                C1709e c1709e = (C1709e) this.f6696j;
                Surface surface = (Surface) this.f6697k;
                C1707d c1707d = (C1707d) this.f6698l;
                c1709e.getClass();
                int i = c1707d.f6651a;
                int i10 = c1707d.f6652b;
                synchronized (c1709e.f6659a) {
                    try {
                        if (c1709e.f6666h == surface) {
                            try {
                                Renderer.resetWindow(surface, 0, 0, i, i10);
                                return;
                            } catch (RuntimeException e10) {
                                c1709e.m2800f(e10);
                                return;
                            }
                        }
                        return;
                    } finally {
                    }
                }
            default:
                C1840u0 c1840u0 = (C1840u0) this.f6696j;
                AtomicReference atomicReference = (AtomicReference) this.f6697k;
                CountDownLatch countDownLatch = (CountDownLatch) this.f6698l;
                c1840u0.getClass();
                try {
                    try {
                        Image image = c1840u0.f7317e;
                        if (image != null) {
                            atomicReference.set(C1840u0.m3152a(image));
                        }
                    } finally {
                        countDownLatch.countDown();
                    }
                } catch (RuntimeException e11) {
                    Log.e("u0", "offscreen frame not copied", e11);
                }
                return;
        }
    }
}

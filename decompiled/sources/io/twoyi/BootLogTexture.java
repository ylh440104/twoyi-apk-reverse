package io.twoyi;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import p086h5.RunnableC1510h;
import p202r7.AbstractC3377c;
import p214s7.C3517b0;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* loaded from: classes.dex */
public class BootLogTexture extends GLSurfaceView {

    /* renamed from: u */
    public static final char[] f6594u = "0123456789ABCDEF<>*+-/=:".toCharArray();

    /* renamed from: i */
    public final AtomicBoolean f6595i;

    /* renamed from: j */
    public final Random f6596j;

    /* renamed from: k */
    public final Handler f6597k;

    /* renamed from: l */
    public boolean f6598l;

    /* renamed from: m */
    public final String[] f6599m;

    /* renamed from: n */
    public int f6600n;

    /* renamed from: o */
    public int f6601o;

    /* renamed from: p */
    public volatile String f6602p;

    /* renamed from: q */
    public volatile C3517b0 f6603q;

    /* renamed from: r */
    public boolean f6604r;

    /* renamed from: s */
    public boolean f6605s;

    /* renamed from: t */
    public final RunnableC1510h f6606t;

    public BootLogTexture(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6595i = new AtomicBoolean(false);
        this.f6596j = new Random();
        this.f6597k = new Handler(Looper.getMainLooper());
        this.f6599m = new String[32];
        this.f6606t = new RunnableC1510h(5, this);
        setZOrderMediaOverlay(true);
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        setPreserveEGLContextOnPause(true);
        setRenderer(new C1819k(this, getResources().getDisplayMetrics().density));
        setRenderMode(0);
    }

    private void setActive(boolean z10) {
        if (this.f6605s != z10) {
            this.f6605s = z10;
            if (z10) {
                onResume();
                if (this.f6595i.compareAndSet(false, true)) {
                    AbstractC3377c.f12089i.execute(new RunnableC1823m(3, this));
                }
                if (this.f6598l) {
                    return;
                }
                this.f6598l = true;
                this.f6597k.post(this.f6606t);
                return;
            }
            this.f6595i.set(false);
            C3517b0 c3517b0 = this.f6603q;
            if (c3517b0 != null) {
                new Thread(new RunnableC1823m(2, c3517b0), "stop-boot-logcat").start();
            }
            this.f6598l = false;
            this.f6597k.removeCallbacks(this.f6606t);
            onPause();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
    
        r0 = r0 + 1;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final char m2754a() {
        /*
            r5 = this;
            r0 = 0
        L1:
            r1 = 4
            if (r0 >= r1) goto L3d
            java.lang.String[] r1 = r5.f6599m
            monitor-enter(r1)
            int r2 = r5.f6601o     // Catch: java.lang.Throwable -> L33
            if (r2 <= 0) goto L39
            java.lang.String[] r3 = r5.f6599m     // Catch: java.lang.Throwable -> L33
            java.util.Random r4 = r5.f6596j     // Catch: java.lang.Throwable -> L33
            int r2 = r4.nextInt(r2)     // Catch: java.lang.Throwable -> L33
            r2 = r3[r2]     // Catch: java.lang.Throwable -> L33
            if (r2 == 0) goto L39
            boolean r3 = r2.isEmpty()     // Catch: java.lang.Throwable -> L33
            if (r3 != 0) goto L39
            java.util.Random r3 = r5.f6596j     // Catch: java.lang.Throwable -> L33
            int r4 = r2.length()     // Catch: java.lang.Throwable -> L33
            int r3 = r3.nextInt(r4)     // Catch: java.lang.Throwable -> L33
            char r2 = r2.charAt(r3)     // Catch: java.lang.Throwable -> L33
            boolean r3 = java.lang.Character.isWhitespace(r2)     // Catch: java.lang.Throwable -> L33
            if (r3 != 0) goto L35
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L33
            return r2
        L33:
            r0 = move-exception
            goto L3b
        L35:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L33
            int r0 = r0 + 1
            goto L1
        L39:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L33
            goto L3d
        L3b:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L33
            throw r0
        L3d:
            char[] r0 = io.twoyi.BootLogTexture.f6594u
            java.util.Random r1 = r5.f6596j
            int r2 = r0.length
            int r1 = r1.nextInt(r2)
            char r0 = r0[r1]
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.BootLogTexture.m2754a():char");
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f6604r = true;
        setActive(isShown());
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    public final void onDetachedFromWindow() {
        this.f6604r = false;
        setActive(false);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onVisibilityAggregated(boolean z10) {
        boolean z11;
        super.onVisibilityAggregated(z10);
        if (this.f6604r && z10) {
            z11 = true;
        } else {
            z11 = false;
        }
        setActive(z11);
    }
}

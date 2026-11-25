package io.twoyi.hal;

import android.hardware.camera2.CameraCaptureSession;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.p */
/* loaded from: classes.dex */
public final class C1752p extends CameraCaptureSession.StateCallback {

    /* renamed from: a */
    public final /* synthetic */ C1764v f6871a;

    public C1752p(C1764v c1764v) {
        this.f6871a = c1764v;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onActive(CameraCaptureSession cameraCaptureSession) {
        int i;
        synchronized (this.f6871a.f6965a) {
            try {
                C1764v c1764v = this.f6871a;
                if (c1764v.f6953C == cameraCaptureSession && (((i = c1764v.f6964N) == 4 || i == 5) && !c1764v.f6982r)) {
                    c1764v.f6982r = true;
                    c1764v.f6965a.notifyAll();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onClosed(CameraCaptureSession cameraCaptureSession) {
        boolean z10;
        int i;
        synchronized (this.f6871a.f6965a) {
            try {
                C1764v c1764v = this.f6871a;
                if (!c1764v.f6985u && c1764v.f6953C == cameraCaptureSession && ((i = c1764v.f6964N) == 4 || i == 5)) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (z10) {
                    c1764v.f6953C = null;
                    c1764v.f6981q = false;
                    c1764v.f6982r = false;
                }
                if (c1764v.f6954D == cameraCaptureSession) {
                    c1764v.f6954D = null;
                }
                c1764v.f6965a.notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z10) {
            this.f6871a.m2997l("Host camera capture session closed unexpectedly", null);
        }
        C1764v.m2986a(this.f6871a);
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        synchronized (this.f6871a.f6965a) {
            C1764v c1764v = this.f6871a;
            c1764v.f6980p = false;
            c1764v.f6965a.notifyAll();
        }
        cameraCaptureSession.close();
        this.f6871a.m2997l("Host camera configuration failed", null);
        C1764v.m2986a(this.f6871a);
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
        boolean z10;
        synchronized (this.f6871a.f6965a) {
            try {
                C1764v c1764v = this.f6871a;
                z10 = false;
                c1764v.f6980p = false;
                if (c1764v.f6964N == 4 && !c1764v.f6985u) {
                    z10 = true;
                }
                if (z10) {
                    c1764v.f6953C = cameraCaptureSession;
                    c1764v.f6981q = true;
                }
                c1764v.f6965a.notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z10) {
            cameraCaptureSession.close();
            C1764v.m2986a(this.f6871a);
        }
    }
}

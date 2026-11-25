package io.twoyi.hal;

import android.hardware.camera2.CameraDevice;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.o */
/* loaded from: classes.dex */
public final class C1750o extends CameraDevice.StateCallback {

    /* renamed from: a */
    public final /* synthetic */ C1764v f6866a;

    public C1750o(C1764v c1764v) {
        this.f6866a = c1764v;
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onClosed(CameraDevice cameraDevice) {
        boolean z10;
        int i;
        synchronized (this.f6866a.f6965a) {
            try {
                C1764v c1764v = this.f6866a;
                if (!c1764v.f6985u && c1764v.f6951A == cameraDevice && ((i = c1764v.f6964N) == 3 || i == 4 || i == 5)) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (z10) {
                    c1764v.f6951A = null;
                }
                if (c1764v.f6952B == cameraDevice) {
                    c1764v.f6952B = null;
                }
                c1764v.f6965a.notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z10) {
            this.f6866a.m2997l("Host camera closed unexpectedly", null);
        }
        C1764v.m2986a(this.f6866a);
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onDisconnected(CameraDevice cameraDevice) {
        synchronized (this.f6866a.f6965a) {
            C1764v c1764v = this.f6866a;
            c1764v.f6979o = false;
            c1764v.f6965a.notifyAll();
        }
        cameraDevice.close();
        this.f6866a.m2997l("Host camera disconnected", null);
        C1764v.m2986a(this.f6866a);
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onError(CameraDevice cameraDevice, int i) {
        synchronized (this.f6866a.f6965a) {
            C1764v c1764v = this.f6866a;
            c1764v.f6979o = false;
            c1764v.f6965a.notifyAll();
        }
        cameraDevice.close();
        this.f6866a.m2997l("Host camera error " + i, null);
        C1764v.m2986a(this.f6866a);
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public final void onOpened(CameraDevice cameraDevice) {
        boolean z10;
        synchronized (this.f6866a.f6965a) {
            try {
                C1764v c1764v = this.f6866a;
                z10 = false;
                c1764v.f6979o = false;
                if (c1764v.f6964N == 2 && !c1764v.f6985u) {
                    z10 = true;
                }
                if (z10) {
                    c1764v.f6951A = cameraDevice;
                    c1764v.f6964N = 3;
                }
                c1764v.f6965a.notifyAll();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z10) {
            cameraDevice.close();
            C1764v.m2986a(this.f6866a);
        }
    }
}

package io.twoyi.hal;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.util.Log;
import android.view.Surface;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.n */
/* loaded from: classes.dex */
public final class C1748n extends CameraCaptureSession.CaptureCallback {

    /* renamed from: a */
    public final /* synthetic */ C1764v f6862a;

    public C1748n(C1764v c1764v) {
        this.f6862a = c1764v;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureBufferLost(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, Surface surface, long j3) {
        synchronized (this.f6862a.f6965a) {
            try {
                Object tag = captureRequest.getTag();
                C1762u c1762u = this.f6862a.f6956F;
                if ((tag instanceof Long) && c1762u != null && ((Long) tag).longValue() == c1762u.f6944l) {
                    this.f6862a.m2996k(9, "Host camera lost capture buffer");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
        int intValue;
        int intValue2;
        Object tag = captureRequest.getTag();
        Long l2 = (Long) totalCaptureResult.get(CaptureResult.SENSOR_TIMESTAMP);
        try {
            int m2987b = C1764v.m2987b((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE), "AF state");
            int m2987b2 = C1764v.m2987b((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_STATE), "AE state");
            int m2987b3 = C1764v.m2987b((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AWB_STATE), "AWB state");
            Integer num = (Integer) totalCaptureResult.get(CaptureResult.FLASH_STATE);
            Integer num2 = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION);
            synchronized (this.f6862a.f6965a) {
                try {
                    C1762u c1762u = this.f6862a.f6956F;
                    if ((tag instanceof Long) && c1762u != null && ((Long) tag).longValue() == c1762u.f6944l) {
                        if (l2 != null && l2.longValue() > 0) {
                            c1762u.f6935c = l2.longValue();
                            c1762u.f6936d = m2987b;
                            c1762u.f6937e = m2987b2;
                            c1762u.f6938f = m2987b3;
                            if (num == null) {
                                intValue = 0;
                            } else {
                                intValue = num.intValue();
                            }
                            c1762u.f6939g = intValue;
                            if (num2 == null) {
                                intValue2 = c1762u.f6934b.f6880f;
                            } else {
                                intValue2 = num2.intValue();
                            }
                            c1762u.f6940h = intValue2;
                            this.f6862a.m3004s();
                            return;
                        }
                        this.f6862a.m2996k(9, "Host result has invalid timestamp");
                    }
                } finally {
                }
            }
        } catch (RuntimeException e10) {
            synchronized (this.f6862a.f6965a) {
                this.f6862a.m2996k(9, "Host capture result is incomplete");
                Log.w("v", "Invalid Camera2 capture result", e10);
            }
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
        synchronized (this.f6862a.f6965a) {
            try {
                Object tag = captureRequest.getTag();
                C1762u c1762u = this.f6862a.f6956F;
                if ((tag instanceof Long) && c1762u != null && ((Long) tag).longValue() == c1762u.f6944l) {
                    this.f6862a.m2996k(9, "Host camera rejected capture");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.hardware.camera2.CameraCaptureSession.CaptureCallback
    public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
        synchronized (this.f6862a.f6965a) {
            try {
                C1764v c1764v = this.f6862a;
                C1762u c1762u = c1764v.f6956F;
                if (c1762u != null && c1762u.f6943k == i) {
                    c1764v.m2996k(8, "Host capture sequence was aborted");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

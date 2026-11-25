package io.twoyi.hal;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import ba.C0587n;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.h0 */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1737h0 implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ C1741j0 f6826i;

    /* renamed from: j */
    public final /* synthetic */ ParcelFileDescriptor f6827j;

    /* renamed from: k */
    public final /* synthetic */ int f6828k;

    /* renamed from: l */
    public final /* synthetic */ String f6829l;

    /* renamed from: m */
    public final /* synthetic */ long f6830m;

    public /* synthetic */ RunnableC1737h0(C1741j0 c1741j0, ParcelFileDescriptor parcelFileDescriptor, int i, String str, long j3) {
        this.f6826i = c1741j0;
        this.f6827j = parcelFileDescriptor;
        this.f6828k = i;
        this.f6829l = str;
        this.f6830m = j3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C1741j0 c1741j0 = this.f6826i;
        ParcelFileDescriptor parcelFileDescriptor = this.f6827j;
        int i = this.f6828k;
        String str = this.f6829l;
        long j3 = this.f6830m;
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor)));
            try {
                try {
                    C0587n m2940d = c1741j0.m2940d(i, j3, str);
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeInt(m2940d.f2441b);
                    byte[] bArr = (byte[]) m2940d.f2442c;
                    dataOutputStream.writeInt(bArr.length);
                    dataOutputStream.write(bArr);
                    byte[] bArr2 = (byte[]) m2940d.f2443d;
                    dataOutputStream.writeInt(bArr2.length);
                    dataOutputStream.write(bArr2);
                    dataOutputStream.close();
                } catch (IOException e10) {
                    dataOutputStream.writeBoolean(false);
                    dataOutputStream.writeUTF(String.valueOf(e10.getMessage()));
                    dataOutputStream.close();
                }
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e11) {
            Log.w("j0", "shell client went away: " + e11);
        }
    }
}

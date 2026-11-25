package io.twoyi.hal;

import android.media.AudioTrack;
import android.net.LocalSocket;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructPollfd;
import android.util.Log;
import io.twoyi.host.BinderC1809u;
import io.twoyi.utils.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import p081h0.AbstractC1474y0;
import p089h8.AbstractC1589y;
import p278z.AbstractC4540b;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.s0 */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1759s0 implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f6920i;

    /* renamed from: j */
    public final /* synthetic */ Object f6921j;

    /* renamed from: k */
    public final /* synthetic */ Object f6922k;

    public /* synthetic */ RunnableC1759s0(int i, Object obj, Object obj2) {
        this.f6920i = i;
        this.f6922k = obj;
        this.f6921j = obj2;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [io.twoyi.hal.x0, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        AudioTrack audioTrack;
        StringBuilder sb;
        switch (this.f6920i) {
            case AbstractC4695a.f17215a /* 0 */:
                C1761t0 c1761t0 = (C1761t0) this.f6922k;
                LocalSocket localSocket = (LocalSocket) this.f6921j;
                c1761t0.getClass();
                try {
                    InputStream inputStream = localSocket.getInputStream();
                    OutputStream outputStream = localSocket.getOutputStream();
                    int m2981e = C1761t0.m2981e(inputStream);
                    if (m2981e == 16 && !KernelSuHal.isSupported()) {
                        Log.i("t0", "KernelSU HAL unsupported in this backend");
                        IOUtils.closeSilently(localSocket);
                    } else if (!c1761t0.m2983a(m2981e)) {
                        C1761t0.m2982f(outputStream, m2981e);
                        IOUtils.closeSilently(localSocket);
                    } else {
                        C1761t0.m2982f(outputStream, m2981e);
                        int i = 1;
                        switch (m2981e) {
                            case AbstractC4695a.f17215a /* 0 */:
                                C1770y c1770y = C1770y.f7003e;
                                c1770y.getClass();
                                C1770y.f7001c.execute(new RunnableC1759s0(i, c1770y, localSocket));
                                break;
                            case 1:
                                C1770y.f7003e.getClass();
                                ?? obj = new Object();
                                obj.f6998b = new AtomicBoolean();
                                obj.f6999c = new Object();
                                obj.f6997a = localSocket;
                                Thread thread = new Thread(new RunnableC1766w(obj, 0), "twoyi_audio_record_close");
                                thread.setDaemon(true);
                                thread.start();
                                AbstractC1589y.m2664j(new C1768x(0, obj), true, "android.permission.RECORD_AUDIO");
                                break;
                            case 2:
                                AbstractC1765v0.f6991a.getClass();
                                TwoyiResolverHal.m2867a(localSocket);
                                break;
                            case 3:
                                C1725c1.f6763c.m2879a(localSocket);
                                break;
                            case 4:
                                C1763u0.f6946d.m2984a(localSocket);
                                break;
                            case AbstractC4540b.f16788j /* 5 */:
                                C1735g0.f6817i.m2934f(localSocket);
                                break;
                            case AbstractC4540b.f16786h /* 6 */:
                                C1735g0.f6817i.m2933e(localSocket);
                                break;
                            case 7:
                                Log.i("t0", "Bluetooth HAL disabled; reject type-7 request");
                                IOUtils.closeSilently(localSocket);
                                break;
                            case 8:
                                C1757r0.f6888t.m2969j(localSocket);
                                break;
                            case 9:
                                AbstractC1742k.m2948h(c1761t0.f6932f, localSocket);
                                break;
                            case 10:
                                C1770y.f7003e.m3011c(localSocket);
                                break;
                            case 11:
                                C1721b0.f6732d.m2874a(localSocket);
                                break;
                            case AbstractC1474y0.f5961f /* 12 */:
                                C1731e1.f6781t.m2920x(localSocket);
                                break;
                            case 13:
                                C1769x0 c1769x0 = C1769x0.f6996e;
                                Log.d("x0", "HAL handleRil");
                                c1769x0.f6997a = localSocket;
                                ((HandlerC1767w0) c1769x0.f7000d).sendEmptyMessageDelayed(2, 200L);
                                break;
                            case 14:
                                C1722b1.f6736h.m2876a(localSocket);
                                break;
                            case AbstractC4540b.f16789k /* 15 */:
                                C1719a1.f6728e.m2871b(localSocket);
                                break;
                            case 16:
                                KernelSuHal.handle(localSocket);
                                IOUtils.closeSilently(localSocket);
                                break;
                            case 17:
                                C1741j0.f6847c.m2938a(localSocket);
                                break;
                            default:
                                Log.w("t0", "unknown hal type: " + m2981e);
                                IOUtils.closeSilently(localSocket);
                                break;
                        }
                    }
                    return;
                } catch (IOException e10) {
                    Log.e("t0", "HAL socket request failed", e10);
                    IOUtils.closeSilently(localSocket);
                    return;
                }
            case 1:
                C1770y c1770y2 = (C1770y) this.f6922k;
                LocalSocket localSocket2 = (LocalSocket) this.f6921j;
                c1770y2.getClass();
                long j3 = 0;
                try {
                    audioTrack = C1770y.m3009b();
                    try {
                        audioTrack.play();
                        Log.i("y", "host playback session started");
                        InputStream inputStream2 = localSocket2.getInputStream();
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = inputStream2.read(bArr);
                            if (read >= 0) {
                                if (read != 0) {
                                    int i10 = 0;
                                    while (i10 < read) {
                                        int write = audioTrack.write(bArr, i10, read - i10, 0);
                                        if (write <= 0) {
                                            Log.w("y", "AudioTrack.write failed: " + write);
                                        } else {
                                            i10 += write;
                                        }
                                    }
                                    j3 += read;
                                }
                            }
                        }
                        C1770y.m3010d(audioTrack);
                        C1770y.m3008a(localSocket2);
                        sb = new StringBuilder("host playback session closed, bytes=");
                    } catch (Throwable th) {
                        th = th;
                        try {
                            Log.w("y", "audio playback session failed", th);
                            C1770y.m3010d(audioTrack);
                            C1770y.m3008a(localSocket2);
                            sb = new StringBuilder("host playback session closed, bytes=");
                            sb.append(j3);
                            Log.i("y", sb.toString());
                            return;
                        } catch (Throwable th2) {
                            C1770y.m3010d(audioTrack);
                            C1770y.m3008a(localSocket2);
                            Log.i("y", "host playback session closed, bytes=" + j3);
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    audioTrack = null;
                }
                sb.append(j3);
                Log.i("y", sb.toString());
                return;
            default:
                C1735g0 c1735g0 = (C1735g0) this.f6922k;
                C1733f0 c1733f0 = (C1733f0) this.f6921j;
                c1735g0.getClass();
                StructPollfd structPollfd = new StructPollfd();
                structPollfd.fd = c1733f0.f6805a.getFileDescriptor();
                structPollfd.events = (short) 0;
                StructPollfd[] structPollfdArr = {structPollfd};
                while (!c1733f0.f6807c) {
                    try {
                        structPollfd.revents = (short) 0;
                        try {
                            if (Os.poll(structPollfdArr, BinderC1809u.f7171g0) > 0 && (((short) (OsConstants.POLLERR | OsConstants.POLLHUP | OsConstants.POLLNVAL)) & structPollfd.revents) != 0) {
                                C1735g0.m2928j(c1733f0);
                                return;
                            }
                        } catch (ErrnoException e11) {
                            if (e11.errno != OsConstants.EINTR) {
                                throw e11;
                            }
                        }
                    } catch (ErrnoException e12) {
                        if (!c1733f0.f6807c && e12.errno != OsConstants.EBADF) {
                            Log.w("g0", "Camera peer-close watcher failed", e12);
                            C1735g0.m2928j(c1733f0);
                            return;
                        }
                        return;
                    }
                }
                return;
        }
    }
}

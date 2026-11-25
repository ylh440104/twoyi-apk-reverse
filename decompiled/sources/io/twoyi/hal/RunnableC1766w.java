package io.twoyi.hal;

import android.media.AudioRecord;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructPollfd;
import android.util.Log;
import io.twoyi.host.BinderC1809u;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.w */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1766w implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ int f6992i;

    /* renamed from: j */
    public final /* synthetic */ C1769x0 f6993j;

    public /* synthetic */ RunnableC1766w(C1769x0 c1769x0, int i) {
        this.f6992i = i;
        this.f6993j = c1769x0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AudioRecord audioRecord;
        String str;
        StringBuilder sb;
        String sb2;
        int i = this.f6992i;
        C1769x0 c1769x0 = this.f6993j;
        switch (i) {
            case AbstractC4695a.f17215a /* 0 */:
                AtomicBoolean atomicBoolean = (AtomicBoolean) c1769x0.f6998b;
                StructPollfd structPollfd = new StructPollfd();
                structPollfd.fd = c1769x0.f6997a.getFileDescriptor();
                structPollfd.events = (short) OsConstants.POLLIN;
                do {
                    try {
                        try {
                            if (!atomicBoolean.get()) {
                                structPollfd.revents = (short) 0;
                            }
                        } catch (ErrnoException e10) {
                            if (!atomicBoolean.get() && e10.errno != OsConstants.EBADF) {
                                ExecutorService executorService = C1770y.f7001c;
                                Log.w("y", "recording close watcher failed", e10);
                            }
                        }
                        return;
                    } finally {
                        c1769x0.m3006a();
                    }
                } while (Os.poll(new StructPollfd[]{structPollfd}, BinderC1809u.f7171g0) <= 0);
                return;
            default:
                long j3 = 0;
                try {
                } catch (Throwable th) {
                    th = th;
                    audioRecord = null;
                }
                if (((AtomicBoolean) c1769x0.f6998b).get()) {
                    synchronized (c1769x0.f6999c) {
                        try {
                            if (((AudioRecord) c1769x0.f7000d) == null) {
                                c1769x0.f7000d = null;
                            }
                        } finally {
                        }
                    }
                } else {
                    int minBufferSize = AudioRecord.getMinBufferSize(8000, 16, 2);
                    if (minBufferSize <= 0) {
                        ExecutorService executorService2 = C1770y.f7001c;
                        Log.w("y", "AudioRecord minimum buffer unavailable: " + minBufferSize);
                        synchronized (c1769x0.f6999c) {
                            try {
                                if (((AudioRecord) c1769x0.f7000d) == null) {
                                    c1769x0.f7000d = null;
                                }
                            } finally {
                            }
                        }
                    } else {
                        audioRecord = new AudioRecord(1, 8000, 16, 2, Math.max(minBufferSize, 1280));
                        try {
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                if (!((AtomicBoolean) c1769x0.f6998b).get()) {
                                    ExecutorService executorService3 = C1770y.f7001c;
                                    Log.w("y", "audio recording session failed", th);
                                }
                                synchronized (c1769x0.f6999c) {
                                    try {
                                        if (((AudioRecord) c1769x0.f7000d) == audioRecord) {
                                            c1769x0.f7000d = null;
                                        }
                                    } finally {
                                    }
                                }
                                C1769x0.m3005b(audioRecord);
                                c1769x0.m3006a();
                                ExecutorService executorService4 = C1770y.f7001c;
                                str = "y";
                                sb = new StringBuilder("host recording session closed, bytes=");
                                sb.append(j3);
                                sb2 = sb.toString();
                                Log.i(str, sb2);
                                return;
                            } catch (Throwable th3) {
                                synchronized (c1769x0.f6999c) {
                                    try {
                                        if (((AudioRecord) c1769x0.f7000d) == audioRecord) {
                                            c1769x0.f7000d = null;
                                        }
                                        C1769x0.m3005b(audioRecord);
                                        c1769x0.m3006a();
                                        ExecutorService executorService5 = C1770y.f7001c;
                                        Log.i("y", "host recording session closed, bytes=0");
                                        throw th3;
                                    } finally {
                                    }
                                }
                            }
                        }
                        if (audioRecord.getState() != 1) {
                            ExecutorService executorService6 = C1770y.f7001c;
                            Log.w("y", "AudioRecord initialization failed");
                            synchronized (c1769x0.f6999c) {
                                try {
                                    if (((AudioRecord) c1769x0.f7000d) == audioRecord) {
                                        c1769x0.f7000d = null;
                                    }
                                } finally {
                                }
                            }
                            C1769x0.m3005b(audioRecord);
                        } else {
                            synchronized (c1769x0.f6999c) {
                                if (((AtomicBoolean) c1769x0.f6998b).get()) {
                                    synchronized (c1769x0.f6999c) {
                                        try {
                                            if (((AudioRecord) c1769x0.f7000d) == audioRecord) {
                                                c1769x0.f7000d = null;
                                            }
                                        } finally {
                                        }
                                    }
                                    C1769x0.m3005b(audioRecord);
                                } else {
                                    c1769x0.f7000d = audioRecord;
                                    audioRecord.startRecording();
                                    ExecutorService executorService7 = C1770y.f7001c;
                                    Log.i("y", "host recording session started");
                                    byte[] bArr = new byte[1280];
                                    OutputStream outputStream = c1769x0.f6997a.getOutputStream();
                                    while (true) {
                                        if (!((AtomicBoolean) c1769x0.f6998b).get()) {
                                            int read = audioRecord.read(bArr, 0, 1280, 0);
                                            if (read <= 0) {
                                                if (!((AtomicBoolean) c1769x0.f6998b).get()) {
                                                    ExecutorService executorService8 = C1770y.f7001c;
                                                    Log.w("y", "AudioRecord.read failed: " + read);
                                                }
                                            } else {
                                                outputStream.write(bArr, 0, read);
                                                j3 += read;
                                            }
                                        }
                                    }
                                    synchronized (c1769x0.f6999c) {
                                        try {
                                            if (((AudioRecord) c1769x0.f7000d) == audioRecord) {
                                                c1769x0.f7000d = null;
                                            }
                                        } finally {
                                        }
                                    }
                                    C1769x0.m3005b(audioRecord);
                                    c1769x0.m3006a();
                                    ExecutorService executorService9 = C1770y.f7001c;
                                    str = "y";
                                    sb = new StringBuilder("host recording session closed, bytes=");
                                    sb.append(j3);
                                    sb2 = sb.toString();
                                }
                            }
                            Log.i(str, sb2);
                            return;
                        }
                    }
                    str = "y";
                    sb2 = "host recording session closed, bytes=0";
                    Log.i(str, sb2);
                    return;
                }
                c1769x0.m3006a();
                ExecutorService executorService10 = C1770y.f7001c;
                str = "y";
                sb2 = "host recording session closed, bytes=0";
                Log.i(str, sb2);
                return;
        }
    }
}

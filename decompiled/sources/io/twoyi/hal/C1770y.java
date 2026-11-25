package io.twoyi.hal;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.net.LocalSocket;
import android.util.Log;
import io.twoyi.utils.IOUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p198r3.AbstractC3356a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.y */
/* loaded from: classes.dex */
public final class C1770y {

    /* renamed from: c */
    public static final ExecutorService f7001c = Executors.newSingleThreadExecutor();

    /* renamed from: d */
    public static final ExecutorService f7002d = Executors.newSingleThreadExecutor();

    /* renamed from: e */
    public static final C1770y f7003e = new C1770y();

    /* renamed from: a */
    public final Object f7004a = new Object();

    /* renamed from: b */
    public WeakReference f7005b;

    /* renamed from: a */
    public static void m3008a(LocalSocket localSocket) {
        try {
            localSocket.shutdownOutput();
        } catch (IOException unused) {
        }
        try {
            localSocket.shutdownInput();
        } catch (IOException unused2) {
        }
        IOUtils.closeSilently(localSocket);
    }

    /* renamed from: b */
    public static AudioTrack m3009b() {
        int minBufferSize = AudioTrack.getMinBufferSize(44100, 12, 2);
        if (minBufferSize > 0) {
            AudioTrack build = new AudioTrack.Builder().setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).build()).setAudioFormat(new AudioFormat.Builder().setSampleRate(44100).setChannelMask(12).setEncoding(2).build()).setBufferSizeInBytes(Math.max(minBufferSize, 8192)).setTransferMode(1).build();
            if (build.getState() == 1) {
                return build;
            }
            build.release();
            throw new IllegalStateException("AudioTrack initialization failed");
        }
        throw new IllegalStateException(AbstractC3356a.m5776b(minBufferSize, "AudioTrack minimum buffer unavailable: "));
    }

    /* renamed from: d */
    public static void m3010d(AudioTrack audioTrack) {
        if (audioTrack != null) {
            try {
                if (audioTrack.getPlayState() == 3) {
                    audioTrack.stop();
                }
            } catch (RuntimeException unused) {
            }
            try {
                audioTrack.flush();
            } catch (RuntimeException unused2) {
            }
            try {
                audioTrack.release();
            } catch (RuntimeException unused3) {
            }
        }
    }

    /* renamed from: c */
    public final void m3011c(LocalSocket localSocket) {
        int i;
        try {
            try {
                int m2981e = C1761t0.m2981e(localSocket.getInputStream());
                if (m2981e == 1) {
                    i = m3012e(C1761t0.m2981e(localSocket.getInputStream()));
                } else {
                    Log.w("y", "unknown audio control command: " + m2981e);
                    i = -22;
                }
                C1761t0.m2982f(localSocket.getOutputStream(), i);
                m3008a(localSocket);
            } catch (IOException e10) {
                Log.w("y", "audio control session failed", e10);
                m3008a(localSocket);
            }
        } catch (Throwable th) {
            m3008a(localSocket);
            throw th;
        }
    }

    /* renamed from: e */
    public final int m3012e(int i) {
        Context context;
        AudioManager audioManager;
        if (i >= 0 && i <= 3) {
            WeakReference weakReference = this.f7005b;
            if (weakReference == null) {
                context = null;
            } else {
                context = (Context) weakReference.get();
            }
            if (context == null || (audioManager = (AudioManager) context.getSystemService(AudioManager.class)) == null) {
                return -19;
            }
            synchronized (this.f7004a) {
                try {
                    try {
                        audioManager.setMode(i);
                        Log.i("y", "host audio mode=" + i);
                    } catch (RuntimeException e10) {
                        Log.w("y", "AudioManager.setMode failed for mode " + i, e10);
                        return -5;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return 0;
        }
        return -22;
    }
}

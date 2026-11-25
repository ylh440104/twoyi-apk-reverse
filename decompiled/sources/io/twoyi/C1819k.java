package io.twoyi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.text.TextUtils;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.Random;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import p027c3.C0679e;
import p247w0.AbstractC4176k;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.k */
/* loaded from: classes.dex */
public final class C1819k implements GLSurfaceView.Renderer {

    /* renamed from: V */
    public static final Object[][] f7232V;

    /* renamed from: A */
    public int f7233A;

    /* renamed from: B */
    public int f7234B;

    /* renamed from: C */
    public int f7235C;

    /* renamed from: D */
    public int f7236D;

    /* renamed from: E */
    public int f7237E;

    /* renamed from: F */
    public int f7238F;

    /* renamed from: G */
    public int[] f7239G;

    /* renamed from: H */
    public int[] f7240H;

    /* renamed from: I */
    public int f7241I;

    /* renamed from: J */
    public int f7242J;

    /* renamed from: K */
    public boolean f7243K;

    /* renamed from: O */
    public FloatBuffer f7247O;

    /* renamed from: P */
    public ShortBuffer f7248P;

    /* renamed from: Q */
    public FloatBuffer f7249Q;

    /* renamed from: R */
    public FloatBuffer f7250R;

    /* renamed from: S */
    public final float f7251S;

    /* renamed from: T */
    public final float f7252T;

    /* renamed from: a */
    public final BootLogTexture f7254a;

    /* renamed from: d */
    public int f7257d;

    /* renamed from: e */
    public int f7258e;

    /* renamed from: j */
    public int f7263j;

    /* renamed from: k */
    public int f7264k;

    /* renamed from: l */
    public int f7265l;

    /* renamed from: m */
    public int f7266m;

    /* renamed from: n */
    public int f7267n;

    /* renamed from: o */
    public int f7268o;

    /* renamed from: p */
    public int f7269p;

    /* renamed from: q */
    public int f7270q;

    /* renamed from: r */
    public int f7271r;

    /* renamed from: s */
    public int f7272s;

    /* renamed from: t */
    public int f7273t;

    /* renamed from: u */
    public int f7274u;

    /* renamed from: v */
    public int f7275v;

    /* renamed from: w */
    public int f7276w;

    /* renamed from: x */
    public int f7277x;

    /* renamed from: y */
    public int f7278y;

    /* renamed from: z */
    public int f7279z;

    /* renamed from: b */
    public final Random f7255b = new Random();

    /* renamed from: c */
    public final C1815i[] f7256c = new C1815i[5];

    /* renamed from: f */
    public final float[] f7259f = new float[16];

    /* renamed from: g */
    public final float[] f7260g = new float[16];

    /* renamed from: h */
    public final float[] f7261h = new float[16];

    /* renamed from: i */
    public final float[] f7262i = new float[16];

    /* renamed from: L */
    public final C0679e f7244L = new C0679e(10240);

    /* renamed from: M */
    public final C0679e f7245M = new C0679e(128);

    /* renamed from: N */
    public final C0679e f7246N = new C0679e(80);

    /* renamed from: U */
    public final float[] f7253U = new float[4];

    static {
        Float valueOf = Float.valueOf(-26.0f);
        Float valueOf2 = Float.valueOf(0.35f);
        Float valueOf3 = Float.valueOf(0.5f);
        Float valueOf4 = Float.valueOf(0.7f);
        Boolean bool = Boolean.FALSE;
        Object[] objArr = {valueOf, valueOf2, valueOf3, valueOf4, 120, 20, bool};
        Float valueOf5 = Float.valueOf(-12.0f);
        Float valueOf6 = Float.valueOf(0.55f);
        Float valueOf7 = Float.valueOf(0.75f);
        Float valueOf8 = Float.valueOf(0.8f);
        Float valueOf9 = Float.valueOf(AbstractC4176k.f15279f);
        Float valueOf10 = Float.valueOf(1.0f);
        Float valueOf11 = Float.valueOf(0.9f);
        Boolean bool2 = Boolean.TRUE;
        f7232V = new Object[][]{objArr, new Object[]{valueOf5, valueOf6, valueOf7, valueOf8, 96, 22, bool}, new Object[]{valueOf9, valueOf8, valueOf10, valueOf11, 72, 30, bool2}, new Object[]{Float.valueOf(10.0f), Float.valueOf(0.95f), Float.valueOf(1.3f), Float.valueOf(1.15f), 56, 24, bool2}, new Object[]{Float.valueOf(18.0f), valueOf10, Float.valueOf(1.6f), Float.valueOf(1.45f), 40, 18, bool2}};
    }

    public C1819k(BootLogTexture bootLogTexture, float f4) {
        this.f7254a = bootLogTexture;
        this.f7251S = 10.0f * f4;
        this.f7252T = f4 * 8.0f;
        for (int i = 0; i < 5; i++) {
            Object[] objArr = f7232V[i];
            this.f7256c[i] = new C1815i(((Float) objArr[0]).floatValue(), ((Float) objArr[1]).floatValue(), ((Float) objArr[2]).floatValue(), ((Integer) objArr[5]).intValue(), ((Boolean) objArr[6]).booleanValue());
        }
    }

    /* renamed from: a */
    public static void m3126a(int i, int i10) {
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, i);
        GLES20.glUniform1i(i10, 0);
    }

    /* renamed from: b */
    public static int m3127b(String str, String str2) {
        int m3128c = m3128c(35633, str);
        int m3128c2 = m3128c(35632, str2);
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, m3128c);
        GLES20.glAttachShader(glCreateProgram, m3128c2);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 0) {
            GLES20.glDeleteShader(m3128c);
            GLES20.glDeleteShader(m3128c2);
            return glCreateProgram;
        }
        throw new RuntimeException("program link failed: " + GLES20.glGetProgramInfoLog(glCreateProgram));
    }

    /* renamed from: c */
    public static int m3128c(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        throw new RuntimeException("shader compile failed: " + GLES20.glGetShaderInfoLog(glCreateShader));
    }

    /* renamed from: d */
    public final void m3129d(C0679e c0679e, float[] fArr) {
        if (c0679e.f2857c == 0) {
            return;
        }
        GLES20.glUseProgram(this.f7263j);
        GLES20.glUniformMatrix4fv(this.f7264k, 1, false, fArr, 0);
        m3126a(this.f7236D, this.f7265l);
        this.f7247O.clear();
        this.f7247O.put((float[]) c0679e.f2858d, 0, c0679e.f2856b);
        this.f7247O.position(0);
        GLES20.glBindBuffer(34962, this.f7237E);
        GLES20.glBufferData(34962, c0679e.f2856b * 4, this.f7247O, 35040);
        this.f7248P.clear();
        this.f7248P.put((short[]) c0679e.f2859e, 0, c0679e.f2857c);
        this.f7248P.position(0);
        GLES20.glBindBuffer(34963, this.f7238F);
        GLES20.glBufferData(34963, c0679e.f2857c * 2, this.f7248P, 35040);
        GLES20.glEnableVertexAttribArray(this.f7266m);
        GLES20.glVertexAttribPointer(this.f7266m, 3, 5126, false, 36, 0);
        GLES20.glEnableVertexAttribArray(this.f7267n);
        GLES20.glVertexAttribPointer(this.f7267n, 2, 5126, false, 36, 12);
        GLES20.glEnableVertexAttribArray(this.f7268o);
        GLES20.glVertexAttribPointer(this.f7268o, 4, 5126, false, 36, 20);
        GLES20.glDrawElements(4, c0679e.f2857c, 5123, 0);
        GLES20.glDisableVertexAttribArray(this.f7266m);
        GLES20.glDisableVertexAttribArray(this.f7267n);
        GLES20.glDisableVertexAttribArray(this.f7268o);
    }

    /* renamed from: e */
    public final void m3130e(int i, int i10) {
        this.f7249Q.position(0);
        GLES20.glEnableVertexAttribArray(i);
        GLES20.glVertexAttribPointer(i, 2, 5126, false, 16, (Buffer) this.f7249Q);
        this.f7249Q.position(2);
        GLES20.glEnableVertexAttribArray(i10);
        GLES20.glVertexAttribPointer(i10, 2, 5126, false, 16, (Buffer) this.f7249Q);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glDisableVertexAttribArray(i);
        GLES20.glDisableVertexAttribArray(i10);
    }

    /* renamed from: f */
    public final void m3131f(C1815i c1815i, C1817j c1817j, float f4, float f10) {
        boolean z10;
        boolean z11;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16 = c1815i.f7216g;
        float f17 = (-f16) / 2.0f;
        float f18 = f16 / 2.0f;
        for (int i = 0; i < c1817j.f7229e; i++) {
            float f19 = c1817j.f7227c;
            float f20 = c1815i.f7217h;
            float f21 = f19 - (i * f20);
            if (f21 + f20 >= f17 && f21 - f20 <= f18) {
                if (i == 0 && !c1817j.f7230f) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                if (!z10 && c1817j.f7226b[i] != 0) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                float f22 = c1815i.f7215f[i] * c1815i.f7211b;
                float f23 = 1.0f;
                if (z11) {
                    f22 = Math.min(1.0f, f22 * 1.8f);
                }
                float f24 = f22;
                float[] m3132g = m3132g(c1817j.f7225a[i]);
                float f25 = c1815i.f7210a;
                float f26 = c1815i.f7217h;
                float f27 = m3132g[0];
                float f28 = m3132g[1];
                float f29 = m3132g[2];
                float f30 = m3132g[3];
                if (z10) {
                    f12 = 0.8117647f;
                } else {
                    if (z11) {
                        f11 = 0.56078434f;
                    } else {
                        f11 = 0.0627451f;
                    }
                    f12 = f11;
                }
                if (z10) {
                    f13 = 1.0f;
                } else {
                    if (!z11) {
                        f23 = 0.8352941f;
                    }
                    f13 = f23;
                }
                if (z10) {
                    f15 = 0.8392157f;
                } else {
                    if (z11) {
                        f14 = 0.6745098f;
                    } else {
                        f14 = 0.26666668f;
                    }
                    f15 = f14;
                }
                this.f7244L.m1455a(f4, f21, f25, f10, f26, f27, f28, f29, f30, f12, f13, f15, f24);
                if (z10 && c1815i.f7213d) {
                    this.f7245M.m1455a(f4, f21, c1815i.f7210a, f10, c1815i.f7217h, m3132g[0], m3132g[1], m3132g[2], m3132g[3], 0.8117647f, 1.0f, 0.8392157f, 1.0f);
                }
            }
        }
    }

    /* renamed from: g */
    public final float[] m3132g(char c6) {
        int i;
        if (c6 >= '!' && c6 <= '~') {
            i = c6 - '!';
        } else {
            i = 30;
        }
        float f4 = ((i % 16) * 64) / 1024.0f;
        float[] fArr = this.f7253U;
        fArr[0] = f4;
        float f10 = ((i / 16) * 128) / 1024.0f;
        fArr[1] = f10;
        fArr[2] = f4 + 0.0625f;
        fArr[3] = f10 + 0.125f;
        return fArr;
    }

    /* renamed from: h */
    public final void m3133h(C1817j c1817j) {
        byte[] bArr = c1817j.f7226b;
        Arrays.fill(bArr, (byte) 0);
        for (int i = 2; i < c1817j.f7229e; i++) {
            if (this.f7255b.nextFloat() < 0.1f) {
                c1817j.f7225a[i] = this.f7254a.m2754a();
                bArr[i] = 1;
            }
        }
    }

    /* renamed from: i */
    public final void m3134i(long j3) {
        double d6 = ((float) j3) * 0.001f;
        Matrix.setLookAtM(this.f7261h, 0, (((float) Math.sin(0.4d * d6)) * 1.2f) + 0.5f, (((float) Math.sin((d6 * 0.25d) + 1.2999999523162842d)) * 0.8f) + 0.3f, 30.0f, AbstractC4176k.f15279f, AbstractC4176k.f15279f, -2.0f, AbstractC4176k.f15279f, 1.0f, AbstractC4176k.f15279f);
        Matrix.multiplyMM(this.f7259f, 0, this.f7260g, 0, this.f7261h, 0);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onDrawFrame(GL10 gl10) {
        long j3;
        long uptimeMillis = SystemClock.uptimeMillis();
        m3134i(uptimeMillis);
        C1815i[] c1815iArr = this.f7256c;
        int length = c1815iArr.length;
        int i = 0;
        while (true) {
            float f4 = 2.0f;
            if (i >= length) {
                break;
            }
            C1815i c1815i = c1815iArr[i];
            BootLogTexture bootLogTexture = this.f7254a;
            float f10 = c1815i.f7216g;
            int i10 = c1815i.f7214e;
            float f11 = (-f10) / 2.0f;
            int i11 = 0;
            while (i11 < c1815i.f7219j) {
                C1715h c1715h = c1815i.f7221l[i11];
                float f12 = f4;
                long j10 = c1715h.f6705e;
                C1817j[] c1817jArr = c1715h.f6701a;
                if (uptimeMillis < j10) {
                    j3 = uptimeMillis;
                } else {
                    if (c1715h.f6703c) {
                        C1817j c1817j = c1817jArr[c1715h.f6702b ^ 1];
                        c1817j.f7227c -= c1815i.f7217h;
                        m3133h(c1817j);
                        if ((i10 * c1815i.f7217h) + c1817j.f7227c < f11) {
                            c1715h.f6703c = false;
                        }
                    }
                    int i12 = c1715h.f6702b;
                    C1817j c1817j2 = c1817jArr[i12];
                    float f13 = c1817j2.f7227c;
                    j3 = uptimeMillis;
                    float f14 = c1815i.f7217h;
                    float f15 = f13 - f14;
                    if (f15 < f11) {
                        c1817j2.f7227c = f15;
                        c1817j2.f7230f = true;
                        c1715h.f6703c = true;
                        int i13 = i12 ^ 1;
                        c1715h.f6702b = i13;
                        C1817j c1817j3 = c1817jArr[i13];
                        c1817j3.f7230f = false;
                        c1817j3.f7229e = 0;
                        c1817j3.f7227c = ((c1815i.f7216g / 12.0f) * this.f7255b.nextFloat()) + (c1815i.f7216g / f12) + f14;
                        c1817j3.f7228d = bootLogTexture.m2754a();
                        long nextInt = (this.f7255b.nextInt(61) + 60) / c1815i.f7212c;
                        c1715h.f6704d = nextInt;
                        c1715h.f6705e = j3 + nextInt;
                    } else {
                        char[] cArr = c1817j2.f7225a;
                        System.arraycopy(cArr, 0, cArr, 1, i10 - 1);
                        cArr[0] = c1817j2.f7228d;
                        if (c1817j2.f7229e > 0) {
                            cArr[1] = bootLogTexture.m2754a();
                        }
                        int i14 = c1817j2.f7229e;
                        if (i14 < i10) {
                            c1817j2.f7229e = i14 + 1;
                        }
                        m3133h(c1817j2);
                        c1817j2.f7227c = f15;
                        c1715h.f6705e = j3 + c1715h.f6704d;
                    }
                }
                i11++;
                f4 = f12;
                uptimeMillis = j3;
            }
            i++;
        }
        C0679e c0679e = this.f7244L;
        c0679e.f2856b = 0;
        c0679e.f2857c = 0;
        C0679e c0679e2 = this.f7245M;
        c0679e2.f2856b = 0;
        c0679e2.f2857c = 0;
        for (C1815i c1815i2 : this.f7256c) {
            float f16 = c1815i2.f7217h * 0.5f;
            for (int i15 = 0; i15 < c1815i2.f7219j; i15++) {
                float f17 = c1815i2.f7220k;
                float f18 = c1815i2.f7218i;
                float f19 = (f18 / 2.0f) + (i15 * f18) + f17;
                C1715h c1715h2 = c1815i2.f7221l[i15];
                m3131f(c1815i2, c1715h2.f6701a[c1715h2.f6702b], f19, f16);
                if (c1715h2.f6703c) {
                    m3131f(c1815i2, c1715h2.f6701a[c1715h2.f6702b ^ 1], f19, f16);
                }
            }
        }
        C0679e c0679e3 = this.f7246N;
        c0679e3.f2856b = 0;
        c0679e3.f2857c = 0;
        String str = this.f7254a.f6602p;
        if (!TextUtils.isEmpty(str)) {
            float f20 = this.f7251S * 1.3f;
            float f21 = f20 * 0.5f;
            int min = Math.min(str.length(), (int) ((this.f7257d - (this.f7252T * 2.0f)) / f21));
            float f22 = (f20 / 2.0f) + this.f7252T;
            for (int i16 = 0; i16 < min; i16++) {
                char charAt = str.charAt(i16);
                if (!Character.isWhitespace(charAt)) {
                    float[] m3132g = m3132g(charAt);
                    this.f7246N.m1455a((f21 / 2.0f) + (i16 * f21) + this.f7252T, f22, AbstractC4176k.f15279f, f21, f20, m3132g[0], m3132g[1], m3132g[2], m3132g[3], 0.47843137f, 0.65882355f, 0.5254902f, 0.9f);
                }
            }
        }
        if (this.f7243K) {
            GLES20.glBindFramebuffer(36160, this.f7239G[0]);
            GLES20.glViewport(0, 0, this.f7241I, this.f7242J);
            GLES20.glClear(16384);
            m3129d(this.f7245M, this.f7259f);
            GLES20.glDisable(3042);
            GLES20.glUseProgram(this.f7269p);
            for (int i17 = 0; i17 < 2; i17++) {
                GLES20.glBindFramebuffer(36160, this.f7239G[1]);
                m3126a(this.f7240H[0], this.f7270q);
                GLES20.glUniform2f(this.f7271r, 1.0f / this.f7241I, AbstractC4176k.f15279f);
                m3130e(this.f7272s, this.f7273t);
                GLES20.glBindFramebuffer(36160, this.f7239G[0]);
                m3126a(this.f7240H[1], this.f7270q);
                GLES20.glUniform2f(this.f7271r, AbstractC4176k.f15279f, 1.0f / this.f7242J);
                m3130e(this.f7272s, this.f7273t);
            }
            GLES20.glEnable(3042);
        }
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glViewport(0, 0, this.f7257d, this.f7258e);
        GLES20.glClear(16384);
        m3129d(this.f7244L, this.f7259f);
        float[] fArr = this.f7262i;
        C0679e c0679e4 = this.f7246N;
        if (c0679e4.f2857c > 0) {
            float f23 = (this.f7251S * 1.3f) + (this.f7252T * 2.0f);
            this.f7250R.clear();
            FloatBuffer floatBuffer = this.f7250R;
            float f24 = this.f7257d;
            floatBuffer.put(new float[]{AbstractC4176k.f15279f, AbstractC4176k.f15279f, f24, AbstractC4176k.f15279f, AbstractC4176k.f15279f, f23, f24, f23});
            this.f7250R.position(0);
            GLES20.glUseProgram(this.f7279z);
            GLES20.glUniformMatrix4fv(this.f7233A, 1, false, fArr, 0);
            GLES20.glUniform4f(this.f7234B, AbstractC4176k.f15279f, AbstractC4176k.f15279f, AbstractC4176k.f15279f, 0.6f);
            GLES20.glEnableVertexAttribArray(this.f7235C);
            GLES20.glVertexAttribPointer(this.f7235C, 2, 5126, false, 0, (Buffer) this.f7250R);
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f7235C);
        }
        m3129d(c0679e4, fArr);
        if (this.f7243K) {
            GLES20.glBlendFunc(1, 1);
            GLES20.glUseProgram(this.f7274u);
            GLES20.glUniform1f(this.f7276w, 1.4f);
            m3126a(this.f7240H[0], this.f7275v);
            m3130e(this.f7277x, this.f7278y);
            GLES20.glBlendFunc(770, 771);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceChanged(GL10 gl10, int i, int i10) {
        this.f7257d = i;
        this.f7258e = i10;
        GLES20.glViewport(0, 0, i, i10);
        float f4 = i;
        float f10 = i10;
        float f11 = f4 / f10;
        Matrix.perspectiveM(this.f7260g, 0, 45.0f, f11, 1.0f, 100.0f);
        m3134i(SystemClock.uptimeMillis());
        Matrix.orthoM(this.f7262i, 0, AbstractC4176k.f15279f, f4, AbstractC4176k.f15279f, f10, -1.0f, 1.0f);
        long uptimeMillis = SystemClock.uptimeMillis();
        int i11 = 0;
        while (true) {
            C1815i[] c1815iArr = this.f7256c;
            if (i11 >= c1815iArr.length) {
                break;
            }
            Object[] objArr = f7232V[i11];
            C1815i c1815i = c1815iArr[i11];
            int intValue = ((Integer) objArr[4]).intValue();
            float floatValue = ((Float) objArr[3]).floatValue();
            float tan = (float) (Math.tan(Math.toRadians(22.5d)) * (30.0f - c1815i.f7210a) * 2.0d);
            c1815i.f7216g = tan;
            float f12 = tan * f11;
            float f13 = tan / intValue;
            c1815i.f7217h = f13;
            float f14 = f13 * 0.75f * floatValue;
            c1815i.f7218i = f14;
            int max = Math.max(1, (int) (f12 / f14));
            c1815i.f7219j = max;
            float f15 = 2.0f;
            c1815i.f7220k = ((-max) * c1815i.f7218i) / 2.0f;
            c1815i.f7221l = new C1715h[max];
            for (int i12 = 0; i12 < c1815i.f7219j; i12++) {
                c1815i.f7221l[i12] = new C1715h(c1815i.f7214e);
            }
            int i13 = 0;
            while (i13 < c1815i.f7219j) {
                C1715h c1715h = c1815i.f7221l[i13];
                c1715h.f6702b = 0;
                c1715h.f6703c = false;
                C1817j c1817j = c1715h.f6701a[0];
                c1817j.f7230f = false;
                c1817j.f7229e = 0;
                c1817j.f7227c = ((c1815i.f7216g / 8.0f) * this.f7255b.nextFloat()) + (c1815i.f7216g / f15) + c1815i.f7217h;
                c1817j.f7228d = this.f7254a.m2754a();
                c1715h.f6704d = (r14.nextInt(61) + 60) / c1815i.f7212c;
                c1715h.f6705e = r14.nextInt(500) + uptimeMillis;
                i13++;
                f15 = 2.0f;
            }
            i11++;
        }
        this.f7241I = Math.max(1, i / 2);
        this.f7242J = Math.max(1, i10 / 2);
        int[] iArr = this.f7239G;
        if (iArr != null) {
            GLES20.glDeleteFramebuffers(iArr.length, iArr, 0);
            int[] iArr2 = this.f7240H;
            GLES20.glDeleteTextures(iArr2.length, iArr2, 0);
        }
        int[] iArr3 = new int[2];
        this.f7239G = iArr3;
        this.f7240H = new int[2];
        GLES20.glGenFramebuffers(2, iArr3, 0);
        GLES20.glGenTextures(2, this.f7240H, 0);
        this.f7243K = true;
        for (int i14 = 0; i14 < 2; i14++) {
            GLES20.glBindTexture(3553, this.f7240H[i14]);
            GLES20.glTexImage2D(3553, 0, 6408, this.f7241I, this.f7242J, 0, 6408, 5121, null);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLES20.glBindFramebuffer(36160, this.f7239G[i14]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f7240H[i14], 0);
            if (GLES20.glCheckFramebufferStatus(36160) != 36053) {
                this.f7243K = false;
            }
        }
        GLES20.glBindFramebuffer(36160, 0);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(AbstractC4176k.f15279f, AbstractC4176k.f15279f, AbstractC4176k.f15279f, 1.0f);
        GLES20.glDisable(2929);
        GLES20.glEnable(3042);
        int m3127b = m3127b("uniform mat4 uMVP;\nattribute vec3 aPos;\nattribute vec2 aUV;\nattribute vec4 aColor;\nvarying vec2 vUV;\nvarying vec4 vColor;\nvoid main() {\n  gl_Position = uMVP * vec4(aPos, 1.0);\n  vUV = aUV;\n  vColor = aColor;\n}\n", "precision mediump float;\nuniform sampler2D uTex;\nvarying vec2 vUV;\nvarying vec4 vColor;\nvoid main() {\n  gl_FragColor = vec4(vColor.rgb, vColor.a * texture2D(uTex, vUV).a);\n}\n");
        this.f7263j = m3127b;
        this.f7264k = GLES20.glGetUniformLocation(m3127b, "uMVP");
        this.f7265l = GLES20.glGetUniformLocation(this.f7263j, "uTex");
        this.f7266m = GLES20.glGetAttribLocation(this.f7263j, "aPos");
        this.f7267n = GLES20.glGetAttribLocation(this.f7263j, "aUV");
        this.f7268o = GLES20.glGetAttribLocation(this.f7263j, "aColor");
        int m3127b2 = m3127b("attribute vec2 aPos;\nattribute vec2 aUV;\nvarying vec2 vUV;\nvoid main() {\n  gl_Position = vec4(aPos, 0.0, 1.0);\n  vUV = aUV;\n}\n", "precision mediump float;\nuniform sampler2D uTex;\nuniform vec2 uDir;\nvarying vec2 vUV;\nvoid main() {\n  vec4 sum = texture2D(uTex, vUV) * 0.2270270270;\n  vec2 o1 = uDir * 1.3846153846;\n  vec2 o2 = uDir * 3.2307692308;\n  sum += texture2D(uTex, vUV + o1) * 0.3162162162;\n  sum += texture2D(uTex, vUV - o1) * 0.3162162162;\n  sum += texture2D(uTex, vUV + o2) * 0.0702702703;\n  sum += texture2D(uTex, vUV - o2) * 0.0702702703;\n  gl_FragColor = sum;\n}\n");
        this.f7269p = m3127b2;
        this.f7270q = GLES20.glGetUniformLocation(m3127b2, "uTex");
        this.f7271r = GLES20.glGetUniformLocation(this.f7269p, "uDir");
        this.f7272s = GLES20.glGetAttribLocation(this.f7269p, "aPos");
        this.f7273t = GLES20.glGetAttribLocation(this.f7269p, "aUV");
        int m3127b3 = m3127b("attribute vec2 aPos;\nattribute vec2 aUV;\nvarying vec2 vUV;\nvoid main() {\n  gl_Position = vec4(aPos, 0.0, 1.0);\n  vUV = aUV;\n}\n", "precision mediump float;\nuniform sampler2D uTex;\nuniform float uIntensity;\nvarying vec2 vUV;\nvoid main() {\n  gl_FragColor = texture2D(uTex, vUV) * uIntensity;\n}\n");
        this.f7274u = m3127b3;
        this.f7275v = GLES20.glGetUniformLocation(m3127b3, "uTex");
        this.f7276w = GLES20.glGetUniformLocation(this.f7274u, "uIntensity");
        this.f7277x = GLES20.glGetAttribLocation(this.f7274u, "aPos");
        this.f7278y = GLES20.glGetAttribLocation(this.f7274u, "aUV");
        int m3127b4 = m3127b("uniform mat4 uMVP;\nattribute vec2 aPos;\nvoid main() {\n  gl_Position = uMVP * vec4(aPos, 0.0, 1.0);\n}\n", "precision mediump float;\nuniform vec4 uColor;\nvoid main() {\n  gl_FragColor = uColor;\n}\n");
        this.f7279z = m3127b4;
        this.f7233A = GLES20.glGetUniformLocation(m3127b4, "uMVP");
        this.f7234B = GLES20.glGetUniformLocation(this.f7279z, "uColor");
        this.f7235C = GLES20.glGetAttribLocation(this.f7279z, "aPos");
        Bitmap createBitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setColor(-1);
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(96.0f);
        for (char c6 = '!'; c6 <= '~'; c6 = (char) (c6 + 1)) {
            int i = c6 - '!';
            canvas.drawText(String.valueOf(c6), ((64.0f - paint.measureText(String.valueOf(c6))) / 2.0f) + ((i % 16) * 64), ((i / 16) * 128) + 100.0f, paint);
        }
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexParameteri(3553, 10241, 9987);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLUtils.texImage2D(3553, 0, createBitmap, 0);
        GLES20.glGenerateMipmap(3553);
        createBitmap.recycle();
        this.f7236D = iArr[0];
        int[] iArr2 = new int[2];
        GLES20.glGenBuffers(2, iArr2, 0);
        this.f7237E = iArr2[0];
        this.f7238F = iArr2[1];
        this.f7247O = ByteBuffer.allocateDirect(1474560).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f7248P = ByteBuffer.allocateDirect(122880).order(ByteOrder.nativeOrder()).asShortBuffer();
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f7249Q = asFloatBuffer;
        asFloatBuffer.put(new float[]{-1.0f, -1.0f, AbstractC4176k.f15279f, AbstractC4176k.f15279f, 1.0f, -1.0f, 1.0f, AbstractC4176k.f15279f, -1.0f, 1.0f, AbstractC4176k.f15279f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
        this.f7249Q.position(0);
        this.f7250R = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f7239G = null;
        this.f7243K = false;
    }
}

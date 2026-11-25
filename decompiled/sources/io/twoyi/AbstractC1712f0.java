package io.twoyi;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.text.Layout;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import io.twoyi.host.EnumC1774a;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.security.KeyStore;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import na.AbstractC2689d;
import na.AbstractC2697l;
import na.C2699n;
import p008a7.C0106x;
import p011aa.AbstractC0242c;
import p011aa.C0240b;
import p011aa.C0251j;
import p011aa.C0258q;
import p011aa.EnumC0238a;
import p018b3.AbstractC0446o;
import p029c5.C0698f;
import p032c9.C0719b;
import p032c9.C0721d;
import p038d3.AbstractC0811d;
import p041d7.C0853u;
import p050e3.C0934a;
import p050e3.C0938e;
import p050e3.C0945l;
import p066f7.C1139j;
import p066f7.C1140k;
import p067f8.C1174f1;
import p067f8.EnumC1168e1;
import p068f9.AbstractC1283a;
import p068f9.AbstractC1294l;
import p068f9.AbstractC1301s;
import p069fa.C1315n;
import p069fa.C1317p;
import p088h7.C1556h;
import p089h8.AbstractC1589y;
import p098i5.InterfaceC1658e;
import p101i8.C1674j;
import p101i8.C1681q;
import p101i8.C1683s;
import p101i8.C1685u;
import p114j8.AbstractC1932l;
import p114j8.AbstractC1933m;
import p114j8.C1930j;
import p114j8.C1940t;
import p114j8.C1942v;
import p116ja.C1968i;
import p116ja.RunnableC1965f;
import p117jb.C1975a;
import p125k6.C2024e;
import p127k8.C2045c;
import p135l4.AbstractC2229i0;
import p151m9.C2436g;
import p162o.C2743w;
import p167o4.AbstractC2777a;
import p170o7.AbstractC2780a;
import p171o8.AbstractC2784c;
import p183p8.C2996b;
import p198r3.AbstractC3356a;
import p239v1.C4128u;
import p247w0.AbstractC4176k;
import p255w8.InterfaceC4252c;
import p265x8.AbstractC4450j;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.f0 */
/* loaded from: classes.dex */
public abstract class AbstractC1712f0 {

    /* renamed from: a */
    public static boolean f6693a;

    /* renamed from: b */
    public static final /* synthetic */ int f6694b = 0;

    /* JADX WARN: Type inference failed for: r0v1, types: [c9.d, c9.b] */
    /* renamed from: A */
    public static C0721d m2807A(Collection collection) {
        AbstractC4450j.m7255e(collection, "<this>");
        return new C0719b(0, collection.size() - 1, 1);
    }

    /* renamed from: B */
    public static int m2808B(List list) {
        AbstractC4450j.m7255e(list, "<this>");
        return list.size() - 1;
    }

    /* renamed from: C */
    public static float m2809C(int i, String[] strArr) {
        float parseFloat = Float.parseFloat(strArr[i]);
        if (parseFloat >= AbstractC4176k.f15279f && parseFloat <= 1.0f) {
            return parseFloat;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
    }

    /* renamed from: D */
    public static SecretKey m2810D() {
        KeyStore.SecretKeyEntry secretKeyEntry;
        SecretKey secretKey = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyStore.Entry entry = keyStore.getEntry("twin_provider_key", null);
            if (entry instanceof KeyStore.SecretKeyEntry) {
                secretKeyEntry = (KeyStore.SecretKeyEntry) entry;
            } else {
                secretKeyEntry = null;
            }
            if (secretKeyEntry != null) {
                secretKey = secretKeyEntry.getSecretKey();
            }
        } catch (Exception unused) {
        }
        if (secretKey != null) {
            return secretKey;
        }
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder("twin_provider_key", 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).setUserAuthenticationRequired(false).build());
        SecretKey generateKey = keyGenerator.generateKey();
        AbstractC4450j.m7254d(generateKey, "generateKey(...)");
        return generateKey;
    }

    /* renamed from: E */
    public static final int m2811E(int i, int i10) {
        return (i >> i10) & 31;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.app.Application$ActivityLifecycleCallbacks, java.lang.Object] */
    /* renamed from: F */
    public static synchronized void m2812F(ContextWrapper contextWrapper) {
        synchronized (AbstractC1712f0.class) {
            if (f6693a) {
                return;
            }
            boolean z10 = true;
            f6693a = true;
            Application application = (Application) contextWrapper.getApplicationContext();
            AbstractC1589y.f6353c = application;
            application.registerActivityLifecycleCallbacks(new Object());
            if (AbstractC2689d.m4546m() < 0) {
                z10 = false;
            }
            if (z10) {
                Renderer.load();
            }
        }
    }

    /* renamed from: G */
    public static final boolean m2813G(Context context) {
        AbstractC4450j.m7255e(context, "context");
        if (m2835c(context) != null) {
            return true;
        }
        return false;
    }

    /* renamed from: H */
    public static boolean m2814H(String str, String str2) {
        if (str.startsWith(str2.concat("(")) && str.endsWith(")")) {
            return true;
        }
        return false;
    }

    /* renamed from: I */
    public static boolean m2815I(C1556h c1556h) {
        String str = c1556h.f6251e;
        String str2 = c1556h.f6249c;
        String obj = AbstractC1294l.m2254X0(str).toString();
        if (obj.length() > 0 && !obj.equals(c1556h.f6248b) && !obj.equals(str2) && !AbstractC1301s.m2264n0(obj, ".".concat(str2))) {
            return true;
        }
        return false;
    }

    /* renamed from: J */
    public static List m2816J(Object obj) {
        List singletonList = Collections.singletonList(obj);
        AbstractC4450j.m7254d(singletonList, "singletonList(...)");
        return singletonList;
    }

    /* renamed from: K */
    public static List m2817K(Object... objArr) {
        AbstractC4450j.m7255e(objArr, "elements");
        if (objArr.length > 0) {
            return AbstractC1932l.m3246j0(objArr);
        }
        return C1940t.f7630i;
    }

    /* renamed from: L */
    public static C1140k m2818L(String str) {
        InputStream resourceAsStream = C1140k.class.getClassLoader().getResourceAsStream("prompts/".concat(str));
        if (resourceAsStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, AbstractC1283a.f5006a), 8192);
            try {
                String m4858l = AbstractC2780a.m4858l(bufferedReader);
                bufferedReader.close();
                C1674j m2135d = new C1139j(AbstractC1294l.m2243M0(m4858l, "\n")).m2135d(C1942v.f7632i);
                List list = (List) m2135d.f6542i;
                String str2 = (String) m2135d.f6543j;
                if (str2 == null) {
                    return new C1140k(list);
                }
                throw new IllegalArgumentException(AbstractC3356a.m5779e("Unexpected {% ", str2, " %}").toString());
            } finally {
            }
        } else {
            throw new IllegalArgumentException("Missing prompt template: ".concat(str));
        }
    }

    /* renamed from: M */
    public static final long m2819M(float f4, long j3) {
        if (!Float.isNaN(f4) && f4 < 1.0f) {
            return C4128u.m6834b(C4128u.m6836d(j3) * f4, j3);
        }
        return j3;
    }

    /* renamed from: N */
    public static ArrayList m2820N(Object... objArr) {
        AbstractC4450j.m7255e(objArr, "elements");
        if (objArr.length == 0) {
            return new ArrayList();
        }
        return new ArrayList(new C1930j(objArr, true));
    }

    /* renamed from: O */
    public static C1174f1 m2821O(String str, ArrayList arrayList) {
        String obj;
        if (str != null && (obj = AbstractC1294l.m2254X0(str).toString()) != null) {
            if (obj.length() <= 0) {
                obj = null;
            }
            if (obj != null) {
                ArrayList arrayList2 = C1174f1.f4784c;
                ArrayList arrayList3 = new ArrayList();
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList2.get(i);
                    i++;
                    String lowerCase = ((EnumC1168e1) obj2).name().toLowerCase(Locale.ROOT);
                    AbstractC4450j.m7254d(lowerCase, "toLowerCase(...)");
                    if (arrayList.contains(lowerCase)) {
                        arrayList3.add(obj2);
                    }
                }
                Set m3269G0 = AbstractC1933m.m3269G0(arrayList3);
                C1174f1 c1174f1 = new C1174f1(m3269G0, obj);
                if (!m3269G0.isEmpty()) {
                    return c1174f1;
                }
            }
        }
        return null;
    }

    /* renamed from: Q */
    public static final List m2822Q(List list) {
        int size = list.size();
        if (size != 0) {
            if (size != 1) {
                return list;
            }
            return m2816J(list.get(0));
        }
        return C1940t.f7630i;
    }

    /* renamed from: R */
    public static int m2823R(CharSequence charSequence, int i) {
        while (i < charSequence.length() && (charSequence.charAt(i) == ' ' || charSequence.charAt(i) == '\t')) {
            i++;
        }
        return i;
    }

    /* renamed from: S */
    public static void m2824S(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() != null && colorStateList != null && colorStateList.isStateful()) {
            int[] drawableState = textInputLayout.getDrawableState();
            int[] drawableState2 = checkableImageButton.getDrawableState();
            int length = drawableState.length;
            int[] copyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
            System.arraycopy(drawableState2, 0, copyOf, length, drawableState2.length);
            int colorForState = colorStateList.getColorForState(copyOf, colorStateList.getDefaultColor());
            Drawable mutate = drawable.mutate();
            mutate.setTintList(ColorStateList.valueOf(colorForState));
            checkableImageButton.setImageDrawable(mutate);
        }
    }

    /* renamed from: T */
    public static int m2825T(Context context, int i, int i10) {
        TypedValue m4575N = AbstractC2697l.m4575N(context, i);
        if (m4575N != null && m4575N.type == 16) {
            return m4575N.data;
        }
        return i10;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:53:0x010e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x012c A[Catch: NumberFormatException -> 0x0140, LOOP:3: B:47:0x00fc->B:57:0x012c, LOOP_END, TryCatch #1 {NumberFormatException -> 0x0140, blocks: (B:44:0x00e7, B:47:0x00fc, B:49:0x0102, B:53:0x010e, B:57:0x012c, B:61:0x0132, B:66:0x0147, B:79:0x014c, B:81:0x014f, B:86:0x015b, B:87:0x0160, B:89:0x0161, B:90:0x0166), top: B:43:0x00e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0132 A[Catch: NumberFormatException -> 0x0140, TryCatch #1 {NumberFormatException -> 0x0140, blocks: (B:44:0x00e7, B:47:0x00fc, B:49:0x0102, B:53:0x010e, B:57:0x012c, B:61:0x0132, B:66:0x0147, B:79:0x014c, B:81:0x014f, B:86:0x015b, B:87:0x0160, B:89:0x0161, B:90:0x0166), top: B:43:0x00e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0147 A[Catch: NumberFormatException -> 0x0140, TryCatch #1 {NumberFormatException -> 0x0140, blocks: (B:44:0x00e7, B:47:0x00fc, B:49:0x0102, B:53:0x010e, B:57:0x012c, B:61:0x0132, B:66:0x0147, B:79:0x014c, B:81:0x014f, B:86:0x015b, B:87:0x0160, B:89:0x0161, B:90:0x0166), top: B:43:0x00e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0183 A[SYNTHETIC] */
    /* renamed from: U */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.animation.TimeInterpolator m2826U(android.content.Context r18, int r19, android.animation.TimeInterpolator r20) {
        /*
            Method dump skipped, instructions count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.AbstractC1712f0.m2826U(android.content.Context, int, android.animation.TimeInterpolator):android.animation.TimeInterpolator");
    }

    /* renamed from: V */
    public static final void m2827V(View view, InterfaceC1658e interfaceC1658e) {
        AbstractC4450j.m7255e(view, "<this>");
        view.setTag(R.id.view_tree_saved_state_registry_owner, interfaceC1658e);
    }

    /* renamed from: W */
    public static void m2828W(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        boolean z10;
        Field field = AbstractC2229i0.f8541a;
        boolean hasOnClickListeners = checkableImageButton.hasOnClickListeners();
        boolean z11 = false;
        int i = 1;
        if (onLongClickListener != null) {
            z10 = true;
        } else {
            z10 = false;
        }
        if (hasOnClickListeners || z10) {
            z11 = true;
        }
        checkableImageButton.setFocusable(z11);
        checkableImageButton.setClickable(hasOnClickListeners);
        checkableImageButton.setPressable(hasOnClickListeners);
        checkableImageButton.setLongClickable(z10);
        if (!z11) {
            i = 2;
        }
        checkableImageButton.setImportantForAccessibility(i);
    }

    /* renamed from: Y */
    public static final int m2829Y(C2743w c2743w) {
        int m4731c;
        int i = c2743w.f9909b;
        int m4731c2 = c2743w.m4731c(0);
        while (c2743w.f9909b != 0 && c2743w.m4731c(0) == m4731c2) {
            c2743w.m4734f(0, c2743w.m4732d());
            c2743w.m4733e(c2743w.f9909b - 1);
            int i10 = c2743w.f9909b;
            int i11 = i10 >>> 1;
            int i12 = 0;
            while (i12 < i11) {
                int m4731c3 = c2743w.m4731c(i12);
                int i13 = (i12 + 1) * 2;
                int i14 = i13 - 1;
                int m4731c4 = c2743w.m4731c(i14);
                if (i13 < i10 && (m4731c = c2743w.m4731c(i13)) > m4731c4) {
                    if (m4731c > m4731c3) {
                        c2743w.m4734f(i12, m4731c);
                        c2743w.m4734f(i13, m4731c3);
                        i12 = i13;
                    }
                } else if (m4731c4 > m4731c3) {
                    c2743w.m4734f(i12, m4731c4);
                    c2743w.m4734f(i14, m4731c3);
                    i12 = i14;
                }
            }
        }
        return m4731c2;
    }

    /* renamed from: Z */
    public static void m2830Z() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    /* renamed from: a */
    public static final long m2831a(float f4, float f10) {
        return (Float.floatToRawIntBits(f10) & 4294967295L) | (Float.floatToRawIntBits(f4) << 32);
    }

    /* renamed from: a0 */
    public static void m2832a0() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v4, types: [aa.c, aa.q] */
    /* JADX WARN: Type inference failed for: r1v1, types: [aa.h, java.lang.Object] */
    /* renamed from: b */
    public static C0258q m2833b(InterfaceC4252c interfaceC4252c) {
        C0240b c0240b = AbstractC0242c.f884d;
        AbstractC4450j.m7255e(c0240b, "from");
        ?? obj = new Object();
        C0251j c0251j = c0240b.f885a;
        obj.f896a = c0251j.f898a;
        boolean z10 = c0251j.f900c;
        obj.f897b = c0251j.f899b;
        String str = c0251j.f901d;
        String str2 = c0251j.f902e;
        EnumC0238a enumC0238a = c0251j.f904g;
        boolean z11 = c0251j.f903f;
        C2024e c2024e = c0240b.f886b;
        interfaceC4252c.mo169i(obj);
        if (AbstractC4450j.m7251a(str, "    ")) {
            C0251j c0251j2 = new C0251j(obj.f896a, obj.f897b, z10, str, str2, z11, enumC0238a);
            AbstractC4450j.m7255e(c2024e, "module");
            return new AbstractC0242c(c0251j2, c2024e);
        }
        throw new IllegalArgumentException("Indent should not be specified when default printing mode is used");
    }

    /* renamed from: b0 */
    public static final C1683s m2834b0(String str) {
        int i;
        AbstractC4695a.m7567f(10);
        int length = str.length();
        if (length != 0) {
            int i10 = 0;
            char charAt = str.charAt(0);
            if (AbstractC4450j.m7256f(charAt, 48) < 0) {
                i = 1;
                if (length == 1 || charAt != '+') {
                    return null;
                }
            } else {
                i = 0;
            }
            int i11 = 119304647;
            while (i < length) {
                int digit = Character.digit((int) str.charAt(i), 10);
                if (digit >= 0) {
                    if (Integer.compareUnsigned(i10, i11) > 0) {
                        if (i11 == 119304647) {
                            i11 = Integer.divideUnsigned(-1, 10);
                            if (Integer.compareUnsigned(i10, i11) > 0) {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    }
                    int i12 = i10 * 10;
                    int i13 = digit + i12;
                    if (Integer.compareUnsigned(i13, i12) < 0) {
                        return null;
                    }
                    i++;
                    i10 = i13;
                } else {
                    return null;
                }
            }
            return new C1683s(i10);
        }
        return null;
    }

    /* renamed from: c */
    public static final EnumC1774a m2835c(Context context) {
        String string;
        AbstractC4450j.m7255e(context, "context");
        Object obj = null;
        if (Build.VERSION.SDK_INT < 34 || (string = context.getApplicationContext().getSharedPreferences("twin_agent", 4).getString("phone_mode", null)) == null) {
            return null;
        }
        C2996b c2996b = EnumC1774a.f7035l;
        c2996b.getClass();
        C1681q c1681q = new C1681q(4, c2996b);
        while (true) {
            if (!c1681q.hasNext()) {
                break;
            }
            Object next = c1681q.next();
            if (AbstractC1301s.m2265o0(((EnumC1774a) next).name(), string)) {
                obj = next;
                break;
            }
        }
        return (EnumC1774a) obj;
    }

    /* renamed from: c0 */
    public static final C1685u m2836c0(String str) {
        AbstractC4450j.m7255e(str, "<this>");
        AbstractC4695a.m7567f(10);
        int length = str.length();
        if (length != 0) {
            int i = 0;
            char charAt = str.charAt(0);
            if (AbstractC4450j.m7256f(charAt, 48) < 0) {
                i = 1;
                if (length == 1 || charAt != '+') {
                    return null;
                }
            }
            long j3 = 10;
            long j10 = 0;
            long j11 = 512409557603043100L;
            while (i < length) {
                int digit = Character.digit((int) str.charAt(i), 10);
                if (digit >= 0) {
                    if (Long.compareUnsigned(j10, j11) > 0) {
                        if (j11 == 512409557603043100L) {
                            j11 = Long.divideUnsigned(-1L, j3);
                            if (Long.compareUnsigned(j10, j11) > 0) {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    }
                    long j12 = j10 * j3;
                    long j13 = (digit & 4294967295L) + j12;
                    if (Long.compareUnsigned(j13, j12) < 0) {
                        return null;
                    }
                    i++;
                    j10 = j13;
                } else {
                    return null;
                }
            }
            return new C1685u(j10);
        }
        return null;
    }

    /* renamed from: d */
    public static final Object[] m2837d(Object[] objArr, int i, Object obj, Object obj2) {
        Object[] objArr2 = new Object[objArr.length + 2];
        AbstractC1932l.m3255s0(objArr, objArr2, 0, i, 6);
        AbstractC1932l.m3253q0(objArr, objArr2, i + 2, i, objArr.length);
        objArr2[i] = obj;
        objArr2[i + 1] = obj2;
        return objArr2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0073, code lost:
    
        if (r0.isEmpty() == false) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0084  */
    /* renamed from: d0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean m2838d0(java.lang.Throwable r6, p255w8.InterfaceC4250a r7) {
        /*
            java.lang.String r0 = "<this>"
            p265x8.AbstractC4450j.m7255e(r6, r0)
            java.lang.Integer r0 = p203r8.AbstractC3378a.f12091a
            r1 = 0
            if (r0 == 0) goto L27
            int r0 = r0.intValue()
            r2 = 19
            if (r0 < r2) goto L13
            goto L27
        L13:
            java.lang.reflect.Method r0 = p193q8.AbstractC3240a.f11572b
            if (r0 == 0) goto L24
            java.lang.Object r0 = r0.invoke(r6, r1)
            if (r0 == 0) goto L24
            java.lang.Throwable[] r0 = (java.lang.Throwable[]) r0
            java.util.List r0 = p114j8.AbstractC1932l.m3246j0(r0)
            goto L34
        L24:
            j8.t r0 = p114j8.C1940t.f7630i
            goto L34
        L27:
            java.lang.Throwable[] r0 = r6.getSuppressed()
            java.lang.String r2 = "getSuppressed(...)"
            p265x8.AbstractC4450j.m7254d(r0, r2)
            java.util.List r0 = p114j8.AbstractC1932l.m3246j0(r0)
        L34:
            int r2 = r0.size()
            r3 = 0
            r4 = r3
        L3a:
            if (r4 >= r2) goto L4a
            java.lang.Object r5 = r0.get(r4)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            boolean r5 = r5 instanceof p154n1.C2540f
            if (r5 == 0) goto L47
            return r3
        L47:
            int r4 = r4 + 1
            goto L3a
        L4a:
            java.lang.Object r7 = r7.mo232a()     // Catch: java.lang.Throwable -> L6d
            n1.a r7 = (p154n1.C2535a) r7     // Catch: java.lang.Throwable -> L6d
            if (r7 == 0) goto L76
            java.util.List r0 = r7.f9300a     // Catch: java.lang.Throwable -> L6d
            boolean r2 = r7.f9301b     // Catch: java.lang.Throwable -> L6d
            if (r2 == 0) goto L6f
            int r2 = r0.size()     // Catch: java.lang.Throwable -> L6d
            r4 = r3
        L5d:
            if (r4 >= r2) goto L76
            java.lang.Object r5 = r0.get(r4)     // Catch: java.lang.Throwable -> L6d
            n1.b r5 = (p154n1.C2536b) r5     // Catch: java.lang.Throwable -> L6d
            b8.j1 r5 = r5.f9303b     // Catch: java.lang.Throwable -> L6d
            if (r5 == 0) goto L6a
            goto L75
        L6a:
            int r4 = r4 + 1
            goto L5d
        L6d:
            r7 = move-exception
            goto L81
        L6f:
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L6d
            if (r0 != 0) goto L76
        L75:
            r3 = 1
        L76:
            if (r3 == 0) goto L82
            n1.f r1 = new n1.f     // Catch: java.lang.Throwable -> L6d
            p265x8.AbstractC4450j.m7252b(r7)     // Catch: java.lang.Throwable -> L6d
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L6d
            goto L82
        L81:
            r1 = r7
        L82:
            if (r1 == 0) goto L87
            p101i8.AbstractC1664a.m2733a(r6, r1)
        L87:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.AbstractC1712f0.m2838d0(java.lang.Throwable, w8.a):boolean");
    }

    /* renamed from: e */
    public static final Object[] m2839e(int i, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length - 2];
        AbstractC1932l.m3255s0(objArr, objArr2, 0, i, 6);
        AbstractC1932l.m3253q0(objArr, objArr2, i, i + 2, objArr.length);
        return objArr2;
    }

    /* renamed from: f */
    public static final Object[] m2840f(int i, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length - 1];
        AbstractC1932l.m3255s0(objArr, objArr2, 0, i, 6);
        AbstractC1932l.m3253q0(objArr, objArr2, i, i + 1, objArr.length);
        return objArr2;
    }

    /* renamed from: g */
    public static final void m2841g(C2743w c2743w, int i) {
        if (c2743w.f9909b != 0 && (c2743w.m4731c(0) == i || c2743w.m4731c(c2743w.f9909b - 1) == i)) {
            return;
        }
        int i10 = c2743w.f9909b;
        c2743w.m4729a(i);
        while (i10 > 0) {
            int i11 = ((i10 + 1) >>> 1) - 1;
            int m4731c = c2743w.m4731c(i11);
            if (i <= m4731c) {
                break;
            }
            c2743w.m4734f(i10, m4731c);
            i10 = i11;
        }
        c2743w.m4734f(i10, i);
    }

    /* renamed from: h */
    public static void m2842h(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            if (colorStateList != null && colorStateList.isStateful()) {
                int[] drawableState = textInputLayout.getDrawableState();
                int[] drawableState2 = checkableImageButton.getDrawableState();
                int length = drawableState.length;
                int[] copyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
                System.arraycopy(drawableState2, 0, copyOf, length, drawableState2.length);
                drawable.setTintList(ColorStateList.valueOf(colorStateList.getColorForState(copyOf, colorStateList.getDefaultColor())));
            } else {
                drawable.setTintList(colorStateList);
            }
            if (mode != null) {
                drawable.setTintMode(mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    /* renamed from: i */
    public static boolean m2843i(ArrayList arrayList, int i, int i10) {
        C1975a c1975a = (C1975a) arrayList.get(i);
        C1975a c1975a2 = (C1975a) arrayList.get(i10);
        if (i > 0) {
            int i11 = i - 1;
            if (((C1975a) arrayList.get(i11)).f7762g == c1975a.f7762g + 1 && ((C1975a) arrayList.get(i11)).f7761f == c1975a.f7761f && ((C1975a) arrayList.get(i11)).f7757b == c1975a.f7757b - 1 && ((C1975a) arrayList.get(c1975a.f7762g + 1)).f7757b == c1975a2.f7757b + 1) {
                return true;
            }
            return false;
        }
        return false;
    }

    /* renamed from: j */
    public static ArrayList m2844j(Object... objArr) {
        if (objArr.length == 0) {
            return new ArrayList();
        }
        return new ArrayList(new C1930j(objArr, true));
    }

    /* renamed from: k */
    public static final Object m2845k(C1968i c1968i, AbstractC2784c abstractC2784c) {
        RunnableC1965f runnableC1965f;
        C2436g c2436g = new C2436g(1, AbstractC2689d.m4554v(abstractC2784c));
        c2436g.m4160s();
        C0106x c0106x = new C0106x(5, c2436g);
        int i = 0;
        if (c1968i.f7712m.compareAndSet(false, true)) {
            C2699n c2699n = C2699n.f9761a;
            c1968i.f7713n = C2699n.f9761a.mo4520g();
            C0698f c0698f = c1968i.f7708i.f5179i;
            RunnableC1965f runnableC1965f2 = new RunnableC1965f(c1968i, c0106x);
            c0698f.getClass();
            synchronized (c0698f) {
                ((ArrayDeque) c0698f.f2915j).add(runnableC1965f2);
                String str = ((C1315n) c1968i.f7709j.f5206j).f5131d;
                Iterator it = ((ArrayDeque) c0698f.f2916k).iterator();
                while (true) {
                    if (it.hasNext()) {
                        runnableC1965f = (RunnableC1965f) it.next();
                        if (AbstractC4450j.m7251a(((C1315n) runnableC1965f.f7705k.f7709j.f5206j).f5131d, str)) {
                            break;
                        }
                    } else {
                        Iterator it2 = ((ArrayDeque) c0698f.f2915j).iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                runnableC1965f = (RunnableC1965f) it2.next();
                                if (AbstractC4450j.m7251a(((C1315n) runnableC1965f.f7705k.f7709j.f5206j).f5131d, str)) {
                                    break;
                                }
                            } else {
                                runnableC1965f = null;
                                break;
                            }
                        }
                    }
                }
                if (runnableC1965f != null) {
                    runnableC1965f2.f7704j = runnableC1965f.f7704j;
                }
            }
            c0698f.m1497o();
            c2436g.m4162u(new C0853u(i, c1968i));
            return c2436g.m4159r();
        }
        throw new IllegalStateException("Already Executed");
    }

    /* renamed from: l */
    public static int m2846l(ArrayList arrayList, Comparable comparable) {
        int size = arrayList.size();
        AbstractC4450j.m7255e(arrayList, "<this>");
        int size2 = arrayList.size();
        if (size >= 0) {
            if (size <= size2) {
                int i = size - 1;
                int i10 = 0;
                while (i10 <= i) {
                    int i11 = (i10 + i) >>> 1;
                    int m2853s = m2853s((Comparable) arrayList.get(i11), comparable);
                    if (m2853s < 0) {
                        i10 = i11 + 1;
                    } else if (m2853s > 0) {
                        i = i11 - 1;
                    } else {
                        return i11;
                    }
                }
                return -(i10 + 1);
            }
            throw new IndexOutOfBoundsException("toIndex (" + size + ") is greater than size (" + size2 + ").");
        }
        throw new IllegalArgumentException(AbstractC3356a.m5777c(size, "fromIndex (0) is greater than toIndex (", ")."));
    }

    /* renamed from: m */
    public static C2045c m2847m(C2045c c2045c) {
        c2045c.m3498f();
        c2045c.f8079k = true;
        if (c2045c.f8078j > 0) {
            return c2045c;
        }
        return C2045c.f8076l;
    }

    /* renamed from: n */
    public static void m2848n(String str, boolean z10) {
        if (z10) {
        } else {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: o */
    public static void m2849o(int i) {
        if (i >= 0) {
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: p */
    public static void m2850p(Object obj, String str) {
        if (obj != null) {
        } else {
            throw new NullPointerException(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* renamed from: q */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void m2851q(android.content.Context r7) {
        /*
            android.content.Context r7 = r7.getApplicationContext()
            java.lang.Class<io.twoyi.GuestInstanceClient> r0 = io.twoyi.GuestInstanceClient.class
            monitor-enter(r0)
            io.twoyi.GuestInstanceClient r1 = io.twoyi.GuestInstanceClient.f6607D     // Catch: java.lang.Throwable -> L76
            monitor-exit(r0)
            r0 = 1
            r2 = 0
            if (r1 == 0) goto L28
            r1.f6628q = r0
            io.twoyi.q0 r3 = r1.f6620i
            if (r3 == 0) goto L19
            r3.mo2788a()     // Catch: android.os.RemoteException -> L19
            r3 = r0
            goto L1a
        L19:
            r3 = r2
        L1a:
            android.os.Handler r4 = r1.f6614c
            io.twoyi.q r5 = new io.twoyi.q
            r5.<init>(r1, r0)
            r4.post(r5)
            if (r3 == 0) goto L28
            r1 = r0
            goto L29
        L28:
            r1 = r2
        L29:
            if (r1 != 0) goto L4d
            boolean r3 = na.AbstractC2689d.m4555x(r7, r2)
            if (r3 == 0) goto L4d
            android.content.Intent r3 = new android.content.Intent     // Catch: java.lang.RuntimeException -> L45
            java.lang.Class r4 = na.AbstractC2689d.m4530K(r2)     // Catch: java.lang.RuntimeException -> L45
            r3.<init>(r7, r4)     // Catch: java.lang.RuntimeException -> L45
            java.lang.String r4 = "io.twoyi.action.SHUTDOWN_GUEST_INSTANCE"
            android.content.Intent r3 = r3.setAction(r4)     // Catch: java.lang.RuntimeException -> L45
            r7.startService(r3)     // Catch: java.lang.RuntimeException -> L45
            r1 = r0
            goto L4d
        L45:
            r3 = move-exception
            java.lang.String r4 = "f0"
            java.lang.String r5 = "unable to deliver guest shutdown"
            android.util.Log.e(r4, r5, r3)
        L4d:
            if (r1 == 0) goto L75
            r3 = 3000(0xbb8, double:1.482E-320)
            long r5 = android.os.SystemClock.uptimeMillis()
            long r5 = r5 + r3
        L56:
            boolean r1 = na.AbstractC2689d.m4555x(r7, r2)
            if (r1 == 0) goto L6c
            long r3 = android.os.SystemClock.uptimeMillis()
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L66
            r0 = r2
            goto L6c
        L66:
            r3 = 50
            android.os.SystemClock.sleep(r3)
            goto L56
        L6c:
            if (r0 != 0) goto L75
            java.lang.String r7 = "f0"
            java.lang.String r0 = "guest instance process still running after shutdown request"
            android.util.Log.w(r7, r0)
        L75:
            return
        L76:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L76
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.AbstractC1712f0.m2851q(android.content.Context):void");
    }

    /* renamed from: r */
    public static void m2852r(C2045c c2045c, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C1556h c1556h = (C1556h) it.next();
            c2045c.add(c1556h);
            m2852r(c2045c, c1556h.f6254h);
        }
    }

    /* renamed from: s */
    public static int m2853s(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    /* renamed from: t */
    public static ImageView.ScaleType m2854t(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 5) {
                            if (i != 6) {
                                return ImageView.ScaleType.CENTER;
                            }
                            return ImageView.ScaleType.CENTER_INSIDE;
                        }
                        return ImageView.ScaleType.CENTER_CROP;
                    }
                    return ImageView.ScaleType.FIT_END;
                }
                return ImageView.ScaleType.FIT_CENTER;
            }
            return ImageView.ScaleType.FIT_START;
        }
        return ImageView.ScaleType.FIT_XY;
    }

    /* renamed from: u */
    public static final C0938e m2855u(Context context) {
        int i;
        C2024e c2024e = new C2024e(19);
        context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 31) {
            i = C0945l.f3954a.m1919a(context);
        } else {
            i = 0;
        }
        return new C0938e(c2024e, new C0934a(i));
    }

    /* renamed from: v */
    public static C2045c m2856v() {
        return new C2045c(10);
    }

    /* renamed from: w */
    public static C1317p m2857w(String str) {
        AbstractC4450j.m7255e(str, "<this>");
        Matcher matcher = C1317p.f5137c.matcher(str);
        if (matcher.lookingAt()) {
            String group = matcher.group(1);
            AbstractC4450j.m7254d(group, "typeSubtype.group(1)");
            Locale locale = Locale.US;
            AbstractC4450j.m7254d(locale, "US");
            AbstractC4450j.m7254d(group.toLowerCase(locale), "this as java.lang.String).toLowerCase(locale)");
            String group2 = matcher.group(2);
            AbstractC4450j.m7254d(group2, "typeSubtype.group(2)");
            AbstractC4450j.m7254d(group2.toLowerCase(locale), "this as java.lang.String).toLowerCase(locale)");
            ArrayList arrayList = new ArrayList();
            Matcher matcher2 = C1317p.f5138d.matcher(str);
            int end = matcher.end();
            while (end < str.length()) {
                matcher2.region(end, str.length());
                if (matcher2.lookingAt()) {
                    String group3 = matcher2.group(1);
                    if (group3 == null) {
                        end = matcher2.end();
                    } else {
                        String group4 = matcher2.group(2);
                        if (group4 == null) {
                            group4 = matcher2.group(3);
                        } else if (AbstractC1301s.m2272v0(group4, "'", false) && AbstractC1301s.m2264n0(group4, "'") && group4.length() > 2) {
                            group4 = group4.substring(1, group4.length() - 1);
                            AbstractC4450j.m7254d(group4, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        arrayList.add(group3);
                        arrayList.add(group4);
                        end = matcher2.end();
                    }
                } else {
                    StringBuilder sb = new StringBuilder("Parameter is not formatted correctly: \"");
                    String substring = str.substring(end);
                    AbstractC4450j.m7254d(substring, "this as java.lang.String).substring(startIndex)");
                    sb.append(substring);
                    sb.append("\" for: \"");
                    throw new IllegalArgumentException(AbstractC3356a.m5780f(sb, str, '\"').toString());
                }
            }
            return new C1317p(str, (String[]) arrayList.toArray(new String[0]));
        }
        throw new IllegalArgumentException(("No subtype found for: \"" + str + '\"').toString());
    }

    /* renamed from: x */
    public static final InterfaceC1658e m2858x(View view) {
        InterfaceC1658e interfaceC1658e;
        AbstractC4450j.m7255e(view, "<this>");
        while (view != null) {
            Object tag = view.getTag(R.id.view_tree_saved_state_registry_owner);
            if (tag instanceof InterfaceC1658e) {
                interfaceC1658e = (InterfaceC1658e) tag;
            } else {
                interfaceC1658e = null;
            }
            if (interfaceC1658e != null) {
                return interfaceC1658e;
            }
            Object m4817P = AbstractC2777a.m4817P(view);
            if (m4817P instanceof View) {
                view = (View) m4817P;
            } else {
                view = null;
            }
        }
        return null;
    }

    /* renamed from: y */
    public static final float m2859y(Layout layout, int i, Paint paint) {
        int i10;
        float abs;
        float width;
        float lineLeft = layout.getLineLeft(i);
        ThreadLocal threadLocal = AbstractC0446o.f1800a;
        if (layout.getEllipsisCount(i) <= 0 || layout.getParagraphDirection(i) != 1 || lineLeft >= AbstractC4176k.f15279f) {
            return AbstractC4176k.f15279f;
        }
        float measureText = paint.measureText("…") + (layout.getPrimaryHorizontal(layout.getEllipsisStart(i) + layout.getLineStart(i)) - lineLeft);
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i);
        if (paragraphAlignment == null) {
            i10 = -1;
        } else {
            i10 = AbstractC0811d.f3524a[paragraphAlignment.ordinal()];
        }
        if (i10 == 1) {
            abs = Math.abs(lineLeft);
            width = (layout.getWidth() - measureText) / 2.0f;
        } else {
            abs = Math.abs(lineLeft);
            width = layout.getWidth() - measureText;
        }
        return width + abs;
    }

    /* renamed from: z */
    public static final float m2860z(Layout layout, int i, Paint paint) {
        float width;
        float width2;
        ThreadLocal threadLocal = AbstractC0446o.f1800a;
        if (layout.getEllipsisCount(i) > 0) {
            int i10 = -1;
            if (layout.getParagraphDirection(i) == -1 && layout.getWidth() < layout.getLineRight(i)) {
                float measureText = paint.measureText("…") + (layout.getLineRight(i) - layout.getPrimaryHorizontal(layout.getEllipsisStart(i) + layout.getLineStart(i)));
                Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i);
                if (paragraphAlignment != null) {
                    i10 = AbstractC0811d.f3524a[paragraphAlignment.ordinal()];
                }
                if (i10 == 1) {
                    width = layout.getWidth() - layout.getLineRight(i);
                    width2 = (layout.getWidth() - measureText) / 2.0f;
                } else {
                    width = layout.getWidth() - layout.getLineRight(i);
                    width2 = layout.getWidth() - measureText;
                }
                return width - width2;
            }
            return AbstractC4176k.f15279f;
        }
        return AbstractC4176k.f15279f;
    }

    /* renamed from: P */
    public abstract void m2861P(int i, int i10);

    /* renamed from: X */
    public abstract void m2862X();
}

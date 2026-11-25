package io.twoyi.p105ui.twin;

import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.Rational;
import androidx.lifecycle.AbstractC0305p0;
import androidx.lifecycle.EnumC0304p;
import io.twoyi.AbstractC1712f0;
import io.twoyi.GuestInstanceClient;
import io.twoyi.InterfaceC1829p;
import io.twoyi.InterfaceC1832q0;
import io.twoyi.R;
import io.twoyi.hal.C1772z;
import io.twoyi.host.BinderC1809u;
import io.twoyi.host.EnumC1774a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import na.AbstractC2697l;
import p000a.AbstractC0000a;
import p014b.AbstractActivityC0349n;
import p014b.AbstractC0352q;
import p014b.C0343i0;
import p014b.C0345j0;
import p015b0.C0386x;
import p022b8.C0501j;
import p024c.AbstractC0608j;
import p029c5.C0695c;
import p029c5.C0697e;
import p029c5.C0698f;
import p067f8.C1147a4;
import p067f8.C1171e4;
import p067f8.C1206k3;
import p067f8.C1277y3;
import p067f8.EnumC1259v0;
import p094i1.C1610f;
import p101i8.AbstractC1664a;
import p101i8.C1674j;
import p101i8.C1679o;
import p114j8.AbstractC1934n;
import p150m8.InterfaceC2410c;
import p151m9.AbstractC2484x;
import p196r0.C3348s;
import p245v7.C4159b;
import p247w0.AbstractC4176k;
import p258x0.AbstractC4332p;
import p258x0.C4288d1;
import p258x0.C4321l2;
import p265x8.AbstractC4450j;
import p265x8.AbstractC4460t;
import p265x8.C4445e;
import p286z7.C4677e;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* loaded from: classes.dex */
public final class MainActivity extends AbstractActivityC0349n implements InterfaceC1829p {

    /* renamed from: I */
    public static final /* synthetic */ int f7321I = 0;

    /* renamed from: B */
    public String f7322B;

    /* renamed from: C */
    public C1171e4 f7323C;

    /* renamed from: D */
    public final C4288d1 f7324D = AbstractC4332p.m7135t(Boolean.FALSE);

    /* renamed from: E */
    public Rect f7325E;

    /* renamed from: F */
    public C4677e f7326F;

    /* renamed from: G */
    public final C1679o f7327G;

    /* renamed from: H */
    public final C1772z f7328H;

    public MainActivity() {
        int i = 6;
        this.f7327G = AbstractC1664a.m2736d(new C3348s(i, this));
        this.f7328H = new C1772z(i, this);
    }

    /* renamed from: k */
    public final boolean m3153k() {
        return ((Boolean) this.f7327G.getValue()).booleanValue();
    }

    /* renamed from: l */
    public final void m3154l(Intent intent) {
        String str;
        if (intent != null) {
            str = intent.getAction();
        } else {
            str = null;
        }
        if (AbstractC4450j.m7251a(str, "io.twoyi.action.OPEN_SETTINGS")) {
            C1171e4 c1171e4 = this.f7323C;
            if (c1171e4 != null) {
                c1171e4.m2181f(C1206k3.f4852a);
            } else {
                AbstractC4450j.m7260j("vm");
                throw null;
            }
        }
    }

    /* renamed from: m */
    public final PictureInPictureParams m3155m(C1147a4 c1147a4) {
        float f4;
        C1674j c1674j;
        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
        float f10 = c1147a4.f4658b;
        Float valueOf = Float.valueOf(f10);
        if (f10 <= AbstractC4176k.f15279f) {
            valueOf = null;
        }
        if (valueOf != null) {
            f4 = valueOf.floatValue();
        } else {
            f4 = 0.516f;
        }
        PictureInPictureParams.Builder aspectRatio = builder.setAspectRatio(new Rational(AbstractC4695a.m7555S(AbstractC0000a.m35q(f4, 0.42f, 2.38f) * BinderC1809u.f7171g0), BinderC1809u.f7171g0));
        List<EnumC1259v0> m11V = AbstractC0000a.m11V(c1147a4);
        ArrayList arrayList = new ArrayList(AbstractC1934n.m3289e0(m11V, 10));
        for (EnumC1259v0 enumC1259v0 : m11V) {
            int ordinal = enumC1259v0.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal == 2) {
                        c1674j = new C1674j(Integer.valueOf(R.drawable.ic_pip_stop), Integer.valueOf(R.string.twin_stop_task));
                    } else {
                        throw new RuntimeException();
                    }
                } else {
                    c1674j = new C1674j(Integer.valueOf(R.drawable.ic_pip_resume), Integer.valueOf(R.string.twin_resume));
                }
            } else {
                c1674j = new C1674j(Integer.valueOf(R.drawable.ic_pip_pause), Integer.valueOf(R.string.twin_pause));
            }
            int intValue = ((Number) c1674j.f6542i).intValue();
            int intValue2 = ((Number) c1674j.f6543j).intValue();
            Intent putExtra = new Intent("io.twoyi.action.PIP_CONTROL").setPackage(getPackageName()).putExtra("control", enumC1259v0.name());
            AbstractC4450j.m7254d(putExtra, "putExtra(...)");
            PendingIntent broadcast = PendingIntent.getBroadcast(this, enumC1259v0.ordinal(), putExtra, 201326592);
            String string = getString(intValue2);
            AbstractC4450j.m7254d(string, "getString(...)");
            arrayList.add(new RemoteAction(Icon.createWithResource(this, intValue), string, string, broadcast));
        }
        PictureInPictureParams.Builder sourceRectHint = aspectRatio.setActions(arrayList).setSourceRectHint(this.f7325E);
        if (Build.VERSION.SDK_INT >= 31) {
            sourceRectHint.setAutoEnterEnabled(c1147a4.m2156j());
        }
        PictureInPictureParams build = sourceRectHint.build();
        AbstractC4450j.m7254d(build, "build(...)");
        return build;
    }

    /* renamed from: n */
    public final void m3156n(C1147a4 c1147a4) {
        if (m3153k()) {
            C4677e c4677e = new C4677e(c1147a4.f4658b, c1147a4.m2156j(), AbstractC0000a.m11V(c1147a4), this.f7325E);
            if (!c4677e.equals(this.f7326F)) {
                this.f7326F = c4677e;
                try {
                    setPictureInPictureParams(m3155m(c1147a4));
                } catch (IllegalArgumentException e10) {
                    Log.w("MainActivity", "picture-in-picture parameters refused", e10);
                } catch (IllegalStateException e11) {
                    Log.w("MainActivity", "picture-in-picture parameters refused", e11);
                }
            }
        }
    }

    @Override // p014b.AbstractActivityC0349n, android.app.Activity
    public final void onCreate(Bundle bundle) {
        String str;
        int i = AbstractC0352q.f1507a;
        C0343i0 c0343i0 = C0343i0.f1460k;
        AbstractC0352q.m913a(this, new C0345j0(0, 0, 0, c0343i0), new C0345j0(AbstractC0352q.f1507a, AbstractC0352q.f1508b, 0, c0343i0));
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33 && checkSelfPermission("android.permission.POST_NOTIFICATIONS") != 0) {
            requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 0);
        }
        String str2 = C4159b.m6882e(this).m6889g().f15129a;
        AbstractC4450j.m7254d(str2, "id");
        this.f7322B = str2;
        EnumC1774a m2835c = AbstractC1712f0.m2835c(this);
        if (m2835c == null || (str = m2835c.name()) == null) {
            str = "guest";
        }
        Context applicationContext = getApplicationContext();
        AbstractC4450j.m7254d(applicationContext, "getApplicationContext(...)");
        String str3 = this.f7322B;
        InterfaceC2410c interfaceC2410c = null;
        if (str3 != null) {
            Context applicationContext2 = applicationContext.getApplicationContext();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            C0501j c0501j = new C0501j(9, applicationContext2, str3);
            C4445e m7263a = AbstractC4460t.m7263a(C1171e4.class);
            if (!linkedHashMap.containsKey(m7263a)) {
                linkedHashMap.put(m7263a, new C0697e(m7263a, c0501j));
                Collection values = linkedHashMap.values();
                AbstractC4450j.m7255e(values, "initializers");
                C0697e[] c0697eArr = (C0697e[]) values.toArray(new C0697e[0]);
                C0698f c0698f = new C0698f(mo784e(), new C0695c((C0697e[]) Arrays.copyOf(c0697eArr, c0697eArr.length)), mo746d());
                String str4 = this.f7322B;
                if (str4 != null) {
                    String str5 = str4 + "/" + str;
                    AbstractC4450j.m7255e(str5, "key");
                    this.f7323C = (C1171e4) c0698f.m1496n(AbstractC4460t.m7263a(C1171e4.class), str5);
                    if (bundle == null) {
                        m3154l(getIntent());
                    }
                    if (m3153k()) {
                        this.f7324D.setValue(Boolean.valueOf(isInPictureInPictureMode()));
                        AbstractC2697l.m4574M(this, this.f7328H, new IntentFilter("io.twoyi.action.PIP_CONTROL"), 4);
                        AbstractC2484x.m4210s(AbstractC0305p0.m756f(this), null, new C0386x(this, interfaceC2410c, 23), 3);
                    }
                    AbstractC0608j.m1424a(this, new C1610f(1005365818, true, new C4321l2(5, this)));
                    return;
                }
                AbstractC4450j.m7260j("instanceId");
                throw null;
            }
            throw new IllegalArgumentException(("A `initializer` with the same `clazz` has already been added: " + m7263a.m7247b() + '.').toString());
        }
        AbstractC4450j.m7260j("instanceId");
        throw null;
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        if (m3153k()) {
            unregisterReceiver(this.f7328H);
        }
        super.onDestroy();
    }

    @Override // p014b.AbstractActivityC0349n, android.app.Activity
    public final void onNewIntent(Intent intent) {
        AbstractC4450j.m7255e(intent, "intent");
        super.onNewIntent(intent);
        m3154l(intent);
    }

    @Override // p014b.AbstractActivityC0349n, android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z10, Configuration configuration) {
        AbstractC4450j.m7255e(configuration, "newConfig");
        super.onPictureInPictureModeChanged(z10, configuration);
        this.f7324D.setValue(Boolean.valueOf(z10));
        if (!z10 && this.f1486i.f1326d.compareTo(EnumC0304p.f1295l) >= 0) {
            C1171e4 c1171e4 = this.f7323C;
            if (c1171e4 != null) {
                c1171e4.m2181f(C1277y3.f4991a);
            } else {
                AbstractC4450j.m7260j("vm");
                throw null;
            }
        }
    }

    @Override // p014b.AbstractActivityC0349n, android.app.Activity
    public final void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (m3153k() && Build.VERSION.SDK_INT < 31) {
            C1171e4 c1171e4 = this.f7323C;
            if (c1171e4 != null) {
                if (((C1147a4) c1171e4.f4772m.f10733i.getValue()).m2156j()) {
                    try {
                        C1171e4 c1171e42 = this.f7323C;
                        if (c1171e42 != null) {
                            enterPictureInPictureMode(m3155m((C1147a4) c1171e42.f4772m.f10733i.getValue()));
                            return;
                        } else {
                            AbstractC4450j.m7260j("vm");
                            throw null;
                        }
                    } catch (IllegalStateException e10) {
                        Log.w("MainActivity", "picture-in-picture refused", e10);
                        return;
                    }
                }
                return;
            }
            AbstractC4450j.m7260j("vm");
            throw null;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z10) {
        InterfaceC1832q0 interfaceC1832q0;
        super.onWindowFocusChanged(z10);
        String str = this.f7322B;
        if (str != null) {
            if (str != null) {
                GuestInstanceClient m2759e = GuestInstanceClient.m2759e(this, str);
                m2759e.getClass();
                GuestInstanceClient.m2757c();
                m2759e.f6630s = z10;
                if (m2759e.f6620i != null) {
                    interfaceC1832q0 = m2759e.f6620i;
                } else {
                    interfaceC1832q0 = m2759e.f6609A;
                }
                if (interfaceC1832q0 != null) {
                    try {
                        interfaceC1832q0.mo2780E(z10);
                        return;
                    } catch (RemoteException unused) {
                        return;
                    }
                }
                return;
            }
            AbstractC4450j.m7260j("instanceId");
            throw null;
        }
    }
}

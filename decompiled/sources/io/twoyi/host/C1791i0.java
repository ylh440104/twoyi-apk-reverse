package io.twoyi.host;

import android.hardware.input.InputManager;
import android.os.IBinder;
import android.view.InputEvent;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import p081h0.AbstractC1474y0;
import p101i8.AbstractC1664a;
import p101i8.C1674j;
import p101i8.C1676l;
import p108j1.AbstractC1858b;
import p108j1.C1857a;
import p133l1.AbstractC2181i;
import p133l1.C2177e;
import p154n1.AbstractC2539e;
import p154n1.AbstractC2541g;
import p163o0.AbstractC2752f;
import p201r6.C3370a;
import p213s6.AbstractC3511a;
import p255w8.InterfaceC4250a;
import p258x0.C4309i2;
import p258x0.C4341s;
import p263x6.C4404b;
import p263x6.C4414l;
import p263x6.EnumC4412j;
import p265x8.AbstractC4450j;
import p278z.AbstractC4540b;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.i0 */
/* loaded from: classes.dex */
public final /* synthetic */ class C1791i0 implements InterfaceC4250a {

    /* renamed from: i */
    public final /* synthetic */ int f7082i;

    public /* synthetic */ C1791i0(int i) {
        this.f7082i = i;
    }

    /* JADX WARN: Type inference failed for: r1v30, types: [x6.c, java.lang.Object] */
    @Override // p255w8.InterfaceC4250a
    /* renamed from: a */
    public final Object mo232a() {
        Object m2734b;
        int i = this.f7082i;
        Class cls = Integer.TYPE;
        switch (i) {
            case AbstractC4695a.f17215a /* 0 */:
                return Class.forName("android.app.TaskInfo").getMethod("getActivityType", null);
            case 1:
                Object invoke = Class.forName("android.app.IActivityTaskManager$Stub").getMethod("asInterface", IBinder.class).invoke(null, Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "activity_task"));
                AbstractC4450j.m7252b(invoke);
                return invoke;
            case 2:
                Object invoke2 = Class.forName("android.os.IPowerManager$Stub").getMethod("asInterface", IBinder.class).invoke(null, Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "power"));
                AbstractC4450j.m7252b(invoke2);
                Method[] methods = invoke2.getClass().getMethods();
                AbstractC4450j.m7254d(methods, "getMethods(...)");
                for (Method method : methods) {
                    if (AbstractC4450j.m7251a(method.getName(), "userActivity") && Arrays.equals(method.getParameterTypes(), new Class[]{cls, Long.TYPE, cls, cls})) {
                        return new C1674j(invoke2, method);
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            case 3:
                Object invoke3 = Class.forName("android.view.WindowManagerGlobal").getMethod("getWindowManagerService", null).invoke(null, null);
                AbstractC4450j.m7252b(invoke3);
                return invoke3;
            case 4:
                try {
                    m2734b = Class.forName("android.hardware.input.InputManagerGlobal").getMethod("getInstance", null).invoke(null, null);
                } catch (Throwable th) {
                    m2734b = AbstractC1664a.m2734b(th);
                }
                if (m2734b instanceof C1676l) {
                    m2734b = null;
                }
                if (m2734b == null) {
                    Object invoke4 = InputManager.class.getMethod("getInstance", null).invoke(null, null);
                    AbstractC4450j.m7252b(invoke4);
                    return invoke4;
                }
                return m2734b;
            case AbstractC4540b.f16788j /* 5 */:
                return AbstractC1795k0.f7107b.getValue().getClass().getMethod("injectInputEvent", InputEvent.class, cls);
            case AbstractC4540b.f16786h /* 6 */:
                return InputEvent.class.getMethod("setDisplayId", cls);
            case 7:
                return Class.forName("android.app.TaskInfo").getField("displayId");
            case 8:
                C4309i2 c4309i2 = AbstractC1858b.f7399a;
                return C1857a.f7398i;
            case 9:
                throw new IllegalStateException("CompositionLocal LocalSavedStateRegistryOwner not present");
            case 10:
                return new C2177e(new LinkedHashMap());
            case 11:
                C4309i2 c4309i22 = AbstractC2181i.f8466a;
                return null;
            case AbstractC1474y0.f5961f /* 12 */:
                C4309i2 c4309i23 = AbstractC2539e.f9307a;
                return null;
            case 13:
                C4309i2 c4309i24 = AbstractC2541g.f9309a;
                return null;
            case 14:
                C4341s c4341s = AbstractC2752f.f9930a;
                return null;
            case AbstractC4540b.f16789k /* 15 */:
                return new C3370a(0);
            case 16:
                throw new IllegalStateException("No local Padding");
            case 17:
                throw new IllegalStateException("No local MarkdownDimens");
            case 18:
                throw new IllegalStateException("No local ImageTransformer");
            case 19:
                return new Object();
            case 20:
                return EnumC4412j.f16371i;
            case 21:
                return new C4404b(new Object());
            case 22:
                return new Object();
            case 23:
                return AbstractC3511a.m5994a(4194303);
            case 24:
                throw new IllegalStateException("No local MarkdownAnimations");
            case 25:
                return new C4414l();
            case 26:
                return new C3370a(1);
            case 27:
                throw new IllegalStateException("CompositionLocal ReferenceLinkHandler not present");
            case 28:
                throw new IllegalStateException("No local MarkdownColors");
            default:
                throw new IllegalStateException("No local MarkdownTypography");
        }
    }
}

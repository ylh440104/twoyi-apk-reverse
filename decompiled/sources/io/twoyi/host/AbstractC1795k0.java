package io.twoyi.host;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.UiAutomation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.os.Looper;
import android.util.SparseArray;
import android.view.InputEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import p022b8.C0470c3;
import p068f9.AbstractC1283a;
import p101i8.AbstractC1664a;
import p101i8.C1679o;
import p114j8.AbstractC1933m;
import p114j8.AbstractC1934n;
import p170o7.AbstractC2780a;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.k0 */
/* loaded from: classes.dex */
public abstract class AbstractC1795k0 {

    /* renamed from: a */
    public static final C1679o f7106a = AbstractC1664a.m2736d(new C1791i0(3));

    /* renamed from: b */
    public static final C1679o f7107b = AbstractC1664a.m2736d(new C1791i0(4));

    /* renamed from: c */
    public static final C1679o f7108c = AbstractC1664a.m2736d(new C1791i0(5));

    /* renamed from: d */
    public static final C1679o f7109d = AbstractC1664a.m2736d(new C1791i0(6));

    /* renamed from: e */
    public static final C1679o f7110e = AbstractC1664a.m2736d(new C1791i0(7));

    /* renamed from: f */
    public static final C1679o f7111f = AbstractC1664a.m2736d(new C0470c3(29));

    /* renamed from: g */
    public static final C1679o f7112g = AbstractC1664a.m2736d(new C1791i0(0));

    /* renamed from: h */
    public static final C1679o f7113h = AbstractC1664a.m2736d(new C1791i0(1));

    /* renamed from: i */
    public static final C1679o f7114i = AbstractC1664a.m2736d(new C1791i0(2));

    /* renamed from: a */
    public static void m3068a(UiAutomation uiAutomation) {
        try {
            UiAutomation.class.getMethod("clearCache", null).invoke(uiAutomation, null);
        } catch (Throwable th) {
            AbstractC1664a.m2734b(th);
            Object invoke = UiAutomation.class.getMethod("getConnectionId", null).invoke(uiAutomation, null);
            AbstractC4450j.m7253c(invoke, "null cannot be cast to non-null type kotlin.Int");
            int intValue = ((Integer) invoke).intValue();
            Field declaredField = Class.forName("android.view.accessibility.AccessibilityInteractionClient").getDeclaredField("sCaches");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            AbstractC4450j.m7253c(obj, "null cannot be cast to non-null type android.util.SparseArray<*>");
            SparseArray sparseArray = (SparseArray) obj;
            synchronized (sparseArray) {
                Object obj2 = sparseArray.get(intValue);
                if (obj2 == null) {
                    return;
                }
                obj2.getClass().getMethod("clear", null).invoke(obj2, null);
            }
        }
    }

    /* renamed from: b */
    public static UiAutomation m3069b(Looper looper) {
        UiAutomation uiAutomation = (UiAutomation) UiAutomation.class.getDeclaredConstructor(Looper.class, Class.forName("android.app.IUiAutomationConnection")).newInstance(looper, Class.forName("android.app.UiAutomationConnection").getDeclaredConstructor(null).newInstance(null));
        UiAutomation.class.getMethod("connect", Integer.TYPE).invoke(uiAutomation, 1);
        AbstractC4450j.m7252b(uiAutomation);
        return uiAutomation;
    }

    /* renamed from: c */
    public static String m3070c(String str) {
        Process start = new ProcessBuilder("dumpsys", "activity", "-p", str, "activities").redirectErrorStream(true).start();
        InputStream inputStream = start.getInputStream();
        AbstractC4450j.m7254d(inputStream, "getInputStream(...)");
        String m4858l = AbstractC2780a.m4858l(new BufferedReader(new InputStreamReader(inputStream, AbstractC1283a.f5006a), 8192));
        start.waitFor();
        return m4858l;
    }

    /* renamed from: d */
    public static boolean m3071d(InputEvent inputEvent, int i, int i10) {
        AbstractC4450j.m7255e(inputEvent, "event");
        Object value = f7109d.getValue();
        AbstractC4450j.m7254d(value, "getValue(...)");
        ((Method) value).invoke(inputEvent, Integer.valueOf(i));
        Object value2 = f7108c.getValue();
        AbstractC4450j.m7254d(value2, "getValue(...)");
        Object invoke = ((Method) value2).invoke(f7107b.getValue(), inputEvent, Integer.valueOf(i10));
        AbstractC4450j.m7253c(invoke, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) invoke).booleanValue();
    }

    /* renamed from: e */
    public static void m3072e(int i, int i10) {
        C1679o c1679o = f7113h;
        Class<?> cls = c1679o.getValue().getClass();
        Class cls2 = Integer.TYPE;
        cls.getMethod("moveRootTaskToDisplay", cls2, cls2).invoke(c1679o.getValue(), Integer.valueOf(i), Integer.valueOf(i10));
    }

    /* renamed from: f */
    public static HashSet m3073f(Context context) {
        AbstractC4450j.m7255e(context, "context");
        Class<?> cls = Class.forName("android.media.AudioPlaybackConfiguration");
        Method method = cls.getMethod("getClientUid", null);
        Method method2 = cls.getMethod("getPlayerState", null);
        List<AudioPlaybackConfiguration> activePlaybackConfigurations = ((AudioManager) context.getSystemService(AudioManager.class)).getActivePlaybackConfigurations();
        AbstractC4450j.m7254d(activePlaybackConfigurations, "getActivePlaybackConfigurations(...)");
        ArrayList arrayList = new ArrayList();
        for (Object obj : activePlaybackConfigurations) {
            if (AbstractC4450j.m7251a(method2.invoke((AudioPlaybackConfiguration) obj, null), 2)) {
                arrayList.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            Object invoke = method.invoke((AudioPlaybackConfiguration) obj2, null);
            AbstractC4450j.m7253c(invoke, "null cannot be cast to non-null type kotlin.Int");
            hashSet.add((Integer) invoke);
        }
        return hashSet;
    }

    /* renamed from: g */
    public static String m3074g(Context context) {
        ActivityInfo activityInfo;
        String str;
        ActivityInfo activityInfo2;
        AbstractC4450j.m7255e(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intent addCategory = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME");
        AbstractC4450j.m7254d(addCategory, "addCategory(...)");
        ResolveInfo resolveActivity = packageManager.resolveActivity(addCategory, 65536);
        if (resolveActivity != null && (activityInfo = resolveActivity.activityInfo) != null && (str = activityInfo.packageName) != null) {
            Intent intent = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.SECONDARY_HOME").setPackage(str);
            AbstractC4450j.m7254d(intent, "setPackage(...)");
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            AbstractC4450j.m7254d(queryIntentActivities, "queryIntentActivities(...)");
            ResolveInfo resolveInfo = (ResolveInfo) AbstractC1933m.m3275m0(queryIntentActivities);
            if (resolveInfo != null && (activityInfo2 = resolveInfo.activityInfo) != null) {
                return activityInfo2.name;
            }
            return null;
        }
        return null;
    }

    /* renamed from: h */
    public static void m3075h(int i) {
        C1679o c1679o = f7106a;
        Class<?> cls = c1679o.getValue().getClass();
        Class cls2 = Integer.TYPE;
        cls.getMethod("setWindowingMode", cls2, cls2).invoke(c1679o.getValue(), Integer.valueOf(i), 1);
    }

    /* renamed from: i */
    public static void m3076i(int i) {
        C1679o c1679o = f7106a;
        Class<?> cls = c1679o.getValue().getClass();
        Class cls2 = Integer.TYPE;
        cls.getMethod("setDisplayImePolicy", cls2, cls2).invoke(c1679o.getValue(), Integer.valueOf(i), 0);
    }

    /* renamed from: j */
    public static void m3077j(Context context, int i, String str, int i10) {
        AbstractC4450j.m7255e(context, "context");
        AbstractC4450j.m7255e(str, "pkg");
        Class cls = Integer.TYPE;
        AppOpsManager.class.getMethod("setMode", String.class, cls, String.class, cls).invoke(context.getSystemService(AppOpsManager.class), "android:play_audio", Integer.valueOf(i), str, Integer.valueOf(i10));
    }

    /* renamed from: k */
    public static ArrayList m3078k(Context context) {
        Intent intent;
        String str;
        ComponentName componentName;
        String str2;
        ComponentName componentName2;
        String str3;
        Intent intent2;
        String str4;
        int i;
        boolean z10;
        ComponentName component;
        ComponentName component2;
        AbstractC4450j.m7255e(context, "context");
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(ActivityManager.class)).getRunningTasks(100);
        AbstractC4450j.m7254d(runningTasks, "getRunningTasks(...)");
        ArrayList arrayList = new ArrayList(AbstractC1934n.m3289e0(runningTasks, 10));
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
            intent = runningTaskInfo.baseIntent;
            if (intent != null && (component2 = intent.getComponent()) != null) {
                str = component2.getClassName();
            } else {
                str = null;
            }
            boolean m7251a = AbstractC4450j.m7251a(str, "io.twoyi.host.HostBackdropActivity");
            int i10 = ((Field) f7110e.getValue()).getInt(runningTaskInfo);
            boolean z11 = ((Field) f7111f.getValue()).getBoolean(runningTaskInfo);
            componentName = runningTaskInfo.baseActivity;
            if (componentName != null) {
                str2 = componentName.getPackageName();
            } else {
                str2 = null;
            }
            componentName2 = runningTaskInfo.topActivity;
            if (componentName2 != null) {
                str3 = componentName2.getPackageName();
            } else {
                str3 = null;
            }
            intent2 = runningTaskInfo.baseIntent;
            if (intent2 != null && (component = intent2.getComponent()) != null) {
                str4 = component.getPackageName();
            } else {
                str4 = null;
            }
            String[] strArr = {str2, str3, str4};
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (int i11 = 0; i11 < 3; i11++) {
                String str5 = strArr[i11];
                if (str5 != null) {
                    linkedHashSet.add(str5);
                }
            }
            i = runningTaskInfo.taskId;
            if (!m7251a && AbstractC4450j.m7251a(((Method) f7112g.getValue()).invoke(runningTaskInfo, null), 1)) {
                z10 = true;
            } else {
                z10 = false;
            }
            arrayList.add(new C1814z(i10, z11, linkedHashSet, i, z10, m7251a));
        }
        return arrayList;
    }
}

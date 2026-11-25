package io.twoyi.host;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p032c9.C0721d;
import p068f9.C1289g;
import p068f9.C1290h;
import p068f9.C1292j;
import p114j8.AbstractC1933m;
import p265x8.AbstractC4450j;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.a0 */
/* loaded from: classes.dex */
public abstract class AbstractC1775a0 {

    /* renamed from: a */
    public static final C1292j f7036a = new C1292j("\\* Task\\{");

    /* renamed from: b */
    public static final C1292j f7037b = new C1292j("launchedFromPackage=(\\S+)");

    /* renamed from: c */
    public static final C1292j f7038c = new C1292j("\\bbgControl=(\\w+)");

    /* renamed from: a */
    public static boolean m3022a(String str, ArrayList arrayList) {
        AbstractC4450j.m7255e(str, "pkg");
        if (!arrayList.isEmpty()) {
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                C1814z c1814z = (C1814z) obj;
                if (c1814z.f7204a == 0 && c1814z.f7205b && c1814z.f7206c.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: b */
    public static String m3023b(int i, String str) {
        C1290h c1290h;
        C0721d m2224b;
        int length;
        String str2;
        C0721d m2224b2;
        String str3 = "\\* Task\\{\\S+ #" + i + "\\b";
        AbstractC4450j.m7255e(str3, "pattern");
        Pattern compile = Pattern.compile(str3);
        AbstractC4450j.m7254d(compile, "compile(...)");
        Matcher matcher = compile.matcher(str);
        AbstractC4450j.m7254d(matcher, "matcher(...)");
        if (!matcher.find(0)) {
            c1290h = null;
        } else {
            c1290h = new C1290h(matcher, str);
        }
        if (c1290h != null && (m2224b = c1290h.m2224b()) != null) {
            int i10 = m2224b.f3036j;
            C1290h m2229b = f7036a.m2229b(str, i10);
            if (m2229b != null && (m2224b2 = m2229b.m2224b()) != null) {
                length = m2224b2.f3035i;
            } else {
                length = str.length();
            }
            String substring = str.substring(i10, length);
            AbstractC4450j.m7254d(substring, "substring(...)");
            C1290h m2229b2 = f7037b.m2229b(substring, 0);
            if (m2229b2 != null && (str2 = (String) ((C1289g) m2229b2.m2223a()).get(1)) != null && !str2.equals("null")) {
                return str2;
            }
        }
        return null;
    }

    /* renamed from: c */
    public static C1814z m3024c(int i, ArrayList arrayList) {
        Object obj;
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i10 = 0;
        int i11 = 0;
        while (i11 < size) {
            Object obj2 = arrayList.get(i11);
            i11++;
            C1814z c1814z = (C1814z) obj2;
            if (c1814z.f7204a == i && c1814z.f7208e) {
                arrayList2.add(obj2);
            }
        }
        int size2 = arrayList2.size();
        while (true) {
            if (i10 < size2) {
                obj = arrayList2.get(i10);
                i10++;
                if (((C1814z) obj).f7205b) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        C1814z c1814z2 = (C1814z) obj;
        if (c1814z2 == null) {
            return (C1814z) AbstractC1933m.m3275m0(arrayList2);
        }
        return c1814z2;
    }
}

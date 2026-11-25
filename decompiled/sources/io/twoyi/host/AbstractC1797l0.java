package io.twoyi.host;

import io.twoyi.AbstractC1712f0;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import p011aa.C0258q;
import p068f9.AbstractC1283a;
import p079g9.C1374f;
import p179p4.AbstractC2956d;
import p265x8.AbstractC4450j;
import p285z6.C4643c0;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.l0 */
/* loaded from: classes.dex */
public abstract class AbstractC1797l0 {

    /* renamed from: a */
    public static final C0258q f7117a = AbstractC1712f0.m2833b(new C1374f(16));

    /* renamed from: a */
    public static C4643c0 m3079a(byte[] bArr) {
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, gZIPInputStream.available()));
            AbstractC2956d.m5286h(gZIPInputStream, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            AbstractC4450j.m7254d(byteArray, "toByteArray(...)");
            gZIPInputStream.close();
            return (C4643c0) f7117a.m555a(new String(byteArray, AbstractC1283a.f5006a), C4643c0.Companion.serializer());
        } finally {
        }
    }

    /* renamed from: b */
    public static byte[] m3080b(C4643c0 c4643c0) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            byte[] bytes = f7117a.m556b(C4643c0.Companion.serializer(), c4643c0).getBytes(AbstractC1283a.f5006a);
            AbstractC4450j.m7254d(bytes, "getBytes(...)");
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            AbstractC4450j.m7254d(byteArray, "toByteArray(...)");
            return byteArray;
        } finally {
        }
    }
}

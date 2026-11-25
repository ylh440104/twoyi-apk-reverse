package io.twoyi.hal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.Image;
import android.net.LocalSocket;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import p089h8.C1568f;
import p089h8.C1590z;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.hal.k */
/* loaded from: classes.dex */
public abstract class AbstractC1742k {

    /* renamed from: a */
    public static final byte[] f6851a = {84, 71, 80, 83};

    /* renamed from: a */
    public static void m2941a(float f4, float f10, float f11, String str) {
        if (Float.isFinite(f4) && f4 >= f10 && f4 <= f11) {
        } else {
            throw new IllegalArgumentException("invalid ".concat(str));
        }
    }

    /* renamed from: b */
    public static C1729e m2942b(List list, int i, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        C1729e c1729e = null;
        if (list == null || i <= 0 || i10 < i || i10 > 240) {
            return null;
        }
        Iterator it = list.iterator();
        C1729e c1729e2 = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            C1729e c1729e3 = (C1729e) it.next();
            if (c1729e3 != null && (i11 = c1729e3.f6778a) > 0 && (i12 = c1729e3.f6779b) >= i11 && i11 <= i && i12 >= i10) {
                if (i11 == i && i12 == i10) {
                    c1729e = c1729e3;
                    break;
                }
                if (c1729e2 == null || (i13 = i12 - i11) < (i16 = (i14 = c1729e2.f6779b) - (i15 = c1729e2.f6778a)) || ((i13 == i16 && i11 > i15) || (i13 == i16 && i11 == i15 && i12 < i14))) {
                    c1729e2 = c1729e3;
                }
            }
        }
        if (c1729e != null) {
            return c1729e;
        }
        return c1729e2;
    }

    /* renamed from: c */
    public static C1734g m2943c(List list, C1734g c1734g) {
        int i = c1734g.f6815a;
        int i10 = c1734g.f6816b;
        if (m2949i(c1734g)) {
            List<C1734g> m2947g = m2947g(list);
            for (C1734g c1734g2 : m2947g) {
                if (c1734g2.equals(c1734g)) {
                    return c1734g2;
                }
            }
            C1734g c1734g3 = null;
            C1734g c1734g4 = null;
            for (C1734g c1734g5 : m2947g) {
                if (c1734g5.f6815a * i10 == i * c1734g5.f6816b && c1734g5.m2925a() >= c1734g.m2925a() && (c1734g4 == null || c1734g5.m2925a() < c1734g4.m2925a())) {
                    c1734g4 = c1734g5;
                }
            }
            if (c1734g4 != null) {
                return c1734g4;
            }
            for (C1734g c1734g6 : m2947g) {
                if (c1734g3 != null) {
                    int compare = Double.compare(Math.abs((c1734g6.f6815a / c1734g6.f6816b) - (i / i10)), Math.abs((c1734g3.f6815a / c1734g3.f6816b) - (i / i10)));
                    if (compare == 0 && (compare = Long.compare(Math.abs(c1734g6.m2925a() - c1734g.m2925a()), Math.abs(c1734g3.m2925a() - c1734g.m2925a()))) == 0) {
                        compare = 0;
                    }
                    if (compare < 0) {
                    }
                }
                c1734g3 = c1734g6;
            }
            if (c1734g3 != null) {
                return c1734g3;
            }
            throw new IllegalArgumentException("Camera has no usable preview sizes");
        }
        throw new IllegalArgumentException("Requested preview size is invalid");
    }

    /* renamed from: d */
    public static byte[] m2944d(int i, long j3, long j10, byte[] bArr) {
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("invalid GPS frame kind");
        }
        if (j3 >= 1 && j3 <= 4294967295L) {
            if (j10 > 0) {
                if (bArr != null && bArr.length <= 6148) {
                    if (i == 1 && (bArr.length < 3 || bArr.length > 256)) {
                        throw new IllegalArgumentException("invalid NMEA frame payload");
                    }
                    if (i == 2 && bArr.length < 4) {
                        throw new IllegalArgumentException("invalid SV frame payload");
                    }
                    ByteBuffer order = ByteBuffer.allocate(bArr.length + 24).order(ByteOrder.LITTLE_ENDIAN);
                    order.put(f6851a);
                    order.put((byte) 2);
                    order.put((byte) i);
                    order.putShort((short) 24);
                    order.putInt(bArr.length);
                    order.putInt((int) j3);
                    order.putLong(j10);
                    order.put(bArr);
                    return order.array();
                }
                throw new IllegalArgumentException("invalid GPS frame payload");
            }
            throw new IllegalArgumentException("invalid source elapsed realtime");
        }
        throw new IllegalArgumentException("invalid GPS frame sequence");
    }

    /* renamed from: e */
    public static byte[] m2945e(String str) {
        if (str.endsWith("\r\n")) {
            for (int i = 0; i < str.length() - 2; i++) {
                char charAt = str.charAt(i);
                if (charAt < ' ' || charAt > '~') {
                    throw new IllegalArgumentException("NMEA must contain visible ASCII");
                }
            }
            byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
            if (bytes.length >= 3 && bytes.length <= 256) {
                return bytes;
            }
            throw new IllegalArgumentException("invalid NMEA payload size");
        }
        throw new IllegalArgumentException("NMEA must end with CRLF");
    }

    /* renamed from: f */
    public static byte[] m2946f(List list) {
        if (list != null && list.size() <= 256) {
            ByteBuffer order = ByteBuffer.allocate((list.size() * 24) + 4).order(ByteOrder.LITTLE_ENDIAN);
            order.putShort((short) list.size());
            order.putShort((short) 24);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                C1740j c1740j = (C1740j) it.next();
                if (c1740j != null) {
                    order.put((byte) c1740j.f6839a);
                    order.put((byte) c1740j.f6840b);
                    order.putShort((short) c1740j.f6841c);
                    order.putFloat(c1740j.f6842d);
                    order.putFloat(c1740j.f6843e);
                    order.putFloat(c1740j.f6844f);
                    order.putFloat(c1740j.f6845g);
                    order.putFloat(c1740j.f6846h);
                } else {
                    throw new IllegalArgumentException("null satellite");
                }
            }
            return order.array();
        }
        throw new IllegalArgumentException("invalid satellite count");
    }

    /* renamed from: g */
    public static List m2947g(List list) {
        if (list != null && !list.isEmpty()) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                C1734g c1734g = (C1734g) it.next();
                if (m2949i(c1734g) && m2950j(c1734g.f6815a, c1734g.f6816b) <= 4194304) {
                    linkedHashSet.add(c1734g);
                }
            }
            ArrayList arrayList = new ArrayList(linkedHashSet.size());
            Iterator it2 = linkedHashSet.iterator();
            int i = 0;
            int i10 = 0;
            while (it2.hasNext()) {
                arrayList.add(new C1732f((C1734g) it2.next(), i10));
                i10++;
            }
            arrayList.sort(Comparator.comparingLong(new C1568f(1)).reversed().thenComparingInt(new C1590z(1)));
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            int size = arrayList.size();
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                arrayList2.add(((C1732f) obj).f6803a);
            }
            return Collections.unmodifiableList(arrayList2);
        }
        return Collections.EMPTY_LIST;
    }

    /* renamed from: h */
    public static void m2948h(Context context, LocalSocket localSocket) {
        int i;
        int i10;
        try {
            C1718a0 c1718a0 = C1718a0.f6723c;
            try {
                i = localSocket.getInputStream().read();
            } catch (IOException e10) {
                e10.printStackTrace();
                i = -1;
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
            } catch (Throwable th2) {
                try {
                    localSocket.close();
                } catch (IOException e11) {
                    e11.printStackTrace();
                }
                throw th2;
            }
        }
        if (i == 0) {
            try {
                C1761t0.m2980d(localSocket.getInputStream(), new byte[8], 8);
            } catch (IOException e12) {
                e12.printStackTrace();
            }
            context.sendBroadcast(new Intent("android.intent.action.TIME_SET"));
            try {
                localSocket.close();
            } catch (IOException e13) {
                e13.printStackTrace();
                return;
            }
        }
        if (i == 1) {
            try {
                byte[] bArr = new byte[4];
                localSocket.getInputStream().read(bArr, 0, 4);
                i10 = ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[0] & 255);
            } catch (IOException e14) {
                try {
                    e14.printStackTrace();
                    i10 = -1;
                } catch (IOException e15) {
                    e15.printStackTrace();
                }
            }
            if (i10 != -1) {
                byte[] bArr2 = new byte[i10];
                C1761t0.m2980d(localSocket.getInputStream(), bArr2, i10);
                new String(bArr2);
            }
            context.sendBroadcast(new Intent("android.intent.action.TIME_SET"));
        }
        localSocket.close();
    }

    /* renamed from: i */
    public static boolean m2949i(C1734g c1734g) {
        if (c1734g != null) {
            int i = c1734g.f6816b;
            int i10 = c1734g.f6815a;
            if (i10 > 0 && i > 0 && (i10 & 1) == 0 && (i & 1) == 0 && i10 <= 8192 && i <= 8192) {
                try {
                    if (Math.multiplyExact(i10, i) > 16777216) {
                        return false;
                    }
                    if (m2950j(i10, i) <= 33554432) {
                        return true;
                    }
                } catch (ArithmeticException | IllegalArgumentException unused) {
                }
            }
        }
        return false;
    }

    /* renamed from: j */
    public static int m2950j(int i, int i10) {
        if (i > 0 && i10 > 0 && (i & 1) == 0 && (i10 & 1) == 0) {
            int multiplyExact = Math.multiplyExact(i, i10);
            return Math.addExact(multiplyExact, multiplyExact / 2);
        }
        throw new IllegalArgumentException("YUV420 dimensions must be positive and even");
    }

    /* renamed from: k */
    public static void m2951k(Image image, int i, int i10, byte[] bArr) {
        int i11 = i;
        byte[] bArr2 = bArr;
        if (image.getFormat() == 35) {
            if (image.getWidth() == i11 && image.getHeight() == i10) {
                Rect cropRect = image.getCropRect();
                if (cropRect != null && cropRect.left == 0 && cropRect.top == 0 && cropRect.width() == i11 && cropRect.height() == i10) {
                    Image.Plane[] planes = image.getPlanes();
                    if (planes != null && planes.length == 3) {
                        C1726d[] c1726dArr = new C1726d[3];
                        for (int i12 = 0; i12 < 3; i12++) {
                            Image.Plane plane = planes[i12];
                            if (plane != null) {
                                c1726dArr[i12] = new C1726d(plane.getBuffer(), plane.getRowStride(), plane.getPixelStride());
                            } else {
                                throw new IllegalArgumentException("YUV plane is missing");
                            }
                        }
                        m2950j(i, i10);
                        if (i11 > 0 && i10 > 0 && (i11 & 1) == 0 && (i10 & 1) == 0) {
                            long j3 = 0;
                            long j10 = i11;
                            long j11 = j3 + j10;
                            long j12 = i10;
                            long j13 = j3 + j12;
                            if (j11 <= j10 && j13 <= j12) {
                                if (bArr2.length == m2950j(i, i10)) {
                                    C1726d c1726d = c1726dArr[0];
                                    m2954n(c1726d);
                                    C1726d c1726d2 = c1726dArr[1];
                                    m2954n(c1726d2);
                                    C1726d c1726d3 = c1726dArr[2];
                                    m2954n(c1726d3);
                                    int i13 = 0;
                                    int i14 = 0;
                                    while (i13 < i10) {
                                        C1726d.m2881a(c1726d, i13, 0, i11, bArr2, i14, 1);
                                        i14 += i;
                                        i13++;
                                        i11 = i;
                                        bArr2 = bArr;
                                    }
                                    int i15 = i10 / 2;
                                    int i16 = i / 2;
                                    for (int i17 = 0; i17 < i15; i17++) {
                                        C1726d.m2881a(c1726d3, i17, 0, i16, bArr, i14, 2);
                                        int i18 = i14;
                                        C1726d.m2881a(c1726d2, i17, 0, i16, bArr, i18 + 1, 2);
                                        i14 = (i16 * 2) + i18;
                                    }
                                    if (i14 == bArr.length) {
                                        return;
                                    } else {
                                        throw new IllegalStateException("NV21 conversion produced an invalid size");
                                    }
                                }
                                throw new IllegalArgumentException("NV21 output has an unexpected size");
                            }
                            throw new IllegalArgumentException("Crop rectangle exceeds the image");
                        }
                        throw new IllegalArgumentException("YUV420 crop must be positive and even");
                    }
                    throw new IllegalArgumentException("YUV_420_888 must expose three planes");
                }
                throw new IllegalArgumentException("Camera returned an unexpected crop rectangle");
            }
            throw new IllegalArgumentException("Image dimensions changed during capture");
        }
        throw new IllegalArgumentException("Image must be YUV_420_888");
    }

    /* renamed from: l */
    public static C1738i m2952l(InputStream inputStream, long j3) {
        byte[] bArr = new byte[32];
        int read = inputStream.read();
        if (read < 0) {
            return null;
        }
        bArr[0] = (byte) read;
        int i = 1;
        while (i < 32) {
            int read2 = inputStream.read(bArr, i, 32 - i);
            if (read2 >= 0) {
                if (read2 != 0) {
                    i += read2;
                }
            } else {
                throw new EOFException("Camera v2 packet ended early");
            }
        }
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        int i10 = order.getInt();
        int unsignedInt = Short.toUnsignedInt(order.getShort());
        int unsignedInt2 = Short.toUnsignedInt(order.getShort());
        int unsignedInt3 = Short.toUnsignedInt(order.getShort());
        int unsignedInt4 = Short.toUnsignedInt(order.getShort());
        long unsignedLong = Integer.toUnsignedLong(order.getInt());
        int i11 = order.getInt();
        long unsignedLong2 = Integer.toUnsignedLong(order.getInt());
        int i12 = order.getInt();
        int i13 = order.getInt();
        if (i10 == 843274068 && unsignedInt == 2 && unsignedInt2 == 32 && (32768 & unsignedInt3) == 0 && unsignedInt4 == 0 && unsignedLong != 0 && unsignedLong > j3 && i11 == 0 && unsignedLong2 <= 4096 && i12 == 0 && i13 == 0) {
            int i14 = (int) unsignedLong2;
            byte[] bArr2 = new byte[i14];
            int i15 = 0;
            while (i15 < i14) {
                int read3 = inputStream.read(bArr2, i15, i14 - i15);
                if (read3 >= 0) {
                    if (read3 != 0) {
                        i15 += read3;
                    }
                } else {
                    throw new EOFException("Camera v2 packet ended early");
                }
            }
            return new C1738i(unsignedInt3, unsignedLong, bArr2);
        }
        throw new IOException("Invalid camera v2 request header");
    }

    /* renamed from: m */
    public static void m2953m(C1738i c1738i, int i) {
        if (c1738i.f6833c.length == i) {
            return;
        }
        throw new IOException("Invalid payload size for opcode " + c1738i.f6831a);
    }

    /* renamed from: n */
    public static void m2954n(C1726d c1726d) {
        if (c1726d != null) {
            int i = c1726d.f6767b;
            if (i >= 0 && c1726d.f6768c >= i && c1726d.f6769d > 0 && c1726d.f6770e > 0) {
                return;
            } else {
                throw new IllegalArgumentException("Invalid YUV plane layout");
            }
        }
        throw new IllegalArgumentException("YUV plane is missing");
    }

    /* renamed from: o */
    public static void m2955o(OutputStream outputStream, int i, long j3, int i10, String str) {
        byte[] bytes;
        if (i10 != 0) {
            if (str == null) {
                bytes = new byte[0];
            } else {
                bytes = str.getBytes(StandardCharsets.UTF_8);
            }
            if (bytes.length > 1024) {
                bytes = Arrays.copyOf(bytes, 1024);
                while (bytes.length > 0 && (bytes[bytes.length - 1] & 192) == 128) {
                    bytes = Arrays.copyOf(bytes, bytes.length - 1);
                }
            }
            ByteBuffer order = ByteBuffer.allocate(bytes.length + 4).order(ByteOrder.LITTLE_ENDIAN);
            order.putInt(bytes.length).put(bytes);
            m2957q(outputStream, i, j3, i10, order.array());
            return;
        }
        throw new IllegalArgumentException("Error response requires nonzero status");
    }

    /* renamed from: p */
    public static void m2956p(OutputStream outputStream, int i, long j3, int i10, int i11) {
        if (j3 > 0 && j3 <= 4294967295L && i11 >= 0) {
            ByteBuffer order = ByteBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN);
            order.putInt(843274068);
            order.putShort((short) 2);
            order.putShort((short) 32);
            order.putShort((short) i);
            order.putShort((short) 0);
            order.putInt((int) j3);
            order.putInt(i10);
            order.putInt(i11);
            order.putInt(0);
            order.putInt(0);
            outputStream.write(order.array());
            return;
        }
        throw new IllegalArgumentException("Invalid camera v2 response header");
    }

    /* renamed from: q */
    public static void m2957q(OutputStream outputStream, int i, long j3, int i10, byte[] bArr) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        m2956p(outputStream, i | 32768, j3, i10, bArr.length);
        outputStream.write(bArr);
    }
}

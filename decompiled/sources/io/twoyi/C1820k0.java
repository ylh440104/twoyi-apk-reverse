package io.twoyi;

import android.content.Context;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import ba.C0587n;
import io.twoyi.utils.RomManager;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import na.AbstractC2689d;
import org.json.JSONException;
import org.json.JSONObject;
import p008a7.AbstractC0108z;
import p198r3.AbstractC3356a;
import p245v7.C4158a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.k0 */
/* loaded from: classes.dex */
public final class C1820k0 implements Closeable {

    /* renamed from: i */
    public final Context f7280i;

    /* renamed from: j */
    public final C4158a f7281j;

    /* renamed from: k */
    public final int f7282k;

    /* renamed from: l */
    public final CountDownLatch f7283l;

    /* renamed from: m */
    public volatile InterfaceC1832q0 f7284m;

    /* renamed from: n */
    public volatile DataInputStream f7285n;

    /* renamed from: o */
    public final ServiceConnectionC1818j0 f7286o;

    public C1820k0(Context context, C4158a c4158a, int i) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.f7283l = countDownLatch;
        ServiceConnectionC1818j0 serviceConnectionC1818j0 = new ServiceConnectionC1818j0(this);
        this.f7286o = serviceConnectionC1818j0;
        Context applicationContext = context.getApplicationContext();
        this.f7280i = applicationContext;
        this.f7281j = c4158a;
        this.f7282k = i;
        String str = c4158a.f15129a;
        int m2760j = GuestInstanceClient.m2760j(applicationContext, str);
        if (m2760j >= 0) {
            if (applicationContext.bindService(new Intent(applicationContext, (Class<?>) AbstractC2689d.m4530K(m2760j)).putExtra("io.twoyi.extra.INSTANCE_ID", str), serviceConnectionC1818j0, 0)) {
                try {
                    if (countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                        return;
                    }
                    close();
                    throw new IOException("guest " + str + " did not answer");
                } catch (InterruptedException e10) {
                    close();
                    Thread.currentThread().interrupt();
                    throw new IOException(e10);
                }
            }
            throw new IOException(AbstractC3356a.m5779e("guest ", str, " has no service"));
        }
        throw new IOException(AbstractC3356a.m5779e("guest ", str, " is not running"));
    }

    /* renamed from: b */
    public final byte[] m3135b(String str, JSONObject jSONObject) {
        try {
            return (byte[]) m3137g(new C1816i0(this, jSONObject.put("op", str).toString(), 1)).f2442c;
        } catch (JSONException e10) {
            throw new IOException(e10);
        }
    }

    /* renamed from: c */
    public final void m3136c(List list, String... strArr) {
        String str = "twoyi-install-" + UUID.randomUUID();
        File file = new File(RomManager.getRootfsDir(this.f7280i, this.f7281j), AbstractC0108z.m337m("data/local/tmp/", str));
        if (file.mkdirs()) {
            int i = 0;
            file.setExecutable(true, false);
            file.setReadable(true, false);
            List asList = Arrays.asList(strArr);
            for (int i10 = 0; i10 < list.size(); i10++) {
                try {
                    try {
                        File file2 = new File(file, i10 + ".apk");
                        Files.copy(((File) list.get(i10)).toPath(), file2.toPath(), new CopyOption[0]);
                        file2.setReadable(true, false);
                    } catch (JSONException e10) {
                        throw new IOException(e10);
                    }
                } catch (Throwable th) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        int length = listFiles.length;
                        while (i < length) {
                            listFiles[i].delete();
                            i++;
                        }
                    }
                    file.delete();
                    throw th;
                }
            }
            m3135b("install", new JSONObject().put("path", "/data/local/tmp/" + str).put("replace", asList.contains("-r")).put("allowTest", asList.contains("-t")).put("grant", asList.contains("-g")));
            File[] listFiles2 = file.listFiles();
            if (listFiles2 != null) {
                int length2 = listFiles2.length;
                while (i < length2) {
                    listFiles2[i].delete();
                    i++;
                }
            }
            file.delete();
            return;
        }
        throw new IOException("cannot create " + file);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        DataInputStream dataInputStream = this.f7285n;
        if (dataInputStream != null) {
            try {
                dataInputStream.close();
            } catch (IOException unused) {
            }
        }
        this.f7284m = null;
        try {
            this.f7280i.unbindService(this.f7286o);
        } catch (IllegalArgumentException unused2) {
        }
    }

    /* renamed from: g */
    public final C0587n m3137g(C1816i0 c1816i0) {
        InterfaceC1832q0 interfaceC1832q0 = this.f7284m;
        if (interfaceC1832q0 != null) {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            try {
                DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(createPipe[0])));
                try {
                    this.f7285n = dataInputStream;
                    try {
                        try {
                            c1816i0.m3125a(interfaceC1832q0, createPipe[1]);
                            createPipe[1].close();
                            if (dataInputStream.readBoolean()) {
                                int readInt = dataInputStream.readInt();
                                byte[] bArr = new byte[dataInputStream.readInt()];
                                dataInputStream.readFully(bArr);
                                byte[] bArr2 = new byte[dataInputStream.readInt()];
                                dataInputStream.readFully(bArr2);
                                C0587n c0587n = new C0587n(readInt, bArr, bArr2);
                                dataInputStream.close();
                                return c0587n;
                            }
                            throw new IOException(dataInputStream.readUTF());
                        } catch (RemoteException e10) {
                            throw new IOException(e10);
                        }
                    } catch (Throwable th) {
                        createPipe[1].close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    try {
                        dataInputStream.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                    throw th2;
                }
            } finally {
                this.f7285n = null;
            }
        }
        throw new IOException("guest shell is closed");
    }
}

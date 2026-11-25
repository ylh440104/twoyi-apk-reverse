package io.twoyi.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import io.twoyi.hal.C1761t0;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import p089h8.AbstractC1574j;
import p089h8.AbstractC1578n;
import p089h8.C1559a0;
import p089h8.C1561b0;
import p089h8.C1563c0;
import p089h8.C1565d0;
import p089h8.C1575k;
import p089h8.C1576l;
import p089h8.C1590z;
import p089h8.EnumC1567e0;
import p089h8.EnumC1577m;
import p116ja.C1972m;
import p198r3.AbstractC3356a;
import p214s7.C3526g;
import p245v7.C4158a;
import p254w7.C4245b;
import p254w7.C4246c;
import p254w7.C4247d;
import p254w7.C4249f;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* loaded from: classes.dex */
public final class RomManager {
    private static final String CUSTOM_ROM_FILE_NAME = "rootfs_3rd.zst";
    private static final String LOADER32_FILE = "libloader32.so";
    private static final String LOADER_FILE = "libloader.so";
    private static final long MAX_ROOTFS_VERSION_BYTES = 4096;
    private static final String ROOTFS_NAME = "rootfs.tar.zst";
    private static final String ROOTFS_VERSION_FILE = "rootfs.version";
    private static final String TAG = "RomManager";

    static {
        System.loadLibrary("twoyi");
    }

    private RomManager() {
    }

    private static void addPathAlias(Set<String> set, String str) {
        if (str != null && !str.isEmpty()) {
            set.add(str);
            try {
                set.add(new File(str).getCanonicalPath());
            } catch (IOException unused) {
            }
        }
    }

    public static void bootTwoyi(Context context) {
        killOrphanProcess(context);
        int nativeRecoverRootfs = nativeRecoverRootfs(context.getDataDir().getAbsolutePath());
        if (nativeRecoverRootfs == 0) {
            try {
                repairRomArtifacts(context);
                File rootfsDir = getRootfsDir(context);
                File file = new File(rootfsDir, "dev");
                boolean isFile = new File(rootfsDir, "system/bin/init").isFile();
                if (isFile) {
                    resetVolatileDeviceState(file);
                }
                ensureDir(new File(file, "input"));
                ensureDir(new File(file, "socket"));
                ensureDir(new File(file, "maps"));
                if (isFile) {
                    ensureDir(new File(file, "vid"));
                    ensureDir(new File(file, "vbinder"));
                    ensureDir(new File(file, "hwbinder"));
                }
                ensureDir(new File(context.getDataDir(), "socket"));
                createLoaderSymlink(context);
                saveLastKmsg(context);
                C1761t0.m2978b(context);
                return;
            } catch (IOException e10) {
                throw new IllegalStateException("Unable to restore ROM compiled files", e10);
            }
        }
        throw new IllegalStateException(AbstractC3356a.m5776b(nativeRecoverRootfs, "Unable to recover rootfs transaction: "));
    }

    private static C1561b0 buildGuestProcessScope(Context context) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        addPathAlias(linkedHashSet, "/data/data/" + context.getPackageName());
        addPathAlias(linkedHashSet, context.getDataDir().getAbsolutePath());
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            addPathAlias(linkedHashSet2, AbstractC3356a.m5778d(str, "/rootfs"));
            addPathAlias(linkedHashSet2, AbstractC3356a.m5778d(str, "/rootfs.zst.rollback"));
            addPathAlias(linkedHashSet2, AbstractC3356a.m5778d(str, "/rootfs.zst.old"));
        }
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        addPathAlias(linkedHashSet3, getLoaderPath(context));
        addPathAlias(linkedHashSet3, getLoader32Path(context));
        return new C1561b0(new ArrayList(linkedHashSet2), new ArrayList(linkedHashSet3), new ArrayList(linkedHashSet));
    }

    private static long checkedAdd(long j3, long j10) {
        if (j3 >= 0 && j10 >= 0 && j3 <= Long.MAX_VALUE - j10) {
            return j3 + j10;
        }
        throw new IOException("ROM asset offset overflow");
    }

    private static void createLoaderSymlink(Context context) {
        Path path = new File(context.getDataDir(), "loader64").toPath();
        Path path2 = new File(context.getDataDir(), "loader32").toPath();
        createLoaderSymlink(path, getLoaderPath(context));
        createLoaderSymlink(path2, getLoader32Path(context));
    }

    private static void ensureDir(File file) {
        if (!file.isDirectory()) {
            if (!file.exists() && (file.mkdirs() || file.isDirectory())) {
                return;
            }
            throw new IllegalStateException("Unable to create directory: " + file);
        }
    }

    private static C4245b extract3rdRootfs(Context context) {
        File file = get3rdRootfsFile(context);
        if (file.exists()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            C4245b extractOpenedFile = extractOpenedFile(context, file, context.getDataDir());
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            Log.i(TAG, "extract 3rd rom (ustar): " + (elapsedRealtime2 - elapsedRealtime) + "ms, " + extractOpenedFile.m6944a());
            return extractOpenedFile;
        }
        throw new IOException("3rd rom file missing: " + file);
    }

    private static C4245b extractOpenedFile(Context context, File file, File file2) {
        return extractOpenedFile(context, file, file2, false);
    }

    private static void extractOrRepairOpenedRom(Context context, int i, long j3, File file, C4247d c4247d, boolean z10) {
        int nativeExtractRootfs;
        String str;
        C4245b installedRomInfo;
        if (z10 && ((installedRomInfo = getInstalledRomInfo(context)) == null || !installedRomInfo.m6946e(c4247d.f15860a))) {
            throw new IOException("repair source does not match the installed ROM payload");
        }
        if (z10) {
            nativeExtractRootfs = nativeRepairRootfs(i, j3, c4247d.f15862c, file.getAbsolutePath(), c4247d.f15863d, c4247d.m6958l(), (String[]) c4247d.f15865f.clone());
        } else {
            nativeExtractRootfs = nativeExtractRootfs(i, j3, c4247d.f15862c, file.getAbsolutePath(), c4247d.f15863d, c4247d.m6958l());
        }
        if (nativeExtractRootfs != 0) {
            StringBuilder sb = new StringBuilder("native ROM ");
            if (z10) {
                str = "repair";
            } else {
                str = "extraction";
            }
            sb.append(str);
            sb.append(" failed: ");
            sb.append(nativeExtractRootfs);
            throw new IOException(sb.toString());
        }
    }

    public static synchronized boolean extractRootfs(Context context) {
        synchronized (RomManager.class) {
            try {
                C4245b lambda$extractRootfs$1 = lambda$extractRootfs$1(context.getSharedPreferences("app_kv", 0).getBoolean("should_use_third_party_rom", false), context);
                if (lambda$extractRootfs$1 != null) {
                    writeInstalledRomInfo(context, lambda$extractRootfs$1);
                    AbstractC1574j.m2651f(context, "rom_should_be_re_install", false);
                    Log.i(TAG, "rootfs install committed: " + lambda$extractRootfs$1.m6944a());
                } else {
                    throw new IllegalStateException("extractor returned no ROM identity");
                }
            } catch (Exception e10) {
                Log.e(TAG, "rootfs install transaction failed", e10);
                showRootfsInstallationFailure(context);
                return false;
            }
        }
        return true;
    }

    private static C4245b extractRootfsInAssets(Context context) {
        return extractRootfsInAssets(context, false);
    }

    public static File get3rdRootfsFile(Context context) {
        return context.getFileStreamPath(CUSTOM_ROM_FILE_NAME);
    }

    public static File getDataLocalPropFile(Context context) {
        return new File(new File(getRootfsDir(context), "data"), "local.prop");
    }

    public static C4245b getInstalledRomInfo(Context context) {
        File file = new File(context.getFilesDir(), ROOTFS_VERSION_FILE);
        try {
            long size = Files.size(file.toPath());
            if (size > 0 && size <= MAX_ROOTFS_VERSION_BYTES) {
                return C4245b.m6942c(Files.readAllBytes(file.toPath()));
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String getLoader32Path(Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir, LOADER32_FILE).getAbsolutePath();
    }

    public static String getLoaderPath(Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir, LOADER_FILE).getAbsolutePath();
    }

    public static C4245b getPackagedRomInfo(Context context) {
        try {
            AssetFileDescriptor openFd = context.getAssets().openFd(ROOTFS_NAME);
            try {
                C4245b c4245b = (C4245b) C4247d.m6952f(new FileInputStream(openFd.getFileDescriptor()).getChannel(), openFd.getStartOffset(), openFd.getDeclaredLength(), C4249f.m6961c(context)).f7747d;
                if ("direct".equals(c4245b.f15835e) && "ustar".equals(c4245b.f15836f)) {
                    openFd.close();
                    return c4245b;
                }
                throw new IOException("packaged ROM is not a direct USTAR payload");
            } finally {
            }
        } catch (Throwable th) {
            Log.e(TAG, "unable to parse packaged ROM", th);
            return null;
        }
    }

    public static File getPrivAppDir(Context context) {
        return new File(getSystemDir(context), "priv-app");
    }

    public static File getRomSdcardDir(Context context) {
        return new File(getRootfsDir(context), "storage/emulated/0");
    }

    public static File getRootfsDir(Context context) {
        return new File(context.getDataDir(), "rootfs");
    }

    public static File getSystemDir(Context context) {
        return new File(getRootfsDir(context), "system");
    }

    public static File getVendorDir(Context context) {
        return new File(getRootfsDir(context), "vendor");
    }

    public static File getVendorPropFile(Context context) {
        return new File(getVendorDir(context), "default.prop");
    }

    private static boolean hasProperties(Path path, Properties properties) {
        Properties properties2 = new Properties();
        try {
            InputStream newInputStream = Files.newInputStream(path, new OpenOption[0]);
            try {
                properties2.load(newInputStream);
                if (newInputStream != null) {
                    newInputStream.close();
                }
                return properties2.equals(properties);
            } finally {
            }
        } catch (NoSuchFileException unused) {
            return false;
        }
    }

    public static void initRootfs(Context context) {
        Files.deleteIfExists(getDataLocalPropFile(context).toPath());
        File vendorPropFile = getVendorPropFile(context);
        Locale locale = Locale.getDefault();
        Properties properties = new Properties();
        properties.setProperty("persist.sys.locale", locale.toLanguageTag());
        String id = TimeZone.getDefault().getID();
        String str = TAG;
        Log.i(str, "timezone: " + id);
        properties.setProperty("persist.sys.timezone", id);
        int i = context.getResources().getDisplayMetrics().densityDpi;
        Log.i(str, "density: " + i);
        if (i > 0) {
            properties.setProperty("ro.sf.lcd_density", Integer.toString(i));
            if (hasProperties(vendorPropFile.toPath(), properties)) {
                return;
            }
            writeBootProperties(vendorPropFile, properties);
            return;
        }
        throw new IllegalArgumentException("densityDpi must be positive");
    }

    public static C4245b inspectAndValidateRom(Context context, File file) {
        File file2 = new File(context.getFilesDir(), "rom_validate_tmp");
        IOUtils.deleteDirectory(file2);
        file2.mkdirs();
        try {
            try {
                return extractOpenedFile(context, file, file2);
            } catch (IOException e10) {
                Log.e(TAG, "third-party ROM validation failed", e10);
                IOUtils.deleteDirectory(file2);
                return null;
            }
        } finally {
            IOUtils.deleteDirectory(file2);
        }
    }

    public static boolean isAboveAndroid12() {
        if (Build.VERSION.PREVIEW_SDK_INT + Build.VERSION.SDK_INT >= 31) {
            return true;
        }
        return false;
    }

    public static boolean isAndroid12() {
        if (Build.VERSION.PREVIEW_SDK_INT + Build.VERSION.SDK_INT == 31) {
            return true;
        }
        return false;
    }

    public static boolean isAndroid12L() {
        if (Build.VERSION.PREVIEW_SDK_INT + Build.VERSION.SDK_INT == 32) {
            return true;
        }
        return false;
    }

    public static boolean isAndroid13() {
        if (Build.VERSION.PREVIEW_SDK_INT + Build.VERSION.SDK_INT == 33) {
            return true;
        }
        return false;
    }

    public static boolean isSystemAlive() {
        C3526g c3526g = new C3526g(AbstractC1574j.m2650e());
        c3526g.m6012h0("pidof surfaceflinger");
        c3526g.f12607b = null;
        c3526g.f12608c = C3526g.f12603g;
        if (c3526g.m6013i0().f2620a == 0) {
            return true;
        }
        return false;
    }

    private static void killOrphanProcess(Context context) {
        C1563c0 readStrongGuestSnapshot;
        int i;
        C1561b0 buildGuestProcessScope = buildGuestProcessScope(context);
        Set<Integer> readOwnAncestorPids = readOwnAncestorPids();
        int i10 = 0;
        while (i10 < 5) {
            Map<Integer, C1563c0> selectOrphanGuestTree = selectOrphanGuestTree(snapshotStrongGuestProcesses(buildGuestProcessScope));
            if (!selectOrphanGuestTree.isEmpty()) {
                ArrayList arrayList = new ArrayList(selectOrphanGuestTree.values());
                arrayList.sort(Comparator.comparingInt(new C1590z(0)).reversed());
                int size = arrayList.size();
                int i11 = 0;
                int i12 = 0;
                while (i12 < size) {
                    Object obj = arrayList.get(i12);
                    i12++;
                    C1563c0 c1563c0 = (C1563c0) obj;
                    if (readOwnAncestorPids.contains(Integer.valueOf(c1563c0.f6278a)) || (readStrongGuestSnapshot = readStrongGuestSnapshot(c1563c0.f6278a, Process.myUid(), buildGuestProcessScope)) == null) {
                        i = i10;
                    } else {
                        int i13 = readStrongGuestSnapshot.f6278a;
                        C1576l c1576l = readStrongGuestSnapshot.f6279b;
                        i = i10;
                        if (c1576l.f6314c != c1563c0.f6279b.f6314c) {
                            continue;
                        } else {
                            int i14 = c1576l.f6313b;
                            C1563c0 c1563c02 = selectOrphanGuestTree.get(Integer.valueOf(i14));
                            if (i14 == 1 || (c1563c02 != null && !readOwnAncestorPids.contains(Integer.valueOf(i14)))) {
                                if (c1563c02 != null) {
                                    C1565d0 readProcStat = readProcStat(AbstractC3356a.m5776b(i14, "/proc/"));
                                    EnumC1567e0 enumC1567e0 = readProcStat.f6283a;
                                    if (enumC1567e0 == EnumC1567e0.f6287k) {
                                        if (!unknownProcReadProvesExit(i14)) {
                                            throw unknownProcessIdentity(i14, "selected parent stat");
                                        }
                                    } else if (enumC1567e0 != EnumC1567e0.f6286j) {
                                        if (AbstractC1578n.m2653b(c1563c02.f6279b, (C1576l) readProcStat.f6284b)) {
                                        }
                                    }
                                }
                                Log.i(TAG, "killing stale guest pid=" + i13 + " ppid=" + i14 + " exe=" + readStrongGuestSnapshot.f6280c + " cwd=" + readStrongGuestSnapshot.f6281d);
                                Process.killProcess(i13);
                                i11++;
                            }
                        }
                    }
                    i10 = i;
                }
                int i15 = i10;
                if (i11 == 0) {
                    break;
                }
                SystemClock.sleep(50L);
                i10 = i15 + 1;
            } else {
                return;
            }
        }
        Map<Integer, C1563c0> selectOrphanGuestTree2 = selectOrphanGuestTree(snapshotStrongGuestProcesses(buildGuestProcessScope));
        selectOrphanGuestTree2.keySet().removeAll(readOwnAncestorPids);
        if (selectOrphanGuestTree2.isEmpty()) {
            return;
        }
        throw new IllegalStateException("Unable to stop stale guest processes: " + selectOrphanGuestTree2.keySet());
    }

    private static /* synthetic */ C4245b lambda$extractRootfs$1(boolean z10, Context context) {
        if (z10) {
            return extract3rdRootfs(context);
        }
        return extractRootfsInAssets(context);
    }

    private static native int nativeExtractRootfs(int i, long j3, long j10, String str, long j11, byte[] bArr);

    private static native int nativeRecoverRootfs(String str);

    private static native int nativeRepairRootfs(int i, long j3, long j10, String str, long j11, byte[] bArr, String[] strArr);

    public static boolean needsUpgrade(Context context) {
        String m6944a;
        C4245b installedRomInfo = getInstalledRomInfo(context);
        C4245b packagedRomInfo = getPackagedRomInfo(context);
        String str = TAG;
        StringBuilder sb = new StringBuilder("installed ROM: ");
        String str2 = "invalid";
        if (installedRomInfo == null) {
            m6944a = "invalid";
        } else {
            m6944a = installedRomInfo.m6944a();
        }
        sb.append(m6944a);
        Log.i(str, sb.toString());
        StringBuilder sb2 = new StringBuilder("packaged ROM: ");
        if (packagedRomInfo != null) {
            str2 = packagedRomInfo.m6944a();
        }
        sb2.append(str2);
        Log.i(str, sb2.toString());
        if (installedRomInfo != null && packagedRomInfo != null && romExist(context) && installedRomInfo.m6946e(packagedRomInfo)) {
            if (!installedRomInfo.m6946e(packagedRomInfo) || installedRomInfo.f15831a != packagedRomInfo.f15831a || !installedRomInfo.f15832b.equals(packagedRomInfo.f15832b) || !Objects.equals(installedRomInfo.f15833c, packagedRomInfo.f15833c) || !installedRomInfo.f15834d.equals(packagedRomInfo.f15834d) || !installedRomInfo.f15838h.equals(packagedRomInfo.f15838h)) {
                try {
                    writeInstalledRomInfo(context, packagedRomInfo);
                    Log.i(str, "refreshed ROM marker metadata without re-extracting payload");
                    return false;
                } catch (IOException e10) {
                    Log.e(TAG, "unable to refresh ROM marker metadata", e10);
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static EnumC1577m probeProcessAccess(int i) {
        EnumC1577m enumC1577m = EnumC1577m.f6318l;
        try {
            Os.kill(i, 0);
            return EnumC1577m.f6315i;
        } catch (ErrnoException e10) {
            int i10 = e10.errno;
            if (i10 == OsConstants.EPERM) {
                return EnumC1577m.f6316j;
            }
            if (i10 == OsConstants.ESRCH) {
                return EnumC1577m.f6317k;
            }
            return enumC1577m;
        } catch (RuntimeException unused) {
            return enumC1577m;
        }
    }

    private static C1563c0 processGoneOrThrow(int i, C1576l c1576l, String str, EnumC1567e0 enumC1567e0) {
        boolean z10;
        C1565d0 readProcStat;
        EnumC1567e0 enumC1567e02;
        C1576l c1576l2;
        EnumC1567e0 enumC1567e03 = EnumC1567e0.f6286j;
        if (enumC1567e0 == enumC1567e03) {
            z10 = true;
        } else {
            z10 = false;
        }
        Pattern pattern = AbstractC1578n.f6320a;
        if (z10 || (enumC1567e02 = (readProcStat = readProcStat(AbstractC3356a.m5776b(i, "/proc/"))).f6283a) == enumC1567e03 || (enumC1567e02 == EnumC1567e0.f6285i && (c1576l2 = (C1576l) readProcStat.f6284b) != null && AbstractC1578n.m2653b(c1576l, c1576l2))) {
            return null;
        }
        if (readProcStat.f6283a == EnumC1567e0.f6287k) {
            if (!unknownProcReadProvesExit(i)) {
                throw unknownProcessIdentity(i, AbstractC3356a.m5778d(str, " and follow-up stat"));
            }
            return null;
        }
        throw unknownProcessIdentity(i, str + " (" + enumC1567e0 + ")");
    }

    private static Set<Integer> readOwnAncestorPids() {
        HashSet hashSet = new HashSet();
        int myPid = Process.myPid();
        int i = 0;
        while (true) {
            if (myPid <= 1 || i >= 64 || !hashSet.add(Integer.valueOf(myPid))) {
                break;
            }
            C1565d0 readProcStat = readProcStat(AbstractC3356a.m5776b(myPid, "/proc/"));
            EnumC1567e0 enumC1567e0 = readProcStat.f6283a;
            if (enumC1567e0 == EnumC1567e0.f6287k) {
                if (!unknownProcReadProvesExit(myPid)) {
                    throw unknownProcessIdentity(myPid, "ancestor stat");
                }
            } else {
                if (enumC1567e0 == EnumC1567e0.f6286j) {
                    break;
                }
                myPid = ((C1576l) readProcStat.f6284b).f6313b;
                i++;
            }
        }
        return hashSet;
    }

    private static C1565d0 readProcBytes(String str) {
        try {
            return new C1565d0(EnumC1567e0.f6285i, Files.readAllBytes(Paths.get(str, new String[0])));
        } catch (NoSuchFileException unused) {
            return new C1565d0(EnumC1567e0.f6286j, null);
        } catch (IOException | RuntimeException unused2) {
            return new C1565d0(EnumC1567e0.f6287k, null);
        }
    }

    private static C1565d0 readProcLink(String str) {
        try {
            return new C1565d0(EnumC1567e0.f6285i, Files.readSymbolicLink(Paths.get(str, new String[0])).toString());
        } catch (NoSuchFileException unused) {
            return new C1565d0(EnumC1567e0.f6286j, null);
        } catch (IOException | RuntimeException unused2) {
            return new C1565d0(EnumC1567e0.f6287k, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static p089h8.C1565d0 readProcStat(java.lang.String r8) {
        /*
            java.lang.String r0 = "/stat"
            java.lang.String r8 = p198r3.AbstractC3356a.m5778d(r8, r0)
            h8.d0 r8 = readProcText(r8)
            h8.e0 r0 = r8.f6283a
            r1 = 0
            h8.e0 r2 = p089h8.EnumC1567e0.f6285i
            if (r0 == r2) goto L17
            h8.d0 r8 = new h8.d0
            r8.<init>(r0, r1)
            return r8
        L17:
            java.lang.Object r8 = r8.f6284b
            java.lang.String r8 = (java.lang.String) r8
            java.util.regex.Pattern r0 = p089h8.AbstractC1578n.f6320a
            if (r8 != 0) goto L21
        L1f:
            r8 = r1
            goto L6f
        L21:
            r0 = 41
            int r0 = r8.lastIndexOf(r0)
            if (r0 < 0) goto L1f
            r3 = 1
            int r0 = r0 + r3
            int r4 = r8.length()
            if (r0 < r4) goto L32
            goto L1f
        L32:
            java.lang.String r8 = r8.substring(r0)
            java.lang.String r8 = r8.trim()
            java.lang.String r0 = "\\s+"
            java.lang.String[] r8 = r8.split(r0)
            int r0 = r8.length
            r4 = 19
            if (r0 > r4) goto L46
            goto L1f
        L46:
            r0 = 0
            r5 = r8[r0]
            int r5 = r5.length()
            if (r5 == r3) goto L50
            goto L1f
        L50:
            r5 = r8[r0]     // Catch: java.lang.NumberFormatException -> L1f
            char r0 = r5.charAt(r0)     // Catch: java.lang.NumberFormatException -> L1f
            r3 = r8[r3]     // Catch: java.lang.NumberFormatException -> L1f
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L1f
            r8 = r8[r4]     // Catch: java.lang.NumberFormatException -> L1f
            long r4 = java.lang.Long.parseLong(r8)     // Catch: java.lang.NumberFormatException -> L1f
            if (r3 < 0) goto L1f
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 <= 0) goto L1f
            h8.l r8 = new h8.l     // Catch: java.lang.NumberFormatException -> L1f
            r8.<init>(r0, r3, r4)     // Catch: java.lang.NumberFormatException -> L1f
        L6f:
            if (r8 != 0) goto L79
            h8.d0 r8 = new h8.d0
            h8.e0 r0 = p089h8.EnumC1567e0.f6287k
            r8.<init>(r0, r1)
            goto L7f
        L79:
            h8.d0 r0 = new h8.d0
            r0.<init>(r2, r8)
            r8 = r0
        L7f:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.utils.RomManager.readProcStat(java.lang.String):h8.d0");
    }

    private static C1565d0 readProcText(String str) {
        C1565d0 readProcBytes = readProcBytes(str);
        EnumC1567e0 enumC1567e0 = readProcBytes.f6283a;
        EnumC1567e0 enumC1567e02 = EnumC1567e0.f6285i;
        if (enumC1567e0 == enumC1567e02) {
            return new C1565d0(enumC1567e02, new String((byte[]) readProcBytes.f6284b, StandardCharsets.UTF_8));
        }
        return new C1565d0(enumC1567e0, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0148, code lost:
    
        r2 = 3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static p089h8.C1563c0 readStrongGuestSnapshot(int r18, int r19, p089h8.C1561b0 r20) {
        /*
            Method dump skipped, instructions count: 537
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.utils.RomManager.readStrongGuestSnapshot(int, int, h8.b0):h8.c0");
    }

    public static void reboot(Context context) {
        context.getApplicationContext().startActivity(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()));
        shutdown(context);
    }

    private static void repairRomArtifacts(Context context) {
        if (context.getSharedPreferences("app_kv", 0).getBoolean("should_use_third_party_rom", false)) {
            extractOpenedFile(context, get3rdRootfsFile(context), context.getDataDir(), true);
        } else {
            extractRootfsInAssets(context, true);
        }
    }

    private static void resetVolatileDeviceState(File file) {
        Path path = file.toPath();
        if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            return;
        }
        try {
            Files.walkFileTree(path, new C1559a0(path));
        } catch (IOException e10) {
            throw new RuntimeException("Unable to reset Android 13 rootfs /dev", e10);
        }
    }

    public static boolean romExist(Context context) {
        return new File(getRootfsDir(context), "init").exists();
    }

    private static void saveLastKmsg(Context context) {
        try {
            Files.move(new File(context.getDataDir(), "log.txt").toPath(), new File(context.getDataDir(), "last_kmsg.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException unused) {
        }
    }

    private static Map<Integer, C1563c0> selectOrphanGuestTree(Map<Integer, C1563c0> map) {
        HashMap hashMap = new HashMap();
        for (C1563c0 c1563c0 : map.values()) {
            hashMap.put(Integer.valueOf(c1563c0.f6278a), c1563c0.f6279b);
        }
        Pattern pattern = AbstractC1578n.f6320a;
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        ArrayDeque arrayDeque = new ArrayDeque();
        for (Map.Entry entry : hashMap.entrySet()) {
            Integer num = (Integer) entry.getKey();
            num.intValue();
            int i = ((C1576l) entry.getValue()).f6313b;
            ((List) hashMap2.computeIfAbsent(Integer.valueOf(i), new C1575k(0))).add(num);
            if (i == 1) {
                hashMap3.put(num, 0);
                arrayDeque.addLast(num);
            }
        }
        while (!arrayDeque.isEmpty()) {
            Integer num2 = (Integer) arrayDeque.removeFirst();
            num2.intValue();
            List<Integer> list = (List) hashMap2.get(num2);
            if (list != null) {
                int intValue = ((Integer) hashMap3.get(num2)).intValue() + 1;
                for (Integer num3 : list) {
                    num3.intValue();
                    if (!hashMap3.containsKey(num3)) {
                        hashMap3.put(num3, Integer.valueOf(intValue));
                        arrayDeque.addLast(num3);
                    }
                }
            }
        }
        HashMap hashMap4 = new HashMap();
        for (Map.Entry entry2 : hashMap3.entrySet()) {
            C1563c0 c1563c02 = map.get(entry2.getKey());
            c1563c02.f6282e = ((Integer) entry2.getValue()).intValue();
            hashMap4.put(Integer.valueOf(c1563c02.f6278a), c1563c02);
        }
        return hashMap4;
    }

    private static void setFileMode(File file, int i) {
        try {
            Os.chmod(file.getAbsolutePath(), i);
        } catch (ErrnoException e10) {
            throw new IOException("Unable to set file mode: " + file, e10);
        }
    }

    public static boolean shouldExtractRootfs(Context context) {
        boolean z10;
        boolean romExist = romExist(context);
        boolean z11 = context.getSharedPreferences("app_kv", 0).getBoolean("rom_should_be_re_install", false);
        boolean z12 = context.getSharedPreferences("app_kv", 0).getBoolean("should_use_third_party_rom", false);
        if (!z12 && needsUpgrade(context)) {
            z10 = true;
        } else {
            z10 = false;
        }
        if (romExist && !z11 && (z12 || !z10)) {
            return false;
        }
        return true;
    }

    public static void shutdown(Context context) {
        System.exit(0);
        Process.killProcess(Process.myPid());
    }

    private static Map<Integer, C1563c0> snapshotStrongGuestProcesses(C1561b0 c1561b0) {
        C1563c0 readStrongGuestSnapshot;
        HashMap hashMap = new HashMap();
        String[] list = new File("/proc").list();
        if (list != null) {
            int myUid = Process.myUid();
            for (String str : list) {
                try {
                    int parseInt = Integer.parseInt(str);
                    if (parseInt > 1 && parseInt != Process.myPid() && (readStrongGuestSnapshot = readStrongGuestSnapshot(parseInt, myUid, c1561b0)) != null) {
                        hashMap.put(Integer.valueOf(parseInt), readStrongGuestSnapshot);
                    }
                } catch (NumberFormatException unused) {
                }
            }
            return hashMap;
        }
        throw new IllegalStateException("Unable to enumerate /proc for stale guest cleanup");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0069 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void syncDirectory(java.nio.file.Path r7) {
        /*
            java.lang.String r0 = "Unable to close directory: "
            java.lang.String r1 = "Unable to sync directory: "
            r2 = 0
            java.lang.String r3 = r7.toString()     // Catch: java.lang.Throwable -> L34 android.system.ErrnoException -> L36
            int r4 = android.system.OsConstants.O_RDONLY     // Catch: java.lang.Throwable -> L34 android.system.ErrnoException -> L36
            int r5 = android.system.OsConstants.O_CLOEXEC     // Catch: java.lang.Throwable -> L34 android.system.ErrnoException -> L36
            r4 = r4 | r5
            r5 = 0
            java.io.FileDescriptor r3 = android.system.Os.open(r3, r4, r5)     // Catch: java.lang.Throwable -> L34 android.system.ErrnoException -> L36
            android.system.Os.fsync(r3)     // Catch: java.lang.Throwable -> L2f android.system.ErrnoException -> L32
            if (r3 == 0) goto L67
            android.system.Os.close(r3)     // Catch: android.system.ErrnoException -> L1c
            goto L67
        L1c:
            r1 = move-exception
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r2.<init>(r7, r1)
            goto L67
        L2f:
            r1 = move-exception
            r2 = r3
            goto L6b
        L32:
            r2 = move-exception
            goto L3a
        L34:
            r1 = move-exception
            goto L6b
        L36:
            r3 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
        L3a:
            java.io.IOException r4 = new java.io.IOException     // Catch: java.lang.Throwable -> L2f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L2f
            r5.append(r7)     // Catch: java.lang.Throwable -> L2f
            java.lang.String r1 = r5.toString()     // Catch: java.lang.Throwable -> L2f
            r4.<init>(r1, r2)     // Catch: java.lang.Throwable -> L2f
            if (r3 == 0) goto L66
            android.system.Os.close(r3)     // Catch: android.system.ErrnoException -> L51
            goto L66
        L51:
            r1 = move-exception
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r0)
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            r2.<init>(r7, r1)
            r4.addSuppressed(r2)
        L66:
            r2 = r4
        L67:
            if (r2 != 0) goto L6a
            return
        L6a:
            throw r2
        L6b:
            if (r2 == 0) goto L83
            android.system.Os.close(r2)     // Catch: android.system.ErrnoException -> L71
            goto L83
        L71:
            r2 = move-exception
            java.io.IOException r3 = new java.io.IOException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r0)
            r4.append(r7)
            java.lang.String r7 = r4.toString()
            r3.<init>(r7, r2)
        L83:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.twoyi.utils.RomManager.syncDirectory(java.nio.file.Path):void");
    }

    private static boolean unknownProcReadProvesExit(int i) {
        EnumC1577m probeProcessAccess = probeProcessAccess(i);
        Pattern pattern = AbstractC1578n.f6320a;
        if (probeProcessAccess == EnumC1577m.f6317k) {
            return true;
        }
        return false;
    }

    private static IllegalStateException unknownProcessIdentity(int i, String str) {
        return new IllegalStateException("Unable to verify stale guest process pid=" + i + " field=" + str);
    }

    public static void wipeUserData(Context context) {
        IOUtils.deleteDirectory(new File(getRootfsDir(context), "data"));
    }

    private static void writeBootProperties(File file, Properties properties) {
        if (file.exists()) {
            setFileMode(file, 384);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
                try {
                    properties.store(outputStreamWriter, (String) null);
                    outputStreamWriter.flush();
                    fileOutputStream.getFD().sync();
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } finally {
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } finally {
            setFileMode(file, 256);
        }
    }

    public static void writeInstalledRomInfo(Context context, C4245b c4245b) {
        File filesDir = context.getFilesDir();
        Path path = new File(filesDir, "rootfs.version.tmp").toPath();
        Path path2 = new File(filesDir, ROOTFS_VERSION_FILE).toPath();
        Files.deleteIfExists(path);
        FileOutputStream fileOutputStream = new FileOutputStream(path.toFile());
        try {
            fileOutputStream.write(c4245b.m6945b());
            fileOutputStream.getFD().sync();
            fileOutputStream.close();
            try {
                Files.move(path, path2, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                syncDirectory(filesDir.toPath());
            } catch (IOException e10) {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException e11) {
                    e10.addSuppressed(e11);
                }
                throw e10;
            }
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static C4245b extractOpenedFile(Context context, File file, File file2, boolean z10) {
        C4247d m6950d;
        ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
        try {
            FileInputStream fileInputStream = new FileInputStream(open.getFileDescriptor());
            try {
                long j3 = Os.fstat(open.getFileDescriptor()).st_size;
                if (j3 > 0) {
                    if (z10) {
                        C1972m m6952f = C4247d.m6952f(fileInputStream.getChannel(), 0L, j3, C4249f.m6961c(context));
                        byte[] bArr = (byte[]) m6952f.f7745b;
                        C4245b c4245b = (C4245b) m6952f.f7747d;
                        long j10 = m6952f.f7744a;
                        C4246c c4246c = (C4246c) m6952f.f7746c;
                        m6950d = new C4247d(bArr, c4245b, j10, j3 - j10, c4246c.f15847i, c4246c.f15848j, c4246c.f15849k);
                    } else {
                        m6950d = C4247d.m6950d(fileInputStream.getChannel(), 0L, j3, C4249f.m6961c(context));
                    }
                    C4247d c4247d = m6950d;
                    c4247d.m6957i();
                    extractOrRepairOpenedRom(context, open.getFd(), c4247d.f15861b, file2, c4247d, z10);
                    C4245b c4245b2 = c4247d.f15860a;
                    open.close();
                    return c4245b2;
                }
                throw new IOException("opened ROM fd is empty");
            } catch (ErrnoException e10) {
                throw new IOException("unable to stat opened ROM fd", e10);
            }
        } finally {
        }
    }

    private static C4245b extractRootfsInAssets(Context context, boolean z10) {
        C4247d m6950d;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            AssetFileDescriptor openFd = context.getAssets().openFd(ROOTFS_NAME);
            try {
                FileInputStream fileInputStream = new FileInputStream(openFd.getFileDescriptor());
                if (z10) {
                    FileChannel channel = fileInputStream.getChannel();
                    long startOffset = openFd.getStartOffset();
                    long declaredLength = openFd.getDeclaredLength();
                    C1972m m6952f = C4247d.m6952f(channel, startOffset, declaredLength, C4249f.m6961c(context));
                    byte[] bArr = (byte[]) m6952f.f7745b;
                    C4245b c4245b = (C4245b) m6952f.f7747d;
                    long j3 = m6952f.f7744a;
                    C4246c c4246c = (C4246c) m6952f.f7746c;
                    m6950d = new C4247d(bArr, c4245b, j3, declaredLength - j3, c4246c.f15847i, c4246c.f15848j, c4246c.f15849k);
                } else {
                    m6950d = C4247d.m6950d(fileInputStream.getChannel(), openFd.getStartOffset(), openFd.getDeclaredLength(), C4249f.m6961c(context));
                }
                C4247d c4247d = m6950d;
                C4245b c4245b2 = c4247d.f15860a;
                c4247d.m6957i();
                extractOrRepairOpenedRom(context, openFd.getParcelFileDescriptor().getFd(), checkedAdd(openFd.getStartOffset(), c4247d.f15861b), context.getDataDir(), c4247d, z10);
                if (!z10) {
                    Log.i(TAG, "extract rootfs (ustar): " + (SystemClock.elapsedRealtime() - elapsedRealtime) + "ms, " + c4245b2.m6944a());
                }
                openFd.close();
                return c4245b2;
            } finally {
            }
        } catch (RuntimeException e10) {
            throw new IOException("open or extract rootfs asset failed", e10);
        }
    }

    public static File getRootfsDir(Context context, C4158a c4158a) {
        return getRootfsDir(context);
    }

    private static void createLoaderSymlink(Path path, String str) {
        try {
            Files.deleteIfExists(path);
            Files.createSymbolicLink(path, Paths.get(str, new String[0]), new FileAttribute[0]);
        } catch (IOException e10) {
            throw new RuntimeException("symlink loader failed.", e10);
        }
    }

    private static void showRootfsInstallationFailure(Context context) {
    }
}

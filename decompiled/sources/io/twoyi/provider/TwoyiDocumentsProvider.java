package io.twoyi.provider;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsProvider;
import android.webkit.MimeTypeMap;
import io.twoyi.R;
import io.twoyi.utils.RomManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import p008a7.AbstractC0108z;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* loaded from: classes.dex */
public class TwoyiDocumentsProvider extends DocumentsProvider {

    /* renamed from: i */
    public static final String[] f7300i = {"root_id", "mime_types", "flags", "icon", "title", "summary", "document_id", "available_bytes"};

    /* renamed from: j */
    public static final String[] f7301j = {"document_id", "mime_type", "_display_name", "last_modified", "flags", "_size"};

    /* renamed from: a */
    public static File m3141a(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file;
        }
        throw new FileNotFoundException(file.getAbsolutePath() + " not found");
    }

    /* renamed from: b */
    public static String m3142b(File file) {
        if (file.isDirectory()) {
            return "vnd.android.document/directory";
        }
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(name.substring(lastIndexOf + 1).toLowerCase());
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
            return "application/octet-stream";
        }
        return "application/octet-stream";
    }

    /* renamed from: c */
    public final void m3143c(MatrixCursor matrixCursor, String str, File file) {
        int i;
        String name;
        if (str == null) {
            str = file.getAbsolutePath();
        } else {
            file = m3141a(str);
        }
        if (file.isDirectory()) {
            if (file.canWrite()) {
                i = 8;
            }
            i = 0;
        } else {
            if (file.canWrite()) {
                i = 2;
            }
            i = 0;
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && parentFile.canWrite()) {
            i |= 4;
        }
        if (file.equals(RomManager.getRomSdcardDir(getContext()))) {
            name = getContext().getResources().getString(R.string.sdcard_of_twoyi);
        } else {
            name = file.getName();
        }
        String m3142b = m3142b(file);
        if (m3142b.startsWith("image/")) {
            i |= 1;
        }
        MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
        newRow.add("document_id", str);
        newRow.add("_display_name", name);
        newRow.add("_size", Long.valueOf(file.length()));
        newRow.add("mime_type", m3142b);
        newRow.add("last_modified", Long.valueOf(file.lastModified()));
        newRow.add("flags", Integer.valueOf(i));
        newRow.add("icon", Integer.valueOf(getContext().getApplicationInfo().icon));
    }

    @Override // android.provider.DocumentsProvider
    public final String createDocument(String str, String str2, String str3) {
        boolean createNewFile;
        File file = new File(str, str3);
        int i = 2;
        while (file.exists()) {
            file = new File(str, str3 + " (" + i + ")");
            i++;
        }
        try {
            if ("vnd.android.document/directory".equals(str2)) {
                createNewFile = file.mkdir();
            } else {
                createNewFile = file.createNewFile();
            }
            if (createNewFile) {
                return file.getPath();
            }
            throw new FileNotFoundException("Failed to create document: " + file.getPath());
        } catch (IOException unused) {
            throw new FileNotFoundException("Failed to create document:" + file.getPath());
        }
    }

    @Override // android.provider.DocumentsProvider
    public final void deleteDocument(String str) {
        if (m3141a(str).delete()) {
        } else {
            throw new FileNotFoundException(AbstractC0108z.m337m("Failed to delete document:", str));
        }
    }

    @Override // android.provider.DocumentsProvider
    public final String getDocumentType(String str) {
        return m3142b(m3141a(str));
    }

    @Override // android.provider.DocumentsProvider
    public final boolean isChildDocument(String str, String str2) {
        if (str != null && str2 != null) {
            return str2.startsWith(str);
        }
        return false;
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        return true;
    }

    @Override // android.provider.DocumentsProvider
    public final ParcelFileDescriptor openDocument(String str, String str2, CancellationSignal cancellationSignal) {
        return ParcelFileDescriptor.open(m3141a(str), ParcelFileDescriptor.parseMode(str2));
    }

    @Override // android.provider.DocumentsProvider
    public final Cursor queryChildDocuments(String str, String[] strArr, String str2) {
        if (strArr == null) {
            strArr = f7301j;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        File[] listFiles = m3141a(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                m3143c(matrixCursor, null, file);
            }
        }
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public final Cursor queryDocument(String str, String[] strArr) {
        if (strArr == null) {
            strArr = f7301j;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        m3143c(matrixCursor, str, null);
        return matrixCursor;
    }

    @Override // android.provider.DocumentsProvider
    public final Cursor queryRoots(String[] strArr) {
        File romSdcardDir = RomManager.getRomSdcardDir(getContext());
        if (strArr == null) {
            strArr = f7300i;
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        String charSequence = getContext().getApplicationInfo().loadLabel(getContext().getPackageManager()).toString();
        MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
        newRow.add("root_id", "0");
        newRow.add("document_id", romSdcardDir.getAbsolutePath());
        newRow.add("summary", getContext().getResources().getString(R.string.sdcard_of_twoyi));
        newRow.add("flags", 19);
        newRow.add("title", charSequence);
        newRow.add("mime_types", "*/*");
        newRow.add("available_bytes", Long.valueOf(romSdcardDir.getFreeSpace()));
        newRow.add("icon", Integer.valueOf(getContext().getApplicationInfo().icon));
        return matrixCursor;
    }
}

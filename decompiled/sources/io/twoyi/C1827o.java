package io.twoyi;

import java.util.Objects;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.o */
/* loaded from: classes.dex */
public final class C1827o {

    /* renamed from: a */
    public static volatile C1827o f7298a;

    /* renamed from: a */
    public static synchronized void m3140a(C1827o c1827o) {
        synchronized (C1827o.class) {
            try {
                C1827o c1827o2 = f7298a;
                if (c1827o2 != null) {
                    if (c1827o2.equals(c1827o)) {
                        return;
                    }
                    throw new IllegalStateException("guest endpoints already configured: " + c1827o2);
                }
                Renderer.configureEndpoints("/data/data/io.twoyi/rootfs", "/data/data/io.twoyi/rootfs/dev/input/touch", "/data/data/io.twoyi/rootfs/dev/input/key0", "twoyi_hal", "TWOYI_SOCK", "/data/data/io.twoyi/rootfs/gfxstream-vulkan");
                f7298a = c1827o;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj || (obj instanceof C1827o)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash("/data/data/io.twoyi/rootfs", "/data/data/io.twoyi/rootfs/dev/input/touch", "/data/data/io.twoyi/rootfs/dev/input/key0", "twoyi_hal", "TWOYI_SOCK", "/data/data/io.twoyi/rootfs/gfxstream-vulkan");
    }

    public final String toString() {
        return "GuestEndpoints{renderer=/data/data/io.twoyi/rootfs touch=/data/data/io.twoyi/rootfs/dev/input/touch key=/data/data/io.twoyi/rootfs/dev/input/key0 hal=@twoyi_hal control=@TWOYI_SOCK gfxstream=/data/data/io.twoyi/rootfs/gfxstream-vulkan}";
    }
}

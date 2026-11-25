package io.twoyi.host;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.host.f */
/* loaded from: classes.dex */
public final /* synthetic */ class RunnableC1784f implements Runnable {

    /* renamed from: i */
    public final /* synthetic */ boolean f7060i;

    @Override // java.lang.Runnable
    public final void run() {
        boolean z10 = this.f7060i;
        if (C1794k.f7093h != z10) {
            C1794k.f7093h = z10;
            InterfaceC1803o0 interfaceC1803o0 = C1794k.f7086a;
            C1794k.m3063b();
            if (z10) {
                InterfaceC1803o0 interfaceC1803o02 = C1794k.f7086a;
                if (interfaceC1803o02 != null) {
                    C1794k.f7090e.execute(new RunnableC1780d(interfaceC1803o02, 2));
                }
                C1794k.m3064c();
            }
        }
    }
}

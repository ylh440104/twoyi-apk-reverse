package io.twoyi;

import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.util.Log;
import io.twoyi.host.HostDisplayService;
import java.util.ArrayList;
import p008a7.AbstractC0108z;
import p028c4.RunnableC0690k;
import p265x8.AbstractC4450j;
import p287z8.AbstractC4695a;

/* compiled from: r8-map-id-7bf9c8a41511711ea9494642f15e05c388791a88a4daf33624d11626852f77fa */
/* renamed from: io.twoyi.c0 */
/* loaded from: classes.dex */
public final class RemoteCallbackListC1706c0 extends RemoteCallbackList {

    /* renamed from: a */
    public final /* synthetic */ int f6649a;

    /* renamed from: b */
    public final /* synthetic */ AbstractServiceC1716h0 f6650b;

    public /* synthetic */ RemoteCallbackListC1706c0(AbstractServiceC1716h0 abstractServiceC1716h0, int i) {
        this.f6649a = i;
        this.f6650b = abstractServiceC1716h0;
    }

    @Override // android.os.RemoteCallbackList
    public final void onCallbackDied(IInterface iInterface, Object obj) {
        int i;
        Integer num;
        switch (this.f6649a) {
            case AbstractC4695a.f17215a /* 0 */:
                if (obj instanceof Integer) {
                    AbstractServiceC1710e0 abstractServiceC1710e0 = (AbstractServiceC1710e0) this.f6650b;
                    int intValue = ((Integer) obj).intValue();
                    abstractServiceC1710e0.getClass();
                    ArrayList arrayList = new ArrayList();
                    synchronized (abstractServiceC1710e0.f6680n) {
                        i = 0;
                        for (int i10 = 0; i10 < abstractServiceC1710e0.f6681o.size(); i10++) {
                            try {
                                long keyAt = abstractServiceC1710e0.f6681o.keyAt(i10);
                                if (((int) (keyAt >>> 32)) == intValue) {
                                    arrayList.add(Long.valueOf(keyAt));
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        StringBuilder m341q = AbstractC0108z.m341q(intValue, "UI process ", " died; detaching ");
                        m341q.append(arrayList.size());
                        m341q.append(" surface(s)");
                        Log.i("e0", m341q.toString());
                    }
                    int size = arrayList.size();
                    while (i < size) {
                        Object obj2 = arrayList.get(i);
                        i++;
                        abstractServiceC1710e0.m2802e(((Long) obj2).longValue());
                    }
                    return;
                }
                return;
            default:
                AbstractC4450j.m7255e((InterfaceC1836s0) iInterface, "listener");
                if (obj instanceof Integer) {
                    num = (Integer) obj;
                } else {
                    num = null;
                }
                if (num != null) {
                    HostDisplayService hostDisplayService = (HostDisplayService) this.f6650b;
                    hostDisplayService.f7020l.post(new RunnableC0690k(hostDisplayService, num.intValue(), 4));
                    return;
                }
                return;
        }
    }
}

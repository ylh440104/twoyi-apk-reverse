/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.twoyi;

import android.content.Context;
import android.content.Intent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author weishu
 * @date 2021/10/27.
 */

public class TwoyiStatusManager {

    private static final TwoyiStatusManager INSTANCE = new TwoyiStatusManager();
    private TwoyiStatusManager() {
    }

    private final AtomicBoolean mStarted = new AtomicBoolean(false);
    private final AtomicBoolean mShown = new AtomicBoolean(false);

    private volatile CountDownLatch mBootLatch = new CountDownLatch(1);

    public static TwoyiStatusManager getInstance() {
        return INSTANCE;
    }

    public void updateVisibility(boolean visible) {
        mShown.set(visible);
    }

    public synchronized void markStarted() {
        if (mStarted.compareAndSet(false, true)) {
            mBootLatch.countDown();
        }
    }

    public boolean isStarted() {
        return mStarted.get();
    }

    public synchronized void reset() {
        CountDownLatch previous = mBootLatch;
        mStarted.set(false);
        mShown.set(false);
        mBootLatch = new CountDownLatch(1);
        // Release observers of the previous boot; they must not report success.
        previous.countDown();
    }

    public boolean waitBoot(long timeout, TimeUnit unit) throws InterruptedException, BrokenBarrierException {
        CountDownLatch boot = mBootLatch;
        if (!boot.await(timeout, unit)) {
            return false;
        }
        synchronized (this) {
            if (boot != mBootLatch) {
                throw new BrokenBarrierException("boot session reset");
            }
            return mStarted.get();
        }
    }

    public void switchOs(Context context) {
        if (!mStarted.get()) {
            return;
        }

        Intent intent;
        if (mShown.get()) {
            intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
        } else {
            intent = new Intent(context, Render2Activity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
        mShown.set(!mShown.get());
    }
}

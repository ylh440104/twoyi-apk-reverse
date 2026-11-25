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

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.twoyi;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cleveroad.androidmanimation.LoadingAnimationView;

import java.util.concurrent.TimeUnit;
import java.lang.ref.WeakReference;

import io.twoyi.utils.LogEvents;
import io.twoyi.utils.NavUtils;
import io.twoyi.utils.RomManager;

/**
 * @author weishu
 * @date 2021/10/20.
 */
public class Render2Activity extends Activity implements View.OnTouchListener {

    private static final String TAG = "Render2Activity";
    private static final WindowOwner WINDOW_OWNER = new WindowOwner();

    private SurfaceView mSurfaceView;

    private ViewGroup mRootView;
    private LoadingAnimationView mLoadingView;
    private TextView mLoadingText;
    private View mLoadingLayout;
    private View mBootLogView;
    private Thread mBootObserver;
    private final Runnable mExitListener = () -> runOnUiThread(this::showGuestFailedUi);

    private void showGuestFailedUi() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (mBootObserver != null) {
            mBootObserver.interrupt();
        }
        if (mLoadingView != null) {
            mLoadingView.stopAnimation();
        }
        if (mLoadingLayout != null) {
            mLoadingLayout.setVisibility(View.VISIBLE);
        }
        if (mLoadingText != null) {
            mLoadingText.setVisibility(View.VISIBLE);
            mLoadingText.setText(R.string.boot_failed);
        }
    }


    private final SurfaceHolder.Callback mSurfaceCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            Surface surface = holder.getSurface();
            WindowManager windowManager = getWindowManager();
            Display defaultDisplay = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);

            float xdpi = displayMetrics.xdpi;
            float ydpi = displayMetrics.ydpi;

            try {
                Renderer.init(surface, RomManager.getLoaderPath(getApplicationContext()), xdpi, ydpi, (int) getBestFps());
                WINDOW_OWNER.attach(holder);
            } catch (IllegalStateException e) {
                Log.e(TAG, "Guest launch failed", e);
                if (mBootObserver != null) {
                    mBootObserver.interrupt();
                }
                mLoadingView.stopAnimation();
                mLoadingText.setVisibility(View.VISIBLE);
                mLoadingText.setText(R.string.boot_failed);
                stopService(new android.content.Intent(Render2Activity.this, GuestSessionService.class));
                return;
            }

            Log.i(TAG, "surfaceCreated");
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
            if (!WINDOW_OWNER.isCurrent(holder)) {
                return;
            }
            Surface surface = holder.getSurface();
            Renderer.resetWindow(surface, 0, 0, mSurfaceView.getWidth(), mSurfaceView.getHeight());
            Log.i(TAG, "surfaceChanged: " + mSurfaceView.getWidth() + "x" + mSurfaceView.getHeight());
        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            if (WINDOW_OWNER.detach(holder)) {
                Renderer.removeWindow(holder.getSurface());
            }
            Log.i(TAG, "surfaceDestroyed!");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean started = TwoyiStatusManager.getInstance().isStarted();
        Log.i(TAG, "onCreate: " + savedInstanceState + " isStarted: " + started);

        super.onCreate(savedInstanceState);

        // A completed guest session belongs to the process, not this Activity.
        // Keep its completion signal so a replacement window can observe it.
        // The process starts with an incomplete latch. Activity recreation
        // must not reset it or race a BOOT_COMPLETED notification.

        NavUtils.hideNavigation(getWindow());

        setContentView(R.layout.ac_render);
        mRootView = findViewById(R.id.root);

        mSurfaceView = new SurfaceView(this);
        mSurfaceView.getHolder().addCallback(mSurfaceCallback);

        mLoadingLayout = findViewById(R.id.loadingLayout);
        mLoadingView = findViewById(R.id.loading);
        mLoadingText = findViewById(R.id.loadingText);
        mBootLogView = findViewById(R.id.bootlog);

        mLoadingLayout.setVisibility(View.VISIBLE);
        mLoadingView.startAnimation();

        TwoyiStatusManager status = TwoyiStatusManager.getInstance();
        status.addExitListener(mExitListener);
        if (status.isFailed()) {
            showGuestFailedUi();
        } else if (started) {
            GuestSessionService.start(this);
            // Do not reinstall or reinitialize a ROM that is already running.
            mRootView.addView(mSurfaceView, 0);
            showBootingProcedure();
        } else {
            UITips.checkForAndroid12(this, this::bootSystem);
        }

        mSurfaceView.setOnTouchListener(this);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: " + savedInstanceState);

        // Surface callbacks reconnect the new view to the process-owned renderer.
    }

    private void bootSystem() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        GuestSessionService.start(this);
        showTipsForFirstBoot();
        WeakReference<Render2Activity> observer = new WeakReference<>(this);
        RomPreparation.ensureReady(getApplicationContext()).whenComplete((ignored, failure) -> {
            Render2Activity activity = observer.get();
            if (activity != null) {
                activity.runOnUiThread(() -> activity.onRomPrepared(failure));
            }
        });
    }

    private void onRomPrepared(Throwable failure) {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        if (failure != null) {
            Log.e(TAG, "ROM preparation failed", failure);
            mLoadingView.stopAnimation();
            mLoadingText.setText(R.string.boot_failed);
            return;
        }
        if (mSurfaceView.getParent() == null) {
            mRootView.addView(mSurfaceView, 0);
        }
        showBootingProcedure();
    }

    private void showTipsForFirstBoot() {
        mLoadingText.setText(R.string.extracting_tips);
        mRootView.postDelayed(() -> {
            if (!isFinishing() && !isDestroyed() && RomPreparation.isExtracting()) {
                mLoadingText.setText(R.string.first_boot_tips);
            }
        }, 5000);

        mRootView.postDelayed(() -> {
            if (!isFinishing() && !isDestroyed() && RomPreparation.isExtracting()) {
                mLoadingText.setText(R.string.first_boot_tips2);
            }
        }, 10 * 1000);

        mRootView.postDelayed(() -> {
            if (!isFinishing() && !isDestroyed() && RomPreparation.isExtracting()) {
                mLoadingText.setText(R.string.first_boot_tips3);
            }
        }, 15 * 1000);
    }

    private void showBootingProcedure() {
        // mLoadingText.setText(R.string.booting_tips);
        mLoadingText.setVisibility(View.GONE);
        mBootLogView.setVisibility(View.VISIBLE);
        if (mBootObserver != null && mBootObserver.isAlive()) {
            return;
        }
        mBootObserver = new Thread(() -> {

            if (true) {
                boolean success = false;
                try {
                    success = TwoyiStatusManager.getInstance().waitBoot(15, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } catch (java.util.concurrent.BrokenBarrierException e) {
                    return;
                }

                if (isFinishing() || isDestroyed()) {
                    return;
                }
                if (TwoyiStatusManager.getInstance().isFailed()) {
                    runOnUiThread(this::showGuestFailedUi);
                    return;
                }
                if (!success) {
                    LogEvents.trackBootFailure(getApplicationContext());

                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), R.string.boot_failed, Toast.LENGTH_SHORT).show());

                    // Continue observing a slow boot; a timeout is not proof
                    // that the guest has exited. Destroying this view cancels
                    // its observer without touching the process-owned guest.
                    while (!isFinishing() && !isDestroyed() && !TwoyiStatusManager.getInstance().isFailed()) {
                        try {
                            if (TwoyiStatusManager.getInstance().waitBoot(1, TimeUnit.SECONDS)) {
                                success = true;
                                break;
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        } catch (java.util.concurrent.BrokenBarrierException e) {
                            return;
                        }
                    }
                    if (TwoyiStatusManager.getInstance().isFailed()) {
                        runOnUiThread(this::showGuestFailedUi);
                        return;
                    }
                    if (!success) {
                        return;
                    }
                }
            }

            runOnUiThread(() -> {
                if (isFinishing() || isDestroyed()) {
                    return;
                }
                mLoadingView.stopAnimation();
                mLoadingLayout.setVisibility(View.GONE);
            });
        }, "waiting-boot");
        mBootObserver.start();
    }

    @Override
    protected void onDestroy() {
        TwoyiStatusManager.getInstance().removeExitListener(mExitListener);
        if (mBootObserver != null) {
            mBootObserver.interrupt();
        }
        if (mLoadingView != null) {
            mLoadingView.stopAnimation();
        }
        super.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            NavUtils.hideNavigation(getWindow());
        }

        // Update global visibility.
        TwoyiStatusManager.getInstance().updateVisibility(hasFocus);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Renderer.handleTouch(event);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: " + keyCode);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            // TODO: 2021/10/26 Add Volume control
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Renderer.sendKeycode(KeyEvent.KEYCODE_BACK);
    }

    private float getBestFps() {
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Display.Mode[] supportedModes = defaultDisplay.getSupportedModes();
        float fps = 45;
        for (Display.Mode supportedMode : supportedModes) {
            float refreshRate = supportedMode.getRefreshRate();
            if (refreshRate > fps) {
                // fps = refreshRate;
            }
        }

        Log.w(TAG, "current fps: " + fps);
        return fps;
    }
}

/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.twoyi;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;
import io.twoyi.utils.AppKV;
import io.twoyi.utils.RomManager;

/** One preparation task per host process, independent of Activity lifetime. */
final class RomPreparation {
    private static CompletableFuture<Void> preparation;
    private static volatile boolean extracting;

    static boolean isExtracting() { return extracting; }

    static synchronized CompletableFuture<Void> ensureReady(Context context) {
        if (preparation != null) {
            return preparation;
        }
        Context app = context.getApplicationContext();
        CompletableFuture<Void> result = new CompletableFuture<>();
        preparation = result;
        new Thread(() -> {
            try {
                prepare(app);
                result.complete(null);
            } catch (Exception e) {
                // Keep the failed result: reopening the UI must not start a
                // second destructive extraction in this process.
                result.completeExceptionally(e);
            } finally {
                extracting = false;
            }
        }, "prepare-rom").start();
        return result;
    }

    private static void prepare(Context context) throws IOException {
        if (TwoyiStatusManager.getInstance().isStarted()) {
            return;
        }
        File pending = new File(context.getFilesDir(), "rom-install.pending");
        boolean exists = RomManager.romExist(context);
        boolean upgrade = RomManager.needsUpgrade(context);
        boolean forced = pending.exists() || AppKV.getBooleanConfig(context,
                AppKV.FORCE_ROM_BE_RE_INSTALL, false);
        boolean thirdParty = AppKV.getBooleanConfig(context,
                AppKV.SHOULD_USE_THIRD_PARTY_ROM, false);
        if (!exists || forced || (!thirdParty && upgrade)) {
            extracting = true;
            Files.write(pending.toPath(), new byte[]{1});
            RomManager.extractRootfs(context, exists, upgrade, forced, thirdParty);
            if (!RomManager.romExist(context)
                    || !new File(RomManager.getRootfsDir(context), "system").isDirectory()) {
                throw new IOException("ROM extraction did not produce required boot files");
            }
            RomManager.initRootfs(context);
            Files.delete(pending.toPath());
        }
    }
}

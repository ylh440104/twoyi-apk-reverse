/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package io.twoyi;

import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TwoyiMessenger {
    public static final String TAG = "TwoyiMessenger";
    public static final String SWITCH_HOST = "SWITCH_HOST";
    public static final String PING = "PING";
    private static final String SOCK_NAME = "TWOYI_SOCK";
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private static TwoyiMessenger INSTANCE;

    private LocalSocket socket;
    private OutputStream output;

    private TwoyiMessenger() {
    }

    public synchronized TwoyiMessenger connect() {
        if (socket != null) {
            return this;
        }
        LocalSocket candidate = new LocalSocket(LocalSocket.SOCKET_SEQPACKET);
        try {
            candidate.connect(new LocalSocketAddress(SOCK_NAME));
            OutputStream connectedOutput = candidate.getOutputStream();
            // Publish a connection only after both operations succeeded.
            socket = candidate;
            output = connectedOutput;
        } catch (IOException e) {
            try {
                candidate.close();
            } catch (IOException ignored) {
            }
            Log.w(TAG, "control socket connection failed", e);
        }
        return this;
    }

    public static synchronized TwoyiMessenger getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TwoyiMessenger();
        }
        return INSTANCE;
    }

    public void send(String msg) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            EXECUTOR.execute(() -> write(msg));
        } else {
            write(msg);
        }
    }

    private synchronized void write(String msg) {
        connect();
        if (output == null) {
            return;
        }
        try {
            // One write preserves the existing SEQPACKET message boundary.
            output.write(msg.getBytes(StandardCharsets.US_ASCII));
            output.flush();
        } catch (IOException e) {
            Log.w(TAG, "control socket send failed; next send will reconnect", e);
            try {
                socket.close();
            } catch (IOException ignored) {
            } finally {
                socket = null;
                output = null;
            }
            // Do not replay a possibly delivered SWITCH_HOST toggle.
        }
    }
}

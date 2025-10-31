/*
 *  Copyright (c) 2018-2025 twinlife SA.
 *  SPDX-License-Identifier: AGPL-3.0-only
 *
 *  Contributors:
 *   Christian Jacquemot (Christian.Jacquemot@twinlife-systems.com)
 *   Stephane Carrez (Stephane.Carrez@twin.life)
 */

package org.libwebsockets;

import androidx.annotation.NonNull;

/**
 * Represents a websocket connection.
 */
public class Session {
    private long nativeSession; // pointer to the websocket::Session* instance

    public Session(long nativeSession) {
        this.nativeSession = nativeSession;
    }

    /**
     * Check if the public/private key is valid.
     *
     * @return true if the public/private key is valid.
     */
    public boolean isValid() {

        return nativeSession != 0;
    }

    public boolean sendMessage(@NonNull byte[] message, boolean binary) {

        return nativeSendMessage(this.nativeSession, message, binary);
    }

    public boolean close() {

        boolean result = nativeClose(this.nativeSession);
        this.nativeSession = 0;
        return result;
    }

    public long getSessionId() {

        return nativeGetSessionId(this.nativeSession);
    }

    public int getActiveSocket() {

        return nativeGetActiveSocket(this.nativeSession);
    }

    private static native boolean nativeSendMessage(long session, byte[] message, boolean binary);
    private static native boolean nativeClose(long session);
    private static native long nativeGetSessionId(long session);
    private static native int nativeGetActiveSocket(long session);
}

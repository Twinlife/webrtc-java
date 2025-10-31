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

import org.webrtc.CalledByNative;

import java.nio.ByteBuffer;

/**
 * Observer interface called by the libwebsocket when some event occurred.
 */
public interface Observer {
    @CalledByNative
    void onConnect(long sessionId, @NonNull ConnectionStats[] stats, int active);

    @CalledByNative
    void onConnectError(long sessionId, @NonNull ConnectionStats[] stats, int error);

    @CalledByNative
    void onReceive(long sessionId, @NonNull ByteBuffer message, boolean binary);

    @CalledByNative
    void onClose(long sessionId);
}

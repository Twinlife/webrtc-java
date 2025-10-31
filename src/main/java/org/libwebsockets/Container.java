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
import androidx.annotation.Nullable;

/**
 * Defines the libwebsocket container onto which we can create websocket sessions.
 */
public class Container {
    private long nativeContainer; // pointer to the websocket::Container* instance

    public static final int CONFIG_SECURE = 0x01;          // Use TLS
    public static final int CONFIG_DIRECT_CONNECT = 0x02;  // Start a direct connection
    public static final int CONFIG_FIRST_PROXY = 0x04;     // Start a connection by using the first proxy
    public static final int CONFIG_KEEP_OTHERS = 0x08;     // Keep other websocket running even if we are connected
    public static final int CONFIG_NO_DIRECT = 0x10;       // Don't make a direct connection
    public static final int CONFIG_DISABLE_SNI = 0x20;     // Disable sending the SNI in ClientHello
    public static final int CONFIG_SNI_PASSTHROUGH = 0x40; // Proxy mode in SNI passthrough
    public static final int CONFIG_SNI_OVERRIDE = 0x80;    // Override the SNI with a custom value
    public static final int CONFIG_TRY_CUSTOM_SNI = 0x100; // Try custom SNI override after a delay if direct connect failed

    public Container(int level) {

        nativeContainer = nativeCreateContainer(level);
    }

    @Nullable
    public Session create(@NonNull Observer observer, long sessionId, int port,
                          @NonNull String host, @Nullable String customSNI, @NonNull String path,
                          int method, long timeout, @Nullable SocketProxyDescriptor[] proxies) {

        long sessionObj = nativeCreateSession(nativeContainer, observer, sessionId, port, host, customSNI, path, method, timeout, proxies);
        return sessionObj == 0 ? null : new Session(sessionObj);
    }

    public void service(int timeout) {

        nativeService(nativeContainer, timeout);
    }

    public void triggerWorker() {

        nativeTriggerWorker(nativeContainer);
    }

    /**
     * Release the internal memory allocated for the public/private keypair.
     */
    public void dispose() {
        long n = nativeContainer;
        if (n != 0) {
            nativeContainer = 0;
            nativeDispose(n);
        }
    }

    private static native long nativeCreateContainer(int level);
    private static native long nativeCreateSession(long container, Observer observer, long session, int port, String host, String customSNI, String path,
                                                   int method, long timeout, SocketProxyDescriptor[] proxies);
    private static native void nativeService(long container, int timeout);
    private static native void nativeTriggerWorker(long container);
    private static native void nativeDispose(long container);
}

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
 * Describes a proxy that can be used to establish a websocket connection.
 * A list of proxies is given in the Container.create() method the the Session
 * will be connected either directly or through one of the selected proxy.
 */
public class SocketProxyDescriptor {
    @NonNull
    public String proxyAddress;
    @Nullable
    public String proxyUsername;
    @Nullable
    public String proxyPassword;
    @Nullable
    public String proxyPath;
    int proxyPort;
    int method;

    public static SocketProxyDescriptor createSNIProxy(@NonNull String proxyAddress, int proxyPort, boolean disableSNI, @Nullable String proxyPath) {
        int method = Container.CONFIG_SNI_PASSTHROUGH;
        if (disableSNI) {
            method |= Container.CONFIG_DISABLE_SNI;
        }
    	if (proxyPath != null) {
	        method |= Container.CONFIG_SNI_OVERRIDE;
	    }
	    return new SocketProxyDescriptor(proxyAddress, proxyPort, proxyPath, method);
    }

    public static SocketProxyDescriptor createKeyProxy(@NonNull String proxyAddress, int proxyPort, @NonNull String proxyPath) {
    	return new SocketProxyDescriptor(proxyAddress, proxyPort, proxyPath, 0);
    }
    private SocketProxyDescriptor(@NonNull String proxyAddress, int proxyPort, @Nullable String proxyPath, int method) {
        this.proxyAddress = proxyAddress;
        this.proxyPort = proxyPort;
        this.proxyPath = proxyPath;
        this.method = method;
    }
}

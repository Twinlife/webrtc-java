/*
 *  Copyright (c) 2018-2025 twinlife SA.
 *  SPDX-License-Identifier: AGPL-3.0-only
 *
 *  Contributors:
 *   Christian Jacquemot (Christian.Jacquemot@twinlife-systems.com)
 *   Stephane Carrez (Stephane.Carrez@twin.life)
 */

package org.libwebsockets;

import androidx.annotation.Nullable;
import org.webrtc.CalledByNative;

/**
 * Holds connection statistics after successful connection or a connection failure.
 */
public class ConnectionStats {
    public int index;
    public int proxyIndex;
    public int connectCount;
    public long dnsTime;
    public long tcpConnectTime;
    public long txnResponseTime;
    public long tlsConnectTime;
    public boolean sniOverride;
    public boolean ipv6;
    public int lastError;
    @Nullable
    public String ipAddr;

    @CalledByNative
    public ConnectionStats(int index, int proxyIndex, boolean sniOverride, int connectCount,
                           long dnsTime, long tcpConnectTime, long txnResponseTime,
                           long tlsConnectTime, int lastError,
                           boolean ipv6, String ipAddr) {
        this.index = index;
        this.proxyIndex = proxyIndex;
        this.sniOverride = sniOverride;
        this.dnsTime = dnsTime;
        this.tcpConnectTime = tcpConnectTime;
        this.txnResponseTime = txnResponseTime;
        this.tlsConnectTime = tlsConnectTime;
        this.connectCount = connectCount;
        this.lastError = lastError;
        this.ipv6 = ipv6;
        this.ipAddr = ipAddr;
    }
}

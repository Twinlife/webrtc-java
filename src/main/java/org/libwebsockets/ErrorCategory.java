/*
 *  Copyright (c) 2021-2025 twinlife SA.
 *  SPDX-License-Identifier: AGPL-3.0-only
 *
 *  Contributors:
 *   Stephane Carrez (Stephane.Carrez@twin.life)
 */

package org.libwebsockets;

public enum ErrorCategory {
    ERR_NONE,
    ERR_DNS,
    ERR_CONNECT,
    ERR_TLS,
    ERR_TLS_HOSTNAME,
    ERR_INVALID_CA,
    ERR_TCP,
    ERR_PROXY,
    ERR_RESOURCE,
    ERR_WEBSOCKET,
    ERR_IO,
    ERR_TIMEOUT,
    ERR_UNKNOWN;

    public static ErrorCategory toErrorCategory(int errorCode) {
        switch (errorCode) {
            case 0:
                return ERR_NONE;
            case 1:
                return ERR_DNS;
            case 2:
                return ERR_CONNECT;
            case 3:
                return ERR_TLS;
            case 4:
                return ERR_TLS_HOSTNAME;
            case 5:
                return ERR_INVALID_CA;
            case 6:
                return ERR_TCP;
            case 7:
                return ERR_PROXY;
            case 8:
                return ERR_WEBSOCKET;
            case 9:
                return ERR_RESOURCE;
            case 10:
                return ERR_IO;
            case 11:
                return ERR_TIMEOUT;
            default:
                return ERR_UNKNOWN;
        }
    }
}

/**
 * Copyright (c) 2010-2022 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.blink.internal;

import java.time.Instant;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.blink.internal.dto.BlinkAccount;

/**
 * Test class.
 *
 * @author Matthias Oesterheld - Initial contribution
 */
@NonNullByDefault
public class BlinkTestUtil {

    public static BlinkAccount testBlinkAccount() {
        BlinkAccount blinkAccount = new BlinkAccount();
        blinkAccount.account = new BlinkAccount.Account();
        blinkAccount.account.account_id = 123L;
        blinkAccount.account.tier = "e006";
        blinkAccount.account.client_id = 987L;
        blinkAccount.auth = new BlinkAccount.Auth();
        blinkAccount.auth.token = "abc";
        blinkAccount.lastTokenRefresh = Instant.now();
        return blinkAccount;
    }
}
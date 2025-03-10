/*
 * Copyright (c) 2010-2025 Contributors to the openHAB project
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
package org.openhab.binding.fenecon.internal.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.junit.jupiter.api.Test;
import org.openhab.binding.fenecon.internal.FeneconBindingConstants;

/**
 * Test for {@link GridPower}.
 *
 * @author Philipp Schneider - Initial contribution
 */
@NonNullByDefault
public class GridPowerTest {

    @Test
    void testSelling() {
        GridPower gridValue = GridPower
                .get(new FeneconResponse(FeneconBindingConstants.GRID_ACTIVE_POWER_ADDRESS, "comment", "-1777"));
        assertEquals(1777, gridValue.sellTo());
        assertEquals(0, gridValue.buyFrom());
    }

    @Test
    void testBuying() {
        GridPower gridValue = GridPower
                .get(new FeneconResponse(FeneconBindingConstants.GRID_ACTIVE_POWER_ADDRESS, "comment", "1777"));
        assertEquals(1777, gridValue.buyFrom());
        assertEquals(0, gridValue.sellTo());
    }
}

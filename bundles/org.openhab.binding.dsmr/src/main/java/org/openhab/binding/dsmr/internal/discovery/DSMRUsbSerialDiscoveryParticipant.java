/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
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
package org.openhab.binding.dsmr.internal.discovery;

import static org.openhab.binding.dsmr.internal.DSMRBindingConstants.*;

import java.util.Set;

import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.config.discovery.DiscoveryResult;
import org.openhab.core.config.discovery.DiscoveryResultBuilder;
import org.openhab.core.config.discovery.usbserial.UsbSerialDeviceInformation;
import org.openhab.core.config.discovery.usbserial.UsbSerialDiscoveryParticipant;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.ThingUID;
import org.osgi.service.component.annotations.Component;

/**
 * Adds DSMR bridge discovery results for FTDI USB to Serial devices.
 *
 * @author Wouter Born - Initial contribution
 */
@Component(service = UsbSerialDiscoveryParticipant.class)
public class DSMRUsbSerialDiscoveryParticipant implements UsbSerialDiscoveryParticipant {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES = Set.of(THING_TYPE_DSMR_BRIDGE);

    public static final int VENDOR_ID = 0x0403;
    public static final int PRODUCT_ID = 0x6001;
    public static final String DEFAULT_LABEL = "DSMR Stick";

    @Override
    public Set<ThingTypeUID> getSupportedThingTypeUIDs() {
        return SUPPORTED_THING_TYPES;
    }

    @Override
    public @Nullable DiscoveryResult createResult(UsbSerialDeviceInformation deviceInformation) {
        if (isDSMRStick(deviceInformation)) {
            return DiscoveryResultBuilder.create(createBridgeThingType(deviceInformation))
                    .withLabel(createLabel(deviceInformation)).withRepresentationProperty(CONFIGURATION_SERIAL_PORT)
                    .withProperty(CONFIGURATION_SERIAL_PORT, deviceInformation.getSerialPort()).build();
        } else {
            return null;
        }
    }

    @Override
    public @Nullable ThingUID getThingUID(UsbSerialDeviceInformation deviceInformation) {
        if (isDSMRStick(deviceInformation)) {
            return createBridgeThingType(deviceInformation);
        } else {
            return null;
        }
    }

    private ThingUID createBridgeThingType(UsbSerialDeviceInformation deviceInformation) {
        String serialNumber = deviceInformation.getSerialNumber();
        if (serialNumber != null) {
            return new ThingUID(THING_TYPE_DSMR_BRIDGE, serialNumber);
        } else {
            return new ThingUID(THING_TYPE_DSMR_BRIDGE, String.valueOf(deviceInformation.getProductId()));
        }
    }

    private boolean isDSMRStick(UsbSerialDeviceInformation deviceInformation) {
        return deviceInformation.getVendorId() == VENDOR_ID && deviceInformation.getProductId() == PRODUCT_ID;
    }

    private @Nullable String createLabel(UsbSerialDeviceInformation deviceInformation) {
        String serialNumber = deviceInformation.getSerialNumber();

        if (serialNumber != null && !serialNumber.isEmpty()) {
            return String.format("%s (%s)", DEFAULT_LABEL, serialNumber);
        } else {
            return DEFAULT_LABEL;
        }
    }
}

/**
 * Oshi (https://github.com/dblock/oshi)
 * 
 * Copyright (c) 2010 - 2016 The Oshi Project Team
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * dblock[at]dblock[dot]org
 * alessandro[at]perucchi[dot]org
 * widdis[at]gmail[dot]com
 * https://github.com/dblock/oshi/graphs/contributors
 */
package oshi.software.os.mac;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

import oshi.json.NullAwareJsonObjectBuilder;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystemVersion;

/**
 * @author alessandro[at]perucchi[dot]org
 * @author widdis[at]gmail[dot]com
 */

public class MacOperatingSystem implements OperatingSystem {
    private String _family;

    private OperatingSystemVersion _version;

    private JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);

    @Override
    public OperatingSystemVersion getVersion() {
        if (this._version == null) {
            this._version = new MacOSVersionInfoEx();
        }
        return this._version;
    }

    @Override
    public String getFamily() {
        if (this._family == null)
            this._family = System.getProperty("os.name");
        return this._family;
    }

    @Override
    public String getManufacturer() {
        return "Apple";
    }

    @Override
    public JsonObject toJSON() {
        return NullAwareJsonObjectBuilder.wrap(jsonFactory.createObjectBuilder()).add("manufacturer", getManufacturer()).add("family", getFamily())
                .add("version", getVersion().toJSON()).build();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getManufacturer());
        sb.append(" ");
        sb.append(getFamily());
        sb.append(" ");
        sb.append(getVersion().toString());
        return sb.toString();
    }
}

/**
 * Copyright 2016 Boundless, http://boundlessgeo.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License
 */
package com.boundlessgeo.spatialconnect.mqtt;

public enum QoS {

    AT_MOST_ONCE(0),
    AT_LEAST_ONCE(1),
    EXACTLY_ONCE(2);

    private final int value;

    /**
     * @param value
     */
    QoS(int value) {
        this.value = value;
    }

    /**
     * The QoS value (0,1,2)
     */
    public int value() {
        return value;
    }

    /**
     * @param value
     * @return QoS
     * @throws IllegalArgumentException if the value is invalid
     */
    public static QoS valueOf(int value) {
        for (QoS q : QoS.values()) {
            if (q.value == value)
                return q;
        }
        throw new IllegalArgumentException(String.format("Invalid QoS: %d", value));
    }

}
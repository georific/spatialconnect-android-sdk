/**
 * Copyright 2015-2016 Boundless, http://boundlessgeo.com
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
package com.boundlessgeo.spatialconnect.config;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SCConfig {

    @JsonProperty("stores")
    private List<SCStoreConfig> stores;

    @JsonProperty("forms")
    private List<SCFormConfig> forms;

    @JsonProperty("remote")
    private SCRemoteConfig remote;

    public SCConfig() {
    }

    public List<SCStoreConfig> getStoreConfigs() {
        return stores;
    }

    public void setStoreConfigs(List<SCStoreConfig> configs) {
        this.stores = configs;
    }

    public List<SCFormConfig> getFormConfigs() {
        return forms;
    }

    public void setFormConfigs(List<SCFormConfig> forms) {
        this.forms = forms;
    }

    public SCRemoteConfig getRemote() {
        return remote;
    }

    public void setRemote(SCRemoteConfig remote) {
        this.remote = remote;
    }
}

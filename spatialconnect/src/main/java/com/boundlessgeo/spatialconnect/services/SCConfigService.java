/*
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

package com.boundlessgeo.spatialconnect.services;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.boundlessgeo.spatialconnect.SpatialConnect;
import com.boundlessgeo.spatialconnect.config.SCConfig;
import com.boundlessgeo.spatialconnect.config.SCFormConfig;
import com.boundlessgeo.spatialconnect.config.SCStoreConfig;
import com.boundlessgeo.spatialconnect.scutilities.Json.SCObjectMapper;
import com.boundlessgeo.spatialconnect.scutilities.Storage.SCFileUtilities;
import com.boundlessgeo.spatialconnect.stores.FormStore;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;

/**
 * The SCConfigService is responsible for managing the configuration for SpatialConnect.  This includes downloading
 * remote configuration and sweeping the external storage for config files, if required. The config service is
 * also responsible for parsing the relevant parts of the config and invoking functions on other services using parts
 * of the {@link SCConfig}.
 */
public class SCConfigService extends SCService implements SCServiceLifecycle {

    private static final String LOG_TAG = SCConfigService.class.getSimpleName();
    private static final String SERVICE_NAME = "SC_CONFIG_SERVICE";
    private Context context;
    private static final String CONFIGS_DIR = "configs";
    private ArrayList<File> localConfigFiles = new ArrayList<>();
    private SCDataService dataService;

    public SCConfigService(Context context) {
        this.context = context;
        this.dataService = SpatialConnect.getInstance().getDataService();
    }

    /**
     * Loads all config files and registers the stores for each config.
     * <p/>
     * <p>First, we try to load the default config from internal storage, then we try to load other configs from the
     * external storage folder.  Note that both internal and external storage supports the "no network" use case
     * where the SDcard has data on it and the config file points to that data.  Next, we try to download any
     * configuration files from the SpatialConnect backend service defined in the {@code remote} attribute of a
     * config.  Then we load any configs that were added with {@link SCConfigService#addConfig(File)}.</p>
     */
    private void loadConfigs() {
        if (getDefaultConfig() != null) {
            loadConfigs(Arrays.asList(getDefaultConfig()));
        }
        loadConfigs(getConfigFilesFromExternalStorage());
        loadConfigs(this.getLocalConfigFiles());
    }

    /**
     * Get configs (valid JSON files with an ".scfg" extension) packaged within the app's
     * <a href="http://developer.android.com/guide/topics/data/data-storage.html#filesExternal">external storage</a>
     * directory for config files.
     */
    private List<File> getConfigFilesFromExternalStorage() {
        if (isExternalStorageWritable()) {
            File configsDir = context.getExternalFilesDir(CONFIGS_DIR);
            if (configsDir == null || !configsDir.exists()) {
                configsDir.mkdir();
            }
            Log.i(LOG_TAG, "Searching for config files in external storage directory " + configsDir.toString());
            File[] configFiles = SCFileUtilities.findFilesByExtension(configsDir, ".scfg");
            if (configFiles.length > 0) {
                return Arrays.asList(configFiles);
            }
            else {
                Log.d(LOG_TAG, "No config files found in external storage directory.");
            }
        }
        return new ArrayList<>();
    }

    /**
     * Loads the default configuration file from the app's internal files directory.  Note that the configs.scfg file
     * must be copied to the internal files directory by the application using this sdk.
     */
    private File getDefaultConfig() {
        Log.d(LOG_TAG, "Getting default config from " + context.getFilesDir() + "/config.scfg");
        File[] configFiles = SCFileUtilities.findFilesByExtension(context.getFilesDir(), "config.scfg");
        if (configFiles.length > 0) {
            return configFiles[0];
        }
        else {
            Log.w(LOG_TAG, "No default config file found in internal storage directory.");
        }
        return null;
    }

    // method to load the configuration from a file.
    private void loadConfigs(List<File> configFiles) {
        if (configFiles.size() > 0) {
            Log.d(LOG_TAG, "Loading " + configFiles.size() + " config files.");
            for (File file : configFiles) {
                final SCConfig scConfig;
                try {
                    scConfig = SCObjectMapper.getMapper().readValue(file, SCConfig.class);
                    loadConfig(scConfig);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadConfig(SCConfig config) {
        loadForms(config.getForms());
        loadDataStores(config.getStores());
        if (config.getRemote() != null) {
            SpatialConnect.getInstance().connectBackend(config.getRemote());
        }
    }

    /* Registers all the forms specified in each config file */
    private void loadForms(List<SCFormConfig> formConfigs) {
        if (formConfigs != null) {
            Log.d(LOG_TAG, "Loading "+ formConfigs.size() +" form configs");
            for (SCFormConfig formConfig : formConfigs) {
                Log.d(LOG_TAG, "Creating table for form " + formConfig.getFormKey());
                FormStore store = dataService.getFormStore();
                if (store != null) {
                    store.registerFormByConfig(formConfig);
                }
            }
        }
    }

    /* Registers all the stores specified in each config file */
    private void loadDataStores(List<SCStoreConfig> storeConfigs) {
        if (storeConfigs != null) {
            Log.d(LOG_TAG, "Loading "+ storeConfigs.size() +" store configs");
            for (SCStoreConfig storeConfig : storeConfigs) {
                Log.d(LOG_TAG, "Adding store " + storeConfig.getName() + " to data service.");
                try {
                    dataService.registerAndStartStoreByConfig(storeConfig);
                } catch (Exception ex) {
                    Log.w(LOG_TAG, "Exception adding store to data service ", ex);
                }
            }
        }
    }

    /**
     * If application developers want to store their configs within the APK itself, they can add the config using
     * this method instead of using the external storage or the network.
     */
    public void addConfig(File configFile) {
        Log.d(LOG_TAG, "Adding config file " + configFile.getPath());
        localConfigFiles.add(configFile);
    }

    private ArrayList<File> getLocalConfigFiles() {
        return localConfigFiles;
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static String getAndroidVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release + ")";
    }

    public void loadConfigFromCache() {
        SCConfig config = getCachedConfig();
        if (config != null) {
            loadConfig(config);
        }
    }

    public void setCachedConfig(SCConfig config) {

        try {
            SpatialConnect sc = SpatialConnect.getInstance();
            String configJson = SCObjectMapper.getMapper().writeValueAsString(config);
            sc.getCache().setValue(configJson, "spatialconnect.config.remote.cached");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public SCConfig getCachedConfig() {
        SCConfig returnConfig = null;
        try {
            SpatialConnect sc = SpatialConnect.getInstance();
            String configJson = sc.getCache().getStringValue("spatialconnect.config.remote.cached");
            if (configJson != null) {
                returnConfig = SCObjectMapper.getMapper().readValue(
                        configJson,
                        SCConfig.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnConfig;
    }

    public void addForm(SCFormConfig c) {
        SpatialConnect sc = SpatialConnect.getInstance();
        sc.getDataService().getFormStore().registerFormByConfig(c);
    }

    public void removeForm(SCFormConfig c) {
        SpatialConnect sc = SpatialConnect.getInstance();
        sc.getDataService().getFormStore().unregisterFormByConfig(c);
    }

    public void addStore(SCStoreConfig c) {
        SpatialConnect sc = SpatialConnect.getInstance();
        sc.getDataService().registerAndStartStoreByConfig(c);
    }

    public void removeStore(SCStoreConfig c) {
        SpatialConnect sc = SpatialConnect.getInstance();
        SCDataService dataService = sc.getDataService();
        dataService.unregisterStore(dataService.getStoreById(c.getUniqueID()));
    }

    @Override
    public Observable<Void> start() {
        Log.d(LOG_TAG, "Starting SCConfig Service.  Loading all configs");
        if (getStatus() != SCServiceStatus.SC_SERVICE_RUNNING) {
            loadConfigs();
            this.setStatus(SCServiceStatus.SC_SERVICE_RUNNING);
        }
        return Observable.empty();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void startError() {
        super.startError();
    }

    public static String serviceId() {
        return SERVICE_NAME;
    }
}
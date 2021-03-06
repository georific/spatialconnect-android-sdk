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

import android.content.Context;
import android.util.Log;

import com.boundlessgeo.spatialconnect.config.SCRemoteConfig;
import com.boundlessgeo.spatialconnect.schema.SCMessageOuterClass;
import com.boundlessgeo.spatialconnect.services.SCAuthService;
import com.boundlessgeo.spatialconnect.services.SCConfigService;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.HashMap;
import java.util.Map;

import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * MqttHandler is used to interact with the {@link org.eclipse.paho.android.service.MqttService} which acts as
 * the MQTT client, sending messages to and receiving messages from the broker.  This class handles authenticating
 * with the broker and other standard MQTT client operations (subscribe, publish, disconnect, etc).
 *
 * http://www.hivemq.com/blog/mqtt-client-library-enyclopedia-paho-android-service
 */
public class MqttHandler implements MqttCallback {

    private final static String LOG_TAG = MqttHandler.class.getSimpleName();

    private static MqttHandler instance;
    private MqttAndroidClient client;
    private Context context;
    public PublishSubject<Map<String, SCMessageOuterClass.SCMessage>> scMessageSubject = PublishSubject.create();
    public final static String REPLY_TO_TOPIC = String.format("/device/%s-replyTo", SCConfigService.getClientId());
    public static BehaviorSubject<Integer> clientConnected = BehaviorSubject.create(0);

    private MqttHandler(Context context) {
        this.context = context;
    }

    public static MqttHandler getInstance(Context context) {
        if (instance == null) {
            instance = new MqttHandler(context);
        }
        return instance;
    }

    /**
     * Initialize the MqttHander by creating an instance of MqttAndroidClient and registering this instance as the
     * client's callback listener.
     */
    public void initialize(SCRemoteConfig config) {
        Log.d(LOG_TAG, "initializing MqttHander.");
        String brokerUri = getMqttBrokerUri(config);
        client = new MqttAndroidClient(context, brokerUri, SCConfigService.getClientId());
        client.setCallback(this);
    }

    /**
     * Reads the {@code SCRemoteConfig} and returns the formatted mqtt broker uri.  The format is
     * "protocol://brokerHost:brokerPort" where protocol is tcp or ssl.
     *
     * @param config
     * @return the mqtt broker uri
     */
    private String getMqttBrokerUri(SCRemoteConfig config) {
        return String.format("%s://%s:%s",
                config.getMqttProtocol(),
                config.getMqttHost(),
                config.getMqttPort().toString()
        );
    }

    /**
     * Connect client to the MQTT broker with authToken as the username.
     */
    public void connect() {
        Log.d(LOG_TAG, "connecting to mqtt broker at " + client.getServerURI());
        // only try to connect to mqtt broker after the user has successfully authenticated
        SCAuthService.loginStatus.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean authenticated) {
                if (authenticated) {
                    try {
                        // set the clean session to remove any previous connection the broker may have for this client
                        MqttConnectOptions options = new MqttConnectOptions();
                        options.setCleanSession(true);
//                        String authToken = SCAuthService.getAccessToken();
//                        options.setUserName(authToken);
//                        options.setPassword("anypass".toCharArray());
                        client.connect(options, context, new ConnectActionListener());
                    }
                    catch (MqttException e) {
                        Log.e(LOG_TAG, "could not connect to mqtt broker.", e.getCause());
                    }
//                    catch (IOException e) {
//                        Log.e(LOG_TAG, "could not connect to mqtt broker.", e.getCause());
//                    }
                }
            }
        });

    }

    /**
     * Subscribe client to a topic.
     *
     * @param topic to subscribe to
     * @param qos   quality of service (0, 1, 2)
     */
    public void subscribe(final String topic, final int qos) {
        clientConnected.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (integer == 1) {
                    try {
                        Log.d(LOG_TAG, "subscribing to topic " + topic + " with qos " + qos);
                        client.subscribe(topic, qos);
                    }
                    catch (MqttException e) {
                        Log.e(LOG_TAG, "could not subscribe to topic " + topic, e.getCause());
                    }
                }
            }
        });
    }

    /**
     * Publish message to a topic.
     *
     * @param topic   topic to publish the message to
     * @param message SCMessage to send as payload
     * @param qos     quality of service (0, 1, 2)
     */
    public void publish(final String topic, final SCMessageOuterClass.SCMessage message, final int qos) {
        clientConnected.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                if (integer == 1) {
                    Log.d(LOG_TAG, "publishing to topic " + topic + " with qos " + qos);
                    // create a new MqttMessage from the message string
                    MqttMessage mqttMsg = new MqttMessage(message.toByteArray());
                    mqttMsg.setQos(qos);
                    try {
                        client.publish(topic, mqttMsg);
                    }
                    catch (MqttException e) {
                        Log.e(LOG_TAG, "could not publish to topic " + topic, e.getCause());
                    }
                }
            }
        });
    }

    @Override
    public void connectionLost(Throwable cause) {
        Log.d(LOG_TAG, "Lost connection to mqtt broker.", cause);
        clientConnected.onNext(0);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Log.d(LOG_TAG, "received message on topic " + topic);
        SCMessageOuterClass.SCMessage scMessage = SCMessageOuterClass.SCMessage.parseFrom(message.getPayload());
        Log.d(LOG_TAG, "message payload: " + scMessage.getPayload());
        HashMap<String, SCMessageOuterClass.SCMessage> map = new HashMap<>(1);
        map.put(topic, scMessage);
        scMessageSubject.onNext(map);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // todo: update or remove the audit table rows when changes have been successfully delivered to backend
    }

    /**
     * An implementation of an IMqttActionListener for connecting/authenticating to the broker.
     */
    class ConnectActionListener implements IMqttActionListener {
        @Override
        public void onSuccess(IMqttToken asyncActionToken) {
            Log.d(LOG_TAG, "Connection Success!");
            clientConnected.onNext(1);
            scMessageSubject.publish();
        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable ex) {
            Log.d(LOG_TAG, "Connection Failure!", ex);
        }
    }
}

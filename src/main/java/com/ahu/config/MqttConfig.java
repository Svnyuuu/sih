package com.ahu.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: MqttConfig
 * @description: mqtt配置类
 * @author: pxm
 * @createTime: 2024/6/22
 * @version: 1.0.0
 */
@Configuration
@Slf4j
public class MqttConfig {

    /**
     * MQTT服务端地址
     */
    @Value("${mqtt.url}")
    private String serverURI;
    /**
     * 订阅客户端id（不能重复）
     */
    @Value("${mqtt.clientIdSub}")
    private String clientIdSub;

    /**
     * 发布客户端id（不能重复）
     */
    @Value("${mqtt.clientIdPub}")
    private String clientIdPub;
    /**
     * 用户名
     */
    @Value("${mqtt.username}")
    private String username;
    /**
     * 密码
     */
    @Value("${mqtt.password}")
    private String password;

    /**
     * 推送的主题
     */
    @Value("${mqtt.topic}")
    private String topic;

    /**
     * mqtt客户端
     */
    @SuppressWarnings("unused")
    private static MqttClient mqttClient;
    /**
     * mqtt发送消息客户端
     */
    private static MqttClient sendMqttClient;
    static String serverURI2;

    public static MqttClient getSendClient() {

        return sendMqttClient;
    }

    static MqttConnectOptions opts;

    @Bean
    MqttConnectOptions mqttConnectOptions() {
        try {
            opts = new MqttConnectOptions();
            opts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
            opts.setMaxReconnectDelay(5 * 1000);
            opts.setConnectionTimeout(2 * 1000);
            opts.setHttpsHostnameVerificationEnabled(false);
            opts.setCleanSession(false);
            opts.setKeepAliveInterval(5);
            opts.setAutomaticReconnect(true);
            opts.setUserName(username);
            opts.setPassword(password.toCharArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
        serverURI2 = serverURI;
        return opts;
    }

    @Bean
    MqttClient sendMqttClient() {
        MqttClient sendClient = null;
        try {

            MemoryPersistence persistence = new MemoryPersistence();
            sendClient = new MqttClient(serverURI, clientIdPub + "send", persistence);
            IMqttToken token = sendClient.connectWithResult(opts);
            token.waitForCompletion();
            sendMqttClient = sendClient;
        } catch (MqttException e) {
            log.error(e.getMessage());

        }
        return sendMqttClient;
    }

    @Bean
    public MqttClient mqttClient() {
        MqttClient client = null;
        try {

            MemoryPersistence persistence = new MemoryPersistence();
            client = new MqttClient(serverURI, clientIdSub, persistence);
            IMqttToken token = client.connectWithResult(opts);
            token.waitForCompletion();

            log.info("=====================订阅所有mqtt服务端下发信息====================");

            IMqttToken iMqttToken = client.subscribeWithResponse(topic, 0);
            iMqttToken.waitForCompletion();
            String str = new String(token.getResponse().getPayload());
            System.out.println("============================" + str);
            mqttClient = client;
        } catch (MqttException e) {
            log.error(e.getMessage());
        }
        return client;
    }
}

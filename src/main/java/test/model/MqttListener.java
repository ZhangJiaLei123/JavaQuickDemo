package test.model;

import blxt.qjava.autovalue.inter.MqttClientListener;
import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

/***
 * mqtt 订阅测试
 */
@MqttClientListener(isAuthor=true, qos = {1})
public class MqttListener extends MqttClient implements MqttCallback {

    public MqttListener(String serverURI, String clientId) throws MqttException {
        super(serverURI, clientId);
    }

    @Override
    public void disconnected(MqttDisconnectResponse mqttDisconnectResponse) {
        System.out.println("disconnected");
    }

    @Override
    public void mqttErrorOccurred(MqttException e) {
        System.out.println("mqttErrorOccurred");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("messageArrived: " + s + " " + mqttMessage.toString());
    }

    @Override
    public void deliveryComplete(IMqttToken iMqttToken) {
        System.out.println("deliveryComplete");
    }

    @Override
    public void connectComplete(boolean b, String s) {
        System.out.println("connectComplete");
    }

    @Override
    public void authPacketArrived(int i, MqttProperties mqttProperties) {
        System.out.println("authPacketArrived");
    }
}

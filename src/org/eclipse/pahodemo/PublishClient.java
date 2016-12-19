package org.eclipse.pahodemo;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PublishClient implements MqttCallback{
	MqttClient client;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PublishClient().mqttPub();

	}
	
	public void mqttPub(){
	    try {
	        this.setConnection();

	        // Connect
	        client.connect();

	        // Create new message
	        MqttMessage message = new MqttMessage();
	        message.setPayload("A single test message from b112358".getBytes());
	        message.setQos(0);

	        // Publish message to a topic
	        System.out.println("Publishing a message.");
	        client.publish("pahodemo/test/b112358", message);

	        // Disconnect
	        client.disconnect();

	      } catch (MqttException e) {
	        e.printStackTrace();
	      } catch (Exception e){
	        e.printStackTrace();
	      }
	}

	public void setConnection(){
	    // Client
	    try{
	        client = new MqttClient("tcp://localhost:1883", "mqtt_test_b112358_pub");
	    } catch (MqttException e) {
	        e.printStackTrace();
	    }

	    // Connection Options
	    MqttConnectOptions options = new MqttConnectOptions();

	    // Set the will
	    options.setWill("pahodemo/clienterrors", "CRASHED - CONNECTION NOT CLOSED CLEANLY".getBytes(),2,true);

	    // Set Callback
	    client.setCallback(this);
	}
	public void deliveryComplete(IMqttDeliveryToken token) {
	    System.out.println("Message delivered to the broker.");
	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {}

	public void connectionLost(Throwable cause) {}
}

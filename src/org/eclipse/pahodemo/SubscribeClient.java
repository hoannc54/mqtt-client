package org.eclipse.pahodemo;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SubscribeClient implements MqttCallback{
	
	MqttClient client;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SubscribeClient().mqttSub();
	}
	

	public void mqttSub(){
		 try {
		        // Set connection
		        this.setConnection();

		        // Connect
		        client.connect();

		        // Subscribe

		        client.subscribe("mqtttest", 0);
		        // Disconnect
		        // client.disconnect();

		      } catch (MqttException e) {
		        e.printStackTrace();
		      }
	}
	
	public void setConnection(){
	    try {
	        // Client
	        client = new MqttClient("tcp://localhost:1883", "mqtt_test_b112358_sub");
	    } catch (MqttException e) {
	        e.printStackTrace();
	    }

//	    // Connection Options
	    MqttConnectOptions options = new MqttConnectOptions();
	    options.setCleanSession(false);

	    // Set the will
	    options.setWill("pahodemo/clienterrors", "CRASHED - CONNECTION NOT CLOSED CLEANLY".getBytes(),2,true);

	    client.setCallback(this);
	}
	
	@Override
	public void connectionLost(Throwable cause) {
	    // TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
	        throws Exception {
		System.out.println("Message Arrived: " + message + " on tipic: " + topic.getBytes());  
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
	    // TODO Auto-generated method stub

	}

}
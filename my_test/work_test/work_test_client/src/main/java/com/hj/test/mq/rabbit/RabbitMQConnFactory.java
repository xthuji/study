package com.hj.test.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnFactory {

	public static Connection getConnection() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.6.23");
		factory.setPort(5672);
		Connection connection = null;
		try {
			connection = factory.newConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnection(String host, int port) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		Connection connection = null;
		try {
			connection = factory.newConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Channel getChannel() {
		Channel channel = null;
		try {
			channel = getConnection().createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return channel;
	}
	
	public static Channel getChannel(String host, int port) {
		Channel channel = null;
		try {
			channel = getConnection(host, port).createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return channel;
	}
	
	public static void closeConnection(Connection connection) {
		if (connection==null) {
			return;
		}
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeChannel(Channel channel) {
		if (channel==null) {
			return;
		}
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeChannelAndConnection(Channel channel) {
		Connection connection = channel.getConnection();
		closeChannel(channel);
		closeConnection(connection);
	}
}

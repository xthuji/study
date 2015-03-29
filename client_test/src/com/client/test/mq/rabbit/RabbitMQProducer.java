package com.client.test.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;

public class RabbitMQProducer {

	private final static String QUEUE_NAME = "hello test";

	public static void main(String[] args) throws IOException {
		Channel channel =  RabbitMQConnFactory.getChannel();

		//实际使用时，可能不需要这行代码
//		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		RabbitMQConnFactory.closeChannelAndConnection(channel);
	}
}

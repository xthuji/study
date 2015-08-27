package com.client.test.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class RabbitMQConsumer {

	private final static String QUEUE_NAME = "hello test";

	public static void main(String[] argv) throws Exception {

		Channel channel = RabbitMQConnFactory.getChannel();

		//实际使用时，可能不需要这行代码
//		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" + message + "'");
			Thread.currentThread().sleep(1000);
		}
		
	}
}

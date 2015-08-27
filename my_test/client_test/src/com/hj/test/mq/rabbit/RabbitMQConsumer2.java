package com.hj.test.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者
 * fanout广播模式
 * 不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
 * 很像子网广播，每台子网内的主机都获得了一份复制的消息。
 * @author huji
 *
 */
public class RabbitMQConsumer2 {

	private final static String QUEUE_NAME = "hello test1";

	public static void main(String[] argv) throws Exception {

		Channel channel = RabbitMQConnFactory.getChannel();

		channel.exchangeDeclare("test", "fanout");
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(QUEUE_NAME+" [x] Received '" + message + "'");
			Thread.currentThread().sleep(1000);
		}
		
	}
}

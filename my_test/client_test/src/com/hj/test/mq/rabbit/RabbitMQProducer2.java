package com.hj.test.mq.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;

/**
 * 生产者
 * fanout广播模式
 * 不处理路由键。你只需要简单的将队列绑定到交换机上。一个发送到交换机的消息都会被转发到与该交换机绑定的所有队列上。
 * 很像子网广播，每台子网内的主机都获得了一份复制的消息。
 * @author huji
 *
 */
public class RabbitMQProducer2 {

	private final static String QUEUE_NAME = "hello test";

	public static void main(String[] args) throws IOException {
		Channel channel =  RabbitMQConnFactory.getChannel();
		
		channel.exchangeDeclare("test", "fanout");
//		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind("hello test1", "test", "routingKey");  
		channel.queueBind("hello test2", "test", "routingKey");  
		
		for (int i = 0; i < 5; i++) {
			String message = "Hello World!"+i;
			//路由键需要设置为空， 测试了一下，好像也不是必须设为空
			channel.basicPublish("test", "routingKey", null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}

		RabbitMQConnFactory.closeChannelAndConnection(channel);
	}
}

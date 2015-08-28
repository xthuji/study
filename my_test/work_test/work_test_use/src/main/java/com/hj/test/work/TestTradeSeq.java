package com.hj.test.work;

import org.junit.Test;

import com.hj.test.mq.rabbit.RabbitMQConnFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class TestTradeSeq {
	
	@Test
	public void testConsumerMessage() throws Exception {
		Channel channel = RabbitMQConnFactory.getChannel("192.168.6.165", 5672);
		String QUEUE_NAME = "q_new_recharging_test";
		String exchange = "ex_new_recharge";
		String routingKey = "rout_new_recharge";
		
//		channel.exchangeDeclare(exchange, "fanout"); // direct fanout
		// topic
//		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind(QUEUE_NAME, exchange, routingKey);
		try {
			// channel.queueDeclare(QUEUE_NAME , false, false, false, null);
			System.out
			.println(" [*] Waiting for messages. To exit press CTRL+C");
			
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(QUEUE_NAME, true, consumer);
			
			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(" [x] Received '" + message + "'");
				Thread.currentThread().sleep(1000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProcuctorMessage() throws Exception {
		
		Channel channel = RabbitMQConnFactory.getChannel("192.168.6.165", 5672);
		String QUEUE_NAME = "q_new_recharging_test";
		String exchange = "ex_new_recharge";
		String routingKey = "rout_new_recharge";
		
//		channel.exchangeDeclare(exchange, "fanout"); // direct fanout topic
		channel.queueBind(QUEUE_NAME, exchange, routingKey);
		
//		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "{\"Type\":\"giftRechargingInfo\",\"Data\":{\"OrderId\":\"142004160015445500\",\"Usr\":\"xingapple\",\"GiftType\":\"51\",\"Channel\":\"\",\"Version\":\"\",\"Price\":0.448,\"PhoneModel\":\"\",\"Phone\":\"\",\"IMEI\":\"\",\"Reserve1\":\"\",\"Date\":\"2015-01-01 00:00:00\"}}";
		channel.basicPublish(exchange, routingKey, null,
		message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		
		RabbitMQConnFactory.closeChannelAndConnection(channel);
		

	}

}

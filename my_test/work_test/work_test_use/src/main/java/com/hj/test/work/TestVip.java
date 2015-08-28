package com.hj.test.work;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hj.test.mq.rabbit.RabbitMQConnFactory;
import com.hj.test.tools.GsonUtils;
import com.hj.test.tools.TimeUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class TestVip {

	public static void main(String[] args) throws Exception {
		// consumerVip();
	}

	@Test
	public void consumerVip() throws Exception {
		Channel channel = RabbitMQConnFactory.getChannel("192.168.6.165", 5672);

		String QUEUE_NAME = "vipNotifyQueueName_pre";
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
	public void consumerVip2() throws Exception {
		Channel channel = RabbitMQConnFactory.getChannel("192.168.6.165", 5672);
		String QUEUE_NAME = "q_user_vip_test_q";
		String exchange = "vip_notify_test";
		String routingKey = "rout_vip_notify_test";

		channel.exchangeDeclare(exchange, "direct"); // direct fanout
														// topic
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
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
	public void producerVip() throws Exception {
		Channel channel = RabbitMQConnFactory.getChannel("192.168.6.165", 5672);

		String QUEUE_NAME = "vipNotifyQueueName_pre";

		// channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "{\"usr\":\"xingyooo\",\"vipLevel\":1,\"expireTime\":\"2015-02-05 16:15:00\"}";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		RabbitMQConnFactory.closeChannelAndConnection(channel);
	}

	@Test
	public void producerVip2() throws Exception {
		Channel channel = RabbitMQConnFactory.getChannel("192.168.6.165", 5672);
		String QUEUE_NAME = "q_user_vip_test_q";
		String exchange = "vip_notify_test";
		String routingKey = "rout_vip_notify_test";

		channel.exchangeDeclare(exchange, "direct"); // direct fanout
														// topic
		channel.queueBind(QUEUE_NAME, exchange, routingKey);

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "{\"usr\":\"xingyooo\",\"vipLevel\":1,\"expireTime\":\"2015-02-05 16:15:00\"}";
		channel.basicPublish(exchange, routingKey, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");

		RabbitMQConnFactory.closeChannelAndConnection(channel);
	}

	public enum OrderOriginEnum {

		VIP("VIP", "999"), LISTENBOOK("ListenBook", "888");
		private String originType;// 来源类型
		private String orderPrefix;// 订单前缀

		private OrderOriginEnum(String originType, String orderPerfix) {
			this.originType = originType;
			this.orderPrefix = orderPerfix;
		}

	}

	@Test
	public void testEnum() throws Exception {
		if (OrderOriginEnum.VIP.name().equals("VIP")) {
			System.out.println("===" + OrderOriginEnum.VIP.toString());
		}
		if (OrderOriginEnum.VIP.orderPrefix.equals("999")) {
			System.out.println("+++" + OrderOriginEnum.VIP.originType);
		}
		System.out.println("===" + OrderOriginEnum.VIP.orderPrefix);

		System.out
				.println("--------" + OrderOriginEnum.VIP.orderPrefix + 22222);
		// System.out.println(OrderOriginEnum.getOriginType("999"));
		// System.out.println(OrderOriginEnum.getOrderPrefix("VIP"));
	}

	class A {
		public Date date;
		public String user;
		public String level;
	}

	@Test
	public void testSort() throws Exception {
		ArrayList<A> objList = new ArrayList<A>();
		A a = new A();
		a.level = "0";
		objList.add(a);
		A b = new A();
		b.level = "1";
		objList.add(b);
		A c = new A();
		c.level = "";
		objList.add(c);
		Collections.shuffle(objList);
		System.out.println("list:" + GsonUtils.objectToJson(objList));

		Collections.sort(objList, new Comparator<A>() {
			@Override
			public int compare(A o1, A o2) {
				return o2.level.compareTo(o1.level);
			}
		});

		System.out.println("list:" + GsonUtils.objectToJson(objList));

	}

	@Test
	public void testStringCase() throws Exception {
		Map map = new HashMap<String, Object>();
		map.put("aa", "value");
		map.put("bb", null);
		String str = (String) map.get("aa");
		String str2 = (String) map.get("bb");
		System.out.println("1:" + str);
		System.out.println("2:" + str2);
		BigDecimal amount = new BigDecimal("5.0");
		int price = amount.intValue();
		System.out.println(price);
	}

	@Test
	public void testGsonMap() throws Exception {
		String json = "{\"orderId\":\"99915011921658637\",\"usr\":\"i344450421\",\"productId\":\"100662\",\"productType\":\"19\",\"schemeI\":\"41\",\"channelId\":\"999941\",\"phoneType\":\"GT-I9228\",\"clientVersion\":\"760003\",\"map\":{\"schemeId\":\"41\",\"desc\":\"\",\"p3\":\"760003\",\"source\":\"1\",\"subId\":\"90\",\"phoneType\":\"GT-I9228\",\"channelId\":\"999941\",\"reserve2\":\"\",\"clientVersion\":\"760003\",\"reserve1\":\"501603\",\"p19\":\"\"}}";
		VipOrderNotifyInfo bean = GsonUtils.jsonToBean(json,
				VipOrderNotifyInfo.class);
		String id = bean.channelId;
		System.out.println(id);
		System.out.println("success");

		String json2 = "{\"serviceURL\":www.google.com,\"hoursService\":{\"3\":{\"dayOfWeek\":3,\"closeTime\":\"6:30 PM\",\"openTime\":\"7:30 AM\"},\"2\":{\"dayOfWeek\":2,\"closeTime\":\"6:30 PM\",\"openTime\":\"7:30 AM\"},\"1\":{\"dayOfWeek\":1,\"closeTime\":\"6:30 PM\",\"openTime\":\"7:30 AM\"}},\"dealerAttributes\":[{\"language\":\"English\",\"dealerAttributeName\":\"Spanish Speaking\",\"updateDate\":1174971061000},{\"language\":\"English\",\"updateDate\":1103003316000}]}";
		GsonUtils.jsonToBean(json2, Dealer.class);
		System.out.println("success");

	}

	@Test
	public void testListRm() throws Exception {
		String json2 = "{\"serviceURL\":www.google.com,\"hoursService\":{\"3\":{\"dayOfWeek\":3,\"closeTime\":\"6:30 PM\",\"openTime\":\"7:30 AM\"},\"2\":{\"dayOfWeek\":2,\"closeTime\":\"6:30 PM\",\"openTime\":\"7:30 AM\"},\"1\":{\"dayOfWeek\":1,\"closeTime\":\"6:30 PM\",\"openTime\":\"7:30 AM\"}},\"dealerAttributes\":[{\"language\":\"English\",\"dealerAttributeName\":\"Spanish Speaking\",\"updateDate\":1174971061000},{\"language\":\"English\",\"updateDate\":1103003316000}]}";
		Dealer bean = GsonUtils.jsonToBean(json2, Dealer.class);

		List<DealerAttributes> list = bean.dealerAttributes;
		System.out.println("aa" + list);
		for (DealerAttributes dealerAttributes : list) {
			if (!"Spanish Speaking"
					.equals(dealerAttributes.dealerAttributeName)) {
				System.out.println(dealerAttributes);

				list.remove(dealerAttributes);
				continue;
			}
			dealerAttributes.dealerAttributeName = "new name";
		}
		System.out.println("bb" + list);
	}

	public static int getcountDaysUp(String stratDate, String endDate) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(TimeUtils.formatString(stratDate));
			double t1 = c.getTime().getTime();
			c.setTime(TimeUtils.formatString(endDate));
			double t2 = c.getTime().getTime();
			return (int) Math.ceil(Math.abs(t1 - t2) / (1000 * 3600 * 24));
		} catch (ParseException e) {
			// throw new Exception(e);
			return 0;
		}

	}

	@Test
	public void testCountDays() throws Exception {
		String time1 = "2015-02-05 03:59:48";
		String time2 = "2015-02-05 22:59:48";
		int days = getcountDaysUp(time1, time2);
		System.out.println(days);
	}

	
	@Test
	public void testTime() throws Exception {
//		Date date = new Date(new java.sql.Time(System.currentTimeMillis()).getTime());
//		String timeString = TimeUtils.formatDate(date);
		String timeString = TimeUtils.GetCurrentTime(TimeUtils.SDF_YYYY_MM_DD_00);
		System.out.println(timeString);
		Date date = TimeUtils.formatString(timeString);
		System.out.println(date);
	}
	
	@Test
	public void testGamePrice() throws Exception {
//		float account = 2.09f;
//		String productPrice = "209";
		float account = 8.9f;
		String productPrice = "890";
		int pPrice = Integer.valueOf(productPrice);
		if (account*100<pPrice) {
			System.out.println("account*100="+account*100);
		}
		
	}
	
	@Test
	public void testGamePrice2() throws Exception {
		float account = 2.09f;
		String productPrice = "209";
		int pPrice = Integer.valueOf(productPrice);
		
		if (new BigDecimal(account).subtract(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(pPrice))>0) {
			System.out.println("account*100="+account*100);
		}
		
	}
	
	@Test
	public void testGamePrice3() throws Exception {
		float account = 2.09f;
		String productPrice = "209";
		int pPrice = Integer.valueOf(productPrice);
		int amount = new BigDecimal(account*100).setScale(2,BigDecimal.ROUND_HALF_UP).intValue();
		System.out.println("account="+amount);
		if (account>pPrice) {
			System.err.println("account*100<pPrice");
		}
		
	}
	
	@Test
	public void testPrice() throws Exception {
	    float account = 0.01f;
	    String productPrice = "0.01";
	    boolean flag = new BigDecimal(productPrice).compareTo(new BigDecimal(account))==0;
	    if (new BigDecimal(productPrice).setScale(2,BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(account).setScale(2,BigDecimal.ROUND_HALF_UP))==0) {
	        System.err.println("flag="+flag);
        }
	    System.out.println("flag="+flag);
	    
	}
}

class VipOrderNotifyInfo {
	public String orderId;
	public String usr;
	public String productId;
	public String productType;
	public String schemeI;
	public String channelId;
	public String phoneType;
	public String clientVersion;
	public Map<String, String> map;

	public VipOrderNotifyInfo(String orderId, String usr, String productId,
			String productType, String schemeI, String channelId,
			String phoneType, String clientVersion, Map<String, String> map) {
		this.orderId = orderId;
		this.usr = usr;
		this.productId = productId;
		this.productType = productType;
		this.schemeI = schemeI;
		this.channelId = channelId;
		this.phoneType = phoneType;
		this.clientVersion = clientVersion;
		this.map = map;
	}
}

class Dealer {
	public String serviceURL;
	public Map<String, Hours> hoursService;
	public List<DealerAttributes> dealerAttributes;

	@Override
	public String toString() {
		return String
				.format("[Dealer: serviceURL=%1$s, hoursService=%2$s, dealerAttributes=%3$s]",
						serviceURL, hoursService, dealerAttributes);
	}
}

class Hours {
	private int dayOfWeek;
	private String closeTime;
	private String openTime;

	@Override
	public String toString() {
		return String.format(
				"[Hours: dayOfWeek=%1$d, closeTime=%2$s, openTime=%3$s]",
				dayOfWeek, closeTime, openTime);
	}
}

class DealerAttributes {
	private String language;
	public String dealerAttributeName;
	private long updateDate;

	@Override
	public String toString() {
		return String
				.format("[DealerAttributes: language=%1$s, dealerAttributeName=%2$s, updateDate=%3$d]",
						language, dealerAttributeName, updateDate);
	}
}
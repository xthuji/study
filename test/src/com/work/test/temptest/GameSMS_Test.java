package com.work.test.temptest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.work.test.bean.sms.GameSmsConfBean;
import com.work.test.bean.sms.ProvinceIpRespBean;
import com.work.test.bean.sms.SmsChannelBean;
import com.work.test.bean.sms.SmsChannelConfBean;
import com.work.test.tools.HttpClientTools;

/**
 * @author huji
 * 
 */
public class GameSMS_Test {

	private static final String AMOUNT_10 = "amount_10";
	private static final String AMOUNT_5 = "amount_5";
	private static final String ALL_FEE = "all_fee";
	Gson gson = new Gson();
	Jedis redis;

	Jedis getJedisClient() {
		Jedis jedis = new Jedis("192.168.6.19", 6379);
		return jedis;
	}

	@Before
	public void initClient() {
		this.redis = getJedisClient();
	}

	@Test
	public void contextTest() {
		// String str =
		// "[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]";
		String str = "[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null},{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]";
		String key = "game_sms_ConfBean_001";
		redis.del(key);
		// redis.set(key,str);
		String json = redis.get(key);
		List<SmsChannelBean> obj = gson.fromJson(json,
				new TypeToken<List<SmsChannelBean>>() {
				}.getType());
		System.out.println(obj);
	}

	@Test
	public void mapTest() {
		String key = "game_sms_ConfBean_hashmap";
		String jsonString1 = "[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]";
		String jsonString2 = "[{\"id\":\"4\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8002CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(ALL_FEE, "[\"10\", \"30\", \"5\"]");
		map.put(AMOUNT_5, jsonString1);
		map.put(AMOUNT_10, jsonString2);
		redis.hmset(key, map);
		Map<String, String> map2 = redis.hgetAll(key);
		System.out.println(ALL_FEE + "=" + map2.get(ALL_FEE));
		List<SmsChannelBean> amount5 = gson.fromJson(map2.get(AMOUNT_5),
				new TypeToken<List<SmsChannelBean>>() {
				}.getType());
		List<SmsChannelBean> amount10 = gson.fromJson(map2.get(AMOUNT_10),
				new TypeToken<List<SmsChannelBean>>() {
				}.getType());
		System.out.println(amount5);
		System.out.println(amount10);
	}

	@Test
	public void delTest() {
		String key = "game_sms_ConfBean_hashmap";
		// redis.hdel(key,ALL_FEE);
		// String fees = redis.hget(key, ALL_FEE);
		// System.out.println(fees);
		Set<String> keyNum = redis.hkeys(key);
		System.out.println(keyNum);
		redis.del(key);
		keyNum = redis.hkeys(key);
		System.out.println(keyNum);
	}

	@Test
	public void smsConfTest() {
		String str = "{\"province\":\"省份\",\"mobileType\":\"3\",\"fee\":[\"10\",\"30\",\"5\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}";
		GameSmsConfBean gameSmsconf = gson.fromJson(str, GameSmsConfBean.class);
		String province = gameSmsconf.getProvince();
		String mobileType = gameSmsconf.getMobileType();
		Set feeSet = gameSmsconf.getFee();
		List<SmsChannelConfBean> smsConfContent = gameSmsconf
				.getSmsChannelContent();
		System.out.println(province);
	}

	@Test
	public void setTest() {
		String key = "gameSmsConf_fee_100000_1";
		redis.del(key);
		Set<String> set = new LinkedHashSet<String>();
		set.add("5");
		set.add("10");
		set.add("30");
		for (String fee : set) {
			redis.sadd(key, fee);
			System.out.println("sadd--" + fee);
		}
		Set<String> feeSet = redis.smembers(key);
		System.out.println("smembers--" + gson.toJson(feeSet));
	}

	@Test
	public void listTest() {
		String key = "gameSmsConf_fee_100000_12";
		redis.del(key);
		List<String> list = new ArrayList<String>();
		list.add("5");
		list.add("10");
		list.add("30");
		for (String fee : list) {
			redis.lpush(key, fee);
			System.out.println("lpush--" + fee);
		}
		List feeList = redis.lrange(key, 0, -1);
		System.out.println("lrange--" + gson.toJson(feeList));
	}

	@Test
	public void zsetTest() {
		String key = "gameSmsConf_fee_100000_1";
		redis.del(key);
		Set<String> set = new LinkedHashSet<String>();
		set.add("5");
		set.add("10");
		set.add("30");
		double i = 0;
		for (String fee : set) {
			redis.zadd(key, i, fee);
			System.out.println("zadd--" + fee);
			i++;
		}
		for (String fee : set) {
			redis.zadd(key, i, fee);
			System.out.println("zadd--" + fee);
			i++;
		}
		Set<String> feeSet = redis.zrange(key, 0, -1);
		System.out.println("smembers--" + gson.toJson(feeSet));
	}

	@Test
	public void gameSmsTest() {
		String data = "{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}";
		// String url =
		// "http://localhost:8089/payapi/gameSmsConf/releaseSmsCfg";
		String url = "http://pay.ireader.com:33000/payapi/gameSmsConf/releaseSmsCfg";
		String x = HttpClientTools.sendGet(url, "str=" + data);
		System.out.println(x);

	}

	@Test
	public void gameSmsPostTest() {
		String data = "[{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}]";
//		String url = "http://localhost:8089/payapi/gameSmsConf/releaseSmsCfg";
		 String url =
		 "http://pay.ireader.com:27100/payapi/gameSmsConf/releaseSmsCfg";
		String x = HttpClientTools.sendPost(url, data);
		System.out.println(x);

	}

	// /////////////////////////////////////////////////////////////////////////
	@Test
	public void testStrBuilder1() throws Exception {// 1360
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			StringBuilder sb = new StringBuilder()
					.append("[")
					.append("{\"amount\":\"")
					.append("5")
					.append("\",\"list\":")
					.append("[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append(",")
					.append("{\"amount\":\"")
					.append("10")
					.append("\",\"list\":")
					.append("[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append(",")
					.append("{\"amount\":\"")
					.append("30")
					.append("\",\"list\":")
					.append("[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append("]");
			String string = sb.toString();
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testStrBuilder2() throws Exception {// 2411
		Set<String> set = new LinkedHashSet<String>();
		set.add("5");
		set.add("10");
		set.add("30");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			StringBuilder sb = new StringBuilder()
					.append("[{")
					.append("\"province\":\"")
					.append("100000")
					.append("\",\"mobileType\":\"")
					.append("1")
					.append("\",\"fee\":")
					.append(gson.toJson(set))
					.append(",\"smsChannelContent\":[")
					.append("{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append(",")
					.append("{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append(",")
					.append("{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append("]}]");
			String string = sb.toString();
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	// //////////////////////////////////////////////////////////////////////////

	@Test
	public void gameSmsGsonTest1() {// 12366
		String data = "[{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}]";
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			List<GameSmsConfBean> gameSmsconfList = gson.fromJson(data,
					new TypeToken<List<GameSmsConfBean>>() {
					}.getType());
		}
		System.out.println(System.currentTimeMillis() - start);

	}

	@Test
	public void gameSmsGsonTest2() {// 14607
		Set<String> set = new LinkedHashSet<String>();
		set.add("5");
		set.add("10");
		set.add("30");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			StringBuilder sb = new StringBuilder()
					.append("[{")
					.append("\"province\":\"")
					.append("100000")
					.append("\",\"mobileType\":\"")
					.append("1")
					.append("\",\"fee\":")
					.append(gson.toJson(set))
					.append(",\"smsChannelContent\":[")
					.append("{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append(",")
					.append("{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append(",")
					.append("{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append("]}]");
			String string = sb.toString();
			List<GameSmsConfBean> gameSmsconfList = gson.fromJson(string,
					new TypeToken<List<GameSmsConfBean>>() {
					}.getType());
		}
		System.out.println(System.currentTimeMillis() - start);

	}

	@Test
	public void gameSmsGsonTest3() {// 12171
		String data = "[{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}]";
		List<GameSmsConfBean> gameSmsconfList = gson.fromJson(data,
				new TypeToken<List<GameSmsConfBean>>() {
				}.getType());
		GameSmsConfBean gameSmsConfBean = gameSmsconfList.get(0);
		LinkedHashSet<String> set = gameSmsConfBean.getFee();
		String province = gameSmsConfBean.getProvince();
		String mobileType = gameSmsConfBean.getMobileType();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			StringBuilder sb = new StringBuilder()
					.append("[")
					.append("{\"amount\":\"")
					.append("5")
					.append("\",\"list\":")
					.append("[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append(",")
					.append("{\"amount\":\"")
					.append("10")
					.append("\",\"list\":")
					.append("[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append(",")
					.append("{\"amount\":\"")
					.append("30")
					.append("\",\"list\":")
					.append("[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}")
					.append("]");
			String string = sb.toString();
			List<SmsChannelConfBean> beanlist = gson.fromJson(string,
					new TypeToken<List<SmsChannelConfBean>>() {
					}.getType());
			List<GameSmsConfBean> list = new ArrayList<GameSmsConfBean>();
			GameSmsConfBean gameSmsConf = new GameSmsConfBean();
			gameSmsConf.setFee(set);
			gameSmsConf.setMobileType(mobileType);
			gameSmsConf.setProvince(province);
			gameSmsConf.setSmsChannelContent(beanlist);
		}
		System.out.println(System.currentTimeMillis() - start);

	}

	@Test
	public void gameSmsGsonTest4() {// 10294
		String data = "[{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}]";
		String jsonString1 = "{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}";
		String jsonString2 = "{\"id\":\"4\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8002CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}";
		String jsonString3 = "{\"id\":\"5\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8002CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}";

		List<GameSmsConfBean> gameSmsconfList = gson.fromJson(data,
				new TypeToken<List<GameSmsConfBean>>() {
				}.getType());
		GameSmsConfBean gameSmsConfBean = gameSmsconfList.get(0);
		LinkedHashSet<String> set = gameSmsConfBean.getFee();
		String province = gameSmsConfBean.getProvince();
		String mobileType = gameSmsConfBean.getMobileType();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			List<SmsChannelBean> confList = new ArrayList<SmsChannelBean>();
			SmsChannelBean smsChannel = gson.fromJson(jsonString1,
					SmsChannelBean.class);
			SmsChannelBean smsChannel2 = gson.fromJson(jsonString2,
					SmsChannelBean.class);
			SmsChannelBean smsChannel3 = gson.fromJson(jsonString3,
					SmsChannelBean.class);
			confList.add(smsChannel);

			List<SmsChannelConfBean> beanlist = new ArrayList<SmsChannelConfBean>();
			SmsChannelConfBean smsChannelContent = new SmsChannelConfBean();
			smsChannelContent.setAmount("5");
			smsChannelContent.setList(confList);
			beanlist.add(smsChannelContent);

			List<GameSmsConfBean> list = new ArrayList<GameSmsConfBean>();
			GameSmsConfBean gameSmsConf = new GameSmsConfBean();
			gameSmsConf.setFee(set);
			gameSmsConf.setMobileType(mobileType);
			gameSmsConf.setProvince(province);
			gameSmsConf.setSmsChannelContent(beanlist);
		}
		System.out.println(System.currentTimeMillis() - start);

	}

	/** 去除拼串影响，gosn串越长，转换成对象需要时间越多 */

	// ///////////////////////////////////////////////////////////////////////////
	@Test
	public void testForeach1() throws Exception {// 29
		String data = "[{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}]";
		List<GameSmsConfBean> gameSmsconfList = gson.fromJson(data,
				new TypeToken<List<GameSmsConfBean>>() {
				}.getType());
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			for (GameSmsConfBean gameSmsconf : gameSmsconfList) {
				String province = gameSmsconf.getProvince();
				String mobileType = gameSmsconf.getMobileType();
				Set<String> feeSet = gameSmsconf.getFee();
				List<SmsChannelConfBean> smsConfContent = gameSmsconf
						.getSmsChannelContent();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testForeach2() throws Exception {// 10
		String data = "[{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}]";
		List<GameSmsConfBean> gameSmsconfList = gson.fromJson(data,
				new TypeToken<List<GameSmsConfBean>>() {
				}.getType());
		long start = System.currentTimeMillis();
		for (int j = 0; j < 100000; j++) {
			for (int i = 0, len = gameSmsconfList.size(); i < len; i++) {
				GameSmsConfBean gameSmsconf = gameSmsconfList.get(i);
				String province = gameSmsconf.getProvince();
				String mobileType = gameSmsconf.getMobileType();
				Set<String> feeSet = gameSmsconf.getFee();
				List<SmsChannelConfBean> smsConfContent = gameSmsconf
						.getSmsChannelContent();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testForeach3() throws Exception {// 38
		String data = "[{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}]";
		List<GameSmsConfBean> gameSmsconfList = gson.fromJson(data,
				new TypeToken<List<GameSmsConfBean>>() {
				}.getType());
		LinkedHashSet<String> feeSet = gameSmsconfList.get(0).getFee();
		long start = System.currentTimeMillis();
		for (int j = 0; j < 100000; j++) {
			List<SmsChannelConfBean> smsChannelContent = new ArrayList<SmsChannelConfBean>();
			for (String fee : feeSet) {
				SmsChannelConfBean confBean = new SmsChannelConfBean();
				confBean.setAmount(fee);
				smsChannelContent.add(confBean);
			}
			smsChannelContent = null;
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testForeach4() throws Exception {// 38
		String data = "[{\"province\":\"100000\",\"mobileType\":\"1\",\"fee\":[\"5\",\"10\",\"30\"],\"smsChannelContent\":[{\"amount\":\"5\",\"list\":[{\"id\":\"7\",\"spNumber\":\"11802115050\",\"spContent\":\"131000B2000000B000A6001CZ\",\"fee\":\"5\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"10\",\"list\":[{\"id\":\"8\",\"spNumber\":\"11802115100\",\"spContent\":\"131000B2000000B000A8001CZ\",\"fee\":\"10\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]},{\"amount\":\"30\",\"list\":[{\"id\":\"9\",\"spNumber\":\"11802115300\",\"spContent\":\"131000B2000000B000J6001CZ\",\"fee\":\"30\",\"dayLimit\":null,\"monthLimit\":null,\"showType\":null,\"style\":\"2\",\"unit\":null,\"num\":null,\"message\":null,\"message2\":null,\"serveOrderId\":null}]}]}]";
		List<GameSmsConfBean> gameSmsconfList = gson.fromJson(data,
				new TypeToken<List<GameSmsConfBean>>() {
				}.getType());
		LinkedHashSet<String> feeSet = gameSmsconfList.get(0).getFee();
		long start = System.currentTimeMillis();
		for (int j = 0; j < 100000; j++) {
			List<SmsChannelConfBean> smsChannelContent = new ArrayList<SmsChannelConfBean>();
			Iterator<String> iterator = feeSet.iterator();
			while (iterator.hasNext()) {
				String fee = (String) iterator.next();
				SmsChannelConfBean confBean = new SmsChannelConfBean();
				confBean.setAmount(fee);
				smsChannelContent.add(confBean);
			}
			smsChannelContent = null;
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testProvinceJson1() throws Exception {//2344
		String data = "{\"msg\": \"\", \"body\": {\"province\": \"\u5317\u4eac\", \"ip\": \"116.243.169.52\", \"provCode\": \"100000\"}, \"code\": 0}";
		String province = null;
		long start = System.currentTimeMillis();
		for (int j = 0; j < 1000000; j++) {
			ProvinceIpRespBean respBean = gson.fromJson(data,
					ProvinceIpRespBean.class);
			if ("0".equals(respBean.getCode())) {
				province = respBean.getBody().getProvCode();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testProvinceJson2() throws Exception {//2525
		String data = "{\"msg\": \"\", \"body\": {\"province\": \"\u5317\u4eac\", \"ip\": \"116.243.169.52\", \"provCode\": \"100000\"}, \"code\": 0}";
		String province = null;
		long start = System.currentTimeMillis();
		for (int j = 0; j < 1000000; j++) {
			JsonObject jsonObj = new JsonParser().parse(data).getAsJsonObject();
			if ("0".equals(jsonObj.get("code").getAsString())) {
				province = jsonObj.get("body").getAsJsonObject().get("province").getAsString();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}

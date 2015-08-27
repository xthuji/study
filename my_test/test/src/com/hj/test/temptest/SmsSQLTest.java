package com.hj.test.temptest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hj.test.tools.HttpUtils;

public class SmsSQLTest {
	private static final Logger logger = LoggerFactory
			.getLogger(SmsSQLTest.class);

	@Test
	public void testName() throws Exception {
		String province = "";
		String phone = "131231342123";
		String url = "http://59.151.100.36:8070/cps/getMobileSectionInfo?mobile="
				+ phone;
		String resp = HttpUtils.httpGet(url, null, "utf-8", false, 3000);
		logger.info(resp);
		JsonObject jsonObj = new JsonParser().parse(resp).getAsJsonObject();
		JsonObject info = null;
		try {
			info = jsonObj.get("info").getAsJsonObject();
		} catch (Exception e) {
			logger.info("Exception getMobileSection is null,phone={}.", phone);
		}
		if (info == null) {
			logger.info("getMobileSection is null,phone={}.", phone);
		} else {
			province = info.get("province").getAsString();
		}
		System.out.println(province);
	}

	@Test
	public void testname2() throws Exception {
		String province = "";
		String phone = "13123134212";
		String url = "http://59.151.100.36:8070/cps/getMobileSectionInfo?mobile="
				+ phone;
		String resp = HttpUtils.httpGet(url, null, "utf-8", false, 3000);
		logger.info(resp);
		Map<String, Object> map = jsonToMap(resp);
		Map<String, Object> map2 = (Map<String, Object>) map.get("info");
		province = (String) map2.get("province");
		System.out.println(province);

	}

	public static Map<String, Object> jsonToMap(String json) {
        JsonObject object = (JsonObject) new com.google.gson.JsonParser().parse(json);
        Set<Map.Entry<String, JsonElement>> set = object.entrySet();
        Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
        HashMap<String, Object> map = new HashMap<String, Object>();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> entry = iterator.next();
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (!value.isJsonPrimitive()) {
                map.put(key, jsonToMap(value.toString()));
            } else {
                map.put(key, value.getAsString());
            }
        }
        return map;
	}
	
	@Test
	public void testname3() throws Exception {
		String str = "";
		boolean flag = StringUtils.isEmpty(str);
		System.out.println(flag);
	}
}

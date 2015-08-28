package com.hj.test.tools;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class UrlMapConvert {

	/**
	 * 将url参数转成map
	 * @param str
	 * @return
	 */
	public static Map<String, String> url2Map(String str) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isBlank(str)) {
			return map;
		}
		String[] params = str.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转成url参数格式
	 * @param map
	 * @return
	 */
	public static String map2Url(Map<String, String> map) {
		if (null == map || map.isEmpty()) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(entry.getKey()).append("=").append(entry.getValue());
		}

		return sb.toString();
	}
	
}

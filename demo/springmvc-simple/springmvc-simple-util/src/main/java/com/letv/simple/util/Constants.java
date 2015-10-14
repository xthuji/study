package com.letv.simple.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaohengchong
 * @email  zhaohengchong@letv.com
 * @version 2014-6-20 上午11:00:20 
 */
public class Constants {
	
	/**
	 * 获取性别属性名称
	 * @return
	 */
	public static Map<Integer, String> getSexMap() {
		Map<Integer, String> sexMap = new HashMap<Integer, String>();
		sexMap.put(0, "男");
		sexMap.put(1, "女");
		return sexMap;
	}
}

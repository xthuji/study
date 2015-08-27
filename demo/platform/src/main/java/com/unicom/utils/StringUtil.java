package com.unicom.utils;

/**
 * 字符串工具类
 * @author chenxinquan
 *
 */
public class StringUtil {
	
	/**
	 * @param str 判断字符串是否为空
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		if(str == null || str.length() == 0) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(isEmpty("2"));
	}
}

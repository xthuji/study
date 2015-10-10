package com.hj.test.tools;

public class ClassUtils {

	/**
	 * 获取此方法调用者的方法名
	 * @return
	 */
	public static String currentMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	/**
	 * 获取此方法调用者的上一级方法的名称
	 * @return
	 */
	public static String parentMethodName() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}
    
}

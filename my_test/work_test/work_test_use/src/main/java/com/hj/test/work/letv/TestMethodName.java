package com.hj.test.work.letv;

import com.hj.test.tools.ClassUtils;

public class TestMethodName {

	public static void main(String[] args) {
		String methodName = ClassUtils.currentMethodName();
		System.out.println(methodName);
		String method = getMethod();
		System.out.println(method);
	}

	private static String getMethod() {
		return ClassUtils.parentMethodName();
	}
	
}

package com.work.test.usetest;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.work.test.bean.beanutils.A;
import com.work.test.bean.beanutils.B;

public class CopyBeanProperty {
	public static Gson gson = new Gson();

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String json = "{\"id\":\"1\",\"name\":\"nameA\",\"age\":\"11\"}";
		A a = gson.fromJson(json, A.class);
		System.out.println(gson.toJson(a));
		// 必须要将类定义为 public class才能使用BeanUtils
		System.out.println(BeanUtils.getProperty(a, "id"));
		System.out.println(BeanUtils.getProperty(a, "name"));
		System.out.println(BeanUtils.getProperty(a, "age"));
		A a1 = new A();
		B b = new B();
		b.setCode(null);
		BeanUtils.copyProperties(a1, a);
		BeanUtils.copyProperties(b, a);
		System.out.println(gson.toJson(a1));
		System.out.println(gson.toJson(b));
	}
}


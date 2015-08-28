package com.hj.test.study.singleton;
/**
 * 饿汉式单例类.在类初始化时，已经自行实例化   
 * @author huji
 *
 */
public class Singleton1 {
	// 私有的默认构造子
	private Singleton1() {
	}

	// 已经自行实例化
	private static final Singleton1 single = new Singleton1();

	// 静态工厂方法
	public static Singleton1 getInstance() {
		return single;
	}
}
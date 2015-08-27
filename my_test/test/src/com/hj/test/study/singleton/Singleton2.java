package com.hj.test.study.singleton;

/**
 * 懒汉式单例类.在第一次调用的时候实例化   
 * @author huji
 *
 */
public class Singleton2 {
	// 私有的默认构造子
	private Singleton2() {
	}

	// 注意，这里没有final
	private static volatile Singleton2 single = null;

	// 静态工厂方法
	public static Singleton2 getInstance() {
		if (single == null) {
			synchronized (single) {
				if (single == null) {
					single = new Singleton2();
				}
			}
		}
		return single;
	}
}
package com.hj.test.study.singleton;

/**
 * 单例--饿汉式
 * 
 * @author huji
 * 
 */
public class Singleton {

	private static Singleton instence = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstence() {
		return instence;
	}
}

/**
 * 单例--懒汉式
 * 
 * @author huji
 * 
 */
class Singleton_2 {

	private static Singleton_2 instence;

	private Singleton_2() {
	}

	public static Singleton_2 getInstence() {
		if (null == instence) {
			instence = new Singleton_2();
		}
		return instence;
	}
}

/**
 * 单例--同步
 * 
 * @author huji
 * 
 */
class Singleton_3 {

	private static Singleton_3 instence;

	private Singleton_3() {
	}

	public static synchronized Singleton_3 getInstence() {
		if (null == instence) {
			instence = new Singleton_3();
		}
		return instence;
	}
}

/**
 * 单例--静态内部类
 * 
 * @author huji
 * 
 */
class Singleton_4 {
	private Singleton_4() {
	}

	private static class SingletonObj {
		public static final Singleton_4 INSTENCE = new Singleton_4();
	}

	public static final Singleton_4 getInstence() {
		return SingletonObj.INSTENCE;
	}
}

/**
 * 单例--枚举
 * 
 * @author huji
 * 
 */
enum Singleton_5 {
	INSTANCE;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[" + name + "]";
	}
}

/**
 * 单例--双重校验锁
 * 
 * @author huji
 * 
 */
class Singleton_6 {
	// 是否是final的不重要，因为最多只可能实例化一次。
	private static volatile Singleton_6 instence;

	private Singleton_6() {
	}

	public static Singleton_6 getInstence() {
		if (null == instence) {
			// 双重检查加锁，只有在第一次实例化时，才启用同步机制，提高了性能。
			synchronized (Singleton_6.class) {
				if (null == instence) {
					instence = new Singleton_6();
				}
			}
		}
		return instence;
	}
}

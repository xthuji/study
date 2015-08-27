package com.hj.test.bean.typetoken;

public abstract class Foo2<T> {

	public Class<T> type;

	public Foo2() {
		this.type = (Class<T>) getClass();
	}

	public static void main(String[] args) {

		Foo2<String> foo = new Foo2<String>() {
		};
		Class mySuperClass = foo.getClass();

		System.out.println(mySuperClass);
	}
}
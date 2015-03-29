package com.work.test.bean.typetoken;

import java.lang.reflect.Array;

public class Foo1<T> {
	public Class<T> kind;

	public Foo1(Class<T> clazz) {
		this.kind = clazz;
	}

	public T[] getInstance() {
		return (T[]) Array.newInstance(kind, 5);
	}
}

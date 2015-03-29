package com.work.test.study.proxy;

/**
 * 测试cglib代理
 * 
 * @author huji
 * 
 */
public class TestCglib {

	public static void main(String[] args) {
		BookFacadeCglib cglib = new BookFacadeCglib();
		BookFacadeImpl1 bookCglib = (BookFacadeImpl1) cglib
				.getInstance(new BookFacadeImpl1());
		bookCglib.addBook();
	}
}
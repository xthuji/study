package com.work.test.study.proxy;

/**
 * 接口实现
 * 
 * @author huji
 * 
 */
public class BookFacadeImpl implements BookFacade {

	@Override
	public void addBook() {
		System.out.println("增加图书方法。。。");
	}

}
package com.work.test.study.proxy;

/**
 * 测试jdk动态代理
 * 
 * @author huji
 * 
 */
public class TestProxy {

	public static void main(String[] args) {
		//换用另一种方式来做，容易理解一点
//		BookFacadeProxy proxy = new BookFacadeProxy();
//		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
//		bookProxy.addBook();
		
		// 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
		BookFacadeProxy handler = new BookFacadeProxy(new BookFacadeImpl());
		// 通过Proxy的newProxyInstance方法来创建我们的代理对象
		BookFacade proxy = (BookFacade) handler.createProxyObject();
		// 使用代理对象调用方法
		// 这里无论访问哪个方法，都是会把请求转发到handler.invoke  
		proxy.addBook();
		
	}

}
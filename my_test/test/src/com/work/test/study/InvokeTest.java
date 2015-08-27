package com.work.test.study;

/**
 * 执行顺序：
 * 父类 静态代码块
 * 子类 静态代码块
 * 父类 代码块
 * 父类 构造方法
 * 子类 代码块
 * 子类 构造方法
 */
public class InvokeTest extends InvokeTestParent{

	public static String str = "CHILD";
	static{
		System.out.println("child static code block invoke");
	}
	
	{
		System.out.println("child code block invoke");
	}
	
	public InvokeTest () {
		System.out.println("child constructor invoke");
	}
	
	public void doSomething() {
		System.out.println("child method invoke");
	}
	
	public static void main(String[] args) {
		new InvokeTest();
		/* 执行结果
		parent static code block invoke
		child static code block invoke
		parent code block invoke
		parent constructor invoke
		child code block invoke
		child constructor invoke
		*/

	}

}

class InvokeTestParent{
	public static String str = "PARENT";
	static{
		System.out.println("parent static code block invoke");
	}
	
	{
		System.out.println("parent code block invoke");
	}
	
	public InvokeTestParent () {
		System.out.println("parent constructor invoke");
	}
	
	public void doSomething() {
		System.out.println("parent method invoke");
	}
}
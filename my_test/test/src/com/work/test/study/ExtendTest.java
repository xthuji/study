package com.work.test.study;

public class ExtendTest {
	void eat(){
		System.out.println("eat parent!");
	}
	ExtendTest(){
		eat();
		System.out.println("parent constructor");
	}
	public static void main(String[] args) {
		//虚拟机调用方法时，默认会传一个当前的指针this(可以这么理解)
		//执行时，先调用子类自己的，如果没有再调用父类的，但是引用依旧会传递，所以构造方法也会传子类的引用，
		//new时，调用的也是子类的eat方法
		
		ExtendTest bean = new TestExtends();
		bean.eat();
		/*输出：
		eat child!
		parent constructor
		eat child!
		*/

	}

}
class TestExtends extends ExtendTest{
	void eat(){
		System.out.println("eat child!");
	}
}
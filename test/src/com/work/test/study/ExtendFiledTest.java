package com.work.test.study;

/**
 * 问题： 
 * 当子类继承父类，而父类调用了子类方法且此子类方法改变了子类的实例变量， 
 * 那么创建子类对象后，子类的实例变量的值是否会变成修改后的值? 
 * 结果:
 * 子类的实例变量没有改变
 *  
 * @author huji
 *
 */
public class ExtendFiledTest {

	public static void main(String[] args) {
		Son son = new Son();
		System.out.println("---" + son.str);// 没有变，还是 = set when init
		System.out.println("---" + son.staticStr);
/*		输出结果： 
		set in pre()
		set in pre() staticStr
		---set in pre()
		---set in pre() staticStr*/

	}
}

class Father {

	public Father() {
		pre();
	}

	void pre() {

	}

}

class Son extends Father{
	public String str;
	public static String staticStr = "set when init staticStr";
	
	@Override
	void pre() {
		str = "set in pre()";
		staticStr = "set in pre() staticStr";
		System.out.println(str);
		System.out.println(staticStr);
	}

}
package com.hj.test.study;

/**
 * 用父类和子类所创建的引用指向子类所创建的对象， 
 * 父类引用所调用子类对象中的属性值或方法的结果是什么呢？
 * 
 * @author huji
 * 
 */
public class FieldDemo {
	public static void main(String[] args) {
		Student t = new Student("Jack");
		Person p = t;// 父类创建的引用指向子类所创建的对象
		System.out.println(t.name + "," + p.name);
		System.out.println(t.getName() + "," + p.getName());
/*		Jack,Rose
		Jack,Jack*/
	}

}

class Person {
	String name;
	int age;

	public String getName() {
		return this.name;
	}
}

class Student extends Person {
	String name; // 属性和父类属性名相同，但在做开发时一般不会和父类属性名相同！！

	public Student(String name) {
		this.name = name;
		super.name = "Rose"; // 为父类中的属性赋值
	}

	public String getName() {
		return this.name;
	}
}
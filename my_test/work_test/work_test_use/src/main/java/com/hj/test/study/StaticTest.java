package com.hj.test.study;

/**
 * 在程序运行时的区别：实例变量属于某个对象的属性，必须创建了实例对象，<br>
 * 其中的实例变量才会被分配空间，才能使用这个实例变量。静态变量不属于某<br>
 * 个实例对象，而是属于类，所以也称为类变量，只要程序加载了类的字节码，<br>
 * 不用创建任何实例对象，静态变量就会被分配空间，静态变量就可以被使用了。<br>
 * 总之，实例变量必须创建对象后才可以通过这个对象来使用，静态变量则可以<br>
 * 直接使用类名来引用。例如，对于下面的程序，无论创建多少个实例对象，<br>
 * 永远都只分配了一个<code>staticInt</code>变量，并且每创建一个实例对象，<br>
 * 这个<code>staticInt</code>就会加1；但是，每创建一个实例对象，就会分配一个<code>random</code>，<br>
 * 即可能分配多个<code>random</code>，并且每个<code>random</code>的值都只自加了1次。<br>
 * 
 * @author huji
 * 
 */
public class StaticTest {

	private static int staticInt = 2;
	private int random = 2;

	public StaticTest() {
		staticInt++;
		random++;
		System.out.println("staticInt = " + staticInt + "  random = " + random);
	}

	public static void main(String[] args) {
		StaticTest test = new StaticTest();
		StaticTest test2 = new StaticTest();
		System.out.println("----staticInt = " + test.staticInt + "  random = "
				+ test.random);
		System.out.println("----staticInt = " + test2.staticInt + "  random = "
				+ test2.random);
/*		staticInt = 3  random = 3
		staticInt = 4  random = 3
		----staticInt = 4  random = 3
		----staticInt = 4  random = 3*/

	}
}
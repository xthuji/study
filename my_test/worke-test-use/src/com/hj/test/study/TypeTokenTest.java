package com.hj.test.study;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.google.gson.Gson;
import com.hj.test.bean.typetoken.Foo1;
import com.hj.test.bean.typetoken.Foo2;

public class TypeTokenTest {

	private Gson gson = new Gson();
	private final static Log log = LogFactory.getLog(TypeTokenTest.class);


	@Test
	public void testC() throws Exception {
		// 这种方法虽然能解决问题，但是每次都要传入一个Class类参数，显得比较麻烦。
		Foo1<String> foo = new Foo1(String.class);
		log.info(foo.getInstance().getClass().getName());
	}
	
	

	/**
	 * 声明一个抽象的父类Foo，匿名子类将泛型类作为Foo的泛型参数传入构造一个实例，再调用getClass方法获得这个子类的Class类型。
这里虽然通过另一种方式获得了匿名子类的Class类型，但是并没有直接将泛型参数T的Class类型传进来，那又是如何获得泛型参数的类型的呢， 这要依赖Java的Class字节码中存储的泛型参数信息。Java的泛型机制虽然在运行期间泛型类和非泛型类都相同，但是在编译java源代码成 class文件中还是保存了泛型相关的信息，这些信息被保存在class字节码常量池中，使用了泛型的代码处会生成一个signature签名字段，通过 签名signature字段指明这个常量池的地址。
关于class文件中存储泛型参数类型的具体的详细的知识可以参考这里：http://stackoverflow.com/questions/937933/where-are-generic-types-stored-in-java-class-files
JDK里面提供了方法去读取这些泛型信息的方法，再借助反射，就可以获得泛型参数的具体类型。
Class类的getGenericSuperClass()方法
概括来说就是对于带有泛型的class，返回一个ParameterizedType对象，对于Object、接口和原始类型返回null，对于数 组class则是返回Object.class。ParameterizedType是表示带有泛型参数的类型的Java类型，JDK1.5引入了泛型之 后，Java中所有的Class都实现了Type接口，ParameterizedType则是继承了Type接口，所有包含泛型的Class类都会实现 这个接口。
实际运用中还要考虑比较多的情况，比如获得泛型参数的个数避免数组越界等，具体可以参看Gson中的TypeToken类及ParameterizedTypeImpl类的代码。
	 *
	 */
	@Test
	public void testE() throws Exception {
		Foo2<String> foo = new Foo2<String>() {
		};
		// getGenericSuperclass对于带有泛型的class，返回一个ParameterizedType对象
		// ParameterizedType是表示带有泛型参数的类型的Java类型
		Type mySuperClass = foo.getClass().getGenericSuperclass();
		Type type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
		log.info(type);

	}
}

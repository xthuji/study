package com.hj.test.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 
 * JDK动态代理代理类 
 * 代理实体handler(运行时，实际执行操作的handler类)
 * 
 * @author student 
 *  
 */  
public class BookFacadeProxy implements InvocationHandler {  
	//　这个就是我们要代理的真实对象
    private Object target;  
    
/* 不用这种，使用另一种方式，将绑定和生成代理对象分开   *//** 
     * 绑定委托对象并返回一个代理类 
     * @param target 
     * @return 
     *//*  
    public Object bind(Object target) {  
        this.target = target;  
        //取得代理对象  
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)  
    }  */
  
    /**
     * 构造方法，给我们要代理的真实对象赋初值
     * 
     * @param target 需要代理的对象
     */
    public BookFacadeProxy(Object target) {
		this.target = target;
	}
    
    /**
     * 创建实际代理对象
     * 将目标对象关联到代理实体handler上
     * @return 代理对象
     */
    public Object createProxyObject() {
    	/*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到  this代理实体handler 这个对象上
         * 
         * 说明：
         * 可能会以为返回的这个代理对象会是BookFacadeProxy类型的对象，或者是InvocationHandler的对象，结果却不是，
         * 首先我们解释一下为什么我们这里可以将其转化为BookFacade类型的对象？
         * 原因就是在newProxyInstance这个方法的第二个参数上，我们给这个代理对象提供了一组什么接口，那么我这个代理对象就会实现了这组接口，
         * 这个时候我们当然可以将这个代理对象强制类型转化为这组接口中的任意一个，因为这里的接口是BookFacade类型，所以就可以将其转化为BookFacade类型了。
		 * 
		 * 同时我们一定要记住，通过 Proxy.newProxyInstance 创建的代理对象是在jvm运行时动态生成的一个对象，它并不是我们的InvocationHandler类型，也不是我们定义的那组接口的类型，
         * 而是在运行是动态生成的一个对象，并且命名方式都是这样的形式，以$开头，proxy为中，最后一个数字表示对象的标号。
         */
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
    /** 
     * 调用方法 
     * 这个invoke方法是通过JVM虚拟机生成的代理     来开启的
     */  
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {  
        Object result=null;  
        System.out.println("事物开始");  
        //执行方法  
        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        result=method.invoke(target, args);  
        System.out.println("事物结束");  
        return result;  
    }  
  
}  
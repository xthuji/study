package com.hj.test.memcached;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.Counter;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.auth.AuthInfo;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.command.TextCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.impl.KetamaMemcachedSessionLocator;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MemcacheClient {
	
	public static MemcachedClient client;
	
	@Before
	public void init() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("192.168.6.7:11000"));
		// 192.168.6.165:10000
		// AddrUtil.getAddresses("server1:11211 server2:11211")
		// 宕机报警
//		builder.setFailureMode(true);
		builder.setCommandFactory(new TextCommandFactory());
//		builder.setSessionLocator(new KetamaMemcachedSessionLocator());
//		// 使用二进制文件
//		builder.setCommandFactory(new BinaryCommandFactory());
//		/**
//		 * 设置连接池大小，即客户端个数 In a high concurrent enviroment,you may want to pool
//		 * memcached clients. But a xmemcached client has to start a reactor
//		 * thread and some thread pools, if you create too many clients,the cost
//		 * is very large. Xmemcached supports connection pool instreadof client
//		 * pool. you can create more connections to one or more memcached
//		 * servers, and these connections share the same reactor and thread
//		 * pools, it will reduce the cost of system. 默认的pool
//		 * size是1。设置这一数值不一定能提高性能，请依据你的项目的测试结果为准。初步的测试表明只有在大并发下才有提升。
//		 * 设置连接池的一个不良后果就是，同一个memcached的连接之间的数据更新并非同步的
//		 * 因此你的应用需要自己保证数据更新的原子性（采用CAS或者数据之间毫无关联）。
//		 */
//		builder.setConnectionPoolSize(10);
		client = builder.build();
	}

	@After
	public void after() throws Exception {
		client.shutdown();
	}
	
	@Test
	public void baseFunction() throws TimeoutException, InterruptedException, MemcachedException {
		// 存储操作
		//set 命令用于向缓存添加新的键值对。如果键已经存在，则之前的值将被替换。如果使用 set 命令正确设定了键值对，服务器将使用单词 STORED 进行响应。
        if (!client.set("hello", 0, "dennis")) {
            System.err.println("set error");
        }
        //仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。如果缓存中已经存在键，则之前的值将仍然保持相同，并且您将获得响应 NOT_STORED。
        client.add("hello", 0, "dennis");
        //仅当键已经存在时，replace 命令才会替换缓存中的键。如果缓存中不存在键，那么您将从 memcached 服务器接受到一条 NOT_STORED 响应。 
        client.replace("hello", 0, "dennis");

        // get操作
        //get 命令用于检索与之前添加的键值对相关的值。您将使用 get 执行大多数检索操作。
        String name = (String) client.get("hello");
        System.out.println(name);

        // 批量获取
        List<String> keys = new ArrayList<String>();
        keys.add("hello");
        keys.add("test");
        Map<String, Object> map = client.get(keys);
        System.out.println("map size:"+map.size());

        // delete操作
        //delete 命令用于删除 memcached 中的任何现有值。您将使用一个键调用delete，如果该键存在于缓存中，则删除该值。如果不存在，则返回一条NOT_FOUND 消息。
        if (!client.delete("hello", 1000)) {
            System.err.println("delete error");
        }

        // incr,decr
        client.incr("a", 4);
        client.decr("a", 4);

        // version
        Map<InetSocketAddress, String> version = client.getVersions();
        System.out.println(version);
        // 增删改查自定义对象
        Name dennis = new Name("dennis", "zhuang", 26, -1);
        System.out.println("dennis:" + dennis);
        client.set("dennis", 0, dennis);

        Name cachedPerson = (Name) client.get("dennis");
        System.out.println("cachedPerson:" + cachedPerson);
        cachedPerson.money = -10000;

        client.replace("dennis", 0, cachedPerson);
        Name cachedPerson2 = (Name) client.get("dennis");
        System.out.println("cachedPerson2:" + cachedPerson2);

        // delete
        client.delete("dennis");
        System.out.println("after delete:" + client.get("dennis"));
	}
	
	@Test
	public void test1() throws IOException {
		try {
			/**
			 * 第一个是存储的key名称，
			 * 第二个是expire时间（单位秒），超过这个时间,memcached将这个数据替换出去，0表示永久存储（默认是一个月)
			 * 第三个参数就是实际存储的数据
			 */
//			192.168.6.7:11000 
//			client.set("hello", 0, "Hello,xmemcached");
			String value = client.get("hello");
			System.out.println("hello=" + value);
			boolean result = client.delete("hello");
			value = client.get("hello");
			System.out.println("hello=" + value +",result="+result);
//			192.168.6.165:10000
//			client.set("hello2", 0, "Hello,xmemcached");
			String value2 = client.get("hello2");
			System.out.println("hello2=" + value2);
			boolean result2 = client.delete("hello2");
			value2 = client.get("hello2");
            System.out.println("hello2=" + value2 +",result="+result2);

			// value=client.get(“hello”,3000);

			/**
			 * Memcached是通过cas协议实现原子更新，所谓原子更新就是compare and set，
			 * 原理类似乐观锁，每次请求存储某个数据同时要附带一个cas值， memcached比对这个cas值与当前存储数据的cas值是否相等，
			 * 如果相等就让新的数据覆盖老的数据，如果不相等就认为更新失败， 这在并发环境下特别有用
			 */
//			GetsResponse<Integer> result = client.gets("a");
//			long cas = result.getCas();
//			// 尝试将a的值更新为2
//			if (!client.cas("a", 0, 2, cas)) {
//				System.err.println("cas error");
//			}
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore
		}
		try {
			// close memcached client
			client.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}

	}

	@Test
	public void test2() throws TimeoutException, InterruptedException,
			MemcachedException, IOException {
		client.flushAll();
		if (!client.set("hello", 0, "world")) {
			System.err.println("set error");
		}
		if (client.add("hello", 0, "dennis")) {
			System.err.println("Add error,key is existed");
		}
		if (!client.replace("hello", 0, "dennis")) {
			System.err.println("replace error");
		}
		client.append("hello", " good");
		client.prepend("hello", "hello ");
		String name = client.get("hello", new StringTranscoder());
		System.out.println(name);

		/**
		 * 而删除数据则是通过deleteWithNoReply方法，这个方法删除数据并且告诉memcached
		 * 不用返回应答，因此这个方法不会等待应答直接返回，特别适合于批量处理
		 */
		client.deleteWithNoReply("hello");
	}

	@Test
	public void incrDecr() throws IOException, TimeoutException,
			InterruptedException, MemcachedException {
		/**
		 * 第一个参数指定递增的key名称， 第二个参数指定递增的幅度大小， 第三个参数指定当key不存在的情况下的初始值。
		 * 两个参数的重载方法省略了第三个参数，默认指定为0。
		 */
		assert (1 == client.incr("a", 5, 1));
		assert (6 == client.incr("a", 5));
		assert (10 == client.incr("a", 4));
		assert (9 == client.decr("a", 1));
		assert (7 == client.decr("a", 2));
	}
	@Test
	public void testCAS() throws IOException, TimeoutException,
			InterruptedException, MemcachedException {
		client.set("a", 10, 1);
		GetsResponse<Integer> result = client.gets("a");
		long cas = result.getCas();
		// 尝试将a的值更新为2
		if (!client.cas("a", 5, 1, cas)) {
			System.err.println("cas 1 error");
		}
//		client.set("a", 10, 1);
		// 尝试将a的值更新为2
		if (!client.cas("a", 5, 1, result.getCas())) {
			System.err.println("cas 2 error");
		}

	}
	
	@Test
	public void testAdd() throws Exception {
		client.add("add", 10, 1);
	    if (client.add("add", 10, 1)) {  
	        Object value = client.get("add");  
	        client.set("add", 10, 1);  
	        client.delete("add");  
	        System.out.println(value);
	    } else {  
	    	Object value = client.get("add"); 
	    	System.err.println(value);
	    }  
	}

	@Test
	public void counter() throws Exception {
		Counter counter = client.getCounter("counter", 0);
		counter.incrementAndGet();
		counter.decrementAndGet();
		counter.addAndGet(-10);
	}

	public void auth() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		builder.addAuthInfo(AddrUtil.getOneAddress("localhost:11211"),
				AuthInfo.typical("cacheuser", "123456"));
		// Must use binary protocol
		builder.setCommandFactory(new BinaryCommandFactory());
		MemcachedClient client = builder.build();
	}

	public void nioPool() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("localhost:11211"));
		builder.setConnectionPoolSize(5);
	}

	/**
	 * 这里应该安装kestrel消息服务器，才能使用如下API生效
	 * 
	 * @throws IOException
	 * @throws MemcachedException
	 * @throws InterruptedException
	 * @throws TimeoutException
	 */
	@Test
	public void testGet() throws IOException, TimeoutException,
			InterruptedException, MemcachedException {
		String value = client.get("1");
		System.out.println("hello=" + value);
	}
	
	@Test
    public void testForeach() throws Exception {
	    System.out.println("testForeach start");
	    int size = 1000;
        String key = "hello";
        for (int i = 0; i < size; i++) {
            client.set(key+i, 0, "Hello,xmemcached"+i);
            Thread.sleep(10);
        }
        System.out.println("set done");
        for (int i = 0; i < size; i++) {
            boolean result = client.delete(key+i);
            if (!result) {
                System.err.println("delete key"+i);
            }
            Thread.sleep(10);
        }
        System.out.println("delete done");
        for (int i = 0; i < size; i++) {
            String value = client.get(key+i);
            if (StringUtils.isNotEmpty(value)) {
                System.out.println("get key"+i);
            }
        }
        System.out.println("testForeach stop");

    }
}

class Name implements Serializable {
    String firstName;
    String lastName;
    int age;
    int money;

    public Name(String firstName, String lastName, int age, int money) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.money = money;
    }

    public String toString() {
        return "[" + firstName + " " + lastName + ",age=" + age + ",money="
                + money + "]";
    }

}
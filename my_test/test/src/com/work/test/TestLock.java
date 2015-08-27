package com.work.test;

import java.io.IOException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.junit.Test;

public class TestLock {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("192.168.6.7:11000"));
		MemcachedClient client = builder.build();
		Thread threada = new AddThread(client);
		threada.setName("a ");
		Thread threadb = new AddThread(client);
		threadb.setName("b ");
		threada.start();
		threadb.start();
	}

	@Test
	public void testAdd() throws Exception {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("192.168.6.7:11000"));
		MemcachedClient client = builder.build();
		String key = "testAddkey";
		for (int i = 0; i < 21; i++) {
			boolean result = client.add(key, 10, 1);
			System.out.println("add:"+i+"  "+result);
			Thread.sleep(1000);
		}
//		System.out.println(result);
	}
	
}
class AddThread extends Thread{
	public MemcachedClient client = null;
	public AddThread(MemcachedClient client){
		this.client = client;
	}
	public boolean stopFlag = false;
	
	@Override
	public void run() {
		String key = "testAddkey";
		int count = 11;
		for (int i = 0; i < count; i++) {
			boolean result = false;
			try {
				result = client.add(key, 5, 1);
				System.out.println(Thread.currentThread().getName()+"add:"+i+"  "+result);
				Thread.currentThread().sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i>=count-1) {
				stopFlag = true;
			}
			if (stopFlag) {
				System.out.println(Thread.currentThread().getName()+" stop");
				Thread.currentThread().stop();
			}
		}
	}
}
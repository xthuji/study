package com.work.test.usetest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.junit.Test;

public class MemcachedTest {

	@Test
	public void testGet() throws IOException, TimeoutException,
			InterruptedException, MemcachedException {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
				AddrUtil.getAddresses("192.168.6.7:11000"));
		MemcachedClient client = builder.build();
		String result = client.get("test_memcached_get_0");
		
		System.out.println(result);
	}
	
}

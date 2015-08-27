package com.work.test.temptest;

import org.junit.Test;

public class RefrenceTest {

	@Test
	public void testString() throws Exception {
		
		String a = "abcd";
		String b = a;
		a = a + "e";
		System.out.println(b);
	}
}

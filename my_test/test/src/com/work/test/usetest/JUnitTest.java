package com.work.test.usetest;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.work.test.study.singleton.Singleton2;

public class JUnitTest {

	private static String changeFee2Yuan(String transAmt){
		BigDecimal amount = new BigDecimal(transAmt).divide(new BigDecimal(100)).setScale(0);
		return amount.toString();
	}
	
	@Test
	public void testName() throws Exception {
		String yuanString = changeFee2Yuan("1200");
		assertEquals("12", yuanString);
		//在idea工具中好像无法直接使用assertEquals，但可以用Assert.assertEquals
//		Assert.assertEquals("12", yuanString);
	}
	@Test
	public void commonTest() throws Exception {
		Singleton2 bean = Singleton2.getInstance();
	}
}

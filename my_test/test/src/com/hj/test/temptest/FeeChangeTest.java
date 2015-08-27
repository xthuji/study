package com.hj.test.temptest;

import java.math.BigDecimal;

public class FeeChangeTest {


	private static String changeFee2Fen(String fee){
		BigDecimal amount = new BigDecimal(fee).multiply(new BigDecimal(100)).setScale(0);
		int amt = amount.intValue();
		String fen = String.format("%012d", amt);
		return fen;
	}
	
	private static String changeFee2Yuan(String transAmt){
		BigDecimal amount = new BigDecimal(transAmt).divide(new BigDecimal(100)).setScale(2);
		return amount.toString();
	}
	
	public static void main(String[] args) {
		String fenString = changeFee2Fen("12");
		System.out.println(fenString);
		
		String yuanString = changeFee2Yuan("000000003000");
		System.out.println(yuanString);
		
//	    int youNumber = 1;     
//	    // 0 代表前面补充0     
//	    // 4 代表长度为4     
//	    // d 代表参数为正数型     
//	    String str = String.format("%04d", youNumber);     
//	    System.out.println(str); // 0001 
	    
	}
}

package com.work.test.study;

/**
 * final修饰的字符串相加，编译时就会计算得到字符串。
 * 所以其内存地址==直接声明的完整字符串内存地址
 * 其他的方式都是在运行时计算得到新的字符串
 */
public class StringTest {

	public static void main(String[] args) {
		String str = "ABC2";
		String str1 = "ABC";
		String str2 = str1 + 2;
		final String str3 = "ABC"; 
		String str4 = str3 + 2;
		String str5 = new String("ABC2");
		String str6 = "ABC" + 2;
		
		/* 编译后的代码    
		String str = "ABC2";
	    String str1 = "ABC";
	    String str2 = str1 + 2;
	    String str3 = "ABC";
	    String str4 = "ABC2";
	    String str5 = new String("ABC2");
	    String str6 = "ABC2";
	    */
		
		System.out.println(str==str2);//false
		System.out.println(str==str4);//ture
		System.out.println(str==str3+2);//true
		System.out.println(str==str1+2);//false
		System.out.println(str==str5);//false
		System.out.println(str==str6);//true
	}
}

package com.hj.test.study;

/**
 * 字符串转字符计数
 * 将aaaawwwweaa输出4a4w1e2a
 * @author huji
 *
 */
public class String2CharCount {

	public static void main(String[] args) {
		String s = "aaaawwwweaa";
		System.out.println(fn(s));
		System.out.println(fn2(s));
	}

	public static String fn(String s) {
		StringBuilder rs = new StringBuilder();
		String sTmp = "";
		int iTmp = 0;
		for (int i = 0; i < s.length(); i++) {
			if (0 == i) {
				sTmp = s.substring(0, 1);
				iTmp = 1;
			} else {
				if (sTmp.equals(s.substring(i, i + 1))) {
					iTmp++;
				} else {
					rs.append("" + iTmp + sTmp);
					sTmp = s.substring(i, i + 1);
					iTmp = 1;
				}
			}
		}
		if (iTmp != 0) {
			rs.append("" + iTmp + sTmp);
		}
		return rs.toString();
	}
	
	public static String fn2(String str) {
		StringBuilder resultSB = new StringBuilder();
		char[] chars = str.toCharArray();
		char sTmp = ' ';
		int iTmp = 0;
		for (int i = 0; i < str.length(); i++) {
			if (0 == i) {
				sTmp = chars[i];
				iTmp = 1;
			} else {
				if (sTmp==chars[i]) {
					iTmp++;
				} else {
					resultSB.append("" + iTmp + sTmp);
					sTmp = chars[i];
					iTmp = 1;
				}
			}
		}
		if (iTmp != 0) {
			resultSB.append("" + iTmp + sTmp);
		}
		return resultSB.toString();
	}

}

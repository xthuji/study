package com.work.test.temptest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetHtmlBodyTest {

	public static void main(String[] args) {
		String htmlString = "<!-- 支持多笔退款查询处理页面 --><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=GB2312\" /></head><body>ResponeseCode=111&Message=商户请求非法!</body></html>";

		Matcher m = Pattern.compile("<body>([^<]+)</body>").matcher(htmlString);
		String body = null;
		if (m.find()){
			body = m.group(1);
		}
		System.out.println(body);
	}

}

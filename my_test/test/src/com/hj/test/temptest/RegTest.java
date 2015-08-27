package com.hj.test.temptest;

import static org.junit.Assert.*;

import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegTest {

	@Test
	public void testName() throws Exception {
		String srcString = "insert into aa (order_id,fee,usr)values('%s','%s','%s')";
		String partten = "/value\\=+\\d+/i";
		String reg = "insert into \\w+\\(([\\w,]+)\\) values\\(([\\w,]+)\\);?";

	}

	@Test
	public void testName2() throws Exception {
		String sql = "insert into tb_temp(bookCase,book,bookName,price,author) values (1,'文学类','在山的那边','9999','云天锐')";

		String regex = "\\s*insert\\s*into\\s*[\\w\\.]+\\(([\\w,\\s]+)\\)\\s*values\\s*\\(([\\w,\\s\'\\u4e00-\\u9fa5]+)\\);?\\s*";
		String keys = sql.replaceAll(regex, "$1");
		String values = sql.replaceAll(regex, "$2");
		System.out.println(keys);
		System.out.println(values);

		Map<String, String> map = new HashMap<String, String>();
		String[] keyArrays = keys.split(",");
		String[] valArrays = values.split(",");
		for (int i = 0; i < keyArrays.length; i++) {
			map.put(keyArrays[i], valArrays[i]);
		}
		System.out.println(map.toString());

	}

	/**
	 * 截取()中间的内容
	 * @throws Exception
	 */
	@Test
	public void testName3() throws Exception {
		String str = "insert into tb_temp(bookCase,book,bookName,price,author) values (1,'文学类','在山的那边','9999','云天锐')";
		Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");

		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	/**
	 * 截取()中间的内容
	 * @throws Exception
	 */
	@Test
	public void testName4() throws Exception {
		String str = "insert into tb_temp(bookCase,book,bookName,price,author) values (1,'文学类','在山的那边','9999','云天锐')";
		Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
		// Pattern pattern = Pattern.compile("/\\(([^\\(]*)\\)/");

		Matcher matcher = pattern.matcher(str);
		matcher.find();
		String keys = matcher.group();
		System.out.println(keys);
		matcher.find();
		String values = matcher.group();
		System.out.println(values);
		
		List<String> keyList = Arrays.asList(keys.trim().split(","));
		System.out.println(keyList.toString());
		List<String> valueList = Arrays.asList(values.trim().split(","));
		System.out.println(valueList.toString());
	}
	
	/**
	 * 截取《 》中间的内容
	 * @throws Exception
	 */
	@Test
	public void testName5() throws Exception {
		String str="A《BCD》EF》G";
		String str2 = str.replaceAll("《(.*?)》", "<span class=\"brown\">《$1》</span>");
		System.out.println(str2);
	}
	
	/**
	 * 截取两个指定字符串 中间的 字符串
	 * MEMBERID":"和"中简单字符串
	 * @throws Exception
	 */
	@Test
	public void testName6() throws Exception {
		String str = "\"PROMOTEDTYPE\":\"260\",\"MEMBERID\":\"24912496\",\"SHOPTYPE\":\"2\",\"EXLEVEL\":\"15\"，\"PROMOTEDTYPE\":\"260\",\"MEMBERID\":\"78912496\",\"SHOPTYPE\":\"2\",\"EXLEVEL\":\"15\"，\"PROMOTEDTYPE\":\"260\",\"EXMEMBERID\":\"28912496\",\"SHOPTYPE\":\"2\",\"EXLEVEL\":\"15\"，\"PROMOTEDTYPE\":\"260\",\"MEMBERID\":\"74122906\",\"SHOPTYPE\":\"2\",\"EXLEVEL\":\"15\"。";
		String regex = "(?<=MEMBERID\\\":\\\")[^\\\"]*(?=\\\")";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		if(m.find()){
			System.out.println(m.group());
		}
//        while (m.find()) {
//            System.out.println(m.group());
//        }
		
	}
	/**
	 * 截取html标签<body>中的内容
	 * @throws Exception
	 */
	@Test
	public void testName7() throws Exception {
        String htmlString = "<!-- 支持多笔退款查询处理页面 --><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=GB2312\" /></head><body>ResponeseCode=111&Message=商户请求非法!</body></html>" ;

        Matcher m = Pattern.compile("<body>([^<]+)</body>").matcher(htmlString);
        String body = null;
         if (m.find()){
             body = m.group(1);
        }
        System. out.println(body);
        
	}
}

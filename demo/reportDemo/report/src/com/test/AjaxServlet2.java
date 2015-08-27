package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.bean.ConsumerBean;
/**
 * list形式的多条数据
 * @author huji
 *
 */
public class AjaxServlet2 extends HttpServlet {
	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List list = new ArrayList();
		List list1 = getData();
		List list2 = getData2();
		List list3 = getData3();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		
		Gson gson = new Gson();
		String str = gson.toJson(list.toArray());
//		System.out.println(str);
		
		PrintWriter out = response.getWriter();
		out.write(str);// 注意这里向jsp输出的流，在script中的截获方法
		out.close();

	}
	
	private List getData(){
		List list = new ArrayList();
		ConsumerBean consumer = new ConsumerBean();
		consumer.setConsume(12);
		consumer.setYearMonth("2013-01");
		list.add(consumer);
		ConsumerBean consumer2 = new ConsumerBean();
		consumer2.setConsume(22);
		consumer2.setYearMonth("2013-02");
		list.add(consumer2);
		ConsumerBean consumer3 = new ConsumerBean();
		consumer3.setConsume(15);
		consumer3.setYearMonth("2013-03");
		list.add(consumer3);
		ConsumerBean consumer4 = new ConsumerBean();
		consumer4.setConsume(18);
		consumer4.setYearMonth("2013-04");
		list.add(consumer4);
		ConsumerBean consumer5 = new ConsumerBean();
		consumer5.setConsume(12);
		consumer5.setYearMonth("2013-05");
		list.add(consumer5);
		ConsumerBean consumer6 = new ConsumerBean();
		consumer6.setConsume(22);
		consumer6.setYearMonth("2013-06");
		list.add(consumer6);
		ConsumerBean consumer7 = new ConsumerBean();
		consumer7.setConsume(18);
		consumer7.setYearMonth("2013-07");
		list.add(consumer7);
		ConsumerBean consumer8 = new ConsumerBean();
		consumer8.setConsume(19);
		consumer8.setYearMonth("2013-08");
		list.add(consumer8);
		ConsumerBean consumer9 = new ConsumerBean();
		consumer9.setConsume(38);
		consumer9.setYearMonth("2013-09");
		list.add(consumer9);
		ConsumerBean consumer10 = new ConsumerBean();
		consumer10.setConsume(10);
		consumer10.setYearMonth("2013-10");
		list.add(consumer10);
		ConsumerBean consumer11 = new ConsumerBean();
		consumer11.setConsume(28);
		consumer11.setYearMonth("2013-11");
		list.add(consumer11);
		ConsumerBean consumer12 = new ConsumerBean();
		consumer12.setConsume(18);
		consumer12.setYearMonth("2013-12");
		list.add(consumer12);
		return list;
	}
	private static List getData2(){
		List list = new ArrayList();
		ConsumerBean consumer = new ConsumerBean();
		consumer.setConsume(13);
		consumer.setYearMonth("2013-01");
		list.add(consumer);
		ConsumerBean consumer2 = new ConsumerBean();
		consumer2.setConsume(12);
		consumer2.setYearMonth("2013-02");
		list.add(consumer2);
		ConsumerBean consumer3 = new ConsumerBean();
		consumer3.setConsume(25);
		consumer3.setYearMonth("2013-03");
		list.add(consumer3);
		ConsumerBean consumer4 = new ConsumerBean();
		consumer4.setConsume(8);
		consumer4.setYearMonth("2013-04");
		list.add(consumer4);
		ConsumerBean consumer5 = new ConsumerBean();
		consumer5.setConsume(16);
		consumer5.setYearMonth("2013-05");
		list.add(consumer5);
		ConsumerBean consumer6 = new ConsumerBean();
		consumer6.setConsume(26);
		consumer6.setYearMonth("2013-06");
		list.add(consumer6);
		ConsumerBean consumer7 = new ConsumerBean();
		consumer7.setConsume(28);
		consumer7.setYearMonth("2013-07");
		list.add(consumer7);
		ConsumerBean consumer8 = new ConsumerBean();
		consumer8.setConsume(9);
		consumer8.setYearMonth("2013-08");
		list.add(consumer8);
		ConsumerBean consumer9 = new ConsumerBean();
		consumer9.setConsume(28);
		consumer9.setYearMonth("2013-09");
		list.add(consumer9);
		ConsumerBean consumer10 = new ConsumerBean();
		consumer10.setConsume(10);
		consumer10.setYearMonth("2013-10");
		list.add(consumer10);
		ConsumerBean consumer11 = new ConsumerBean();
		consumer11.setConsume(18);
		consumer11.setYearMonth("2013-11");
		list.add(consumer11);
		ConsumerBean consumer12 = new ConsumerBean();
		consumer12.setConsume(28);
		consumer12.setYearMonth("2013-12");
		list.add(consumer12);
		return list;
	}
	private static List getData3(){
		List list = new ArrayList();
		ConsumerBean consumer = new ConsumerBean();
		consumer.setConsume(12);
		consumer.setYearMonth("2013-01");
		list.add(consumer);
		ConsumerBean consumer2 = new ConsumerBean();
		consumer2.setConsume(12);
		consumer2.setYearMonth("2013-02");
		list.add(consumer2);
		ConsumerBean consumer3 = new ConsumerBean();
		consumer3.setConsume(25);
		consumer3.setYearMonth("2013-03");
		list.add(consumer3);
		ConsumerBean consumer4 = new ConsumerBean();
		consumer4.setConsume(28);
		consumer4.setYearMonth("2013-04");
		list.add(consumer4);
		ConsumerBean consumer5 = new ConsumerBean();
		consumer5.setConsume(13);
		consumer5.setYearMonth("2013-05");
		list.add(consumer5);
		ConsumerBean consumer6 = new ConsumerBean();
		consumer6.setConsume(25);
		consumer6.setYearMonth("2013-06");
		list.add(consumer6);
		ConsumerBean consumer7 = new ConsumerBean();
		consumer7.setConsume(13);
		consumer7.setYearMonth("2013-07");
		list.add(consumer7);
		ConsumerBean consumer8 = new ConsumerBean();
		consumer8.setConsume(17);
		consumer8.setYearMonth("2013-08");
		list.add(consumer8);
		ConsumerBean consumer9 = new ConsumerBean();
		consumer9.setConsume(30);
		consumer9.setYearMonth("2013-09");
		list.add(consumer9);
		ConsumerBean consumer10 = new ConsumerBean();
		consumer10.setConsume(12);
		consumer10.setYearMonth("2013-10");
		list.add(consumer10);
		ConsumerBean consumer11 = new ConsumerBean();
		consumer11.setConsume(24);
		consumer11.setYearMonth("2013-11");
		list.add(consumer11);
		ConsumerBean consumer12 = new ConsumerBean();
		consumer12.setConsume(13);
		consumer12.setYearMonth("2013-12");
		list.add(consumer12);
		return list;
	}
}
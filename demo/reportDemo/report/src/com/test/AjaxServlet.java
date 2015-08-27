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
 * list形式的单条数据
 * @author huji
 *
 */
public class AjaxServlet extends HttpServlet {
	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset=GBK");
		List list = getData();
		
		Gson gson = new Gson();
		String str = gson.toJson(list);
//		System.out.println(str);
		
		PrintWriter out = response.getWriter();
		out.write(str);// 注意这里向jsp输出的流，在script中的截获方法
		out.close();

	}
	
	public static void main(String[] args) {
		List list = getData();
		
		Gson gson = new Gson();
		String str = gson.toJson(list);
		System.out.println(str);
	}
	
	private static List getData(){
		List list = new ArrayList();
		ConsumerBean consumer = new ConsumerBean();
		consumer.setConsume(12);
		consumer.setYearMonth("2013年01月");
		list.add(consumer);
		ConsumerBean consumer2 = new ConsumerBean();
		consumer2.setConsume(22);
		consumer2.setYearMonth("2013年02月");
		list.add(consumer2);
		ConsumerBean consumer3 = new ConsumerBean();
		consumer3.setConsume(15);
		consumer3.setYearMonth("2013年03月");
		list.add(consumer3);
		ConsumerBean consumer4 = new ConsumerBean();
		consumer4.setConsume(18);
		consumer4.setYearMonth("2013年04月");
		list.add(consumer4);
		ConsumerBean consumer5 = new ConsumerBean();
		consumer5.setConsume(12);
		consumer5.setYearMonth("2013年05月");
		list.add(consumer5);
		ConsumerBean consumer6 = new ConsumerBean();
		consumer6.setConsume(22);
		consumer6.setYearMonth("2013年06月");
		list.add(consumer6);
		ConsumerBean consumer7 = new ConsumerBean();
		consumer7.setConsume(18);
		consumer7.setYearMonth("2013年07月");
		list.add(consumer7);
		ConsumerBean consumer8 = new ConsumerBean();
		consumer8.setConsume(19);
		consumer8.setYearMonth("2013年08月");
		list.add(consumer8);
		ConsumerBean consumer9 = new ConsumerBean();
		consumer9.setConsume(38);
		consumer9.setYearMonth("2013年09月");
		list.add(consumer9);
		ConsumerBean consumer10 = new ConsumerBean();
		consumer10.setConsume(10);
		consumer10.setYearMonth("2013年10月");
		list.add(consumer10);
		ConsumerBean consumer11 = new ConsumerBean();
		consumer11.setConsume(28);
		consumer11.setYearMonth("2013年11月");
		list.add(consumer11);
		ConsumerBean consumer12 = new ConsumerBean();
		consumer12.setConsume(18);
		consumer12.setYearMonth("2013年12月");
		list.add(consumer12);
		return list;
	}
}
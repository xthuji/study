package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.bean.ConsumerBean;
import com.test.bean.FusionMapConstant;
import com.test.bean.FusionMapping;
/**
 * map形式的多条数据
 * @author huji
 *
 */
public class AjaxServlet5 extends HttpServlet {
	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset=GBK");
		String isChina = request.getParameter("isChina");
		String area = request.getParameter("area");
		Map map = new HashMap();
		if ("1".equals(isChina)) {
			map = getData1();
		}else {
			map = getData2(area);
		}
		
		Gson gson = new Gson();
		String str = gson.toJson(map.values());
		System.out.println(str);
		
		PrintWriter out = response.getWriter();
		out.write(str);// 注意这里向jsp输出的流，在script中的截获方法
		out.close();

	}
	
	private static Map getData1(){
		Map<String, FusionMapping> chinaMap = FusionMapConstant.chinaMap;
		return chinaMap;
	}
	private static Map getData2(String code){
		Map<String, FusionMapping> provinceMap = FusionMapConstant.provinceMap.get(code);
		return provinceMap;
	}
}
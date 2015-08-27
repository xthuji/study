<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page import="java.io.*"%>
<%
	String checkCode = request.getParameter("VCode");
	String rightCheckCode = (String) session.getAttribute("code");
	boolean flag = false;
	if (checkCode == null || "".equals(checkCode)) {
		flag = false;
	}
	if (checkCode.equalsIgnoreCase(rightCheckCode)) {
		flag = true;
	}
	/* System.out.println("请求验证码："+checkCode);
	System.out.println("真实验证码："+rightCheckCode);
	System.out.println("验证结果："+flag); */
	//response.setContentType("text/html;charset=gbk");
	//response.getWriter().print("<script type=\"text/javascript\">alert(\"验证码："+flag+"\");history.back();</script>");
	response.getWriter().print("验证码"+(flag?"正确！":"错误！"));
%>
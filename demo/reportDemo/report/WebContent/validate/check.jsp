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
	/* System.out.println("������֤�룺"+checkCode);
	System.out.println("��ʵ��֤�룺"+rightCheckCode);
	System.out.println("��֤�����"+flag); */
	//response.setContentType("text/html;charset=gbk");
	//response.getWriter().print("<script type=\"text/javascript\">alert(\"��֤�룺"+flag+"\");history.back();</script>");
	response.getWriter().print("��֤��"+(flag?"��ȷ��":"����"));
%>
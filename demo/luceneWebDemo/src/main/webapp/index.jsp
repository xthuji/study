<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>搜索</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
  <div align="center">
	  <form method="post" action="createIndex">
	  	<input type="submit" value="创建索引"/>
	  </form>
	  <%
	  String msg = (String)request.getAttribute("msg");
	  if(msg!=null && !msg.equals("")) {
		  %>
		  <font color="red"><%=msg %></font>
		  <%
	  }
	  %>
  </div>
  <%
  String keyWord =(String)request.getAttribute("keyWord");
  if(keyWord==null) {
	  keyWord = "";
  }
  %>
  <div align="center">
  <form method="post" action="search">
  	搜索关键字：<input type="text" name="keyWord" value="<%=keyWord%>"/> <input type="submit" value="搜 索"/>
  </form>
  </div>
         <% 
	  String resultNum = (String)request.getAttribute("resultNum");//查询结果总条数
	  Map<String, String> map = (Map<String, String>)request.getAttribute("map");
	  if(resultNum != null) {
		  %>
	  <div>查询结果：</div>
	  <div>搜索到的结果数：<%=resultNum %></div>
	  <%
	  for (String key : map.keySet()) {
		  String  value = map.get(key);
      %>
		<div>路径：<%=key %></div>
		<div>内容：<%=value %></div>
	  <%
	     }
	  }%>
  </body>
</html>

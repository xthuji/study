<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:set var="base" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>联通数据平台</title>
<%//导入框架引用的css %>
<link type="text/css" rel="stylesheet" href="${base}/res/css/index.css" />
<link type="text/css" rel="stylesheet" href="${base}/res/js/jquerydialog/css/jquery.modaldialog.css" />
<link type="text/css" rel="stylesheet" href="${base}/res/js/colorbox/res/colorbox.css" />
<link type="text/css" rel="stylesheet" href="${base}/res/js/qtip2/jquery.qtip.min.css" />
<%//导入框架引用的js %>
<script type="text/javascript" src="${base}/res/js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${base}/res/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${base}/res/js/jquerydialog/js/jquery.modaldialog.js"></script>
<script type="text/javascript" src="${base}/res/js/colorbox/jquery.colorbox-min.js"></script>
<script type="text/javascript" src="${base}/res/js/qtip2/jquery.qtip.min.js"></script>
<script type="text/javascript" src="${base}/res/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/res/js/App.js"></script>

<decorator:head />
</head>

<body>
<div class="top">
	<div class="logo"><a href="#"><img src="${base}/res/images/logo.gif" width="1002" height="58" /></a></div>
    <div class="nav_left"></div>
    <div class="nav_center"></div>
    <div class="nav_right"></div>
    <div class="clear"></div>
</div>
<!--top end-->
<div class="main">
	<div class="left">
    	<h2>流量分析</h2>
    	<ul>
        	<li><a href="${base}/testUser/index_userQuery.j">概况</a></li>
        </ul>	
        <h2>流量分析</h2>
    	<ul>
        	<li><a href="${base}/testUser/index_userQuery.j">概况</a></li>
        </ul>
    </div>
    <div class="right">
      <decorator:body />
      </div>
    <div class="clear"></div>
</div><!--main end-->
<div class="foot">
	<center>版权信息</center>
</div>
</body>
</html>

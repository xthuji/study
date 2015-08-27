    <%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="utf-8"%>  
    <%@ page import="org.jfree.chart.ChartFactory,org.jfree.chart.JFreeChart,org.jfree.chart.plot.PlotOrientation,  
    org.jfree.chart.servlet.ServletUtilities,org.jfree.data.category.DefaultCategoryDataset"%>  
    <%  
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
    dataset.addValue(510, "深圳", "苹果");  
    dataset.addValue(320, "深圳", "香蕉");  
    dataset.addValue(580, "深圳", "橘子");  
    dataset.addValue(390, "深圳", "梨子");  
    JFreeChart chart = ChartFactory.createBarChart3D("水果销量统计图",   
                      "水果",  
                      "销量",  
                      dataset,  
                      PlotOrientation.VERTICAL,  
                      false,  
                      false,  
                      false);  
    String filename = ServletUtilities.saveChartAsPNG(chart, 420, 300, null, session);  
    String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;  
    %>  
    <img src="<%= graphURL %>" width=420 height=300 border=0>  
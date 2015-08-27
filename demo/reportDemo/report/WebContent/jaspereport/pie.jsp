    <%@ page contentType="text/html;charset=GBK"%>  
      
    <%@ page import="org.jfree.chart.*,org.jfree.chart.plot.PiePlot,org.jfree.chart.title.TextTitle,org.jfree.chart.labels.*,  
    org.jfree.data.general.DefaultPieDataset,org.jfree.chart.servlet.ServletUtilities,java.awt.*,java.text.NumberFormat"%>  
    <%  
    //设置饼图数据集  
    DefaultPieDataset dataset = new DefaultPieDataset();  
    dataset.setValue("黑心矿难", 720);  
    dataset.setValue("醉酒驾驶", 530);  
    dataset.setValue("城管强拆", 210);  
    dataset.setValue("医疗事故", 91);  
    dataset.setValue("其他", 66);  
      
    //通过工厂类生成JFreeChart对象  
    JFreeChart chart = ChartFactory.createPieChart("非正常死亡人数分布图", dataset, true, true, false);  
    //加个副标题  
    chart.addSubtitle(new TextTitle("2010年度"));  
    PiePlot pieplot = (PiePlot) chart.getPlot();  
    pieplot.setLabelFont(new Font("宋体", 0, 11));  
    //设置饼图是圆的（true），还是椭圆的（false）；默认为true  
    pieplot.setCircular(true);  
    StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1},{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
    pieplot.setLabelGenerator(standarPieIG);  
      
    //没有数据的时候显示的内容  
    pieplot.setNoDataMessage("无数据显示");  
    pieplot.setLabelGap(0.02D);  
      
    String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session);  
    String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;  
    %>  
    <img src="<%= graphURL %>" width=490 height=306 border=0 >  
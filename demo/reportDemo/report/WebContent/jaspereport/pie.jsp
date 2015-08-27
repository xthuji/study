    <%@ page contentType="text/html;charset=GBK"%>  
      
    <%@ page import="org.jfree.chart.*,org.jfree.chart.plot.PiePlot,org.jfree.chart.title.TextTitle,org.jfree.chart.labels.*,  
    org.jfree.data.general.DefaultPieDataset,org.jfree.chart.servlet.ServletUtilities,java.awt.*,java.text.NumberFormat"%>  
    <%  
    //���ñ�ͼ���ݼ�  
    DefaultPieDataset dataset = new DefaultPieDataset();  
    dataset.setValue("���Ŀ���", 720);  
    dataset.setValue("��Ƽ�ʻ", 530);  
    dataset.setValue("�ǹ�ǿ��", 210);  
    dataset.setValue("ҽ���¹�", 91);  
    dataset.setValue("����", 66);  
      
    //ͨ������������JFreeChart����  
    JFreeChart chart = ChartFactory.createPieChart("���������������ֲ�ͼ", dataset, true, true, false);  
    //�Ӹ�������  
    chart.addSubtitle(new TextTitle("2010���"));  
    PiePlot pieplot = (PiePlot) chart.getPlot();  
    pieplot.setLabelFont(new Font("����", 0, 11));  
    //���ñ�ͼ��Բ�ģ�true����������Բ�ģ�false����Ĭ��Ϊtrue  
    pieplot.setCircular(true);  
    StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1},{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
    pieplot.setLabelGenerator(standarPieIG);  
      
    //û�����ݵ�ʱ����ʾ������  
    pieplot.setNoDataMessage("��������ʾ");  
    pieplot.setLabelGap(0.02D);  
      
    String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, null, session);  
    String graphURL = request.getContextPath() + "/DisplayChart?filename=" + filename;  
    %>  
    <img src="<%= graphURL %>" width=490 height=306 border=0 >  
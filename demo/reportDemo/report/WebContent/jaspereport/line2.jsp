<%@ page contentType="text/html;charset=GBK"%>
<%@ page
	import="org.jfree.chart.ChartFactory,org.jfree.chart.renderer.xy.XYItemRenderer,org.jfree.chart.plot.XYPlot,org.jfree.chart.JFreeChart,org.jfree.chart.renderer.xy.XYLineAndShapeRenderer,org.jfree.chart.servlet.ServletUtilities,org.jfree.chart.title.TextTitle,java.awt.Font,org.jfree.data.time.TimeSeries,org.jfree.data.time.Month,org.jfree.data.time.TimeSeriesCollection,org.jfree.chart.labels.*,org.jfree.ui.*"%>
<%
	//ʱ���������ݼ���
	TimeSeriesCollection lineDataset = new TimeSeriesCollection();
	//������ͳ��ʱ���� 
	TimeSeries timeSeriesA = new TimeSeries("����վ������ͳ��", Month.class);
	TimeSeries timeSeriesB = new TimeSeries("����վ������ͳ��", Month.class);
	//�������ݼ��� 
	timeSeriesA.add(new Month(1, 2010), 3200);
	timeSeriesA.add(new Month(2, 2010), 3900);
	timeSeriesA.add(new Month(3, 2010), 6200);
	timeSeriesA.add(new Month(4, 2010), 4200);
	timeSeriesA.add(new Month(5, 2010), 5200);
	timeSeriesA.add(new Month(6, 2010), 3300);
	timeSeriesA.add(new Month(7, 2010), 4400);
	timeSeriesA.add(new Month(8, 2010), 7300);
	timeSeriesA.add(new Month(9, 2010), 2400);
	timeSeriesA.add(new Month(10, 2010), 2500);
	timeSeriesA.add(new Month(11, 2010), 3600);
	timeSeriesA.add(new Month(12, 2010), 2500);
	timeSeriesB.add(new Month(1, 2010), 1120);
	timeSeriesB.add(new Month(2, 2010), 900);
	timeSeriesB.add(new Month(3, 2010), 600);
	timeSeriesB.add(new Month(4, 2010), 820);
	timeSeriesB.add(new Month(5, 2010), 800);
	timeSeriesB.add(new Month(6, 2010), 1200);
	timeSeriesB.add(new Month(7, 2010), 1200);
	timeSeriesB.add(new Month(8, 2010), 830);
	timeSeriesB.add(new Month(9, 2010), 1200);
	timeSeriesB.add(new Month(10, 2010), 1500);
	timeSeriesB.add(new Month(11, 2010), 1600);
	timeSeriesB.add(new Month(12, 2010), 1500);
	lineDataset.addSeries(timeSeriesA);
	lineDataset.addSeries(timeSeriesB);
	JFreeChart chart = ChartFactory.createTimeSeriesChart("������ͳ��ʱ����",
			"�·�", "������", lineDataset, true, true, true);
	//���������� 
	TextTitle title = new TextTitle("ĳ��վ�������ͳ��", new Font("����",
			Font.ITALIC, 15));
	chart.setTitle(title);
	//�����ӱ��� 
	TextTitle subtitle = new TextTitle("2010���", new Font("����",
			Font.BOLD, 12));
	chart.addSubtitle(subtitle);
	chart.setAntiAlias(true);
	
	//���������Ƿ���ʾ���ݵ� 
	XYPlot plot = (XYPlot) chart.getPlot();
	XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) plot
			.getRenderer();
	xylinerenderer.setBaseShapesVisible(true);
	//����������ʾ�����ݵ��ֵ 
	XYItemRenderer xyitem = plot.getRenderer();
	xyitem.setBaseItemLabelsVisible(true);
	xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(
			ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
	xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
	xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 12));
	plot.setRenderer(xyitem);
	
	String filename = ServletUtilities.saveChartAsPNG(chart, 500, 400,
			null, session);
	String graphURL = request.getContextPath()
			+ "/DisplayChart?filename=" + filename;
%>
<img src="<%=graphURL%>" width=500 height=400 border=0>

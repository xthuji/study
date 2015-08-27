<%@ page contentType="text/html;charset=GBK"%>
<%@ page
	import="org.jfree.chart.ChartFactory,org.jfree.chart.JFreeChart,org.jfree.chart.servlet.ServletUtilities,org.jfree.chart.title.TextTitle,org.jfree.data.time.TimeSeries,org.jfree.data.time.Month,org.jfree.chart.plot.XYPlot,org.jfree.data.time.TimeSeriesCollection,java.awt.Font,org.jfree.chart.renderer.xy.XYLineAndShapeRenderer,org.jfree.chart.renderer.xy.XYItemRenderer,org.jfree.ui.RectangleInsets,org.jfree.chart.labels.*,org.jfree.ui.*,org.jfree.chart.axis.*,java.util.*"%>
<%
	//������ͳ��ʱ����
	TimeSeries timeSeries = new TimeSeries("ĳ��վ������ͳ��", Month.class);
	//ʱ���������ݼ���
	TimeSeriesCollection lineDataset = new TimeSeriesCollection();
	//�������ݼ��� 
	timeSeries.add(new Month(1, 2010), 1100);
	timeSeries.add(new Month(2, 2010), 1200);
	timeSeries.add(new Month(3, 2010), 1000);
	timeSeries.add(new Month(4, 2010), 900);
	timeSeries.add(new Month(5, 2010), 1000);
	timeSeries.add(new Month(6, 2010), 1200);
	timeSeries.add(new Month(7, 2010), 1300);
	timeSeries.add(new Month(8, 2010), 1400);
	timeSeries.add(new Month(9, 2010), 1200);
	timeSeries.add(new Month(10, 2010), 1500);
	timeSeries.add(new Month(11, 2010), 1600);
	timeSeries.add(new Month(12, 2010), 1300);
	lineDataset.addSeries(timeSeries);
	JFreeChart chart = ChartFactory.createTimeSeriesChart("������ͳ��ʱ����",
			"�·�", "������", lineDataset, true, true, true);
	//���������� 
	chart.setTitle(new TextTitle("ĳ��վ������ͳ��", new Font("����",
			Font.ITALIC, 15)));
	//�����ӱ��� 
	TextTitle subtitle = new TextTitle("2010���", new Font("����",
			Font.BOLD, 12));
	chart.addSubtitle(subtitle);
	chart.setAntiAlias(true);
	XYPlot plot = (XYPlot) chart.getPlot();
	//����ʱ����ķ�Χ�� 
	DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
	dateaxis.setDateFormatOverride(new java.text.SimpleDateFormat("M��"));
	dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH, 1));
	Calendar date = Calendar.getInstance();
	date.set(2009, 11, 1);
	Calendar mdate = Calendar.getInstance();
	mdate.set(2010, 11, 30);
	dateaxis.setRange(date.getTime(), mdate.getTime());
	//����������귶Χ 
	ValueAxis axis = plot.getRangeAxis();
	axis.setRange(800, 1800);
	plot.setRangeAxis(axis);
	//��������ͼ��xy��ľ��� [��,��,��,��] 
	plot.setAxisOffset(new RectangleInsets(0D, 0D, 0D, 12D));
	//���������Ƿ���ʾ���ݵ� 
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
	String filename = ServletUtilities.saveChartAsPNG(chart, 500, 360,
			null, session);
	String graphURL = request.getContextPath()
			+ "/DisplayChart?filename=" + filename;
%>
<img src="<%=graphURL%>" width=500 height=360 border=0>

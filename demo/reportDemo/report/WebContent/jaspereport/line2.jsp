<%@ page contentType="text/html;charset=GBK"%>
<%@ page
	import="org.jfree.chart.ChartFactory,org.jfree.chart.renderer.xy.XYItemRenderer,org.jfree.chart.plot.XYPlot,org.jfree.chart.JFreeChart,org.jfree.chart.renderer.xy.XYLineAndShapeRenderer,org.jfree.chart.servlet.ServletUtilities,org.jfree.chart.title.TextTitle,java.awt.Font,org.jfree.data.time.TimeSeries,org.jfree.data.time.Month,org.jfree.data.time.TimeSeriesCollection,org.jfree.chart.labels.*,org.jfree.ui.*"%>
<%
	//时间曲线数据集合
	TimeSeriesCollection lineDataset = new TimeSeriesCollection();
	//访问量统计时间线 
	TimeSeries timeSeriesA = new TimeSeries("甲网站访问量统计", Month.class);
	TimeSeries timeSeriesB = new TimeSeries("乙网站访问量统计", Month.class);
	//构造数据集合 
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
	JFreeChart chart = ChartFactory.createTimeSeriesChart("访问量统计时间线",
			"月份", "访问量", lineDataset, true, true, true);
	//设置主标题 
	TextTitle title = new TextTitle("某网站年访问量统计", new Font("隶书",
			Font.ITALIC, 15));
	chart.setTitle(title);
	//设置子标题 
	TextTitle subtitle = new TextTitle("2010年度", new Font("黑体",
			Font.BOLD, 12));
	chart.addSubtitle(subtitle);
	chart.setAntiAlias(true);
	
	//设置曲线是否显示数据点 
	XYPlot plot = (XYPlot) chart.getPlot();
	XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) plot
			.getRenderer();
	xylinerenderer.setBaseShapesVisible(true);
	//设置曲线显示各数据点的值 
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

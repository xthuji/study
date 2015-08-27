package com.test.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.test.bean.ConsumerBean;

public class XmlBuilder {
	public static String[] colorArray = {"AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646", 
			"8E468E", "588526", "B3AA00", "008ED6", "9D080D", "A186BE"}; 
	
	/**
	 * 柱状图
	 * 
	 * @param map
	 * @return
	 */
	public static String buildPillarXml0(Map map) {
		StringBuilder str = new StringBuilder();
		str.append("<graph xaxisname='Continent' yaxisname='Export' hovercapbg='DEDEBE' hovercapborder='889E6D' rotateNames='0' yAxisMaxValue='100' numdivlines='9' divLineColor='CCCCCC' divLineAlpha='80' decimalPrecision='0' showAlternateHGridColor='1' AlternateHGridAlpha='30' AlternateHGridColor='CCCCCC' caption='Global Export' subcaption='In Millions Tonnes per annum pr Hectare' >");
		str.append("   <categories font='Arial' fontSize='11' fontColor='000000'>");
		str.append("      <category name='N. America' hoverText='North America'/>");
		str.append("      <category name='Asia'/>");
		str.append("      <category name='Europe'/>");
		str.append("      <category name='Australia'/>");
		str.append("      <category name='Africa'/>");
		str.append("   </categories>");
		str.append("   <dataset seriesname='Rice' color='FDC12E'>");
		str.append("      <set value='30'/>");
		str.append("      <set value='26'/>");
		str.append("      <set value='29'/>");
		str.append("      <set value='31'/>");
		str.append("      <set value='34'/>");
		str.append("   </dataset>");
		str.append("    <dataset seriesname='Wheat' color='56B9F9'>");
		str.append("      <set value='67'/>");
		str.append("      <set value='98'/>");
		str.append("      <set value='79'/>");
		str.append("      <set value='73'/>");
		str.append("      <set value='80'/>");
		str.append("   </dataset>");
		str.append("    <dataset seriesname='Grain' color='C9198D' >");
		str.append("      <set value='27'/>");
		str.append("      <set value='25'/>");
		str.append("      <set value='28'/>");
		str.append("      <set value='26'/>");
		str.append("      <set value='10'/>");
		str.append("   </dataset>");
		str.append("</graph>");
		return str.toString();
	}
	/**
	 * 柱状图
	 * 
	 * @param map
	 * @return
	 */
	public static String buildPillarXml(Map map) {
		List list = (List) map.get("list");
		StringBuilder str = new StringBuilder();
		StringBuilder categoriesStr = new StringBuilder();
		StringBuilder datasetStr = new StringBuilder();
		str.append("<graph numberPrefix='$' decimalPrecision='0' animation='1' showValues='1' showNames='1' rotateValues='1' caption='"+map.get("reportName")+"' yAxisName='"+map.get("YName")+"' xAxisName='"+map.get("XName")+"' yaxismaxvalue='30'>");
		categoriesStr.append("<categories >");
		Map category = (Map)list.get(0);
		List catagories = (List) category.get("data");
		for (Object object : catagories) {
			ConsumerBean consumer = (ConsumerBean) object;
			categoriesStr.append("<category name='"+consumer.YearMonth+"'/>");
		}
		categoriesStr.append("</categories>");
		for (int i=0; i<list.size();i++) {
			Map dataMap = (Map) list.get(i);
			String name = (String) dataMap.get("name");
			List dataList = (List) dataMap.get("data");
			datasetStr.append("<dataset seriesName='"+name+"' color='"+colorArray[i]+"'>");
			for (Object list2 : dataList) {
				ConsumerBean consumer = (ConsumerBean) list2;
				datasetStr.append("<set value='"+consumer.Consume+"'/>");
			}
			datasetStr.append("</dataset>");
		}
		str.append(categoriesStr).append(datasetStr);
		str.append("</graph>");
		return str.toString();
	}

	/**
	 * 饼图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildPieXml(List list) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 折线图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildLineXml(List list) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 柱状&折线图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildCol_LineXml(List list) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 环形图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildDoughnutXml(List list) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 面积图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildAreaXml(List list) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 条形图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildColXml(List list) {

		return null;
	}

	/**
	 * 漏斗图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildFunnelXml(List list) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * stackedBar图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildStackedBarXml(List list) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * stackedCol图
	 * 
	 * @param list
	 * @return
	 */
	public static String buildStackedColXml(List list) {
		// TODO Auto-generated method stub
		return null;
	}

}

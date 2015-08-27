package com.unicom.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @author chenxinquan
 *
 */
public class DateUtil {

	public final static String DATE_FORMAT = "yyyy-MM-dd";
	public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * 获得现在时间，格式为：yyyy-MM-dd
	 * @return string yyyy-MM-dd
	 */
	public static String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Calendar c = Calendar.getInstance();
		return format.format(c.getTime());
	}
	/**
	 * 获得现在时间，格式为：yyyy-MM-dd HH:mm:ss
	 * @return string yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowDateTimeStr() {
		SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT);
		Calendar c = Calendar.getInstance();
		return format.format(c.getTime());
	}
	/**
	 * 获得系统当前时间
	 * @return Date
	 */
	public static Date getNowDate(){
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}
	/**
	 * @param date 待转化的日期,格式为：yyyy-MM-dd
	 * @return 字符串式的日期,格式为：yyyy-MM-dd
	 */
	public static String getDateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return format.format(date);
	}
	/**
	 * @param date 待转化的日期,格式为：yyyy-MM-dd HH:mm:ss
	 * @return 字符串式的日期,格式为：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT);
		return format.format(date);
	}
	/**
	 * @param dateStr 把日期字符串转化成日期类型的日期，格式：yyyy-MM-dd
	 * @return Date类型的日期
	 */
	public static Date getStringToDate(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * @param dateStr 把日期字符串转化成日期类型的日期，格式：yyyy-MM-dd HH:mm:ss
	 * @return Date类型的日期
	 */
	public static Date getStringToDateTime(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static void main(String[] args) {
		System.out.println(getStringToDate("2013-9-08"));
	}
}

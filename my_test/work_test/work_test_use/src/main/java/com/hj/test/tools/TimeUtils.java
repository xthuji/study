package com.hj.test.tools;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtils {
    private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);

    public static final int H_M = 0;
    public static final int H_M_S = 1;
    public static final int Y_H_M = 2;
    public static final int Y_H_M_H_M = 3;
    public static final int Y_H_M_H_M_S = 4;
    public static final int Y = 5;
    public static final int Y_H = 6;
    public static final int Y_H_M_H_M_S_2 = 7;
    public static final int yyyy_MM_ddTHH_mm_ss_z = 8;
    public static final String sdfString = "yyyy-MM-dd HH:mm:ss";
    public static final String sdfStr = "yyyyMMddHHmmss";
    public static String SDF_YYYY_MM_DD_00 = "yyyy-MM-dd 00:00:00";
    public static String SDF_YYYY_MM_DD = "yyyy-MM-dd";
    public static String SDF_YYYYMM = "yyyyMM";
    public static String SDF_YYYYMMDD = "yyyyMMdd";

    private static SimpleDateFormat dateformat0;
    private static SimpleDateFormat dateformat1;
    private static SimpleDateFormat dateformat2;
    private static SimpleDateFormat dateformat3;
    private static SimpleDateFormat dateformat4;
    private static SimpleDateFormat dateformat5;
    private static SimpleDateFormat dateformat6;
    private static SimpleDateFormat dateformat7;
    private static SimpleDateFormat dateformat8;

    static {
        dateformat0 = new SimpleDateFormat("HH:mm");
        dateformat1 = new SimpleDateFormat("HH:mm:ss");
        dateformat2 = new SimpleDateFormat("yyyy-MM-dd");
        dateformat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateformat4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateformat5 = new SimpleDateFormat("yyyy年");
        dateformat6 = new SimpleDateFormat("yyyy年MM月");
        dateformat7 = new SimpleDateFormat("yyyyMMddHHmmss");
        dateformat8 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'");
    }
    
    

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfString);
        return sdf.format(date);
    }

    public static boolean canFormated(String date, String format) {
        SimpleDateFormat s = new SimpleDateFormat(format);
        boolean b = false;
        try {
            s.parse(date);
            b = true;
        } catch (ParseException p) {
        }
        return b;
    }

    public static Date formatString(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfString);
        return sdf.parse(time);
    }

    public static Date formatString(String time, String fromatStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(fromatStr);
        return sdf.parse(time);
    }

    public static Date formatDate(String str, SimpleDateFormat sdf) throws ParseException {
        return sdf.parse(str);
    }

    /**
     * 根据时戳获得时间格式文本
     *
     * @param timestamp
     * @return 时间格式文本 （yyyy-MM-dd HH:mm:ss）
     */
    public static String GetTimeByStamp(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat format = new SimpleDateFormat(sdfString);
        return format.format(date);
    }

    /**
     * 根据时间格式文本 获得时戳
     *
     * @param time 时间格式文本 （yyyy-MM-dd HH:mm:ss）
     * @return timestamp
     */
    public static String GetStampByTime(String time) {
        String Stamp = "";
        SimpleDateFormat sdf = new SimpleDateFormat(sdfString);
        Date date;
        try {
            date = sdf.parse(time);
            Stamp = date.getTime() + "000";
        } catch (Exception e) {
            throw new BaseException(e);
        }
        return Stamp;
    }

    /**
     * 获得当前时间
     *
     * @return 时间格式文本 （yyyy-MM-dd HH:mm:ss）
     */
    public static String GetCurrentTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(sdfString);
        return format.format(date);
    }
    
    /**
     * 获得当前时间
     *
     * @return 时间格式文本 （yyyy-MM-dd HH:mm:ss）
     */
    public static String GetCurrentTime(String sdfStr) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(sdfStr);
        return format.format(date);
    }
    

    public static String formatDate(Date date, String formateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(formateStr);
        return sdf.format(date);
    }


    public static long formatDate(String dateTimeStr, String startOrEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat(TimeUtils.sdfString);

        if (dateTimeStr != null && !"".equals(dateTimeStr)) {
            if ("start".equals(startOrEnd)) {
                if (!dateTimeStr.trim().contains(" ")) {
                    dateTimeStr = dateTimeStr + " 00:00:00";
                }
            }
            if ("end".equals(startOrEnd)) {
                if (!dateTimeStr.trim().contains(" ")) {
                    dateTimeStr = dateTimeStr + " 23:59:59";
                }
            }
            try {
                Date dateTime = sdf.parse(dateTimeStr);
                return dateTime.getTime();
            } catch (Exception e) {
                logger.error("formatDate error", e);
            }
        }
        return -1;
    }
    /*
    public static Date formatString(String time) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(time);
    }

    public static String formatDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    */

    /**
     * 获得系统的纳秒时间long值
     *
     * @return
     */
    public static long getNowTime() {
        return System.nanoTime();
    }


    /**
     * @param num        格式化类型标识
     * @param stringDate 日期的字符串形式
     * @return Date对象
     * @description 返回格日期
     */
    @Deprecated
    public static Date getFormatDate(int num, String stringDate) {
        Date resultDate = null;
        SimpleDateFormat dateformat = getFormat(num);
        if (dateformat != null) {
            try {
                resultDate = dateformat.parse(stringDate);
            } catch (Exception ex) {
                throw new BaseException(ex);
            }
        }

        return resultDate;
    }

    /**
     * 根据不同的类型得到不同的格式化工具类
     *
     * @param num 表示类型
     * @return 不同类型的格式化工具类
     */
    private static SimpleDateFormat getFormat(int num) {
        SimpleDateFormat dateformat = null;
        switch (num) {
            case TimeUtils.H_M:
                dateformat = dateformat0;
                break;
            case TimeUtils.H_M_S:
                dateformat = dateformat1;
                break;
            case TimeUtils.Y_H_M:
                dateformat = dateformat2;
                break;
            case TimeUtils.Y_H_M_H_M:
                dateformat = dateformat3;
                break;
            case TimeUtils.Y_H_M_H_M_S:
                dateformat = dateformat4;
                break;
            case TimeUtils.Y:
                dateformat = dateformat5;
                break;
            case TimeUtils.Y_H:
                dateformat = dateformat6;
                break;
            case TimeUtils.Y_H_M_H_M_S_2:
                dateformat = dateformat7;
                break;
            case TimeUtils.yyyy_MM_ddTHH_mm_ss_z:
                dateformat = dateformat8;
                break;
            default:
                break;
        }
        return dateformat;
    }


    /**
     * 将日期转换成unix时间戳
     *
     * @param date
     * @return
     */
    public static long dateToUnixTimestamp(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 将unix时间戳转换成日期
     *
     * @param unixTimestamp
     * @return
     */
    public static Date unixTimestampToDate(long unixTimestamp) {
        return new Date(unixTimestamp * 1000);
    }

    /**
     * @param num  类型
     * @param date 需要处理的日期
     * @return 返回需要的日期字符串形式
     * @description 返回格式化字符串型日期
     */
    public static String getFormatString(int num, Date date) {
        if (date == null)
            return "";
        else {
            SimpleDateFormat dateformat = getFormat(num);
            return dateformat == null ? "" : dateformat.format(date);
        }
    }

    /**
     * 描述: 按默认格式的字符串 计算出距离今天的天数
     * 参数: @param date 日期字符串
     * 返回: int 距离今天的天数
     * 作者: wangmingli@zhangyue.com
     * 时间: 2012-12-24 下午06:22:31
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatString(date));
            long t1 = c.getTime().getTime();
            return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }


    /**
     * 描述: 按用户格式字符串 计算出距离今天的天数
     * 参数: @param date 日期字符串
     * 参数: @param format 日期格式
     * 返回: int 距离今天的天数
     * 作者: wangmingli@zhangyue.com
     * 时间: 2012-12-24 下午06:21:04
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatString(date));
            long t1 = c.getTime().getTime();
            return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 计算时间之间的天数差 绝对值
     * @param stratDateStr
     * @param endDateStr
     * @return
     */
    public static int getCountDays(String stratDateStr, String endDateStr) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatString(stratDateStr));
            long t1 = c.getTime().getTime();
            c.setTime(formatString(endDateStr));
            long t2 = c.getTime().getTime();

            return (int) java.lang.Math.abs((t1 / 1000 - t2 / 1000) / 3600 / 24);
        } catch (ParseException e) {
            throw new BaseException(e);
        }

    }
    
    /**
     * 计算两个时间的天数差 绝对值
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getCountDays(Date startDate, Date endDate) {
        long to = endDate.getTime();
        long from = startDate.getTime();
        return (int) java.lang.Math.abs(to - from) / (1000 * 60 * 60 * 24);
	}

    /**
     * 描述: 获取前几天或后几天时间 如：前一天 -1，后一天 1
     * 作者: wangmingli@zhangyue.com
     * 时间: 2013-4-2 下午08:24:50
     */
    public static Date getFutureOrPastDate(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, i);
        Date date = calendar.getTime();
        return date;
    }
    
}

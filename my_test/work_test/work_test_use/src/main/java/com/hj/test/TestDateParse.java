package com.hj.test;

import static org.junit.Assert.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.gson.Gson;
import com.hj.test.tools.GsonUtils;
import com.hj.test.tools.TimeUtils;


public class TestDateParse {
	
    public static final Date parseDate(String strDate, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
        	throw new RuntimeException(e);
        }

        return date;
    }
    
    public static void main(String[] args) throws Exception {
    	String startTime = "20141222";
    	Date date = null;
    	try{
            date = parseDate(startTime,"yyMMdd");
        }catch (Exception e){
            throw new Exception("startTime格式错误");
        }
    	System.out.println(date);
	}
    
    class A {
    	public Date date;
    	public String user;
    }
    @Test
	public void testName() throws Exception {
		A a = new A();
		a.date = new Date();
		a.user = "aaa";
		System.out.println(a.user);
		System.out.println(GsonUtils.objectToJsonDateFomart(a));
	}
    
    @Test
	public void testParse() throws Exception {
		String json = "{\"date\":\"2015-01-08 10:52:38\"}";
		A a = GsonUtils.jsonToBean(json, A.class);
		System.out.println(a.user);
		System.out.println(a.date);
	}

    @Test
	public void testDate() throws Exception {
    	Date date = new Date(1412165222482L);
		String dateString = TimeUtils.formatDate(date);
		System.out.println(dateString);
	}
    
    @Test
	public void testDateDur() throws Exception {
		String dateString1 = "2015-01-05 10:52:38";
		String dateString2 = "2015-01-08 10:52:38";
		int days = TimeUtils.getCountDays(dateString1, dateString2);
		System.out.println(days);
	}
}

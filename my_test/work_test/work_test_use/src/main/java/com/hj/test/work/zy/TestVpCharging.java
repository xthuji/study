package com.hj.test.work.zy;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hj.test.tools.TimeUtils;

public class TestVpCharging {

    private static final Logger logger = LoggerFactory.getLogger(TestVpCharging.class);
    
    @Test
    public void testDate() throws Exception {
        
        Date now = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(now);
        ca.add(Calendar.MINUTE, -9);
        Date targetTime = ca.getTime();
        String time = TimeUtils.formatDate(targetTime, "MMdd_HHmm");
        time = time.substring(0, time.length()-1)+"0";
        System.out.println(time);
    }
    
    @Test
    public void testcoupon() throws Exception {
        
        float rate = 0.03f;
        int num = 20;
        int yuebing = 100;
        float price = 0.01f;
        int result = (int) (price*yuebing*num*rate);
        System.out.println(result);
        System.out.println(Float.valueOf(price*yuebing).intValue());
        System.out.println((int)(price*yuebing));
        
    }
}

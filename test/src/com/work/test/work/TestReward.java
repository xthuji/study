package com.work.test.work;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestReward {

    @Test
    public void testName() throws Exception {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        final double diff = cal.getTimeInMillis() - System.currentTimeMillis();
        System.out.println(diff/86400000+" days left.");
        Date endDate = cal.getTime();
        System.out.println("endDate = " + endDate);
//        int sec = TimeUtils.getcountSeconds(startDate, endDate);
//        System.out.println(sec);
//        final Object[] params = new Object[] { 1,20};
//        System.out.println(params);
    }
}

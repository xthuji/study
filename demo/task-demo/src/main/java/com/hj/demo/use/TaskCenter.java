package com.hj.demo.use;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TaskCenter {

	public static void main(String[] args) throws Exception {

        // 初始化Spring
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "classpath:applicationContext.xml");

        // 初始化调度工厂
//        TBScheduleManagerFactory scheduleManagerFactory = new TBScheduleManagerFactory();
//
//        Properties p = new Properties();
//        p.put("zkConnectString", "localhost:2181,localhost:2182,localhost:2183");
//        p.put("zkConnectString", "localhost:2180");
//        p.put("rootPath", "/my-schedule/test");
//        p.put("zkSessionTimeout", "6000");
//        p.put("userName", "ScheduleAdmin");
//        p.put("password", "password");
//        p.put("isCheckParentPath", "true");

//        scheduleManagerFactory.setApplicationContext(ctx);
        
//        scheduleManagerFactory.init(p);  
    }
	
}

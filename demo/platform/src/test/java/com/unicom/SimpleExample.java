// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SimpleExample.java

package com.unicom;

import java.util.Date;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Referenced classes of package org.quartz.examples.example1:
//			HelloJob

public class SimpleExample {

	public SimpleExample() {
	}

	public void run() throws Exception {
		Logger log = LoggerFactory.getLogger(SimpleExample.class);
		log.info("------- Initializing ----------------------");
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		log.info("------- Initialization Complete -----------");
		Date runTime = DateBuilder.evenMinuteDate(new Date());
		log.info("------- Scheduling Job  -------------------");
		JobDetail job = JobBuilder.newJob(HelloJob.class)
				.withIdentity("job1", "group1").build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1").startAt(runTime).build();
		sched.scheduleJob(job, trigger);
		log.info((new StringBuilder()).append(job.getKey())
				.append(" will run at: ").append(runTime).toString());
		sched.start();
		log.info("------- Started Scheduler -----------------");
		log.info("------- Waiting 65 seconds... -------------");
		try {
			Thread.sleep(65000L);
		} catch (Exception e) {
		}
		log.info("------- Shutting Down ---------------------");
		sched.shutdown(true);
		log.info("------- Shutdown Complete -----------------");
	}

	public static void main(String args[]) throws Exception {
		SimpleExample example = new SimpleExample();
		example.run();
	}
}

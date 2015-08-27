// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HelloJob.java

package com.unicom;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {

	private static Logger	_log	= LoggerFactory.getLogger(HelloJob.class);

	public HelloJob() {
	}

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		_log.info((new StringBuilder()).append("Hello World! - ")
				.append(new Date()).toString());
	}

}

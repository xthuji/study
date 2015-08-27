/**
 * ScheduleTest.java
 * com.serviceTest
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 Sep 9, 2013 		qibaichao
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
/**
 * @Project:	smmm
 * @FileName	ScheduleTest.java
 * @Author  qibaichao
 * @Date	Sep 9, 2013
 */

package com.unicom;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author qibaichao
 * @ClassName ScheduleTest
 * @Date Sep 9, 2013
 * @Description:
 */
public class ScheduleTest {

	private ApplicationContext	ctx	= null;

	/**
	 * 调拨接口服务实现测试
	 * 
	 * @author chenxinquan
	 */
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "spring.xml",
				"schedule.xml", "spring-mybatis.xml" });
	}

	/**
	 * 查询数据库测试
	 */
	@Test
	public void getUserById() {

	}
}

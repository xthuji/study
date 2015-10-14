package com.letv.leAcs.worker;

import org.apache.log4j.Logger;

/**
 * @author zhaohengchong
 * @email  zhaohengchong@letv.com
 * @version 2014-5-18 上午09:38:55 
 */
public class TestJob {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	public void execute() {
        this.logger.info("DepSyncJob 定时任务 开始");
        // TODO
        this.logger.info("the testJob execute");
    }
}

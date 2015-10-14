package com.letv.simple.service.impl;

/**
 * @author zhaohengchong
 * @email  zhaohengchong@letv.com
 * @version 2014-6-20 下午12:10:15 
 */
public class InitUserData {
	
	private static InitUserData initUserData;

	private InitUserData() {
		
	}
	
	public static InitUserData getInitUserData() {
		if (initUserData == null) {
			synchronized (initUserData) {
				if (initUserData == null) {
					initUserData = new InitUserData();
				}
			}
		}
		return initUserData;
	}
}

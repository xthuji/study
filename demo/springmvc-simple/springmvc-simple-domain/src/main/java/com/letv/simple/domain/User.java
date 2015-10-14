package com.letv.simple.domain;

import java.util.Date;

/**
 * @author zhaohengchong
 * @email  zhaohengchong@letv.com
 * @version 2014-6-20 上午10:18:47 
 */
public class User {

	/**
	 * ID主键
	 */
	private Integer userId;
	
	/**
	 * userCode
	 */
	private String userCode;
	
	/**
	 * userName
	 */
	private String userName;
	
	/**
	 * 性别，0：男；1，女
	 */
	private Integer sex;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

/**
 * UserPo.java
 * com.xxx.po
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 Sep 4, 2013 		qibaichao
 *
 * Copyright (c) 2013, TNT All Rights Reserved.
 */
/**
 * @Project:	smmm
 * @FileName	UserPo.java
 * @Author  qibaichao
 * @Date	Sep 4, 2013
 */

package com.unicom.po;

/**
 * @Author qibaichao
 * @ClassName UserPo
 * @Date Sep 4, 2013
 * @Description:
 */
public class UserPo extends Po {

	private int					userId;
	private String				userName;
	private String				email;
	private String				phone;
	private String				address;
	private int					age;
	private static final long	serialVersionUID	= 3231420518385431352L;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

package com.letv.simple.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.letv.simple.domain.User;
import com.letv.simple.service.UserService;

/**
 * @author zhaohengchong
 * @email  zhaohengchong@letv.com
 * @version 2014-6-20 上午10:26:23 
 */
@Service
public class UserServiceImpl implements UserService {
	
	public static Map<Integer, User> initUserData = new HashMap<Integer, User>();
	
	public static Integer index = 1;

	public int add(User user) {
		user.setUserId(index);
		user.setCreateTime(new Date());
		initUserData.put(index, user);
		return 1;
	}

	public int delete(Integer userId) {
		initUserData.remove(userId);
		return 1;
	}

	public User select(User user) {
		if (user != null) {
			if (user.getUserId() != null) {
				user = initUserData.get(user.getUserId());
			} else {
				
			}
		}
		return user;
	}

	public Map<Integer, User> selectList(User user) {
		if (initUserData == null || initUserData.size() <= 0) {
			initUserData();
		}
		return initUserData;
	}

	public int update(User user) {
		user.setCreateTime(new Date());
		initUserData.put(user.getUserId(), user);
		return 1;
	}
	
	private void initUserData() {
		if (initUserData == null || initUserData.size() <= 0) {
			initUserData = new HashMap<Integer, User>();
			for (index = 1; index <= 5; index++) {
				User user = new User();
				user.setUserId(index);
				user.setUserCode("userCode" + index);
				user.setUserName("userName" + index);
				user.setSex(index % 2);
				user.setCreateTime(new Date());
				initUserData.put(index, user);
			}
		}
	}
}

package com.letv.simple.service;

import java.util.Map;

import com.letv.simple.domain.User;

/**
 * @author zhaohengchong
 * @email  zhaohengchong@letv.com
 * @version 2014-6-20 上午10:17:53 
 */
public interface UserService {

	/**
	 * 新增
	 * @param user
	 * @return
	 */
	int add(User user);
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	int update(User user);
	
	/**
	 * 删除
	 * @param userId
	 * @return
	 */
	int delete(Integer userId);
	
	/**
	 * 查询数据集合
	 * @param user
	 * @return
	 */
	Map<Integer, User> selectList(User user);
	
	/**
	 * 查询单条数据
	 * @param user
	 * @return
	 */
	User select(User user);
}

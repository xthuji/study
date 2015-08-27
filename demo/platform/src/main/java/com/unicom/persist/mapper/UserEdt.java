package com.unicom.persist.mapper;

import org.apache.ibatis.annotations.Param;

import com.unicom.entity.User;

public interface UserEdt {

	/**
	 * @Author qibaichao
	 * @MethodName insertUser
	 * @param u
	 * @return
	 * @Date Sep 9, 2013
	 * @Description:添加
	 */
	public int insertUser(User u);

	/**
	 * @Author qibaichao
	 * @MethodName deleteUser
	 * @param userId
	 * @return
	 * @Date Sep 9, 2013
	 * @Description: 删除
	 */
	public int deleteUser(@Param("userIds")
	int[] userIds);

	/**
	 * @Author qibaichao
	 * @MethodName updateUser
	 * @param user
	 * @return
	 * @Date Sep 9, 2013
	 * @Description:修改
	 */
	public int updateUser(User user);
}

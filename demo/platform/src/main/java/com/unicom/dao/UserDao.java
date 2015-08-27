package com.unicom.dao;

import com.unicom.core.Pager;
import com.unicom.entity.User;

public interface UserDao {

	public User selectUserById(int userId) throws Exception;

	public int insertUser(User u) throws Exception;

	/**
	 * @Title: getAddressPager
	 * @Description: 分页查询
	 * @param pager
	 * @return
	 */
	public Pager getUserPager(Pager pager) throws Exception;

	/**
	 * @Author qibaichao
	 * @MethodName deleteUser
	 * @param userIds
	 * @return
	 * @throws Exception
	 * @Date Sep 9, 2013
	 * @Description:批量删除
	 */
	public int deleteUser(int[] userIds) throws Exception;

	/**
	 * @Author qibaichao
	 * @MethodName updateUser
	 * @param user
	 * @return
	 * @throws Exception
	 * @Date Sep 9, 2013
	 * @Description:修改
	 */
	public int updateUser(User user) throws Exception;

}

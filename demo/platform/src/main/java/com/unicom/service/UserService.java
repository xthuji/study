package com.unicom.service;

import com.unicom.core.Pager;
import com.unicom.entity.User;
import com.unicom.exception.BussinessException;
import com.unicom.po.UserPo;

/**
 * @author chenxinquan
 */
public interface UserService {

	public UserPo getUserById(int userId) throws BussinessException;

	public int addUser(UserPo userPo) throws BussinessException;

	/**
	 * @Author qibaichao
	 * @MethodName getUserPager
	 * @param pager
	 * @return
	 * @throws BussinessException
	 * @Date Sep 5, 2013
	 * @Description: 分页查询
	 */
	public Pager getUserPager(Pager pager) throws BussinessException;


	/**
	 * @Author qibaichao
	 * @MethodName deleteUser
	 * @param userId
	 * @return
	 * @throws Exception
	 * @Date Sep 9, 2013
	 * @Description: 批量删除
	 */
	public void deleteUser(int[] userId) throws BussinessException;

	/**
	 * @Author qibaichao
	 * @MethodName updateUser
	 * @param user
	 * @return
	 * @throws Exception
	 * @Date Sep 9, 2013
	 * @Description:修改
	 */
	public int updateUser(UserPo userPo) throws BussinessException;

}

package com.unicom.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.unicom.core.Pager;
import com.unicom.dao.UserDao;
import com.unicom.entity.User;
import com.unicom.exception.BussinessException;
import com.unicom.po.UserPo;
import com.unicom.service.UserService;

/**
 * @Author qibaichao
 * @ClassName UserServiceImpl
 * @Date Sep 4, 2013
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

	private static Logger	log	= Logger.getLogger(UserServiceImpl.class);

	@Resource
	private UserDao			userDao;

	/**
	 * @Author qibaichao
	 * @MethodName getUserById
	 * @param userId
	 * @return
	 * @throws BussinessException
	 * @Date Sep 4, 2013
	 * @Description:
	 */
	@Override
	public UserPo getUserById(int userId) throws BussinessException {
		UserPo userPo = null;
		try {
			User u = userDao.selectUserById(userId);
			if (u != null) {
				userPo = new UserPo();
				BeanUtils.copyProperties(u, userPo);
			}
		} catch (Exception e) {
			log.error(e);
			throw new BussinessException(e);
		}

		return userPo;
	}

	/**
	 * @Author qibaichao
	 * @MethodName addUser
	 * @param userPo
	 * @return
	 * @throws BussinessException
	 * @Date Sep 4, 2013
	 * @Description:
	 */
	@Override
	public int addUser(UserPo userPo) throws BussinessException {
		int count = 0;
		User user = null;
		try {
			if (userPo != null) {
				user = new User();
				BeanUtils.copyProperties(userPo, user);
				count = userDao.insertUser(user);
			}
		} catch (Exception e) {
			log.error(e);
			throw new BussinessException(e);
		}
		return count;

	}

	/**
	 * @Author qibaichao
	 * @MethodName getUserPager
	 * @param pager
	 * @return
	 * @throws BussinessException
	 * @Date Sep 5, 2013
	 * @Description:
	 */

	@Override
	public Pager getUserPager(Pager pager) throws BussinessException {

		log.info("getUserPager select page method");
		try {
			pager = this.userDao.getUserPager(pager);
		} catch (Exception e) {
			log.error(e);
			throw new BussinessException(e);
		}
		return pager;

	}

	/**
	 * @Author qibaichao
	 * @MethodName deleteUser
	 * @param userId
	 * @return
	 * @throws BussinessException
	 * @Date Sep 9, 2013
	 * @Description:
	 */

	@Override
	public void deleteUser(int[] userIds) throws BussinessException {
		try {
			this.userDao.deleteUser(userIds);
		} catch (Exception e) {
			log.error(e);
			throw new BussinessException(e);
		}

	}

	/**
	 * @Author qibaichao
	 * @MethodName updateUser
	 * @param user
	 * @return
	 * @throws BussinessException
	 * @Date Sep 9, 2013
	 * @Description:
	 */

	@Override
	public int updateUser(UserPo userPo) throws BussinessException {
		int num = 0;
		User user = null;
		try {
			if (userPo != null) {
				user = new User();
				BeanUtils.copyProperties(userPo, user);
				num = this.userDao.updateUser(user);
			}
		} catch (Exception e) {
			log.error(e);
			throw new BussinessException(e);
		}
		return num;

	}

}

package com.unicom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.unicom.core.Pager;
import com.unicom.dao.UserDao;
import com.unicom.entity.User;
import com.unicom.persist.mapper.UserEdt;
import com.unicom.persist.mapper.UserSer;
import com.unicom.po.UserPo;

@Service
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Resource
	private UserSer	userSer;
	@Resource
	private UserEdt	userEdt;

	@Override
	public User selectUserById(int userId) throws Exception {
		User u = userSer.selectUserById(userId);
		return u;
	}

	@Override
	public int insertUser(User u) throws Exception {
		return userEdt.insertUser(u);
	}

	/**
	 * @Author qibaichao
	 * @MethodName getUserPager
	 * @param pager
	 * @return
	 * @throws Exception
	 * @Date Sep 5, 2013
	 * @Description:
	 */

	@Override
	public Pager getUserPager(Pager pager) throws Exception {

		List<User> resultList = null;
		List<UserPo> poList = new ArrayList<UserPo>();
		UserPo po = null;
		int totalCount = this.userSer.selectCount(pager);
		pager.resetTotalCount(totalCount);
		if (totalCount > 0) {// 如果总条数为0，不查询操作
			resultList = this.userSer.selectUserList(pager);
			for (User user : resultList) {
				po = new UserPo();
				BeanUtils.copyProperties(user, po);
				poList.add(po);
			}
		}
		pager.setTotalCount(totalCount);
		// 将entity 转换成po
		pager.setResultList(poList);
		return pager;

	}

	/**
	 * @Author qibaichao
	 * @MethodName deleteUser
	 * @param userIds
	 * @return
	 * @throws Exception
	 * @Date Sep 9, 2013
	 * @Description:
	 */

	@Override
	public int deleteUser(int[] userIds) throws Exception {

		return this.userEdt.deleteUser(userIds);

	}

	/**
	 * @Author qibaichao
	 * @MethodName updateUser
	 * @param user
	 * @return
	 * @throws Exception
	 * @Date Sep 9, 2013
	 * @Description:
	 */

	@Override
	public int updateUser(User user) throws Exception {

		return this.userEdt.updateUser(user);

	}

}

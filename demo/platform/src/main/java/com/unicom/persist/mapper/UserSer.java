package com.unicom.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.unicom.core.Pager;
import com.unicom.entity.User;

public interface UserSer {

	public User selectUserById(int userId);

	/**
	 * @Author qibaichao
	 * @MethodName selectCount
	 * @param pager
	 * @return
	 * @Date Sep 5, 2013
	 * @Description: 分页总数查询
	 */
	public int selectCount(Pager pager);

	/**
	 * @Author qibaichao
	 * @MethodName selectUserList
	 * @param pager
	 * @return
	 * @Date Sep 5, 2013
	 * @Description: 分页查询
	 */
	public List<User> selectUserList(@Param("pager")
	Pager pager);

}

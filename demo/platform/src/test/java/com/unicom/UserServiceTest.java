package com.unicom;

import java.util.Random;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unicom.po.UserPo;
import com.unicom.service.UserService;

public class UserServiceTest {

	private ApplicationContext	ctx			= null;

	@Resource
	private UserService			userService	= null;

	/**
	 * 调拨接口服务实现测试
	 * 
	 * @author chenxinquan
	 */
	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext(new String[] { "spring.xml",
				"spring-mybatis.xml" });
		userService = ctx.getBean("userServiceImpl", UserService.class);
	}

	/**
	 * 查询数据库测试
	 */
	@Test
	public void getUserById() {

		try {
			UserPo u = userService.getUserById(1);
			System.out.println(" userName = " + u.getUserName());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 插入数据库测试
	 */
	@Test
	public void addUser() {
		try {
			UserPo u = new UserPo();
			u.setUserName("ceshi" + new Random().nextInt(1000));
			int count = userService.addUser(u);
			System.out.println("*********** " + count);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * @Author qibaichao
	 * @MethodName testUpdate
	 * @Date Sep 9, 2013
	 * @Description:测试修改
	 */
	@Test
	public void testUpdate() {
		int num = 0;
		try {
			UserPo userPo = new UserPo();
			userPo.setUserId(2);
			userPo.setUserName("qibaichao");
			num = this.userService.updateUser(userPo);
			System.out.println("num:----" + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			int[] userId = { 20, 30 };
			this.userService.deleteUser(userId);
			System.out.println("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * service事物控制测试
	 */
	// @Test
	// public void transactionTest() {
	// User u = new User();
	// u.setUserName("ceshi" + new Random().nextInt(1000));
	// int count = userService.addUser(u);
	// System.out.println("*********** " + count);
	// }

}

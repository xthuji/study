package com.unicom.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.unicom.core.Pager;
import com.unicom.core.ResultBean;
import com.unicom.po.UserPo;
import com.unicom.service.UserService;

@Controller
@RequestMapping("/testUser")
public class UserController extends BaseController {

	@Resource
	public UserService	userService;

	/**
	 * 页面转向
	 * 
	 * @author qibaichao
	 * @param url
	 *            参数url
	 * @return String
	 */
	@RequestMapping("/index_{url}")
	public String index(@PathVariable
	String url, HttpServletRequest request) {
		request.setAttribute("forward", url);
		return "index";
	}

	@RequestMapping("/showUserAdd")
	public String showUserAdd() {
		return "testUser/userAdd";
	}

	/**
	 * @Author qibaichao
	 * @MethodName saveUser
	 * @param u
	 * @return
	 * @Date Sep 9, 2013
	 * @Description:添加用户
	 */
	@RequestMapping("/userAdd")
	@ResponseBody
	public ResultBean userAdd(UserPo u) {
		ModelAndView modelAndView = new ModelAndView();
		ResultBean resultBean = new ResultBean();
		try {
			userService.addUser(u);
			resultBean.success();
		} catch (Exception e) {
			// 转向到错误页面
			modelAndView.setViewName("error/error");
			modelAndView.addObject("errors", e);
		}
		return resultBean;
	}

	/**
	 * @Author qibaichao
	 * @MethodName saveUser
	 * @param u
	 * @return
	 * @Date Sep 9, 2013
	 * @Description:删除用户
	 */
	@RequestMapping("/userDelete")
	@ResponseBody
	public ResultBean userDelete(int[] ids) {
		ResultBean resultBean = new ResultBean();
		ModelAndView modelAndView = new ModelAndView();
		try {
			this.userService.deleteUser(ids);
			resultBean.success();
		} catch (Exception e) {
			// 转向到错误页面
			modelAndView.setViewName("error/error");
			modelAndView.addObject("errors", e);
		}
		return resultBean;
	}

	/**
	 * @Author qibaichao
	 * @MethodName showUserUpdate
	 * @param param
	 * @return
	 * @Date Sep 10, 2013
	 * @Description: 显示修改页面
	 */
	@RequestMapping("/showUserModify")
	public ModelAndView showUserUpdate(int param) {
		UserPo userPo = null;
		ModelAndView modelAndView = new ModelAndView("testUser/userModify");
		try {
			userPo = this.userService.getUserById(param);
			modelAndView.addObject("userPo", userPo);
		} catch (Exception e) {
			// 转向到错误页面
			modelAndView.setViewName("error/error");
			modelAndView.addObject("errors", e);
		}
		return modelAndView;
	}

	/**
	 * @Author qibaichao
	 * @MethodName userUpdate
	 * @param userPo
	 * @return
	 * @Date Sep 10, 2013
	 * @Description:修改
	 */
	@RequestMapping("/userModify")
	@ResponseBody
	public ResultBean userModify(UserPo userPo) {
		ResultBean resultBean = new ResultBean();
		ModelAndView modelAndView = new ModelAndView();
		try {
			this.userService.updateUser(userPo);
			resultBean.success();
		} catch (Exception e) {
			// 转向到错误页面
			modelAndView.setViewName("error/error");
			modelAndView.addObject("errors", e);
		}
		return resultBean;
	}

	@RequestMapping("/userLook")
	public ModelAndView userLook(int param) {
		UserPo userPo = null;
		ModelAndView modelAndView = new ModelAndView("testUser/userLook");
		try {
			userPo = this.userService.getUserById(param);
			modelAndView.addObject("userPo", userPo);
		} catch (Exception e) {
			// 转向到错误页面
			modelAndView.setViewName("error/error");
			modelAndView.addObject("errors", e);
		}
		return modelAndView;
	}

	/**
	 * @Author qibaichao
	 * @MethodName queryUser
	 * @param pager
	 * @return
	 * @Date Sep 5, 2013
	 * @Description: 分页查询
	 */
	@RequestMapping("/userQuery")
	public ModelAndView userQuery(Pager pager) {

		ModelAndView modelAndView = new ModelAndView("testUser/userListQuery");
		try {
			pager = this.userService.getUserPager(pager);
			// int i = 1 / 0;
			modelAndView.addObject("pager", pager);
		} catch (Exception e) {
			// 转向到错误页面
			modelAndView.setViewName("error/error");
			modelAndView.addObject("errors", e);
		}
		return modelAndView;

	}
}

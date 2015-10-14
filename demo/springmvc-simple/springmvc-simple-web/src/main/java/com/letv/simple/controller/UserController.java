package com.letv.simple.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letv.common.utils.wrap.WrapMapper;
import com.letv.common.utils.wrap.Wrapper;
import com.letv.simple.domain.User;
import com.letv.simple.service.UserService;
import com.letv.simple.util.Constants;

/**
 * @author zhaohengchong
 * @email  zhaohengchong@letv.com
 * @version 2014-4-21 下午08:56:07 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(User user, Model model) {
        this.logger.debug("UserController --> list");
        try {
        	Map<Integer, User> userMap = this.userService.selectList(user);
        	model.addAttribute("userMap", userMap);
		} catch (Exception e) {
			this.logger.error("UserController --> list selectList ERROR:", e);
		}
		model.addAttribute("user", user);
		model.addAttribute("sexMap", Constants.getSexMap());
//		model.addAttribute("others", others);
        return "user/list";
    }
    
    @RequestMapping(value = "/addOrUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public String addOrUpdate(User user, Model model) {
    	this.logger.debug("UserController --> addOrUpdate");
    	try {
    		if (user != null && user.getUserId() != null && !user.getUserId().equals(0)) {
    			user = this.userService.select(user);
    		}
    		model.addAttribute("user", user);
    	} catch (Exception e) {
    		this.logger.error("UserController --> addOrUpdate ERROR:", e);
    	}
    	model.addAttribute("sexMap", Constants.getSexMap());
    	return "user/addOrUpdate";
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Wrapper<User> save(User user, Model model) {
    	this.logger.debug("UserController --> save");
    	try {
			// TODO
    		// checkParams
		} catch (Exception e) {
			this.logger.error("UserController --> save PARAM ERROR:", e);
			return new Wrapper<User>(Wrapper.ILLEGAL_ARGUMENT_CODE_, Wrapper.ILLEGAL_ARGUMENT_MESSAGE);
		}
		try {
			int result = 0;
			if (user.getUserId() == null || user.getUserId().equals(0)) {
				result = this.userService.add(user);
			} else {
				result = this.userService.update(user);
			}
			//TODO check result
			System.out.println(result);
			return WrapMapper.ok();
		} catch (Exception e) {
			this.logger.error("UserController --> save ERROR:", e);
			return new Wrapper<User>(Wrapper.ILLEGAL_ARGUMENT_CODE_, Wrapper.ILLEGAL_ARGUMENT_MESSAGE);
		}
    }
    
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Wrapper<User> delete(@RequestBody Map<String, String> paramMap, Model model) {
    	this.logger.debug("UserController --> delete");
    	try {
			Boolean checkParamOK = Boolean.FALSE;
			String userId = null;
			if (paramMap == null || paramMap.isEmpty()) {
				checkParamOK = Boolean.TRUE;
			} else {
				userId = paramMap.get("userId");
				if (StringUtils.isBlank(userId)) {
					checkParamOK = Boolean.TRUE;
				}
			}
			
			if (checkParamOK) {
				this.logger.info("UserController --> delete 传入参数有误！");
				return new Wrapper<User>(Wrapper.ILLEGAL_ARGUMENT_CODE_, Wrapper.ILLEGAL_ARGUMENT_MESSAGE);
			}
		
			int result = this.userService.delete(Integer.valueOf(userId));
			
			if (result > 0) {
				this.logger.info("UserController --> delete 根据userId["+ userId + "]删除成功");
				return WrapMapper.ok();
			} else {
				this.logger.info("UserController --> delete 根据userId【" + userId + "】删除失败");
				return WrapMapper.error();
			}
			
		} catch (Exception e) {
			this.logger.error("UserController --> delete 删除 异常：", e);
			return WrapMapper.error();
		}
    }
    
}

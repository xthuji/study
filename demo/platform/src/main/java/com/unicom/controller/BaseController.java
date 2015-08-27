package com.unicom.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.unicom.controller.core.DateTypeEditor;
import com.unicom.controller.core.IntegerEditor;

public class BaseController {

	/**
	 * @Author qibaichao
	 * @MethodName initBinder
	 * @param request
	 * @param binder
	 * @throws Exception
	 * @Date Sep 5, 2013
	 * @Description: 类型转换
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new DateTypeEditor());
		binder.registerCustomEditor(int.class, new IntegerEditor());

	}
}

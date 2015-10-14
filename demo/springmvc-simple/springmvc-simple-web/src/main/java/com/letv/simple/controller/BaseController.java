package com.letv.simple.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.letv.common.utils.DateHelper;
import com.letv.common.utils.config.PropertiesHelper;

public class BaseController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String DATE_TIME_FORMAT_START = "yyyy-MM-dd 00:00:00";
	private static final String DATE_TIME_FORMAT_END = "yyyy-MM-dd 23:59:59";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	protected void initDateTimePicker(Model model) {
		Date date = new Date();
		String startTime = DateHelper.format(date, DATE_TIME_FORMAT_START);
		model.addAttribute("startTime", startTime);
		String endTime = DateHelper.format(date, DATE_TIME_FORMAT_END);
		model.addAttribute("endTime", endTime);
	}
	
	protected Boolean hasAuth(String operatorAuth) {
		// 根据手机号查询是否成功
		boolean hasAuth = Boolean.FALSE;
		if (StringUtils.isNotBlank(operatorAuth)) {
			try {
				String operatorStr = PropertiesHelper.newInstance().getValue("operator");
				if (StringUtils.isNotBlank(operatorStr)) {
					String[] operators = operatorStr.split(",");
					for (String operator : operators) {
						if (operatorAuth.equals(operator)) {
							hasAuth = Boolean.TRUE;
							break;
						}
					}
				}
			} catch (Exception e) {
			}
		}
		return hasAuth;
	}
}
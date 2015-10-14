package com.letv.simple.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhaohengchong
 * @email  zhaohengchong@letv.com
 * @version 2014-4-21 下午08:56:07 
 */
@Controller
public class IndexController extends BaseController {

	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public String index() {
		this.logger.info("index");
		return "index";
	}
	
    @RequestMapping(value = "/top", method = RequestMethod.GET)
    public String top(Model model) {
        this.logger.debug("IndexController --> top");
        return "topFrame";
    }
    
    @RequestMapping(value = "/left", method = RequestMethod.GET)
    public String left() {
        this.logger.debug("IndexController --> left");
        return "leftFrame";
    }
}

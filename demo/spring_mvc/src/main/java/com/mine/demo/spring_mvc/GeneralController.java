package com.mine.demo.spring_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/helloworld")
public class GeneralController {

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public ModelAndView hello() {//访问http://127.0.0.1:8080/spring_mvc/helloworld/hello.do
		ModelAndView mv = new ModelAndView();
		mv.setViewName("helloworld");
		return mv;
	}
}

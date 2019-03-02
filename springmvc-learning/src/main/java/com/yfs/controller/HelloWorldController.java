package com.yfs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@RequestMapping("/")
	public ModelAndView hw() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", "卧槽，尼玛！");
		modelAndView.setViewName("welcome");
		return modelAndView;
	}

}

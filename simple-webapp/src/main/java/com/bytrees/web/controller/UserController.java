package com.bytrees.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/user")
public class UserController {
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/index");
		return modelAndView;
	}

	@RequestMapping(method=RequestMethod.GET, value="/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/login");
		return modelAndView;
	}
}

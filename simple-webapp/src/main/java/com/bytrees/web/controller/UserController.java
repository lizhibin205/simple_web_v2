package com.bytrees.web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/user")
public class UserController {
	/**
	 * 用户主页
	 * @param authentication
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView index(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName", authentication.getName());
		modelAndView.addObject("authorityList", authentication.getAuthorities());
		modelAndView.setViewName("user/index");
		return modelAndView;
	}

	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/login");
		return modelAndView;
	}

	
}

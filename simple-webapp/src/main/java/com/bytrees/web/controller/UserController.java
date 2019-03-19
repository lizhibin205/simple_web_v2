package com.bytrees.web.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bytrees.service.entity.Users;
import com.bytrees.service.repository.UsersRepository;


@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UsersRepository userRepository;

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

	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/register")
    public ModelAndView register() {
    	ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/register");
		return modelAndView;
    }

	/**
	 * 注册实现
	 */
	@RequestMapping(method=RequestMethod.POST, value="/register")
    public void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        if (username == null || username.length() < 6) {
        	response.sendRedirect("/user/register?error=1");
        	return ;
        }
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (password1 == null || password2 == null || !password1.equals(password2) || password1.length() < 6) {
        	response.sendRedirect("/user/register?error=2");
        	return ;
        }
        Users user = new Users();
        user.setName(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password1));
        userRepository.save(user);
        response.sendRedirect("/user/login?register");
    }
}

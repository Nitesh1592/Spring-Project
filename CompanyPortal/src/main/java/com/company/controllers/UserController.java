package com.company.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.company.entity.Employee;
import com.company.services.UserLogin;

@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	public UserLogin userLogin;
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		//logs debug message
		logger.info("Enter into login method");
		ModelAndView mav = new ModelAndView("login");
		HttpSession session = request.getSession();
		if (null != session.getAttribute("isLoggedin") && true == (Boolean) session.getAttribute("isLoggedin")) {
			System.out.println("Session data : " + session);
			mav.setViewName("redirect:dashboard");
		}
		
		mav.addObject("employee", new Employee());
		logger.info(mav);
		return mav;
	}
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.POST)
	public ModelAndView doLogin(@Email @Valid @RequestParam(value="email", required=true) String email,
			@RequestParam(value="password", required=true) String password, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Enter into doLogin method");
		ModelAndView mav = new ModelAndView();
		if (userLogin.doLogin(email, password, request)) {
			mav.setViewName("redirect:dashboard");
		} else {
			mav.addObject("employee", new Employee());
			mav.setViewName("login");
		}
		
		logger.info("Enter into doLogin method");
		return mav;
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response)
	{
		logger.info("dashboard() method start");
		ModelAndView mav = new ModelAndView("dashboard");
		HttpSession session = request.getSession();

		if (null == session.getAttribute("isLoggedin")) {
			mav.setViewName("redirect:login");
		}
		
		
		logger.info("dashboard() method end");
		return mav;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
	{
		logger.info("logout method start");
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView mav = new ModelAndView("redirect:/");
		
		logger.info("logout method end");
		return mav;
	}
}

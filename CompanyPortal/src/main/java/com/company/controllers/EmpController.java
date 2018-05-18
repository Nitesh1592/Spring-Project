package com.company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmpController {
	
	@RequestMapping(value="emp-list", method = RequestMethod.GET)
	public ModelAndView empList() {
		ModelAndView mav = new ModelAndView("emp_list");
		return mav;
	}
}

package com.company.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.controllers.UserController;
import com.company.dao.UserLoginDao;
import com.company.entity.Employee;

@Service
public class UserLoginServiceImpl implements UserLogin {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
    private UserLoginDao loginDao;
	
	
	public boolean doLogin(String email, String password, HttpServletRequest request) {
		logger.info("UserLoginServiceImpl is called for doLogin() method");
		Employee empDetail = loginDao.doLogin(email, password);
		HttpSession session = request.getSession();
		boolean isLoggedin = false;
		if (empDetail != null) {
			isLoggedin = true;
			session.setAttribute("isLoggedin",isLoggedin);
			session.setAttribute("empDetail",empDetail);
			session.setAttribute("roleName", empDetail.getRole());
		}
		logger.info("UserLoginServiceImpl is called for doLogin() method end");
		return isLoggedin;
	}

}

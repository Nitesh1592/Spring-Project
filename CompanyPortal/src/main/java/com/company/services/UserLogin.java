package com.company.services;

import javax.servlet.http.HttpServletRequest;

public interface UserLogin {
	
	public boolean doLogin(String email, String password, HttpServletRequest request);
}

package com.company.dao;

import com.company.entity.Employee;

public interface UserLoginDao {
	public Employee doLogin(String email, String password);
}

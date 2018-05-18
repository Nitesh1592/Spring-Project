package com.company.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.controllers.UserController;
import com.company.entity.Employee;

@Repository
@Transactional
public class UserLoginDaoImpl implements UserLoginDao {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Employee doLogin(String email, String password) {
		logger.info("UserLoginDaoImpl() method called");
		Employee emp = null;
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Employee> cquery = cb.createQuery(Employee.class);
		Root<Employee> root = cquery.from(Employee.class);
		try {
			cquery.select(root);
			cquery.where(cb.and(
					cb.equal(root.get("email"), email),
					cb.equal(root.get("password"), password)
			));
			
			emp = session.createQuery(cquery).getSingleResult();
		} catch(Exception e) {
			logger.info("No resultr exception occurred");
		}
		
		return emp;
	}

}

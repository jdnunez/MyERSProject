package com.revature.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.ImpUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.User;

public class ImpUserServices implements UserServices {
	
	private static Logger logger = LoggerFactory.getLogger(ImpUserServices.class);
	
	private static UserDAO userDAO;
	
	public ImpUserServices() {
		userDAO = new ImpUserDAO();
	}

	@Override
	public boolean registerUser(User user) {
		// Logging... 
		logger.info("ImpUserServices::registerUser() called. Creating new user...");
		int userId = userDAO.create(user);
		// Logging...
		logger.info("Received from DAO. New user id: " + userId);
		return (userId != 0) ? true : false;
	}

	@Override
	public boolean login(String username, String password) {
		User target = userDAO.getByUsername(username);
		if(target.getUsername().equalsIgnoreCase(username) && target.getPassword().equalsIgnoreCase(password)) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

}

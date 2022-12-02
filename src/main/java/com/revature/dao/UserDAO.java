package com.revature.dao;

import com.revature.models.User;

public interface UserDAO {
	// create
	int create(User user);
	
	// read
	User getByUserId(int userId);
	User getByUsername(String username);
	
	// update
	boolean update(User user);
	
	// delete
	boolean deleteByUserId(int userId);
}

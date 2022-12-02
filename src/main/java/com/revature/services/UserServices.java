package com.revature.services;

import com.revature.models.User;

public interface UserServices {
	
	public boolean registerUser(User user);
	
	public boolean login(String username, String password);
	
	public User getUserById(int userId);
	
	public User getUserByUsername(String username);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(int userId);
	
}
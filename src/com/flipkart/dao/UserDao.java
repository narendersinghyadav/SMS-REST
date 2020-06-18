package com.flipkart.dao;

import com.flipkart.model.User;

//User data access object
public interface UserDao {
	
	//Add user in user table
	public boolean addUser(User user);
	
	//Delete user from user table
	public boolean deleteUser(User user);
	
	//Get password of a username
	public User getPasswordByUsername(String username);
}

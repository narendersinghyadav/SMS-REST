package com.flipkart.service;

import org.apache.log4j.Logger;

import com.flipkart.dao.UserDaoImpl;
import com.flipkart.exception.IncorrectPasswordException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.model.User;

//Check for valid authentication
public class CheckIdentity {
	//Logger
	UserDaoImpl userdao=new UserDaoImpl();
	Logger logger=Logger.getLogger(CheckIdentity.class);

	//check credentials
	public int checkIdentity(String username,String password) throws IncorrectPasswordException, UserNotFoundException {
		User user=userdao.getPasswordByUsername(username);
		//User not found
		if(user.getPassword().equals("")) {
			throw new UserNotFoundException(username);
		}
		//valid user
		else if(user.getPassword().equals(password)) {
			return user.getRole();
		}
		else {
			//incorrect password
			throw new IncorrectPasswordException(username);

		}

	}
}

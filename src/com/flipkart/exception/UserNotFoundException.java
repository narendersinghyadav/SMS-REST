package com.flipkart.exception;

//User not found exception
public class UserNotFoundException extends Exception{
	String username;
	public UserNotFoundException(String username){
		this.username=username;
	}
	public String getMessage() {
		return username+" User not found";
	}
}

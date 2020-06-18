package com.flipkart.exception;

//Incorrect Password exception
public class IncorrectPasswordException extends Exception{
	String username;
	public IncorrectPasswordException(String username){
		this.username=username;
	}
	public String getMessage() {
		return username+" password is not correct";
	}
}

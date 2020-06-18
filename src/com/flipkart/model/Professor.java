package com.flipkart.model;

//Professor model class inherit user class
public class Professor extends User{
	private String professorName;
	private String professorAddress;
	private String professorMobilenumber;
	
	//Professor constructor
	public Professor(String username,String password,String professorName,String professorAddress,String professorMobilenumber) {
		super(username,password,2);
		this.professorName=professorName;
		this.professorAddress=professorAddress;
		this.professorMobilenumber=professorMobilenumber;
	}
	public Professor() {
		
	}
	public Professor(String username) {
		super(username,"");
	}
	public String getProfessorName() {
		return professorName;
	}
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	public String getProfessorAddress() {
		return professorAddress;
	}
	public void setProfessorAddress(String professorAddress) {
		this.professorAddress = professorAddress;
	}
	public String getProfessorMobilenumber() {
		return professorMobilenumber;
	}
	public void setProfessorMobilenumber(String professorMobilenumber) {
		this.professorMobilenumber = professorMobilenumber;
	}
}

package com.flipkart.model;

//Admin model class
public class Admin extends User{
	//Properties
	private String adminName;
	private String adminAddress;
	private String adminMobilenumber;

	//Constructor
	public Admin(String username,String password,String adminName,String adminAddress,String adminMobilenumber) {
		super(username,password);
		this.adminName=adminName;
		this.adminAddress=adminAddress;
		this.adminMobilenumber=adminMobilenumber;
	}
	
	//Getter and Setter
	public Admin(String username) {
		super(username,"");
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminAddress() {
		return adminAddress;
	}
	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}
	public String getAdminMobilenumber() {
		return adminMobilenumber;
	}
	public void setAdminMobilenumber(String adminMobilenumber) {
		this.adminMobilenumber = adminMobilenumber;
	}
}

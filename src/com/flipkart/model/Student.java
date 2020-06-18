package com.flipkart.model;
//Student class inherit user class
public class Student extends User{

	private String studentName;
	private String studentAddress;
	private String studentYear;
	private String studentMobilenumber;
	private String gender;


	public Student(String username,String password,String studentName,String studentAddress,String studentYear,String studentMobilenumber,String gender) {
		super(username,password,3);
		this.studentName=studentName;
		this.studentAddress=studentAddress;
		this.studentYear=studentYear;
		this.studentMobilenumber=studentMobilenumber;
		this.gender=gender;
		
	}
	public Student() {
		
	}
	public Student(String username) {
		super(username,"");
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getStudentname() {
		return studentName;
	}
	public void setStudentname(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentaddress() {
		return studentAddress;
	}
	public void setStudentaddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public String getStudentyear() {
		return studentYear;
	}
	public void setStudentyear(String studentYear) {
		this.studentYear = studentYear;
	}
	public String getStudentmobilenumber() {
		return studentMobilenumber;
	}
	public void setStudentmobilenumber(String studentMobilenumber) {
		this.studentMobilenumber = studentMobilenumber;
	}

}

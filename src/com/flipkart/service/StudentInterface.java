package com.flipkart.service;

import java.util.HashMap;

import com.flipkart.model.Student;

//Functionality of student operations
public interface StudentInterface {
	//add 4 courses with respective course id's
	public boolean addCourses(Student student,int courseid1,int courseid2,int courseid3,int courseid4);
	
	//Change courses
	public void changeCourse(Student student,int courseid1,int courseid2,int courseid3,int courseid4);
	
	//View selected courses
	public void viewSelectedCourses(String username);
	
	//view Catalog
	public void viewCatalog();
	
	//View grades if given by professor
	public HashMap<Integer,Integer> viewGrade(Student student);
	
	//pay fees
	public void payFees(String paymentmode,Student student);
	
	//get own details 
	public Student getStudentDetails(String username,String password);
}

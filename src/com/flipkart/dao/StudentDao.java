package com.flipkart.dao;


import java.util.HashMap;
import java.util.List;

import com.flipkart.exception.FullCourseNotification;
import com.flipkart.model.Student;

//Student data access object class
public interface StudentDao {
	//get student details
	public Student getStudentInfo(String username,String password);
	//add course for registration
	public boolean addCourse(Student student,int courseid1,int courseid2,int courseid3,int courseid4) throws FullCourseNotification;
	
	//update course details
	public boolean updateCourse(Student student,int courseid1,int courseid2,int courseid3,int courseid4) throws FullCourseNotification;
	
	//List of selected courses
	public List<Integer> listSelectedCourses(String username);
	
	//List of grades
	public HashMap<Integer,Integer> listGrade(Student student);
	
	//Pay fees
	public void payFees(Student student,String paymentmode);
}

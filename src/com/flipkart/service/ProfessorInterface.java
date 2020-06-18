package com.flipkart.service;

import java.util.List;

import com.flipkart.model.Course;
import com.flipkart.model.Student;

//Functionality by professor
public interface ProfessorInterface {
	
	//View enrolled students in a particular course
	public List<Student> viewEnrolledStudents(int courseid);
	//Upload grades
	public void updateGrade(int grade,Student student,int courseid);
	//Choose course for teaching
	public void chooseCourse(String professorusername,int courseid);
	//View choosed courses for teaching
	public List<Course> viewSelectedCourse(String username);
}

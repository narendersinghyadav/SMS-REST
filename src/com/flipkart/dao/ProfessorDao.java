package com.flipkart.dao;

import java.util.List;

import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

//ProfessorDao interface
public interface ProfessorDao {
	//List of enrolled student in a course
	public List<Student> listEnrolledStudents(int courseid);
	//Upload grade
	public boolean addGrade(int grade,Student student,int courseid);
	//Choose course for teaching
	public boolean chooseCourse(String professor,int courseid);
	//List selected courses
	public List<Course> viewSelectedCourses(String username);
}

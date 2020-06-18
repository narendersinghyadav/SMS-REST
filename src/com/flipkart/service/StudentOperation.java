package com.flipkart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.dao.CatalogDao;
import com.flipkart.dao.CatalogDaoImpl;
import com.flipkart.dao.StudentDao;
import com.flipkart.dao.StudentDaoImpl;
import com.flipkart.exception.FullCourseNotification;
import com.flipkart.model.Course;
import com.flipkart.model.Student;

//Operation by students
public class StudentOperation implements  StudentInterface{
	private Logger logger=Logger.getLogger(StudentOperation.class);
	StudentDao studentdao =new StudentDaoImpl();
	
	//Add course during registration
	@Override
	public boolean addCourses(Student student,int courseid1, int courseid2, int courseid3, int courseid4) {
		
		//Fetch course id of all courses present in catalog
		CatalogDao coursedao=new CatalogDaoImpl();
		List<Course> courselist=coursedao.getCatalog();
		List<Integer> courseidlist=new ArrayList<Integer>();
		courselist.forEach(course->courseidlist.add(course.getCourseId()));
		
		//If any course id is not present in catalog
		try {
		if(!(courseidlist.contains(courseid1) && courseidlist.contains(courseid2) && courseidlist.contains(courseid3)&& courseidlist.contains(courseid4))){
			logger.error("Invalid course id");
			return false;
		}
		//Add course
		else if(studentdao.addCourse(student, courseid1, courseid2, courseid3, courseid4)) {
			logger.info("Courses successfully added");
		}
		else {
			logger.error("You have already choosen.If want can update");
			return false;
		}
		}catch (FullCourseNotification e) {
			e.displayMessage();
		}
		return true;
	}

	//Update courses registered by student
	@Override
	public void changeCourse(Student student,int courseid1,int courseid2,int courseid3,int courseid4) {
		
		//Fetch course id of all courses present in catalog
				CatalogDao coursedao=new CatalogDaoImpl();
				List<Course> courselist=coursedao.getCatalog();
				List<Integer> courseidlist=new ArrayList<Integer>();
				courselist.forEach(course->courseidlist.add(course.getCourseId()));
				
		try {
			if(!(courseidlist.contains(courseid1) && courseidlist.contains(courseid2) && courseidlist.contains(courseid3)&& courseidlist.contains(courseid4))){
				logger.error("Invalid course id");
			}
			else if(studentdao.updateCourse(student, courseid1, courseid2, courseid3, courseid4)) {
				logger.info("Courses successfully updated");
			}
			else {
				logger.error("Please update again.You have not added courses");
			}
		} catch (FullCourseNotification e) {
			e.displayMessage();
		}
	}

	//View catalog of courses
	@Override
	public void viewCatalog() {
		//List all courses
		CatalogDao catalog =new CatalogDaoImpl();
		List<Course> list=catalog.getCatalog();
		logger.info(String.format("%1$10s %2$10s %3$10s %4$10s %5$10s","Course Id","Course Name","Course Schedule","Fees","Catalog id"));
		list.forEach(course->logger.info(String.format("%1$10s %2$10s %3$10s %4$20s %5$10s",course.getCourseId(),course.getCourseName(),course.getCourseSchedule(),course.getFees(),course.getCatalogid())));

	}


	//View grades given by professor
	@Override
	public HashMap<Integer,Integer> viewGrade(Student student) {
		StudentDaoImpl studentdao=new StudentDaoImpl();
		HashMap<Integer,Integer> grades=studentdao.listGrade(student);
		logger.info(String.format("%1$10s %2$10s","Course ID","Course Grade"));
		//Show course id with grades
		grades.forEach((id,grade)->logger.info(String.format("%1$10s %2$10s",id,grade)));
		return grades;
	}

	//Pay fees
	@Override
	public void payFees(String paymentmode,Student student) {
		StudentDaoImpl studentdao=new StudentDaoImpl();
		studentdao.payFees(student,paymentmode);
		logger.info("fees payment complete");
	}
	
	//Get own details
	public Student getStudentDetails(String username,String password) {
		logger.info("Student details are fetched");
		return studentdao.getStudentInfo(username, password);
	}

	//View added courses for registration
	@Override
	public void viewSelectedCourses(String username) {
		
		//List of courses added
		List<Integer> courseidlist=studentdao.listSelectedCourses(username);
		logger.info("Course Id of selected courses");
		logger.info(courseidlist);
		
	}

}

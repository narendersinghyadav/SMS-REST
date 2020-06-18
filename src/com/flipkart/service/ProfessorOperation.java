package com.flipkart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.dao.CatalogDao;
import com.flipkart.dao.CatalogDaoImpl;
import com.flipkart.dao.ProfessorDao;
import com.flipkart.dao.ProfessorDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.Student;

//Operation performed by professor
public class ProfessorOperation implements ProfessorInterface{
	private Logger logger =Logger.getLogger(ProfessorOperation.class);
	ProfessorDao professordao=new ProfessorDaoImpl();

	//view enrolled students in a particular course
	@Override
	public List<Student> viewEnrolledStudents(int courseid) {

		//Student list
		List<Student> list=professordao.listEnrolledStudents(courseid);
		if(list.size()==0) {
			logger.info("No students enrolled in this course or invalid course id");
		}
		else {
			logger.info(String.format("%1$10s %2$10s","Student Username","Student Name"));
			list.forEach(student->logger.info(String.format("%1$10s %2$10s",student.getUsername(),student.getStudentname())));
		}
		return list;
	}

	//Upload grade by professor
	@Override
	public void updateGrade(int grade, Student student,int courseid) {
		//Successfully uploaded
		if(professordao.addGrade(grade, student, courseid)) {
			logger.info("grades for student "+student.getUsername()+" updated");
		}
		//Uploading failed
		else {
			logger.error("not updated");
		}
	}

	//Choose course for teaching
	@Override
	public void chooseCourse(String professorusername,int courseid) {

		if(professordao.chooseCourse(professorusername, courseid)) {
			logger.info("Course with courseid "+courseid+" successfully choosen");
		}
		else {
			logger.error("not able to choose course.Invalid course");
		}
	}

	//View courses selected by professor himself.
	@Override
	public List<Course> viewSelectedCourse(String username) {
		List<Course> courselist=professordao.viewSelectedCourses(username);
		if(courselist.size()==0) {
			logger.info("You have not selected any course for teaching");
		}
		else {
			logger.info(String.format("%1$10s %2$10s %3$10s %4$10s %5$10s %6$10s","Course Id","Course Name","Course Schedule","Number of students","Fees","Catalogid"));
			courselist.forEach(list->logger.info(String.format("%1$10s %2$10s %3$10s %4$10s %5$20s %6$10s",list.getCourseId(),list.getCourseName(),list.getCourseSchedule(),list.getNumberOfStudents(),list.getFees(),list.getCatalogid())));
		}
		return courselist;
	}

}

package com.flipkart.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.flipkart.dao.AdminDao;
import com.flipkart.dao.AdminDaoImpl;
import com.flipkart.model.Admin;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

//Operation performed by admin
public class AdminOperation implements AdminInterface{

	//Logger
	private Logger logger=Logger.getLogger(AdminOperation.class);
	AdminDao admindao=new AdminDaoImpl();

	//Add student to database
	@Override
	public void addStudent(Student student) {
		if(admindao.insertStudent(student)) {
			logger.info(student.getUsername()+" added successfully");
		}
		else {
			logger.error(student.getUsername()+" not added successfully.Student with this username already present");
		}
	}

	//Delete student from database
	@Override
	public void deleteStudent(Student student) {

		if(admindao.dropStudent(student)) {
			logger.info(student.getUsername()+" deleted successfully");
		}
		else {
			logger.error("error in deletion.Student with this username is not present");
		}
	}

	//Update student details
	@Override
	public void updateStudent(Student student) {

		if(admindao.updateStudent(student)) {
			logger.info(student.getUsername()+" updated successfully");
		}
		else {
			logger.error(student.getUsername()+" not updated.Student with this username is not present");
		}

	}

	//Add professor to database
	@Override
	public void addProfessor(Professor professor) {

		if(admindao.insertProfessor(professor)) {
			logger.info(professor.getUsername()+" added successfully");
		}
		else {
			logger.error(professor.getUsername()+" not added successfully.Professor with this username is already present");
		}
	}

	//Delete professor
	@Override
	public void deleteProfessor(Professor professor) {

		if(admindao.dropProfessor(professor)) {
			logger.info(professor.getUsername()+" deleted successfully");
		}
		else {
			logger.error(professor.getUsername()+" not deleted successfully.Professsor with this username is not present");
		}
	}

	//Update professor details
	@Override
	public void updateProfessor(Professor professor) {

		if(admindao.updateProfessor(professor)) {
			logger.info(professor.getUsername()+" updated successfully");
		}
		else {
			logger.error(professor.getUsername()+" not updated successfully.Professsor with this username is not present.");
		}
	}

	//Add admin to database
	@Override
	public void addAdmin(Admin admin) {

		if(admindao.insertAdmin(admin)) {
			logger.info(admin.getUsername()+" added successfully");
		}
		else {
			logger.error(admin.getUsername()+" not added successfully.Admin with this username is already present");
		}
	}

	//Delete admin type user from database
	@Override
	public void deleteAdmin(Admin admin) {

		if(admindao.dropAdmin(admin)) {
			logger.info(admin.getUsername()+" deleted successfully");
		}
		else {
			logger.error(admin.getUsername()+" not deleted successfully.Admin with this username is not present");
		}
	}

	//Update admin details
	@Override
	public void updateAdmin(Admin admin) {

		if(admindao.updateAdmin(admin)) {
			logger.info(admin.getUsername()+" updated successfully");
		}
		else {
			logger.error(admin.getUsername()+" not updated successfully.Admin with this username is not present");
		}
	}

	//Add course to catalog
	@Override
	public void addCourseToDb(Course course) {

		if(admindao.insertCourseToDb(course)) {
			logger.info(course.getCourseName()+" course added successfully");
		}
		else {
			logger.error(course.getCourseName()+" course not added successfully.Course with courseid already present");
		}
	}

	//Delete course from catalog
	@Override
	public void deleteCourseFromDb(Course course) {
	
		if(admindao.dropCourseFromDb(course)) {
			logger.info(course.getCourseName()+" course deleted successfully");
		}
		else {
			logger.error(course.getCourseName()+" course not deleted successfully.No course with such course id");
		}
	}

	//Update course details
	@Override
	public void updateCourseInDb(Course course) {

		if(admindao.updateCourseInDb(course)) {
			logger.info(course.getCourseName()+" updated successfully");
		}
		else {
			logger.error(course.getCourseName()+" not updated successfully.No course with such courseid");
		}
	}

	//List all students
	@Override
	public List<Student> listStudent() {
		
		//List of all students
		List<Student> studentlist=admindao.fetchStudent();
		logger.info(String.format("%1$10s %2$10s %3$10s %4$10s %5$10s","username","name","address","year","mobile number"));

		//Female list.Add Ms. before name using stream
		List<Student> studentfemale=studentlist
				.stream()
				.filter(student->student.getGender().equalsIgnoreCase("f"))
				.flatMap(student->Stream.of(new Student(student.getUsername(),""," Ms."+student.getStudentname(),student.getStudentaddress(),student.getStudentyear(),student.getStudentmobilenumber(),student.getGender())))
				.collect(Collectors.toList());
		studentfemale.forEach(list->logger.info(String.format("%1$10s %2$10s %3$10s %4$10s %5$10s",list.getUsername(),list.getStudentname(),list.getStudentaddress(),list.getStudentyear(),list.getStudentmobilenumber())));

		//Male students list .Add Mr before name using stream
		List<Student> studentmale=studentlist
				.stream()
				.filter(student->student.getGender().equalsIgnoreCase("m"))
				.flatMap(student->Stream.of(new Student(student.getUsername(),""," Mr."+student.getStudentname(),student.getStudentaddress(),student.getStudentyear(),student.getStudentmobilenumber(),student.getGender())))
				.collect(Collectors.toList());
		studentmale.forEach(list->logger.info(String.format("%1$10s %2$10s %3$10s %4$10s %5$10s",list.getUsername(),list.getStudentname(),list.getStudentaddress(),list.getStudentyear(),list.getStudentmobilenumber())));
		return studentlist;
	}
	

}

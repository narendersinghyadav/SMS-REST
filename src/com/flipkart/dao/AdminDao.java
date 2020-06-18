package com.flipkart.dao;

import java.util.List;

import com.flipkart.model.Admin;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;

//AdminDao interface
public interface AdminDao {
	//Insert/delete/update student in student table
	public boolean insertStudent(Student student);
	public boolean dropStudent(Student student);
	public boolean updateStudent(Student student);

	//Insert/delete/update professor in professor table
	public boolean insertProfessor(Professor professor);
	public boolean dropProfessor(Professor professor);
	public boolean updateProfessor(Professor pprofessor);

	//Insert/delete/update admin in admin table
	public boolean insertAdmin(Admin professor);
	public boolean dropAdmin(Admin professor);
	public boolean updateAdmin(Admin professor);

	//Insert/delete/update course in catalog table
	public boolean insertCourseToDb(Course course);
	public boolean dropCourseFromDb(Course course);
	public boolean updateCourseInDb(Course course);

	//Fetch list of all students
	public List<Student> fetchStudent();
}

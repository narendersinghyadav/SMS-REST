package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.exception.FullCourseNotification;
import com.flipkart.model.Student;
import com.flipkart.utils.CloseDbConnection;
import com.flipkart.utils.DBUtil;

//Student Dao Implementation
public class StudentDaoImpl implements StudentDao,CloseDbConnection{

	private static Logger logger=Logger.getLogger(StudentDaoImpl.class);
	public static Connection connection=null;

	//Insert course in registration table with timestamp
	@Override
	public boolean addCourse(Student student, int courseid1, int courseid2, int courseid3, int courseid4) throws FullCourseNotification {

		connection=DBUtil.getConnection();

		//Local time
		LocalTime localtime=LocalTime.now();
		LocalDate localdate=LocalDate.now();
		LocalDateTime localdatetime=LocalDateTime.now();

		//Increase number of students by 1 for each selected course
		CatalogDao catalogdao=new CatalogDaoImpl();
		catalogdao.increaseNumberOfStudents(courseid1);
		catalogdao.increaseNumberOfStudents(courseid2);
		catalogdao.increaseNumberOfStudents(courseid3);
		catalogdao.increaseNumberOfStudents(courseid4);

		try {
			//add courses in registration table
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.ADD_COURSE_BY_STUDENT);
			statement.setString(1,student.getUsername());
			statement.setInt(2,courseid1);
			statement.setInt(3,courseid2);
			statement.setInt(4,courseid3);
			statement.setInt(5,courseid4);
			statement.setString(6,localdate+"/"+localtime+"/"+localdatetime.getDayOfWeek());
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			//close connection
			closeConnection(connection);
		}
		return true;	
	}

	//Update courses for registration
	@Override
	public boolean updateCourse(Student student,int courseid1,int courseid2,int courseid3,int courseid4) throws FullCourseNotification {


		LocalTime localtime=LocalTime.now();
		LocalDate localdate=LocalDate.now();
		LocalDateTime localdatetime=LocalDateTime.now();

		CatalogDao catalogdao=new CatalogDaoImpl();
		//Increase number of students for new courses selected
		catalogdao.increaseNumberOfStudents(courseid1);
		catalogdao.increaseNumberOfStudents(courseid2);
		catalogdao.increaseNumberOfStudents(courseid3);
		catalogdao.increaseNumberOfStudents(courseid4);

		//decrease number of students by 1 of previously selected courses
		StudentDao studentdao=new StudentDaoImpl();
		List<Integer> courselist=studentdao.listSelectedCourses(student.getUsername());
		courselist.forEach(courseid->catalogdao.decreaseNumberOfStudents(courseid));

		try {
			//Update details in registration table for student
			connection=DBUtil.getConnection();
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.UPDATE_COURSE_BY_STUDENT);
			statement.setInt(1,courseid1);
			statement.setInt(2,courseid2);
			statement.setInt(3,courseid3);
			statement.setInt(4,courseid4);
			statement.setString(5,localdate+"/"+localtime+"/"+localdatetime.getDayOfWeek());
			statement.setString(6,student.getUsername());
			//update course id's
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();
			

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			//close connection
			closeConnection(connection);
		}
		return true;

	}

	//Retrieve grades
	@Override
	public HashMap<Integer,Integer> listGrade(Student student) {

		connection=DBUtil.getConnection();
		HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();

		try {
			//list of grades of a particular student with course id
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.LIST_GRADE);
			statement.setString(1,student.getUsername());
			ResultSet resultset=statement.executeQuery();
			while(resultset.next()) {
				map.put(resultset.getInt("courseid"),resultset.getInt("coursegrade"));
			}
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		//return map of courseid and coursegrade
		return map;

	}

	//Pay fees
	@Override
	public void payFees(Student student,String paymentmode) {
		// Payment done
		connection=DBUtil.getConnection();
		try {
			//update payment
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.UPDATE_PAYMENT);
			statement.setString(1,paymentmode);
			statement.setString(2,paymentmode+" is used");
			statement.executeUpdate();

			//get payment id
			statement=connection.prepareStatement(SQLConstantQueries.GET_PAYMENT_ID);	
			ResultSet resultset=statement.executeQuery();
			while(resultset.next()) {
				int paymentid=resultset.getInt("paymentid");
				registerComplete(paymentid,student);
			}
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
	}

	//View own details by student
	@Override
	public Student getStudentInfo(String username,String password) {

		connection=DBUtil.getConnection();
		Student student=null;

		try {
			//get own details 
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.GET_STUDENT_DETAILS);

			statement.setString(1,username);
			ResultSet resultset=statement.executeQuery();
			while(resultset.next()) {

				//Details
				username=resultset.getString("username");
				String name=resultset.getString("name");
				String address=resultset.getString("address");
				String year=resultset.getString("year");
				String mobilenumber=resultset.getString("mobilenumber");
				String gender=resultset.getString("gender");
				student=new Student(username,password,name,address,year,mobilenumber,gender);
			}
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		//Return student object
		return student;
	}

	//List of selected courses during registration
	@Override
	public List<Integer> listSelectedCourses(String username) {

		connection=DBUtil.getConnection();
		List<Integer> courses=new ArrayList<Integer>();

		try {
			//list of selected courses
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.LIST_SELECTED_COURSES);
			statement.setString(1,username);
			ResultSet resultset=statement.executeQuery();
			while(resultset.next()) {
				//Course id's of selected courses
				courses.add(resultset.getInt("course1id"));
				courses.add(resultset.getInt("course2id"));
				courses.add(resultset.getInt("course3id"));
				courses.add(resultset.getInt("course4id"));
			}
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		//return courses list with course id only
		return courses;
	}
	//do entry in registration table
	public void registerComplete(int paymentid,Student student) {
		connection=DBUtil.getConnection();

		try {
			//list of selected courses
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.REGISTRATION_ENTRY);
			statement.setString(1,student.getUsername());
			statement.setInt(2,paymentid);
			statement.setString(3, LocalDateTime.now().toString());
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
	}
}

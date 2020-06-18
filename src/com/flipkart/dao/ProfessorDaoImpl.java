package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.utils.CloseDbConnection;
import com.flipkart.utils.DBUtil;

//Professor DAO implementation
public class ProfessorDaoImpl implements ProfessorDao,CloseDbConnection{


	private static Logger logger=Logger.getLogger(ProfessorDao.class);
	public static Connection connection=null;

	//List of enrolled student by course id
	@Override
	public List<Student> listEnrolledStudents(int courseid) {
		
		//get connection
		connection=DBUtil.getConnection();
		Student student=null;
		List<Student> array=new ArrayList<Student> ();
		
		try {
			//list of students in a particular course
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.LIST_ENROLLED_STUDENTS);
			statement.setInt(1, courseid);
			statement.setInt(2, courseid);
			statement.setInt(3, courseid);
			statement.setInt(4, courseid);
			ResultSet resultset=statement.executeQuery();

			while(resultset.next()){
				//Retrieve  user name
				String username=resultset.getString("username");
				String name=resultset.getString("name");
				student=new Student(username);
				student.setStudentname(name);
				array.add(student);
			}

			//close resultset and statement
			resultset.close();
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		//return array of students
		return array;
	}

	//Upload grade of a student for a particular course
	@Override
	public boolean addGrade(int grade, Student student,int courseid) {
		// get connection
		connection=DBUtil.getConnection();
		CatalogDao coursedao=new CatalogDaoImpl();
		
		//List of courses
		List<Course> courselist=coursedao.getCatalog();
		courselist=courselist.stream().filter(list->list.getCourseId()==courseid).collect(Collectors.toList());
		
		//Check validity of course id
		if(courselist.size()==0) {
			logger.error("Invalid course id");
			return false;
		}
		
		//Check validity of student user name
		StudentDao studentdao=new StudentDaoImpl();
		Student studentinfo=studentdao.getStudentInfo(student.getUsername(), student.getPassword());
		if(studentinfo==null) {
			logger.error("Invalid student username");
			return false;
		}
		
		//Upload grades
		try {
			//list customer statement
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.ADD_GRADE);
			statement.setString(1, student.getUsername());
			statement.setInt(2, courseid);
			statement.setInt(3, grade);

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

	//Choose course for teaching
	@Override
	public boolean chooseCourse(String professorusername,int courseid) {
		
		connection=DBUtil.getConnection();
		CatalogDao coursedao=new CatalogDaoImpl();
		
		// Check validity of course id 
		List<Course> courselist=coursedao.getCatalog();
		courselist=courselist.stream().filter(list->list.getCourseId()==courseid).collect(Collectors.toList());
		if(courselist.size()==0) {
			return false;
		}
		//choose course for teaching
		try {
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.CHOOSE_COURSE_FOR_TEACHING);
			statement.setString(1, professorusername);
			statement.setInt(2, courseid);
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

	//View selected course for teaching
	@Override
	public List<Course> viewSelectedCourses(String username) {
		connection=DBUtil.getConnection();
		List<Course> array=new ArrayList<Course>();
		
		try {
			//list courses teach by professor himself
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.LIST_COURSES_BY_USERNAME);
			statement.setString(1,username);;
			ResultSet resultset=statement.executeQuery();

			while(resultset.next()){
				//Retrieve course details
				Course course =new Course(resultset.getInt("courseid"),resultset.getString("coursename"),resultset.getString("courseschedule"),resultset.getInt("numberofStudents"),resultset.getInt("fees"),resultset.getInt("catalogid"));
				array.add(course);
			}

			resultset.close();
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		//Return array of courses
		return array;
	}
		

}

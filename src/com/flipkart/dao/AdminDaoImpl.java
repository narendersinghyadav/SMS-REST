package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constant.RoleConstants;
import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.model.Admin;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.model.User;
import com.flipkart.utils.CloseDbConnection;
import com.flipkart.utils.DBUtil;

//AdminDao implementation
public class AdminDaoImpl implements AdminDao,CloseDbConnection{

	private static Logger logger=Logger.getLogger(AdminDaoImpl.class);
	public static Connection connection=null;
	UserDao userdao=new UserDaoImpl();

	//Add student to student table
	@Override
	public boolean insertStudent(Student student) {
		
		connection=DBUtil.getConnection();
		User user=new User(student.getUsername(),student.getPassword(),RoleConstants.STUDENT);
		
		try {
			//Before adding student to student table, student is added to user table
			userdao.addUser(user);
			//Prepare sql statement
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.ADD_STUDENT);
			statement.setString(1, student.getUsername());
			statement.setString(2, student.getStudentname());
			statement.setString(3, student.getStudentaddress());
			statement.setString(4, student.getStudentyear());
			statement.setString(5, student.getStudentmobilenumber());
			statement.setString(6,student.getGender());
			//execute statement
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			//Closing database connection
			closeConnection(connection);
		}
		return true;
	}

	//Delete student from student table
	@Override
	public boolean dropStudent(Student student) {
		
		connection=DBUtil.getConnection();
		
		User user=new User(student.getUsername(),student.getPassword(),student.getRole());
		try {
			
			//Prepare sql query for deleting student
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.DELETE_STUDENT);
			statement.setString(1, student.getUsername());
			int row=statement.executeUpdate();
			if(row==0) {
				
				return false;
			}
			//Delete student entry from user table
			userdao.deleteUser(user);
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			//Closing database connection
			closeConnection(connection);
		}
		return true;
	}

	//Update student info in student table
	@Override
	public boolean updateStudent(Student student) {
		
		connection=DBUtil.getConnection();

		
		try {
			//Prepare update query
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.UPDATE_STUDENT);
			statement.setString(6, student.getUsername());
			statement.setString(1, student.getStudentname());
			statement.setString(2, student.getStudentaddress());
			statement.setString(3, student.getStudentyear());
			statement.setString(4, student.getStudentmobilenumber());
			statement.setString(5,student.getGender());
			int row=statement.executeUpdate();
			//If updated
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

	//Insert professor info in professor table
	@Override
	public boolean insertProfessor(Professor professor) {
		
		connection=DBUtil.getConnection();
		User user=new User(professor.getUsername(),professor.getPassword(),RoleConstants.PROFESSOR);
		
		try {
			//Insert professor info in user table--info like username,password and role
			userdao.addUser(user);
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.ADD_PROFESSOR);
			statement.setString(1, professor.getUsername());
			statement.setString(2, professor.getProfessorName());
			statement.setString(3, professor.getProfessorAddress());
			statement.setString(4,professor.getProfessorMobilenumber());
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			//Close connection
			closeConnection(connection);
		}
		return true;

	}

	//Delete professor from database
	@Override
	public boolean dropProfessor(Professor professor) {
		
		connection=DBUtil.getConnection();
		User user=new User(professor.getUsername(),professor.getPassword(),professor.getRole());
		
		try {
			
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.DELETE_PROFESSOR);
			statement.setString(1, professor.getUsername());
			//Execute query to delete professor
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			//Delete student entry from user table
			userdao.deleteUser(user);
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

	//Update professor info in professor table except username
	@Override
	public boolean updateProfessor(Professor professor) {
		connection=DBUtil.getConnection();

		try {
			//Update query
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.UPDATE_PROFESSOR);
			statement.setString(4, professor.getUsername());
			statement.setString(1, professor.getProfessorName());
			statement.setString(2, professor.getProfessorAddress());
			statement.setString(3, professor.getProfessorMobilenumber());
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			//Close connection
			closeConnection(connection);
		}
		return true;

	}

	//insert admin in admin table
	@Override
	public boolean insertAdmin(Admin admin) {
		
		connection=DBUtil.getConnection();
		User user=new User(admin.getUsername(),admin.getPassword(),RoleConstants.ADMIN);
		
		try {
			//Adding admin in user table
			userdao.addUser(user);
			//Adding admin in admin table
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.ADD_ADMIN);
			statement.setString(1, admin.getUsername());
			statement.setString(2, admin.getAdminName());
			statement.setString(3, admin.getAdminAddress());
			statement.setString(4,admin.getAdminMobilenumber());
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			//Close connection
			closeConnection(connection);
		}
		return true;
	}

	//Delete Admin from database
	@Override
	public boolean dropAdmin(Admin admin) {
		
		connection=DBUtil.getConnection();
		User user=new User(admin.getUsername(),admin.getPassword(),admin.getRole());

		try {
			
			//Delete admin from admin table
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.DELETE_ADMIN);
			statement.setString(1, admin.getUsername());
			int row=statement.executeUpdate();
			if(row==0) {
				
				return false;
			}
			//Delete admin from user table
			userdao.deleteUser(user);
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			//Close connection to database
			closeConnection(connection);
		}
		return true;
	}

	//Update admin info in admin table
	@Override
	public boolean updateAdmin(Admin admin) {
		
		connection=DBUtil.getConnection();
		
		try {
			//Update query for admin info in admin table
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.UPDATE_ADMIN);
			statement.setString(4, admin.getUsername());
			statement.setString(1, admin.getAdminName());
			statement.setString(2, admin.getAdminAddress());
			statement.setString(3, admin.getAdminMobilenumber());
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			//statement close
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//Close connection
			closeConnection(connection);
		}
		return true;
	}

	//Insert course details in catalog table
	@Override
	public boolean insertCourseToDb(Course course) {
		
		connection=DBUtil.getConnection();
		
		try {
			//adding course to catalog table
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.ADD_COURSE_TO_DB);
			statement.setInt(1, course.getCourseId());
			statement.setString(2, course.getCourseName());
			statement.setString(3, course.getCourseSchedule());
			statement.setInt(4, course.getNumberOfStudents());
			statement.setInt(5, course.getFees());
			statement.setInt(6, course.getCatalogid());
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

	//Delete course form catalog table using course id
	@Override
	public boolean dropCourseFromDb(Course course) {
		
		connection=DBUtil.getConnection();
		
		try {
			//Delete query for delete course entry using course id
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.DELETE_COURSE_TO_DB);
			statement.setInt(1, course.getCourseId());
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		return true;
	}

	//Update  course details usinf course id
	@Override
	public boolean updateCourseInDb(Course course) {
		
		connection=DBUtil.getConnection();
		try {
			//Update query for updating course details like name,course schedule,number of students
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.UPDATE_COURSE_TO_DB);
			statement.setInt(6, course.getCourseId());
			statement.setString(1, course.getCourseName());
			statement.setString(2, course.getCourseSchedule());
			statement.setInt(3, course.getNumberOfStudents());
			statement.setInt(4, course.getFees());
			statement.setInt(5, course.getCatalogid());
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			closeConnection(connection);
		}
		return true;

	}

	//Fetch list of all students present in db
	@Override
	public List<Student> fetchStudent() {
		
		connection=DBUtil.getConnection();
		List<Student> studentlist=new ArrayList<Student>();
		Student student=null;
		
		try {
			//List student query
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.LIST_STUDENT);
			ResultSet resultset=statement.executeQuery();
			while(resultset.next()){
				//Retrieve by column names
				student=new Student(resultset.getString("username")," ",resultset.getString("name"),resultset.getString("address"),resultset.getString("year"),resultset.getString("mobilenumber"),resultset.getString("gender"));
				//Adding student object retrieved to student array
				studentlist.add(student);
			}

			resultset.close();
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		//Returning list of students
		return studentlist;
	}

}

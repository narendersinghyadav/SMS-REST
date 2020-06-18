package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.exception.FullCourseNotification;
import com.flipkart.model.Course;
import com.flipkart.utils.CloseDbConnection;
import com.flipkart.utils.DBUtil;

//CatalogDao implementation 
public class CatalogDaoImpl implements CatalogDao,CloseDbConnection{
	
	//Logger log4j
	private static Logger logger=Logger.getLogger(UserDaoImpl.class);
	public static Connection connection=null;
	
	//List courses of catalog
	@Override
	public List<Course> getCatalog() {
		
		connection=DBUtil.getConnection();
		List<Course> courselist=new ArrayList<Course>();
		
		try {
			//get list of courses query
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.LIST_CATALOG);
			ResultSet resultset=statement.executeQuery();

			//Getting result set
			while(resultset.next()){
				//Retrieve by column name
				int courseid=resultset.getInt("courseid");
				String coursename=resultset.getString("coursename");
				String courseschedule=resultset.getString("courseschedule");
				int numberofstudents=resultset.getInt("numberofStudents");
				int fees=resultset.getInt("fees");
				int catalogid=resultset.getInt("catalogid");
				Course course=new Course(courseid,coursename,courseschedule,numberofstudents,fees,catalogid);
				courselist.add(course);
			}
			resultset.close();
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		return courselist;
	}
	
	//increment number of student in a course by 1
	@Override
	public boolean increaseNumberOfStudents(int courseid) throws FullCourseNotification {
		
		connection=DBUtil.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.INCREMENT_NUMBER_OF_STUDENTS);
			statement.setInt(1, courseid);
			int row=statement.executeUpdate();
			if(row==0) {
				throw new FullCourseNotification(courseid);
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
	
	//Decrement number of student in a course by 1
	@Override
	public boolean decreaseNumberOfStudents(int courseid) {
		connection=DBUtil.getConnection();
		try {
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.DECREMENT_NUMBER_OF_STUDENTS);
			statement.setInt(1, courseid);
			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();
		}catch(SQLException e) {
			logger.error(e.getMessage());
		}
		return true;
	}
}

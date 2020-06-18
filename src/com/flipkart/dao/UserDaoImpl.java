package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLConstantQueries;
import com.flipkart.model.User;
import com.flipkart.utils.CloseDbConnection;
import com.flipkart.utils.DBUtil;

//User data access object
public class UserDaoImpl implements UserDao,CloseDbConnection{
	
	private static Logger logger=Logger.getLogger(UserDaoImpl.class);
	public static Connection connection=null;
	
	//Retrieve password,role by user name
	@Override
	public User getPasswordByUsername(String username) {
		connection=DBUtil.getConnection();
		User user=null;
		String password="";
		int role=1;
		
		try {
			//list user by user name 
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.LIST_BY_USERNAME);
			statement.setString(1, username);
			ResultSet resultset=statement.executeQuery();

			while(resultset.next()){
				//Retrieve by column names
				password=resultset.getString("password");
				role=resultset.getInt("role");
			}
			user=new User(username,password,role);
			resultset.close();
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
		}finally {
			//close connection
			closeConnection(connection);
		}
		//return user object
		return user;
	}
	
	//Add user to userlogin table
	@Override
	public boolean addUser(User user) {
		connection=DBUtil.getConnection();
		
		try {
			//Add user
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.ADD_USER);
			statement.setString(1,user.getUsername());
			statement.setString(2,user.getPassword());
			statement.setInt(3,user.getRole());

			int row=statement.executeUpdate();
			if(row==0) {
				return false;
			}
			statement.close();

		}catch(SQLException e) {
			logger.error(e.getMessage());
			return false;
		}finally {
			///close connection
			closeConnection(connection);
		}
		return true;
	}
	
	//Delete user from userlogin table
	@Override
	public boolean deleteUser(User user) {
		connection=DBUtil.getConnection();
		
		try {
			//Delete query
			PreparedStatement statement=connection.prepareStatement(SQLConstantQueries.DELETE_USER);
			statement.setString(1,user.getUsername());

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
	

}

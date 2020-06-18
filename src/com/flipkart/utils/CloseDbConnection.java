package com.flipkart.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

//Close database connection
public interface CloseDbConnection {
	//Default method to close database connections made by DAO classes
	default public void closeConnection(Connection connection) {
		if (connection != null) {
	        try {
	        	//Close connection
	            connection.close();
	        } catch (SQLException e) {
	        	Logger.getLogger(CloseDbConnection.class).error("Error while closing connection");
	        }
	    }
	}
}

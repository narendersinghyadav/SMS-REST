package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
//database connection utility
public class DBUtil {
	private static Logger logger =Logger.getLogger(DBUtil.class);
	private static Connection connection = null;
	//get connection from database
	public static Connection getConnection() {
		try {
			//retrieve details from config.properties file of db
			Properties prop = new Properties();
			InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(inputStream);
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			Class.forName(driver);
			//establish connection and return Connection class object 
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return connection;

	}
}

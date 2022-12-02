package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCConnectionUtil {

	// This utility class will establish a database connection between this Java Program and our SQL database
	// static is a non-access modifier - belongs to this class only and not instance objects of this class
	// access modifiers: public, private, protected, and default
	
	public static Logger logger = LoggerFactory.getLogger(JDBCConnectionUtil.class);
	
	// make our connection
	public static Connection getConnection() {
		//1. establish DB credentials (URL for the host, username, & password)
		// There are two ways to share your credentials with your Java program
		// a. environment variables (recommended)
		// b. Hard-coded way (DONT DO IT)
		
		// Format:
		// URL: jdbc:postgresql://[host-url]:[port-number]/[database-name(optional)]
		// Username & Password - same as before
		
		Connection conn = null;
		
		try {
			//2. Establish our connection's DriverManager to make a new connection based on provided credentials
			logger.info(String.format(
					"Making a DB connection with credentials: "
					+ "\nURL: %s "
					+ "\nUsername: %s "
					+ "\nPassword: %s", 
					System.getenv("DB_URL"),
					System.getenv("DB_USERNAME"),
					System.getenv("DB_PASSWORD"))
					);
			
			conn = DriverManager.getConnection(
					System.getenv("DB_URL"),
					System.getenv("DB_USERNAME"),
					System.getenv("DB_PASSWORD")
					);
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return conn;
	}
}

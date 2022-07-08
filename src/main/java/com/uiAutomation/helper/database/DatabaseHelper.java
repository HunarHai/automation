package com.uiAutomation.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class DatabaseHelper {

	private static final Logger log = LoggerHelper.getLogger(DatabaseHelper.class);

	private static String url = "jdgc:mysql://localhost/person";	//jdbc
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "password";
	private static Connection connection;
	private static DatabaseHelper instance = null;
	
	/**
	 * 
	 */
	public DatabaseHelper() {
		connection = getSingleInstanceConnection();
		log.info("DatabaseHelper object is created...");
		TestBase.logExtentReport("DatabaseHelper object is created...");
	}

	/**
	 * 
	 * @return
	 */
	public static DatabaseHelper getInstance() {
		if (instance == null) {
			instance = new DatabaseHelper();
		}
		return instance;
	}

	/**
	 * This method is to get Database Connection Instance
	 * 
	 * @return
	 */
	private Connection getSingleInstanceConnection() {
		try {
			Class.forName(driverName);
			try {
				connection = DriverManager.getConnection(url, userName,
						password);
				if (connection != null)
					log.info("Connected to Database...");
			} catch (SQLException e) {
				log.error("Failed to create Database connection...");
			}
		} catch (ClassNotFoundException e) {
			log.info("Driver not found..." + e);
		}
		return connection;
	}

	/**
	 * 
	 * @return
	 */
	public Connection getConnection() {
		return connection;
	}

	public static ResultSet getResultSet(String dbQuery) {
		instance = DatabaseHelper.getInstance();
		connection = instance.getConnection();
		log.info("Executing Query: " + dbQuery);
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(dbQuery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
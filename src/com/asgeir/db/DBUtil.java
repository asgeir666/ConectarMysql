package com.asgeir.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "Senati2020";
	private static final String M_CONN_STRING = 
			"jdbc:mysql://localhost/explorecalifornia";
	private static final String H_CONN_STRING =
			"jdbc:hsqldb:data/explorecalifornia";
	private static final String S_CONN_STRING = 
			"jdbc:sqlserver://localhost;databaseName=medicaldata";
	
	public static Connection getConnection(DBType dbtype) throws SQLException{	
	
		switch (dbtype) {
		case HSQL:
			return DriverManager.getConnection(H_CONN_STRING,USERNAME,PASSWORD);	
		case MYSQL:
			return DriverManager.getConnection(M_CONN_STRING,USERNAME,PASSWORD);			
		case MSQL:
			return DriverManager.getConnection(S_CONN_STRING,USERNAME,PASSWORD);
		default:
			return null;
		}
	}
	
	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("Error state: " + e.getSQLState());	
	} 
}

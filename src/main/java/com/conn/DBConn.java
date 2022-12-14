package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConn {
	private Connection conn = null;
	private Properties connConfig = new Properties();
	private String endPoint;
	
	public DBConn() {
		connConfig.setProperty("user", "root");
		connConfig.setProperty("password", "passwd");
		endPoint = "jdbc:mariadb://localhost:3306/library_db";
	}
	
	public Connection getConnection() {
		
		try {
			conn = DriverManager.getConnection(endPoint, connConfig);
			System.out.println("Connection - Sucessful");
			
		} catch (SQLException e) {
			System.out.println("Connection - Failed");
			e.printStackTrace();
		}
		
		return conn;
	}
}

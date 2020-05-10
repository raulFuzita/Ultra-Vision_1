package com.raul.rental_shop.Ultra_Vision.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static Database instance  = new Database();
	
	private static Connection connection;
	private static PreparedStatement preparedStmt;
	private static Statement stmt;
    private static String serverName = "jdbc:mysql://127.0.0.1:3306/ultra_vision_raul";
    private static String username = "root";
    private static String password = "";

	private Database() {}
	
	public static Database getInstance() throws SQLException {
		return instance;
	}
	
	public static Database connect() throws SQLException {
		try {
			
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
            		serverName + "?autoReconnect=true&useSSL=false", 
            		username, 
            		password
            	);
            
            stmt = connection.createStatement();
            
            System.out.println("Database has connected successfully");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Database connection Failed : " + ex.getMessage());
        }
		return instance;
	}
	
	public static Database setPreparedStmt(String sql) throws SQLException {
		preparedStmt = connection.prepareStatement(sql);
		return instance;
	}
	
	public static Statement getStatment() {
		return stmt;
	}
	
	public static PreparedStatement getPreparedStmt() {
		return preparedStmt;
	}
	
	public void close() throws SQLException {
		connection.close();
		stmt.close();
	}

}

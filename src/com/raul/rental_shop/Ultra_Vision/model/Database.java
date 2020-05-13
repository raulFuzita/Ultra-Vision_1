package com.raul.rental_shop.Ultra_Vision.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>Class Database is a singleton. You don't need to instantiate this<br>
 * class since it is singleton and all methods are static.</p>
 * 
 * <p>If you want to set a different connection for this class you must<br>
 * reset all values as you wish.
 * If the connection can't be establish an exception is thrown and a message<br>
 * is displayed on the Java console but you can change if you want to<br>
 * customize the way the message is displayed</p>
 * 
 * <p>All attributes in this class are private and static access.
 * 
 */
public class Database {
	
	/* Like any singleton class it has an attribute type of the 
	 * class itself which instantiates it.*/
	private static Database instance  = new Database();
	
	private static Connection connection;
	private static PreparedStatement preparedStmt;
	private static Statement stmt;
	
	/*
	 * You database connection information should be placed down here.
	 */
    private static String serverName = "jdbc:mysql://127.0.0.1:3306/ultra_vision_raul";
    private static String username = "root";
    private static String password = "";

    /* As any other singleton technique this class uses a private constructor 
     * to prevent it to be instantiate. It means there is only one instance of
     * this class.*/
	private Database() {}
	
	/**
	 * This method could be invoked at the begin of your application since
	 * this is going to be a unique instance. Then later on you are able to 
	 * use all the benefits without having to instantiate.
	 * 
	 * @return a unique instance of the class Database.
	 * @throws SQLException since it manage SQL database connection.
	 */
	public static Database getInstance() throws SQLException {
		return instance;
	}
	
	/**
	 * This method will perform a connection with the database using the 
	 * information signed for the attributes of the class. This method uses
	 * a mysql drive over version 7. Make sure which drive you're using.
	 * This connection applies auto reconnect and ignores an SSL 
	 * authentication. This class is used for study purpose. It isn't
	 * aiming high security measures. Some database could have restrictions
	 * due to the Apache database setting. So ignoring SSL will avoid 
	 * common problems.
	 * 
	 * @return class Database which is a unique instance of the class.
	 * @throws SQLException
	 */
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
	
	/**
	 * This method is used to prepare statement and avoid SQL injection.
	 * Keep in mind you SHOULD NOT rely on only this measure.
	 * 
	 * @param sql is a type of String which must contain a valid SQL statement.
	 * @return the unique instance of this class. This technique allows a 
	 * chaining method.
	 * 
	 * @throws SQLException since this class manages SQL database connection
	 * and queries.
	 */
	public static Database setPreparedStmt(String sql) throws SQLException {
		preparedStmt = connection.prepareStatement(sql);
		return instance;
	}
	
	/**
	 * This method will returns a Statement type of the class.
	 * Use this method for queries that doesn't demand arguments.
	 * IF you need to pass arguments use a prepared statement instead.
	 * 
	 * @return a Statement type of this class which is singleton.
	 */
	public static Statement getStatment() {
		return stmt;
	}
	
	/**
	 * This method is recommended when you want to pass arguments in a 
	 * SQL query.
	 * 
	 * @return a PreparedStaement of this class which is singleton
	 * is returned. 
	 */
	public static PreparedStatement getPreparedStmt() {
		return preparedStmt;
	}
	
	/**
	 * This method is useful to be invoked in a destructor. It will kill the
	 * connection and the statement.
	 * 
	 * @throws SQLException since this class manages database connections and
	 * queries.
	 */
	public void close() throws SQLException {
		connection.close();
		stmt.close();
	}

}

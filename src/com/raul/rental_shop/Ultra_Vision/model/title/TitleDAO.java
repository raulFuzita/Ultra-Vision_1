package com.raul.rental_shop.Ultra_Vision.model.title;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.Database;

/**
 * @author Raul Macedo Fuzita
 * 
 * @version 13.05.20
 * <br>Version is based on the last update date.
 * 
 * @apiNote
 * <p>Class TitleDAO is a class Data Access Object that handles the basic<br>
 * CRUD operations such as retrieving all data, retrieving a single data,<br>
 * adding a data, updating a data, and some other features. For further<br>
 * information check out the methods documentation.</p>
 * 
 * @role Handle database queries for the class TitleDAO
 * 
 * <p>The only attribute in this class is a List that sign a type of<br>
 * CustomerEntity and instantiates an array list.</p>
 * 
 */
public class TitleDAO implements DAO<TitleEntity> {

	/* This attributes is populated with data from a database*/
	private List<TitleEntity> ts = new ArrayList<>();

	/**
	 * This method retrieves all values compatible with a TitleEntity
	 * based on an id (identification).
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of int. It refers to a title code
	 * number. It is expected that the value is a primary key in a 
	 * database table.
	 */
	@SuppressWarnings("static-access")
	@Override
	public TitleEntity get(int index) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class TitleEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM title WHERE code = ?";
		
		/* A singleton class called Database available at 
		 * package com.raul.rental_shop.Ultra_Vision.model is used to
		 * perform a database connection.*/
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		TitleEntity t = new TitleEntity();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			t.setCode(rs.getInt("code"));
			t.setName(rs.getString("name"));
			t.setGenre(rs.getString("genre"));
			t.setCost(rs.getDouble("cost"));
			t.setYear(rs.getString("year"));
			t.setMediaFormat(rs.getString("media_format"));
			t.setTypeTitle(rs.getString("type_title"));
		}
		
		return t;
	}

	/**
	 * This method will return all data from a table that is compatible with
	 * a class TitleEntity attributes.
	 * 
	 * @return List signed by a class TitleEntity.
	 */
	@Override
	public List<TitleEntity> getAll() throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class TitleEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM title";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			/* In this case a TitleEntity is instantiated every loop
			 * to be able to store all customer values and then stores it
			 * in the list class attributes. */
			TitleEntity t = new TitleEntity();
			
			t.setCode(rs.getInt("code"));
			t.setName(rs.getString("name"));
			t.setGenre(rs.getString("genre"));
			t.setCost(rs.getDouble("cost"));
			t.setYear(rs.getString("year"));
			t.setMediaFormat(rs.getString("media_format"));
			t.setTypeTitle(rs.getString("type_title"));
			
			this.ts.add(t);
		}
		
		return ts;
	}

	/**
	 * This method will store a data into a database compatible with a
	 * class TitleEntity attributes.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of TitleEntity. Be aware that the database table
	 * must to be compatible with the class TitleEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments were stored in the database.
	 * Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean add(TitleEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class TitleEntity. Please, check out its documentation*/
		String sql = "INSERT INTO title "
				+ "(name, genre, cost, year, media_format, type_title, code)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setString(1, t.getName());
		stmt.setString(2, t.getGenre());
		stmt.setDouble(3, t.getCost());
		stmt.setString(4, t.getYear());
		stmt.setString(5, t.getMediaFormat());
		stmt.setString(6, t.getTypeTitle());
		stmt.setInt(7, t.getCode());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will remove a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of TitleEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of TitleEntity. Be aware that the database table
	 * must to be compatible with the class TitleEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are deleted according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean remove(TitleEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class TitleEntity. Please, check out its documentation*/
		String sql = "DELETE FROM title WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getCode());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will update a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of TitleEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of TitleEntity. Be aware that the database table
	 * must to be compatible with the class TitleEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are updated according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean update(TitleEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class TitleEntity. Please, check out its documentation*/
		String sql = "UPDATE title SET name = ?, genre = ?,"
				+ " cost = ?, year = ?, media_format = ?, type_title = ? WHERE code = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, t.getName());
		stmt.setString(2, t.getGenre());
		stmt.setDouble(3, t.getCost());
		stmt.setString(4, t.getYear());
		stmt.setString(5, t.getMediaFormat());
		stmt.setString(6, t.getTypeTitle());
		stmt.setInt(7, t.getCode());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will search for a record in the database that matches with
	 * the following table columns:
	 * 
	 * <ul>
	 * 	<li>code</li>
	 * 	<li>name</li>
	 * 	<li>genre</li>
	 * 	<li>media_format</li>
	 * </ul>
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of TitleEntity. Be aware that the database table
	 * must to be compatible with the class TitleEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return a List signed to TitleEntity. The values returned have to 
	 * match the passed argument. Otherwise it'll return an empty List.
	 */
	@SuppressWarnings("static-access")
	@Override
	public List<TitleEntity> search(String text) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class TitleEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM title WHERE code LIKE ? "
				+ "OR name LIKE ? OR genre LIKE ? OR media_format LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		/* The character '%' will make the database looks for a record thats
		 * starts with the given parameter and end with any character. */
		stmt.setString(1, text + "%");
		stmt.setString(2, text + "%");
		stmt.setString(3, text + "%");
		stmt.setString(4, text + "%");
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			TitleEntity t = new TitleEntity();
			t.setCode(rs.getInt("code"));
			t.setName(rs.getString("name"));
			t.setGenre(rs.getString("genre"));
			t.setCost(rs.getDouble("cost"));
			t.setMediaFormat(rs.getString("media_format"));
			t.setTypeTitle(rs.getString("type_title"));
			
			this.ts.add(t);
		}
		
		return ts;
	}
	
	/**
	 * This method will be invoked right after create a title. This method will
	 * get the last auto-increment primary key id named code. Then the value is
	 * returned. This technique will make it possible to use the id (code) to 
	 * assign an entity child class.
	 * 
	 * @return the largest id (code). Otherwise zero.
	 */
	public Integer lastCode() throws SQLException {
		
		/*
		 * This query will look for the largest id number and will return
		 * it in a column named code.
		 */
		String sql = "SELECT MAX(code) AS code FROM title";
		
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		/* An Integer object is used since if a null is tried to assign 
		 * primitive value will throw an exception. An Integer object is 
		 * able to hold a null value and later on be handled.*/
		Integer code = null;
		
		// Goes through all columns in the database
		while(rs.next()) {
			code = rs.getInt("code");
		}
		
		return (code != null) ? code : 0;
	}

}

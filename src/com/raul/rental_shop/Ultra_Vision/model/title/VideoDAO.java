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
 * <p>Class VideoDAO is a class Data Access Object that handles the basic<br>
 * CRUD operations such as retrieving all data, retrieving a single data,<br>
 * adding a data, updating a data, and some other features. For further<br>
 * information check out the methods documentation.</p>
 * 
 * @role Handle database queries for the class VideoDAO
 * 
 * <p>The only attribute in this class is a List that sign a type of<br>
 * VideoDAO and instantiates an array list.</p>
 * 
 */
public class VideoDAO implements DAO<VideoEntity>{

	/* This attributes is populated with data from a database*/
	private List<VideoEntity> vds = new ArrayList<>();
	
	/**
	 * This method retrieves all values compatible with a VideoEntity
	 * based on an id (identification).
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of int. It refers to a video code
	 * number. It is expected that the value is a primary key in a 
	 * database table.
	 */
	@SuppressWarnings("static-access")
	@Override
	public VideoEntity get(int index) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class VideoEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM video WHERE code = ?";
		
		/* A singleton class called Database available at 
		 * package com.raul.rental_shop.Ultra_Vision.model is used to
		 * perform a database connection.*/
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		VideoEntity vd = new VideoEntity();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			vd.setCode(rs.getInt("code"));
			vd.setDirector(rs.getString("director"));
			vd.setDescription(rs.getString("description"));
		}
		
		return vd;
	}

	/**
	 * This method will return all data from a table that is compatible with
	 * a class VideoEntity attributes.
	 * 
	 * @return List signed by a class VideoEntity.
	 */
	@Override
	public List<VideoEntity> getAll() throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class VideoEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM video";
		
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			/* In this case a VideoEntity is instantiated every loop
			 * to be able to store all video values and then stores it
			 * in the list class attributes. */
			VideoEntity vd = new VideoEntity();
			
			vd.setCode(rs.getInt("code"));
			vd.setDirector(rs.getString("director"));
			vd.setDescription(rs.getString("description"));
			
			this.vds.add(vd);
		}
		
		return vds;
	}

	/**
	 * This method will store a data into a database compatible with a
	 * class VideoEntity attributes.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of VideoEntity. Be aware that the database table
	 * must to be compatible with the class VideoEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments were stored in the database.
	 * Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean add(VideoEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class VideoEntity. Please, check out its documentation*/
		String sql = "INSERT INTO video (code, director, description)"
				+ "VALUES (?, ?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setInt(1, t.getCode());
		stmt.setString(2, t.getDirector());
		stmt.setString(3, t.getDescription());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will remove a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of VideoEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of VideoEntity. Be aware that the database table
	 * must to be compatible with the class VideoEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are deleted according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean remove(VideoEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class VideoEntity. Please, check out its documentation*/
		String sql = "DELETE FROM video WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getCode());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will update a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of VideoEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of VideoEntity. Be aware that the database table
	 * must to be compatible with the class VideoEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are updated according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean update(VideoEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class VideoEntity. Please, check out its documentation*/
		String sql = "UPDATE video SET director = ?, description = ? WHERE code = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, t.getDirector());
		stmt.setString(2, t.getDescription());
		stmt.setInt(3, t.getCode());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will search for a record in the database that matches with
	 * the following table columns:
	 * 
	 * <ul>
	 * 	<li>code</li>
	 * 	<li>director</li>
	 * 	<li>description</li>
	 * </ul>
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of VideoEntity. Be aware that the database table
	 * must to be compatible with the class VideoEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return a List signed to VideoEntity. The values returned have to 
	 * match the passed argument. Otherwise it'll return an empty List.
	 */
	@SuppressWarnings("static-access")
	@Override
	public List<VideoEntity> search(String text) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class VideoEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM video WHERE code LIKE ? "
				+ "OR director LIKE ? OR description LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		/* The character '%' will make the database looks for a record thats
		 * starts with the given parameter and end with any character. */
		stmt.setString(1, text + "%");
		stmt.setString(2, text + "%");
		stmt.setString(3, text + "%");
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			VideoEntity vd = new VideoEntity();
			vd.setCode(rs.getInt("code"));
			vd.setDirector(rs.getString("director"));
			vd.setDescription(rs.getString("description"));
			
			this.vds.add(vd);
		}
		
		return vds;
	}

}

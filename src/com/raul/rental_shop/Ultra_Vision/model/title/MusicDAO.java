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
 * <p>Class MusicDAO is a class Data Access Object that handles the basic<br>
 * CRUD operations such as retrieving all data, retrieving a single data,<br>
 * adding a data, updating a data, and some other features. For further<br>
 * information check out the methods documentation.</p>
 * 
 * @role Handle database queries for the class MusicDAO
 * 
 * <p>The only attribute in this class is a List that sign a type of<br>
 * MusicDAO and instantiates an array list.</p>
 * 
 */
public class MusicDAO implements DAO<MusicEntity> {

	/* This attributes is populated with data from a database*/
	private List<MusicEntity> ms = new ArrayList<>();
	
	/**
	 * This method retrieves all values compatible with a MusicEntity
	 * based on an id (identification).
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of int. It refers to a music code
	 * number. It is expected that the value is a primary key in a 
	 * database table.
	 */
	@SuppressWarnings("static-access")
	@Override
	public MusicEntity get(int index) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MusicEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM music WHERE code = ?";
		
		/* A singleton class called Database available at 
		 * package com.raul.rental_shop.Ultra_Vision.model is used to
		 * perform a database connection.*/
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		MusicEntity m = new MusicEntity();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			m.setCode(rs.getInt("code"));
			m.setArtist(rs.getString("artist"));
			m.setAlbum(rs.getString("album"));
		}
		
		return m;
	}

	/**
	 * This method will return all data from a table that is compatible with
	 * a class MusicEntity attributes.
	 * 
	 * @return List signed by a class MusicEntity.
	 */
	@Override
	public List<MusicEntity> getAll() throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MusicEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM music";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			/* In this case a MusicEntity is instantiated every loop
			 * to be able to store all music values and then stores it
			 * in the list class attributes. */
			MusicEntity m = new MusicEntity();
			
			m.setCode(rs.getInt("code"));
			m.setArtist(rs.getString("artist"));
			m.setAlbum(rs.getString("album"));
			
			this.ms.add(m);
		}
		
		return ms;
	}

	/**
	 * This method will store a data into a database compatible with a
	 * class MusicEntity attributes.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of MusicEntity. Be aware that the database table
	 * must to be compatible with the class MusicEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments were stored in the database.
	 * Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean add(MusicEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MusicEntity. Please, check out its documentation*/
		String sql = "INSERT INTO music (code, artist, album)"
				+ "VALUES (?, ?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setInt(1, t.getCode());
		stmt.setString(2, t.getArtist());
		stmt.setString(3, t.getAlbum());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will remove a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of MusicEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of MusicEntity. Be aware that the database table
	 * must to be compatible with the class MusicEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are deleted according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean remove(MusicEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MusicEntity. Please, check out its documentation*/
		String sql = "DELETE FROM music WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getCode());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will update a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of MusicEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of MusicEntity. Be aware that the database table
	 * must to be compatible with the class MusicEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are updated according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean update(MusicEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MusicEntity. Please, check out its documentation*/
		String sql = "UPDATE music SET artist = ?, album = ? WHERE code = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, t.getArtist());
		stmt.setString(2, t.getAlbum());
		stmt.setDouble(3, t.getCode());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will search for a record in the database that matches with
	 * the following table columns:
	 * 
	 * <ul>
	 * 	<li>code</li>
	 * 	<li>artist</li>
	 * 	<li>album</li>
	 * </ul>
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of MusicEntity. Be aware that the database table
	 * must to be compatible with the class MusicEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return a List signed to MusicEntity. The values returned have to 
	 * match the passed argument. Otherwise it'll return an empty List.
	 */
	@SuppressWarnings("static-access")
	@Override
	public List<MusicEntity> search(String text) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MusicEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM music WHERE code LIKE ? "
				+ "OR artist LIKE ? OR album LIKE ?";
		
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
			
			MusicEntity m = new MusicEntity();
			m.setCode(rs.getInt("code"));
			m.setArtist(rs.getString("artist"));
			m.setAlbum(rs.getString("album"));
			
			this.ms.add(m);
		}
		
		return ms;
	}

}

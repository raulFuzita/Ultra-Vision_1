package com.raul.rental_shop.Ultra_Vision.model.customer;

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
 * <p>Class MembershipCardDAO is a class Data Access Object that handles the basic<br>
 * CRUD operations such as retrieving all data, retrieving a single data,<br>
 * adding a data, updating a data, and some other features. For further<br>
 * information check out the methods documentation.</p>
 * 
 * @role Handle database queries for the class CustomerEntity
 * 
 * <p>The only attribute in this class is a List that sign a type of<br>
 * MembershipCardDAO and instantiates an array list.</p>
 * 
 */
public class MembershipCardDAO implements DAO<MembershipCardEntity> {

	/* This attributes is populated with data from a database*/
	private List<MembershipCardEntity> cards = new ArrayList<>();
	
	/**
	 * This method retrieves all values compatible with a MembershipCardEntity
	 * based on an id (identification).
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of int. It refers to a customer membership
	 * number. It is expected that the value is a primary key in a 
	 * database table.
	 */
	@SuppressWarnings("static-access")
	@Override
	public MembershipCardEntity get(int index) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MembershipCardEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM membership_card WHERE idmembership_card = ?";
		
		/* A singleton class called Database available at 
		 * package com.raul.rental_shop.Ultra_Vision.model is used to
		 * perform a database connection.*/
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		MembershipCardEntity card = new MembershipCardEntity();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			card.setMembershipCardNumber(rs.getInt("idmembership_card"));
			card.setPoints(rs.getInt("points"));
		}
		
		return card;
	}

	/**
	 * This method will return all data from a table that is compatible with
	 * a class MembershipCardEntity attributes.
	 * 
	 * @return List signed by a class MembershipCardEntity.
	 */
	@Override
	public List<MembershipCardEntity> getAll() throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MembershipCardEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM membership_card";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			/* In this case a MembershipCardEntity is instantiated every loop
			 * to be able to store all membership card values and then stores it
			 * in the list class attributes. */
			MembershipCardEntity card = new MembershipCardEntity();
			card.setMembershipCardNumber(rs.getInt("idmembership_card"));
			card.setPoints(rs.getInt("points"));
			
			this.cards.add(card);
		}
		
		return cards;
	}

	/**
	 * This method will store a data into a database compatible with a
	 * class MembershipCardEntity attributes.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of MembershipCardEntity. Be aware that the database 
	 * table must to be compatible with the class MembershipCardEntity attributes. 
	 * Also it is recommended you check out the connection set in the singleton 
	 * class database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments were stored in the database.
	 * Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean add(MembershipCardEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MembershipCardEntity. Please, check out its documentation*/
		String sql = "INSERT INTO membership_card (idmembership_card, points)"
				+ "VALUES (?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setInt(1, t.getMembershipCardNumber());
		stmt.setInt(2, t.getPoints());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will remove a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of MembershipCardEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of MembershipCardEntity. Be aware that the database
	 * table must to be compatible with the class MembershipCardEntity attributes. 
	 * Also it is  recommended you check out the connection set in the singleton 
	 * class database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are deleted according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean remove(MembershipCardEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MembershipCardEntity. Please, check out its documentation*/
		String sql = "DELETE FROM membership_card WHERE idmembership_card = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getMembershipCardNumber());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will update a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of MembershipCardEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of MembershipCardEntity. Be aware that the database 
	 * table must to be compatible with the class MembershipCardEntity attributes.  
	 * Also it is recommended you check out the connection set in the singleton 
	 * class database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are updated according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean update(MembershipCardEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MembershipCardEntity. Please, check out its documentation*/
		String sql = "UPDATE membership_card SET points = ? "
				+ "WHERE idmembership_card = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setInt(1, t.getPoints());
		stmt.setInt(2, t.getMembershipCardNumber());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will search for a record in the database that matches with
	 * the following table columns:
	 * 
	 * <ul>
	 * 	<li>idmembership_card</li>
	 * 	<li>points</li>
	 * </ul>
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of MembershipCardEntity. Be aware that the database 
	 * table must to be compatible with the class MembershipCardEntity attributes.  
	 * Also it is recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return List signed to MembershipCardEntity. The values returned have to
	 * match the passed argument. Otherwise it'll return an empty List.
	 */
	@SuppressWarnings("static-access")
	@Override
	public List<MembershipCardEntity> search(String text) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class MembershipCardEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM membership_card WHERE "
				+ "idmembership_card LIKE ? OR points LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		/* The character '%' will make the database looks for a record thats
		 * starts with the given parameter and end with any character. */
		stmt.setString(1, text + "%");
		stmt.setString(2, text + "%");
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			MembershipCardEntity card = new MembershipCardEntity();
			card.setMembershipCardNumber(rs.getInt("idmembership_card"));
			card.setPoints(rs.getInt("points"));
			
			this.cards.add(card);
		}
		
		return cards;
	}

}

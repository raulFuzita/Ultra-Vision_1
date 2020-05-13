package com.raul.rental_shop.Ultra_Vision.model.rental;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
 * <p>Class RentalDAO is a class Data Access Object that handles the basic<br>
 * CRUD operations such as retrieving all data, retrieving a single data,<br>
 * adding a data, updating a data, and some other features. For further<br>
 * information check out the methods documentation.</p>
 * 
 * @role Handle database queries for the class RentalDAO
 * 
 * <p>The only attribute in this class is a List that sign a type of<br>
 * CustomerEntity and instantiates an array list.</p>
 * 
 */
public class RentalDAO implements DAO<RentalEntity> {

	/* This attributes is populated with data from a database*/
	private List<RentalEntity> rts = new ArrayList<>();
	
	/**
	 * This method retrieves all values compatible with a RentalEntity
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
	public RentalEntity get(int index) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class CustomerEntity. Please, check out its documentation*/
		String sql = "SELECT r.*, CONCAT(c.firstname, ' ', c.lastname) AS fullname,"
				+ " c.membership_plan, t.name, t.media_format FROM rental r"
				+ " INNER JOIN customer c ON c.membership_card = r.customer_membership_card"
				+ " INNER JOIN title t ON t.code = r.title_code"
				+ " WHERE r.customer_membership_card = ?";
		
		/* A singleton class called Database available at 
		 * package com.raul.rental_shop.Ultra_Vision.model is used to
		 * perform a database connection.*/
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		RentalEntity rt = new RentalEntity();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			rt.setId(rs.getInt("id"));
			rt.setCustomerMembershipNumber(rs.getInt("customer_membership_card"));
			rt.setTitleCode(rs.getInt("title_code"));
			rt.setRentAt(Timestamp.valueOf(rs.getString("rent_at")));
			rt.setReturnAt(Timestamp.valueOf(rs.getString("return_at")));
			boolean returned = (rs.getByte("is_returned") == 1) ? true : false;
			rt.setReturned(returned);
			rt.setFullname(rs.getString("fullname"));
			rt.setTypePlan(rs.getString("membership_plan"));
			rt.setTitleName(rs.getString("name"));
			rt.setMediaFormat(rs.getString("media_format"));
		}
		
		return rt;
	}

	/**
	 * This method will return all data from a table that is compatible with
	 * a class RentalEntity attributes.
	 * 
	 * @return List signed by a class RentalEntity.
	 */
	@Override
	public List<RentalEntity> getAll() throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class RentalEntity. Please, check out its documentation*/
		String sql = "SELECT r.*, CONCAT(c.firstname, ' ', c.lastname) AS fullname,"
				+ " c.membership_plan, t.name, t.media_format FROM rental r"
				+ " INNER JOIN customer c ON c.membership_card = r.customer_membership_card"
				+ " INNER JOIN title t ON t.code = r.title_code";
		
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			/* In this case a RentalEntity is instantiated every loop
			 * to be able to store all rental values and then stores it
			 * in the list class attributes. */
			RentalEntity rt = new RentalEntity();
			
			rt.setId(rs.getInt("id"));
			rt.setCustomerMembershipNumber(rs.getInt("customer_membership_card"));
			rt.setTitleCode(rs.getInt("title_code"));
			rt.setRentAt(Timestamp.valueOf(rs.getString("rent_at")));
			rt.setReturnAt(Timestamp.valueOf(rs.getString("return_at")));
			boolean returned = (rs.getByte("is_returned") == 1) ? true : false;
			rt.setReturned(returned);
			rt.setFullname(rs.getString("fullname"));
			rt.setTypePlan(rs.getString("membership_plan"));
			rt.setTitleName(rs.getString("name"));
			rt.setMediaFormat(rs.getString("media_format"));
			
			this.rts.add(rt);
		}
		
		return rts;
	}

	/**
	 * This method will store a data into a database compatible with a
	 * class RentalEntity attributes.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of RentalEntity. Be aware that the database table
	 * must to be compatible with the class RentalEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments were stored in the database.
	 * Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean add(RentalEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class RentalEntity. Please, check out its documentation*/
		String sql = "INSERT INTO rental (customer_membership_card,"
				+ " title_code, rent_at, return_at, is_returned)"
				+ "VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setInt(1, t.getCustomerMembershipNumber());
		stmt.setInt(2, t.getTitleCode());
		stmt.setString(3, t.getRentAt().toString());
		stmt.setString(4, t.getReturnAt().toString());
		byte returned = (byte) ((t.isReturned()) ? 1 : 0);
		stmt.setByte(5, returned);
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will remove a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of RentalEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of RentalEntity. Be aware that the database table
	 * must to be compatible with the class RentalEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are deleted according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean remove(RentalEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class RentalEntity. Please, check out its documentation*/
		String sql = "DELETE FROM rental WHERE id = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getId());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will update a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of RentalEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of RentalEntity. Be aware that the database table
	 * must to be compatible with the class RentalEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are updated according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean update(RentalEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class RentalEntity. Please, check out its documentation*/
		String sql = "UPDATE rental SET title_code = ?, rent_at = ?,"
				+ " return_at = ?, is_returned = ?"
				+ " WHERE customer_membership_card = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setInt(1, t.getTitleCode());
		stmt.setString(2, t.getRentAt().toString());
		stmt.setString(3, t.getReturnAt().toString());
		byte returned = (byte) ((t.isReturned()) ? 1 : 0);
		stmt.setByte(4, returned);
		stmt.setInt(5, t.getCustomerMembershipNumber());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will search for a record in the database that matches with
	 * the following table columns:
	 * 
	 * <ul>
	 * 	<li>customer_membership_card</li>
	 * 	<li>rent_at</li>
	 * 	<li>name</li>
	 * 	<li>media_format</li>
	 * 	<li>firstname</li>
	 * 	<li>lastname</li>
	 * </ul>
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of RentalEntity. Be aware that the database table
	 * must to be compatible with the class RentalEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return a List signed to RentalEntity. The values returned have to match
	 * the argument passed. Otherwise it returns an empty List.
	 */
	@SuppressWarnings("static-access")
	@Override
	public List<RentalEntity> search(String text) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class RentalEntity. Please, check out its documentation*/
		String sql = "SELECT r.*, CONCAT(c.firstname, ' ', c.lastname) AS fullname, "
				+ " c.membership_plan, t.name, t.media_format FROM rental r"
				+ " INNER JOIN customer c ON c.membership_card = r.customer_membership_card"
				+ " INNER JOIN title t ON t.code = r.title_code"
				+ " WHERE customer_membership_card LIKE ? "
				+ " OR rent_at LIKE ? OR t.name LIKE ?"
				+ " OR t.media_format LIKE ? OR c.firstname LIKE ?"
				+ " OR c.lastname LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		/* The character '%' will make the database looks for a record thats
		 * starts with the given parameter and end with any character. */
		stmt.setString(1, text + "%");
		stmt.setString(2, text + "%");
		stmt.setString(3, text + "%");
		stmt.setString(4, text + "%");
		stmt.setString(5, text + "%");
		stmt.setString(6, text + "%");
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			RentalEntity rt = new RentalEntity();
			
			rt.setId(rs.getInt("id"));
			rt.setCustomerMembershipNumber(rs.getInt("customer_membership_card"));
			rt.setTitleCode(rs.getInt("title_code"));
			rt.setRentAt(Timestamp.valueOf(rs.getString("rent_at")));
			rt.setReturnAt(Timestamp.valueOf(rs.getString("return_at")));
			boolean returned = (rs.getByte("is_returned") == 1) ? true : false;
			rt.setReturned(returned);
			rt.setFullname(rs.getString("fullname"));
			rt.setTypePlan(rs.getString("membership_plan"));
			rt.setTitleName(rs.getString("name"));
			rt.setMediaFormat(rs.getString("media_format"));
			
			this.rts.add(rt);
		}
		
		return rts;
	}

	
	/**
	 * This method will count the number of records signed to the primary key id
	 * passed as an argument.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of RentalEntity. Be aware that the database table
	 * must to be compatible with the class RentalEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return an int value which is the number of record signed with the
	 * primary key id.
	 */
	@SuppressWarnings("static-access")
	public int ownerRents(int index) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class RentalEntity. Please, check out its documentation*/
		String sql = "SELECT COUNT(customer_membership_card) AS rents"
				+ " FROM rental WHERE customer_membership_card = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();

		int rents = 0;
		
		while(rs.next()) {
			/* It'll look for a column named rents because in the SQL statement
			 * the columns customer_membership_card is renamed to rent temporally.*/
			rents = rs.getInt("rents");
		}
		
		return rents;
	}
	
	/**
	 * This method will retrieve all records of a database that has a primary key
	 * value of the passed argument.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param membershipCard is a type of RentalEntity. Be aware that the database table
	 * must to be compatible with the class RentalEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return a List signed to RentalEntity. This list is populated with all 
	 * records that refers to a customer in the database designed for the 
	 * class RentalEntity.
	 */
	@SuppressWarnings("static-access")
	public List<RentalEntity> ownersItems(int membershipCard) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class RentalEntity. Please, check out its documentation*/
		String sql = "SELECT r.*, CONCAT(c.firstname, ' ', c.lastname) AS fullname,"
				+ " c.membership_plan, t.name, t.media_format FROM rental r"
				+ " INNER JOIN customer c ON c.membership_card = r.customer_membership_card"
				+ " INNER JOIN title t ON t.code = r.title_code"
				+ " WHERE r.customer_membership_card = ?";
		
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setInt(1, membershipCard);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			RentalEntity rt = new RentalEntity();
			
			rt.setId(rs.getInt("id"));
			rt.setCustomerMembershipNumber(rs.getInt("customer_membership_card"));
			rt.setTitleCode(rs.getInt("title_code"));
			rt.setRentAt(Timestamp.valueOf(rs.getString("rent_at")));
			rt.setReturnAt(Timestamp.valueOf(rs.getString("return_at")));
			boolean returned = (rs.getByte("is_returned") == 1) ? true : false;
			rt.setReturned(returned);
			rt.setFullname(rs.getString("fullname"));
			rt.setTypePlan(rs.getString("membership_plan"));
			rt.setTitleName(rs.getString("name"));
			rt.setMediaFormat(rs.getString("media_format"));
			
			this.rts.add(rt);
		}
		
		return rts;
	}
	
	/**
	 * This method will search for a record in the database that matches with
	 * the following table columns:
	 * 
	 * <ul>
	 * 	<li>customer_membership_card and rent_at</li>
	 * 	<li>customer_membership_card and name</li>
	 * 	<li>customer_membership_card and media_format</li>
	 * 	<li>customer_membership_card and firstname</li>
	 * 	<li>customer_membership_card and lastname</li>
	 * </ul>
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param membershipCard is a type of RentalEntity. Be aware that the database 
	 * table must to be compatible with the class RentalEntity attributes.  
	 * Also it is recommended you check out the connection set in the singleton 
	 * class database. Otherwise it will throw an exception.
	 * 
	 * @param text is a type of String. It'll be used to look for a value 
	 * in the target columns that matches with this value and end with anything.
	 * 
	 * @return a List signed to RentalEntity that contains all records that 
	 * refers to the primary key passed as argument. It could returns nothing
	 * if no value if such a key word is found.
	 */
	@SuppressWarnings("static-access")
	public List<RentalEntity> ownerSearch(int membershipCard, String text) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class RentalEntity. Please, check out its documentation*/
		String sql = "SELECT r.*, CONCAT(c.firstname, ' ', c.lastname) AS fullname, "
				+ " c.membership_plan, t.name, t.media_format FROM rental r"
				+ " INNER JOIN customer c ON c.membership_card = r.customer_membership_card"
				+ " INNER JOIN title t ON t.code = r.title_code"
				+ " WHERE customer_membership_card = ? AND rent_at LIKE ? "
				+ " OR customer_membership_card = ? AND t.name LIKE ?"
				+ " OR customer_membership_card = ? AND t.media_format LIKE ? "
				+ " OR customer_membership_card = ? AND c.firstname LIKE ?"
				+ " OR customer_membership_card = ? AND c.lastname LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		/* The character '%' will make the database looks for a record thats
		 * starts with the given parameter and end with any character. */
		stmt.setInt(1, membershipCard);
		stmt.setString(2, text + "%");
		stmt.setInt(3, membershipCard);
		stmt.setString(4, text + "%");
		stmt.setInt(5, membershipCard);
		stmt.setString(6, text + "%");
		stmt.setInt(7, membershipCard);
		stmt.setString(8, text + "%");
		stmt.setInt(9, membershipCard);
		stmt.setString(10, text + "%");
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			RentalEntity rt = new RentalEntity();
			
			rt.setId(rs.getInt("id"));
			rt.setCustomerMembershipNumber(rs.getInt("customer_membership_card"));
			rt.setTitleCode(rs.getInt("title_code"));
			rt.setRentAt(Timestamp.valueOf(rs.getString("rent_at")));
			rt.setReturnAt(Timestamp.valueOf(rs.getString("return_at")));
			boolean returned = (rs.getByte("is_returned") == 1) ? true : false;
			rt.setReturned(returned);
			rt.setFullname(rs.getString("fullname"));
			rt.setTypePlan(rs.getString("membership_plan"));
			rt.setTitleName(rs.getString("name"));
			rt.setMediaFormat(rs.getString("media_format"));
			
			this.rts.add(rt);
		}
		
		return rts;
	}
	
}

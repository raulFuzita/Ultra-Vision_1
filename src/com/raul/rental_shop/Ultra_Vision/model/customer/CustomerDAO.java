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
 * <p>Class CustomerDAO is a class Data Access Object that handles the basic<br>
 * CRUD operations such as retrieving all data, retrieving a single data,<br>
 * adding a data, updating a data, and some other features. For further<br>
 * information check out the methods documentation.</p>
 * 
 * @role Handle database queries for the class CustomerDAO
 * 
 * <p>The only attribute in this class is a List that sign a type of<br>
 * CustomerEntity and instantiates an array list.</p>
 * 
 */
public class CustomerDAO implements DAO<CustomerEntity> {

	/* This attributes is populated with data from a database*/
	private List<CustomerEntity> users = new ArrayList<>();
	
	/**
	 * This method retrieves all values compatible with a CustomerEntity
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
	public CustomerEntity get(int index) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class CustomerEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM customer WHERE membership_card = ?";
		
		/* A singleton class called Database available at 
		 * package com.raul.rental_shop.Ultra_Vision.model is used to
		 * perform a database connection.*/
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		CustomerEntity user = new CustomerEntity();
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			user.setMembershipCardNumber(rs.getInt("membership_card"));
			user.setMembershipPlan(rs.getString("membership_plan"));
			user.setPassword(rs.getString("password"));
			user.setPrivilege(rs.getString("privilege"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setPhonenumber(rs.getString("phonenumber"));
			user.setStreet(rs.getString("street"));
			user.setCity(rs.getString("city"));
			user.setCountry(rs.getString("country"));
			user.setBankCard(rs.getString("bank_card"));
			user.setBirthday(rs.getString("birthday"));
			
		}
		
		return user;
	}

	/**
	 * This method will return all data from a table that is compatible with
	 * a class CustomerEntity attributes.
	 * 
	 * @return List signed by a class CustomerEntity.
	 */
	@Override
	public List<CustomerEntity> getAll() throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class CustomerEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM customer";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		// Goes through all columns in the database
		while(rs.next()) {
			
			/* In this case a CustomerEntity is instantiated every loop
			 * to be able to store all customer values and then stores it
			 * in the list class attributes. */
			CustomerEntity user = new CustomerEntity();
			
			user.setMembershipCardNumber(rs.getInt("membership_card"));
			user.setMembershipPlan(rs.getString("membership_plan"));
			user.setPassword(rs.getString("password"));
			user.setPrivilege(rs.getString("privilege"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setPhonenumber(rs.getString("phonenumber"));
			user.setStreet(rs.getString("street"));
			user.setCity(rs.getString("city"));
			user.setCountry(rs.getString("country"));
			user.setBankCard(rs.getString("bank_card"));
			user.setBirthday(rs.getString("birthday"));
			
			this.users.add(user);
		}
		
		return users;
	}

	/**
	 * This method will store a data into a database compatible with a
	 * class CustomerEntity attributes.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of CustomerEntity. Be aware that the database table
	 * must to be compatible with the class CustomerEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments were stored in the database.
	 * Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean add(CustomerEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class CustomerEntity. Please, check out its documentation*/
		String sql = "INSERT INTO customer "
				+ "(membership_plan, password, privilege, firstname, lastname, "
				+ "phonenumber, street, city, country, bank_card, birthday)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setString(1, t.getMembershipPlan());
		stmt.setString(2, t.getPassword());
		stmt.setString(3, t.getPrivilege());
		stmt.setString(4, t.getFirstname());
		stmt.setString(5, t.getLastname());
		stmt.setString(6, t.getPhonenumber());
		stmt.setString(7, t.getStreet());
		stmt.setString(8, t.getCity());
		stmt.setString(9, t.getCountry());
		stmt.setString(10, t.getBankCard());
		stmt.setString(11, t.getBirthday());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will remove a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of CustomerEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of CustomerEntity. Be aware that the database table
	 * must to be compatible with the class CustomerEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are deleted according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean remove(CustomerEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class CustomerEntity. Please, check out its documentation*/
		String sql = "DELETE FROM customer WHERE membership_card = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getMembershipCardNumber());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will update a record in the database according to the 
	 * attribute that represents the table primary key. Keep in mind that 
	 * the parameter is a type of CustomerEntity.
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of CustomerEntity. Be aware that the database table
	 * must to be compatible with the class CustomerEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return true if the passed arguments are updated according to the 
	 * aimed record in the database. Otherwise false.
	 */
	@SuppressWarnings("static-access")
	@Override
	public boolean update(CustomerEntity t) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class CustomerEntity. Please, check out its documentation*/
		String sql = "UPDATE customer SET membership_plan = ?, password = ?,"
				+ " privilege = ?, firstname = ?, lastname = ?, phonenumber = ?,"
				+ " street = ?, city=?, country = ?, bank_card = ?,"
				+ " birthday = ? WHERE membership_card = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, t.getMembershipPlan());
		stmt.setString(2, t.getPassword());
		stmt.setString(3, t.getPrivilege());
		stmt.setString(4, t.getFirstname());
		stmt.setString(5, t.getLastname());
		stmt.setString(6, t.getPhonenumber());
		stmt.setString(7, t.getStreet());
		stmt.setString(8, t.getCity());
		stmt.setString(9, t.getCountry());
		stmt.setString(10, t.getBankCard());
		stmt.setString(11, t.getBirthday());
		stmt.setInt(12, t.getMembershipCardNumber());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	/**
	 * This method will search for a record in the database that matches with
	 * the following table columns:
	 * 
	 * <ul>
	 * 	<li>membership_card</li>
	 * 	<li>firstname</li>
	 * 	<li>lastname</li>
	 * 	<li>phonenumber</li>
	 * </ul>
	 * 
	 * This method suppress a warning but it highlights that an static class
	 * is used inside of this method. The static class is a singleton class 
	 * Database.
	 * Suppress a warning following the right rules are recommended in the
	 * book Effective Java, Third Edition by Joshua Bloch.
	 * 
	 * @param index is a type of CustomerEntity. Be aware that the database table
	 * must to be compatible with the class CustomerEntity attributes. Also it is 
	 * recommended you check out the connection set in the singleton class
	 * database. Otherwise it will throw an exception.
	 * 
	 * @return a List signed to CustomerEntity. The values returned have to 
	 * match the passed argument. Otherwise it'll return an empty List.
	 */
	@SuppressWarnings("static-access")
	@Override
	public List<CustomerEntity> search(String text) throws SQLException {
		
		/* This query is based on the structure of a specific database table.
		 * Any change in your database or used of a new one should match up
		 * with the class CustomerEntity. Please, check out its documentation*/
		String sql = "SELECT * FROM customer WHERE membership_card LIKE ? "
				+ "OR firstname LIKE ? OR lastname LIKE ?"
				+ "OR phonenumber LIKE ?";
		
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
			
			CustomerEntity user = new CustomerEntity();
			user.setMembershipCardNumber(rs.getInt("membership_card"));
			user.setMembershipPlan(rs.getString("membership_plan"));
			user.setPassword(rs.getString("password"));
			user.setPrivilege(rs.getString("privilege"));
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			user.setPhonenumber(rs.getString("phonenumber"));
			user.setStreet(rs.getString("street"));
			user.setCity(rs.getString("city"));
			user.setCountry(rs.getString("country"));
			user.setBankCard(rs.getString("bank_card"));
			user.setBirthday(rs.getString("birthday"));
			
			this.users.add(user);
		}
		
		return users;
	}

}

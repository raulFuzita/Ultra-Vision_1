package com.raul.rental_shop.Ultra_Vision.model.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.Database;

public class CustomerDAO implements DAO<CustomerEntity> {

	List<CustomerEntity> users = new ArrayList<>();
	
	@SuppressWarnings("static-access")
	@Override
	public CustomerEntity get(int index) throws SQLException {
		
		String sql = "SELECT * FROM customer WHERE membership_card = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		CustomerEntity user = new CustomerEntity();
		
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

	@Override
	public List<CustomerEntity> getAll() throws SQLException {
		
		String sql = "SELECT * FROM customer";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
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

	@SuppressWarnings("static-access")
	@Override
	public boolean add(CustomerEntity t) throws SQLException {
		
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

	@SuppressWarnings("static-access")
	@Override
	public boolean remove(CustomerEntity t) throws SQLException {
		
		String sql = "DELETE FROM customer WHERE membership_card = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getMembershipCardNumber());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean update(CustomerEntity t) throws SQLException {
		
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

	@SuppressWarnings("static-access")
	@Override
	public List<CustomerEntity> search(String text) throws SQLException {
		
		String sql = "SELECT * FROM customer WHERE membership_card LIKE ? "
				+ "OR firstname LIKE ? OR lastname LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, text);
		stmt.setString(2, text);
		stmt.setString(3, text);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
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

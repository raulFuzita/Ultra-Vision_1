package com.raul.rental_shop.Ultra_Vision.model.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.Database;

public class MembershipCardDAO implements DAO<MembershipCardEntity> {

	List<MembershipCardEntity> cards = new ArrayList<>();
	
	@SuppressWarnings("static-access")
	@Override
	public MembershipCardEntity get(int index) throws SQLException {
		
		String sql = "SELECT * FROM membership_card WHERE idmembership_card = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		MembershipCardEntity card = new MembershipCardEntity();
		
		while(rs.next()) {
			
			card.setMembershipCardNumber(rs.getInt("idmembership_card"));
			card.setPoints(rs.getInt("points"));
		}
		
		return card;
	}

	@Override
	public List<MembershipCardEntity> getAll() throws SQLException {
		
		String sql = "SELECT * FROM membership_card";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		while(rs.next()) {
			
			MembershipCardEntity card = new MembershipCardEntity();
			card.setMembershipCardNumber(rs.getInt("idmembership_card"));
			card.setPoints(rs.getInt("points"));
			
			this.cards.add(card);
		}
		
		return cards;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean add(MembershipCardEntity t) throws SQLException {
		
		String sql = "INSERT INTO membership_card (idmembership_card, points)"
				+ "VALUES (?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setInt(1, t.getMembershipCardNumber());
		stmt.setInt(2, t.getPoints());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean remove(MembershipCardEntity t) throws SQLException {
		
		String sql = "DELETE FROM membership_card WHERE idmembership_card = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getMembershipCardNumber());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean update(MembershipCardEntity t) throws SQLException {
		
		String sql = "UPDATE membership_card SET points = ? "
				+ "WHERE idmembership_card = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setInt(1, t.getPoints());
		stmt.setInt(2, t.getMembershipCardNumber());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public List<MembershipCardEntity> search(String text) throws SQLException {
		
		String sql = "SELECT * FROM membership_card WHERE "
				+ "idmembership_card LIKE ? OR points LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, text);
		stmt.setString(2, text);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		while(rs.next()) {
			
			MembershipCardEntity card = new MembershipCardEntity();
			card.setMembershipCardNumber(rs.getInt("idmembership_card"));
			card.setPoints(rs.getInt("points"));
			
			this.cards.add(card);
		}
		
		return cards;
	}

}

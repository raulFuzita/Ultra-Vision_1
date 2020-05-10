package com.raul.rental_shop.Ultra_Vision.model.title;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.Database;

public class TVDAO implements DAO<TVEntity> {

	List<TVEntity> tvs = new ArrayList<>();
	
	@SuppressWarnings("static-access")
	@Override
	public TVEntity get(int index) throws SQLException {
		
		String sql = "SELECT * FROM tv WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		TVEntity tv = new TVEntity();
		
		while(rs.next()) {
			
			tv.setId(rs.getInt("id"));
			tv.setCode(rs.getInt("code"));
			tv.setCharacterSeries(rs.getString("characterSeries"));
		}
		
		return tv;
	}

	@Override
	public List<TVEntity> getAll() throws SQLException {
		
		String sql = "SELECT * FROM tv";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		while(rs.next()) {
			
			TVEntity tv = new TVEntity();
			
			tv.setId(rs.getInt("id"));
			tv.setCode(rs.getInt("code"));
			tv.setCharacterSeries(rs.getString("characterSeries"));
			
			this.tvs.add(tv);
		}
		
		return tvs;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean add(TVEntity t) throws SQLException {
		
		String sql = "INSERT INTO tv (id, code, characterSeries)"
				+ "VALUES (?, ?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setInt(1, t.getId());
		stmt.setInt(2, t.getCode());
		stmt.setString(3, t.getCharacterSeries());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean remove(TVEntity t) throws SQLException {
		
		String sql = "DELETE FROM tv WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getCode());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean update(TVEntity t) throws SQLException {
		
		String sql = "UPDATE tv SET characterSeries = ? WHERE code = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, t.getCharacterSeries());
		stmt.setInt(2, t.getCode());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public List<TVEntity> search(String text) throws SQLException {
		
		String sql = "SELECT * FROM tv WHERE code LIKE ? "
				+ "OR characterSeries LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, text);
		stmt.setString(2, text);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		while(rs.next()) {
			
			TVEntity tv = new TVEntity();
			tv.setId(rs.getInt("id"));
			tv.setCode(rs.getInt("code"));
			tv.setCharacterSeries(rs.getString("characterSeries"));
			
			this.tvs.add(tv);
		}
		
		return tvs;
	}

	
}

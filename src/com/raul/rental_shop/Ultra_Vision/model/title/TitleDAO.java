package com.raul.rental_shop.Ultra_Vision.model.title;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.Database;

public class TitleDAO implements DAO<TitleEntity> {

	List<TitleEntity> ts = new ArrayList<>();

	@SuppressWarnings("static-access")
	@Override
	public TitleEntity get(int index) throws SQLException {
		
		String sql = "SELECT * FROM title WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		TitleEntity t = new TitleEntity();
		
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

	@Override
	public List<TitleEntity> getAll() throws SQLException {
		
		String sql = "SELECT * FROM title";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		while(rs.next()) {
			
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

	@SuppressWarnings("static-access")
	@Override
	public boolean add(TitleEntity t) throws SQLException {
		
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

	@SuppressWarnings("static-access")
	@Override
	public boolean remove(TitleEntity t) throws SQLException {
		
		String sql = "DELETE FROM title WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getCode());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean update(TitleEntity t) throws SQLException {
		
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

	@SuppressWarnings("static-access")
	@Override
	public List<TitleEntity> search(String text) throws SQLException {
		
		String sql = "SELECT * FROM title WHERE code LIKE ? "
				+ "OR name LIKE ? OR genre LIKE ? OR media_format LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, text + "%");
		stmt.setString(2, text + "%");
		stmt.setString(3, text + "%");
		stmt.setString(4, text + "%");
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
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
	
	public Integer lastCode() throws SQLException {
		
		String sql = "SELECT MAX(code) AS code FROM title";
		
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		Integer code = null;
		
		while(rs.next()) {
			code = rs.getInt("code");
		}
		
		return (code != null) ? code : 0;
	}

}

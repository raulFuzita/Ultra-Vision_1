package com.raul.rental_shop.Ultra_Vision.model.title;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.Database;

public class MusicDAO implements DAO<MusicEntity> {

	List<MusicEntity> ms = new ArrayList<>();
	
	@SuppressWarnings("static-access")
	@Override
	public MusicEntity get(int index) throws SQLException {
		
		String sql = "SELECT * FROM music WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		MusicEntity m = new MusicEntity();
		
		while(rs.next()) {
			
			m.setId(rs.getInt("id"));
			m.setCode(rs.getInt("code"));
			m.setArtist(rs.getString("artist"));
			m.setAlbum(rs.getString("album"));
		}
		
		return m;
	}

	@Override
	public List<MusicEntity> getAll() throws SQLException {
		
		String sql = "SELECT * FROM music";
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		while(rs.next()) {
			
			MusicEntity m = new MusicEntity();
			
			m.setId(rs.getInt("id"));
			m.setCode(rs.getInt("code"));
			m.setArtist(rs.getString("artist"));
			m.setAlbum(rs.getString("album"));
			
			this.ms.add(m);
		}
		
		return ms;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean add(MusicEntity t) throws SQLException {
		
		String sql = "INSERT INTO music (code, artist, album)"
				+ "VALUES (?, ?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setInt(1, t.getCode());
		stmt.setString(2, t.getArtist());
		stmt.setString(3, t.getAlbum());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean remove(MusicEntity t) throws SQLException {
		
		String sql = "DELETE FROM music WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getCode());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean update(MusicEntity t) throws SQLException {
		
		String sql = "UPDATE music SET artist = ?, album = ? WHERE code = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, t.getArtist());
		stmt.setString(2, t.getAlbum());
		stmt.setDouble(3, t.getCode());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public List<MusicEntity> search(String text) throws SQLException {
		
		String sql = "SELECT * FROM music WHERE code LIKE ? "
				+ "OR artist LIKE ? OR album LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, text);
		stmt.setString(2, text);
		stmt.setString(3, text);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		while(rs.next()) {
			
			MusicEntity m = new MusicEntity();
			m.setId(rs.getInt("id"));
			m.setCode(rs.getInt("code"));
			m.setArtist(rs.getString("artist"));
			m.setAlbum(rs.getString("album"));
			
			this.ms.add(m);
		}
		
		return ms;
	}

}

package com.raul.rental_shop.Ultra_Vision.model.title;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.raul.rental_shop.Ultra_Vision.model.DAO;
import com.raul.rental_shop.Ultra_Vision.model.Database;

public class VideoDAO implements DAO<VideoEntity>{

	List<VideoEntity> vds = new ArrayList<>();
	
	@SuppressWarnings("static-access")
	@Override
	public VideoEntity get(int index) throws SQLException {
		
		String sql = "SELECT * FROM video WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, index);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
		VideoEntity vd = new VideoEntity();
		
		while(rs.next()) {
			
			vd.setCode(rs.getInt("code"));
			vd.setDirector(rs.getString("director"));
			vd.setDescription(rs.getString("description"));
		}
		
		return vd;
	}

	@Override
	public List<VideoEntity> getAll() throws SQLException {
		
		String sql = "SELECT * FROM video";
		
		ResultSet rs = Database.getStatment().executeQuery(sql);
		
		while(rs.next()) {
			
			VideoEntity vd = new VideoEntity();
			
			vd.setCode(rs.getInt("code"));
			vd.setDirector(rs.getString("director"));
			vd.setDescription(rs.getString("description"));
			
			this.vds.add(vd);
		}
		
		return vds;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean add(VideoEntity t) throws SQLException {
		
		String sql = "INSERT INTO video (code, director, description)"
				+ "VALUES (?, ?, ?)";
		
		PreparedStatement stmt = Database.setPreparedStmt(sql).getPreparedStmt();
		
		stmt.setInt(1, t.getCode());
		stmt.setString(2, t.getDirector());
		stmt.setString(3, t.getDescription());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean remove(VideoEntity t) throws SQLException {
		
		String sql = "DELETE FROM video WHERE code = ?";
		
		Database.setPreparedStmt(sql)
			.getPreparedStmt()
			.setInt(1, t.getCode());
		
		int result = Database.getPreparedStmt().executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public boolean update(VideoEntity t) throws SQLException {
		
		String sql = "UPDATE video SET director = ?, description = ? WHERE code = ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, t.getDirector());
		stmt.setString(2, t.getDescription());
		stmt.setInt(3, t.getCode());
		
		int result = stmt.executeUpdate();
		
		return (result > 0) ? true : false;
	}

	@SuppressWarnings("static-access")
	@Override
	public List<VideoEntity> search(String text) throws SQLException {
		
		String sql = "SELECT * FROM video WHERE code LIKE ? "
				+ "OR director LIKE ? OR description LIKE ?";
		
		Database.getInstance().connect().setPreparedStmt(sql);
		PreparedStatement stmt = Database.getPreparedStmt();
		
		stmt.setString(1, text);
		stmt.setString(2, text);
		stmt.setString(3, text);
		
		ResultSet rs = Database.getPreparedStmt()
				.executeQuery();
		
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

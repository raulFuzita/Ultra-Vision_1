package com.raul.rental_shop.Ultra_Vision.model;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

	public T get(int index) throws SQLException;
	public List<T> getAll() throws SQLException;
	public boolean add(T t) throws SQLException;
	public boolean remove(T t) throws SQLException;
	public boolean update(T t) throws SQLException;
	public List<T> search(String text) throws SQLException;
}

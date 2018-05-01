package ua.edu.dao;

import java.sql.SQLException;

public interface GenericDao<T>{
	
	public abstract void create(T t) throws SQLException;
	public abstract void update(T t) throws SQLException;
	public abstract void delete(int id) throws SQLException;
	public abstract T getById(int id) throws SQLException;
	
}
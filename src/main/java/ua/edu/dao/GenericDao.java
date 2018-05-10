package ua.edu.dao;

public interface GenericDao<T>{
	
	public abstract void create(T t);
	public abstract void update(T t);
	public abstract void delete(int id);
	
}
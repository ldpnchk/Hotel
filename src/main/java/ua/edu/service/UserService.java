package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.DaoFactory;
import ua.edu.dao.JdbcDaoFactory;
import ua.edu.dao.UserDao;
import ua.edu.dao.connection.DataSource;
import ua.edu.entity.User;

public class UserService {
	
	DaoFactory daoFactory = new JdbcDaoFactory();
	
	public void createUser(User user){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	UserDao userDao = daoFactory.createUserDao(connection);
    		userDao.create(user);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	UserDao userDao = daoFactory.createUserDao(connection);
    		userDao.update(user);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int id){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	UserDao userDao = daoFactory.createUserDao(connection);
    		userDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

package ua.edu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import ua.edu.controller.util.validator.UserValidator;
import ua.edu.model.dao.UserDao;
import ua.edu.model.entity.User;
import ua.edu.model.exception.GeneralInvalidInputException;

public class UserService extends Service{
	
	private static UserService instance;
	
	private UserService(){
		
	}
	
	public static UserService getInstance(){
        if(instance == null){
            synchronized (UserService.class){
                if(instance == null){
                	instance = new UserService();
                }
            }
        }
        return instance;
    }
	
	public void createUser(User user) throws GeneralInvalidInputException{
		UserValidator.getInstance().validateUser(user);
        try (Connection connection = dataSource.getConnection()){
        	UserDao userDao = daoFactory.createUserDao(connection);
    		userDao.create(user);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user){
        try (Connection connection = dataSource.getConnection()){
        	UserDao userDao = daoFactory.createUserDao(connection);
    		userDao.update(user);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int id){
        try (Connection connection = dataSource.getConnection()){
        	UserDao userDao = daoFactory.createUserDao(connection);
    		userDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Optional<User> getUserByUsername(String username){
        try (Connection connection = dataSource.getConnection()){
        	UserDao userDao = daoFactory.createUserDao(connection);
    		return userDao.getUserByUsername(username);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public Optional<User> getUserByEmail(String email){
        try (Connection connection = dataSource.getConnection()){
        	UserDao userDao = daoFactory.createUserDao(connection);
    		return userDao.getUserByEmail(email);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}

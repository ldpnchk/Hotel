package ua.edu.dao;

import java.util.Optional;

import ua.edu.entity.User;

public interface UserDao extends GenericDao<User>{
	
	Optional<User> getUserByUsername(String username);

}

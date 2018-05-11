package ua.edu.model.dao;

import java.util.Optional;

import ua.edu.model.entity.User;

public interface UserDao extends GenericDao<User>{
	
	Optional<User> getUserByUsername(String username);

}

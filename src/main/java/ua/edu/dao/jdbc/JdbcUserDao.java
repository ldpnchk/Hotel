package ua.edu.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.UserDao;
import ua.edu.entity.User;

public class JdbcUserDao implements UserDao{
	
	private static final String INSERT = "INSERT INTO users (login, password, email, phone_number, email, "
			+ "phone_number, first_name, last_name, patronymic, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE users SET login = ?, password = ?, email = ?, phone_number = ?, "
			+ "first_name = ?, last_name = ?, patronymic = ?, role = ? WHERE users_id = ?;";
	private static final String DELETE = "DELETE FROM use WHERE use_id=?";
	private static final String GET_BY_ID = "SELECT * FROM user WHERE user_id=?;";

	private Connection connection;
	
	public JdbcUserDao(Connection connection){
		this.connection = connection;
	}

	public void create(User t) throws SQLException {
		
	}

	public void update(User t) throws SQLException {
		
	}

	public void delete(int id) throws SQLException {
		
	}

	public User getById(int id) throws SQLException {
		return null;
	}

}

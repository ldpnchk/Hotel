package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Optional;

import ua.edu.dao.UserDao;
import ua.edu.entity.User;
import ua.edu.entity.UserRole;

public class MySQLUserDAO implements UserDao{
	
	private static final String INSERT = "INSERT INTO users (username, password, email, phone_number, "
			+ "first_name, last_name, patronymic, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE users SET username = ?, password = ?, email = ?, phone_number = ?, "
			+ "first_name = ?, last_name = ?, patronymic = ?, role = ? WHERE users_id = ?;";
	private static final String DELETE = "DELETE FROM users WHERE users_id = ?;";
	private static final String GET_BY_USERNAME = "SELECT * FROM users WHERE username = ?;";

	private Connection connection;
	
	public MySQLUserDAO(Connection connection){
		this.connection = connection;
	}

	public void create(User user) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		createStatement.setString(1, user.getUsername());
		createStatement.setString(2, user.getPassword());
		createStatement.setString(3, user.getEmail());
		createStatement.setString(4, user.getPhoneNumber());
		createStatement.setString(5, user.getFirstName());
		createStatement.setString(6, user.getLastName());
		if (user.getPatronymic() != null){
			createStatement.setString(7, user.getPatronymic());
		} else {
			createStatement.setNull(7, Types.VARCHAR);
		}
		createStatement.setString(8, user.getUserRole().name().toLowerCase());
		createStatement.executeUpdate();
		
		ResultSet rs = createStatement.getGeneratedKeys();
		if (rs.next()) {
			user.setId(rs.getInt(1));
		}
		
		createStatement.close();
	}

	public void update(User user) throws SQLException {
		PreparedStatement updateStatement = connection.prepareStatement(UPDATE);
		updateStatement.setString(1, user.getUsername());
		updateStatement.setString(2, user.getPassword());
		updateStatement.setString(3, user.getEmail());
		updateStatement.setString(4, user.getPhoneNumber());
		updateStatement.setString(5, user.getFirstName());
		updateStatement.setString(6, user.getLastName());
		if (user.getPatronymic() != null){
			updateStatement.setString(7, user.getPatronymic());
		} else {
			updateStatement.setNull(7, Types.VARCHAR);
		}
		updateStatement.setString(8, user.getUserRole().name().toLowerCase());
		updateStatement.setInt(9, user.getId());
		updateStatement.executeUpdate();
		updateStatement.close();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement(DELETE);
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		Optional<User> user = Optional.empty();
		try (PreparedStatement query = connection.prepareStatement(GET_BY_USERNAME)) {
			query.setString(1, username);
			ResultSet resultSet = query.executeQuery();
			if (resultSet.next()) {
				user = Optional.of(extractUserFromResultSet(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return user;
	}
	
	private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getInt("users_id"));
		user.setUsername(resultSet.getString("username"));
		user.setPassword(resultSet.getString("password"));
		user.setEmail(resultSet.getString("email"));
		user.setPhoneNumber(resultSet.getString("phone_number"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setPatronymic(resultSet.getString("patronymic"));
		user.setUserRole(UserRole.valueOf(resultSet.getString("role").toUpperCase()));
		return user;
	}
}

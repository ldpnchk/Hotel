package ua.edu.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import ua.edu.dao.UserDao;
import ua.edu.entity.User;

public class JdbcUserDao implements UserDao{
	
	private static final String INSERT = "INSERT INTO users (login, password, email, phone_number, "
			+ "first_name, last_name, patronymic, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE users SET login = ?, password = ?, email = ?, phone_number = ?, "
			+ "first_name = ?, last_name = ?, patronymic = ?, role = ? WHERE users_id = ?;";
	private static final String DELETE = "DELETE FROM use WHERE use_id=?";

	private Connection connection;
	
	public JdbcUserDao(Connection connection){
		this.connection = connection;
	}

	public void create(User user) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement(INSERT);
		createStatement.setString(1, user.getLogin());
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
		createStatement.setString(8, user.getRole().name().toLowerCase());
		createStatement.executeUpdate();
		
		ResultSet rs = createStatement.getGeneratedKeys();
		if (rs.next()) {
			user.setId(rs.getInt(1));
		}
	}

	public void update(User user) throws SQLException {
		PreparedStatement updateStatement = connection.prepareStatement(UPDATE);
		updateStatement.setString(1, user.getLogin());
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
		updateStatement.setString(8, user.getRole().name().toLowerCase());
		updateStatement.executeUpdate();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement(DELETE);
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
	}
}

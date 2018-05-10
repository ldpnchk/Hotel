package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Optional;

import org.apache.log4j.Logger;

import ua.edu.dao.UserDao;
import ua.edu.entity.User;
import ua.edu.entity.UserRole;
import ua.edu.exception.DatabaseException;
import ua.edu.util.ConfigManager;

public class MySQLUserDAO implements UserDao{
	
	final static Logger logger = Logger.getLogger(MySQLUserDAO.class);

	private Connection connection;
	
	public MySQLUserDAO(Connection connection){
		this.connection = connection;
	}

	public void create(User user){
		try (PreparedStatement createStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_USER_INSERT), 
						Statement.RETURN_GENERATED_KEYS)){
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
		} catch (SQLException e){
			logger.error("MySQLUserDAO create error: " + user.toString(), e);
			throw new DatabaseException();
		}
	}

	public void update(User user){
		try (PreparedStatement updateStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_USER_UPDATE))){
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
		} catch (SQLException e){
			logger.error("MySQLUserDAO update error: " + user.toString(), e);
			throw new DatabaseException();
		}
	}

	public void delete(int id){
		try (PreparedStatement deleteStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_USER_DELETE))){
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLUserDAO delete error: " + id, e);
			throw new DatabaseException();
		}
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		Optional<User> user = Optional.empty();
		try (PreparedStatement query = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_USER_GET_BY_USERNAME))) {
			query.setString(1, username);
			ResultSet resultSet = query.executeQuery();
			if (resultSet.next()) {
				user = Optional.of(extractUserFromResultSet(resultSet));
			}
		} catch (SQLException e){
			logger.error("MySQLUserDAO getUserByUsername error: " + username, e);
			throw new DatabaseException();
		}
		return user;
	}
	
	static User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
		return new User.UserBuilder()
				.setId(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.USERS_USERS_ID)))
				.setUsername(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.USERS_USERNAME)))
				.setPassword(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.USERS_PASSWORD)))
				.setEmail(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.USERS_EMAIL)))
				.setPhoneNumber(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.USERS_PHONE_NUMBER)))
				.setFirstName(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.USERS_FIRST_NAME)))
				.setLastName(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.USERS_LAST_NAME)))
				.setPatronymic(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.USERS_PATRONYMIC)))
				.setUserRole(UserRole.getUserRole(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.USERS_ROLE))))
				.build();
	}
}

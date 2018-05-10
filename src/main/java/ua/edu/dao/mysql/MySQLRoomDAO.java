package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ua.edu.dao.RoomDao;
import ua.edu.entity.Room;
import ua.edu.exception.DatabaseException;
import ua.edu.util.ConfigManager;

public class MySQLRoomDAO implements RoomDao{
	
	final static Logger logger = Logger.getLogger(MySQLRoomDAO.class);
	
	private Connection connection;
	
	public MySQLRoomDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Room room){
		try (PreparedStatement createStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_INSERT), 
						Statement.RETURN_GENERATED_KEYS)){
			createStatement.setString(1, room.getRoomNumber());
			createStatement.setInt(2, room.getRoomType().getId());
			createStatement.executeUpdate();
			
			ResultSet rs = createStatement.getGeneratedKeys();
			if (rs.next()) {
				room.setId(rs.getInt(1));
			}
		} catch (SQLException e){
			logger.error("MySQLRoomDAO create error: " + room.toString(), e);
			throw new DatabaseException();
		}
	}

	public void update(Room room){
		try (PreparedStatement updateStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_UPDATE))){
			updateStatement.setString(1, room.getRoomNumber());
			updateStatement.setInt(2, room.getRoomType().getId());
			updateStatement.setInt(3, room.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLRoomDAO update error: " + room.toString(), e);
			throw new DatabaseException();
		}
	}

	public void delete(int id){
		try (PreparedStatement deleteStatement = connection.prepareStatement(
				ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_DELETE))){
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLRoomDAO delete error: " + id, e);
			throw new DatabaseException();
		}
	}
	
	static Room extractRoomFromResultSet(ResultSet resultSet) throws SQLException {
		return new Room.RoomBuilder()
				.setId(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.ROOM_ROOM_ID)))
				.setRoomNumber(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.ROOM_ROOM_NUMBER)))
				.build();
	}

}

package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.edu.dao.RoomDao;
import ua.edu.entity.Room;
import ua.edu.util.ConfigurationManager;

public class MySQLRoomDAO implements RoomDao{
	
	private Connection connection;
	
	public MySQLRoomDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Room room) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_INSERT), 
						Statement.RETURN_GENERATED_KEYS);
		createStatement.setString(1, room.getRoomNumber());
		createStatement.setInt(2, room.getRoomType().getId());
		createStatement.executeUpdate();
		
		ResultSet rs = createStatement.getGeneratedKeys();
		if (rs.next()) {
			room.setId(rs.getInt(1));
		}
		
		createStatement.close();
	}

	public void update(Room room) throws SQLException {
		PreparedStatement updateStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_UPDATE));
		updateStatement.setString(1, room.getRoomNumber());
		updateStatement.setInt(2, room.getRoomType().getId());
		updateStatement.setInt(3, room.getId());
		updateStatement.executeUpdate();
		updateStatement.close();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement(
				ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_DELETE));
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}

}

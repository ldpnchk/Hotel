package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import ua.edu.dao.RoomTypeDao;
import ua.edu.entity.RoomType;
import ua.edu.util.ConfigurationManager;

public class MySQLRoomTypeDAO implements RoomTypeDao{
	
	private Connection connection;
	
	public MySQLRoomTypeDAO(Connection connection){
		this.connection = connection;
	}

	public void create(RoomType roomType) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_TYPE_INSERT), 
						Statement.RETURN_GENERATED_KEYS);
		createStatement.setString(1, roomType.getName());
		createStatement.setInt(2, roomType.getCapacity());
		createStatement.setInt(3, roomType.getPrice());
		if (roomType.getDescription() != null){
			createStatement.setString(4, roomType.getDescription());
		} else {
			createStatement.setNull(4, Types.VARCHAR);
		}
		createStatement.executeUpdate();
		
		ResultSet rs = createStatement.getGeneratedKeys();
		if (rs.next()) {
			roomType.setId(rs.getInt(1));
		}
		
		createStatement.close();
	}

	public void update(RoomType roomType) throws SQLException {
		PreparedStatement updateStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_TYPE_UPDATE));
		updateStatement.setString(1, roomType.getName());
		updateStatement.setInt(2, roomType.getCapacity());
		updateStatement.setInt(3, roomType.getPrice());
		if (roomType.getDescription() != null){
			updateStatement.setString(4, roomType.getDescription());
		} else {
			updateStatement.setNull(4, Types.VARCHAR);
		}
		updateStatement.setInt(5, roomType.getId());
		updateStatement.executeUpdate();
		updateStatement.close();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_TYPE_DELETE));
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}

}

package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.edu.dao.RoomDao;
import ua.edu.entity.Room;

public class MySQLRoomDAO implements RoomDao{
	
	private static final String INSERT = "INSERT INTO room (room_number, room_type_id) VALUES (?, ?);";
	private static final String UPDATE = "UPDATE room SET room_number = ?, room_type_id = ? WHERE room_id = ?;";
	private static final String DELETE = "DELETE FROM room WHERE room_id=?";
	
	private Connection connection;
	
	public MySQLRoomDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Room room) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
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
		PreparedStatement updateStatement = connection.prepareStatement(UPDATE);
		updateStatement.setString(1, room.getRoomNumber());
		updateStatement.setInt(2, room.getRoomType().getId());
		updateStatement.setInt(3, room.getId());
		updateStatement.executeUpdate();
		updateStatement.close();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement(DELETE);
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}

}

package ua.edu.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ua.edu.dao.RoomDao;
import ua.edu.entity.Room;

public class JdbcRoomDao implements RoomDao{
	
	private static final String INSERT = "INSERT INTO room (number, room_type_id) VALUES (?, ?);";
	private static final String UPDATE = "UPDATE room SET number = ?, room_type_id = ? WHERE room_id = ?;";
	private static final String DELETE = "DELETE FROM room WHERE room_id=?";
	
	private Connection connection;
	
	public JdbcRoomDao(Connection connection){
		this.connection = connection;
	}

	public void create(Room room) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement(INSERT);
		createStatement.setString(1, room.getNumber());
		createStatement.setInt(2, room.getRoomType().getId());
		createStatement.executeUpdate();
		
		ResultSet rs = createStatement.getGeneratedKeys();
		if (rs.next()) {
			room.setId(rs.getInt(1));
		}
	}

	public void update(Room room) throws SQLException {
		PreparedStatement updateStatement = connection.prepareStatement(UPDATE);
		updateStatement.setString(1, room.getNumber());
		updateStatement.setInt(2, room.getRoomType().getId());
		updateStatement.executeUpdate();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement(DELETE);
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
	}

}

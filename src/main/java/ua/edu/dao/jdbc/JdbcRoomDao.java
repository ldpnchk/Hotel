package ua.edu.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.RoomDao;
import ua.edu.entity.Room;

public class JdbcRoomDao implements RoomDao{
	
	private static final String INSERT = "INSERT INTO room (number, room_type_id) VALUES (?, ?);";
	private static final String UPDATE = "UPDATE room SET number = ?, room_type_id = ? WHERE room_id = ?;";
	private static final String DELETE = "DELETE FROM room WHERE room_id=?";
	private static final String GET_BY_ID = "SELECT * FROM room WHERE room_id=?;";
	
	private Connection connection;
	
	public JdbcRoomDao(Connection connection){
		this.connection = connection;
	}

	public void create(Room t) throws SQLException {
		
	}

	public void update(Room t) throws SQLException {
		
	}

	public void delete(int id) throws SQLException {
		
	}

	public Room getById(int id) throws SQLException {
		return null;
	}

}

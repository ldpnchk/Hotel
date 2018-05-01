package ua.edu.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.RoomTypeDao;
import ua.edu.entity.RoomType;

public class JdbcRoomTypeDao implements RoomTypeDao{
	
	private static final String INSERT = "INSERT INTO room_type (name, capacity, price, description) "
			+ "VALUES (?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE room_type SET name = ?, capacity = ?, price = ?, "
			+ "description = ? WHERE room_type_id = ?;";
	private static final String DELETE = "DELETE FROM room_type WHERE room_type_id=?";
	private static final String GET_BY_ID = "SELECT * FROM room_type WHERE room_type_id=?;";
	
	private Connection connection;
	
	public JdbcRoomTypeDao(Connection connection){
		this.connection = connection;
	}

	public void create(RoomType t) throws SQLException {
		
	}

	public void update(RoomType t) throws SQLException {
		
	}

	public void delete(int id) throws SQLException {
		
	}

	public RoomType getById(int id) throws SQLException {
		return null;
	}

}

package ua.edu.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.ReservationDao;
import ua.edu.entity.Reservation;

public class JdbcReservationDao implements ReservationDao{
	
	private static final String INSERT = "INSERT INTO reservation (reservation_date, start_date, "
			+ "end_date, user_comment, administrator_comment, status, users_id, room_type_id, room_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String UPDATE = "UPDATE reservation SET reservation_date = ?, start_date = ?, "
			+ "end_date = ?, user_comment = ?, administrator_comment = ?, status = ?, users_id = ?, "
			+ "room_type_id = ?, room_id = ? WHERE reservation_id = ?;";
	private static final String DELETE = "DELETE FROM reservation WHERE reservation_id=?";
	private static final String GET_BY_ID = "SELECT * FROM reservation WHERE reservation_id=?;";
	
	private Connection connection;
	
	public JdbcReservationDao(Connection connection){
		this.connection = connection;
	}

	public void create(Reservation t) throws SQLException {
		
	}

	public void update(Reservation t) throws SQLException {
		
	}

	public void delete(int id) throws SQLException {
		
	}

	public Reservation getById(int id) throws SQLException {
		return null;
	}

}

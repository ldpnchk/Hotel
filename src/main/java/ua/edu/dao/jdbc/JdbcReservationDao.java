package ua.edu.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

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
	
	private Connection connection;
	
	public JdbcReservationDao(Connection connection){
		this.connection = connection;
	}

	public void create(Reservation reservation) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		createStatement.setDate(1, new Date(reservation.getReservationDate().getTime()));
		createStatement.setDate(2, new Date(reservation.getStartDate().getTime()));
		createStatement.setDate(3, new Date(reservation.getEndDate().getTime()));
		if (reservation.getClientComment() != null){
			createStatement.setString(4, reservation.getClientComment());
		} else {
			createStatement.setNull(4, Types.VARCHAR);
		}
		if (reservation.getAdministratorComment() != null){
			createStatement.setString(5, reservation.getAdministratorComment());
		} else {
			createStatement.setNull(5, Types.VARCHAR);
		}
		createStatement.setString(6, reservation.getReservationStatus().name().toLowerCase());
		createStatement.setInt(7, reservation.getClient().getId());
		createStatement.setInt(8, reservation.getRoomType().getId());
		if (reservation.getRoom() != null){
			createStatement.setInt(9, reservation.getRoom().getId());
		} else {
			createStatement.setNull(9, Types.INTEGER);
		}
		createStatement.executeUpdate();
		
		ResultSet rs = createStatement.getGeneratedKeys();
		if (rs.next()) {
			reservation.setId(rs.getInt(1));
		}
		
		createStatement.close();
	}

	public void update(Reservation reservation) throws SQLException {
		PreparedStatement updateStatement = connection.prepareStatement(UPDATE);
		updateStatement.setDate(1, new Date(reservation.getReservationDate().getTime()));
		updateStatement.setDate(2, new Date(reservation.getStartDate().getTime()));
		updateStatement.setDate(3, new Date(reservation.getEndDate().getTime()));
		if (reservation.getClientComment() != null){
			updateStatement.setString(4, reservation.getClientComment());
		} else {
			updateStatement.setNull(4, Types.VARCHAR);
		}
		if (reservation.getAdministratorComment() != null){
			updateStatement.setString(5, reservation.getAdministratorComment());
		} else {
			updateStatement.setNull(5, Types.VARCHAR);
		}
		updateStatement.setString(6, reservation.getReservationStatus().name().toLowerCase());
		updateStatement.setInt(7, reservation.getClient().getId());
		updateStatement.setInt(8, reservation.getRoomType().getId());
		if (reservation.getRoom() != null){
			updateStatement.setInt(9, reservation.getRoom().getId());
		} else {
			updateStatement.setNull(9, Types.INTEGER);
		}
		updateStatement.setInt(10, reservation.getId());
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

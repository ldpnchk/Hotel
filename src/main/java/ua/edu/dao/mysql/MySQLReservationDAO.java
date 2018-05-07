package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Optional;

import ua.edu.dao.ReservationDao;
import ua.edu.entity.Reservation;
import ua.edu.entity.ReservationStatus;
import ua.edu.util.ConfigurationManager;

public class MySQLReservationDAO implements ReservationDao{
	
	private Connection connection;
	
	public MySQLReservationDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Reservation reservation) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_RESERVATION_INSERT), 
						Statement.RETURN_GENERATED_KEYS);
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
		PreparedStatement updateStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_RESERVATION_UPDATE));
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
		PreparedStatement deleteStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_RESERVATION_DELETE));
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}

	@Override
	public Optional<Reservation> getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(int id) {
		Optional<Reservation> reservation = Optional.empty();
		try (PreparedStatement query = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_RESERVATION_GET_BY_ID_WITH_USER_AND_ROOM_TYPE_AND_ROOM_AND_PAYMENT))) {
			query.setInt(1, id);
			ResultSet resultSet = query.executeQuery();
			if (resultSet.next()) {
				reservation = Optional.of(extractReservationWithUserAndRoomTypeAndRoomAndPaymentFromResultSet(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return reservation;
	}
	
	private Reservation extractReservationWithUserAndRoomTypeAndRoomAndPaymentFromResultSet(ResultSet resultSet) throws SQLException{
		Reservation reservation = extractReservationFromResultSet(resultSet);
		reservation.setClient(MySQLUserDAO.extractUserFromResultSet(resultSet));
		reservation.setRoomType(MySQLRoomTypeDAO.extractRoomTypeFromResultSet(resultSet));
		
		resultSet.getInt(ConfigurationManager.getInstance().getString(ConfigurationManager.PAYMENT_PAYMENT_ID));
		if (!resultSet.wasNull()){
			reservation.setPayment(MySQLPaymentDAO.extractPaymentFromResultSet(resultSet));
		}
		
		resultSet.getInt(ConfigurationManager.getInstance().getString(ConfigurationManager.ROOM_ROOM_ID));
		if (!resultSet.wasNull()){
			reservation.setRoom(MySQLRoomDAO.extractRoomFromResultSet(resultSet));
		}
		
		return reservation;
	}

	static Reservation extractReservationFromResultSet(ResultSet resultSet) throws SQLException {
		return new Reservation.ReservationBuilder()
				.setId(resultSet.getInt(ConfigurationManager.getInstance().getString(ConfigurationManager.RESERVATION_RESERVATION_ID)))
				.setReservationDate(resultSet.getDate(ConfigurationManager.getInstance().getString(ConfigurationManager.RESERVATION_RESERVATION_DATE)))
				.setStartDate(resultSet.getDate(ConfigurationManager.getInstance().getString(ConfigurationManager.RESERVATION_START_DATE)))
				.setEndDate(resultSet.getDate(ConfigurationManager.getInstance().getString(ConfigurationManager.RESERVATION_END_DATE)))
				.setReservationStatus(ReservationStatus.getReservationStatus(resultSet.getString(ConfigurationManager.getInstance().getString(ConfigurationManager.RESERVATION_STATUS))))
				.setClientComment(resultSet.getString(ConfigurationManager.getInstance().getString(ConfigurationManager.RESERVATION_CLIENT_COMMENT)))
				.setAdministratorComment(resultSet.getString(ConfigurationManager.getInstance().getString(ConfigurationManager.RESERVATION_ADMINISTRATOR_COMMENT)))
				.build();
	}

}

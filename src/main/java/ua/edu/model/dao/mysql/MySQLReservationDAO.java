package ua.edu.model.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Optional;

import org.apache.log4j.Logger;

import ua.edu.model.dao.ReservationDao;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;
import ua.edu.model.exception.DatabaseException;
import ua.edu.model.util.ConfigManager;

public class MySQLReservationDAO implements ReservationDao{
	
	final static Logger logger = Logger.getLogger(MySQLReservationDAO.class);
	
	private Connection connection;
	
	public MySQLReservationDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Reservation reservation){
		try (PreparedStatement createStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_RESERVATION_INSERT), 
						Statement.RETURN_GENERATED_KEYS)){
			createStatement.setTimestamp(1, new java.sql.Timestamp(reservation.getReservationDate().getTime()));
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
		} catch (SQLException e){
			logger.error("MySQLReservationDAO create error: " + reservation.toString(), e);
			throw new DatabaseException();
		}
	}

	public void update(Reservation reservation){
		try (PreparedStatement updateStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_RESERVATION_UPDATE))){
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
		} catch (SQLException e){
			logger.error("MySQLReservationDAO update error: " + reservation.toString(), e);
			throw new DatabaseException();
		}
	}

	public void delete(int id){
		try (PreparedStatement deleteStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_RESERVATION_DELETE))){
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLReservationDAO delete error: " + id, e);
			throw new DatabaseException();
		}
	}

	@Override
	public Optional<Reservation> getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(int id) {
		Optional<Reservation> reservation = Optional.empty();
		try (PreparedStatement query = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_RESERVATION_GET_BY_ID_WITH_USER_AND_ROOM_TYPE_AND_ROOM_AND_PAYMENT))) {
			query.setInt(1, id);
			ResultSet resultSet = query.executeQuery();
			if (resultSet.next()) {
				reservation = Optional.of(extractReservationWithUserAndRoomTypeAndRoomAndPaymentFromResultSet(resultSet));
			}
		} catch (SQLException e){
			logger.error("MySQLReservationDAO getReservationByIdWithUserAndRoomTypeAndRoomAndPayment error: " + id, e);
			throw new DatabaseException();
		}
		return reservation;
	}
	
	private Reservation extractReservationWithUserAndRoomTypeAndRoomAndPaymentFromResultSet(ResultSet resultSet) throws SQLException{
		Reservation reservation = extractReservationFromResultSet(resultSet);
		reservation.setClient(MySQLUserDAO.extractUserFromResultSet(resultSet));
		reservation.setRoomType(MySQLRoomTypeDAO.extractRoomTypeFromResultSet(resultSet));
		
		resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.PAYMENT_PAYMENT_ID));
		if (!resultSet.wasNull()){
			reservation.setPayment(MySQLPaymentDAO.extractPaymentFromResultSet(resultSet));
		}
		
		resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.ROOM_ROOM_ID));
		if (!resultSet.wasNull()){
			reservation.setRoom(MySQLRoomDAO.extractRoomFromResultSet(resultSet));
		}
		
		return reservation;
	}

	static Reservation extractReservationFromResultSet(ResultSet resultSet) throws SQLException {
		return new Reservation.ReservationBuilder()
				.setId(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.RESERVATION_RESERVATION_ID)))
				.setReservationDate(resultSet.getDate(ConfigManager.getInstance().getString(ConfigManager.RESERVATION_RESERVATION_DATE)))
				.setStartDate(resultSet.getDate(ConfigManager.getInstance().getString(ConfigManager.RESERVATION_START_DATE)))
				.setEndDate(resultSet.getDate(ConfigManager.getInstance().getString(ConfigManager.RESERVATION_END_DATE)))
				.setReservationStatus(ReservationStatus.getReservationStatus(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.RESERVATION_STATUS))))
				.setClientComment(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.RESERVATION_CLIENT_COMMENT)))
				.setAdministratorComment(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.RESERVATION_ADMINISTRATOR_COMMENT)))
				.build();
	}

}
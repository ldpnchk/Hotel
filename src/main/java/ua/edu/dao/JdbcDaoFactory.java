package ua.edu.dao;
import java.sql.Connection;

import ua.edu.dao.jdbc.*;

public class JdbcDaoFactory extends DaoFactory{

	@Override
	public PaymentDao createPaymentDao(Connection connection) {
		return new JdbcPaymentDao(connection);
	}

	@Override
	public ReservationDao createReservationDao(Connection connection) {
		return new JdbcReservationDao(connection);
	}

	@Override
	public RoomDao createRoomDao(Connection connection) {
		return new JdbcRoomDao(connection);
	}

	@Override
	public RoomTypeDao createRoomTypeDao(Connection connection) {
		return new JdbcRoomTypeDao(connection);
	}

	@Override
	public UserDao createUserDao(Connection connection) {
		return new JdbcUserDao(connection);
	}

}

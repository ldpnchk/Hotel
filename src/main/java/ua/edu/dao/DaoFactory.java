package ua.edu.dao;

import java.sql.Connection;

import ua.edu.dao.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {
	
	public static DaoFactory getDaoFactory() {
        return JdbcDaoFactory.getInstance();
    }

	public abstract PaymentDao createPaymentDao(Connection connection);

	public abstract ReservationDao createReservationDao(Connection connection);

	public abstract RoomDao createRoomDao(Connection connection);

	public abstract RoomTypeDao createRoomTypeDao(Connection connection);

	public abstract UserDao createUserDao(Connection connection);

}

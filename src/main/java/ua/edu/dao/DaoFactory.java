package ua.edu.dao;

import java.sql.Connection;

public abstract class DaoFactory {

    public abstract PaymentDao createPaymentDao(Connection connection);
    public abstract ReservationDao createReservationDao(Connection connection);
    public abstract RoomDao createRoomDao(Connection connection);
    public abstract RoomTypeDao createRoomTypeDao(Connection connection);
    public abstract UserDao createUserDao(Connection connection);

}

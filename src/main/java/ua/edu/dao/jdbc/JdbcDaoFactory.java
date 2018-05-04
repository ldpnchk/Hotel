package ua.edu.dao.jdbc;

import java.sql.Connection;

import ua.edu.dao.DaoFactory;
import ua.edu.dao.PaymentDao;
import ua.edu.dao.ReservationDao;
import ua.edu.dao.RoomDao;
import ua.edu.dao.RoomTypeDao;
import ua.edu.dao.UserDao;

public class JdbcDaoFactory extends DaoFactory{
	
	private static JdbcDaoFactory instance;
	
	public static JdbcDaoFactory getInstance(){
        if(instance == null){
            synchronized (JdbcDaoFactory.class){
                if(instance == null){
                	instance = new JdbcDaoFactory();
                }
            }
        }
        return instance;
    }
	
	public PaymentDao createPaymentDao(Connection connection) {
		return new JdbcPaymentDao(connection);
	}

	public ReservationDao createReservationDao(Connection connection) {
		return new JdbcReservationDao(connection);
	}

	public RoomDao createRoomDao(Connection connection) {
		return new JdbcRoomDao(connection);
	}

	public RoomTypeDao createRoomTypeDao(Connection connection) {
		return new JdbcRoomTypeDao(connection);
	}

	public UserDao createUserDao(Connection connection) {
		return new JdbcUserDao(connection);
	}

}

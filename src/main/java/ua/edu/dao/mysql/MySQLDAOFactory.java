package ua.edu.dao.mysql;

import java.sql.Connection;

import ua.edu.dao.AbstractDaoFactory;
import ua.edu.dao.PaymentDao;
import ua.edu.dao.ReservationDao;
import ua.edu.dao.RoomDao;
import ua.edu.dao.RoomTypeDao;
import ua.edu.dao.UserDao;

public class MySQLDAOFactory extends AbstractDaoFactory{
	
	private static MySQLDAOFactory instance;
	
	private MySQLDAOFactory() {
		
	}
	
	public static MySQLDAOFactory getInstance(){
        if(instance == null){
            synchronized (MySQLDAOFactory.class){
                if(instance == null){
                	instance = new MySQLDAOFactory();
                }
            }
        }
        return instance;
    }
	
	public PaymentDao createPaymentDao(Connection connection) {
		return new MySQLPaymentDAO(connection);
	}

	public ReservationDao createReservationDao(Connection connection) {
		return new MySQLReservationDAO(connection);
	}

	public RoomDao createRoomDao(Connection connection) {
		return new MySQLRoomDAO(connection);
	}

	public RoomTypeDao createRoomTypeDao(Connection connection) {
		return new MySQLRoomTypeDAO(connection);
	}

	public UserDao createUserDao(Connection connection) {
		return new MySQLUserDAO(connection);
	}

}

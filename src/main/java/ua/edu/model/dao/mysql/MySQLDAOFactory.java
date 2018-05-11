package ua.edu.model.dao.mysql;

import java.sql.Connection;

import ua.edu.model.dao.AbstractDaoFactory;
import ua.edu.model.dao.PaymentDao;
import ua.edu.model.dao.ReservationDao;
import ua.edu.model.dao.RoomDao;
import ua.edu.model.dao.RoomTypeDao;
import ua.edu.model.dao.UserDao;

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

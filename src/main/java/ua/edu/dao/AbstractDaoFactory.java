package ua.edu.dao;

import java.sql.Connection;

import ua.edu.dao.mysql.MySQLDAOFactory;
import ua.edu.util.ConfigurationManager;

public abstract class AbstractDaoFactory {
	
	public static AbstractDaoFactory getFactory(){
		
		if (ConfigurationManager.getInstance().getString(ConfigurationManager.DAO_FACTORY).equals
				(ConfigurationManager.getInstance().getString(ConfigurationManager.DAO_FACTORY_MYSQL_KEY))){
			return MySQLDAOFactory.getInstance();
		} else {
			throw new RuntimeException();
		}
		
	}

	public abstract PaymentDao createPaymentDao(Connection connection);

	public abstract ReservationDao createReservationDao(Connection connection);

	public abstract RoomDao createRoomDao(Connection connection);

	public abstract RoomTypeDao createRoomTypeDao(Connection connection);

	public abstract UserDao createUserDao(Connection connection);

}

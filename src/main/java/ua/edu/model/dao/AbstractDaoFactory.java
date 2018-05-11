package ua.edu.model.dao;

import java.sql.Connection;

import ua.edu.model.dao.mysql.MySQLDAOFactory;
import ua.edu.model.util.ConfigManager;

public abstract class AbstractDaoFactory {
	
	public static AbstractDaoFactory getFactory(){
		
		if (ConfigManager.getInstance().getString(ConfigManager.DAO_FACTORY).equals
				(ConfigManager.getInstance().getString(ConfigManager.DAO_FACTORY_MYSQL_KEY))){
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

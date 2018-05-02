package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.DaoFactory;
import ua.edu.dao.JdbcDaoFactory;
import ua.edu.dao.RoomTypeDao;
import ua.edu.dao.connection.ConnectionPool;
import ua.edu.entity.RoomType;

public class RoomTypeService {
	
	DaoFactory daoFactory = new JdbcDaoFactory();
	
	public void createRoomType(RoomType roomType){
        try (Connection connection = ConnectionPool.getConnection()){
        	RoomTypeDao roomTypeDao = daoFactory.createRoomTypeDao(connection);
    		roomTypeDao.create(roomType);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRoomType(RoomType roomType){
        try (Connection connection = ConnectionPool.getConnection()){
        	RoomTypeDao roomTypeDao = daoFactory.createRoomTypeDao(connection);
    		roomTypeDao.update(roomType);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoomType(int id){
        try (Connection connection = ConnectionPool.getConnection()){
        	RoomTypeDao roomTypeDao = daoFactory.createRoomTypeDao(connection);
    		roomTypeDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

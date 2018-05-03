package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.DaoFactory;
import ua.edu.dao.JdbcDaoFactory;
import ua.edu.dao.RoomDao;
import ua.edu.dao.connection.DataSource;
import ua.edu.entity.Room;

public class RoomService {
	
	DaoFactory daoFactory = new JdbcDaoFactory();
	
	public void createRoom(Room room){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	RoomDao roomDao = daoFactory.createRoomDao(connection);
    		roomDao.create(room);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRoom(Room room){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	RoomDao roomDao = daoFactory.createRoomDao(connection);
    		roomDao.update(room);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoom(int id){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	RoomDao roomDao = daoFactory.createRoomDao(connection);
    		roomDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

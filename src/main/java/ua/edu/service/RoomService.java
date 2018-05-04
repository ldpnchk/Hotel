package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.DaoFactory;
import ua.edu.dao.RoomDao;
import ua.edu.dao.connection.DataSource;
import ua.edu.entity.Room;

public class RoomService {
	
	private static RoomService instance;
	
	public static RoomService getInstance(){
        if(instance == null){
            synchronized (RoomService.class){
                if(instance == null){
                	instance = new RoomService();
                }
            }
        }
        return instance;
    }
	
	public void createRoom(Room room){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	RoomDao roomDao = DaoFactory.getDaoFactory().createRoomDao(connection);
    		roomDao.create(room);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRoom(Room room){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	RoomDao roomDao = DaoFactory.getDaoFactory().createRoomDao(connection);
    		roomDao.update(room);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoom(int id){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	RoomDao roomDao = DaoFactory.getDaoFactory().createRoomDao(connection);
    		roomDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

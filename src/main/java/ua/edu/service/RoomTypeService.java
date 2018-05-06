package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.RoomTypeDao;
import ua.edu.entity.RoomType;

public class RoomTypeService extends Service{
	
	private static RoomTypeService instance;
	
	private RoomTypeService(){
		
	}
	
	public static RoomTypeService getInstance(){
        if(instance == null){
            synchronized (RoomTypeService.class){
                if(instance == null){
                	instance = new RoomTypeService();
                }
            }
        }
        return instance;
    }
	
	public void createRoomType(RoomType roomType){
        try (Connection connection = dataSource.getConnection()){
        	RoomTypeDao roomTypeDao = daoFactory.createRoomTypeDao(connection);
    		roomTypeDao.create(roomType);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateRoomType(RoomType roomType){
        try (Connection connection = dataSource.getConnection()){
        	RoomTypeDao roomTypeDao = daoFactory.createRoomTypeDao(connection);
    		roomTypeDao.update(roomType);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRoomType(int id){
        try (Connection connection = dataSource.getConnection()){
        	RoomTypeDao roomTypeDao = daoFactory.createRoomTypeDao(connection);
    		roomTypeDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

package ua.edu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ua.edu.model.dao.RoomDao;
import ua.edu.model.entity.Room;

public class RoomService extends Service{

	private static RoomService instance;

	private RoomService(){

	}

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
		try (Connection connection = dataSource.getConnection()){
			RoomDao roomDao = daoFactory.createRoomDao(connection);
			roomDao.create(room);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateRoom(Room room){
		try (Connection connection = dataSource.getConnection()){
			RoomDao roomDao = daoFactory.createRoomDao(connection);
			roomDao.update(room);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteRoom(int id){
		try (Connection connection = dataSource.getConnection()){
			RoomDao roomDao = daoFactory.createRoomDao(connection);
			roomDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Room> getFreeRoomsByDatesAndRoomType(LocalDate startDate, LocalDate endDate, int roomTypeId){
		List<Room> rooms = new ArrayList<Room>();
		try (Connection connection = dataSource.getConnection()){
			RoomDao roomDao = daoFactory.createRoomDao(connection);
			rooms = roomDao.getFreeRoomsByDatesAndRoomType(startDate, endDate, roomTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}
	
	public List<Room> getAllRooms(){
		List<Room> rooms = new ArrayList<Room>();
		try (Connection connection = dataSource.getConnection()){
			RoomDao roomDao = daoFactory.createRoomDao(connection);
			rooms = roomDao.getAllRooms();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}

}

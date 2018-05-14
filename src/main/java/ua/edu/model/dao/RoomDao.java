package ua.edu.model.dao;

import java.time.LocalDate;
import java.util.List;

import ua.edu.model.entity.Room;

public interface RoomDao extends GenericDao<Room>{

    List<Room> getFreeRoomsByDatesAndRoomType(LocalDate startDate, LocalDate endDate, int roomTypeId);
    List<Room> getAllRooms();

}

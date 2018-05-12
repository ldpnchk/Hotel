package ua.edu.model.dao;

import java.time.LocalDate;
import java.util.List;

import ua.edu.model.entity.RoomType;

public interface RoomTypeDao extends GenericDao<RoomType>{
	
	List<RoomType> getFreeRoomTypesByDatesAndCapacity(LocalDate startDate, LocalDate endDate, int capacity);

}

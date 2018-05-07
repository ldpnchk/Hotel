package ua.edu.dao;

import java.util.Date;
import java.util.List;

import ua.edu.entity.RoomType;

public interface RoomTypeDao extends GenericDao<RoomType>{
	
	List<RoomType> getFreeRoomTypesByDatesAndCapacity(Date startDate, Date endDate, int capacity);

}

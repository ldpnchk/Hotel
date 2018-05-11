package ua.edu.model.dao;

import java.util.Date;
import java.util.List;

import ua.edu.model.entity.RoomType;

public interface RoomTypeDao extends GenericDao<RoomType>{
	
	List<RoomType> getFreeRoomTypesByDatesAndCapacity(Date startDate, Date endDate, int capacity);

}

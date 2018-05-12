package ua.edu.model.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ua.edu.model.dao.RoomTypeDao;
import ua.edu.model.dto.RoomTypeDto;
import ua.edu.model.entity.RoomType;

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
	
	public List<RoomTypeDto> getFreeRoomTypesByDatesAndCapacity(LocalDate startDate, LocalDate endDate, int capacity){
		List<RoomTypeDto> roomTypesDto = new ArrayList<RoomTypeDto>();
        try (Connection connection = dataSource.getConnection()){
        	RoomTypeDao roomTypeDao = daoFactory.createRoomTypeDao(connection);
        	List<RoomType> roomTypes = roomTypeDao.getFreeRoomTypesByDatesAndCapacity(startDate, endDate, capacity);
        	roomTypesDto = getRoomTypesDto(roomTypes, (int) ChronoUnit.DAYS.between(startDate, endDate));
        } catch (SQLException e) {
			e.printStackTrace();
		}
       	return roomTypesDto;
	}
	
	private List<RoomTypeDto> getRoomTypesDto(List<RoomType> roomTypes, int daysNumber){
		List<RoomTypeDto> result = new ArrayList<RoomTypeDto>();
		for (RoomType roomType : roomTypes){
			BigDecimal pricePerNight = new BigDecimal(roomType.getPrice() / 100);
			BigDecimal totalPrice = pricePerNight.multiply(new BigDecimal(daysNumber));
			
			result.add(new RoomTypeDto.RoomTypeDtoBuilder()
					.setId(roomType.getId())
					.setName(roomType.getName())
					.setCapacity(roomType.getCapacity())
					.setDescription(roomType.getDescription())
					.setPricePerNight(pricePerNight)
					.setTotalPrice(totalPrice)
					.build());
		}
		return result;
	}

}

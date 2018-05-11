package ua.edu.model.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ua.edu.model.dao.RoomTypeDao;
import ua.edu.model.entity.RoomType;
import ua.edu.model.exception.DatabaseException;
import ua.edu.model.util.ConfigManager;

public class MySQLRoomTypeDAO implements RoomTypeDao{
	
	final static Logger logger = Logger.getLogger(MySQLRoomTypeDAO.class);
	
	private Connection connection;
	
	public MySQLRoomTypeDAO(Connection connection){
		this.connection = connection;
	}

	public void create(RoomType roomType){
		try (PreparedStatement createStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_TYPE_INSERT), 
						Statement.RETURN_GENERATED_KEYS)){
			createStatement.setString(1, roomType.getName());
			createStatement.setInt(2, roomType.getCapacity());
			createStatement.setInt(3, roomType.getPrice());
			if (roomType.getDescription() != null){
				createStatement.setString(4, roomType.getDescription());
			} else {
				createStatement.setNull(4, Types.VARCHAR);
			}
			createStatement.executeUpdate();
			
			ResultSet rs = createStatement.getGeneratedKeys();
			if (rs.next()) {
				roomType.setId(rs.getInt(1));
			}
		} catch (SQLException e){
			logger.error("MySQLRoomTypeDAO create error: " + roomType.toString(), e);
			throw new DatabaseException();
		}
	}

	public void update(RoomType roomType){
		try (PreparedStatement updateStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_TYPE_UPDATE))){
			updateStatement.setString(1, roomType.getName());
			updateStatement.setInt(2, roomType.getCapacity());
			updateStatement.setInt(3, roomType.getPrice());
			if (roomType.getDescription() != null){
				updateStatement.setString(4, roomType.getDescription());
			} else {
				updateStatement.setNull(4, Types.VARCHAR);
			}
			updateStatement.setInt(5, roomType.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLRoomTypeDAO update error: " + roomType.toString(), e);
			throw new DatabaseException();
		}
	}

	public void delete(int id){
		try (PreparedStatement deleteStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_TYPE_DELETE))){
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLRoomTypeDAO delete error: " + id, e);
			throw new DatabaseException();
		}
	}

	@Override
	public List<RoomType> getFreeRoomTypesByDatesAndCapacity(Date startDate, Date endDate, int capacity) {
		List<RoomType> roomTypes = new ArrayList<RoomType>();
		try (PreparedStatement query = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_TYPE_GET_FREE_ROOM_TYPES_BY_DATES_AND_CAPACITY))){
			query.setInt(1, capacity);
			query.setDate(2, new java.sql.Date(startDate.getTime()));
			query.setDate(3, new java.sql.Date(endDate.getTime()));
			
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				roomTypes.add(extractRoomTypeFromResultSet(resultSet));
			}
		} catch (SQLException e){
			logger.error("MySQLRoomTypeDAO getFreeRoomTypesByDatesAndCapacity error: " + startDate + " " + endDate + " " + capacity, e);
			throw new DatabaseException();
		}
		return roomTypes;	
	}
	
	static RoomType extractRoomTypeFromResultSet(ResultSet resultSet) throws SQLException {
		return new RoomType.RoomTypeBuilder()
				.setId(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.ROOM_TYPE_ROOM_TYPE_ID)))
				.setName(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.ROOM_TYPE_NAME)))
				.setCapacity(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.ROOM_TYPE_CAPACITY)))
				.setPrice(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.ROOM_TYPE_PRICE))/100)
				.setDescription(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.ROOM_TYPE_DESCRIPTION)))
				.build();
	}

}

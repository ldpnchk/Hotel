package ua.edu.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.edu.dao.RoomTypeDao;
import ua.edu.entity.RoomType;
import ua.edu.util.ConfigurationManager;

public class MySQLRoomTypeDAO implements RoomTypeDao{
	
	private Connection connection;
	
	public MySQLRoomTypeDAO(Connection connection){
		this.connection = connection;
	}

	public void create(RoomType roomType) throws SQLException {
		PreparedStatement createStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_TYPE_INSERT), 
						Statement.RETURN_GENERATED_KEYS);
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
		
		createStatement.close();
	}

	public void update(RoomType roomType) throws SQLException {
		PreparedStatement updateStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_TYPE_UPDATE));
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
		updateStatement.close();
	}

	public void delete(int id) throws SQLException {
		PreparedStatement deleteStatement = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_TYPE_DELETE));
		deleteStatement.setInt(1, id);
		deleteStatement.executeUpdate();
		deleteStatement.close();
	}

	@Override
	public List<RoomType> getFreeRoomTypesByDatesAndCapacity(Date startDate, Date endDate, int capacity) {
		List<RoomType> roomTypes = new ArrayList<RoomType>();
		try (PreparedStatement query = connection.prepareStatement
				(ConfigurationManager.getInstance().getString(ConfigurationManager.MYSQL_ROOM_TYPE_GET_FREE_ROOM_TYPES_BY_DATES_AND_CAPACITY))){
			query.setInt(1, capacity);
			query.setDate(2, new java.sql.Date(startDate.getTime()));
			query.setDate(3, new java.sql.Date(endDate.getTime()));
			
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				roomTypes.add(extractRoomTypeFromResultSet(resultSet));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return roomTypes;	
	}
	
	static RoomType extractRoomTypeFromResultSet(ResultSet resultSet) throws SQLException {
		return new RoomType.RoomTypeBuilder()
				.setId(resultSet.getInt(ConfigurationManager.getInstance().getString(ConfigurationManager.ROOM_TYPE_ROOM_TYPE_ID)))
				.setName(resultSet.getString(ConfigurationManager.getInstance().getString(ConfigurationManager.ROOM_TYPE_NAME)))
				.setCapacity(resultSet.getInt(ConfigurationManager.getInstance().getString(ConfigurationManager.ROOM_TYPE_CAPACITY)))
				.setPrice(resultSet.getInt(ConfigurationManager.getInstance().getString(ConfigurationManager.ROOM_TYPE_PRICE)))
				.setDescription(resultSet.getString(ConfigurationManager.getInstance().getString(ConfigurationManager.ROOM_TYPE_DESCRIPTION)))
				.build();
	}

}

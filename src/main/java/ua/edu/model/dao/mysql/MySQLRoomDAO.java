package ua.edu.model.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.edu.model.dao.RoomDao;
import ua.edu.model.entity.Room;
import ua.edu.model.exception.DatabaseException;
import ua.edu.model.util.ConfigManager;

public class MySQLRoomDAO implements RoomDao{

	final static Logger logger = Logger.getLogger(MySQLRoomDAO.class);

	private Connection connection;

	public MySQLRoomDAO(Connection connection){
		this.connection = connection;
	}

	public void create(Room room){
		try (PreparedStatement createStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_INSERT),
						Statement.RETURN_GENERATED_KEYS)){
			createStatement.setString(1, room.getRoomNumber());
			createStatement.setInt(2, room.getRoomType().getId());
			createStatement.executeUpdate();

			ResultSet rs = createStatement.getGeneratedKeys();
			if (rs.next()) {
				room.setId(rs.getInt(1));
			}
		} catch (SQLException e){
			logger.error("MySQLRoomDAO create error: " + room.toString(), e);
			throw new DatabaseException();
		}
	}

	public void update(Room room){
		try (PreparedStatement updateStatement = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_UPDATE))){
			updateStatement.setString(1, room.getRoomNumber());
			updateStatement.setInt(2, room.getRoomType().getId());
			updateStatement.setInt(3, room.getId());
			updateStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLRoomDAO update error: " + room.toString(), e);
			throw new DatabaseException();
		}
	}

	public void delete(int id){
		try (PreparedStatement deleteStatement = connection.prepareStatement(
				ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_DELETE))){
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e){
			logger.error("MySQLRoomDAO delete error: " + id, e);
			throw new DatabaseException();
		}
	}

	@Override
	public List<Room> getFreeRoomsByDatesAndRoomType(LocalDate startDate, LocalDate endDate, int roomTypeId) {
		List<Room> rooms = new ArrayList<Room>();
		try (PreparedStatement query = connection.prepareStatement
				(ConfigManager.getInstance().getString(ConfigManager.MYSQL_ROOM_GET_FREE_ROOMS_BY_DATES_AND_ROOM_TYPE))){
			query.setInt(1, roomTypeId);
			query.setDate(2, Date.valueOf(startDate));
			query.setDate(3, Date.valueOf(startDate));
			query.setDate(4, Date.valueOf(endDate));
			query.setDate(5, Date.valueOf(endDate));
			query.setDate(6, Date.valueOf(startDate));
			query.setDate(7, Date.valueOf(endDate));
			query.setDate(8, Date.valueOf(startDate));
			query.setDate(9, Date.valueOf(endDate));

			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				rooms.add(extractRoomFromResultSet(resultSet));
			}
		} catch (SQLException e){
			logger.error("MySQLRoomDAO getFreeRoomsByDatesAndRoomType error: " + startDate + " " + endDate + " " + roomTypeId, e);
			throw new DatabaseException();
		}
		return rooms;
	}

	static Room extractRoomFromResultSet(ResultSet resultSet) throws SQLException {
		return new Room.RoomBuilder()
				.setId(resultSet.getInt(ConfigManager.getInstance().getString(ConfigManager.ROOM_ROOM_ID)))
				.setRoomNumber(resultSet.getString(ConfigManager.getInstance().getString(ConfigManager.ROOM_ROOM_NUMBER)))
				.build();
	}

}

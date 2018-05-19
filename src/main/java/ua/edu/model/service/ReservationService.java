package ua.edu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import ua.edu.model.dao.ReservationDao;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.util.validator.ReservationValidator;

public class ReservationService extends Service{

	private static ReservationService instance;

	private ReservationService(){

	}

	public static ReservationService getInstance(){
		if(instance == null){
			synchronized (ReservationService.class){
				if(instance == null){
					instance = new ReservationService();
				}
			}
		}
		return instance;
	}

	public void createReservation(Reservation reservation) throws GeneralInvalidInputException{
		try (Connection connection = dataSource.getConnection()){
			connection.setAutoCommit(false);
			
			ReservationValidator reservationValidator = new ReservationValidator(connection);
			reservationValidator.validateReservation(reservation);
	
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			reservationDao.create(reservation);
			
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateReservation(Reservation reservation) throws GeneralInvalidInputException{
		try (Connection connection = dataSource.getConnection()){
			System.out.println("1");
			connection.setAutoCommit(false);
			System.out.println("2");
			ReservationValidator reservationValidator = new ReservationValidator(connection);
			reservationValidator.validateReservation(reservation);
			System.out.println("3");
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			reservationDao.update(reservation);
			System.out.println("4");
			connection.commit();
			System.out.println("5");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteReservation(int id){
		try (Connection connection = dataSource.getConnection()){
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			reservationDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<Reservation> getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(int id){
		try (Connection connection = dataSource.getConnection()){
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			return reservationDao.getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<Reservation> getReservationsWithRoomAndRoomTypeByDatesAndRoomAndStatus
			(Optional<LocalDate> startDate, Optional<LocalDate> endDate, 
						Optional<Integer> roomId, Optional<ReservationStatus> reservationStatus){
		try (Connection connection = dataSource.getConnection()){
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			return reservationDao.getReservationsWithRoomAndRoomTypeByDatesAndRoomAndStatus
					(startDate, endDate, roomId, reservationStatus);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<Reservation> getReservationsByUser(int userId, int limit, int offset){
		try (Connection connection = dataSource.getConnection()){
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			return reservationDao.getReservationsByUser(userId, limit, offset);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public int countReservationsByUser(int userId){
		try (Connection connection = dataSource.getConnection()){
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			return reservationDao.countReservationsByUser(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}

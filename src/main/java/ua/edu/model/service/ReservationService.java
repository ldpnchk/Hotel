package ua.edu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import ua.edu.controller.util.validator.ReservationValidator;
import ua.edu.model.dao.ReservationDao;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;
import ua.edu.model.exception.GeneralInvalidInputException;

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
		ReservationValidator.getInstance().validateReservation(reservation);
		try (Connection connection = dataSource.getConnection()){
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			reservationDao.create(reservation);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateReservation(Reservation reservation) throws GeneralInvalidInputException{
		ReservationValidator.getInstance().validateReservation(reservation);
		try (Connection connection = dataSource.getConnection()){
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			reservationDao.update(reservation);
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

	public List<Reservation> getReservationsByUser(int userId){
		try (Connection connection = dataSource.getConnection()){
			ReservationDao reservationDao = daoFactory.createReservationDao(connection);
			return reservationDao.getReservationsByUser(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}

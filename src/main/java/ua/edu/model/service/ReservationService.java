package ua.edu.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import ua.edu.controller.util.validator.ReservationValidator;
import ua.edu.model.dao.ReservationDao;
import ua.edu.model.entity.Reservation;
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

}

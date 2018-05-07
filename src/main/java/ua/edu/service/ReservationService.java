package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import ua.edu.dao.ReservationDao;
import ua.edu.entity.Reservation;

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
	
	public void createReservation(Reservation reservation){
        try (Connection connection = dataSource.getConnection()){
        	ReservationDao reservationDao = daoFactory.createReservationDao(connection);
    		reservationDao.create(reservation);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateReservation(Reservation reservation){
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

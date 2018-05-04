package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.DaoFactory;
import ua.edu.dao.ReservationDao;
import ua.edu.dao.connection.DataSource;
import ua.edu.entity.Reservation;

public class ReservationService {
	
	private static ReservationService instance;
	
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
        try (Connection connection = DataSource.getInstance().getConnection()){
        	ReservationDao reservationDao = DaoFactory.getDaoFactory().createReservationDao(connection);
    		reservationDao.create(reservation);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateReservation(Reservation reservation){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	ReservationDao reservationDao = DaoFactory.getDaoFactory().createReservationDao(connection);
    		reservationDao.update(reservation);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteReservation(int id){
        try (Connection connection = DataSource.getInstance().getConnection()){
        	ReservationDao reservationDao = DaoFactory.getDaoFactory().createReservationDao(connection);
    		reservationDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

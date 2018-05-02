package ua.edu.service;

import java.sql.Connection;
import java.sql.SQLException;

import ua.edu.dao.DaoFactory;
import ua.edu.dao.JdbcDaoFactory;
import ua.edu.dao.ReservationDao;
import ua.edu.dao.connection.ConnectionPool;
import ua.edu.entity.Reservation;

public class ReservationService {
	
	DaoFactory daoFactory = new JdbcDaoFactory();
	
	public void createReservation(Reservation reservation){
        try (Connection connection = ConnectionPool.getConnection()){
        	ReservationDao reservationDao = daoFactory.createReservationDao(connection);
    		reservationDao.create(reservation);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateReservation(Reservation reservation){
        try (Connection connection = ConnectionPool.getConnection()){
        	ReservationDao reservationDao = daoFactory.createReservationDao(connection);
    		reservationDao.update(reservation);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteReservation(int id){
        try (Connection connection = ConnectionPool.getConnection()){
        	ReservationDao reservationDao = daoFactory.createReservationDao(connection);
    		reservationDao.delete(id);
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

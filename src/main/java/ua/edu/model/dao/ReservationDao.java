package ua.edu.model.dao;

import java.util.List;
import java.util.Optional;

import ua.edu.model.entity.Reservation;

public interface ReservationDao extends GenericDao<Reservation>{

	Optional<Reservation> getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(int id);
	
	List<Reservation> getAllReservations();
	List<Reservation> getReservationsByUser(int userId);

}

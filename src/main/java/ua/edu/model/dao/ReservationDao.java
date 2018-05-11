package ua.edu.model.dao;

import java.util.Optional;

import ua.edu.model.entity.Reservation;

public interface ReservationDao extends GenericDao<Reservation>{

	Optional<Reservation> getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(int id);

}

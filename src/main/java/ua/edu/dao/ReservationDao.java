package ua.edu.dao;

import java.util.Optional;

import ua.edu.entity.Reservation;

public interface ReservationDao extends GenericDao<Reservation>{

	Optional<Reservation> getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(int id);

}

package ua.edu.model.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;

public interface ReservationDao extends GenericDao<Reservation>{

	Optional<Reservation> getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(int id);
	
	List<Reservation> getReservationsWithRoomAndRoomTypeByDatesAndRoomAndStatus
			(Optional<LocalDate> startDate, Optional<LocalDate> endDate, 
					Optional<Integer> roomId, Optional<ReservationStatus> reservationStatus);
	List<Reservation> getReservationsByUser(int userId);

}

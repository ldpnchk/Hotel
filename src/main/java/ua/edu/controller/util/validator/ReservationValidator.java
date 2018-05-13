package ua.edu.controller.util.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.exception.InvalidInputException;
import ua.edu.model.util.ConfigManager;

public class ReservationValidator {
	
	private static volatile ReservationValidator instance;
	
    private ReservationValidator() {
    	
    }
    
    private void validateReservationDate(LocalDateTime reservationDate, LocalDate startDate) throws InvalidInputException{
    	if (ChronoUnit.DAYS.between(reservationDate.toLocalDate(), startDate) < 0){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_RESERVATION_DATE), "Reservation date must be before the start date!");
    	}
    }
    
    private void validateResidenceDate(LocalDate startDate, LocalDate endDate) throws InvalidInputException{
    	if (ChronoUnit.DAYS.between(startDate, endDate) < 1){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_START_DATE), "There must be more than 0 days!");
    	}
    }
    
    private void validateStatus(Reservation reservation) throws InvalidInputException{
    	if (reservation.getReservationStatus().equals(ReservationStatus.NEW) && reservation.getRoom() != null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS), "There сannot be a room in the new reservation!");
    	}
    	if (reservation.getReservationStatus().equals(ReservationStatus.DENIED) && reservation.getRoom() != null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS), "There сannot be a room in the denied reservation!");
    	}
    	if (reservation.getReservationStatus().equals(ReservationStatus.APPROVED) && reservation.getRoom() == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS), "There must be a room in the approved reservation!");
    	}
    }

    public void validateReservation(Reservation reservation) throws GeneralInvalidInputException{
		Map<String, String> errors = new HashMap<String, String>();
		
		try{
			ReservationValidator.getInstance().validateReservationDate
					(reservation.getReservationDate(), reservation.getStartDate());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			ReservationValidator.getInstance().validateResidenceDate
					(reservation.getStartDate(), reservation.getEndDate());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			ReservationValidator.getInstance().validateStatus(reservation);
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		if (errors.size() > 0){
			throw new GeneralInvalidInputException(errors);
		}
	}

    public static ReservationValidator getInstance() {
    	if (instance == null) {
            synchronized (ReservationValidator.class) {
                if (instance == null) {
                    instance = new ReservationValidator();
                }
            }
        }
        return instance;
    }


}
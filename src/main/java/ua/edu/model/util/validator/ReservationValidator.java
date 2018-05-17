package ua.edu.model.util.validator;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import ua.edu.model.dao.AbstractDaoFactory;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.exception.InvalidInputException;
import ua.edu.model.util.ConfigManager;

public class ReservationValidator {
	
	private Connection connection;
	
    public ReservationValidator(Connection connection) {
    	this.connection = connection;
    }
    
    private void validateReservationDate(LocalDateTime reservationDate, LocalDate startDate) throws InvalidInputException{
    	if (ChronoUnit.DAYS.between(reservationDate.toLocalDate(), startDate) < 0){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_RESERVATION_DATE), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_RESERVATION_RESERVATION_DATE));
    	}
    }
    
    private void validateResidenceDate(LocalDate startDate, LocalDate endDate) throws InvalidInputException{
    	if (ChronoUnit.DAYS.between(startDate, endDate) < 1){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_START_DATE), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_RESERVATION_START_DATE));
    	}
    }
    
    private void validateStatus(Reservation reservation) throws InvalidInputException{
    	if (reservation.getReservationStatus().equals(ReservationStatus.NEW) && reservation.getRoom() != null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_RESERVATION_NEW_ROOM));
    	}
    	if (reservation.getReservationStatus().equals(ReservationStatus.DENIED) && reservation.getRoom() != null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_RESERVATION_DENIED_ROOM));
    	}
    	if (reservation.getReservationStatus().equals(ReservationStatus.APPROVED) && reservation.getRoom() == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_RESERVATION_APPROVED_ROOM));
    	}
    }

    public void validateReservation(Reservation reservation) throws GeneralInvalidInputException{
		Map<String, String> errors = new HashMap<String, String>();
		
		try{
			validateReservationDate(reservation.getReservationDate(), reservation.getStartDate());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			validateResidenceDate(reservation.getStartDate(), reservation.getEndDate());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			validateStatus(reservation);
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		if (errors.size() > 0){
			throw new GeneralInvalidInputException(errors);
		}
	}

}
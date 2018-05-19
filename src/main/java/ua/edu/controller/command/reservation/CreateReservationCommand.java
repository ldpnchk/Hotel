package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.controller.util.ParameterizedUrlComposer;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;
import ua.edu.model.entity.RoomType;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
import ua.edu.model.exception.DateParserException;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.service.ReservationService;
import ua.edu.model.util.ConfigManager;
import ua.edu.model.util.DateParser;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;

public class CreateReservationCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
    	Reservation reservation;
    	try{
    		reservation = getReservationFromInput(request);
    	} catch (DateParserException e){
    		request.setAttribute("dataErrors", true);
    		return ConfigManager.getInstance().getString(ConfigManager.PAGE_RESERVATION);
        }
    	
    	try {
    		ReservationService.getInstance().createReservation(reservation);
    	} catch (GeneralInvalidInputException e) {
    		request.setAttribute("errors", e.getErrors());
    		return ConfigManager.getInstance().getString(ConfigManager.PAGE_RESERVATION);
    	}
    	
    	HashMap<String, String> urlParameters = new HashMap<String, String>();
    	urlParameters.put("reservationId", String.valueOf(reservation.getId()));
    	return ParameterizedUrlComposer.getInstance().composeUrl
    			(ConfigManager.getInstance().getString(ConfigManager.URL_RESERVATION_DETAILS), 
    					urlParameters);
    }
    
	private Reservation getReservationFromInput(HttpServletRequest request) throws DateParserException{
		return new Reservation.ReservationBuilder()
                .setReservationDate(LocalDateTime.now())
                .setReservationStatus(ReservationStatus.NEW)
                .setClientComment(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_COMMENT)))
                .setClient((User) request.getSession().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER)))
                .setStartDate(DateParser.getInstance().parseDate
                		(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_START_DATE))))
                .setEndDate(DateParser.getInstance().parseDate
                		(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_END_DATE))))
                .setRoomType(new RoomType.RoomTypeBuilder().setId(Integer.parseInt
                		(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_ROOM_TYPE_ID)))).build())
                .build();
	}

}

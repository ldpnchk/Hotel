package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.*;
import ua.edu.model.exception.DateParserException;
import ua.edu.model.service.ReservationService;
import ua.edu.model.util.ConfigManager;
import ua.edu.model.util.DateParser;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

public class CreateReservationCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
    	try{
    		Reservation reservation = new Reservation.ReservationBuilder()
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
            ReservationService.getInstance().createReservation(reservation);
            return ConfigManager.getInstance().getString(ConfigManager.URL_RESERVATION_DETAILS) + 
            		"?reservationId=" + reservation.getId();
    	} catch (DateParserException e){
    		return ConfigManager.getInstance().getString(ConfigManager.PAGE_404);
    	}
        
    }

}

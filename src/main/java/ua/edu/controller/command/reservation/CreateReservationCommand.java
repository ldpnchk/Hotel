package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.*;
import ua.edu.exception.DateParserException;
import ua.edu.service.ReservationService;
import ua.edu.util.DateParser;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

public class CreateReservationCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
    	try{
    		Reservation reservation = new Reservation.ReservationBuilder()
                    .setReservationDate(new Date())
                    .setReservationStatus(ReservationStatus.NEW)
                    .setClientComment(request.getParameter("comment"))
                    .setClient((User) request.getSession().getAttribute("user"))
                    .setStartDate(DateParser.getInstance().parseDate(request.getParameter("startDate")))
                    .setEndDate(DateParser.getInstance().parseDate(request.getParameter("endDate")))
                    .setRoomType(new RoomType.RoomTypeBuilder().setId(Integer.parseInt(request.getParameter("roomTypeId"))).build())
                    .build();
            ReservationService.getInstance().createReservation(reservation);
            return "redirect:/hotel/reservationDetails?reservationId=" + reservation.getId();
    	} catch (DateParserException e){
    		return "/404.jsp";
    	}
        
    }

}

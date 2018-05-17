package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.*;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.service.PaymentService;
import ua.edu.model.service.ReservationService;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UpdateReservationCommand implements Command{

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR})
    public String execute(HttpServletRequest request) {

    	int reservationId = Integer.parseInt
    			(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_RESERVATION_ID)));
    	Optional<Reservation> reservation =
                ReservationService.getInstance().getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(reservationId);
    	
    	if (!reservation.isPresent()){
    		return ConfigManager.getInstance().getString(ConfigManager.PAGE_404);
    	}

    	User user = (User) request.getSession().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
        if (user.getUserRole().equals(UserRole.CLIENT)) {
            String newClientComment = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_COMMENT));
            reservation.get().setClientComment(newClientComment);
        } else if (user.getUserRole().equals(UserRole.ADMINISTRATOR)){
            String newAdministratorComment = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_ADMIN_COMMENT));
            reservation.get().setAdministratorComment(newAdministratorComment);
            
            ReservationStatus reservationStatus = ReservationStatus.getReservationStatus
            		(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS)));
            if(reservationStatus.equals(ReservationStatus.APPROVED)){
                int roomId = Integer.parseInt
                        (request.getParameter(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_ROOM)));
                reservation.get().setRoom(new Room.RoomBuilder().setId(roomId).build());
            }
            if(reservationStatus.equals(ReservationStatus.NEW) || reservationStatus.equals(ReservationStatus.DENIED)){
                reservation.get().setRoom(null);
            }
            if(!reservationStatus.equals(ReservationStatus.PAYED) && reservation.get().getPayment() != null){
                PaymentService.getInstance().deletePayment(reservation.get().getPayment().getId());
            }
            reservation.get().setReservationStatus(reservationStatus);
        }

        try {
        	ReservationService.getInstance().updateReservation(reservation.get());
    	} catch (GeneralInvalidInputException e) {
    		request.setAttribute("errors", e.getErrors());
    	}
        
        return ConfigManager.getInstance().getString(ConfigManager.URL_RESERVATION_DETAILS) + 
        		"?reservationId=" + reservationId;
    }
}

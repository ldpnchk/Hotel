package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
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
            reservation.get().setReservationStatus(reservationStatus);
        }
        ReservationService.getInstance().updateReservation(reservation.get());
        
        return ConfigManager.getInstance().getString(ConfigManager.URL_RESERVATION_DETAILS) + 
        		"?reservationId=" + reservationId;
    }
}

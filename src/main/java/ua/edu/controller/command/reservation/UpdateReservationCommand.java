package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.Reservation;
import ua.edu.entity.ReservationStatus;
import ua.edu.entity.User;
import ua.edu.entity.UserRole;
import ua.edu.service.ReservationService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UpdateReservationCommand implements Command{

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR})
    public String execute(HttpServletRequest request) {
    	int reservationId = Integer.parseInt(request.getParameter("reservationId"));
    	Optional<Reservation> reservation =
                ReservationService.getInstance().getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(reservationId);
    	if (!reservation.isPresent()){
    		return "/404.jsp";
    	}
    	
    	User user = (User) request.getSession().getAttribute("user");
        if(user.getUserRole().equals(UserRole.CLIENT)) {
            String newClientComment = request.getParameter("comment");
            reservation.get().setClientComment(newClientComment);
        } else {
            String newAdministratorComment = request.getParameter("adminComment");
            reservation.get().setAdministratorComment(newAdministratorComment);
            
            ReservationStatus reservationStatus = ReservationStatus.getReservationStatus(request.getParameter("status"));
            reservation.get().setReservationStatus(reservationStatus);
        }
        ReservationService.getInstance().updateReservation(reservation.get());
        
        return "redirect:/hotel/reservationDetails?reservationId=" + reservationId;
    }
}

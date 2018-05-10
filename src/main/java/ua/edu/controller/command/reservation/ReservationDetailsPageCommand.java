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

public class ReservationDetailsPageCommand implements Command {

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
        if(user.getUserRole().getValue().equalsIgnoreCase(UserRole.ADMINISTRATOR.toString()) ||
                reservation.get().getClient().getId() == user.getId()){
            request.setAttribute("statuses", ReservationStatus.values());
            request.setAttribute("reservation", reservation.get());
            return "/reservationDetails.jsp";
        }else{
            return "/403.jsp";
        }
    }
}

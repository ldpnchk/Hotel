package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.UserRole;
import ua.edu.service.ReservationService;

import javax.servlet.http.HttpServletRequest;

public class DeleteReservationCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
        int reservationId = Integer.parseInt(request.getParameter("reservationId"));
        //check client id and reservation client id
        ReservationService.getInstance().deleteReservation(reservationId);
        return "redirect:/hotel/reservation";
    }
}

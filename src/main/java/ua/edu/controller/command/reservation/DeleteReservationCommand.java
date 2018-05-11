package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.UserRole;
import ua.edu.model.service.ReservationService;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class DeleteReservationCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
        int reservationId = Integer.parseInt
        		(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_RESERVATION_ID)));
        //check client id and reservation client id
        ReservationService.getInstance().deleteReservation(reservationId);
        return ConfigManager.getInstance().getString(ConfigManager.URL_RESERVATION);
    }
}

package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
import ua.edu.model.service.ReservationService;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientReservationsPageCommand implements Command{

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
        
        int number = Integer.parseInt(ConfigManager.getInstance().getString(ConfigManager.PAGINATION_SIZE));
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        List<Reservation> reservations = ReservationService.getInstance().getReservationsByUser(user.getId(), number, (page - 1) * number);
        
        request.setAttribute("page", page);
        request.setAttribute("total", (int) Math.ceil((double) ReservationService.getInstance().countReservationsByUser(user.getId()) / number));
        request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_RESERVATIONS), reservations);
        return ConfigManager.getInstance().getString(ConfigManager.PAGE_CLIENT_RESERVATIONS);
    }
}

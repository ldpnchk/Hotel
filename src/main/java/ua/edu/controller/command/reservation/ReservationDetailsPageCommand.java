package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.*;
import ua.edu.model.service.ReservationService;
import ua.edu.model.service.RoomService;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class ReservationDetailsPageCommand implements Command {

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
        
        if(reservation.get().getClient().getId() != user.getId() && !user.getUserRole().equals(UserRole.ADMINISTRATOR)){
        	return ConfigManager.getInstance().getString(ConfigManager.PAGE_404);
        }
        
        request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_STATUSES), ReservationStatus.values());
        request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_RESERVATION), reservation.get());
        request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_PAYMENT_TYPES), PaymentMethod.values());
        request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_ROOMS), RoomService.getInstance().getFreeRoomsByDatesAndRoomType(reservation.get().getStartDate(), reservation.get().getEndDate(), reservation.get().getRoomType().getId()));
        request.setAttribute("price", ((int) ChronoUnit.DAYS.between(reservation.get().getStartDate(),
                reservation.get().getEndDate())) * (reservation.get().getRoomType().getPrice() / 100));
        return ConfigManager.getInstance().getString(ConfigManager.PAGE_RESERVATION_DETAILS);
    }
}

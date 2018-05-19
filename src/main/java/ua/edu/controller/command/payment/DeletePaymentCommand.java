package ua.edu.controller.command.payment;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.controller.util.ParameterizedUrlComposer;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.UserRole;
import ua.edu.model.service.PaymentService;
import ua.edu.model.service.ReservationService;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Optional;

public class DeletePaymentCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.ADMINISTRATOR})
    public String execute(HttpServletRequest request) {
        int reservationId = Integer.parseInt
                (request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_RESERVATION_ID)));
        Optional<Reservation> reservation =
                ReservationService.getInstance().getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(reservationId);

        if (!reservation.isPresent()){
            return ConfigManager.getInstance().getString(ConfigManager.PAGE_404);
        } else {
            PaymentService.getInstance().deletePayment(reservation.get().getPayment().getId());
            HashMap<String, String> urlParameters = new HashMap<String, String>();
        	urlParameters.put("reservationId", String.valueOf(reservation.get().getId()));
        	return ParameterizedUrlComposer.getInstance().composeUrl
        			(ConfigManager.getInstance().getString(ConfigManager.URL_RESERVATION_DETAILS), 
        					urlParameters);
        }
    }
}

package ua.edu.controller.command.payment;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.controller.util.ParameterizedUrlComposer;
import ua.edu.model.entity.*;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.service.PaymentService;
import ua.edu.model.service.ReservationService;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

public class CreatePaymentCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.ADMINISTRATOR})
    public String execute(HttpServletRequest request) {

        int reservationId = Integer.parseInt
                (request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_RESERVATION_ID)));

        Optional<Reservation> reservation = ReservationService.getInstance().getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(reservationId);

        if (!reservation.isPresent()){
        	return ConfigManager.getInstance().getString(ConfigManager.PAGE_404);
        }
        
        if(reservation.get().getPayment() != null){
            PaymentService.getInstance().deletePayment(reservation.get().getPayment().getId());
        }

        Payment payment = getPaymentFromInput(request);
        payment.setReservation(reservation.get());
        PaymentService.getInstance().createPayment(payment);

        reservation.get().setReservationStatus(ReservationStatus.PAYED);
        try {
            ReservationService.getInstance().updateReservation(reservation.get());
        } catch (GeneralInvalidInputException e) {
        	request.setAttribute("errors", e.getErrors());
        }

        HashMap<String, String> urlParameters = new HashMap<String, String>();
    	urlParameters.put("reservationId", String.valueOf(reservation.get().getId()));
    	return ParameterizedUrlComposer.getInstance().composeUrl
    			(ConfigManager.getInstance().getString(ConfigManager.URL_RESERVATION_DETAILS), 
    					urlParameters);
    }
    
    private Payment getPaymentFromInput(HttpServletRequest request){
		return new Payment.PaymentBuilder()
				.setTotal((int) Math.floor(Double.parseDouble(request.getParameter
						(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_AMOUNT))) * 100))
				.setDate(LocalDateTime.now())
				.setPaymentMethod(PaymentMethod.getPaymentMethod(request.getParameter
						(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PAYMENT_METHOD))))
				.build();
	}

}

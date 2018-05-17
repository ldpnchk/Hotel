package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.*;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.service.PaymentService;
import ua.edu.model.service.ReservationService;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class AddReservationPaymentCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.ADMINISTRATOR})
    public String execute(HttpServletRequest request) {

        int reservationId = Integer.parseInt
                (request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_RESERVATION_ID)));
        double amount = Double.parseDouble
                (request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_AMOUNT)));
        PaymentMethod paymentMethod = PaymentMethod.getPaymentMethod
                (request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PAYMENT_METHOD)));

        Reservation reservation = ReservationService.getInstance().getReservationByIdWithUserAndRoomTypeAndRoomAndPayment(reservationId).get();

        if(reservation.getPayment() != null){
            PaymentService.getInstance().deletePayment(reservation.getPayment().getId());
        }

        Payment payment = new Payment();
        payment.setDate(LocalDateTime.now());
        payment.setPaymentMethod(paymentMethod);
        payment.setTotal((int)Math.floor(amount*100));
        payment.setReservation(reservation);
        PaymentService.getInstance().createPayment(payment);

        reservation.setReservationStatus(ReservationStatus.PAYED);
        try {
            ReservationService.getInstance().updateReservation(reservation);
        } catch (GeneralInvalidInputException e) {
            e.printStackTrace();
        }

        return ConfigManager.getInstance().getString(ConfigManager.URL_RESERVATION_DETAILS) +
                "?reservationId=" + reservationId;
    }
}

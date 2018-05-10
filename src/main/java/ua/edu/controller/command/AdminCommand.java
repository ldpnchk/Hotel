package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.Reservation;
import ua.edu.entity.UserRole;
import ua.edu.service.ReservationService;

import java.util.List;

public class AdminCommand implements Command{
	
	@Override
	@RolesAllowed(roles = {UserRole.ADMINISTRATOR})
	public String execute(HttpServletRequest request) {
		//List<Reservation> reservations = ReservationService.getInstance().GET ALL !!!
		return "/admin.jsp";
	}

}

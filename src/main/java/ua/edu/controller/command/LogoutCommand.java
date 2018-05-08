package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.UserRole;

public class LogoutCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR})
	public String execute(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:/hotel/main";
	}

}

package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.UserRole;

public class AdminCommand implements Command{
	
	@Override
	@RolesAllowed(roles = {UserRole.ADMINISTRATOR})
	public String execute(HttpServletRequest request) {
		return "/admin.jsp";
	}

}

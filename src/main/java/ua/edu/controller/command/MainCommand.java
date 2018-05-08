package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.UserRole;

public class MainCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR,  UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		return "/index.jsp";
	}

}

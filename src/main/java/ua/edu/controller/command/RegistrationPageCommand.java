package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPageCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		return "/registration.jsp";
	}

}

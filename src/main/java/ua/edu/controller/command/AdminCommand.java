package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand implements Command{
	
	@Override
	public String execute(HttpServletRequest request) {
		return "/admin.jsp";
	}

}

package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ClientCommand implements Command{
	
	@Override
	public String execute(HttpServletRequest request) {
		return "/client.jsp";
	}

}

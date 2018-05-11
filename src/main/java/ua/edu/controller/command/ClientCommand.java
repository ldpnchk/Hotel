package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.UserRole;
import ua.edu.util.ConfigManager;

public class ClientCommand implements Command{
	
	@Override
	@RolesAllowed(roles = {UserRole.CLIENT})
	public String execute(HttpServletRequest request) {
		return ConfigManager.getInstance().getString(ConfigManager.PAGE_CLIENT);
	}

}

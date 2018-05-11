package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.UserRole;
import ua.edu.model.util.ConfigManager;

public class MainCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR,  UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		return ConfigManager.getInstance().getString(ConfigManager.PAGE_MAIN);
	}

}

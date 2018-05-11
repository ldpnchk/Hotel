package ua.edu.controller.command.user;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.UserRole;
import ua.edu.util.ConfigManager;

public class RegistrationPageCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		return ConfigManager.getInstance().getString(ConfigManager.PAGE_REGISTRATION);
	}

}

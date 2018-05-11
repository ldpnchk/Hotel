package ua.edu.controller.command.authorization;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.UserRole;
import ua.edu.util.ConfigManager;

public class LogoutCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR})
	public String execute(HttpServletRequest request) {
		request.getSession().removeAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
		return ConfigManager.getInstance().getString(ConfigManager.URL_MAIN);
	}

}

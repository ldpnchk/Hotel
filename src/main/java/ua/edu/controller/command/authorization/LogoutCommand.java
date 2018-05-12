package ua.edu.controller.command.authorization;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.controller.util.ContextManager;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
import ua.edu.model.util.ConfigManager;

public class LogoutCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR})
	public String execute(HttpServletRequest request) {
		ContextManager.getInstance().removeUser(request, 
				((User) request.getSession().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER))).getUsername());
		request.getSession().removeAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
		return ConfigManager.getInstance().getString(ConfigManager.URL_MAIN);
	}

}

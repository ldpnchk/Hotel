package ua.edu.controller.command.authorization;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.controller.util.ContextManager;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
import ua.edu.model.service.UserService;
import ua.edu.model.util.ConfigManager;
import ua.edu.model.util.PasswordGenerator;

public class LoginCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		String username = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME));
		String password = PasswordGenerator.getInstance().generatePassword
				(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD)));
		
		if (username == null || password == null){
			return ConfigManager.getInstance().getString(ConfigManager.URL_LOGIN);
		}
		
		Optional<User> user = UserService.getInstance().getUserByUsername(username);
		if (user.isPresent() && user.get().getPassword().equals(password)){
			ContextManager.getInstance().addUser(request, username);
	        request.getSession().setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER), user.get());
            return ConfigManager.getInstance().getString(ConfigManager.URL_MAIN);
		} else {
			return ConfigManager.getInstance().getString(ConfigManager.URL_LOGIN);
		}
	}
	

}

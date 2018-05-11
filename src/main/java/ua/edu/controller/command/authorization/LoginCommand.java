package ua.edu.controller.command.authorization;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.User;
import ua.edu.entity.UserRole;
import ua.edu.service.UserService;
import ua.edu.util.ConfigManager;
import ua.edu.util.PasswordGenerator;

public class LoginCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		String username = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME));
		String password = PasswordGenerator.getInstance().generatePassword
				(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD)));
		
		Optional<User> user = UserService.getInstance().getUserByUsername(username);
		if (user.isPresent() && user.get().getPassword().equals(password)){
			checkMultiLogin(request, username);
	        request.getSession().setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER), user.get());
            return ConfigManager.getInstance().getString(ConfigManager.URL_MAIN);
		} else {
			return ConfigManager.getInstance().getString(ConfigManager.URL_LOGIN);
		}
	}
	
	private void checkMultiLogin(HttpServletRequest request, String username){
		Map<String, HttpSession> loggedUsers = (HashMap<String, HttpSession>) request.getSession()
				.getServletContext().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_LOGGED_USERS));
        if (loggedUsers.containsKey(username)){
        	//loggedUsers.get(username).invalidate(); ???
        	loggedUsers.get(username).removeAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
        }
        loggedUsers.put(username, request.getSession());
        request.getSession().getServletContext().setAttribute(ConfigManager.getInstance()
        		.getString(ConfigManager.ATTRIBUTE_LOGGED_USERS), loggedUsers);
	}

}

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
import ua.edu.util.PasswordGenerator;

public class LoginCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = PasswordGenerator.getInstance().generatePassword(request.getParameter("password"));
		
		Optional<User> user = UserService.getInstance().getUserByUsername(username);
		if (user.isPresent() && user.get().getPassword().equals(password)){
			checkMultiLogin(request, username);
	        request.getSession().setAttribute("user", user.get());
            return "redirect:/hotel/main";
		} else {
			return "redirect:/hotel/login";
		}
	}
	
	private void checkMultiLogin(HttpServletRequest request, String username){
		Map<String, HttpSession> loggedUsers = (HashMap<String, HttpSession>) request.getSession()
				.getServletContext().getAttribute("loggedUsers");
        if (loggedUsers.containsKey(username)){
        	loggedUsers.get(username).invalidate();
        }
        loggedUsers.put(username, request.getSession());
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
	}

}

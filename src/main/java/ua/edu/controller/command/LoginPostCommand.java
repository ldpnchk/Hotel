package ua.edu.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.User;
import ua.edu.entity.UserRole;
import ua.edu.service.UserService;
import ua.edu.util.PasswordGenerator;

public class LoginPostCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = PasswordGenerator.getInstance().generatePassword(request.getParameter("password"));
		Optional<User> user = UserService.getInstance().getUserByUsername(username);
		if (user.isPresent() && user.get().getPassword().equals(password)){
			request.getSession().setAttribute("user", user.get());
            return "redirect:/hotel/main";
		} else {
			return "redirect:/hotel/login";
		}
	}

}

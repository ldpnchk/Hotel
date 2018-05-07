package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.entity.User;
import ua.edu.entity.UserRole;
import ua.edu.service.UserService;
import ua.edu.util.PasswordGenerator;

public class RegisterCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(PasswordGenerator.getInstance().generatePassword(request.getParameter("password")));
		user.setEmail(request.getParameter("email"));
		user.setPhoneNumber(request.getParameter("phonenumber"));
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setUsername(request.getParameter("username"));
		user.setUserRole(UserRole.CLIENT);
		UserService.getInstance().createUser(user);
		return "redirect:/hotel/index.jsp";
	}

}

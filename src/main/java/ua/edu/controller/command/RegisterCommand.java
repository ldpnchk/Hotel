package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.User;
import ua.edu.entity.UserRole;
import ua.edu.service.UserService;
import ua.edu.util.PasswordGenerator;

public class RegisterCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		User user = new User.UserBuilder()
				.setUsername(request.getParameter("username"))
				.setPassword(PasswordGenerator.getInstance().generatePassword(request.getParameter("password")))
				.setEmail(request.getParameter("email"))
				.setPhoneNumber(request.getParameter("phonenumber"))
				.setFirstName(request.getParameter("firstname"))
				.setLastName(request.getParameter("lastname"))
				.setPatronymic(request.getParameter("patronymic"))
				.setUserRole(UserRole.CLIENT)
				.build();
		UserService.getInstance().createUser(user);
		return "redirect:/hotel/index.jsp";
	}

}

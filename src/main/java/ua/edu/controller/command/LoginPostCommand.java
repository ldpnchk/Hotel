package ua.edu.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import ua.edu.entity.User;
import ua.edu.service.UserService;
import ua.edu.util.PasswordGenerator;

public class LoginPostCommand implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = PasswordGenerator.getInstance().generatePassword(request.getParameter("password"));
		Optional<User> user = UserService.getInstance().getUserByUsername(username);
		if (user.isPresent() && user.get().getPassword().equals(password)){
			request.getSession().setAttribute("user", user.get());
            return "redirect:/main";
		} else {
			return "redirect:/login.jsp";
		}
	}

}

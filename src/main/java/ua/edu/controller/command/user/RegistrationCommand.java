package ua.edu.controller.command.user;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
import ua.edu.model.service.UserService;
import ua.edu.model.util.ConfigManager;
import ua.edu.model.util.PasswordGenerator;

public class RegistrationCommand implements Command{

	@Override
	@RolesAllowed(roles = {UserRole.GUEST})
	public String execute(HttpServletRequest request) {
		User user = new User.UserBuilder()
				.setUsername(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME)))
				.setPassword(PasswordGenerator.getInstance().generatePassword
						(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD))))
				.setEmail(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_EMAIL)))
				.setPhoneNumber(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PHONENUMBER)))
				.setFirstName(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_FIRSTNAME)))
				.setLastName(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_LASTNAME)))
				.setPatronymic(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PATRONYMIC)))
				.setUserRole(UserRole.CLIENT)
				.build();
		UserService.getInstance().createUser(user);
		return ConfigManager.getInstance().getString(ConfigManager.URL_MAIN);
	}

}

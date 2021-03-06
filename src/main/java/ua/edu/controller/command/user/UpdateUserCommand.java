package ua.edu.controller.command.user;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.controller.util.ContextManager;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.service.UserService;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements Command{

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR})
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
        String oldUsername = user.getUsername();

        user.setUsername(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME)));
        user.setEmail(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_EMAIL)));
        user.setPhoneNumber(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PHONENUMBER)));
        user.setFirstName(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_FIRSTNAME)));
        user.setLastName(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_LASTNAME)));
        user.setPatronymic(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PATRONYMIC)));
        
        try {
        	UserService.getInstance().updateUser(user);

        	if (!oldUsername.equals(user.getUsername())){
                ContextManager.getInstance().updateUser(request, oldUsername, user.getUsername());
            }
		} catch (GeneralInvalidInputException e) {
			request.setAttribute("errors", e.getErrors());
            return ConfigManager.getInstance().getString(ConfigManager.URL_PROFILE);
		}
        

        
        return ConfigManager.getInstance().getString(ConfigManager.URL_PROFILE);
    }
    
}
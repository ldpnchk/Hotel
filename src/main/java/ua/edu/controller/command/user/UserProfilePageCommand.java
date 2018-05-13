package ua.edu.controller.command.user;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
import ua.edu.model.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;

public class UserProfilePageCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.CLIENT, UserRole.ADMINISTRATOR})
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
        request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER), user);
        return ConfigManager.getInstance().getString(ConfigManager.PAGE_PROFILE);
    }
    
}
package ua.edu.controller.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ua.edu.model.entity.User;
import ua.edu.model.util.ConfigManager;

@WebListener
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		Map<String, HttpSession> loggedUsers = (HashMap<String, HttpSession>) httpSessionEvent.getSession()
				.getServletContext().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_LOGGED_USERS));
        User user = (User) httpSessionEvent.getSession().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
        if (user != null){
        	loggedUsers.remove(user.getUsername());
        }
        httpSessionEvent.getSession().getServletContext().setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_LOGGED_USERS), loggedUsers);
	}
}
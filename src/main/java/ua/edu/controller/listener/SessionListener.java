package ua.edu.controller.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ua.edu.entity.User;

@WebListener
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		Map<String, HttpSession> loggedUsers = (HashMap<String, HttpSession>) httpSessionEvent.getSession()
				.getServletContext().getAttribute("loggedUsers");
        User user = (User) httpSessionEvent.getSession().getAttribute("user");
        loggedUsers.remove(user.getUsername());
        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);

	}
}
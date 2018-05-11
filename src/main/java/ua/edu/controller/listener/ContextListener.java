package ua.edu.controller.listener;

import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import ua.edu.model.util.ConfigManager;

@WebListener
public class ContextListener implements ServletContextListener {
	
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	servletContextEvent.getServletContext().setAttribute(ConfigManager.getInstance()
    			.getString(ConfigManager.ATTRIBUTE_LOGGED_USERS), new HashMap<String, HttpSession>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
package ua.edu.controller.listener;

import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

@WebListener
public class ContextListener implements ServletContextListener {
	
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	servletContextEvent.getServletContext().setAttribute("loggedUsers", new HashMap<String, HttpSession>());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
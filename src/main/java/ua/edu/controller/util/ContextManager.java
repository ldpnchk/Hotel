package ua.edu.controller.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ua.edu.model.util.ConfigManager;

public class ContextManager {
	
	private static volatile ContextManager instance;
	
    private ContextManager() {
    	
    }
    
	@SuppressWarnings("unchecked")
	public void addUser(HttpServletRequest request, String username){
		Map<String, HttpSession> loggedUsers = (HashMap<String, HttpSession>) request.getSession()
				.getServletContext().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_LOGGED_USERS));
        if (loggedUsers.containsKey(username)){
        	loggedUsers.get(username).removeAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
        }
        loggedUsers.put(username, request.getSession());
        request.getSession().getServletContext().setAttribute(ConfigManager.getInstance()
        		.getString(ConfigManager.ATTRIBUTE_LOGGED_USERS), loggedUsers);
	}
	
	@SuppressWarnings("unchecked")
	public void removeUser(HttpServletRequest request, String username){
		Map<String, HttpSession> loggedUsers = (HashMap<String, HttpSession>) request.getSession()
				.getServletContext().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_LOGGED_USERS));
        if (loggedUsers.containsKey(username)){
        	loggedUsers.get(username).removeAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_USER));
        }
        request.getSession().getServletContext().setAttribute(ConfigManager.getInstance()
        		.getString(ConfigManager.ATTRIBUTE_LOGGED_USERS), loggedUsers);
	}

    @SuppressWarnings("unchecked")
    public void updateUser(HttpServletRequest request, String oldUsername, String newUsername){
        Map<String, HttpSession> loggedUsers = (HashMap<String, HttpSession>) request.getSession()
                .getServletContext().getAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_LOGGED_USERS));
        if (loggedUsers.containsKey(oldUsername)){
            loggedUsers.put(newUsername, request.getSession());
            loggedUsers.remove(oldUsername, request.getSession());
        }
        request.getSession().getServletContext().setAttribute(ConfigManager.getInstance()
                .getString(ConfigManager.ATTRIBUTE_LOGGED_USERS), loggedUsers);
    }


    public static ContextManager getInstance() {
    	if (instance == null) {
            synchronized (ContextManager.class) {
                if (instance == null) {
                    instance = new ContextManager();
                }
            }
        }
        return instance;
    }

}

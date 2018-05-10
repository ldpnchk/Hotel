package ua.edu.controller.filter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.edu.controller.command.CommandManager;
import ua.edu.entity.UserRole;

public class AccessManager {
	
	final static Logger logger = Logger.getLogger(AccessManager.class);
	
	private static AccessManager instance;
	
	private AccessManager(){

	}
	
	public static AccessManager getInstance(){
        if(instance == null){
            synchronized (AccessManager.class){
                if(instance == null){
                	instance = new AccessManager();
                }
            }
        }
        return instance;
    }
	
	public boolean checkAccess(String url, UserRole inputUserRole){		
		try {
			Method method = CommandManager.getInstance().getCommand(url).getClass().getMethod("execute", HttpServletRequest.class);
			RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
			UserRole[] users = rolesAllowed.roles();
			for (UserRole userRole : users) {
		        if (userRole.equals(inputUserRole))
		            return true;
		    }
		} catch (NullPointerException | NoSuchMethodException | SecurityException e) {
			logger.error("AccessManager error: " + url + " " + inputUserRole.getValue(), e);
		} 
		return false;
	}
	
}

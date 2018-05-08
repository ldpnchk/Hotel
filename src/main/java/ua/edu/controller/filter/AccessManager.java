package ua.edu.controller.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.command.CommandManager;
import ua.edu.entity.UserRole;

public class AccessManager {
	
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
		    return false;
		} catch (NullPointerException e) {
			return false;
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
		
	}
	
}

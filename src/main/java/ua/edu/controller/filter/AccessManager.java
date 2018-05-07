package ua.edu.controller.filter;

import java.util.ArrayList;
import java.util.HashMap;

import ua.edu.entity.UserRole;

public class AccessManager {
	
	private static AccessManager instance;
	
	private HashMap<String, ArrayList<UserRole>> access = new HashMap<String, ArrayList<UserRole>>();
	
	private AccessManager(){
		access.put("", new ArrayList<UserRole>());
		access.get("").add(UserRole.ADMINISTRATOR);
		access.get("").add(UserRole.CLIENT);
		access.get("").add(UserRole.GUEST);
		access.put("main", new ArrayList<UserRole>());
		access.get("main").add(UserRole.ADMINISTRATOR);
		access.get("main").add(UserRole.CLIENT);
		access.get("main").add(UserRole.GUEST);
		access.put("client", new ArrayList<UserRole>());
		access.get("client").add(UserRole.CLIENT);
		access.put("admin", new ArrayList<UserRole>());
		access.get("admin").add(UserRole.ADMINISTRATOR);
		access.put("login", new ArrayList<UserRole>());
		access.get("login").add(UserRole.GUEST);
		access.put("loginpost", new ArrayList<UserRole>());
		access.get("loginpost").add(UserRole.GUEST);
		access.put("logout", new ArrayList<UserRole>());
		access.get("logout").add(UserRole.ADMINISTRATOR);
		access.get("logout").add(UserRole.CLIENT);
		access.put("register", new ArrayList<UserRole>());
		access.get("register").add(UserRole.GUEST);
		access.put("registration", new ArrayList<UserRole>());
		access.get("registration").add(UserRole.GUEST);
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
	
	public boolean checkAccess(String url, UserRole userRole){
		return access.containsKey(url) && access.get(url).contains(userRole);
	}
	
	

}

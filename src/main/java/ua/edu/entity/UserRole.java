package ua.edu.entity;

public enum UserRole {
	
	ADMINISTRATOR,
	CLIENT,
	GUEST;
	
	public String getValue(){
		return this.name().toLowerCase();
	}
	
	public static UserRole getUserRole(String role){
		return UserRole.valueOf(role.toUpperCase());
	}

}

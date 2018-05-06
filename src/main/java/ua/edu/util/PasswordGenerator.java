package ua.edu.util;

public class PasswordGenerator {
	
	private static volatile PasswordGenerator instance;
	
	private PasswordGenerator(){
		
	}
	
    public static PasswordGenerator getInstance() {
    	if (instance == null) {
            synchronized (PasswordGenerator.class) {
                if (instance == null) {
                    instance = new PasswordGenerator();
                }
            }
        }
        return instance;
    }
    
    public String generatePassword(String password){
    	return password;
    }

}

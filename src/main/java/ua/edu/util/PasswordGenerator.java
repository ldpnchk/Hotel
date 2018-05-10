package ua.edu.util;

import org.apache.commons.codec.digest.DigestUtils;

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
    	return DigestUtils.sha256Hex(password);
    }

}

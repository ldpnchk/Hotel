package ua.edu.controller.util.validator;

import java.util.HashMap;
import java.util.Map;

import ua.edu.model.entity.User;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.exception.InvalidInputException;
import ua.edu.model.service.UserService;
import ua.edu.model.util.ConfigManager;

public class UserValidator {
	
	private static volatile UserValidator instance;
	
	private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	
    private UserValidator() {
    	
    }
    
    private void validateUsername(String username) throws InvalidInputException{
    	if (username == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME), "Username cannot be empty!");
    	}
    	if (username.length() < 5){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME), "Username must contain more than 4 characters!");
    	}
    	if (username.length() > 16){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME), "Username must contain less than 17 characters!");
        }
    	if (UserService.getInstance().getUserByUsername(username).isPresent()){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME), "This username is already taken!");
    	}
    }
    
    private void validatePassword(String password) throws InvalidInputException{
    	if (password == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD), "Password cannot be empty!");
    	}
    	if (password.length() < 5){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD), "Password must contain more than 4 characters!");
    	}
    	if (password.length() > 16){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD), "Password must contain less than 17 characters!");
        }
    }
    
    private void validateEmail(String email) throws InvalidInputException{
    	if (email == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_EMAIL), "Email cannot be empty!");
    	}
    	if (!email.matches(EMAIL_REGEX)){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_EMAIL), "Wrong email format!");
    	}
    	if (UserService.getInstance().getUserByEmail(email).isPresent()){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_EMAIL), "This email is already taken!");
    	}
    }
    
    private void validatePhoneNumber(String phoneNumber) throws InvalidInputException{
    	if (phoneNumber == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PHONENUMBER), "Phone number cannot be empty!");
    	}
    }
    
    private void validateFirstName(String firstName) throws InvalidInputException{
    	if (firstName == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_FIRSTNAME), "First name cannot be empty!");
    	}
    }
    
    private void validateLastName(String lastName) throws InvalidInputException{
    	if (lastName == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_LASTNAME), "Last name cannot be empty!");
    	}
    }
    
    public void validateUser(User user) throws GeneralInvalidInputException{
		Map<String, String> errors = new HashMap<String, String>();
		
		try{
			UserValidator.getInstance().validateUsername(user.getUsername());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			UserValidator.getInstance().validatePassword(user.getPassword());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			UserValidator.getInstance().validateEmail(user.getEmail());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			UserValidator.getInstance().validatePhoneNumber(user.getPhoneNumber());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			UserValidator.getInstance().validateFirstName(user.getFirstName());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			UserValidator.getInstance().validateLastName(user.getLastName());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		if (errors.size() > 0){
			throw new GeneralInvalidInputException(errors);
		}
	}

    public static UserValidator getInstance() {
    	if (instance == null) {
            synchronized (UserValidator.class) {
                if (instance == null) {
                    instance = new UserValidator();
                }
            }
        }
        return instance;
    }


}

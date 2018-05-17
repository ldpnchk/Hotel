package ua.edu.model.util.validator;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import ua.edu.model.dao.AbstractDaoFactory;
import ua.edu.model.entity.User;
import ua.edu.model.exception.GeneralInvalidInputException;
import ua.edu.model.exception.InvalidInputException;
import ua.edu.model.util.ConfigManager;

public class UserValidator {
	
	private Connection connection;
	
	public UserValidator(Connection connection){
		this.connection = connection;
	}
    
    private void validateUsername(String username) throws InvalidInputException{
    	if (username == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_USERNAME_EMPTY));
    	}
    	if (username.length() < 5){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_USERNAME_SHORT));
    	}
    	if (username.length() > 16){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_USERNAME_LONG));
        }
    	if (AbstractDaoFactory.getFactory().createUserDao(connection).getUserByUsername(username).isPresent()){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_USERNAME), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_USERNAME_TAKEN));
    	}
    }
    
    private void validatePassword(String password) throws InvalidInputException{
    	if (password == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_PASSWORD_EMPTY));
    	}
    	if (password.length() < 5){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_PASSWORD_SHORT));
    	}
    	if (password.length() > 16){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PASSWORD), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_PASSWORD_LONG));
        }
    }
    
    private void validateEmail(String email) throws InvalidInputException{
    	if (email == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_EMAIL), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_EMAIL_EMPTY));
    	}
    	if (!email.matches(ConfigManager.getInstance().getString(ConfigManager.REGEX_EMAIL))){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_EMAIL), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_EMAIL_FORMAT));
    	}
    	if (AbstractDaoFactory.getFactory().createUserDao(connection).getUserByEmail(email).isPresent()){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_EMAIL), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_EMAIL_TAKEN));
    	}
    }
    
    private void validatePhoneNumber(String phoneNumber) throws InvalidInputException{
    	if (phoneNumber == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_PHONENUMBER), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_PHONE_NUMBER_EMPTY));
    	}
    }
    
    private void validateFirstName(String firstName) throws InvalidInputException{
    	if (firstName == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_FIRSTNAME), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_FIRST_NAME_EMPTY));
    	}
    }
    
    private void validateLastName(String lastName) throws InvalidInputException{
    	if (lastName == null){
    		throw new InvalidInputException(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_LASTNAME), 
    				ConfigManager.getInstance().getString(ConfigManager.INVALID_INPUT_LAST_NAME_EMPTY));
    	}
    }
    
    public void validateUser(User user) throws GeneralInvalidInputException{
		Map<String, String> errors = new HashMap<String, String>();
		
		try{
			validateUsername(user.getUsername());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			validatePassword(user.getPassword());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			validateEmail(user.getEmail());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			validatePhoneNumber(user.getPhoneNumber());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			validateFirstName(user.getFirstName());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		try{
			validateLastName(user.getLastName());
		} catch (InvalidInputException e) {
			errors.put(e.getField(), e.getMessage());
		}
		
		if (errors.size() > 0){
			throw new GeneralInvalidInputException(errors);
		}
	}

}

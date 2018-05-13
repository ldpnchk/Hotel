package ua.edu.model.exception;

import java.util.Map;

public class GeneralInvalidInputException extends Exception{
	
	private Map<String, String> errors;
	
	public GeneralInvalidInputException(Map<String, String> errors){
		this.errors = errors;
	}
	
	public Map<String, String> getErrors(){
		return this.errors;
	}

}

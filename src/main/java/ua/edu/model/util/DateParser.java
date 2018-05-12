package ua.edu.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.log4j.Logger;

import ua.edu.model.exception.DateParserException;

public class DateParser {
	
	final static Logger logger = Logger.getLogger(DateParser.class);
	
	private static volatile DateParser instance;
	
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	private DateParser(){
		
	}
	
    public static DateParser getInstance() {
    	if (instance == null) {
            synchronized (DateParser.class) {
                if (instance == null) {
                    instance = new DateParser();
                }
            }
        }
        return instance;
    }
    
    public LocalDate parseDate(String date) throws DateParserException{
    	try {
    		return LocalDate.parse(date, formatter);
    	} catch (DateTimeParseException e) {
    		logger.error("DatePaser error: " + date, e);
    		throw new DateParserException();
    	}
    }

}

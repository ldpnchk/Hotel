package ua.edu.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import ua.edu.exception.DateParserException;
import ua.edu.exception.DatabaseException;

public class DateParser {
	
	final static Logger logger = Logger.getLogger(DateParser.class);
	
	private static volatile DateParser instance;
	
	DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
	
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
    
    public Date parseDate(String date) throws DateParserException{
    	try {
			return format.parse(date);
		} catch (ParseException e) {
			logger.error("DataParser parseDate error", e);
			throw new DateParserException();
		}
    }

}

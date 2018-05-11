package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.RoomType;
import ua.edu.entity.UserRole;
import ua.edu.exception.DateParserException;
import ua.edu.service.RoomTypeService;
import ua.edu.util.ConfigManager;
import ua.edu.util.DateParser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationPageCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.GUEST, UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
    	
        String dates = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_DATEFILTER));
        String capacity = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_CAPACITY));
        
        List<RoomType> result = new ArrayList<RoomType>();
        boolean dateFormatError = false;
        
        if(dates != null && capacity != null){
        	String[] datesArr = dates.split("-");
            try {
            	Date startDate = DateParser.getInstance().parseDate(datesArr[0]);
            	Date endDate = DateParser.getInstance().parseDate(datesArr[1]);
            	
            	result = RoomTypeService.getInstance().getFreeRoomTypesByDatesAndCapacity(startDate, endDate, Integer.parseInt(capacity));
            } catch (DateParserException e) {
                dateFormatError = true;
            }
        }

        request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_OPTIONS), result);
        request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_DATE_FORMAT_ERROR), dateFormatError);
        return ConfigManager.getInstance().getString(ConfigManager.PAGE_RESERVATION);
    }
}

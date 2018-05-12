package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.dto.RoomTypeDto;
import ua.edu.model.entity.UserRole;
import ua.edu.model.exception.DateParserException;
import ua.edu.model.service.RoomTypeService;
import ua.edu.model.util.ConfigManager;
import ua.edu.model.util.DateParser;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationPageCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.GUEST, UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
    	
        String dates = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_DATEFILTER));
        String capacity = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_CAPACITY));
        
        List<RoomTypeDto> result = new ArrayList<RoomTypeDto>();
        boolean dateFormatError = false;
        
        if(dates != null && capacity != null){
        	String[] datesArr = dates.split("-");
            try {
            	LocalDate startDate = DateParser.getInstance().parseDate(datesArr[0]);
            	LocalDate endDate = DateParser.getInstance().parseDate(datesArr[1]);
            	
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

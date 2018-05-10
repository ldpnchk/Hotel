package ua.edu.controller.command.reservation;

import ua.edu.controller.command.Command;
import ua.edu.controller.filter.RolesAllowed;
import ua.edu.entity.RoomType;
import ua.edu.entity.UserRole;
import ua.edu.exception.DateParserException;
import ua.edu.service.RoomTypeService;
import ua.edu.util.DateParser;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReservationPageCommand implements Command {

    @Override
    @RolesAllowed(roles = {UserRole.GUEST, UserRole.CLIENT})
    public String execute(HttpServletRequest request) {
    	
        String dates = request.getParameter("datefilter");
        String capacity = request.getParameter("capacity");
        
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

        request.setAttribute("options", result);
        request.setAttribute("dateFormatError", dateFormatError);
        return "/reservation.jsp";
    }
}

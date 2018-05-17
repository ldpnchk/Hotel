package ua.edu.controller.command;

import javax.servlet.http.HttpServletRequest;

import ua.edu.controller.filter.RolesAllowed;
import ua.edu.model.entity.Reservation;
import ua.edu.model.entity.ReservationStatus;
import ua.edu.model.entity.Room;
import ua.edu.model.entity.UserRole;
import ua.edu.model.exception.DateParserException;
import ua.edu.model.service.ReservationService;
import ua.edu.model.service.RoomService;
import ua.edu.model.util.ConfigManager;
import ua.edu.model.util.DateParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AdminCommand implements Command{
	
	@Override
	@RolesAllowed(roles = {UserRole.ADMINISTRATOR})
	public String execute(HttpServletRequest request) {

		Optional<LocalDate> startDate = Optional.empty();
		Optional<LocalDate> endDate = Optional.empty();
		String dates = request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_DATEFILTER));
		if(dates != null){
			String[] datesArr = dates.split("-");
			try {
				startDate = Optional.of(DateParser.getInstance().parseDate(datesArr[0]));
				endDate = Optional.of(DateParser.getInstance().parseDate(datesArr[1]));
			} catch (DateParserException e) {
				System.out.println("Date Parser Exception");
			}
		}
		Optional<Integer> roomId = Optional.empty();
		if(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_ROOM_ID)) != null
				&& !request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_ROOM_ID)).equals(""))
			roomId = Optional.of(Integer.parseInt
					(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_ROOM_ID))));
		Optional<ReservationStatus> reservationStatus = Optional.empty();
		if((request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS))) != null
				&& !request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS)).equals(""))
			reservationStatus = Optional.of(ReservationStatus.getReservationStatus
					(request.getParameter(ConfigManager.getInstance().getString(ConfigManager.PARAMETER_STATUS))));

		List<Reservation> reservations = ReservationService.getInstance().getReservationsWithRoomAndRoomTypeByDatesAndRoomAndStatus
				(startDate, endDate, roomId, reservationStatus);
		List<Room> rooms = RoomService.getInstance().getAllRooms();
		request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_RESERVATIONS), reservations);
		request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_ROOMS), rooms);
		request.setAttribute(ConfigManager.getInstance().getString(ConfigManager.ATTRIBUTE_STATUSES), ReservationStatus.values());
		return ConfigManager.getInstance().getString(ConfigManager.PAGE_ADMIN);
	}

}

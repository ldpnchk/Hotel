package ua.edu.controller.command;

import java.util.HashMap;
import java.util.Map;

import ua.edu.controller.command.authorization.LoginPageCommand;
import ua.edu.controller.command.authorization.LoginCommand;
import ua.edu.controller.command.authorization.LogoutCommand;
import ua.edu.controller.command.reservation.CreateReservationCommand;
import ua.edu.controller.command.reservation.DeleteReservationCommand;
import ua.edu.controller.command.reservation.ReservationDetailsPageCommand;
import ua.edu.controller.command.reservation.ReservationPageCommand;
import ua.edu.controller.command.reservation.UpdateReservationCommand;
import ua.edu.controller.command.user.RegistrationCommand;
import ua.edu.controller.command.user.RegistrationPageCommand;

public class CommandManager {
	
	private static volatile CommandManager instance;
	
	private final Map<String, Command> commands = new HashMap<String, Command>();
	
    private CommandManager() {
    	commands.put("main", new MainCommand());
        commands.put("registration", new RegistrationPageCommand());
        commands.put("register", new RegistrationCommand());
        commands.put("login", new LoginPageCommand());
        commands.put("loginpost", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("admin", new AdminCommand());
        commands.put("client", new ClientCommand());
        commands.put("reservation", new ReservationPageCommand());
        commands.put("createReservation", new CreateReservationCommand());
        commands.put("reservationDetails", new ReservationDetailsPageCommand());
        commands.put("updateReservation", new UpdateReservationCommand());
        commands.put("deleteReservation", new DeleteReservationCommand());
    }

    public static CommandManager getInstance() {
    	if (instance == null) {
            synchronized (CommandManager.class) {
                if (instance == null) {
                    instance = new CommandManager();
                }
            }
        }
        return instance;
    }
    
    public Command getCommand(String path) {
    	return commands.getOrDefault(path, (r)->"/hotel/main");
    }

}

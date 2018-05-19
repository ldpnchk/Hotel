package ua.edu.controller.command;

import java.util.HashMap;
import java.util.Map;

import ua.edu.controller.command.authorization.LoginPageCommand;
import ua.edu.controller.command.authorization.LoginCommand;
import ua.edu.controller.command.authorization.LogoutCommand;
import ua.edu.controller.command.payment.CreatePaymentCommand;
import ua.edu.controller.command.payment.DeletePaymentCommand;
import ua.edu.controller.command.reservation.*;
import ua.edu.controller.command.user.RegistrationCommand;
import ua.edu.controller.command.user.RegistrationPageCommand;
import ua.edu.controller.command.user.UpdateUserCommand;
import ua.edu.controller.command.user.UserProfilePageCommand;

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
        commands.put("allReservations", new AllReservationsPageCommand());
        commands.put("reservation", new ReservationPageCommand());
        commands.put("createReservation", new CreateReservationCommand());
        commands.put("reservationDetails", new ReservationDetailsPageCommand());
        commands.put("updateReservation", new UpdateReservationCommand());
        commands.put("deleteReservation", new DeleteReservationCommand());
        commands.put("updateProfile", new UserProfilePageCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("clientReservations", new ClientReservationsPageCommand());
        commands.put("createPayment", new CreatePaymentCommand());
        commands.put("deletePayment", new DeletePaymentCommand());
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
    	path = path.replaceAll(".*/hotel/" , "");
    	return commands.getOrDefault(path, (r)->"/hotel/main");
    }

}

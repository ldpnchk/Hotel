package ua.edu.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
	
	private static volatile CommandManager instance;
	
	private final Map<String, Command> commands = new HashMap<String, Command>();
	
    private CommandManager() {
    	commands.put("main", new MainCommand());
        commands.put("registration", new RegistrationPageCommand());
        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("loginpost", new LoginPostCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("admin", new AdminCommand());
        commands.put("client", new ClientCommand());
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
    	return commands.getOrDefault(path, (r)->"main");
    }

}

package ua.edu.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.edu.controller.command.AdminCommand;
import ua.edu.controller.command.ClientCommand;
import ua.edu.controller.command.Command;
import ua.edu.controller.command.LoginCommand;
import ua.edu.controller.command.LoginPostCommand;
import ua.edu.controller.command.LogoutCommand;
import ua.edu.controller.command.MainCommand;
import ua.edu.controller.command.RegisterCommand;
import ua.edu.controller.command.RegistrationPageCommand;

@WebServlet(urlPatterns = {"/hotel/*"})
public class Servlet extends HttpServlet {
	
    private Map<String, Command> commands = new HashMap<String, Command>();
	
    public void init(){
    	commands.put("main", new MainCommand());
        commands.put("registration", new RegistrationPageCommand());
        commands.put("register", new RegisterCommand());
        commands.put("login", new LoginCommand());
        commands.put("loginpost", new LoginPostCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("admin", new AdminCommand());
        commands.put("client", new ClientCommand());
    }
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	processRequest(request, response);
    }
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Main servlet");
    	String path = request.getRequestURI();
    	System.out.println("Path: " + path);
    	path = path.replaceAll(".*/hotel/" , "");
    	System.out.println("Path: " + path);
    	Command command = commands.getOrDefault(path, (r)->"redirect:/index.jsp");
    	String page = command.execute(request);
    	System.out.println("Page: " + page);
        if(page.contains("redirect")){
        	System.out.println("redirect");
            response.sendRedirect(page.replace("redirect:", "/hotel"));
        } else {
        	System.out.println("forward");
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

}
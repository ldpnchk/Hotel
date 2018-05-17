package ua.edu.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.edu.controller.command.Command;
import ua.edu.controller.command.CommandManager;
import ua.edu.model.util.ConfigManager;

@WebServlet(urlPatterns = {"/hotel/*"})
public class Servlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		super.init();
		getServletContext().setAttribute(ConfigManager.getInstance()
    			.getString(ConfigManager.ATTRIBUTE_LOGGED_USERS), new HashMap<String, HttpSession>());
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	processRequest(request, response);
    }
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path = request.getRequestURI();
    	Command command = CommandManager.getInstance().getCommand(path);
    	String page = command.execute(request);
    	if(page.contains(ConfigManager.getInstance().getString(ConfigManager.REDIRECT))){
            response.sendRedirect(page.replace(ConfigManager.getInstance().getString(ConfigManager.REDIRECT),
            		ConfigManager.getInstance().getString(ConfigManager.MAIN_DEFAULT)));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

}
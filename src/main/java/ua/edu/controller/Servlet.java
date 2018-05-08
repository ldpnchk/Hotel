package ua.edu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.edu.controller.command.Command;
import ua.edu.controller.command.CommandManager;

@WebServlet(urlPatterns = {"/hotel/*"})
public class Servlet extends HttpServlet {
	
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
    	Command command = CommandManager.getInstance().getCommand(path);
    	System.out.println("Command: " + command.getClass().getName());
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
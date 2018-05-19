package ua.edu.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.edu.model.entity.User;
import ua.edu.model.entity.UserRole;
@WebFilter(urlPatterns = {"/hotel/*"})
public class AuthenticationFilter implements Filter {
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = ((HttpServletRequest) request).getSession(true);
		System.out.println(httpRequest.getRequestURI());
		User user = (User) session.getAttribute("user");
        if (AccessManager.getInstance().checkAccess(httpRequest.getRequestURI().replaceAll(".*/hotel/" , ""), 
        		user == null ? UserRole.GUEST : user.getUserRole())) {
        	filterChain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/hotel/main");
        }

    }

    @Override
    public void destroy() {

    }

}

package logic.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.collections.ObservableList;
import logic.database.ProjectDAO;
import logic.model.Session;

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean result = false;
		ProjectDAO projectDAO = new ProjectDAO();
		
		String username = "";
		
		Cookie cookies[] = request.getCookies();
		for(Cookie cookie: cookies) {
			if(cookie.getName().equals("result"))
				result = Boolean.parseBoolean(cookie.getValue());
			if(cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		if(result) {
			ObservableList<String> projectList = projectDAO.getAllUserProjects(Session.getSession().getLoggedUser());
			ArrayList<String> projects = new ArrayList<>(projectList);
			
			request.setAttribute("project_list", projects);
			request.setAttribute("user", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/homepage.jsp");
			dispatcher.forward(request, response);
		}
	}
}

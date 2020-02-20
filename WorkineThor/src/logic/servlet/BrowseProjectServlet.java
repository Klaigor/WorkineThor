package logic.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.collections.ObservableList;
import logic.database.ProjectDAO;


@WebServlet("/browse")
public class BrowseProjectServlet extends HttpServlet {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
    
	/**
	 * Display the Browse project page
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProjectDAO projectDAO = new ProjectDAO();
		
			ObservableList<String> projectList = projectDAO.getAllProjects();
			ArrayList<String> projects = new ArrayList<>(projectList);
					
			request.setAttribute("project_list", projects);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/browse.jsp");
			dispatcher.forward(request, response);
		}
}

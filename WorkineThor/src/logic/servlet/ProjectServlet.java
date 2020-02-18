package logic.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.ProjectBean;
import logic.database.ProjectDAO;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * fetches project data from the DB and displays it in project.jsp
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ProjectDAO projectDAO = new ProjectDAO();
		ProjectBean projectBean = new ProjectBean();
		
		String activeProject = request.getParameter("id");
		projectBean.setProjectName(activeProject);
		ArrayList<String> projectUsers = new ArrayList<>(projectDAO.getAllProjectUsers(projectBean));
		
		request.setAttribute("active_project", activeProject);
		request.setAttribute("project_users", projectUsers);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/project.jsp");
		rd.forward(request, response);
	}
}

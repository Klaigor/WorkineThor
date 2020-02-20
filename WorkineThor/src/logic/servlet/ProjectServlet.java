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
import logic.bean.ProjectBean;
import logic.database.FileDAO;
import logic.database.ProjectDAO;
import logic.model.Session;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * fetches project data from the DB and displays it in project.jsp
	 * 
	 * @return
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ProjectDAO projectDAO = new ProjectDAO();
		FileDAO fileDAO = new FileDAO();
		ProjectBean projectBean = new ProjectBean();
		
		String activeProject = request.getParameter("id");
		projectBean.setProjectName(activeProject);
		
		ObservableList<String> userResult;
		ObservableList<String> fileResult;
		
		userResult = projectDAO.getAllProjectUsers(projectBean);
		fileResult = fileDAO.getAllProjectFiles(projectBean.getProjectName());
		
		ArrayList<String> projectUsers = new ArrayList<>(userResult);
		ArrayList<String> files = new ArrayList<>(fileResult);
		
		// set currentBrowsingProject on the session
		Session.getSession().setCurrentBrowsingProject(projectBean);

		String admin = projectDAO.getProjectAdmin(projectBean);

		request.setAttribute("files", files);
		request.setAttribute("admin", admin);
		request.setAttribute("active_project", activeProject);
		request.setAttribute("project_users", projectUsers);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/project.jsp");
		rd.forward(request, response);
	}
}

package logic.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.ProjectBean;
import logic.controller.CreateProjectController;
import logic.exceptions.ProjectAlreadyExistsException;

@WebServlet("/create-project")
public class CreateProjectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * create a new project instance in the DB with the data received by create-project.jsp
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean driveActive = false;
		boolean result = false;
		String projectName = request.getParameter("project-name");
		String checkbox = request.getParameter("drive-active");
		String driveName = request.getParameter("drive-select");
		
		if(checkbox == null)
			driveActive = false;
		else 
			driveActive = true;
		
		ProjectBean projectBean = new ProjectBean();
		projectBean.setProjectName(projectName);
		projectBean.setDriveIsActive(driveActive);
		
		if(driveActive) {
			projectBean.setDriveName(driveName);
		}
		
		CreateProjectController controller = CreateProjectController.getInstace();
		
		//creates project on DB
		try {
			controller.createProject(projectBean);
			result = true;
		} catch (ProjectAlreadyExistsException e) {
			result = false;
		}
	
		if(result)
			response.sendRedirect("homepage");
		else {
			response.sendRedirect("jsp/create-project.jsp?failure="+result);
		}
	}
}

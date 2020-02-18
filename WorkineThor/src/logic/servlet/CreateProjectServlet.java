package logic.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PrintWriter out = response.getWriter();
		boolean driveActive;
		String projectName = request.getParameter("project_name");
		String checkbox = request.getParameter("drive_active");
		
		if(checkbox == null)
			driveActive = false;
		else 
			driveActive = true;
		out.println(projectName+ driveActive);
	}
}

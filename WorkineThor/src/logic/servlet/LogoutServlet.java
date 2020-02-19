package logic.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.model.Session;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/* reset Session attributes */
		Session.getSession().getLoggedUser().setUsername(null);
		Session.getSession().getLoggedUser().setPassword(null);
		Session.getSession().setUser(null);
		Session.getSession().setProject(null);
		
		/* redirect to login page */
		response.sendRedirect("index.jsp");
	}
}

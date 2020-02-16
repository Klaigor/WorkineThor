package logic.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		boolean result = false;
		String username = "";
		
		Cookie cookies[] = request.getCookies();
		for(Cookie cookie: cookies) {
			if(cookie.getName().equals("result"))
				result = Boolean.parseBoolean(cookie.getValue());
			if(cookie.getName().equals("username"))
				username = cookie.getValue();
		}
		
		if(result) {
			request.setAttribute("user", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
			dispatcher.forward(request, response);
		}
	}
}

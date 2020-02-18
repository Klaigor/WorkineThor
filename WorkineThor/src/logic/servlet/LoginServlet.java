package logic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.UserBean;
import logic.controller.LoginController;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * if login was a success sends data to HomePageServlet
	 */
	public void	doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		boolean result = false;
		UserBean userBean = new UserBean();
		LoginController controller = new LoginController();
		
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		Cookie usernameCookie = new Cookie("username", username);
		response.addCookie(usernameCookie);
		
		String password = request.getParameter("password");
		
		userBean.setUsername(username);
		userBean.setPassword(password);
		
		try {
			result = controller.signin(userBean);
		} catch (SQLException e) {}
		
		Cookie cookie = new Cookie("result", String.valueOf(result));
		response.addCookie(cookie);
		
		if(result) {
			response.sendRedirect("homepage");
		}
		else {
			out.println("<html>"+
						"<body>");
			out.println("Wrong Username or Password");
			out.println("<a href=\"index.jsp\">"+
						"<input type=\"submit\" value=\"retry\""+
						"</a>"+
						"</body>"+
						"</html>");
		}
	}
}

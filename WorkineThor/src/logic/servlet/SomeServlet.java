package logic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.UserBean;
import logic.controller.LoginController;

public class SomeServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		boolean result = false;
		UserBean userBean = new UserBean();
		LoginController controller = new LoginController();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		userBean.setUsername(username);
		userBean.setPassword(password);
		
		try {
			result = controller.signin(userBean);
		} catch (SQLException e) {}
		
		PrintWriter out = response.getWriter(); 
		
		if(result)
			out.println("logged!");
		else
			out.println("username or password incorrect");
	}
}

package logic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.UserBean;
import logic.controller.LoginController;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{

	/** this number serve per far si che se scarico 
	 * in persistenza qualcosa scritto con questa classe, 
	 * quando lo vado a riprendere, posso controllare che la versione che riprende
	 * dalla persistenza l'oggetto, sia uguale o compliant con la versione della classe 
	 * che ha messo l'oggetto in persistenza
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * if Signup is a success sends data to HomePageServlet
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean result = false;
		UserBean  userBean = new UserBean();
		LoginController controller = new LoginController();
		

		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		Cookie usernameCookie = new Cookie("username", username);
		response.addCookie(usernameCookie);
		
		String password = request.getParameter("password");
	
		userBean.setUsername(username);
		userBean.setPassword(password);
		
		try {
			result = controller.signup(userBean);
		} catch (SQLException e) {}
		
		Cookie cookie = new Cookie("result", String.valueOf(result));
		response.addCookie(cookie);
		
		if(result) {
			response.sendRedirect("homepage");
		}else {
			response.sendRedirect("index.jsp?failure=true");
		}
		
	}
}

























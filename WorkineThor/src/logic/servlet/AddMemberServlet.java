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
import logic.controller.AddMemberController;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/add-member")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		AddMemberController controller = AddMemberController.getInstace();
		ProjectBean bean = new ProjectBean();
		
		ObservableList<String> membersList = controller.showMemberToAddToProject(bean);
		ArrayList<String> members = new ArrayList<> (membersList);
		
		request.setAttribute("member_list", members);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/addMember.jsp");
		dispatcher.forward(request, response);
		
	}

}

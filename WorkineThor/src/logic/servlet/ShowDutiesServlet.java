package logic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.controller.WatchDutiesController;
import logic.model.Duty;

/**
 * Servlet implementation class ShowDutiesServlet
 */
@WebServlet("/duties")
public class ShowDutiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDutiesServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("alive");
		
		String[] project = request.getParameterValues("project-name");
		WatchDutiesController controller = new WatchDutiesController();
		ArrayList<Duty> duties = (ArrayList<Duty>) controller.getWebProjectDuties(project[0]);
		int numberOfDuties = duties.size();
		ArrayList<String> dutiesNames = new ArrayList<>();
		
		for (int i = 0; i < numberOfDuties; i++) {
			dutiesNames.add(duties.get(i).getName());			
		}
		request.setAttribute("project_name", project[0]);
		request.setAttribute("duties_list", dutiesNames);		
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/showDuties.jsp");
		dispatcher.forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}

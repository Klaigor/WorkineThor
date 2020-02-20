package logic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.FileBean;
import logic.controller.AddFileController;
import logic.exceptions.FileAlreadyExistsException;
import logic.model.Session;

@WebServlet("/add-file")
public class AddFileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * da finire
	 * @throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Logger logger = Logger.getLogger(AddFileServlet.class.getName());
		PrintWriter out = response.getWriter();
		out.println("activated");
		
		boolean result = false;
		
		String project = Session.getSession().getCurrentBrowsingProject().getProjectName();
		String[] filePaths = request.getParameterValues("fileArray");
		String[] tokenizedPaths = filePaths[0].split(",");
		
		ArrayList<String> fileNames = new ArrayList<>();
		
		for(String file: tokenizedPaths) {
			fileNames.add(file.substring(file.lastIndexOf('\\')+1));
		}
		
		for(String files: fileNames) {
			out.println(files);
		}
		
		AddFileController controller = AddFileController.getInstace();
		FileBean fileBean = new FileBean();
		
		for(int i=0; i<tokenizedPaths.length; i++) {
			fileBean.setFileName(fileNames.get(i));
			fileBean.setFilePath(tokenizedPaths[i]);
			out.println(fileBean.getFileName() + fileBean.getFilePath());
			
			if(!fileBean.getFileName().isEmpty() && !fileBean.getFilePath().isEmpty()) {
				try {
					controller.addFileToProject(fileBean, project);
				} catch (FileAlreadyExistsException e) {
					result = true;
					logger.log(Level.SEVERE, "Exception occurred file already exists");
				}
			}
			
			if(!result)
				response.sendRedirect("jsp/add-file.jsp");
			else {
				response.sendRedirect("jsp/add-file.jsp?failure="+result);
			}
		}
		
	}
}

package logic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.bean.FileBean;
import logic.controller.AddFileController;
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
		PrintWriter out = response.getWriter();
		out.println("activated");
		
		String project = Session.getSession().getCurrentBrowsingProject().getProjectName();
		String[] filePaths = request.getParameterValues("fileArray");
		String[] tokenizedPaths = filePaths[0].split(",");
		
		ArrayList<String> fileNames = new ArrayList<>();
		
		for(String file: tokenizedPaths) {
			fileNames.add(file.substring(file.lastIndexOf("\\")+1));
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
				controller.addFileToProject(fileBean, project);
			}
		}
		
		
	}
}

package logic.servlet;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.stage.FileChooser;
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
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) {
		Logger logger = Logger.getLogger(AddFileServlet.class.getName());
		boolean success = false;
		
		String projectName = request.getParameter("project-name");
		
		AddFileController controller = AddFileController.getInstace();
		
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);

		FileBean fileBean = new FileBean();
		
		if (selectedFile != null) {
			fileBean.setFilePath(selectedFile.getPath());
			fileBean.setFileName(selectedFile.getName());
			controller.addFileToProject(fileBean, projectName);
			success = true;
		} else {
			logger.log(Level.WARNING, "No file selected");
			success = false;
		}
		
		if(success) {}
	}
}

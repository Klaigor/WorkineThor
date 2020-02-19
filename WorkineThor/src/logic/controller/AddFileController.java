package logic.controller;

import logic.bean.FileBean;
import logic.database.FileDAO;
import logic.exceptions.FileAlreadyExistsException;

public class AddFileController {

	private static AddFileController instance;
	
	/**
	 * private constructor(singleton)
	 */
	private AddFileController() {}
	
	/**
	 * returns active instance
	 * @return
	 */
	public static AddFileController getInstace() {
		if(instance == null)
			instance = new AddFileController();
		return instance;
	}
	
	/**
	 * add file to DB
	 * @param fileBean
	 * @param projectBean
	 * @throws FileAlreadyExistsException 
	 */
	public void addFileToProject(FileBean fileBean, String project) throws FileAlreadyExistsException {
		FileDAO fileDAO = new FileDAO();
		
		fileDAO.addFileToProject(fileBean, project);
	}
}

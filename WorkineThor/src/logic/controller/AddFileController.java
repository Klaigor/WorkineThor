package logic.controller;

import logic.bean.FileBean;
import logic.database.FileDAO;

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
	 */
	public void addFileToProject(FileBean fileBean, String project) {
		FileDAO fileDAO = new FileDAO();
		
		fileDAO.addFileToProject(fileBean, project);
	}
}

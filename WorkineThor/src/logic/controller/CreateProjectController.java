package logic.controller;

import java.io.File;

import logic.bean.FileBean;
import logic.bean.ProjectBean;
import logic.database.ProjectDAO;
import logic.model.Project;
import logic.model.Session;

public class CreateProjectController{
	
	//singleton instance(only one controller)
	private static CreateProjectController instance = null;
	
	private Project newProject;
	
	/**
	 * private constructor
	 */
	private CreateProjectController() {}
	
	/**
	 * method that returns active instance
	 * @return CreateProjectController
	 */
	public static CreateProjectController getInstace() {
		if(instance == null) 
			instance = new CreateProjectController();
		return instance;
	}
	
	/**
	 * method that creates a new project
	 * @param bean
	 */
	public void createProject(ProjectBean bean) {
		ProjectDAO projectDAO = new ProjectDAO();
		newProject = new Project(bean.getProjectName(), bean.getDriveIsActive(), bean.getDriveName());
		
		projectDAO.addProjectToDB(newProject, Session.getSession());
	}
	
	public void addFile(FileBean bean) {
		File file = new File(bean.getFilePath());
		newProject.addFile(file);
	}
	
	public String getProjectName() {
		return newProject.getProjectName();
	}
	
	public boolean getDriveActive() {
		return newProject.getDriveActive();
	}
	
	public String getDriveName() {
		return newProject.getDriveName();
	}
	
	public Project getProject() {
		return newProject;
	}
	
}

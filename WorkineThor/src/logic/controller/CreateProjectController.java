package logic.controller;

import java.io.File;

import logic.bean.FileBean;
import logic.bean.ProjectBean;
import logic.database.FileDAO;
import logic.database.ProjectDAO;
import logic.exceptions.FileAlreadyExistsException;
import logic.exceptions.ProjectAlreadyExistsException;
import logic.model.Project;
import logic.model.Session;

public class CreateProjectController {

	// singleton instance(only one controller)
	private static CreateProjectController instance = null;

	private Project newProject;
	private Project existentProject;

	/**
	 * private constructor
	 */
	private CreateProjectController() {
	}

	/**
	 * method that returns active instance
	 * 
	 * @return CreateProjectController
	 */
	public static CreateProjectController getInstace() {
		if (instance == null)
			instance = new CreateProjectController();
		return instance;
	}

	/**
	 * method that creates a new project and adds it to the DB
	 * 
	 * @param bean
	 * @throws ProjectAlreadyExistsException 
	 */
	public boolean createProject(ProjectBean bean) throws ProjectAlreadyExistsException {
		ProjectDAO projectDAO = new ProjectDAO();
		newProject = new Project(bean.getProjectName(), bean.getDriveIsActive(), bean.getDriveName());

		projectDAO.addProjectToDB(newProject, Session.getSession());

		return true;
	}

	public boolean existentProject(ProjectBean bean) {
		ProjectDAO projectDAO = new ProjectDAO();
		existentProject = projectDAO.getProjectFromDB(bean);

		return true;
	}
	
	public void addMember(String user) {
		ProjectDAO projectDAO = new ProjectDAO();
		ProjectBean bean = new ProjectBean();
		bean.setProjectName(newProject.getProjectName());
		projectDAO.addMemberToProject(bean, user);
	}

	//DEPRECATED DO NOT USE
	public boolean addFile(FileBean bean) throws FileAlreadyExistsException {
		FileDAO fileDAO = new FileDAO();
		ProjectBean projectBean = new ProjectBean();
		File file = new File(bean.getFilePath());
		newProject.addFile(file);
		
		projectBean.setProjectName(newProject.getProjectName());
		fileDAO.addFileToProject(bean, projectBean.getProjectName());
		return true;
	}

	public String getNewProjectName() {
		return newProject.getProjectName();
	}

	public String getProjectName() {
		return existentProject.getProjectName();
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

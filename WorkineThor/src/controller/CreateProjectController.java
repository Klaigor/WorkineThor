package controller;

import bean.ProjectBean;
import model.Project;

public class CreateProjectController {
	
	//singleton instance(only one controller)
	private static CreateProjectController instance = null;
	
	private Project newProject;
	
	//singleton getInstance()
	public static CreateProjectController getInstace() {
		if(instance == null) 
			instance = new CreateProjectController();
		return instance;
	}
	
	//creates new project
	public void createProject(ProjectBean bean) {
		newProject = new Project(bean.getProjectName(), bean.getDriveIsActive(), bean.getDriveName());
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
	
	
}

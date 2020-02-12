package controller;

import java.io.File;

import bean.FileBean;
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

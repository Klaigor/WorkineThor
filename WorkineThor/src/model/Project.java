package model;

import java.io.File;
import java.util.ArrayList;

public class Project {

	private String projectName;
	private ArrayList<User> members;
	private ArrayList<File> files;
	private ArrayList<String> schedule;
	
	public Project() {
		this("");
	}
	
	public Project(String projectName) {
		this.projectName = projectName;
		members = new ArrayList<>();
		files = new ArrayList<>();
		schedule = new ArrayList<>();
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void addMember(User user) {
		members.add(user);
	}
	
	public void removeMember(User user) {
		int indexOfMember = members.indexOf(user);
		members.remove(indexOfMember);
	}
	
	public void addFile(File file) {
		files.add(file);
	}
	
	public void removeFile(File file) {
		int indexOfFile = files.indexOf(file);
		files.remove(indexOfFile);
	}
	
	public void addScheduleAction(String action) {
		schedule.add(action);
	}
	
	public void removeScheduleAction(String action) {
		int indexOfAction = schedule.indexOf(action);
		schedule.remove(indexOfAction);
	}
}

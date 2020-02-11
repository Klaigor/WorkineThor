package model;

import java.io.File;
import java.util.ArrayList;

public class Project {

	private String projectName;
	private String driveName;
	private boolean usesDrive;
	private ArrayList<User> members;
	private ArrayList<File> files;
	private ArrayList<String> schedule;
	
	//default constructor
	public Project() {
		this("", false, "");
	}
	
	//non-default constructor
	public Project(String projectName, boolean usesDrive, String driveName) {
		this.projectName = projectName;
		this.usesDrive = usesDrive;
		this.driveName = driveName;
		members = new ArrayList<>();
		files = new ArrayList<>();
		schedule = new ArrayList<>();
	}
	
	public String getDriveName() {
		return driveName;
	}
	
	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}
	
	public boolean getDriveActive() {
		return usesDrive;
	}
	
	public void setDriveActive(boolean choice) {
		usesDrive = choice;
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

package logic.model;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project {

	private Logger logger =  Logger.getLogger(Project.class.getName());
	
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
	
	public boolean addMember(User user) {
		members.add(user);
		return true;
	}
	
	public boolean removeMember(User user) {
		int indexOfMember = members.indexOf(user);
		members.remove(indexOfMember);
		return true;
	}
	
	public boolean addFile(File file) {
		files.add(file);
		return true;
	}
	
	public boolean removeFile(File file) {
		int indexOfFile = files.indexOf(file);
		files.remove(indexOfFile);
		return true;
	}
	
	public boolean showFiles() {
		for(File file: files) {
			logger.log(Level.INFO, file.getPath());
		}
		return true;
	}
	
	public boolean addScheduleAction(String action) {
		schedule.add(action);
		return true;
	}
	
	public boolean removeScheduleAction(String action) {
		int indexOfAction = schedule.indexOf(action);
		schedule.remove(indexOfAction);
		return true;
	}
}

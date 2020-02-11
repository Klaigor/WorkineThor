package bean;

public class ProjectBean {

	private String projectName;
	private boolean driveIsActive;
	private String driveName;
	private String userToAdd;
	
	//default constructor implicit
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}
	
	public String getDriveName() {
		return driveName;
	}
	
	public void setUserToAdd(String user) {
		userToAdd = user;
	}
	
	public String getUserToAdd() {
		return userToAdd;
	}
	
	public void setDriveIsActive(boolean choice) {
		driveIsActive = choice;
	}
	
	public boolean getDriveIsActive() {
		return driveIsActive;
	}
}

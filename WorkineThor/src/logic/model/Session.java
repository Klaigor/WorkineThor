/**
 * 
 * Singleton instance that manages the session and keeps track of the user that is logged in
 * @author Fuma
 *
 */
package logic.model;

import logic.bean.ProjectBean;

public class Session {
	private static Session sessionInstance = null;
	private User loggedUser = null;
	private Project currentProject = null;
	private Project currentBrowsingProject = null;
	
	/**
	 * constructor made private, to make this class a Singleton
	 */
	private Session() {}
	
	/**
	 * method that retrieves the singleton instance according to the pattern
	 * @return
	 */
	public static Session getSession() {
		if (sessionInstance == null) {
			sessionInstance = new Session();
		}		
		return sessionInstance;		
	}

	/**
	 * sets the user as the currently loggedUser for the session
	 * @param user
	 */
	public void setUser(User user) {
		this.loggedUser = user;
	}
	
	/**
	 * 		retrieves the logged user for the current session 
	 *  rerusn null if there isn't a logged user
	 * @return
	 */
	public User getLoggedUser() {
		return this.loggedUser;
	}
	
	public void setProject(Project project) {
		this.currentProject = project;
	}
	
	/**
	 * 		retrieves the logged user for the current session 
	 *  returnn null if there isn't a logged user
	 * @return
	 */
	public Project getCurrentProject() {
		return this.currentProject;
	}
	
	public void setCurrentBrowsingProject(ProjectBean bean) {
		currentBrowsingProject = new Project();
		currentBrowsingProject.setProjectName(bean.getProjectName());
		if(bean.getDriveName() != null) {
			currentBrowsingProject.setDriveActive(true);
			currentBrowsingProject.setDriveName(bean.getDriveName());
		}
	}
	
	public Project getCurrentBrowsingProject() {
		return currentBrowsingProject;
	}

}

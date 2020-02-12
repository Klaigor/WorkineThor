/**
 * 
 * Singleton instance that manages the session and keeps track of the user that is logged in
 * @author Fuma
 *
 */
package model;

public class Session {
	private static Session sessionInstance = null;
	private User loggedUser = null;

	
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

}

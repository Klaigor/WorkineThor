/*
 * BEAN clas sfor the User entity, 
 * it has just get and set methods for now
 */
package bean;

public class UserBean {
	
	private String username;
	private String password;
	
	public void setPassword( String passwd) {
		this.password = passwd; 
	}
	
	public void setUsername( String usrname) {
		this.username = usrname; 
	}
	
	public String getPassword() {
		return this.password; 
	}
	
	public String getUsername() {
		return this.username; 
	}

}

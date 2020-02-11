/**
 * controller of the Login and Signup use cases
 */
package controller;

import java.sql.SQLException;

import bean.UserBean;
import database.UserDAO;


public class LoginController {

	/**
	 * takes a {@link UserBean} instance and sends it to the {@link UserDAO}
	 * to make an insert query 
	 * @param user
	 * @throws SQLException
	 */
	public void signup (UserBean user) throws SQLException {
		UserDAO usrDAO = new UserDAO();
		usrDAO.signup(user.getUsername(), user.getPassword());
		
		System.out.println("porocdio?2");
	}
}



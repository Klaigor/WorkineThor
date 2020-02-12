/**
 * controller of the Login and Signup use cases
 */
package controller;

import java.sql.SQLException;
import bean.UserBean;
import database.UserDAO;
import model.User;

public class LoginController {

	/**
	 * takes a {@link UserBean} instance and sends it to the {@link UserDAO}
	 * to make an insert query 
	 * @param user
	 * @throws SQLException
	 */
	public void signup (UserBean user) throws SQLException {
		UserDAO usrDAO = new UserDAO();
		usrDAO.insert(user);
	}
	
	
	/*
	 * takes in input a UserBean and check if the user-name and password match with one in the DB
	 * if it finds a match returns true otherwise false
	 */
	public boolean signin (UserBean user) throws SQLException {
		UserDAO usrDAO = new UserDAO();
		UserBean result = usrDAO.getUser(user);

		if (result.getUsername().equals("")) {
		}
		
		if(result.getUsername().equals(user.getUsername()) & result.getPassword().equals(user.getPassword())) {
			
			return true;
		}
		else return false;
	}
}



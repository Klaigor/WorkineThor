/**
 * controller of the Login and Signup use cases
 */
package logic.controller;

import java.sql.SQLException;
import logic.bean.UserBean;
import logic.database.UserDAO;
import logic.exceptions.UserAlreadyExistException;
import logic.exceptions.WrongLoginException;
import logic.model.Session;
import logic.model.User;

public class LoginController{

	/**
	 * takes a {@link UserBean} instance and sends it to the {@link UserDAO} to make
	 * an insert query
	 * 
	 * @param user
	 * @throws SQLException
	 * @throws UserAlreadyExistException 
	 */
	public boolean signup(UserBean user) throws SQLException, UserAlreadyExistException {
		UserDAO usrDAO = new UserDAO();
		usrDAO.insert(user);
		User usr = new User();
		usr.setPassword(user.getPassword());
		usr.setUsername(user.getUsername());

		Session session = Session.getSession();
		session.setUser(usr);
		return true;
	}

	/*
	 * takes in input a UserBean and check if the user-name and password match with
	 * one in the DB if it finds a match returns true otherwise false
	 */
	public boolean signin(UserBean user) throws SQLException, WrongLoginException {
		UserDAO usrDAO = new UserDAO();
		UserBean result = usrDAO.getUser(user);

		if (result.getUsername().equals("")) {
			throw new WrongLoginException(result + "is a wrong username");
		}

		if (result.getUsername().equals(user.getUsername()) & result.getPassword().equals(user.getPassword())) {

			User usr = new User();
			usr.setPassword(result.getPassword());
			usr.setUsername(result.getUsername());

			Session session = Session.getSession();
			session.setUser(usr);

			return true;
		}else return false;
		
	}

}

/**
 *  DAO class for Users entity, it will take care of the persistence, by connecting  
 *  to the DB through the Singleton instance dDDBHandle
 */
package logic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.bean.UserBean;
import logic.exceptions.UserAlreadyExistException;
import logic.model.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {

	private DBhandle dbHandler = DBhandle.getDBhandleInstance();
	private PreparedStatement pst;
	//lo ha voluto sonarcloud
	private String usernamestring = "username";
	/**
	 * prepares the statement and then sends it to the DB
	 * 
	 * @throws SQLException
	 * @throws UserAlreadyExistException 
	 */
	public boolean insert(UserBean user) throws SQLException, UserAlreadyExistException {
		String insert = "INSERT INTO users(username,password)" + "VALUES (?,?)";

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(insert);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());

		try {
			Integer error = pst.executeUpdate();
			if (error == 3) {
				throw new UserAlreadyExistException ("user already exist");	
			}
		} catch (SQLException e){
			e.printStackTrace();
		}	
		pst.executeUpdate();
		return true;
	}

	public UserBean getUser(UserBean userIN) throws SQLException {
		UserBean userOut = new UserBean();
		String getUser = "SELECT * from users where username=? and password=?";

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(getUser);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		pst.setString(1, userIN.getUsername());
		pst.setString(2, userIN.getPassword());
		ResultSet rs = pst.executeQuery();

		if (!rs.first()) { // rs empty no user with the correct username or password
			userOut.setUsername("");
			userOut.setPassword("");
		} else {
			userOut.setUsername(rs.getString(usernamestring));
			userOut.setPassword(rs.getString("password"));
		}
		return userOut;
	}

	public ObservableList<String> getAllUsers() throws SQLException { 
		String getAllUsers = "SELECT username from users WHERE username != ?";
		ObservableList<String> allUsers = FXCollections.observableArrayList();

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(getAllUsers);

			pst.setString(1, Session.getSession().getLoggedUser().getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = pst.executeQuery();

		if (!rs.first()) { // rs empty no user with the correct username

		} else {
			allUsers.addAll(rs.getString(usernamestring));
			while (rs.next())
				allUsers.addAll(rs.getString(usernamestring));
		}
		return allUsers;
	}
	
	public boolean deleteUser(UserBean user) throws SQLException {
		String insert = "DELETE FROM users WHERE username=? and password=?";

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(insert);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());

		pst.executeUpdate();
		return true;
	}

}

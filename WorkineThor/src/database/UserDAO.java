/**
 *  DAO class for Users entity, it will take care of the persistenc, by connecting  
 *  to the DB through the Singleton instance dDDBHandle
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.UserBean;


public class UserDAO {
	
	private DBhandle dbHandler = DBhandle.getDBhandleInstance(); 
	private PreparedStatement pst;
	
	/**
	 * prepares the statement and then sends it to the DB
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void insert(UserBean user) throws SQLException {
		String insert = "INSERT INTO users(username,password)"+"VALUES (?,?)";
		
		Connection dbConnection = dbHandler.getConnection();
		
		try {
			
			pst = dbConnection.prepareStatement(insert);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());
		
		pst.executeUpdate();
		
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
		
        if (!rs.first()){ // rs empty no user with the correct username or password
        	userOut.setUsername("");
        	userOut.setPassword("");
        }
        else {
        	userOut.setUsername(rs.getString("username"));
        	userOut.setPassword(rs.getString("password"));       
        }
		return userOut;	
	}

}





















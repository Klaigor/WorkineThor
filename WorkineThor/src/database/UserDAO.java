/**
 *  DAO class for Users entity, it will take care of the persistenc, by connecting  
 *  to the DB through the Singleton instance dDDBHandle
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDAO {
	
	private Connection dbConnection;
	private DBhandle dbHandler = DBhandle.getDBhandleInstance(); 
	private PreparedStatement pst;
	
	/**
	 * prepares the statement and then sends it to the DB
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public void signup(String username, String password) throws SQLException {
		String insert = "INSERT INTO users(username,password)"+"VALUES (?,?)";
		
		dbConnection = dbHandler.getConnection();
		
		try {
			
			pst = dbConnection.prepareStatement(insert);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pst.setString(1, username);
		pst.setString(2, password);
		
		pst.executeUpdate();
		
	}

}

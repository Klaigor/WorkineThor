/**
 * DAO class for file key abstraction, it will take care of the persistence, by connecting  
 *  to the DB through the Singleton instance dDDBHandle
 */
package database;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bean.FileBean;
import bean.ProjectBean;

public class FileDAO {
	private DBhandle dbHandler = DBhandle.getDBhandleInstance();
	private Connection dbConnection;
	private PreparedStatement pst;
	
	/**
	 * prepares the statement and then sends it to the DB
	 * 
	 * @param filePath
	 * @param fileName
	 * @param projectName
	 * @throws SQLException
	 */
	
	public void insert(File file, String projectName) {
		String insert = "INSERT INTO file(path,nome,project)" + "VALUES (?,?,?)";
		
		dbConnection = dbHandler.getConnection();
		
		try {
			pst = dbConnection.prepareStatement(insert);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		pst.setString(1, file);
	}
}

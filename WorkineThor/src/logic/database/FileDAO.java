/**
 * DAO class for file key abstraction, it will take care of the persistence, by connecting  
 *  to the DB through the Singleton instance dDDBHandle
 */
package logic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.bean.FileBean;
import logic.bean.ProjectBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileDAO {
	private DBhandle dbHandler = DBhandle.getDBhandleInstance();
	private Connection dbConnection;
	private PreparedStatement pst;

	/**
	 * prepares the statement and then sends it to the DB
	 * 
	 * @param filePatha
	 * @param fileName
	 * @param projectName
	 * @throws SQLException
	 */

	public boolean insert(FileBean file, ProjectBean projectName) throws SQLException {
		String insert = "INSERT INTO file(path,nome,project)" + "VALUES (?,?,?)";

		dbConnection = dbHandler.getConnection();

		try {
			pst = dbConnection.prepareStatement(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		pst.setString(1, file.getFilePath());
		pst.setString(2, file.getFileName());
		pst.setString(3, projectName.getProjectName());

		pst.executeUpdate();
		return true;
	}

	public ObservableList<String> getAllFile() throws SQLException {
		String getAllFile = "SELECT * from file";
		ObservableList<String> allFile = FXCollections.observableArrayList();

		dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(getAllFile);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = pst.executeQuery();

		if (!rs.first()) { // rs empty no File

		} else {
			while (rs.next())
				allFile.addAll(rs.getString("path"));
				allFile.addAll(rs.getString("nome"));
				allFile.addAll(rs.getString("Project"));
		}
		return allFile;
	}
}
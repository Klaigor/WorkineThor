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
import logic.exceptions.FileAlreadyExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileDAO {
	private DBhandle dbHandler = DBhandle.getDBhandleInstance();
	private Connection dbConnection;
	private PreparedStatement pst;

	/**
	 * prepares the statement and then sends it to the DB
	 * 
	 * @param file
	 * @param projectName
	 * @throws SQLException
	 */
	public boolean insert(FileBean file, ProjectBean projectName) throws SQLException {
		String insert = "INSERT INTO files(path,name,project)" + "VALUES (?,?,?)";

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

	/**
	 * get all files in the application
	 * @return
	 * @throws SQLException
	 */
	public ObservableList<String> getAllFile() throws SQLException {

		String getAllFile = "SELECT * from files";
		
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
			while (rs.next()) {
				allFile.addAll(rs.getString("path"));
				allFile.addAll(rs.getString("name"));
				allFile.addAll(rs.getString("Project"));
			}
		}
		return allFile;
	}
	
	//modificata per cod smell
	/**
	 * returns all project files
	 * @param project
	 * @return
	 */
	public ObservableList<String> getAllProjectFiles(String project){
		ObservableList<String> files = FXCollections.observableArrayList();
		String sqlString = "SELECT name FROM files WHERE project = ?";
		
		dbConnection = dbHandler.getConnection();
		
		try {
			pst = dbConnection.prepareStatement(sqlString);
			pst.setString(1, project);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.first()) {
				do {
					files.addAll(rs.getString("name"));
				}while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return files;
	}
	
	//modificata per cod smell
	/**
	 * adds current file to project
	 * @param fileBean
	 * @param project
	 * @return
	 * @throws FileAlreadyExistsException 
	 */
	public boolean addFileToProject(FileBean fileBean, String project) throws FileAlreadyExistsException {
		String sqlString = "INSERT INTO files(path,name,project) VALUES (?,?,?)";
		
		boolean result = fileAlreadyExists(project, fileBean);
		
		if(!result) {
			dbConnection = dbHandler.getConnection();
			
			try {
				pst = dbConnection.prepareStatement(sqlString);
				pst.setString(1, fileBean.getFilePath());
				pst.setString(2, fileBean.getFileName());
				pst.setString(3, project);
				
				pst.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return true;
		}
		else return false;
	}
	
	//modificata per cod smell
	public boolean fileAlreadyExists(String project, FileBean bean) throws FileAlreadyExistsException {
		boolean result = false;
		String sqlString = "SELECT path, name FROM files WHERE project = ? AND name = ?";
		
		dbConnection = dbHandler.getConnection();
		
		try {
			pst = dbConnection.prepareStatement(sqlString);
			pst.setString(1, project);
			pst.setString(2, bean.getFileName());
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.first()) {
				throw new FileAlreadyExistsException("File already exist in project:"+project);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}

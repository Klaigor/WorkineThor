package logic.database;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.cj.protocol.Resultset;

import java.util.logging.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import logic.model.Project;
import logic.model.Session;
import logic.model.User;

public class ProjectDAO {
	
	private DBhandle handle = DBhandle.getDBhandleInstance();
	private Connection dbConnection;
	private PreparedStatement statement;
	
	/**
	 * method that adds the created project by the active user(found in Session)
	 * @param project
	 * @param session
	 */
	public boolean addProjectToDB(Project project, Session session) {
		Logger logger = Logger.getLogger(ProjectDAO.class.getName());
		String sqlString = "INSERT INTO projects(project_name, drive_name, user)" + "VALUES (?,?,?)";
		
		dbConnection = handle.getConnection();
		
		try {
			statement = dbConnection.prepareStatement(sqlString);
			statement.setString(1, project.getProjectName());
			statement.setString(2, project.getDriveName());
			statement.setString(3, session.getLoggedUser().getUsername());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL query failed!!");
			return false;
		}
		
		logger.log(Level.INFO, "project:" + project.getProjectName() + " added");
		return true;
	}
	
	public boolean removeProjectFromDB(Project project) {
		Logger logger = Logger.getLogger(ProjectDAO.class.getName());
		String sqlString =  "DELETE FROM projects WHERE " + "project_name = ?";
		
		dbConnection = handle.getConnection();
		
		try {
			statement = dbConnection.prepareStatement(sqlString);
			statement.setString(1, project.getProjectName());
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQL query failed!!");
			return false;
		}
		
		logger.log(Level.INFO, "project:" + project.getProjectName() + " removed");
		return true;
	}
	
	public Project getProjectFromDB(User user) {
		Project tempProject = new Project();
		String sqlString = "SELECT * FROM projects WHERE " + "user = ?";
		ResultSet resultSet;
		
		dbConnection = handle.getConnection();
		
		try {
			statement = dbConnection.prepareStatement(sqlString);
			statement.setString(1, user.getUsername());
			
			resultSet = statement.executeQuery(sqlString);
			
			if(!resultSet.first()) {
				tempProject.setProjectName("");
				tempProject.setDriveName("");
				tempProject.setDriveActive(false);
			}
			else {
				tempProject.setProjectName(resultSet.getString("project_name"));
				tempProject.setDriveName(resultSet.getString("drive_name"));
				if(resultSet.getString("drive_name").isEmpty())
					tempProject.setDriveActive(false);
				else tempProject.setDriveActive(true);
			}
		} catch (SQLException e) {}
		return tempProject;
	}
}

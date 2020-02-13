package database;

import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Project;
import model.Session;

public class ProjectDAO {
	
	private DBhandle handle = DBhandle.getDBhandleInstance();
	private Connection dbConnection;
	private PreparedStatement statement;
	
	/**
	 * method that adds the created project by the active user(found in Session)
	 * @param project
	 * @param session
	 */
	public void addProjectToDB(Project project, Session session) {
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
		}
		
		logger.log(Level.INFO, "project:" + project.getProjectName() + " added");
	}
}

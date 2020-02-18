package logic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.bean.UserBean;
import logic.controller.WatchDutiesController;
import logic.model.Duty;
import logic.model.Project;

public class DutyDAO {


	private DBhandle dbHandler = DBhandle.getDBhandleInstance();
	private PreparedStatement pst;
	
	/**
	 * prepares the statement and then sends it to the DB
	 * 
	 * @throws SQLException
	 */
	public boolean insertDuty(Duty duty) throws SQLException {
		String insert = "INSERT INTO duties " + "VALUES (?,?,?,?,?)";

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(insert);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		pst.setString(1, duty.getName());
		pst.setString(2, duty.getProject());
		pst.setString(1, duty.getAssignedUsers().get(0).getUsername());
		pst.setString(2, "");
		pst.setString(2, "");

		pst.executeUpdate();
		return true;
	}
	
	/**
	 * function called by {@link WatchDutiesController}
	 * retrieves the Duties linked to a Project And gives them back
	 * LA FUNZIONE è FATTA MALE, NEL DB POSSONO ESSERE MESSI 
	 * DUTIES CON STESSO NOME, S EAPPARTENGONO A DB DIFFERENTI, MA QUESTO NON DEVE ESSERE
	 * FATTO ALTRIMENTI LA FUNZIONE NON VA
	 * @param project
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Duty> getDuty(Project project) throws SQLException {
		
		String getDuties = "SELECT DISTINCT name FROM duties WHERE project=?";

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(getDuties);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		pst.setString(1, project.getProjectName());
		ResultSet rs = pst.executeQuery();

		if (!rs.first()) { // rs empty no no duty for the project
			ArrayList<Duty> arrayOut = new ArrayList<Duty>();
			return arrayOut;
		} else {
			int size =0;
			if (rs != null) 
			{
			  rs.last();    // moves cursor to the last row
			  size = rs.getRow(); // get row id 
			}
			ArrayList<Duty> arrayOut = new ArrayList<Duty>();
			rs.first();
			for (int i = 0; i < size; i++) {
				Duty duty = new Duty();
				duty.setName(rs.getString("name"));
				//duty.setProject(rs.getString("project"));
				arrayOut.add(duty);				
			}
			return arrayOut;	
		}
		
	}

	public ObservableList<String> getAllUsers() throws SQLException { 
		String getAllUsers = "SELECT * from users";
		ObservableList<String> allUsers = FXCollections.observableArrayList();

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(getAllUsers);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = pst.executeQuery();

		if (!rs.first()) { // rs empty no user with the correct username

		} else {
			allUsers.addAll(rs.getString("username"));
			while (rs.next())
				allUsers.addAll(rs.getString("username"));
		}
		return allUsers;
	}
}

package logic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	 * LA FUNZIONE E' FATTA MALE, NEL DB POSSONO ESSERE MESSI 
	 * DUTIES CON STESSO NOME, S EAPPARTENGONO A DB DIFFERENTI, MA QUESTO NON DEVE ESSERE
	 * FATTO ALTRIMENTI LA FUNZIONE NON VA
	 * @param project
	 * @return
	 * @throws SQLException
	 */
	public List<Duty> getDuty(Project project) throws SQLException {
		
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
			return new ArrayList<>();

		} else {
			int size =0;
			if (rs.last()) 
			{
			  rs.last();    // moves cursor to the last row
			  size = rs.getRow(); // get row id 
			}
			ArrayList<Duty> arrayOut = new ArrayList<>();
			rs.first();
			for (int i = 0; i < size; i++) {
				Duty duty = new Duty();
				duty.setName(rs.getString("name"));
				rs.next();
				//dovrei settare il progetto a cui appartiene il duty
				arrayOut.add(duty);				
			}
			return arrayOut;	
		}
		
	}
}

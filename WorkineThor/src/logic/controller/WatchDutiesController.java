package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import logic.database.DutyDAO;
import logic.database.ProjectDAO;
import logic.model.Duty;
import logic.model.Session;

public class WatchDutiesController {
	
	Session session = Session.getSession();
	
	public ArrayList<Duty> getProjectDuties(){
		DutyDAO dutyDao = new DutyDAO();
		ArrayList<Duty> arrayDuty = new ArrayList<Duty>();
		try {
			arrayDuty = dutyDao.getDuty(session.getCurrentProject());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayDuty;		
	}
}

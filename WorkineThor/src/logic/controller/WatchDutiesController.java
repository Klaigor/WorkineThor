package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.database.DutyDAO;
import logic.model.Duty;
import logic.model.Session;

public class WatchDutiesController {
	
	Session session = Session.getSession();
	
	public List<Duty> getProjectDuties(){
		DutyDAO dutyDao = new DutyDAO();
		ArrayList<Duty> arrayDuty = new ArrayList<>();
		try {
			arrayDuty = (ArrayList<Duty>) dutyDao.getDuty(session.getCurrentProject());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayDuty;		
	}
}

package logic.controller;

import javafx.collections.ObservableList;
import logic.bean.ProjectBean;
import logic.database.ProjectDAO;

public class AddMemberController {

	private static AddMemberController instance;
	
	private AddMemberController() {}
	
	public static AddMemberController getInstace() {
		if(instance == null)
			instance = new AddMemberController();
		return instance;
	}
	
	public ObservableList<String> showMemberToAddToProject(ProjectBean bean) {
		ProjectDAO projectDAO = new ProjectDAO();
		ObservableList<String> result = null;
		result = projectDAO.getMembersToAdd(bean);
		return result;
	}
}

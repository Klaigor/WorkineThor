package test.controller;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import logic.bean.FileBean;
import logic.database.FileDAO;
import logic.exceptions.FileAlreadyExistsException;
import logic.model.User;

public class StefanoTestClass {

	@Test
	public void userGetUsernameTest() {
		User user = new User();
		assertEquals("", user.getUsername());
	}
	
	@Test
	public void userSetUsernameTest() {
		User user = new User();
		
		user.setUsername("ciccio");
		assertEquals("ciccio", user.getUsername());
	}
	
	@Test
	public void fileDAOAddFileToProject() {
		FileDAO fileDAO = new FileDAO();
		FileBean fileBean = new FileBean();
		
		fileBean.setFileName("add-file.jsp");
		fileBean.setFilePath("C:\\fakepath\\add-file.jsp");
		
		boolean result;
		try {
			result = fileDAO.addFileToProject(fileBean, "prova4");
			result = true;
		} catch (FileAlreadyExistsException e) {
			
		} finally {
			result = false;
		}
		
		assertEquals(false, result);
	}
}

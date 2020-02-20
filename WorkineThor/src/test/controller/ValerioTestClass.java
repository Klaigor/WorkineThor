package test.controller;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import logic.model.Project;

public class ValerioTestClass {

	@Test
	public void testProjectName() {
		Project project = new Project();
		project.setProjectName("prova");
		assertEquals("prova", project.getProjectName());		
	}
	
	@Test
	public void testDriveName() {
		Project project = new Project();
		project.setDriveName("Mega");
		assertEquals("Mega", project.getDriveName());
	}
	
	@Test
	public void testDriveActive() {
		Project project = new Project();
		project.setDriveActive(false);
		assertEquals(false , project.getDriveActive());
	}
}

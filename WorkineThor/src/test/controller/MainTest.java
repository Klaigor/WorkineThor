package test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import javafx.stage.Stage;
import logic.workinethor.Main;

public class MainTest {
	
	@Test
	public void testStart() throws IOException {
		Main main = new Main();
		Stage stage = new Stage();
		main.start(stage);
		
		boolean success = main.hasStarted();
		
		assertEquals(true, success);
	}
	
	@Test
	public void testStartMainLayout() throws IOException {
		boolean success =  Main.startMainLayout();
		
		assertTrue(success);
	}
}

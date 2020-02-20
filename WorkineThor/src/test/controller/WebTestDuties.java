package test.controller;

import org.junit.*;

import test.controller.SeleniumTestRiccardo;

import static org.junit.Assert.*;

import java.sql.SQLException;

public class WebTestDuties {

	 
	    /**
	     * Sets up the test fixture. 
	     * (Called before every test case method.)
	     * @throws SQLException 
	     */
	    @Before
	    public void setUp() {
	    }
	 
	    /**
	     * Tears down the test fixture. 
	     * (Called after every test case method.)
	     * @throws SQLException 
	     */
	    @After
	    public void tearDown(){
	    	
	    }
	    
	    @Test
	    public void testDutiesWeb() throws SQLException {
	
	        assertEquals("prova Duties", SeleniumTestRiccardo.dutiesTestSelenium()); 
	    }
	    
}


package test.controller;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WebBrowseFile {
	
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

        assertEquals("Project: prova", SeleniumTestValerio.browseFile());
    }
    

}

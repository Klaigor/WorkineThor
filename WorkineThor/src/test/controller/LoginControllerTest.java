package test.controller;

import org.junit.*;

import logic.bean.UserBean;
import logic.controller.LoginController;
import logic.database.UserDAO;
import logic.exceptions.UserAlreadyExistException;
import logic.exceptions.WrongLoginException;

import static org.junit.Assert.*;

import java.sql.SQLException;

public class LoginControllerTest {

	 
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
	    public void testSignup() throws SQLException, UserAlreadyExistException {
	    	UserBean user = new UserBean();
	    	LoginController controller = new LoginController();
	    	user.setPassword("test");
	    	user.setUsername("test"); 	
	        assertEquals(true, controller.signup(user)); //sarebber buona cosa mettere nella funzione teardown ua query che elimina ogni volta questo utente dal db
	    	UserDAO dao = new UserDAO();
	    	dao.deleteUser(user);
	    }
	    
	    @Test
	    public void testSignin() throws SQLException, WrongLoginException {
	    	UserBean user = new UserBean();
	    	LoginController controller = new LoginController();
	    	user.setPassword("cane");
	    	user.setUsername("culo"); 	
	        assertEquals(true, controller.signin(user));
	    	
	    }

	    
	    
	    /**
	    @Test(expected=IndexOutOfBoundsException.class)
	    public void testForException() {
	        Object o = emptyList.get(0);
	    }
	    */
}


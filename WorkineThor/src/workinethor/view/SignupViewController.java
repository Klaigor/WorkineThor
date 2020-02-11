package workinethor.view;

import database.DBhandle;
import workinethor.view.LoginViewController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;	

public class SignupViewController {
	
	private Connection dbConnection;
	private DBhandle dbHandler = DBhandle.getDBhandleInstance(); 
	private PreparedStatement pst;
}

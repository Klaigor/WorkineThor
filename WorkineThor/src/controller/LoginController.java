package controller;

import database.DBhandle;
import workinethor.view.LoginViewController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginController {

	private Connection dbConnection;
	private DBhandle dbHandler = DBhandle.getDBhandleInstance(); 
	private PreparedStatement pst;
	
	
	public void signup() {
		String insert = "INSERT INTO users(username,password)"+"VALUES (?,?)";
		
		dbConnection = dbHandler.getConnection();
		
		try {
			
			pst = dbConnection.prepareStatement(insert);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/*	
		pst.setString(1, );
		pst.setString(2, );
	*/	
		
	}
}

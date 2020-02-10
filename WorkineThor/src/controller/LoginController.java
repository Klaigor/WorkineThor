package controller;

import database.DBhandle;
import workinethor.view.LoginViewController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginController {

	private Connection dbConnection;
	private DBhandle dbHandler = new DBhandle(); 
	private PreparedStatement pst;
	
	
	public void signup() {
		String insert = "INSERT INTO users(username,password)"+"VALUES (?,?)";
		
		dbConnection = dbHandler.getConnection();
	}
}

package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBhandle {
	
	private String dbHost = "localhost";
	private String dbPort = "3306";
	private String dbUser = "root";
	private String dbPasswd = "password";
	private String dbName = "workinethor";
	private Connection dbConnection;
	
	
	public Connection  getConnection() {
		String connectionString = "jdbc:mysql://" + this.dbHost+ ":" + this.dbPort+"/"+ this.dbName; ;
		
		try {
			
			dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPasswd);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dbConnection;
		
	}
	 
/**
	public static void main (String args[]) throws ClassNotFoundException, SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/workinethor", "root", "password");
		System.out.println("dio");
	}**/
}

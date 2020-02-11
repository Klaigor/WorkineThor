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
	private static DBhandle dbHandleInstance = null;
	
	
	/**
	 * class constructor made private as stated 
	 * by the Singleton pattern
	 * @return
	 */
	private DBhandle() {};
	
	
	/**
	 * Method that let the other objects acces the Singleton 
	 * instance DBhandle
	 * @return
	 */
	public static DBhandle getDBhandleInstance() {
		if (dbHandleInstance == null) 
            dbHandleInstance = new DBhandle(); 
  
        return dbHandleInstance;
	};
	
	
	/**
	 * Method that Returns the connection to the DB
	 * referred by DBhandle
	 * @return
	 */
	public Connection  getConnection() {
		String connectionString = "jdbc:mysql://" + this.dbHost+ ":" + this.dbPort+"/"+ this.dbName;
		
		try {
			
			dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPasswd);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dbConnection;
		
	}
}

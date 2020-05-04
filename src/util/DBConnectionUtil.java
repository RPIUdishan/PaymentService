package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author Ishanka
 *get the DB connection here
 *
 */

public class DBConnectionUtil {
	
	/**
	 * 
	 * @return the Connection of database
	 */
	public Connection connect() {
		
		Connection conn = null;
		
		try {
			

			 Class.forName("com.mysql.jdbc.Driver"); 
			 
			 conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payments", "root", ""); //path for the database
			
			 
			 
			 if(conn == null) { System.out.println("Error with connection of database"); } //will be printed this in the console when db connection is fail
			 
			 else{ System.out.println("Successful connection of database"); } //will be printed this in the console when db connection is success

		} catch (Exception e) {
			
			System.out.println("Connection Lost...");
			
			e.printStackTrace();
		}
		
		return conn;
	}	
}

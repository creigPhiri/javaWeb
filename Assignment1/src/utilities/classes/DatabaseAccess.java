package utilities.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAccess {
	private static String username = "admin@domain.ca";;
	private static String password = "P@ssword";
	private static String database = "COMP3095";

	  private static Connection connect = null;
	  
	  public static Connection connectDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/"+ database +"?"
		              + "user="+ username +"&password="+ password );
	      return connect;
	    } catch (Exception e) {
	      throw e;
	    } 
	  }
}


//try {
//	Class.forName("com.mysql.jdbc.Driver");
// // loads driver
//	
//Connection c = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "super-secret-password"); // gets a new connection
//
//PreparedStatement ps = c.prepareStatement("select uname,upass from user_table where uname=? and upass=?");
//ps.setString(1, );
//ps.setString(2, pw);
//
//ResultSet rs = ps.executeQuery();
//
//while (rs.next()) {
//	response.sendRedirect("welcome.jsp");
//	return;
//}
//out.println("password incorrect");
//
//
//} 
//catch (SQLException e){
//	out.println("SQL exception not found");
//}
//catch (ClassNotFoundException e) {
//	// TODO Auto-generated catch block
//	out.println("Class not found");
//	e.printStackTrace();
//}
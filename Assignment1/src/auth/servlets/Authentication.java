package auth.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/Authentication")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public PrintWriter pw ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//handles the routing for the pages
		
		response.setContentType("text/html");
		pw = response.getWriter();
		String authType = request.getParameter("authType");
		
			if(authType.equals("login"))
			  {
				pw.println(loginRoute(request));
			  }
				else if(authType.equals("registration")) 
				{
					pw.println(registrationRoute(request));
				}
	
	}
	
	
	//connection variables--------------------------------------
	private static String username = "admin@domain.ca";;
	private static String password = "P@ssword1";
	private static String database = "COMP3095";
	private static Connection connect = null;
	  
	  public static Connection connectDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = (Connection) DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/"+ database +"?"
		              + "user="+ username +"&password="+ password );
	      System.out.println("_____db connected____!!!!");
	      return connect;
	    } catch (Exception e) {
	    	System.out.println("____connection failed____");
	      throw e;
	    } 
	  }
	
	
	//Routes------------------------------------------------------
	
		//method handles the request from the login page
		//parameter: request object
		//return :
			public String loginRoute(HttpServletRequest request) {
				//validate data
				try {
					Connection con =connectDataBase();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//create session
				HttpSession session =request.getSession();
				
				//set session
				session.setAttribute("username", "Malinga");
				return "sent from login page";
			}
			
		
		//method handles requests from the registration page
		// params: request object
		//return:
			public String registrationRoute(HttpServletRequest request) {
				String password = request.getParameter("pw");
				String pwConfirm =request.getParameter("pwConfirm");
				String val ="";
				if(password.equals(pwConfirm)) {
					val+= "<h1 style='color:green'>Passwords match</h1>";  //used for testing purposes simple check for password verification
				}
				else {
					val +="<h1 style='color:red'>Passwords do not match..!!!</h1>";
				}
				return val+= "\nsent from registration page";
			}
}

package auth.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.classes.*;

import com.mysql.jdbc.Connection;


@WebServlet("/Authentication")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public PrintWriter pw ;
       
  
    public Authentication() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//handles the routing for the pages
		
		response.setContentType("text/html");
		pw = response.getWriter();
		String authType = request.getParameter("authType");
		
			if(authType.equals("login"))
			  {
				loginRoute(request, response);
			  }
				else if(authType.equals("registration")) 
				{
					registrationRoute(request, response);
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
			public void loginRoute(HttpServletRequest request, HttpServletResponse response) {
				//validate data
				try {
					Connection con =connectDataBase();
					PreparedStatement ps = con.prepareStatement("select email,passwords from users_table where email=? and passwords=?");
					ps.setString(1, request.getParameter("email"));
					ps.setString(2, request.getParameter("pw"));
					
					ResultSet rs = ps.executeQuery();
					System.out.println(rs.toString());
					
					if (rs.next()) {
						//create session
						HttpSession session =request.getSession();
						
						//set session
						session.setAttribute("username", request.getParameter("email"));
						
						response.sendRedirect(request.getContextPath()+"/Dashboard.jsp");
						return;
					}
					else {
						response.sendRedirect(request.getContextPath()+"/");
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					
				}

			}
			
		
		//method handles requests from the registration page
		// params: request object
		//return:
			public void registrationRoute(HttpServletRequest request, HttpServletResponse response) {
			Person person = new Person();
			person.firstname =request.getParameter("fname");
			person.lastname =request.getParameter("lname");
			person.email =request.getParameter("email");
			person.password =request.getParameter("pw");
				
				if(helperFunctions.passwordValidation(person.password,request.getParameter("pwConfirm")) ) {
					if(person.firstname == null || person.lastname == null) {
						
						try {
							Connection con = connectDataBase();
							PreparedStatement ps = con.prepareStatement("insert in users_table (firstname, lastname,email,passwords,role) values(firstname=?, lastname=?,email=?, passwords =? ,role = ?");
							ps.setString(1, person.firstname);
							ps.setString(2, person.lastname);
							ps.setString(3, person.email);
							ps.setString(4, person.password);
							ps.setString(5, person.role);
							ResultSet rs = ps.executeQuery();
							System.out.println("record inserted");
							
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
				else {
					try {
						response.sendRedirect(request.getContextPath()+"/registration.html");
					}catch(Exception e) {
						e.printStackTrace();
					}
					
				}

			}
}

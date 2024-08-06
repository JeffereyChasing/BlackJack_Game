package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//when I first tried to import the javax.servlet packages, the system kept telling me the packages can not be found
//it took me a long time to figure out the solution for my system: I added the Server Runtime libraries to the ClassPath,
//and instead changing the Apache Tomcat version down 9.0.86 to fix this issue
@WebServlet("/create")
//handle request from /create
public class Create_Servlet extends HttpServlet {
	
	String statement= "";
	//create variable for alter sql statement
	
	 @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 response.setContentType("text/jsp");
		 //set content type
		 
		 String username = request.getParameter("username");
		 String password = request.getParameter("passcode");
		 //get username and passcode
		 System.out.println(username);
		 System.out.println(password);
		 //print username and passcode for debugging
		 
		 
		 //Database part
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//load the driver
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	     Connection con = null;
	     String url = "jdbc:mysql://localhost:3306/blackjack";
	     String user = "root";
	     String pass = "2001518Wu";
	     // initialize connection and my database parameters
	     statement = "INSERT INTO player (first_name, last_name) VALUES (?,?)";
	     // since we want to create account, we add the user inputted username and password to the database
	     
	     try {
			con = DriverManager.getConnection(url,user,pass);
			//we first try to connect our mysql workbench
			PreparedStatement statement_good = con.prepareStatement(statement);{
				//set proper parameterized sql statement
				statement_good.setString(1,username);
				statement_good.setString(2,password);
				//since we using parameterized statement, we use setString to replace the question mark with actual 
				//user inputted value
				if(statement_good.executeUpdate()!=0) {
					//based on different results, we get feedback and debug
					//!=0 means that we got a new update (inserted successfully)
					try {
						TimeUnit.SECONDS.sleep(1);
						//wait for 1 second

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					response.sendRedirect("index.jsp");
					//navigate to the login-in jsp

				}else {
					 response.sendRedirect("wrongAccount.jsp");
					 //otherwise, we notify the user by sending them to the error-handling page
				}
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("wrongAccount.jsp");

			e.printStackTrace();
		}



	    // TODO Auto-generated method stub
	     
	}
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        //call the do-get method
    }
}

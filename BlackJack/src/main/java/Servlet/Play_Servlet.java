package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet("/login")
//serve login request
public class Play_Servlet extends HttpServlet {
	
	String statement= "";
	//create variable for sql statement
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 response.setContentType("text/jsp");
		 //set content type
		 
		 String username = request.getParameter("username");
		 String password = request.getParameter("passcode");
		 //get username and passcode
		 
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
	     statement = "SELECT EXISTS(SELECT 1 FROM player WHERE first_name = ? AND last_name = ?) AS record_exists;";
	     //sql statement to check whether there is such combination
	     try {
	    	    con = DriverManager.getConnection(url, user, pass);
	    	    PreparedStatement statement_good = con.prepareStatement(statement);
	    	    //connect and prepare statment
	    	    
	    	   	statement_good.setString(1, username);
	    	    statement_good.setString(2, password);
	    	    // Set the values for the prepared statement

	    	    ResultSet resultSet = statement_good.executeQuery();
	    	    // Execute the query and obtain a single ResultSet

	    	    if (resultSet.next()) {
		    	    //check if the result set contains valid row
	    	    	boolean recordExists = resultSet.getBoolean("record_exists");
	    	    	//get boolean result of whether record exists
	    	    	
	  	    	  	if(recordExists) {
		  	    	    System.out.println("Record exists: " + recordExists);
		  	    	     // Redirect and confirm that the record exists
		  	    	  try {
						TimeUnit.SECONDS.sleep(1);
						//wait for 1 seconds
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		    	        response.sendRedirect("playgame.jsp");
		    	        //Redirect to playgame.jsp
	  	    	  	}else {
		    	        response.sendRedirect("wrongcombo.jsp");
		    	        //if there is no such combo, navigate to error handling page

	  	    	  	}
	    	    } else {
	    	        System.out.println("Sorry, no matching record in the system.");
	    	    }

	    	 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  

	        
	        
	        
	    // TODO Auto-generated method stub
	}
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}

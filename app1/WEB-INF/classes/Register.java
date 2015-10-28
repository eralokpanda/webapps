package com.ap.app;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


	

public class Register extends HttpServlet {
	
	static String DB_DRIVER;
	static String DB_URL;
	static String DB_USER;
	static String DB_PASS;
	
	String fname = null;
	String lname = null;
	String email = null;
	String pass1 = null;
	String pass2 = null;
	
	Connection con = null;
	Statement st = null;
	
	@Override
	public void init(ServletConfig config) {	
		
			
		ServletContext globalData = config.getServletContext();
		
		DB_DRIVER = globalData.getInitParameter("dbDriver");
		DB_URL = globalData.getInitParameter("dbUrl");
		DB_USER = globalData.getInitParameter("dbUser"); 
		DB_PASS = globalData.getInitParameter("dbPass");
		
		try {
		Class.forName(DB_DRIVER); //loading database driver class
		} catch(ClassNotFoundException e) {
			System.err.println(e);
		}
		
			
	}
		
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		fname = request.getParameter("fname").trim();
		lname = request.getParameter("lname").trim();  
		email = request.getParameter("email").trim();
		pass1 = request.getParameter("pass1").trim();
		pass2 = request.getParameter("pass2").trim();
		
		response.setContentType("text/html");
		int flag = 0;//To check whether the data successfully enter into database or not
		if ((!pass1.equals(pass2)) | (fname.equals("")) | (lname.equals("")) | (email.equals("")) | (pass1.equals("")) ) {
			
			PrintWriter pw = response.getWriter();
			
			pw.print("<html><center>Failure!! Form not filled correctly </center></html>");
			request.getRequestDispatcher("register.html").include(request, response);
		} else {
			
			try {
				con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);// getting database connection object
				st = con.createStatement(); //getting statement object from connection object
				flag = st.executeUpdate("insert into user values(1,'"+fname+"','"+lname+"','"+email+"','"+pass1+"')");
			} catch (SQLException e) {
			System.err.println(e);
			}	
				
			if(flag == (int)1) {
				request.getRequestDispatcher("registerSuccess.html").include(request, response);
			} else {
			request.getRequestDispatcher("registerFailure.html").include(request, response);
			}
		}
		
		
		try {
			if(st != null) {
				st.close();
			}
			if(con != null) {
				con.close();
			}
			
		} catch(Exception e) {
			System.err.println(e);
		}
		
		
	}
	
	@Override
	public void destroy() {}
		
}
	







package com.ap.app;

import java.io.IOException;

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
		
		String DB_DRIVER = globalData.getInitParameter("dbDriver");
		String DB_URL = globalData.getInitParameter("dbUrl");
		String DB_USER = globalData.getInitParameter("dbUser"); 
		String DB_PASS = globalData.getInitParameter("dbPass");
		
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
		
		if (pass1.equals(pass2)) {
			int flag = 0;//To check whether the data successfully enter into database or not
			try {
				con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);// getting database connection object
				st = con.createStatement(); //getting statement object from connection object
				flag = st.executeUpdate("insert into user values(1,'"+fname+"','"+lname+"','"+email+"','"+pass+"')");
			} catch (SQLException e) {
			System.err.println(e);
			}
		}
		response.setContentType("text/html");
		if(flag == (int)1) {
			request.getRequestDispatcher("registerSuccess.html").include(request, response);
		} else {
			request.getRequestDispatcher("registerfailure.html").include(request, response);
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
	







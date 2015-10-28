package com.ap.app;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
  	
	Connection con = null;
	PreparedStatement ps = null;
	
  	@Override
	public void init() {
		
		ServletContext globalData = getServletConfig().getServletContext();
	  
	  	String DB_DRIVER = globalData.getInitParameter("dbDriver");
		String DB_URL = globalData.getInitParameter("dbUrl");
		String DB_USER = globalData.getInitParameter("dbUser");
		String DB_PASS = globalData.getInitParameter("dbPass");
		
		try {
			Class.forName(DB_DRIVER);			
			con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
			ps = con.prepareStatement("SELECT * FROM user WHERE email=? AND pass=?;");
		} catch(ClassNotFoundException | SQLException e) {
		
			System.err.println(e);
			
		} catch(Exception e) {
			
			System.err.println(e);
			
		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("email").trim();
		String pass = request.getParameter("pass").trim();
		
		try {
			ps.setString(1, user);
			ps.setString(2, pass);		
			ResultSet rs = ps.executeQuery();				
			if((rs.next()) && (rs.getString("email").equals(pass))) {
		
				request.getRequestDispatcher("profile.html").forward(request, response);				
			}
			
		} catch (SQLException e) {
			
			System.err.println(e);		
		} catch (Exception e) {
			
			System.err.println(e);
		}
		
			
		
	}
	
	@Override
	public void destroy () {
		
		try {
			
			if (ps != null) {
				
				ps.close();
			}
			if (con != null) {
				
				con.close();
			}				
		} catch (Exception e) {
		
			System.err.println (e);
		}
	}
	
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
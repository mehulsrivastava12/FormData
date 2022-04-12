package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	private static final String INSERT_QUERY ="INSERT INTO user1 VALUES(?,?,?,?,?)";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
				res.setContentType("text/hmtl");
		String first_name = req.getParameter("first_name");
		String last_name = req.getParameter("last_name");
		String email_id = req.getParameter("email_id");
		String mobile_no = req.getParameter("mobile_no");
		String gender = req.getParameter("gender");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo","root","Mehul@1234");
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
			ps.setString(1, first_name);
			ps.setString(2, last_name);
			ps.setString(3, email_id);
			ps.setString(4, mobile_no);
			ps.setString(5, gender);
			
			int count = ps.executeUpdate();
			
			if(count==0) {
				pw.println("Record not stored into database");
			}else {
				pw.println("Record Stored into Database");
			}
		}catch(SQLException se) {
			pw.println(se.getMessage());
			se.printStackTrace();
		}catch(Exception e) {
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		
		//close the stream
		pw.close();
	}
	
}


package org.jspiders.jdbcApp;
import java.sql.*;

public class UpdateSingle {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		
		String query = "UPDATE btm.student SET name='Rohan' WHERE id=1";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded & Registered.");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Connection Established with Database Server.");
			
			stmt=con.createStatement();
			System.out.println("Platform Created.");
			
			stmt.executeUpdate(query);
			System.out.println("Data Updated.");
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(stmt!=null) {
				try {
					stmt.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Closed all Costly Resource.");
		}
	}
}

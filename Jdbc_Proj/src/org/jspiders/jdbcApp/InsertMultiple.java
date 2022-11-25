package org.jspiders.jdbcApp;
import java.sql.*;

public class InsertMultiple {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;

		String qry1 = "INSERT INTO btm.student VALUES(1, 'Bhupendra', 98.87)";
		String qry2 = "INSERT INTO btm.student VALUES(3, 'Hemant', 87.69)";
		String qry3 = "INSERT INTO btm.student VALUES(4, 'Jaiprakash', 88.87)";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded & Registered.");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Connection Established with Database Server.");

			stmt = con.createStatement();
			System.out.println("Platform Created.");

			stmt.executeUpdate(qry1);
			stmt.executeUpdate(qry2);
			stmt.executeUpdate(qry3);
			System.out.println("Data Inserted.");
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
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

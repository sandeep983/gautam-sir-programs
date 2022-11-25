package org.jspiders.jdbcApp.PreparedStatement;
import java.sql.*;
import java.util.Scanner;

public class FetchById {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the id to fetch: ");
		int id = sc.nextInt();
		sc.close();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT * FROM btm.student where id=?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded & Registered.");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Connection Established with Database Server.");

			pstmt = con.prepareStatement(query);
			System.out.println("Platform Created.");

			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString(2);
				double perc = rs.getDouble(3);
				System.out.println(name + " " + perc);
			} 
			else {
				System.err.println("No data found for id: " + id);
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (rs != null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
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

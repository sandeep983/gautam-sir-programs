package org.jspiders.jdbcApp.PreparedStatement;
import java.sql.*;

public class InsertMultiple {
    public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO btm.student VALUES(?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded & Registered.");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Connection Established with Database Server.");
			
			pstmt=con.prepareStatement(query);
			System.out.println("Platform Created.");
			
			//Setting the values to the ? of query
			pstmt.setInt(1, 5);
			pstmt.setString(2, "Rahul");
			pstmt.setDouble(3, 99.99);
			pstmt.executeUpdate();

			pstmt.setInt(1, 6);
			pstmt.setString(2, "Rohan");
			pstmt.setDouble(3, 99.91);
			pstmt.executeUpdate();
			System.out.println("Data Inserted.");
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
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
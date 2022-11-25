package org.jspiders.jdbcApp.PreparedStatement;
import java.sql.*;

public class FetchAll {
    public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		String query = "SELECT * FROM btm.student";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded & Registered.");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Connection Established with Database Server.");
			
			pstmt=con.prepareStatement(query);
			System.out.println("Platform Created.");

            rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double perc = rs.getDouble("perc");
				System.out.println(id+" "+name+" "+perc);
			}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
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


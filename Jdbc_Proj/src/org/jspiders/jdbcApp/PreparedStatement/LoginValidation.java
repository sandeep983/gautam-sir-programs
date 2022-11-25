package org.jspiders.jdbcApp.PreparedStatement;
import java.sql.*;
import java.util.Scanner;

public class LoginValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name: ");
        String name = sc.next();
        System.out.print("Enter the password: ");
        String password = sc.next();
        sc.close();

		Connection con = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;
		
		String query = "SELECT fullname FROM btm.user where name=? and password=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded & Registered.");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Connection Established with Database Server.");
			
			pstmt=con.prepareStatement(query);
			System.out.println("Platform Created.");
                
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

			if(rs.next()) {
				String username = rs.getString(1);
                System.out.println("Welcome "+username);

			}
            else {
                System.err.println("Invalid Name or Password");
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


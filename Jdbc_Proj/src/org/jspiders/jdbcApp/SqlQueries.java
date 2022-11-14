package org.jspiders.jdbcApp;
import java.sql.*;
import java.util.Scanner;
public class SqlQueries {
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded & Registered.");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=admin");
			System.out.println("Connection Established with Database Server.");
			
			stmt=con.createStatement();
			System.out.println("Platform Created.");
			
			while(true) {
				//Printing menu
				System.out.println("\n--------------------");
				System.out.println("What you want to do?");
				System.out.println("1. INSERT");
				System.out.println("2. UPDATE");
				System.out.println("3. DELETE");
				System.out.println("4. EXIT");
				System.out.println("--------------------");
				System.out.print("\nEnter your choice: ");
				
				int n = sc.nextInt();
				sc.nextLine();
				if(n==4) {
					System.out.println("Exit Successfully.");
					break;
				}
				else {
					switch(n) {
						case 1:
							System.out.println("\nEnter the value inside parenthesis,\nFor example: (1, 'Sandeep', 65.45)");
							System.out.print("\nEnter the value: ");
							String val = sc.nextLine();
							String queryFormat = "Insert into btm.student values";
							stmt.executeUpdate(queryFormat.concat(val));
							System.out.println("Data Inserted.");
							break;
						case 2:
							System.out.println("Not yet implemented.");
							break;
						case 3:
							System.out.println("Not yet implemented.");
							break;
						default: 
							System.out.println("Invalid Choice, Please choose the correct option.");
							break;
					}
				}
			}
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
			System.out.println("\nClosed all Costly Resource.");
		}
	}
}

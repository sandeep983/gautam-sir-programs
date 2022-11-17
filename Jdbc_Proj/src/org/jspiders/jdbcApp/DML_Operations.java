/*	
 *	Performing DML Operations on the Database using JDBC
 *	Using Statement Interface and executeUpdate() method
 * 			Database Name: btm, 
 * 			Table Name: student
 * 			Table Columns: id, name, perc
 */

package org.jspiders.jdbcApp;
import java.sql.*;
import java.util.Scanner;
public class DML_Operations {
	private static final Scanner sc = new Scanner(System.in);
	private static String query, format;
	
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
					System.out.println("\nExit Successfully.");
					break;
				}
				else {
					switch(n) {
						case 1:
							System.out.println("\nWrite the remaining part of the query / Or complete the query:");
							System.out.println("Example: INSERT INTO btm.student VALUES (1, 'Sandeep', 83.39)\n");
							
							System.out.print("INSERT INTO btm.student VALUES ");
							query = sc.nextLine();

							format = "INSERT INTO btm.student VALUES "+query;
							stmt.executeUpdate(format);
							System.out.println("\nData Inserted.");
							break;

						case 2:
							System.out.println("\nWrite the remaining part of the query / Or complete the query:");
							System.out.println("Example: Update btm.student SET name='Ram' where id=1\n");

							System.out.print("Update btm.student SET ");
							query = sc.nextLine();

							format = "Update btm.student SET "+query;
							stmt.executeUpdate(format);
							System.out.println("\nData Updated.");
							break;

						case 3:
							System.out.println("\nWrite the remaining part of the query / Or complete the query:");
							System.out.println("Example: DELETE FROM btm.student WHERE id=1\n");

							System.out.print("DELETE FROM btm.student WHERE ");
							query = sc.nextLine();

							format = "DELETE FROM btm.student WHERE "+query; 
							stmt.executeUpdate(format);
							System.out.println("\nData Deleted.");
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
			System.out.println("Closed all Costly Resource.");
		}
	}
}

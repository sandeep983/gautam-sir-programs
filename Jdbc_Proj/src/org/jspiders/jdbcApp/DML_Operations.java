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
	private static int flag=0;
	
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
						//Inserting data into the table
						case 1:
							System.out.println("\nWrite the remaining part of the query / Or complete the query:");
							System.out.println("Example: INSERT INTO btm.student VALUES (1, 'Sandeep', 83.39)\n");
							
							System.out.print("INSERT INTO btm.student VALUES ");
							query = sc.nextLine();

							format = "INSERT INTO btm.student VALUES "+query;
							
							try {
								stmt.executeUpdate(format);
							}
							catch (SQLIntegrityConstraintViolationException e) {
								System.out.println("\nError: ID already present in the database table.");
								System.out.println("ID is Primary Key in the table, so it can not be duplicate or null.");
								System.out.println(e);
								flag=1;
							}
							catch (SQLSyntaxErrorException e) {
								System.out.println("\nError: Check your syntax.");
								System.out.println(e);
								flag=1;
							}
							
							if(flag==0) {
								System.out.println("\nData Inserted Successfully.");
							}
							else {
								flag=0;
							}
							break;

						//Updating data into the table
						case 2:
							System.out.println("\nWrite the remaining part of the query / Or complete the query:");
							System.out.println("Example: Update btm.student SET name='Ram' where id=1\n");

							System.out.print("Update btm.student SET ");
							query = sc.nextLine();

							format = "Update btm.student SET "+query;

							try {
								stmt.executeUpdate(format);
							}
							catch (SQLSyntaxErrorException e) {
								System.out.println("\nError: Check your syntax.");
								System.out.println(e);
								flag=1;
							}
							
							if(flag==0) {
								System.out.println("\nData Updated Successfully.");
							}
							else {
								flag=0;
							}			
							break;

						//Deleting data from the table
						case 3:
							System.out.println("\nWrite the remaining part of the query / Or complete the query:");
							System.out.println("Example: DELETE FROM btm.student WHERE id=1\n");

							System.out.print("DELETE FROM btm.student WHERE ");
							query = sc.nextLine();

							format = "DELETE FROM btm.student WHERE "+query; 

							try {
								stmt.executeUpdate(format);
							}
							catch (SQLSyntaxErrorException e) {
								System.out.println("\nError: Check your syntax.");
								System.out.println(e);
								flag=1;
							}
							
							if(flag==0) {
								System.out.println("\nData Deleted Successfully.");
							}
							else {
								flag=0;
							}			
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

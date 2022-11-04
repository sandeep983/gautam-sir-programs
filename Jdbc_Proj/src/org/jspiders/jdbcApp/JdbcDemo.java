package org.jspiders.jdbcApp;
public class JdbcDemo {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver class Loaded & Registered");
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

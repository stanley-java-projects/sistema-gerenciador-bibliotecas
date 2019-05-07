package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.DatabaseConnection;

public class CustomerController {
	/**
	 * Store a Singleton instance.
	 */
	private static CustomerController instance;
	
	/**
	 * Singleton method.
	 */
	public static CustomerController getInstance() {
		if (instance == null)
			instance = new CustomerController();
		
		return instance;
	}
	
	/**
	 * Create a customer.
	 */
	public void createCustomer(String name) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Create table "customers" if it doesn't exist */
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS customers (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING)");
			
			/* Create customer */
			statement.executeUpdate("INSERT INTO customers(name) VALUES('" + name + "')");
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
}

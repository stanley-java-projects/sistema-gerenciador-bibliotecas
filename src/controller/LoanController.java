package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.Book;
import model.Customer;
import model.DatabaseConnection;

public class LoanController {
	/**
	 * Store a Singleton instance.
	 */
	private static LoanController instance;
	
	/**
	 * Singleton method.
	 */
	public static LoanController getInstance() {
		if (instance == null)
			instance = new LoanController();
		
		return instance;
	}
	
	/**
	 * Create a loan.
	 */
	public void createLoan(Book book, Customer customer) {
		Connection connection = DatabaseConnection.getInstance().getConnection();
		
		try {
			Statement statement = connection.createStatement();
			
			/* Create table "loans" if it doesn't exist */
			statement.executeUpdate(""
				+ "CREATE TABLE IF NOT EXISTS loans ("
				+ "	id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "	book_id INTEGER, "
				+ "	customer_id INTEGER, "
				+ "	FOREIGN KEY(book_id) REFERENCES books(id), "
				+ "	FOREIGN KEY(customer_id) REFERENCES customers(id)"
				+ ")"
			);
			
			/* Create loan */
			statement.executeUpdate(""
				+ "INSERT INTO loans(book_id, customer_id)"
				+ "VALUES(" + book.getId() + ", " + customer.getId() + ")"
			);
		} catch (SQLException ex) {
			System.err.println("Couldn't get statement: " + ex.getMessage());
		}
	}
}
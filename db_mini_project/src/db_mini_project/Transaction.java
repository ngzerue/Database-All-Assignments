package db_mini_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction {

	 private int transaction_id;
	 private String transaction_type;
	 private double amount;
	 private int account_no;
	public Transaction(int transaction_id, String transaction_type, double amount, int account_no) {
		super();
		this.transaction_id = transaction_id;
		this.transaction_type = transaction_type;
		this.amount = amount;
		this.account_no = account_no;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	@Override
	public String toString() {
		return "Transaction [transaction_id=" + transaction_id + ", transaction_type=" + transaction_type + ", amount="
				+ amount + ", account_no=" + account_no + "]";
	}
	public void transactionMethod() throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		int myRs = 0;
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "root");
			System.out.println("Database connection successful!\n");
			// 2. Create a statement
			
			double new_balance = 0;
			
			if (transaction_type.equals("deposit")) {
				new_balance = getBalance() + amount;
			} else {
				new_balance = getBalance() - amount;
			}
			String update = "update account set balance = (?) where account_no in (?)";
			myStmt = myConn.prepareStatement(update);
			// 3. update Account
			myStmt.setDouble(1, new_balance);
			myStmt.setLong(2, getAccount_no());
			myRs = myStmt.executeUpdate();
			System.out.println("Updated number of users " + myRs);
			
			// 4. Create a statement
			String insert = "insert into transaction values (?, ?, ?, ?)";
			myStmt = myConn.prepareStatement(insert);
			// 3. Insert Data
			myStmt.setLong(1, transaction_id);
			myStmt.setString(2, transaction_type);
			myStmt.setDouble(3, amount);
			myStmt.setDouble(4, getAccount_no());
			myRs = myStmt.executeUpdate();
			System.out.println("Updated number of users " + myRs);
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}
		}
	 
}
	private double getBalance() {
		// TODO Auto-generated method stub
		return 0;
	}
}

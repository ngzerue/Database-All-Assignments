package db_mini_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transfer {

	 private int transfer_id;
	 private int from_account;
	 private int to_account;
	 private double amount;
	 private String reason;
	public Transfer(int transfer_id, int from_account, int to_account, double amount, String reason) {
		super();
		this.transfer_id = transfer_id;
		this.from_account = from_account;
		this.to_account = to_account;
		this.amount = amount;
		this.reason = reason;
	}
	public int getTransfer_id() {
		return transfer_id;
	}
	public void setTransfer_id(int transfer_id) {
		this.transfer_id = transfer_id;
	}
	public int getFrom_account() {
		return from_account;
	}
	public void setFrom_account(int from_account) {
		this.from_account = from_account;
	}
	public int getTo_account() {
		return to_account;
	}
	public void setTo_account(int to_account) {
		this.to_account = to_account;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "Transfer [transfer_id=" + transfer_id + ", from_account=" + from_account + ", to_account=" + to_account
				+ ", amount=" + amount + ", reason=" + reason + "]";
	}
	public void transferMethod() throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		int myRs = 0;
		try {
			// 1. Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root", "root");
			System.out.println("Database connection successful!\n");
			// 2. Create a statement
			
			double new_balance = getBalance() - amount;
			
			String update = "update account set balance = (?) where account_no in (?)";
			myStmt = myConn.prepareStatement(update);
			// 3. update Account
			myStmt.setDouble(1, new_balance);
			myStmt.setLong(2, getAccount_no());
			myRs = myStmt.executeUpdate();
			
			// 2. Create a statement
			double new_balance2 = getTo_balance() + amount;
			
			String update2 = "update account set balance = (?) where account_no in (?)";
			myStmt = myConn.prepareStatement(update2);
			// 3. update Account
			myStmt.setDouble(1, new_balance2);
			myStmt.setLong(2, to_account);
			myRs = myStmt.executeUpdate();
			System.out.println("Updated number of users " + myRs);
			
			// 4. Create a statement
			String insert = "insert into transfer values (?, ?, ?, ?, ?)";
			myStmt = myConn.prepareStatement(insert);
			// 3. Insert Data
			myStmt.setLong(1, transfer_id);
			myStmt.setLong(2, getAccount_no());
			myStmt.setLong(3, to_account);
			myStmt.setDouble(4, amount);
			myStmt.setString(5, reason);
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
	private double getTo_balance() {
		// TODO Auto-generated method stub
		return 0;
	}
	private long getAccount_no() {
		// TODO Auto-generated method stub
		return 0;
	}
	private double getBalance() {
		// TODO Auto-generated method stub
		return 0;
	} 
}

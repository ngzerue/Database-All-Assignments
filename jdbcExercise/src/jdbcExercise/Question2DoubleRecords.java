package jdbcExercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Question2DoubleRecords {
    public static void main(String[] args) throws SQLException {
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	try {
	    myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "Nigsty", "july2011");
	    System.out.println("Database connection successful!\n");
	    
	    myStmt = myConn.createStatement();
	 
	    String sqlUpdate = "UPDATE candidates " + "SET balance = ? "
               + "WHERE account_no = ?";

    // PreparedStatement pstmtmy =myConn.prepareStatement(sqlUpdate);
	    myRs = myStmt.executeQuery("select sum(balance) as totalBalance from account");
	    double sum =0;
	    while (myRs.next()) {
		        sum =sum + 2*myRs.getDouble("totalBalance");}
	    System.out.println("Total after doubling the sum:  "+sum);
	}catch (Exception exc) {
	    exc.printStackTrace();
	} finally {
	    if (myRs != null) {
		myRs.close();
	    }
	    if (myStmt != null) {
		myStmt.close();
	    }
	    if (myConn != null) {
		myConn.close();
	    }
	}
    }
}

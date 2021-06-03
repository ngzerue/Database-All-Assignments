package jdbcExercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Question5PrintAccountDetail {
    public static void main(String[] args) throws SQLException {
    	
    Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	 
	 
	try {
	    // 1. Get a connection to database
	    myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "Nigsty", "july2011");
	    System.out.println("Database connection successful!\n");
	    
	    // 2. Create a statement
	    myStmt = myConn.createStatement();
	    // 3. Execute SQL query
	    myRs = myStmt.executeQuery("select first_name,last_name, MAX(balance),MIN(balance) from account");
	    // 4. Process the result set
	    while (myRs.next()) {
		
	    System.out.println("Highest Balance:  " +" "+ myRs.getString("first_name")+ " "+ myRs.getString("last_name")+ " " +myRs.getDouble("MAX(balance)"));
		System.out.println("Lowest  Balance: " + " "+ myRs.getString("first_name") +"  "+  myRs.getString("last_name")+ " " +myRs.getDouble("MIN(balance)"));}
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
    class Account {
        
        private String first_name;
        private String last_name;
        private double balance;
       

        public Account( String first_name, String last_name, double balance) {
            super();
            
            this.first_name =first_name ;
            this.last_name = last_name;
            this.balance = balance;
            
        }


    	public String getFirst_name() {
    		return first_name;
    	}


    	public void setFirst_name(String first_name) {
    		this.first_name = first_name;
    	}


    	public String getLast_name() {
    		return last_name;
    	}


    	public void setLast_name(String last_name) {
    		this.last_name = last_name;
    	}


    	public double getBalance() {
    		return balance;
    	}


    	public void setBalance(double balance) {
    		this.balance = balance;
    	}
    }
     }

package jdbcExercise;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Question1InsertRecords {

    public static void main(String[] args) {
        List < Account > list = new ArrayList < > ();
        list.add(new Account("Aster","Tekle",5000.0));
        list.add(new Account("Tesfay", "Hailu",3000.0 ));
        list.add(new Account( "Birhane", "Guesh",40000 ));
        list.add(new Account("Letay", "Mehari",7000.0 ));
        list.add(new Account( "Desta", "Gebremedhin",10000 ));
         
        String INSERT_ACCOUNT_SQL = "INSERT INTO Account" + " ( first_name, last_name, balance)"
        		+ " VALUES " + " (?, ?, ?);";
       // String selectSql = "select * from Account";

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/bank", "Nigsty", "july2011");
        	 
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL)) {
            connection.setAutoCommit(false);
            for (Iterator < Account > iterator = list.iterator(); iterator.hasNext();) {
            	Account user = (Account) iterator.next();
                preparedStatement.setString(1, user.getFirst_name());
                preparedStatement.setString(2, user.getLast_name());
                preparedStatement.setDouble(3, user.getBalance());
                
                preparedStatement.addBatch();
            }
            int[] updateCounts = preparedStatement.executeBatch();
            System.out.println(Arrays.toString(updateCounts));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (BatchUpdateException batchUpdateException) {
            printBatchUpdateException(batchUpdateException);
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public static void printBatchUpdateException(BatchUpdateException b) {

        System.err.println("----BatchUpdateException----");
        System.err.println("SQLState:  " + b.getSQLState());
        System.err.println("Message:  " + b.getMessage());
        System.err.println("Vendor:  " + b.getErrorCode());
        System.err.print("Update counts:  ");
        int[] updateCounts = b.getUpdateCounts();

        for (int i = 0; i < updateCounts.length; i++) {
            System.err.print(updateCounts[i] + "   ");
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
 
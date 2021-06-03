package db_mini_project;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		List < Account > list = new ArrayList < > ();
        list.add(new Account(2001, "John", "Doe", 2500.00));
        list.add(new Account(2002, "Tesfay", "Kiros", 1250.00));
        list.add(new Account( 2004, "Hadgu", "Kindeya", 5000.30));
		
		
		String insertSql = "INSERT INTO Account" + " ( account_no,first_name, last_name, balance)"  
		                  + " VALUES " + " (?,?, ?, ?);";

    	//String updateQuery = "update account set balance= ? +1000 where account_no=? ";
		  System.out.println("Connected");
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/banking_system_erd", "Mekelle", "july2011");
        	 
            // Step 2:Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            connection.setAutoCommit(false);
            for (Iterator < Account > iterator = list.iterator(); iterator.hasNext();) {
            	Account account = (Account) iterator.next();
            	preparedStatement.setInt(1, account.getAccount_no());
            	preparedStatement.setString(2, account.getFirst_name());            
            	preparedStatement.setString(3, account.getLast_name());
                preparedStatement.setDouble(4, account.getBalance());
               // account.calculateCredit(1000);
                //account.calculateDebit(500);
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



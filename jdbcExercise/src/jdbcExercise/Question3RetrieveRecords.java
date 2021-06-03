package jdbcExercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question3RetrieveRecords {
   static final String url = "jdbc:mysql://localhost:3306/bank" ;
   static final String user = "Nigsty";
   static final String password = "july2011";
   static final String QUERY = "SELECT * FROM Account";

   public static void main(String[] args) {
      // Open a connection
	   System.out.println("FirstName" + "  " + "LastName" +"  " +  "Balance" );
      try(Connection conn = DriverManager.getConnection(url, user, password);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);)
        
      {		      
         while(rs.next()){
            //Display values
        	 
            System.out.print(rs.getString("first_name")+ "     ");
            System.out.print( rs.getString("last_name")+ "      ");
            System.out.println(rs.getString("balance"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketModel {
	public static Connection conn = null;
	public static PreparedStatement ps = null;
	public static ResultSet rs = null;
	public static void connectDatabase(){
		try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		}catch(Exception e){}
	}
	public void query(String s){
		try {
			conn =
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/workout?user=root&password=");

			ps = conn.prepareStatement(s);
			rs = ps.executeQuery(s);
		}catch(SQLException ex){
			 System.out.println("SQLException: " + ex.getMessage());    
	    	 System.out.println("SQLState: " + ex.getSQLState());
	    	 System.out.println("VendorError: " + ex.getErrorCode());
		}
			
	}
	
	public void insert(String s){
		try {
	    	   conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/workout?user=root&password=");
	    	   ps = conn.prepareStatement(s);    
	    	   ps.execute(s);
	    	  	    	   
	    	} catch (SQLException ex) {
	    	    System.out.println("SQLException: " + ex.getMessage());
	    	    
	    	    System.out.println("SQLState: " + ex.getSQLState());
	    	    System.out.println("VendorError: " + ex.getErrorCode());
	    	}
	}
	public void update(String s){
		try {
	    	   conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/workout?user=root&password=");
	    	   ps = conn.prepareStatement(s);
	    	   ps.executeUpdate();
		
		} catch (SQLException ex) {
 	    System.out.println("SQLException: " + ex.getMessage());
 	    System.out.println("SQLState: " + ex.getSQLState());
 	    System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	}

}

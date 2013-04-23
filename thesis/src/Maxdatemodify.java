import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Maxdatemodify {

	public static void main(String[] args) {
		  Connection con = null; //Database objects 
		  //連接object 
		  Statement stat = null; 
		  Statement stat1 = null; 
		  Statement stat2 = null; 
		  //執行,傳入之sql為完整字串 
		  ResultSet rs = null; 
		  ResultSet rs1 = null; 
		  //結果集 
		  PreparedStatement pst = null; 
		  //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
		  

		  
	    try { 
	    	
	        Class.forName("com.mysql.jdbc.Driver"); 
	        //註冊driver 
	        con = DriverManager.getConnection( 
	        "jdbc:mysql://localhost/roomi_201201_06", 
	        "root","1234qwerA"); 
	        //取得connection
	        
	      } 
	      catch(ClassNotFoundException e) 
	      { 
	        System.out.println("DriverClassNotFound :"+e.toString()); 
	      }//有可能會產生sqlexception 
	      catch(SQLException x) { 
	        System.out.println("Exception :"+x.toString()); 
	      } 
	    String visit = "insert into visit_logintime select userid,MIN(time),MAX(time) from visit_new where userid=";
	    
	    String user  = "select * from user_level";
	    
	    String delete_record = "delete from visit_new where userid="; 
	    try { 
	        stat = con.createStatement(); 
	        stat1=con.createStatement(); 
	        stat2=con.createStatement(); 
	        rs = stat.executeQuery(user); 
	        int count=0;
	        String userid;
	        System.out.println("ID\t\tName\t\tPASSWORD"); 
	        while(rs.next()) 
	        { 
	          userid = rs.getString("userid");
	          System.out.println(++count+"  "+visit+userid+" group by userid order by time ");
	          stat1.executeUpdate(visit+userid+" group by userid order by time "); 
	          System.out.println(delete_record+userid);
	          stat2.executeUpdate(delete_record+userid);
	        } 
	      } 
	      catch(SQLException e) 
	      { 
	        System.out.println("DropDB Exception :" + e.toString()); 
	      } 
	      finally 
	      { 
	    	  System.out.println("FINISHED");
	    	  pst = null; 
	    	  stat = null; 
	          rs = null; 
	      } 
	    
	   
	
	}
	
	
}

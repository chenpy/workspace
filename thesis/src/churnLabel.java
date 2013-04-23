import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class churnLabel {
	public static void main(String[] args) {
		  Connection con = null; //Database objects 
		  //連接object 
		  Statement stat = null; 
		  Statement stat1 = null; 
		  //執行,傳入之sql為完整字串 
		  ResultSet rs = null; 
		  ResultSet rs1 = null; 
		  //結果集 
		  PreparedStatement pst = null; 
		  //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
		  //-----
		  
		  String [] VLAEType = {"buy","fishing","farm","activity","article","act","list"};
		  String [] SCAEType = {"chat","mail","msg","visit","transact"};
		  String [] GAEType = {"login"};
		  int timeIterator = 0,sheetIterator=0,maxTimePeriod=7;
		  String actLabel,actSheet,startDate,endDate;
		  
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

	    
	    //------------Progarm Start
	    //
	
	    //	for(timeIterator=1;timeIterator<=7;timeIterator++){
	      try 
	      { 
	    	//query variable and query
	    	int time=timeIterator;
		    String visit = "update  activity_energy3 set churn=1 where  userid=";
		    String user  = "select userid from churnuser_temp";
	    	// sql variable
	        stat = con.createStatement(); 
	        stat1=con.createStatement(); 
	        rs = stat.executeQuery(user); 
	        int count=0;
	        String userid; 
	        while(rs.next()) 
	        { 
	          userid = rs.getString("userid");
	          System.out.println(++count+"  "+visit+userid);
	          stat1.executeUpdate(visit+userid); 
	          
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
	    
	 //  }
	
	    }
}

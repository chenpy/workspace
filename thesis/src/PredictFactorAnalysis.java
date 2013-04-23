import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PredictFactorAnalysis {
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
		  String [] timePeriod ={"2012-01-01","2012-01-16","2012-02-01","2012-02-16","2012-03-01","2012-03-16","2012-04-01","2012-04-16","2012-05-01","2012-05-16","2012-06-01","2012-06-16","2012-07-01"};
		  String [] dataSheet = {"buy_count_2","fishing","gift_final","login_new","visit_final","farm_final","transact_final","reply"};
		  String [] actType = {"buy","fishing","gift","login","visit","farm","transact","reply"};
		  int timeIterator = 0,sheetIterator=0;
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
	    for(sheetIterator=0;sheetIterator<dataSheet.length;sheetIterator++){
	    	for(timeIterator=0;timeIterator<timePeriod.length-1;timeIterator++){
	      try 
	      { 
	    	//query variable and query
	    	actLabel=actType[sheetIterator];
	    	actSheet=dataSheet[sheetIterator];
	    	startDate=timePeriod[timeIterator];
	    	endDate=timePeriod[timeIterator+1];
	    	int time=timeIterator+1;
	    	String visit = "insert into activity_freq select userid,\""+time+"\",\""+actLabel+"\",sum(count) from "+actSheet+
		    		" where date>=\""+startDate+"\" AND date<\""+endDate+"\" AND userid=";
		    String user  = "select userid from user_level";
	    	// sql variable
	        stat = con.createStatement(); 
	        stat1=con.createStatement(); 
	        rs = stat.executeQuery(user); 
	        int count=0;
	        String userid; 
	        while(rs.next()) 
	        { 
	          userid = rs.getString("userid");
	          System.out.println(++count+"  "+visit+userid+" group by userid  ");
	          stat1.executeUpdate(visit+userid+" group by userid"); 
	          
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
}}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class D_normalizeAE {
	public static void main(String[] args) {
		  Connection con = null; //Database objects 
		  Connection con1 = null; 
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
		  
		  int W_VLAE,W_SCAE,W_GAE;
		  int timeIterator = 0,sheetIterator=0,maxTimePeriod=7;
		  String actLabel,actSheet,startDate,endDate;
		  
	    try { 
	    	
	        Class.forName("com.mysql.jdbc.Driver"); 
	        //註冊driver 
	        con = DriverManager.getConnection( 
	        "jdbc:mysql://localhost/roomi_201201_06", 
	        "root","1234qwerA"); 
	        con1 = DriverManager.getConnection( 
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
	
	    	for(timeIterator=1;timeIterator<=7;timeIterator++){
	      try 
	      { 
	    	//query variable and query
	    	int time=timeIterator;
		    String VLAE_query = "select value from activity_energy where type='VLAE' AND timeperiod="+timeIterator+" AND userid=";
		    String SCAE_query = "select value from activity_energy where type='SCAE' AND timeperiod="+timeIterator+" AND userid=";
		    String GAE_query = "select value from activity_energy where type='GAE' AND timeperiod="+timeIterator+" AND userid=";
		    String user  = "select id from visit_loginuser";
		    String Insert= "insert into activity_energy2 values (";
		    
		    
	    	// sql variable
	        stat = con.createStatement(); 
	        stat1=con1.createStatement(); 
	        rs = stat.executeQuery(user); 
	        int count=0;
	        String userid; 
	        while(rs.next()) 
	        { 
	        	 int VLAE=0,SCAE=0,GAE=0;
	          userid = rs.getString("id");
	         //System.out.println("1"); 
	          rs1=null;
	          System.out.println(VLAE_query+userid);
	          rs1= stat1.executeQuery(VLAE_query+userid); //System.out.println("1.1"); 
	          //System.out.println("1.2"); 
	          if(rs1.next())VLAE= rs1.getInt("value");//System.out.println("1.3"); 
	         // System.out.println("2");
	          rs1=null;
	          rs1= stat1.executeQuery(SCAE_query+userid);
	          if(rs1.next())SCAE= rs1.getInt("value");
	          //System.out.println("3"); 
	          rs1=null;
	          rs1= stat1.executeQuery(GAE_query+userid);
	          if(rs1.next()) GAE= rs1.getInt("value");
	          
	          stat1.executeUpdate("insert into activity_energy2 values ("+userid+","+timeIterator+","+VLAE+","+SCAE+","+GAE+")"); 
	          System.out.println(++count+" insert into activity_energy2 values ("+userid+","+timeIterator+","+VLAE+","+SCAE+","+GAE+")"); 
	       
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
}

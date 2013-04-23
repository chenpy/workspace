import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class activity_energy3 {
	public static void main(String[] args) throws SQLException {
		  Connection con = null; //Database objects 
		  Connection con1 = null; 
		  Connection con2 = null; 
		  //連接object 
		  Statement stat = null; 
		  Statement stat1 = null; 
		  //執行,傳入之sql為完整字串 
		  ResultSet rs = null; 
		  ResultSet rs1 = null; 
		  //結果集 

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
	        con2 = DriverManager.getConnection( 
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
	
	    	
	      try 
	      { 
	    	//query variable and query
	    	int time=timeIterator;
			  PreparedStatement pst = con1.prepareStatement("INSERT INTO activity_energy3 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		    String user  = "select userid from user_level";
		    
		    
	    	// sql variable
	        stat = con.createStatement(); 
	        stat1=con1.createStatement(); 
	        rs = stat.executeQuery(user); 
	        int count=0;
	        String userid; 
	        int [] VLAE=new int [15];
	        int []SCAE=new int [15];
	        int [] GAE=new int [15];
	        while(rs.next()) 
	        { 
	        	userid = rs.getString("userid");
	        	pst.setString(1,userid);
	        	for(timeIterator=1;timeIterator<=12;timeIterator++){
	    		    String VLAE_query = "select * from activity_energy where type='VLAE' AND timeperiod="+timeIterator+" AND userid=";
	    		    String SCAE_query = "select * from activity_energy where type='SCAE' AND timeperiod="+timeIterator+" AND userid=";
	    		    String GAE_query = "select * from activity_energy where type='GAE' AND timeperiod="+timeIterator+" AND userid=";
	    		    VLAE[timeIterator]=0;
	    		    SCAE[timeIterator]=0;
	    		    GAE[timeIterator]=0;
	        		
	        		rs1=null;
	        		//System.out.println(VLAE_query+userid);
	        		rs1= stat1.executeQuery(VLAE_query+userid); //System.out.println("1.1"); 
	          		if(rs1.next())VLAE[timeIterator]= rs1.getInt("value");//System.out.println("1.3"); 
	          		else VLAE[timeIterator]=0;
	          		pst.setInt(timeIterator*3-1,VLAE[timeIterator]);
	          		
	        		rs1=null;
	        		rs1= stat1.executeQuery(SCAE_query+userid);
	        		if(rs1.next())SCAE[timeIterator]= rs1.getInt("value");
	        		else VLAE[timeIterator]=0;
	        		pst.setInt(timeIterator*3,SCAE[timeIterator]);
	        		
	        		rs1=null;
	        		rs1= stat1.executeQuery(GAE_query+userid);
	        		if(rs1.next()) GAE[timeIterator]= rs1.getInt("value");
	        		else VLAE[timeIterator]=0;
	        		pst.setInt(timeIterator*3+1,GAE[timeIterator]);
	        		//System.out.println(++count+" update activity_energy3 set VLAE"+timeIterator+"="+VLAE[timeIterator]+" ,SCAE"+timeIterator+"="+SCAE[timeIterator]+" ,GAE"+timeIterator+"="+GAE[timeIterator]+" where userid="+userid);
	        		//stat1.executeUpdate(" update activity_energy3 set VLAE"+timeIterator+"="+VLAE[timeIterator]+" ,SCAE"+timeIterator+"="+SCAE[timeIterator]+" ,GAE"+timeIterator+"="+GAE[timeIterator]+" where userid="+userid);
	        		
	        	 }
	        	//pst.setInt(23,rs1.getInt("churn"));
	        	
	        	String insert=pst.toString();
	        	insert=insert.substring(46);
	        	System.out.println(++count+" "+insert);
	        	stat1.executeUpdate(insert);
	        	//pst.execute(); 
	        	pst.clearParameters(); 
	        	 	
	        	 
	        }} 
	      
	      catch(SQLException e) 
	      { 
	        System.out.println("DropDB Exception :" + e.toString() ); 
	      } 
	      finally 
	      { 
	    	  System.out.println("FINISHED");

	    	  stat = null; 
	          rs = null; 
	      } 
	    
	    
	
	    
}}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PFA_dateformat {
	public static void main(String[] args) {
		  Connection con = null; //Database objects 
		  //�s��object 
		  Statement stat = null; 
		  Statement stat1 = null; 
		  //����,�ǤJ��sql������r�� 
		  ResultSet rs = null; 
		  ResultSet rs1 = null; 
		  //���G�� 
		  PreparedStatement pst = null; 
		  //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
		  //-----
		  String [] timePeriod ={"0101","0116","0201","0216","0301","0316","0401","0416","0501","0516","0601","0616","0701"};
		  String [] dataSheet = {"act","article","chat_final","list_final","mail_final","msg_final"};
		  String [] actType = {"act","article","chat","list","mail","msg"};
		  int timeIterator = 0,sheetIterator=0;
		  String actLabel,actSheet,startDate,endDate;
		  
	    try { 
	    	
	        Class.forName("com.mysql.jdbc.Driver"); 
	        //���Udriver 
	        con = DriverManager.getConnection( 
	        "jdbc:mysql://localhost/roomi_201201_06", 
	        "root","1234qwerA"); 
	        //���oconnection
	        
	      } 
	      catch(ClassNotFoundException e) 
	      { 
	        System.out.println("DriverClassNotFound :"+e.toString()); 
	      }//���i��|����sqlexception 
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

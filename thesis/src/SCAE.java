import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SCAE {
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
		  
		  String [] VLAEType = {"buy","fishing","farm","activity","article","act","reply"};
		  String [] SCAEType = {"chat","mail","msg","visit","transact","gift","list"};
		  String [] GAEType = {"login"};
		  int timeIterator = 0,sheetIterator=0,maxTimePeriod=7;
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
	
	    	for(timeIterator=1;timeIterator<=12;timeIterator++){
	      try 
	      { 
	    	//query variable and query
	    	int time=timeIterator;
		    String visit = "insert into activity_energy select userid,\""+time+"\",\"SCAE\",sum(frequency) from activity_freq where " +	"timeperiod="+time+" and (acttype=\"chat\" or acttype=\"mail\" or acttype=\"msg\" or acttype=\"visit\" or acttype=\"transact\" or acttype=\"gift\" or acttype=\"list\") and userid=";
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
}

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
public class pickMsgLevelbeyond5 {

	
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
	    String visit = "insert into msg_final select * from msg where userid=";
	    String user  = "select userid from user_level";
	    
	    
	      try 
	      { 
	        stat = con.createStatement(); 
	        stat1=con.createStatement(); 
	        rs = stat.executeQuery(user); 
	        int count=0;
	        String userid;
	        System.out.println("ID\t\tName\t\tPASSWORD"); 
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
	    
	   
	
	}
}

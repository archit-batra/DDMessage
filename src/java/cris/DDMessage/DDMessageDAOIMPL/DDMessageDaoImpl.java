package cris.DDMessage.DDMessageDAOIMPL;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.mail.*; 
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;





import cris.DDMessage.DDMessageDAO.DDMessageDao;
import cris.DDMessage.DDMessageDTO.DDMessageDto;

public class DDMessageDaoImpl implements DDMessageDao 
{
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
	SimpleDateFormat dateFor = new SimpleDateFormat("dd-MMM-yy");
	String DateTime=dateFormat.format(date);
	String Date=dateFor.format(date);
        
	
	
    Connection conobj = null;
	Connection con = null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String sql=null;
	int Status=0;
	
        
        public  static  Connection connect()throws  Exception
        {
          Class.forName("com.mysql.jdbc.Driver");   
          return DriverManager.getConnection("jdbc:mysql://localhost:3306/ddmessage","root","");
        }

        
        
        
        
	/*public DDMessageDto setDDMessageToDto(HttpServletRequest request)
	{
		DDMessageDto ddDto=new DDMessageDto();
		ddDto.setReceipient(request.getParameter("Receipient"));
		ddDto.setPRRNo(Integer.parseInt(request.getParameter("PRRNo")));
		ddDto.setPWBNo(Integer.parseInt(request.getParameter("PWBNo")));
		ddDto.setFromStation(request.getParameter("FromStation"));
		ddDto.setToStation(request.getParameter("ToStation"));
		ddDto.setSubject(request.getParameter("Subject"));
		ddDto.setMessage(request.getParameter("Message"));
		ddDto.setImgUpload(request.getParameter("ImgUpload"));
		
		return ddDto;  
	}*/
	
	public List<String> getStationList()
	{
		Connection conobj = null;
		Connection con = null;
		PreparedStatement ps=null;
		
		String sql=null;
		List<String > StationList=new ArrayList<String>();
		
		try {
			  
			  
			  sql="select DISTINCT STNCODE from M_Userid ORDER BY STNCODE";
		      System.out.println("sql: "+sql);
		      ps=con.prepareStatement(sql);
		      rs=ps.executeQuery(); 
		      if(rs != null)
		      {
		    	 
		    	  while(rs.next())
		    	  {
		    		  StationList.add(rs.getString("STNCODE"));
		    	  }
		      }
		     
		} catch (Exception ex){
			ex.printStackTrace();
		}
		   finally{
			   
			   try
			   {
				   rs.close();
				   ps.close();
				   con.close();
				  }catch (SQLException e) 
			   {
				e.printStackTrace();
			}
			   }
		return StationList;
	
	}
	/*
	public List<DDMessageDto> getSupervisorID()
	{
		connectionobj conobj = null;
		Connection con = null;
		PreparedStatement ps=null;
		
		String sql=null;
		List<DDMessageDto > SupervisorList=new ArrayList<DDMessageDto>();
		
		try {
			  conobj = new connectionobj();
			  con = conobj.getconnobj("Test");
			  
			 
			  
	          sql="SELECT USERID from WEBPARCEL.M_Userid Where DIV='DLI' ORDER BY USERID ";
		      System.out.println("sql: "+sql);
		      ps=con.prepareStatement(sql);
		      rs=ps.executeQuery(); 
		      if(rs != null)
 		      {
		    	 
		    	  while(rs.next())
		    	  {
		    		  DDMessageDto ddDto=new DDMessageDto();
		    		  
		    		  ddDto.setSupervisorId(rs.getString("USERID"));
		    		  SupervisorList.add(ddDto);
		    	  }
		      }
		     
		} catch (Exception ex){
			ex.printStackTrace();
		}
		   finally{
			   
			   try
			   {
				   rs.close();
				   ps.close();
				   con.close();
				  }catch (SQLException e) 
			   {
				e.printStackTrace();
			}
			   }
		return SupervisorList;
	
	}
	*/
	
	
	
	
	public String  SaveDDMessage(DDMessageDto dto)
	{
               
                        
		
		
		String sql=null;
		String Message=null;
		
		
        try {
			  
		Connection con = connect();
		PreparedStatement ps=con.prepareStatement("");	  
			  
			  // InputStream inputstream = new FileInputStream(dto.getImgUpload());
              //System.out.println("UPLOADED_FILE"+UPLOADED_FILE);
	          sql="INSERT INTO Mailstore (SENDER_MAILID,RECEIPIENTMAILID,PRR_NO,PWB_LT,FROM_STN,TO_STN,SUBJECT,MESSAGE,SEND_DT) values(?,?,?,?,?,?,?,?,?)";
		      System.out.println("sql: "+sql);
		      ps=con.prepareStatement(sql);
		      ps.setString(1, "ashish@parcel.indianrail.gov.in");
		      ps.setString(2, dto.getReceipient());
		      ps.setLong(3, dto.getPRRNo());
		      ps.setLong(4, dto.getPWBNo());
		      ps.setString(5, dto.getFromStation().trim());
		      ps.setString(6, dto.getToStation().trim());
		      ps.setString(7, dto.getSubject());
		      ps.setString(8, dto.getMessage());
		      ps.setString(9, Date);
		      //ps.setBinaryStream(10, inputstream, inputstream.available());
		      
		      Status=ps.executeUpdate(); 
			  
		      if(Status > 0)
		    	  Message="Successfully Send";
		      else
		    	  Message="Error Occurred Try Again ?..";  
		      
		
		} catch (Exception ex){
			ex.printStackTrace();
		}
		   finally{
			   
			   try
			   {
				   //rs.close();
				   ps.close();
				   con.close();
				  }catch (SQLException e) 
			   {
				e.printStackTrace();
			}
			   }
		return Message;
	}
	
	
	
    public String sendMail(DDMessageDto ddDto) 
	{
    	 
	      String recipients[]=ddDto.getReceipient().split(",");
	      String Subject=ddDto.getSubject(); 
	      String UserMessage=ddDto.getMessage(); 
	      String from="ashish@parcel.indianrail.gov.in" ; 
	      
	      String attachFile=ddDto.getImgUpload();
	      System.out.println("attachFile : "+attachFile);
	      
	     //Get the session object
	      //String host = "172.16.2.66";//or IP address
	      String host = "10.64.26.164";//or IP address
	      final String user="admin@parcel.indianrail.gov.in";//change accordingly  
	      final String password="Pms@1234";//change accordingly  
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      properties.put("mail.smtp.auth", "true");  
	      properties .setProperty("mail.smtp.port", "25");
	      Session session = Session.getDefaultInstance(properties,  
	    		    new javax.mail.Authenticator() {  
	    		      protected PasswordAuthentication getPasswordAuthentication() {  
	    		    return new PasswordAuthentication(user,password);  
	    		      }  
	    		    });  
	     //compose the message
	      try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         InternetAddress[] addressTo = new InternetAddress[recipients.length];
           for (int i = 0; i < recipients.length; i++) {
                  addressTo[i] = new InternetAddress(recipients[i]);
           }
	         message.setRecipients(Message.RecipientType.TO, addressTo);
	         message.addHeader("Testing Mail Application", "Testing Mail Application");
	         message.setSubject(Subject);
	         //message.setText(UserMessage);
	         message.setSentDate(new Date());
	         
	         
	         // create MimeBodyPart object and set your message content    
	         MimeBodyPart messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setText(UserMessage);
	         
	         // create new MimeBodyPart object and set DataHandler object to this object    
	         /*MimeBodyPart attachPart  = new MimeBodyPart();
	         try {
	        	 attachPart.attachFile(attachFile);
             } catch (IOException ex) {
                 ex.printStackTrace();
             }*/
	         
             // create Multipart object and add MimeBodyPart objects to this object    
	         Multipart multipart = new MimeMultipart();
	         multipart.addBodyPart(messageBodyPart);
	         /* multipart.addBodyPart(attachPart);

	         // Send message
	         message.setContent(multipart );*/
	         Transport.send(message);
	         
	         System.out.println("Message Send Successfully....");

	      }catch (MessagingException mex) {mex.printStackTrace();}
		  return "Message Send";  
	}
    
    public List<DDMessageDto> getDDMessageSaved(DDMessageDto ddMDto)
	{
		Connection conobj = null;
		Connection con = null;
		PreparedStatement ps=null;
		
		String sql=null;
		List<DDMessageDto > DDMessageSavedList=new ArrayList<DDMessageDto>();
		
		try {
			 
			 
			  sql="SELECT SENDER_MAILID,RECEIPIENTMAILID,PRR_NO,PWB_LT,FROM_STN,TO_STN,SUBJECT,MESSAGE,SEND_DT from Mailstore Where PRR_NO='"+ddMDto.getPRRNo()+"'AND PWB_LT='"+ddMDto.getPWBNo()+"'  ";
		      System.out.println("sql: "+sql);
		      ps=con.prepareStatement(sql);
		      rs=ps.executeQuery(); 
		      if(rs != null)
		      {
		    	  while(rs.next())
		    	  {
		    		  DDMessageDto ddDto=new DDMessageDto();
		    		  
		    		  ddDto.setSenderID(rs.getString("SENDER_MAILID"));
		    		  ddDto.setReceipient(rs.getString("RECEIPIENTMAILID"));
		    		  ddDto.setPRRNo(rs.getInt("PRR_NO"));
		    		  ddDto.setPWBNo(rs.getInt("PWB_LT"));
		    		  ddDto.setFromStation(rs.getString("FROM_STN"));
		    		  ddDto.setToStation(rs.getString("TO_STN"));
		    		  ddDto.setSubject(rs.getString("SUBJECT"));
		    		  ddDto.setMessage(rs.getString("MESSAGE"));
		    		  ddDto.setSendDate(rs.getString("SEND_DT"));
		    		  //ddDto.setImgUpload(rs.getString("USERID"));
		    		  
		    		  DDMessageSavedList.add(ddDto);
		    	  }
		      }
		     
		} catch (Exception ex){
			ex.printStackTrace();
		}
		   finally{
			   
			   try
			   {
				   rs.close();
				   ps.close();
				   con.close();
				  }catch (SQLException e) 
			   {
				e.printStackTrace();
			}
			   }
		return DDMessageSavedList;
	
	}
}
	
	
	
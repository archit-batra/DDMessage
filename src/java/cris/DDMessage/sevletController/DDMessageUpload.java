package cris.DDMessage.sevletController;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cris.DDMessage.DDMessageDAO.DDMessageDao;
import cris.DDMessage.DDMessageDAOIMPL.DDMessageDaoImpl;
import cris.DDMessage.DDMessageDTO.DDMessageDto;

/**
 * Servlet implementation class DDMessageUpload
 */
public class DDMessageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DDMessageUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//String filePath = request.getRealPath("/");
		//String filePath=request.getRealPath("") + "WebContent/DDMessageUpload/";
		
		//System.out.println("filePath1"+filePath);
		
		//System.out.println("filePath2"+filePath);
	    boolean isMultipart;
	    String filePath="C:/Program Files/apache-tomcat-6.0.41/webapps/DDMessageupload/";
	    
	    int maxFileSize = 50 * 1024;
	    int maxMemSize = 4 * 1024;
	    File file ;
	    
	    String Receipient=null;
	    String PRRNo=null;
	    String PWBNo=null;
	    String FromStation=null;
	    String ToStation=null;
	    String Subject=null;
	    String UserMessage=null;
	    
	    
	    
	    long sizeInBytes=0L;
	    DDMessageDto ddDto=new DDMessageDto();
	    String Message=null;
	// Check that we have a file upload request
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      
	      if( !isMultipart ){
	    	  request.setAttribute("Message", "File Not Uploaded Try Again ?.. ");
	    	  request.getRequestDispatcher("/DDMessageSuccess.jsp").forward(request, response);
	    	}
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("c:\\temp"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );

	      try{ 
	      // Parse the request to get file items.
	      List fileItems = upload.parseRequest(request);
		
	      // Process the uploaded file items
	      Iterator i = fileItems.iterator();

	      while ( i.hasNext () ) 
	      {
	         FileItem fi = (FileItem)i.next();
	         if ( !fi.isFormField () )	
	         {
	            // Get the uploaded file parameters
	            String fieldName = fi.getFieldName();//field name
	            String fileName = fi.getName(); //field value
	            if(fileName.equals(""))
	            { }else
	            {
	            	  // System.out.println("fieldName "+fieldName+" fileName "+fileName);
			            
			            String contentType = fi.getContentType();
			            boolean isInMemory = fi.isInMemory();
			            sizeInBytes = fi.getSize();
			            // Write the file
			            if( fileName.lastIndexOf("\\") >= 0 ){
			               file = new File( filePath +fileName.substring( fileName.lastIndexOf("\\"))) ;
			            }else{
			               file = new File( filePath +fileName.substring(fileName.lastIndexOf("\\")+1)) ;
			            }
			            fi.write( file ) ;
			            ddDto.setImgUpload(file.getPath());
			            System.out.println("Uploaded Filename: " + fileName);
	            }
	         }if ( fi.isFormField () )
	         {     
	        	     
	        	     
	        	     if(fi.getFieldName().equals("Receipient")){
		                 Receipient = fi.getString(); //field value 
		                 ddDto.setReceipient(Receipient);    
	        	     }
	        	     
	        	     if(fi.getFieldName().equals("PRRNo")){
		                 PRRNo = fi.getString(); //field value
		                 if(PRRNo.equals(""))
		                 { ddDto.setPRRNo(0); }else
		                 ddDto.setPRRNo(Long.parseLong(PRRNo)); 
		                
	        	     }
	        	     
	        	     if(fi.getFieldName().equals("PWBNo")){
	        	    	 PWBNo = fi.getString(); //field value
	        	    	 if(PWBNo.equals(""))
		                 { ddDto.setPWBNo(0); }else
	        	    	 ddDto.setPWBNo(Long.parseLong(PWBNo)); 
			             
	        	    	  
	        	     }
		            
	        	     if(fi.getFieldName().equals("FromStation")){
		                 FromStation = fi.getString(); //field value
		                 ddDto.setFromStation(FromStation);   
	        	     }
	        	     
	        	     if(fi.getFieldName().equals("ToStation")){
		                 ToStation = fi.getString(); //field value
		                 ddDto.setToStation(ToStation);   
	        	     }
	        	     
	        	     if(fi.getFieldName().equals("Subject")){
		                 Subject = fi.getString(); //field value
		                 ddDto.setSubject(Subject); 
	        	     }
	        	     
	        	     if(fi.getFieldName().equals("Message")){
		                 UserMessage = fi.getString(); //field value
		                 ddDto.setMessage(UserMessage);  
	        	     }
	              
		     }
	      }
	      
	      DDMessageDao ddMessdao=new DDMessageDaoImpl();
	      String Status=ddMessdao.sendMail(ddDto);
	      if(Status.equals("Message Send"))
	      {
		      Message=ddMessdao.SaveDDMessage(ddDto);
		      request.setAttribute("Message", Message);
		      request.getRequestDispatcher("/DDMessageSuccess.jsp").forward(request, response);
		     
		  }else
	      {
	    	    request.setAttribute("Message", "Mail Are Not Send Try Again ? ..");
			    request.getRequestDispatcher("/DDMessageSuccess.jsp").forward(request, response);
		  }
	      
	      
	   }catch(Exception ex) {
	        ex.printStackTrace();
	   
	   }
	   
	}

}

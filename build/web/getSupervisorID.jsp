<%@page language="java" import ="java.sql.*" %> 
<%@page language="java" import="org.cris.pms.common.db.connectionobj;" %> 
     
<%    
String Division=request.getParameter("Division"); 
String DivisionArr[]=Division.split(",");
Division="";
for(int i=0; i< DivisionArr.length ;i++)
{
   if(Division == "")
        Division="'"+DivisionArr[i]+"'";
    else
      Division=Division+",'"+DivisionArr[i]+"'";  
}
response.setContentType("text/html");   
response.setHeader("Cache-Control","no-cache");
connectionobj conobj = null;
Connection con = null;  
try  
{
   String buffer="<select multiple='multiple'  name='SupervisorId' id='SupervisorId'>";  
   conobj = new connectionobj();
   con = conobj.getconnobj("Test");  
   Statement stmt = con.createStatement();  
	   
   String Query="SELECT emailid from M_Userid Where DIV IN("+Division+")AND INVALID=0 AND((VALDTTO > Current_date or valdtto is null) AND (VALDTFM < Current_date OR VALDTFM IS NULL ))AND ADMIN_PAD is not null and subStr(ADMIN_PAD,1,1)='1' ORDER BY USERID ";
   System.out.println(Query);  
   ResultSet rs = stmt.executeQuery(Query);  
   while(rs.next())  
   {
     buffer=buffer+"<option value='"+rs.getString("emailid")+"'>"+rs.getString("emailid")+"</option>";  
   }  
     buffer=buffer+"</select>";  
   response.getWriter().println(buffer);  
}  
catch(Exception e)  
{
  response.getWriter().println(e);  
}  
%>  
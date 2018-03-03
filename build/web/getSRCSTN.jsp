<%@page language="java" import ="java.sql.*" %> 
<%
   String PWBLTNO = request.getParameter("PWBNo");
    
    String SationList=null;
    try{        
        Class.forName("com.mysql.jdbc.Driver");   
        Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/ddmessage","root","");
        Statement stmt = con.createStatement();
        String Query="select distinct SRCSTN, DSTNSTN from PWBLTITEMS where PWBLTNO='"+PWBLTNO+"' ";
        ResultSet rs=stmt.executeQuery(Query);
        System.out.println(Query);
        while(rs.next())
        {
            SationList= rs.getString("SRCSTN")+","+rs.getString("DSTNSTN");
            
        }
        response.getWriter().println(SationList);  
    }
    catch(Exception e) {
        e.getStackTrace();
    }
%>

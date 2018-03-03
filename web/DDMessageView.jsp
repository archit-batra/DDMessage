<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.List"%>
<%@page import="cris.DDMessage.DDMessageDTO.DDMessageDto"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cris</title>
</head>
<body>
 <% List<DDMessageDto> DDMessageSavedList=(List<DDMessageDto>)request.getAttribute("DDMessageSavedList"); %>

<table width="90%" align="left" border="1" style="margin-left:5px;">
       
       <tr><th colspan="10" align="center" style="background-color: tan;" >DAMAGE DEDICIENCY MESSAGE</th></tr>
        <tr>
	       <th>SENDER_MAIL</th>
		   <th>SUBJECT</th>
		   <th>MESSAGE</th>
		   <th>RECEIPIENTMAIL</th>
		   <th>PRR No.</th>
		   <th>PWB No.</th>
		   <th>FROM_STN</th>
		   <th>TO_STN</th>
		   <th>STOPPAGE STATION</th>
		   <th>SEND_DT</th>
		</tr>
	   <% for(DDMessageDto ddDto:DDMessageSavedList){ %>
	   <tr>
	       <td><%=ddDto.getSenderID() %></td>
	       <td><%=ddDto.getSubject() %></td>
	       <td><%=ddDto.getMessage() %></td>
	       <td><%=ddDto.getReceipient() %></td>
	       <td><%=ddDto.getPRRNo() %></td>
	       <td><%=ddDto.getPWBNo() %></td>
	       <td><%=ddDto.getFromStation() %></td>
	       <td><%=ddDto.getToStation() %></td>
	       <td>-</td>
	       <td><%=ddDto.getSendDate() %></td>
		</tr>
	  <%} %>
      <tr><td colspan="10" align="center"><a href="DDMessage.jsp"><input type="button" value="Back" /></a> </td></tr>
      </table>
</body>
</html>
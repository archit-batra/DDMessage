<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cris</title>



</head>


<body>

<form name="from1" action="ProposalHandlar" method="post" >
<% String Message =(String)request.getAttribute("Message"); %>

<fieldset style="margin-top: 100px; width:250px;height:100px;border-radius:10px; margin-left:350px; ">
<table style="margin-top: 25px; margin-left:60px;">
<tr><td ><%=Message %></td></tr>	
<tr>
    <td  align="center"  >
     <a href="DDMessage.jsp"><button type="button" class="btn btn-default">Back</button></a>
    </td>
   
</tr>		

</table>
</fieldset>								
<input type="hidden" name="FromPage" id="FromPage" value="" />
</form>

</body>
</html>
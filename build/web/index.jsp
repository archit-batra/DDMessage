<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./stylesheet/pmsstyle.css">
<title>Insert title here</title>
<script type="text/javascript">
function change_case(lowerStr)
{
var getId = lowerStr.id;
if(getId === 'userid')
{
document.getElementById("userid").value=lowerStr.value.toUpperCase();
}
if(getId === 'STN_CODE')
{
document.getElementById("STN_CODE").value=lowerStr.value.toUpperCase();
}
}

function validateFields() {
var a = window.document.myForm.loginStn.value;
var b = window.document.myForm.userId.value;
if(a===null || a==="") {
alert("Plz Enter Login Stn");
return false;
}
if(b===null || b==="") {
alert("Plz Enter User Id");
return false;
}
}
</script>
</head>
<body>
    <form name="myForm" action="DDMessage.jsp" method="post">
    <table>
	<tr>
	<th colspan="2">Login Page</th>
	</tr>
	
	<tr>
		<td align="right">User Id:</td>
		<td align="left"><input type="text" name="userid" id="userid" onblur="change_case(this);"/></td>		
	</tr>
	
	<tr>
		<td align="right">STNCODE:</td>
		<td align="left"><input type="text" name="STN_CODE" id="STN_CODE" onblur="change_case(this);"/></td>		
	</tr>
    
    <tr>
		<td width=50% align="right">Apptype:</td>
		<td align="left"><input type="text" name="apptype" id="apptype" onblur="change_case(this);"/></td>
	</tr>
    
	
    <tr>
		<td class="footer" colspan="2">
		<input type="submit" name="submit" id="submit" value="login" onclick="return validateFields()"/>		
		<input type="reset" name="reset" id="reset" value="Reset"/></td>
	</tr>

</table>
</form> 
</body>
</html>
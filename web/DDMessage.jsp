<%@page import="jdk.nashorn.internal.objects.NativeFunction;"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="cris.DDMessage.DDMessageDAO.DDMessageDao"%>
<%@page import="cris.DDMessage.DDMessageDAOIMPL.DDMessageDaoImpl"%>
<%@page import="java.util.List"%>
<%@page import="cris.DDMessage.DDMessageDTO.DDMessageDto"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CRIS</title>
</head>
<script type="text/javascript">
 function getAjaxObject() {
   if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
     return new XMLHttpRequest();
   } else if (window.ActiveXObject) {
     return new ActiveXObject('Microsoft.XMLHTTP');// code for IE6, IE5
   } else {
     return null;
   }
 }
    function pwbNoDetail()
   {                          
   var xmlhttp=getAjaxObject();
   xmlhttp.onreadystatechange=function() 
   {
       if(xmlhttp.readyState==4 && xmlhttp.status==200)
       {
           var result=xmlhttp.responseText;
	     var detail=result.replace(/^\s+|\s+$/gm,'');
	     if(detail!= null)
	     {
	     		   var resultArr=detail.split(",");
	     		   document.getElementById("FromStation").value=resultArr[0];	           
                           document.getElementById("ToStation").value=resultArr[1];
			   	
             }
       }
   }
   
   var PWBNo = document.getElementById("PWBNo").value;
			var url= "getPWBNodetail.jsp";  
			url += "?PWBNo="+PWBNo;  
			xmlhttp.open("POST",url,true);
			xmlhttp.send();
   
    }
/*function AddID()
{
  var optSelected = "";
  var opts = document.getElementById("SupervisorId");
 
  for(i=0;i<opts.options.length;i++)
  {
     if( opts.options[i].selected )
     { 
        if(optSelected == "")
          optSelected = opts.options[i].value;
        else
          optSelected =optSelected+","+opts.options[i].value;
     }   
        
  }
  var valSelected = document.getElementById("Receipient").value.trim();
  if(valSelected != "") 
    document.getElementById("Receipient").value = valSelected+","+optSelected ;
  else
    document.getElementById("Receipient").value = optSelected ;      
 }

*/
function AddStoppageStation()
{  
  var StoppageStation = document.getElementById("StoppageStation");
  var AddedStoppageStation = document.getElementById("AddedStoppageStation");
  for(var i=0;i<StoppageStation.options.length;i++)
  {
     if( StoppageStation.options[i].selected ) { 
        if(AddedStoppageStation.value == ""){
          AddedStoppageStation.value = StoppageStation.options[i].value;
      }
        else {
            var AddedStoppageStationArr=AddedStoppageStation.value.split(",");
            var flag=false;
            for(var j=0;j<AddedStoppageStationArr.length;j++){
                if(StoppageStation.options[i].value==AddedStoppageStationArr[j]){
                    flag=true;
                    break;
                }
            }
            if(flag==false)
          AddedStoppageStation.value =AddedStoppageStation.value+","+StoppageStation.options[i].value;
      }
     }   
 }
  }


function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : evt.keyCode
    return !(charCode > 31 && (charCode < 48 || charCode > 57));
}

</script>

<script language="javascript" type="text/javascript">  
    var xmlHttp;  
    function showSupervisorID()  
    {  
           var StationList=document.getElementById("AddedStoppageStation").value;
           
           if (typeof XMLHttpRequest != "undefined")  
           {  
             xmlHttp= new XMLHttpRequest();  
           }  
           else if (window.ActiveXObject)   
           {  
             xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");  
           }  
		    if (xmlHttp==null)  
		    { 
		    return;  
		    } 
		      
		    var url= "getSupervisorID.jsp";  
		    url += "?Division=" +StationList;  
		    xmlHttp.onreadystatechange = setSupervisorID;  
		    xmlHttp.open("GET", url, true);  
		    xmlHttp.send(null);  
    }  

function setSupervisorID()   
{   
   if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")  
	{   
	document.getElementById("SupervisorId").innerHTML=xmlHttp.responseText;   
	}   
}    
            
 function Add1() {
 var SupervisorId = document.getElementById("SupervisorId");		   
            var Receipient = document.getElementById("Receipient");
            for(var i=0;i<SupervisorId.options.length;i++) {
            if(SupervisorId.options[i].selected) {
             	if(Receipient.value=="") {
                  Receipient.value = SupervisorId.options[i].value;            
             	} 
             	else {
             	var ReceipientArr=Receipient.value.split(",");
            	var flag=false;
            	for(var j=0;j<ReceipientArr.length;j++) {
            	if(SupervisorId.options[i].value==ReceipientArr[j]) {
            	flag=true;
            	break; 
            	}               
                }
                if(flag==false)
                Receipient.value = Receipient.value +","+ SupervisorId.options[i].value;                
             	}          
            }
            }
            }
            }
            }
</script> 
<script type="text/javascript">
  function setPRRRecord()
  { 
       
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		    xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
		       var StationArr=xmlhttp.responseText;
		       if(StationArr != null)
		       {
			       var strStation=StationArr.split(",");
			       document.getElementById("FromStation").value=strStation[0];
			       document.getElementById("ToStation").value=strStation[1];
			       
			    }
		    }
		  }
			  
	      var PWBNo = document.getElementById("PWBNo").value;
		  var url= "getSRCSTN.jsp";  
		  url += "?PWBNo=" +PWBNo;  
		  xmlhttp.open("POST",url ,true);
		  xmlhttp.send();
   }
  
    
 
</script>

<body>
<%
  // DDMessageDao ddDao=new DDMessageDaoImpl();
  // List<String> StationList=ddDao.getStationList(); 
 %>

 <%! 
 @WebServlet("/myservlet")
public class MyClass{
 int function1(){
 return 0;}
 }
 public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyClass myClass = new MyClass();

        if (request.getParameter("submit") != null) {
            myClass.function1();
        } else {
            // ???
        }

        request.getRequestDispatcher("DDMessage.jsp").forward(request, response);
    }

}
%>
<form name="form1" action="DDMessageUpload" method="post"  enctype="multipart/form-data">
		
		<table align="center" border="1" width="100%">
			<tr style="background-color:#ACF ">
			  <th colspan="2" align="center">Damage Deficiency Message Module</th>
			</tr>
			
			<tr>
	          <td colspan="2">
	            <table>
	            
	                  <td>PRR/PWB No.</td>
                          <td><input type="text"  name="PWBNo" id="PWBNo" size="13" maxlength="15" onblur="pwbNoDetail();" onkeypress="return isNumberKey(event);"  /></td>
	                  <td>From Station</td>
	                   <td><input type="text"  name="FromStation" id="FromStation" size="7" maxlength="7" readonly="readonly"/></td>
	                  <td>To Station</td>
		               <td><input type="text"  name="ToStation" id="ToStation" size="7" maxlength="7" readonly="readonly"/></td>
			          <td>Stoppage Station</td>
		              <td>
	                    <select name="StoppageStation" id="StoppageStation" style="width:70px;" multiple="multiple" >
			               
	                        <option value="NDLS">NDLS</option>
                                <option value="DLI">DLI</option>
                                <option value="CNB">CNB</option>
                                <option value="HWA">HWA</option>
	                   </select>
                                  &nbsp;<input type="button" name="Add" id="Add" value="Add" onclick="AddStoppageStation();"/>
			          </td>
			          <td>
	                    <textarea  cols="10" rows="3" name="AddedStoppageStation" id="AddedStoppageStation" maxlength="1500"></textarea>
	                  </td>
		           </tr>
	            </table>
	          </td>
	        </tr>
	        
			
	        <tr>
	          <td>To</td>
	          <td>
	              <textarea  cols="71" rows="4" name="Receipient" id="Receipient" maxlength="1500"></textarea>
	          </td>
	        </tr>
	        <tr>
	          <td>Supervisor Id</td>
	          <td>
	          <select multiple="multiple"  name="SupervisorId" id="SupervisorId" multiple="multiple">
                      <option value="ram@gmail.com">ram@gmail.com</option>
                      <option value="shyam@gmail.com">shyam@gmail.com</option>
                      <option value="mohan@gmail.com">mohan@gmail.com</option>
                      <option value="sohan@gmail.com">sohan@gmail.com</option>
                      <option value="mukash@gmail.com">mukash@gmail.com</option>
                      <option value="rakesh@gmail.com">rakesh@gmail.com</option>
	          </select>
                      &nbsp;<input type="button" name="Add" id="Add" value="Add" onclick="Add1();"/>  
	          </td>
	        </tr>
	        
			<tr>
	          <td>Subject</td>
	          <td>
	              <input type="text"  name="Subject" id="Subject" size="120" maxlength="200"/>
	          </td>
	        </tr>
			
			<tr>
	          <td>Message</td>
	          <td>
	               <textarea  cols="91" rows="9" name="Message" id="Message" maxlength="2000"></textarea>
	          </td>
	        </tr>
			
	       <tr>
	          <td>Upload</td>
	          <td>
	              <input type="file"  name="ImgUpload" id="ImgUpload" />
	          </td>
	        </tr>
			
	       		
			
			<tr>
				<td colspan='2' align="center">
				    <input type='submit' name='submit' value='Send' />
				   <!--  <input type='button' name='button' value='Exit'  /> -->
				</td>
			</tr>
		</table>
		
		
		
		
		<input type="hidden" name="FromPage" id="FromPage" value="fromDDMessage" />
		</form>
 </body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>view page</title>

<style>

body{

background: #F0F8FF;

}
.back{

	position: absolute;
	left: 1300px;
	top:50px;
}
* {
  font-family: sans-serif; /* Change your font family */
}

.content-table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  min-width: 400px;
  border-radius: 5px 5px 0 0;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

.content-table thead tr {
  background-color: #009879;
  color: #ffffff;
  text-align: left;
  font-weight: bold;
}

.content-table th,
.content-table td {
  padding: 12px 15px;
}

.content-table tbody tr {
  border-bottom: 1px solid #dddddd;
}

.content-table tbody tr:nth-of-type(even) {
  background-color: #f3f3f3;
}

.content-table tbody tr:last-of-type {
  border-bottom: 2px solid #009879;
}

.content-table tbody tr.active-row {
  font-weight: bold;
  color: #009879;
}


</style>
</head>
<body>

<div align = "center">
	
	
	<a class="back" href="#" onclick="goBack();" target ="_self" > Back</a><br><br> 


	<p style="color:red">${message}</p>
	
    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>List of Quotes</h2></caption>
            <thead>
            <tr>
                <th>Quote Id</th>
                <th>Request Date</th>
                <th>price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>David's note</th>
                <th>Note</th>
         
            </tr>
          </thead>
         	<tbody>
         	
		    <c:forEach var="quote" items="${quotes}">
		        <tr style="text-align:center">
		        
		            <form action="negotiate" method="post"> 
		                <td><c:out value="${quote.quoteId}" /></td>
		                <td><c:out value="${quote.requestedDate}" /></td>
		                <td><c:out value="${quote.price}" /></td>
		                <td><c:out value="${quote.startDate}" /></td>
		                <td><c:out value="${quote.endDate}" /></td>
		                <td><c:out value="${quote.status}" /></td>
		                <td><c:out value="${quote.note}" /></td>
		              	<td>
  						<input type="text" name="note" value="" ${quote.status == 'Requested' ? 'disabled' : ''} />
						</td>
		              	<input name="quoteId" hidden value="${quote.quoteId}"/>
		               	<input name="clientId" hidden value="${quote.clientId}"/>
		               	<input name="startDate" hidden value="${quote.startDate}"/>
		               	<input name="endDate" hidden value="${quote.endDate}"/>
		               	<input name="price" hidden value="${quote.price}"/>
		                <td>
		                <input type="submit" value="Negotiate" ${quote.status == 'negotiate' || quote.status == 'generate' ? '' : 'disabled'}  />
		                </td>
		                
		            </form>
		            
		             <form action="acceptQuote" method="post">
		            	 <input name="quoteId" hidden value="${quote.quoteId}"/>
		            	 <input name="clientId" hidden value="${quote.clientId}"/>
		            	 <input name="startDate" hidden value="${quote.startDate}"/>
		               	<input name="endDate" hidden value="${quote.endDate}"/>
		               	<input name="price" hidden value="${quote.price}"/>
		            	 <td><input type="submit" value="Accept" ${quote.status == 'negotiate' || quote.status == 'generate' ? '' : 'disabled'}  /></td>
		            </form>
		            
		             <form action="rejectQuote" method="post">
		            	 <input name="quoteId" hidden value="${quote.quoteId}"/>
		            	 <td><input type="submit" value="reject" ${quote.status != 'accepted' ? '' : 'disabled'}  /></td>
		            </form>
		            
		            
		        </tr>
		    </c:forEach>	
		    
		    </tbody>
        </table>
	</div>
	</div>
	<script>
		function goBack() {
    	window.history.back();
	}
</script>
</body>
</html>
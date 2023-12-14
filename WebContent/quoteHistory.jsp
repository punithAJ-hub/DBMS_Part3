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


.logout{

    border-radius: 1.5rem;
    font-family: BrandonText, Arial, sans-serif;
    font-weight: 700;
    color: white !important;
    line-height: 1.25;
    -ms-flex-pack: center;
    justify-content: center;
    padding: 0.469rem 0.75rem;
    border: none;
    background: indianred;
    display: inline-block;
    width: 10%;
    max-width: 450px;
    height: 35px;
    cursor: pointer;
    font-size: 1.3125rem;
    text-transform: none;
} 

</style>
</head>
<body>

<div align = "center">
	
	
	<a class="logout" onclick="goBack();"target ="_self" > Back</a><br><br> 



    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>List of Quotes</h2></caption>
            <thead>
            <tr>
            	<th>Id</th>
                <th>Quote Id</th>
                <th>Client Id</th>
                <th>Request Date</th>
                <th>price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Note</th>
         
            </tr>
            </thead>
          	<tbody>
		    <c:forEach var="quote_history" items="${quotes}">
		        <tr style="text-align:center">
		        
		            
		            	<td><c:out value="${quote_history.id}" /></td>
		                <td><c:out value="${quote_history.quoteId}" /></td>
		                <td><c:out value="${quote_history.clientId}" /></td>
		                <td><c:out value="${quote_history.requestedDate}" /></td>
		                <td><c:out value="${quote_history.price}" /></td>
		                <td><c:out value="${quote_history.startDate}" /></td>
		                <td><c:out value="${quote_history.endDate}" /></td>
		                <td><c:out value="${quote_history.status}" /></td>
		              	<td>
  							<c:out value="${quote_history.note}" />
						</td>
		          
		            
		       
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
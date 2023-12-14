<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login to Database</title>
<style>

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
	position: absolute;
	left: 1300px;
	top:50px;

}


.btns{
	display: flex;
	flex-direction: row;
	gap: 10%;
	justify-content: center;

}

.btn , .createQuote{
    display: block;
    font-weight: 700;
    color: #fff !important;
    line-height: 1.25;
    -ms-flex-pack: center;
    justify-content: center;
    padding: 0.469rem 0.75rem;
    border: none;
    background: deepskyblue;
    max-width: 450px;
    height: 28px;
    cursor: pointer;
    font-size: 1.3125rem;
    margin-top: 0.5rem;
    margin-bottom: -0.5rem;
    text-transform: none;
    text-align: center;
    align-items: center;
    -ms-flex-pack: center;
    border-radius: 1.5rem;
    
    
}

.btn{
padding-bottom: 30px;

}


body{
background: #F0F8FF;

}


</style>
</head>
<body>
 <center>	<h1 class="title"> Welcome ${username} </h1> </center>
	<div align="center">
		<h1>This is the client View Page</h1>
		
		<div class="btns">
		
		<form class="form" action="createQuote" method="post"> 
	           <input class="btn" type="submit" value="Request Quote" />
	    </form>
		
			
			
			<form class="form" action="quotes" method="post"> 
	          <input type="hidden" name="clientId" value="${clientId}" />
	        
			  <input class="btn" type="submit" value="View Quotes" />
	    	</form>
	    	
	    	<form class="form" action="viewBills" method="post"> 
	          <input type="hidden" name="clientId" value="${clientId}" />
			  <input class="btn" type="submit" value="View Bills" />
	    	</form>
			
		</div>
		
		<a class="logout" href="login.jsp" target ="_self" > Logout</a><br><br> 
		
		 <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>List of Quotes Requests</h2></caption>
            <thead>
            
            <tr>
                <th>Req Id</th>
                <th>Request Date</th>
                <th>Status</th>
                <th>Note</th>
         
            </tr>
            </thead>
            <tbody>
           <c:forEach var="quote" items="${listQuoteRequests}">
           		
           		 <tr style="text-align:center">
                        <td><c:out value="${quote.quoteId}" /></td>
                        <td><c:out value="${quote.requestedDate}" /></td>
                        <td><c:out value="${quote.status}" /></td>
                        <td><c:out value="${quote.note}" /></td>
                </tr>
           		
               
            </c:forEach>
            </tbody>
        </table>
	</div>

	</div>
</body>
</html>
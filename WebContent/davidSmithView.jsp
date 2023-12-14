<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>

<style>

body{
background: #F0F8FF;

}


* {
  font-family: sans-serif; /* Change your font family */
}

.logout{
	position: absolute;
	left: 1300px;
	top:50px;

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

.datainit{
    margin-top: 66px;
    font-weight: 700;
    color: #fff !important;
    line-height: 1.25;
    -ms-flex-pack: center;
    justify-content: center;
    padding: 0.469rem 0.75rem;
    border: none;
    background: green;
    width: 21%;
    max-width: 450px;
    height: 42px;
    cursor: pointer;
    font-size: 1.3125rem;
    margin-top: 0.5rem;
    margin-bottom: -0.5rem;
    text-transform: none;
    text-align: center;
    align-items: center;
    -ms-flex-pack: center;
    border-radius: 1.5rem;
    margin: 36px;

}

.btn{

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
    width: 100%;
    max-width: 450px;
    height: 35px;
    cursor: pointer;
    font-size: 1.3125rem;
    text-transform: none;
}


.btns{
	display: flex;
	flex-direction: row;
	gap: 10%;
	justify-content: center;

}

</style>
</head>
<body>

<div align = "center">
	
	
	<a class="logout" href="login.jsp"target ="_self" > Logout</a><br><br> 
	
	<h2>Welcome! David Smith</h2>
	
	<div class="btns">
		<form action="viewOrders" method="post">
			<input class="btn" type="submit" value="View Orders"/>
		</form>
	
		<form action="quoteHistory" method="post">
			<input class="btn" type="submit" value="Quote History"/>
		</form>
	
	</div>


    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>List of Quotes Requests/Negotiations</h2></caption>
            <p style="color:green">${message}</p>
            <tr>
                <th>Quote Id</th>
                <th>client Id</th>
                <th>Request Date</th>
                <th>price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th> Client status</th>
                <th>Status</th>
                <th>Note</th>
         
            </tr>
           <c:forEach var="quote" items="${listQuoteRequests}">
                <tr style="text-align:center">
                    <form action="updateQuote" method="post"> <!-- Form for each row -->
                        <input type="hidden" name="quoteId" value="${quote.quoteId}" />
                        <input type="hidden" name="clientId" value="${quote.clientId}" />
                         <td><input type="number"  value="${quote.clientId}" disabled /></td>
                        <td><input type="number" value="${quote.quoteId}" disabled /></td>
                        <td><input type="date" name="requestDate" value="${quote.requestedDate}" disabled /></td>
                         <td><input type="number" name="price" value="${quote.price}" /></td>
                        <td><input type="date" name="startDate" value="${quote.startDate}" /></td>
                        <td><input type="date" name="endDate" value="${quote.endDate}" /></td>
                        <td><input type="text"  value="${quote.status}" disabled /></td>
                        <td>
                            <select name="status">
                            	<option value="negotiate"  ${quote.status eq 'negotiate' ? 'selected' : ''}>Negotiate</option>
                                <option value="generate" ${quote.status eq 'Generate' ? 'selected' : ''}>Generate</option>
                            </select>
                        </td>
                        <td><input type="text" name="note" value="${quote.note}" /></td>
                        <td>
                            <input type="submit" value="Update" />
                        </td>
                    </form>
                     <form action="treeDetails" method="post"> 
            			<td>
			                <input type="hidden" name="quoteId" value="${quote.quoteId}" />
			                <input type="submit" value="View Tree Details" />
           				</td>
       				 </form>
                    
                </tr>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>
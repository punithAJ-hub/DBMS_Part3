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


/* .datainit{
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
} */

</style>
</head>
<body>

<div align = "center">
	
	
	<a class="back" onclick="goBack();"target ="_self" > Back</a><br><br> 


	<h4 style="color:green" >${message}</h4>
    <div align="center">
        <table border="1" cellpadding="6">
            <caption><h2>List of Orders</h2></caption>
            <tr>
                <th>Order Id</th>
                <th>Client Id</th>
                <th>Quote Id</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Total Price</th>
                <th>Note</th>
         
            </tr>
          
		    <c:forEach var="order" items="${orders}">
		        <tr style="text-align:center">
		        
		            <form action="generateBill" method="post"> 
		            	<input value ="${order.orderId}" name="orderId" hidden>
		            	<input value ="${order.clientId}" name="clientId" hidden>
		            	<input value ="${order.quoteId}" name="quoteId" hidden>
		                <td><c:out value="${order.orderId}" /></td>
		                <td><c:out value="${order.clientId}" /></td>
		                <td><c:out value="${order.quoteId}" /></td>
		                <td><c:out value="${order.startDate}" /></td>
		                <td><c:out value="${order.endDate}" /></td>
		                <td><input value ="${order.totalPrice}" name="totalPrice" ></td>
		                <td><input  name="note" value="" placeholder="Write a note" ></td>
		                <td><input type="submit" value="Generate Bill"/></td>
		            </form>
		        </tr>
		    </c:forEach>	
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
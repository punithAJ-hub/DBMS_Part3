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


</style>
</head>
<body>

<div align = "center">
	
	<div class="container">

  <div title="Back to Menu" class="backArrow">
    &#10140;
  </div>
  
</div>
	
	<a class="back" onclick="goBack();"target ="_self" > Back</a><br><br> 



    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>List of Bills</h2></caption>
            <thead>
            
            <tr>
                <th>Bill Id</th>
                <th>Order Id</th>
                <th>Total price</th>
                <th>Payment Status</th>
                <th>Generated On</th>
                <th>Paid On </th>
                <th>Note</th>
                <th>Action</th>
         
            </tr>
            </thead>
          	<tbody>
		    <c:forEach var="bill" items="${bills}">
		        <tr style="text-align:center">
		        
		            <form action="reject" method="post"> 
		                <td><c:out value="${bill.billId}" /></td>
		                <td><c:out value="${bill.orderId}" /></td>
		                <td><c:out value="${bill.totalPrice}" /></td>
		                <td><c:out value="${bill.status}" /></td>
		                <td><c:out value="${bill.generated_on}" /></td>
		                <td><c:out value="${bill.paid_on}" /></td>
		              	<td><input type="text" name="note" value="${bill.note}" /></td>
		              	<input name="billId" hidden value="${bill.billId}"/>
		                <td>
		                <input type="submit" value="reject" />
		                </td>
		                
		            </form>
		            
		             <form action="pay" method="post">
		            	 <input name="billId" hidden value="${bill.billId}"/>
		            	 <td><input type="submit" value="pay" /></td>
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
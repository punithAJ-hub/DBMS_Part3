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
            <caption><h2>Big Clients</h2></caption>
            <thead>
            <tr>
                <th>Client Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email </th>
                <th>Phone Number</th>
                <th>Tree Count</th>
            </tr>
          </thead>
         	<tbody>
         	
		    <c:forEach var="client" items="${bigClients}">
		        <tr style="text-align:center">
		                <td><c:out value="${client.clientId}" /></td>
		                <td><c:out value="${client.firstName}" /></td>
		                <td><c:out value="${client.lastName}" /></td>
		                <td><c:out value="${client.email}" /></td>
		                <td><c:out value="${client.phoneNo}" /></td>
		                <td><c:out value="${client.credit_card_info}" /></td>
		        </tr>
		    </c:forEach>	
		    
		    </tbody>
        </table>
	</div>
	
    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Easy Clients</h2></caption>
            <thead>
            <tr>
                <th>Client Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email </th>
                <th>Phone Number</th>
            </tr>
          </thead>
         	<tbody>
         	
		    <c:forEach var="client" items="${easyClients}">
		        <tr style="text-align:center">
		                <td><c:out value="${client.clientId}" /></td>
		                <td><c:out value="${client.firstName}" /></td>
		                <td><c:out value="${client.lastName}" /></td>
		                <td><c:out value="${client.email}" /></td>
		                <td><c:out value="${client.phoneNo}" /></td>
		        </tr>
		    </c:forEach>	
		    
		    </tbody>
        </table>
	</div>
	
	 <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>One Tree Quotes</h2></caption>
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
         	
		    <c:forEach var="quote" items="${oneTreeQuotes}">
		        <tr style="text-align:center">
		                <td><c:out value="${quote.quoteId}" /></td>
		                <td><c:out value="${quote.clientId}" /></td>
		                <td><c:out value="${quote.requestedDate}" /></td>
		                <td><c:out value="${quote.price}" /></td>
		                <td><c:out value="${quote.startDate}" /></td>
		                <td><c:out value="${quote.endDate}" /></td>
		                <td><c:out value="${quote.status}" /></td>
		                <td><c:out value="${quote.note}" /></td>

		        </tr>
		    </c:forEach>	
		    
		    </tbody>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Prospective Clients</h2></caption>
            <thead>
            <tr>
                <th>Client Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email </th>
                <th>Phone Number</th>
            </tr>
          </thead>
         	<tbody>
         	
		    <c:forEach var="client" items="${prospectiveClients}">
		        <tr style="text-align:center">
		                <td><c:out value="${client.clientId}" /></td>
		                <td><c:out value="${client.firstName}" /></td>
		                <td><c:out value="${client.lastName}" /></td>
		                <td><c:out value="${client.email}" /></td>
		                <td><c:out value="${client.phoneNo}" /></td>
		        </tr>
		    </c:forEach>	
		    
		    </tbody>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Over Due Bills</h2></caption>
            <thead>
            
            <tr>
                <th>Bill Id</th>
                <th>Order Id</th>
                <th>Total price</th>
                <th>Payment Status</th>
                <th>Generated On</th>
                <th>Paid On </th>
               
         
            </tr>
            </thead>
          	<tbody>
		    <c:forEach var="bill" items="${overDueBills}">
		        <tr style="text-align:center">
		
		                <td><c:out value="${bill.billId}" /></td>
		                <td><c:out value="${bill.orderId}" /></td>
		                <td><c:out value="${bill.totalPrice}" /></td>
		                <td><c:out value="${bill.status}" /></td>
		                <td><c:out value="${bill.generated_on}" /></td>
		                <td><c:out value="${bill.paid_on}" /></td>
		                
		        </tr>
		    </c:forEach>	
		    </tbody>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Bad Clients</h2></caption>
            <thead>
            <tr>
                <th>Client Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email </th>
                <th>Phone Number</th>
            </tr>
          </thead>
         	<tbody>
         	
		    <c:forEach var="client" items="${badClients}">
		        <tr style="text-align:center">
		                <td><c:out value="${client.clientId}" /></td>
		                <td><c:out value="${client.firstName}" /></td>
		                <td><c:out value="${client.lastName}" /></td>
		                <td><c:out value="${client.email}" /></td>
		                <td><c:out value="${client.phoneNo}" /></td>
		        </tr>
		    </c:forEach>	
		    
		    </tbody>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Good Clients</h2></caption>
            <thead>
            <tr>
                <th>Client Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email </th>
                <th>Phone Number</th>
            </tr>
          </thead>
         	<tbody>
         	
		    <c:forEach var="client" items="${goodClients}">
		        <tr style="text-align:center">
		                <td><c:out value="${client.clientId}" /></td>
		                <td><c:out value="${client.firstName}" /></td>
		                <td><c:out value="${client.lastName}" /></td>
		                <td><c:out value="${client.email}" /></td>
		                <td><c:out value="${client.phoneNo}" /></td>
		        </tr>
		    </c:forEach>	
		    
		    </tbody>
        </table>
	</div>
	<div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Highest Tree</h2></caption>
            <thead>
            <tr>
                <th>Tree Id</th>
                <th>Size</th>
                <th>Height</th>
               
         
            </tr>
            
            </thead>
            <tbody>
           <c:forEach var="tree" items="${highestTrees}">
                <tr style="text-align:center">
                      
                        <td><c:out value="${tree.treeId}" /></td>
                        <td><c:out value="${tree.size}" /></td>
                        <td><c:out value="${tree.height}" /></td>
                        
                </tr>
            </c:forEach>
            </tbody>
        </table>
	</div>
	
	<div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>Statistics</h2></caption>
            <thead>
            <tr>
                <th>Client ID</th>
                <th>first Name</th>
                <th>Last Name</th>
                <th>Total Trees</th>
                <th>Total Amount Due</th>
                <th>Total Amount Paid</th>
                <th>Work Done Date</th>
               
         
            </tr>
            
            </thead>
            <tbody>
           <c:forEach var="statistics" items="${stats}">
                <tr style="text-align:center">
                      
                        <td><c:out value="${statistics.client_id}" /></td>
                        <td><c:out value="${statistics.first_name}" /></td>
                        <td><c:out value="${statistics.last_name}" /></td>
                        <td><c:out value="${statistics.total_trees}" /></td>
                        <td><c:out value="${statistics.amount_due}" /></td>
                         <td><c:out value="${statistics.amount_paid}" /></td>
                          <td><c:out value="${statistics.workDoneDate}" /></td>
                        
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
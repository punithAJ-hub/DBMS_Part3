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
    width: 224px;
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
    height : 20px;
    -ms-flex-pack: center;
    justify-content: centre;
    padding: 0.469rem 0.75rem;
    border: none;
    background: indianred;
    display: inline-block;
    max-width: 450px;
    cursor: pointer;
    font-size: 1.3125rem;
    text-transform: none;
    position: absolute;
    left : 1200px;
    top : 50px;
    
}

.frmBtns{
display: flex;
justify-content: center;
align-items : center;

}

</style>
</head>
<body>

<div align = "center">
	
	<div class="frmBtns">
	
	<form action = "initialize">
		<input class="datainit" type = "submit" value = "Initialize Database"/>
	</form>
	

	<form action = "generateReport">
		<input class="datainit" type = "submit" value = "ViewReport"/>
	</form>
	
	</div>
	
	<a class="logout" href="login.jsp"target ="_self" > Logout</a><br><br> 

    <div align="center">
        <table border="1" cellpadding="6" class="content-table">
            <caption><h2>List of Users</h2></caption>
            <thead>
            <tr>
                <th>User Name</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Password</th>
                <th>User Role</th>
            </tr>
            </thead>
            <tbody>
           
            <c:forEach var="users" items="${listUser}">
                <tr style="text-align:center">
                    <td><c:out value="${users.userName}" /></td>
                    <td><c:out value="${users.firstName}" /></td>
                    <td><c:out value="${users.lastName}" /></td>
                    <td><c:out value="${users.password}" /></td>
                    <td><c:out value="${users.userRole}" /></td>
            </c:forEach>
            
             </tbody>
        </table>
	</div>
	</div>

</body>
</html>
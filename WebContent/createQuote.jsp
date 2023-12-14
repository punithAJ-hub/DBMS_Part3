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


.errormsg{
color:#FF0000;
}
.title{
color: #002f87
}


.submit_btn{
position: absolute;
left : 1000px;
top: 600px;
}

.user_name{
display:flex;
margin:32px;
    border: 1px solid #029ce8;
    border-radius: 6px;
    box-sizing: border-box;
    color: #474747;
    font-family: Roboto,Arial,sans-serif;
    font-size: 1.125rem;
    font-stretch: normal;
    font-style: normal;
    font-weight: 400;
    height: 2.5rem;
    letter-spacing: normal;
    line-height: 1.33;
    padding: .28125rem 1rem;
    transition: background-color 1s ease-in-out 0s
}

.button{
display:flex;
    border-radius: 1.5rem;
    font-family: BrandonText, Arial, sans-serif;
    font-weight: 700;
    color: #fff !important;
    line-height: 1.25;
    -ms-flex-pack: center;
    justify-content: center;
    padding: 0.469rem 0.75rem;
    border: none;
    background: #fe9e1f;
    display: inline-block;
    width: 18%;
    max-width: 450px;
    height: 42px;
    cursor: pointer;
    font-size: 1.3125rem;
    margin-top: 0.5rem;
    margin-bottom: -0.5rem;
    text-transform: none;
}

.btns{
display: flex;
flex-direction : row;
 justify-content: center;
}


.register_button , .submit_btn{
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
    margin: 36px;
    
}

body{
background: #F0F8FF;

}



</style>
</head>
<body>
 <center>	<h1 class="title"> Request for Quote </h1> </center>
	
    <div align="center">
       <%--  <table border="1" cellpadding="6">
            <caption><h2>List of Users</h2></caption>
            <tr>
                <th>User Name</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Password</th>
                <th>User Role</th>
            </tr>
            <c: if(${treesAdded}!=null)>
            <c:forEach var="tree" items="${treesAdded}">
                <tr style="text-align:center">
                    <td><c:out value="${tree.treeId}" /></td>
                    <td><c:out value="${tree.firstName}" /></td>
                    <td><c:out value="${tree.lastName}" /></td>
                    <td><c:out value="${tree.password}" /></td>
                    <td><c:out value="${tree.userRole}" /></td>
            </c:forEach>
            <c: \">
        </table> --%>
        
         <p class="errormsg"> ${error} </p>
			<form >
				<div class="btns" align="center">
					<p class="register_button"><a href="addTree.jsp" target="_self">Add Tree Details</a><p>
					<!-- <label for="note">Note : </label><br>
					<textarea class="note" id="note" type="text" name="note" rows=4  cols=80 placeholder="note" autofocus> </textarea> -->
					<p class="">
    					<a href="submitRequest" class = "submit_btn" ">Submit</a>
					</p>
				</div>
			
			</form>
        
         <caption><h2>List of Trees Added</h2></caption>
		    <table class="content-table">
		    <thead>
		        <tr>
		            <th>Tree ID</th>
		            <th>Size</th>
		            <th>Height</th>
		            <!-- <th>Is Near House</th> -->
		            <th>Note</th>
		        </tr>
		        </thead>
		        <tbody>
		        <c:forEach var="tree" items="${treesAdded}">
		            <tr style="text-align:center">
		                <td><c:out value="${tree.treeId}" /></td>
		                <td><c:out value="${tree.size}" /></td>
		                <td><c:out value="${tree.height}" /></td>
		                <%-- <td><c:out value="${tree.isNearHouse}" /></td> --%>
		                <td><c:out value="${tree.note}" /></td>
		            </tr>
		        </c:forEach>
		        </tbody>
		    </table>
		    
		   

		</div>
		<script>
    	function goToPage(url) {
       		 window.location.href = url;
    		}
		</script>

</body>
</html>
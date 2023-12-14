<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head><title>Registration</title>
<style>

body{
background: #F0F8FF;

}


.registerpage{
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

.userrole{
	font-size: 24px;
    fontsize: 59px;
    right: 126px;
    position: relative;
    }
    
    .regisbutton{
    display: flex;
    margin-top: 66px;
    margin-bottom: 42px;
    font-weight: 700;
    color: #fff !important;
    line-height: 1.25;
    -ms-flex-pack: center;
    justify-content: center;
    padding: 0.469rem 0.75rem;
    border: none;
    background: deepskyblue;
    width: 13%;
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
    display: flex;
    margin-top: 66px;
    margin-top: 66px;
    margin-bottom: 42px;
    }  
    
  .title{
color: #002f87
}  

.errormsg{
color:#FF0000;

}
    

</style>

<script>



</script>

</head>
<body>

<center><h1 class="title"> Welcome to Registration page </h1> </center>
	<div align="center">
		<p class="errormsg"> ${errorOne } </p>
		<p class="errormsg"> ${errorTwo } </p>
		<form action="register">
		
		<input type="text" class="registerpage" name="userName" placeholder="Email" size="45"  value="example@gmail.com" onfocus="this.value=''">
		<input type="text" class="registerpage" name="firstName" placeholder="Firstname" size="45" value="FirstName" onfocus="this.value=''">
		<input type="text" class="registerpage" name="lastName" placeholder="Lastname" size="45" value="LastName" onfocus="this.value=''">
		<input type="password" class="registerpage" name="password" placeholder="Password" size="45" value="password" onfocus="this.value=''">
		<input type="password" class="registerpage" name="confirmation" placeholder="Confirm Passoword" size="45" value="password" onfocus="this.value=''">
		  
		<div class="userrole">User role:<label for="userRole"></label>
						  <select id="userRole" name="userRole">
						    <option value="David Smith">David Smith</option>
						    <option value="Clients">Clients</option>
						    <option value="Admin Root">Admin Root</option>
						  </select>
		</div>
		<input type="text" class="registerpage" name="phoneNo" placeholder="Phone Number" size="45" value="phoneNo" onfocus="this.value=''">
		<input type="text" class="registerpage" name="address" placeholder="Address" size="45" value="address" onfocus="this.value=''">
		<input type="text" class="registerpage" name="creditCardInfo" placeholder="Credit Card Number" size="45" value="creditCardInfo" onfocus="this.value=''">
		 
					
						  <input class= "regisbutton"type="submit" value="Register"/>
						<p class="returntologin">  <a href="login.jsp" target="_self">Return to Login Page</a></p>
			
		</form>
	</div>
</body>
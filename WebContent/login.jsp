<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login to Database</title>
<style>

body{
background: #F0F8FF;

}


.errormsg{
color:#FF0000;
}
.title{
color: #002f87
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


.register_button{
    display: block;
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



</style>
</head>
<body>
 <center>	<h1 class="title"> Welcome to Login page </h1> </center>
	<div align="center">
		<p class="errormsg"> ${loginStr} </p>
		<form action="login" method="post">
		<input class="user_name" tyoe="text" name="userName" size="45" placeholder="Username" autofocus>
		<input class="user_name" tyoe="text" name="password" size="45" placeholder="Password" autofocus>
		<input class="button" type="submit" value="Login"/>
		<p class="register_button"><a href="register.jsp" target="_self">Register Here</a><p>
		</form>
	</div>
</body>
</html>
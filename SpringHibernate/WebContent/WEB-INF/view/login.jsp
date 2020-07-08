<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<form action="login" method="post">
			

			<h2>Login Here</h2>
			
			<h4 style="color: red">${error}</h4>
			<h4 style="color: green">${reset}</h4>
			<div class="inp">
				<input type="email" name="email" placeholder="Email" required="required" value="${email}">
			</div>
			<div class="inp pass">
				<input id="pass" type="password" name="password" placeholder="Password" required="required">
				<i role="button" class="far fa-eye-slash hide" onclick="show(true,this)"></i>
				<i role="button" class="far fa-eye show" onclick="show(false,this)" style="display: none"></i>
			</div>
			
			<input type="submit" value="Login"> or 
			<a href="regform">Register</a> <br>
			<a href="forgotpass">Forgot Password</a>
			
			</form>
</body>
</html>
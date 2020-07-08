<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
<form action="reset" method="post">
			

			<h2>Enter Mail id Here</h2>
			
			<div class="inp">
				<input type="email" name="email" placeholder="Email" required="required" value="${email}">
			</div>
			
			<input type="submit" value="Reset Password"> or 
			<a href="loginform">Login</a>
			
			
			</form>
</body>
</html>
<!DOCTYPE html>
<%@page import="com.springhiber.web.dto.RegisterIn"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.springhiber.web.model.dao.RegisterDao"%>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Sign up</title>
		<link rel="stylesheet" type="text/css" href="./css/login2.css">
		
		<link rel="stylesheet" type="text/css" href="./css/all.css">
		
		<link rel="icon" href="./media/title.png">
		<script src="./js/login2.js"></script>
		<script type="text/javascript" src="https://use.fontawesome.com/3f27990a8f.js"></script>
		
	</head>
	<body>
		<div class="main">
			<form action="register" method="post">
			<div class="logo">
				<i class="far fa-user-circle user"></i>
				<input type="file" id="imgupload" style="display:none"/>
				<label for="imgupload"><i role="button" class="fas fa-cloud-upload-alt"></i></label>
				
			</div>

			<h2>Sign Up here</h2>
			
			<div class="inp">
				<input type="text" name="username" placeholder="User name" required="required">
			</div>
			<div class="inp">
				<input type="email" name="email" placeholder="Email" required="required">
			</div>
			<div class="inp pass">
				<input id="pass" type="password" name="password" placeholder="Password" required="required"> 
				<i role="button" class="far fa-eye-slash hide" onclick="show(true,this)"></i>
				<i role="button" class="far fa-eye show" onclick="show(false,this)" style="display: none"></i>
			</div>
			<div class="inp">
				<input type="text" name="contactnumber" placeholder="Contact number" pattern="^\d{10}$">
			</div>
			<input type="submit" value="Sign Up"> or 
			<a href="loginform">Login</a>
			</form>
		</div>
	</body>
</html>
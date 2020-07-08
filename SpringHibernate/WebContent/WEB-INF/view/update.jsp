<!DOCTYPE html>
<%@page import="com.mysql.cj.xdevapi.SessionFactory"%>
<%@page import="com.springhiber.web.dto.RegisterIn"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.springhiber.web.model.dao.RegisterDao"%>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Update</title>
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

			<h2>Update here</h2>
			
			<div class="inp">
				<input type="text" name="username" placeholder="User name" required="required" value="${user.username}">
			</div>
			<div class="inp">
				<input type="email" name="email" placeholder="Email" required="required" value="${user.email}" readonly="readonly">
			</div>
			<div class="inp pass">
				<input id="pass" type="password" name="password" placeholder="Password" required="required" value="${user.password}"> 
				<i role="button" class="far fa-eye-slash hide" onclick="show(true,this)"></i>
				<i role="button" class="far fa-eye show" onclick="show(false,this)" style="display: none"></i>
			</div>
			<div class="inp">
				<input type="text" name="contactnumber" placeholder="Contact number" pattern="^\d{10}$" value="${user.contactnumber}">
			</div>
			<input type="submit" value="Update">
			</form>
		</div>
	</body>
</html>
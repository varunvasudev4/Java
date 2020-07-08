<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="org.hiber.covid.dao.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Peoples In Same Place</title>
		<link rel="icon" href="./resourses/virus.png">
		<link rel="stylesheet" href="./prsamepl.css" >
		<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@500&display=swap" rel="stylesheet">
</head>
<body>
	<div id="nav">
		<a href="Registed.jsp">HOME</a>
		<a href="positives.jsp" style="left: 250px;">POSITIVE ONLY</a>
		<a href="#" style="left: 490px;">PEOPLES BY PLACE</a>
	</div>
<%
PersonDAO dao = new PersonDAO();
Map<String,List<String>> prsamepl = dao.sameplace();
for(Map.Entry<String,List<String>> entry : prsamepl.entrySet()){%>

	<div class="main">
		<div class="place"><%=entry.getKey()%></div>
		<div class="people">
		<ul>
		<%for(String st : entry.getValue()){%>
			<li><%=st%></li>
			<%}%>
		</ul>
		</div>
	</div>


<%} %>
</body>
</html>
<%@page import="org.hiber.covid.dto.VisitedPlaces"%>
<%@page import="org.hiber.covid.dto.Person"%>
<%@page import="java.util.List"%>
<%@page import="org.hiber.covid.dao.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registered</title>
		<link rel="icon" href="./resourses/virus.png">
		<link rel="stylesheet" href="./Registed.css" >
		<link rel="stylesheet" href="./nav.css" >
		<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@500&display=swap" rel="stylesheet">
		<script src="cform.js"></script>
	</head>
<%
PersonDAO dao = new PersonDAO();
List<Person> persons = dao.showAll();
%>
<body>
	<div id="nav">
		<a href="#">HOME</a>
		<a href="positives.jsp" style="left: 250px;">POSITIVE ONLY</a>
		<a href="prsamepl.jsp" style="left: 490px;">PEOPLES BY PLACE</a>
	</div>
	<%@ include file="common.jsp" %>
</html>
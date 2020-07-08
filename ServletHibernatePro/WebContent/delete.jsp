<%@page import="org.hiber.covid.dao.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
PersonDAO dao = new PersonDAO();
dao.deleteById(request.getParameter("id"));
response.sendRedirect("Registed.jsp");
%>
</body>
</html>
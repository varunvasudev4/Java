<%@page import="org.hiber.covid.dto.VisitedPlaces"%>
<%@page import="java.util.List"%>
<%@page import="org.hiber.covid.dao.PersonDAO"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hiber.covid.utils.SessionFactoryManger"%>
<%@page import="com.mysql.cj.xdevapi.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Corona Form</title>
		<link rel="icon" href="./resourses/virus.png">
		<link rel="stylesheet" href="./cform.css" >
		<link href="https://fonts.googleapis.com/css2?family=Manrope:wght@500&display=swap" rel="stylesheet">
		<script src="cform.js"></script>
	</head>
	<body>
	
	
	
	 <fieldset id="out">
		<legend><h3>Basic Information</h3></legend>
		<form action="fs" method="post">
			<table cellspacing="20px" >
				<tr>
					<td><label>Name</label></td>
					<td><input type="text" name="name" placeholder="Alex Josh" autofocus="autofocus" class="inp" required="required"/></td>
				</tr>
				<tr>
					<td><label>Gender</label> <br><br><br></td>
					<td>
						<input type="radio" name="gender" value="M" class="selgend">Male</input><br>
						<input type="radio" name="gender" value="F" class="selgend">Female</input><br>
						<input type="radio" name="gender" value="O" class="selgend">Others</input>
					</td>
				</tr> 
				<tr>
					<td><label>Age</label></td>
					<td><input type="number" name="age" placeholder="18" required="required" class="inp"/> </td>
				</tr>
				
				<tr>
					<td style="position:relative; top: -69px;"><label>Address</label> </td>
					<td><textarea name="addr" rows="8" cols="15" class="inp"
						style="border-right: 1px solid black" placeholder="Home Name/No.
																		   Street
																		   Place
																		   Pin"></textarea> </td>
				</tr>
				
				<tr>
					<td><label>Mobile</label></td>
					<td><input type="tel" placeholder="9999999999"
					 name="mobile" maxlength="15" min="14"
					 pattern="^\d{10}$" class="inp" required="required"/> </td>
				</tr>
				<tr>
					<td colspan="2">Are you Covid-19 positive ?
						<input type="checkbox" name="status" value="true" onclick="vpsf()" id="oneC">
						<input type="checkbox" onclick="vpsf2()" id="twoC" checked="checked" style="display: none">
						
					</td>
				</tr>
				<tr>
				<td colspan="2">
				 
				 <fieldset id="vps">
					<legend><h4>Visited Places</h4></legend>
			 		<div>
						<input list="vpls" class="inp2" name="vp1" placeholder="Place,District,State"><br>
						<input list="vpls" class="inp2" name="vp2" placeholder="Place,District,State"><br>
						<input list="vpls" class="inp2" name="vp3" placeholder="Place,District,State"><br>
						<input list="vpls" class="inp2" name="vp4" placeholder="Place,District,State">
			 		</div>
				</fieldset>
				 
				</td>
				</tr>
				<tr>
					<td><input type="reset" value="Reset" class="resub" /> </td>
					<td><input type="submit" value="Submit" class="resub"/></td>
				</tr>
			</table>	
		</fieldset>	
		
		</form>
	<datalist id="vpls">
	<%
	PersonDAO dao = new PersonDAO();
	List<VisitedPlaces> vpls = dao.vplss();
	
	for(VisitedPlaces st : vpls){
		String vp ="";
		vp+=st.getPlace()+","+st.getDist()+","+st.getState()+","+st.getId();
	%>
	<option value=<%=vp%>>
	<%}%>
	</datalist>
	</body>
</html>
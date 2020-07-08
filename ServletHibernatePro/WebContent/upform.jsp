<%@page import="org.hiber.covid.dto.Person"%>
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
		<style type="text/css">
		input{
			text-transform: capitalize;
		}
		</style>
	</head>
	<body>
	<%
	PersonDAO dao = new PersonDAO();
	Person p = dao.getById(request.getParameter("id"));
	%>
	
	
	 <fieldset id="out">
		<legend><h3>Basic Information</h3></legend>
		<form action="fs" method="post">
			<input type="hidden" name="id" value="<%=p.getPnid()%>">
			<input type="hidden" name="aid" value="<%=p.getAddr().getAid()%>">
			<table cellspacing="20px" >
				<tr>
					<td><label>Name</label></td>
					<td><input type="text" name="name" 
								placeholder="Alex Josh" 
								autofocus="autofocus" 
								class="inp" 
								value="<%=p.getName()%>"
								required="required"/></td>
				</tr>
				<tr>
					<td><label>Gender</label> <br><br><br></td>
					<td>
						<input type="radio" name="gender" value="M" class="selgend" <%=p.getSex()=='M'?"checked":""%>>Male</input><br>
						<input type="radio" name="gender" value="F" class="selgend" <%=p.getSex()=='F'?"checked":""%>>Female</input><br>
						<input type="radio" name="gender" value="O" class="selgend" <%=p.getSex()=='O'?"checked":""%>>Others</input>
					</td>
				</tr> 
				<tr>
					<td><label>Age</label></td>
					<td><input type="number" 
								name="age" 
								placeholder="18" 
								required="required"
								value="<%=p.getAge()%>" 
								class="inp"/> </td>
				</tr>
				
				<tr>
						<%
						String addr =p.getAddr().getHn().trim()+"\n"
										+p.getAddr().getStreet().trim()+"\n"
										+p.getAddr().getPlace().trim()+"\n"
										+(""+p.getAddr().getPin()).trim();
						%>
					<td style="position:relative; top: -69px;"><label>Address</label> </td>
					<td><textarea name="addr" 
									rows="8" 
									cols="15" 
									class="inp"
									style="border-right: 1px solid black"
									 
									placeholder="Home Name/No.
													Street
													Place
													Pin"><%=addr%>							
						</textarea> </td>
				</tr>
				
				<tr>
					<td><label>Mobile</label></td>
					<td><input type="tel" 
								placeholder="9999999999"
					 			name="mobile" 
					 			pattern="^\d{10}$" 
					 			class="inp"
					 			value="<%=p.getContactnum()%>" 
					 			required="required"/> </td>
				</tr>
				<tr>
					<td colspan="2">Are you Covid-19 positive ?
						<input type="checkbox" name="status" value="true" onclick="vpsf()" id="oneC" <%=p.getStatus()?"checked":""%>>
						<input type="checkbox" onclick="vpsf2()" id="twoC" checked="checked" style="display: none">
						
					</td>
				</tr>
				<tr>
				<td colspan="2">
				 
				 <fieldset id="vps">
					<legend><h4>Visited Places</h4></legend>
			 		<div>
			 		<%
			 		List<VisitedPlaces> vptemp = p.getVisitedPlaces();
			 		for(int i=0;i<vptemp.size();i++){
			 			String val = vptemp.get(i).getPlace()+","
			 							+vptemp.get(i).getDist()+","
			 							+vptemp.get(i).getState()+","
			 							+vptemp.get(i).getId();
			 			String name = "vp"+(i+1);
			 		%>
			 			<input list="vpls" class="inp2" name="<%=name%>" placeholder="Place,District,State" value="<%=val%>"><br>
			 		<%
			 		}
			 		for(int i=vptemp.size();i<4;i++){
			 			String name = "vp"+(i+1);
			 		%>
			 			<input list="vpls" class="inp2" name="<%=name%>" placeholder="Place,District,State"><br>
			 		<%
			 		}
			 		%>
			 		
						
						
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
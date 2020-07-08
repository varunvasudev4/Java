	<table>
		<tr id="header">
			<th >Person Id</th>
			<th >Name</th>
			<th >Gender</th>
			<th >Age</th>
			<th >Address</th>
			<th >Contact Number</th>
			<th >Covid-19 Status</th>
		</tr>
	
		<%
		int top = 51;
		for(Person p : persons){
			
		%>
		
		<tr>
			<td>
			<a class="edit" href="upform.jsp?id=<%=p.getPnid()%>"><img  class="editimg" src="./resourses/edit.png"></a>
			<%=p.getPnid()%>
			</td>
			<td><%=p.getName().toLowerCase()%></td>
			<td><%=p.getSex()%></td>
			<td><%=p.getAge()%></td>
			<td>
			<!-- role="botton" onclick="document.getElementById('').style.display='block' -->
				<div onmouseover="document.getElementById('<%=p.getPnid()%>').style.display='block'" 
					 onmouseout="document.getElementById('<%=p.getPnid()%>').style.display='none'" >
				<%=p.getAddr().getAid()%>
				</div>
				<div id="<%=p.getPnid()%>" 
					 class="addr">
				<%=p.getAddr().getHn().toLowerCase()%><br>
				<%=p.getAddr().getStreet().toLowerCase()%><br>
				<%=p.getAddr().getPlace().toLowerCase()%><br>
				<%=p.getAddr().getPin()%><br><br>
				<%if(p.getStatus()){ %>
				<fieldset>
				<legend>Visited Places</legend>
				<ul>
				<%for(VisitedPlaces vptemp : p.getVisitedPlaces()){ %>
					<li><%=vptemp.toString()%></li>

				<%
				}
				
				}
				%>
				</ul>
				</fieldset>
				</div>
			</td>
			<td ><a class="call" href="tel:<%=p.getContactnum()%>">
			<img class="callimg" alt="" src="./resourses/call.png">
			<%=p.getContactnum()%></a></td>
			<%
			String status = "<h3 style='color:";
			if(p.getStatus()){
				status += "red";
			}
			else{
				status += "green";
			}
			status +="'>"+(p.getStatus()?"+ve":"-ve")+"</h3>";
			
			%>
			<td><%=status%>
				<a class="delete" onclick="if(confirm('Delete')){location.href='delete.jsp?id=<%=p.getPnid()%>';}" >-</a>
			</td>
		</tr>
		<%} %>
	</table>
</body>